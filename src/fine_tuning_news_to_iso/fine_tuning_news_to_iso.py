import boto3
from datetime import datetime

AWS_REGION = "us-west-2"
S3_TRAINING_URI = "s3://isonews/dataset_news_to_iso.jsonl"
S3_OUTPUT_URI = "s3://isonews/"
IAM_ROLE_ARN = "<insert iam role arn>"
BASE_MODEL_ID = "anthropic.claude-3-haiku-20240307-v1:0:200k"

bedrock = boto3.client(service_name="bedrock", region_name=AWS_REGION)

ts = datetime.now().strftime("%Y-%m-%d-%H-%M-%S")
customization_job_name = f"news-to-iso-job-{ts}"
custom_model_name = f"news-to-iso-ft-{ts}"

customization_type = "FINE_TUNING"
hyper_parameters = {
    "epochCount": "5",
    "batchSize": "32"
}
training_data_config = {"s3Uri": S3_TRAINING_URI}
output_data_config = {"s3Uri": S3_OUTPUT_URI}


training_job_response = bedrock.create_model_customization_job(
    customizationType=customization_type,
    jobName=customization_job_name,
    customModelName=custom_model_name,
    roleArn=IAM_ROLE_ARN,
    baseModelIdentifier=BASE_MODEL_ID,
    hyperParameters=hyper_parameters,
    trainingDataConfig=training_data_config,
    outputDataConfig=output_data_config
)

print("Fine-tuning job created:")
print(training_job_response)


job_arn = training_job_response["jobArn"]

# To see the status of the fine tuning, run in the terminal: 
# aws bedrock get-model-customization-job --job-identifier <arn job identifier> --region us-west-2
