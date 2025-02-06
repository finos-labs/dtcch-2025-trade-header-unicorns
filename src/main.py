import os
import json
from src.iso_message_converter import lambda_handler as iso_lambda_handler
from src.cdm_proposedEvent_converter import lambda_handler as cdm_lambda_handler

def ensure_output_directory(directory: str):
    os.makedirs(directory, exist_ok=True)

def save_to_file(file_path: str, data: str, mode: str = "w", encoding: str = "utf-8"):
    with open(file_path, mode, encoding=encoding) as file:
        file.write(data)

def save_json(file_path: str, data: dict):
    with open(file_path, "w", encoding="utf-8") as file:
        json.dump(data, file, indent=4, ensure_ascii=False)

def process_iso_to_cdm(event: dict, output_dir: str):
    ensure_output_directory(output_dir)
    
    # Obtaining ISO Message
    iso_response = iso_lambda_handler(event, None)
    iso_output_path = os.path.join(output_dir, "ISO_output.xml")
    save_to_file(iso_output_path, iso_response["body"])
    
    # Obtaining CDM message 
    cdm_event = {"body": iso_response["body"]}
    cdm_response = cdm_lambda_handler(cdm_event, None)
    cdm_output_path = os.path.join(output_dir, "CDM_output.json")
    save_json(cdm_output_path, cdm_response["body"]["AcceptedWorkFlowStep"])
    
    print(f"ISO Message and CDM Output have been saved in {output_dir}")

if __name__ == "__main__":
    output_directory = "data/outputs"
    initial_event = {}
    process_iso_to_cdm(initial_event, output_directory)


