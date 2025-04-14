import json
from src.ai_model.call_claude import call_claude
from src.utils import read_file, cdm_trades_matching
from src.cdm_api_functions import observation_call, execution_call, validation_call

def lambda_handler(event, context):
    system_instruction_path_gpt2 = "data/instructions/instructions_gpt_cdm_mapper.txt"
    system_instruction_content_gpt2 = read_file(system_instruction_path_gpt2)

    hyperparameters = {
        "max_tokens": 1000,
        "temperature": 0.1,
        "top_p": 0.1
    }

    # Retrieve the ISO message produced from a previous conversion step.
    user_iso_content = event.get("body", "")

    # Calling GPT CDM Mapper_______________________________________________________________
    iso_to_cdm_response = call_claude(
        system_instructions=system_instruction_content_gpt2,
        user_instructions=user_iso_content,
        hyperparameters=hyperparameters
    )
    iso_to_cdm_json = json.loads(iso_to_cdm_response)
    trade_matching_path = cdm_trades_matching(user_iso_content)

    #Calling CDM API______________________________________________________________________
    
    proposedevent = observation_call(trade_matching_path[0], iso_to_cdm_json)['payload']
    acceptedworkflowstep = execution_call(proposedevent)['payload']
    validation_result = validation_call(acceptedworkflowstep['acceptedStep'])


    return {
        "statusCode": 200,
        "body": {"AcceptedWorkFlowStep" : acceptedworkflowstep,
                 "ValidationResult" : validation_result}
    }
