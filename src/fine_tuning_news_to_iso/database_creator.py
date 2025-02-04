import os
import json
import xml.etree.ElementTree as ET


system_instruction_path = "data/instructions/instructions_gpt_iso_mapper.txt"
user_queries_folder = "data/fine_tuning_news_to_iso/synthetic_news/"
assistant_responses_folder = "data/fine_tuning_news_to_iso/synthetic_iso/"
output_jsonl_path = "data/datasets/dataset_news_to_iso.jsonl"

with open(system_instruction_path, "r", encoding="utf-8") as f:
    system_instruction = f.read().strip()

def read_text_file(filepath):
    with open(filepath, "r", encoding="utf-8") as f:
        return f.read().strip()

def read_xml_file(filepath):
    tree = ET.parse(filepath)
    root = tree.getroot()
    return ET.tostring(root, encoding="unicode", method="xml").strip()

dataset_entries = []

user_files = os.listdir(user_queries_folder)  # Ensure matching order
assistant_files = os.listdir(assistant_responses_folder)


if len(user_files) != len(assistant_files): # Check that each user query has a corresponding assistant response
    raise ValueError("Mismatch between user query files and assistant response files.")

for user_file, assistant_file in zip(user_files, assistant_files):

    user_filepath = os.path.join(user_queries_folder, user_file)
    assistant_filepath = os.path.join(assistant_responses_folder, assistant_file)

    user_content = read_text_file(user_filepath)
    assistant_content = read_xml_file(assistant_filepath)

    entry = {
        "system": system_instruction,
        "messages": [
            {"role": "user", "content": user_content},
            {"role": "assistant", "content": assistant_content}
        ]
    }

    dataset_entries.append(entry)

with open(output_jsonl_path, "w", encoding="utf-8") as f:
    for entry in dataset_entries:
        f.write(json.dumps(entry, ensure_ascii=False) + "\n")

print(f"Fine-tuning dataset saved to {output_jsonl_path}")
