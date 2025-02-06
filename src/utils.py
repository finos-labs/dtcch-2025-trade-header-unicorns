import xml.etree.ElementTree as ET
import pandas as pd
import json
import os

def read_file(file_path):
    try:
        with open(file_path, 'r', encoding='utf-8') as file:
            return file.read()
    except FileNotFoundError:
        print(f"Error: The file {file_path} was not found.")
        return None
    except Exception as e:
        print(f"Error reading the file {file_path}: {e}")
        return None
    

def extract_ticker_from_xml(xml_string):

    namespace = {'ns': 'urn:iso:std:iso:20022:tech:xsd:DRAFT1seev.031.002.15'}
    root = ET.fromstring(xml_string)
    ticker_element = root.find(".//ns:UndrlygScty/ns:FinInstrmId/ns:OthrId/ns:Id", namespace)
    return ticker_element.text if ticker_element is not None else None


def get_isin_from_ticker(ticker):

    csv_path="data/isin_enrichment/isin_database.csv"

    try:
        df = pd.read_csv(csv_path, delimiter=";")
        isin = df.loc[df['Ticker'] == ticker, 'ISIN']
        return isin.values[0] if not isin.empty else None
    except Exception as e:
        print(f"Error when reading the file: {e}")
        return None

def insert_isin_into_xml(xml_string, isin):

    namespace = {'ns': 'urn:iso:std:iso:20022:tech:xsd:DRAFT1seev.031.002.15'}
    ET.register_namespace('', namespace['ns']) 
    
    root = ET.fromstring(xml_string)
    
    isin_element = root.find(".//ns:UndrlygScty/ns:FinInstrmId/ns:ISIN", namespace)
    
    if isin_element is not None:
        isin_element.text = isin
    else:
        print("No se encontró el nodo ISIN en el XML.")
    
    return ET.tostring(root, encoding='unicode')

def isin_enrichment(xml_string):
    ticker = extract_ticker_from_xml(xml_string)
    isin = get_isin_from_ticker(ticker)
    return insert_isin_into_xml(xml_string, isin)



def wrap_with_envelope(xml_string):
    # Parsear el XML original
    original_root = ET.fromstring(xml_string)

    # Crear el nuevo elemento 'TlkitEnvlp' con los espacios de nombres
    envelope = ET.Element('TlkitEnvlp', {
        'xmlns': 'urn:tradeheader:xsd:toolkit:envelope',
        'xmlns:xsi': 'http://www.w3.org/2001/XMLSchema-instance'
    })

    # Añadir el elemento 'TlkitRefId'
    tlkit_ref_id = ET.SubElement(envelope, 'TlkitRefId', {'version': '1-0'})
    tlkit_ref_id.text = 'securities_base_sr2024-seev.031.002.15'

    # Crear el elemento 'BizMsgEnvlp' y añadir el mensaje original
    biz_msg_envlp = ET.SubElement(envelope, 'BizMsgEnvlp')
    biz_msg_envlp.append(original_root)

    # Convertir el nuevo árbol XML a cadena
    return ET.tostring(envelope, encoding='unicode')

"""
# Ejemplo de uso
xml_message = '''<Document><body>Hola, mundo!</body></Document>'''
wrapped_xml = wrap_with_envelope(xml_message)
"""

#Envelopes creation for lambda CDM - @gtarres
def load_file(file_path):
    try:
        with open(file_path, 'r', encoding='utf-8') as file:
            return json.load(file)  # Directly parse the JSON content and return it as a dictionary
    except FileNotFoundError:
        print(f"Error: The file {file_path} was not found.")
        return None
    except Exception as e:
        print(f"Error reading the file {file_path}: {e}")
        return None

def create_envelope_observation(wrapper_data, open_trade_data):
    # Create the envelope structure
    envelope = {
        "action": "OBSERVATION",
        "payload": {
            "wrapper": wrapper_data,
            "acceptedStep": open_trade_data
        }
    }
    
    return envelope

def create_execution_envelope(proposed_event):
    # Create the execution envelope structure
    envelope = {
        "action": "EXECUTION",
        "payload": proposed_event
    }
    
    return envelope

def create_validation_envelope(accepted_workflow_step):
    # Create the validation envelope structure
    envelope = {
        "action": "VALIDATION",
        "payload": accepted_workflow_step
    }
    
    return envelope

def save_envelope_to_file(envelope, filename):
    # Get the current directory of the script
    current_directory = os.path.dirname(os.path.abspath(__file__))
    # Define the full path to the file
    file_path = os.path.join(current_directory, filename)
    
    # Write the envelope to a JSON file
    with open(file_path, 'w', encoding='utf-8') as file:
        json.dump(envelope, file, indent=4)
    print(f"Envelope saved as: {file_path}")

"""
# Example of creating and saving as json the three envelopes

# 1. Create the OBSERVATION envelope
wrapper_data = load_file('data/cdm_func_input/5_6_JPM_KO_equity-option-price-return-basket-ex01-new-input-function-cdm.json')
open_trade_data = load_file('data/cdm_trades_workflowStep/5_6_JPM_KO_equity-option-price-return-basket-ex01-new-input.json')
if wrapper_data and open_trade_data:
    observation_envelope = create_envelope_observation(wrapper_data, open_trade_data)
    save_envelope_to_file(observation_envelope, 'observation_envelope.json')
else:
    print("Failed to create the observation envelope due to file read issues.")

# 2. Create the EXECUTION envelope
proposed_event = load_file('data/cdm-manager-lambda/Observation/observation-expected-response-1.json')
if proposed_event:
    execution_envelope = create_execution_envelope(proposed_event)
    save_envelope_to_file(execution_envelope, 'execution_envelope.json')
else:
    print("Failed to create the execution envelope due to file read issues.")

# 3. Create the VALIDATION envelope
accepted_workflow_step = load_file('data/cdm-manager-lambda/Execution/execution-expected-response-1.json')
if accepted_workflow_step:
    validation_envelope = create_validation_envelope(accepted_workflow_step)
    save_envelope_to_file(validation_envelope, 'validation_envelope.json')
else:
    print("Failed to create the validation envelope due to file read issues.")
"""