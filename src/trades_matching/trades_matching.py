import os
import json

def find_cdm_trades_matching_with_isin(isin, directory="data/cdm_trades_workflowstep"):
    """
    This function searches through all JSON files in the specified directory, by default data/cdm_trades, and 
    returns a list of file paths that contain the given ISIN as part of their content.

    Args:
        isin (str): The ISIN code to search for. This should be a string representing the ISIN.
        directory (str, optional): The directory to search for JSON files. Defaults to "data/cdm_trades".

    Returns:
        list: A list of file paths (strings) where the ISIN appears in the content of the JSON file.
                If no files contain the ISIN, returns an empty list.
    """

    matching_files = []
    
    # Loop through all files in the specified directory
    for filename in os.listdir(directory):
        if filename.endswith(".json"):
            filepath = os.path.join(directory, filename)
            
            # Open and read the JSON file
            with open(filepath, 'r') as file:
                try:
                    data = json.load(file)
                    # Check if ISIN appears anywhere in the file
                    if isin in json.dumps(data):  # Convert JSON to string and check for ISIN
                        matching_files.append(filepath)
                except json.JSONDecodeError:
                    print(f"Error reading {filename}")
    
    return matching_files


