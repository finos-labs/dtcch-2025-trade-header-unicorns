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


## Using DCO to sign your commits

**All commits** must be signed with a DCO signature to avoid being flagged by the DCO Bot. This means that your commit log message must contain a line that looks like the following one, with your actual name and email address:

```
Signed-off-by: John Doe <john.doe@example.com>
```

Adding the `-s` flag to your `git commit` will add that line automatically. You can also add it manually as part of your commit log message or add it afterwards with `git commit --amend -s`.

See [CONTRIBUTING.md](./.github/CONTRIBUTING.md) for more information

### Helpful DCO Resources
- [Git Tools - Signing Your Work](https://git-scm.com/book/en/v2/Git-Tools-Signing-Your-Work)
- [Signing commits
](https://docs.github.com/en/github/authenticating-to-github/signing-commits)


## License

Copyright 2025 FINOS

Distributed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

SPDX-License-Identifier: [Apache-2.0](https://spdx.org/licenses/Apache-2.0)



## Creating the Python environment: written by tradeheader 

#### In Windows:

    PS> python -m venv tradeheader_hackathon_venv 
    PS> .\tradeheader_hackathon_venv\Scripts\activate

#### In Linux/MacOS:

    $ python -m venv tradeheader_hackathon_venv  
    $ source tradeheader_hackathon_venv/bin/activate

## Installing the dependencies  pip:

### For development using

    pip install -r requirements.txt 







