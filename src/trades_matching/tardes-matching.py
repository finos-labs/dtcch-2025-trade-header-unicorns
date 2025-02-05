import os
import json

def find_cdm_trades_matching_with_isin(isin, directory="data/cdm_trades"):
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


"""
EXAMPLE OF USE
=================================

# Import the function from your script (assuming it's in 'trades-matching.py')
from trades_matching import find_isin_in_json

# Define the ISIN you want to search for
isin_to_search = "US46625H1005" #This ISIN corresponds to the sample data/cdm_trades\5_6_JPM_KO_equity-option-price-return-basket-ex01-new-input.json
                                #The function should return "Matching files found: data\cdm_trades\5_6_JPM_KO_equity-option-price-return-basket-ex01-new-input.json"

# Call the function with the test ISIN
matching_files = find_isin_in_json(isin_to_search, directory="data/cdm_trades")

# Print the results
if matching_files:
    print("Matching files found:")
    for file in matching_files:
        print(file)
else:
    print("No matching files found.")


"""
