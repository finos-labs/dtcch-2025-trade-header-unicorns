import requests
import json
import os

# CDM APIs' Calls Generic Function ____________________________________________________________________

def post_to_api(url, envelope):

    headers = {
        "Content-Type": "application/json"  
    }

    response = requests.post(url, json=envelope, headers=headers)

    if response.status_code == 200:
        return response.json() 
    else:
        return {"error": f"Request failed with status code {response.status_code}", "details": response.text}

# Calls to OBSERVATION, VALIDATION & EXECUTE CDM APIS______________________________________________

def observation_call(cdm_trade_path, wrapper_data):
    cdm_trade_content = load_file(cdm_trade_path) 
    envelope = create_envelope_observation(wrapper_data, cdm_trade_content)
    url = "https://26t3noe0fd.execute-api.us-west-2.amazonaws.com/Hackathon/cdm-manager"
    return post_to_api(url,envelope)

def execution_call(proposed_cdm):
    envelope = create_execution_envelope(proposed_cdm)
    url = "https://26t3noe0fd.execute-api.us-west-2.amazonaws.com/Hackathon/cdm-manager"
    return post_to_api(url,envelope)

def validation_call(accepted_workflow_step):
    envelope = create_validation_envelope(accepted_workflow_step)
    url = "https://26t3noe0fd.execute-api.us-west-2.amazonaws.com/Hackathon/cdm-manager"
    return post_to_api(url, envelope)



#Creation of Envelopes to call CDM Apis___________________________________________________________

def load_file(file_path):
    try:
        with open(file_path, 'r', encoding='utf-8') as file:
            return json.load(file)  
    except FileNotFoundError:
        print(f"Error: The file {file_path} was not found.")
        return None
    except Exception as e:
        print(f"Error reading the file {file_path}: {e}")
        return None

def create_envelope_observation(wrapper_data, open_trade_data):
    envelope = {
        "action": "OBSERVATION",
        "payload": {
            "wrapper": wrapper_data,
            "acceptedStep": open_trade_data
        }
    }
    
    return envelope

def create_execution_envelope(proposed_event):
    envelope = {
        "action": "EXECUTION",
        "payload": proposed_event
    }
    
    return envelope

def create_validation_envelope(accepted_workflow_step):
    envelope = {
        "action": "VALIDATION",
        "payload": accepted_workflow_step
    }
    
    return envelope

def save_envelope_to_file(envelope, filename):
    current_directory = os.path.dirname(os.path.abspath(__file__))
    file_path = os.path.join(current_directory, filename)
    
    with open(file_path, 'w', encoding='utf-8') as file:
        json.dump(envelope, file, indent=4)
    print(f"Envelope saved as: {file_path}")
