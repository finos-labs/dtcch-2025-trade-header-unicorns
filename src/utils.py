import xml.etree.ElementTree as ET
import pandas as pd
import os
import json

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
    

# ISIN and Ticker Extraction____________________________________________________________

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
    
# ISIN Enrichment in ISO XML Message_____________________________________________________________

def insert_isin_into_xml(xml_string, isin):

    namespace = {'ns': 'urn:iso:std:iso:20022:tech:xsd:DRAFT1seev.031.002.15'}
    ET.register_namespace('', namespace['ns']) 
    
    root = ET.fromstring(xml_string)
    
    isin_element = root.find(".//ns:UndrlygScty/ns:FinInstrmId/ns:ISIN", namespace)
    
    if isin_element is not None:
        isin_element.text = isin
    else:
        print("ISIN was not found in XML.")
    
    return ET.tostring(root, encoding='unicode')

def isin_enrichment(xml_string):
    ticker = extract_ticker_from_xml(xml_string)
    isin = get_isin_from_ticker(ticker)
    return insert_isin_into_xml(xml_string, isin)

# Matching CDM Trades with GPT Wrappers CDM through ISIN search____________________________________

def find_cdm_trades_matching_with_isin(isin, directory="data/cdm_trades_workflowstep"):
    matching_files = []
    
    for filename in os.listdir(directory):
        if filename.endswith(".json"):
            filepath = os.path.join(directory, filename)
            

            with open(filepath, 'r') as file:
                try:
                    data = json.load(file)

                    if isin in json.dumps(data):  
                        matching_files.append(filepath)
                except json.JSONDecodeError:
                    print(f"Error reading {filename}")
    
    return matching_files

def cdm_trades_matching(xml_string):
    ticker = extract_ticker_from_xml(xml_string)
    isin = get_isin_from_ticker(ticker)
    return find_cdm_trades_matching_with_isin(isin)
    

# ISO Validator Envelope _______________________________________________________________________________________

def wrap_with_envelope(xml_string):

    original_root = ET.fromstring(xml_string)


    envelope = ET.Element('TlkitEnvlp', {
        'xmlns': 'urn:tradeheader:xsd:toolkit:envelope',
        'xmlns:xsi': 'http://www.w3.org/2001/XMLSchema-instance'
    })

    tlkit_ref_id = ET.SubElement(envelope, 'TlkitRefId', {'version': '1-0'})
    tlkit_ref_id.text = 'securities_base_sr2024-seev.031.002.15'

    biz_msg_envlp = ET.SubElement(envelope, 'BizMsgEnvlp')
    biz_msg_envlp.append(original_root)

    return ET.tostring(envelope, encoding='unicode')