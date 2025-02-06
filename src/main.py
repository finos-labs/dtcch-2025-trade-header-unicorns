#Only for trying the lambda handlers in the terminal______________________________________________

from src.iso_message_converter import lambda_handler as lambda_handler_1
from src.cdm_proposedEvent_converter import lambda_handler as lambda_handler_2


event_1 = {}
context = None 


response_1 = lambda_handler_1(event_1, context)

event_2 = {
    "body": response_1["body"]  
}

response_2 = lambda_handler_2(event_2, context)

#print("Output from second lambda: ", response_2["body"])



