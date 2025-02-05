import xml.etree.ElementTree as ET
import pandas as pd

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

    

