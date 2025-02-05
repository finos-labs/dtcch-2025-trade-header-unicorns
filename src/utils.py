import xml.etree.ElementTree as ET



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

# Ejemplo de uso
xml_message = '''<Document><body>Hola, mundo!</body></Document>'''
wrapped_xml = wrap_with_envelope(xml_message)