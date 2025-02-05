from src.ai_model.call_claude import call_claude
from src.utils import read_file

def lambda_handler(event, context):
    system_instruction_path = "data/instructions/instructions_gpt_iso_mapper.txt"
    user_news_path = "data/testing_news/news_testing2.txt"

    system_instruction_content = read_file(system_instruction_path)
    user_news_content = read_file(user_news_path)

    hyperparameters = {
        "max_tokens": 1000,
        "temperature": 0.1,
        "top_p": 0.1
    }

    news_to_iso_response = call_claude(
        system_instructions=system_instruction_content, 
        user_instructions=user_news_content, 
        hyperparameters=hyperparameters
    )

    # Return or process the response as needed.
    return {
        "statusCode": 200,
        "body": news_to_iso_response
    }
