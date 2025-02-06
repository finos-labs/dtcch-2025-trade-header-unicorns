[![FINOS - Incubating](https://cdn.jsdelivr.net/gh/finos/contrib-toolbox@master/images/badge-incubating.svg)](https://finosfoundation.atlassian.net/wiki/display/FINOS/Incubating)

# FINOS DTCC Hackathon 


## TH CORPORATE ACTIONS SENTINEL

The **TH Corporate Actions Sentinel** is an AI-powered platform developed for the DTCC AI Hackathon. This solution automates the end-to-end corporate action lifecycle, ensuring that corporate action events are structured in a standardized format, simplifying integration with trading systems, and reducing discrepancies. 

### Project Details
- **Synthetic News Simulation:** Simulates corporate action events for testing and validation using synthetic news data. Future versions will integrate external news APIs.
- **AI-Powered ISO20022 Generation:** Leverages NLP to generate ISO20022 corporate action notifications. Currently, it supports Cash Dividend events, with future plans to extend to other corporate actions.
- **ISO20022 Validation Engine:** Ensures the generated messages are compliant with ISO standards and business constraints.
- **ISIN Lookup & Enrichment:** Uses a simulated CSV-based lookup to enrich ISIN data. Future versions will incorporate an external ISIN API.
- **AI-Powered JSON Transformation:** Converts corporate actions into a CDM-compatible JSON format for trade impact analysis.
- **CDM Integration Layer:** Enables seamless ingestion and processing of corporate action data within the FINOS Common Domain Model (CDM).
- **User Interface:** Provides real-time data visualization of corporate actions, trade impacts, and position changes.

This project aims to automate and optimize the corporate actions process, improving accuracy and reducing manual interventions.

### Team Information

- **Team Name:** TradeHeader Unicorns
- **Team Members:**
    - @arevillaTH
    - @aollerTH
    - @LuciaCortesTH
    - @rperezTH
    - @gtarres

## Usage Guide

#### Creating the Python environment: written by tradeheader 

##### In Windows:

    PS> python -m venv tradeheader_hackathon_venv 
    PS> .\tradeheader_hackathon_venv\Scripts\activate

##### In Linux/MacOS:

    $ python -m venv tradeheader_hackathon_venv  
    $ source tradeheader_hackathon_venv/bin/activate

#### Installing the dependencies  pip:

    pip install -r requirements.txt 

#### To generate databases for fine tuning:

    python -m src.fine_tuning.database_creator  
    
(modify the global variable DATASET to ISO or CDM
    to choose which dataset to create (news-to-iso or iso-to-cdm))

#### To perform the fine tuning:

    python -m src.fine_tuning.fine_tuning
    
(Note that the dataset must be uploaded to S3 first and that using the fine-tuned model is very expensive, which is why we haven't purchased it despite obtaining it for both claude agents)

#### To obtain the ISO Message and CDM Message output run:
    python -m src.main

The outputs will be stored in data/outputs

## License

Copyright 2025 FINOS

Distributed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

SPDX-License-Identifier: [Apache-2.0](https://spdx.org/licenses/Apache-2.0)





