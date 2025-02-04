import boto3
import json

def call_claude(system_instructions=None, user_instructions="", hyperparameters=None):
    session = boto3.Session(profile_name="hackathon-participant")
    client = session.client("bedrock-runtime", region_name="us-west-2")

    model_id = "anthropic.claude-3-haiku-20240307-v1:0"

    if hyperparameters is None:
        hyperparameters = [500, 0.1, 0.1]

    payload = {
        "anthropic_version": "bedrock-2023-05-31", #version of anthropic claude api
        "messages": [
            {"role": "user", "content": user_instructions}
        ],
        "max_tokens": hyperparameters[0],
        "temperature": hyperparameters[1],
        "top_p": hyperparameters[2]
    }

    if system_instructions:
        payload["system"] = system_instructions

    payload_json = json.dumps(payload)

    try:
        response = client.invoke_model(
            modelId=model_id,
            contentType="application/json",
            accept="application/json",
            body=payload_json
        )

        response_body = json.loads(response["body"].read())

        text_content = "".join([item["text"] for item in response_body.get("content", []) if item["type"] == "text"])

        return text_content if text_content else "No response from Claude."

    except Exception as e:
        return f"Error calling Claude API: {str(e)}"


