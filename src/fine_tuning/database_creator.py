import os
import json
import xml.etree.ElementTree as ET

def read_text_file(filepath):
    with open(filepath, "r", encoding="utf-8") as f:
        return f.read().strip()

def strip_namespace(element):
    for elem in element.iter():
        if '}' in elem.tag:
            elem.tag = elem.tag.split('}', 1)[1]  # Remove namespace
    return element

def read_xml_file(filepath):
    tree = ET.parse(filepath)
    root = tree.getroot()
    root = strip_namespace(root)
    return ET.tostring(root, encoding="unicode", method="xml").strip()

def read_json_file(filepath):
    with open(filepath, "r", encoding="utf-8") as f:
        data = json.load(f)
    return json.dumps(data, ensure_ascii=False, indent=2)

def process_dataset(dataset_type):
    config = {
        "ISO": {
            "instruction_path": "data/instructions/instructions_gpt_iso_mapper.txt",
            "user_folder": "data/fine_tuning/synthetic_news/",
            "assistant_folder": "data/fine_tuning/synthetic_iso/",
            "output_path": "data/datasets/dataset_news_to_iso.jsonl",
            "user_reader": read_text_file,
            "assistant_reader": read_xml_file
        },
        "CDM": {
            "instruction_path": "data/instructions/instructions_gpt_cdm_mapper.txt",
            "user_folder": "data/fine_tuning/synthetic_iso/",
            "assistant_folder": "data/fine_tuning/synthetic_cdm/",
            "output_path": "data/datasets/dataset_iso_to_cdm.jsonl",
            "user_reader": read_xml_file,
            "assistant_reader": read_json_file
        }
    }
    
    if dataset_type not in config:
        raise ValueError("Invalid dataset type. Choose either 'ISO' or 'CDM'.")
    
    settings = config[dataset_type]
    system_instruction = read_text_file(settings["instruction_path"])
    
    user_files = sorted(os.listdir(settings["user_folder"]))
    assistant_files = sorted(os.listdir(settings["assistant_folder"]))
    
    if len(user_files) != len(assistant_files):
        raise ValueError("Mismatch between user query files and assistant response files.")
    
    dataset_entries = []
    for user_file, assistant_file in zip(user_files, assistant_files):
        user_content = settings["user_reader"](os.path.join(settings["user_folder"], user_file))
        assistant_content = settings["assistant_reader"](os.path.join(settings["assistant_folder"], assistant_file))
        
        entry = {
            "system": system_instruction,
            "messages": [
                {"role": "user", "content": user_content},
                {"role": "assistant", "content": assistant_content}
            ]
        }
        dataset_entries.append(entry)
    
    with open(settings["output_path"], "w", encoding="utf-8") as f:
        for entry in dataset_entries:
            f.write(json.dumps(entry, ensure_ascii=False) + "\n")
    
    print(f"Fine-tuning dataset saved to {settings['output_path']}")

if __name__ == "__main__":
    DATASET = "CDM"  
    process_dataset(DATASET)
