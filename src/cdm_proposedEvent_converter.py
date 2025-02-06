from src.ai_model.call_claude import call_claude
from src.utils import read_file, cdm_trades_matching

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

    iso_to_cdm_response = call_claude(
        system_instructions=system_instruction_content_gpt2,
        user_instructions=user_iso_content,
        hyperparameters=hyperparameters
    )

    trade_matching_path = cdm_trades_matching(user_iso_content)


    return {
        "statusCode": 200,
        "body": iso_to_cdm_response
    }
