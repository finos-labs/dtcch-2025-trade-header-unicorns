export const newsArticles = [
  {
    id: 1,
    headline: "Global Economy Shows Signs of Recovery",
    content:
      "Recent data suggests a positive trend in global economic indicators. Analysts point to increased consumer spending and a rebound in manufacturing as key factors driving this recovery.",
    isoMessage: "<news><headline>Global Economy Shows Signs of Recovery</headline><content>Recent data suggests a positive trend in global economic indicators.</content></news>",
    impactedOpenTrade: {},
    proposedEvent: {

    },
    acceptedWorkflowStep: "Economy",
  },
  {
    id: 2,
    headline: "Apple Inc. Announces Increased Quarterly Dividend Amid Strong Financial Performance",
    content: `Cupertino, CA – February 3, 2025 – Apple Inc. (NASDAQ: AAPL) today announced that its Board of Directors has approved a quarterly cash dividend of $0.28 per share, reflecting a 7.7% increase from the previous dividend. This marks the company's continued commitment to returning value to shareholders amid sustained growth in revenue and profitability.

The dividend will be payable on March 14, 2025, to shareholders of record as of February 28, 2025. The ex-dividend date is set for February 27, 2025.

CEO Statement

Tim Cook, CEO of Apple, commented:

"Apple's strong performance allows us to increase our quarterly dividend once again, rewarding our loyal shareholders while continuing to invest in innovation and growth. With robust demand for our latest iPhone lineup and growing services revenue, we remain confident in Apple's long-term trajectory."

Market Performance & Future Outlook

Apple's stock has risen 18% over the past year, outpacing the broader market. The company's dividend yield now stands at 0.65%, reinforcing its position as a steady income generator for long-term investors.

Investors and analysts are particularly optimistic about Apple's upcoming advancements in AI-driven services and augmented reality, expected to be key drivers of future revenue.

For further details on Apple's financial performance and upcoming investor events, please visit Apple's investor relations website.`,
    isoMessage: `<?xml version="1.0" encoding="UTF-8"?>
<Document xmlns="urn:iso:std:iso:20022:tech:xsd:DRAFT1seev.031.002.15" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xsi:schemaLocation="urn:swift:xsd:envelope ./../XSDSchemas/MX_seev_031_002_15_enriched.xsd">
	<CorpActnNtfctn>
		<NtfctnGnlInf>
			<NtfctnTp>NEWM</NtfctnTp>
			<PrcgSts>
				<Cd>
					<EvtCmpltnsSts>COMP</EvtCmpltnsSts>
					<EvtConfSts>CONF</EvtConfSts>
				</Cd>
			</PrcgSts>
		</NtfctnGnlInf>
		<CorpActnGnlInf>
			<CorpActnEvtId>DIVGLAX945843</CorpActnEvtId>
			<OffclCorpActnEvtId>BI0159323322</OffclCorpActnEvtId>
				<EvtTp>
				<Cd>DVCA</Cd>
			</EvtTp>
			<MndtryVlntryEvtTp>
				<Cd>MAND</Cd>
			</MndtryVlntryEvtTp>
			<UndrlygScty>
				<FinInstrmId>
					<ISIN>US0378331005</ISIN> 
					<OthrId>
						<Id>AAPL</Id>     
						<Tp>
							<Cd>BLOM</Cd>
						</Tp>
					</OthrId>
				</FinInstrmId>
			</UndrlygScty>
		</CorpActnGnlInf>
		<AcctDtls>
			<ForAllAccts>
				<IdCd>GENR</IdCd>
			</ForAllAccts>
		</AcctDtls>
		<CorpActnDtls>
			<DtDtls>
				<ExDvddDt>
					<Dt>2025-02-27</Dt>   
				</ExDvddDt>
				<PmtDt>
					<Dt>2025-03-14</Dt> 
				</PmtDt>
			</DtDtls>
			<RateAndAmtDtls>
				<IntrstRate>
					<Rate>0.65</Rate>
				</IntrstRate>
			</RateAndAmtDtls>
		</CorpActnDtls>
	</CorpActnNtfctn>
</Document>`,
impactedOpenTrade: {
  "businessEvent" : {
    "intent" : "ContractFormation",
    "eventDate" : "2001-07-13",
    "instruction" : [ {
      "primitiveInstruction" : {
        "contractFormation" : {
          "legalAgreement" : [ {
            "legalAgreementIdentification" : {
              "agreementName" : {
                "agreementType" : "MasterAgreement",
                "masterAgreementType" : {
                  "value" : "ISDAMaster"
                }
              },
              "vintage" : 2002
            },
            "contractualParty" : [ {
              "globalReference" : "f0d3e05e",
              "externalReference" : "party2"
            }, {
              "globalReference" : "f0d36be0",
              "externalReference" : "party1"
            } ],
            "meta" : {
              "globalKey" : "55a4012a"
            }
          }, {
            "legalAgreementIdentification" : {
              "agreementName" : {
                "agreementType" : "Confirmation",
                "contractualDefinitionsType" : [ {
                  "value" : "ISDA2002EquityDerivatives"
                } ]
              }
            },
            "contractualParty" : [ {
              "globalReference" : "f0d3e05e",
              "externalReference" : "party2"
            }, {
              "globalReference" : "f0d36be0",
              "externalReference" : "party1"
            } ],
            "meta" : {
              "globalKey" : "7001fb6d"
            }
          } ]
        }
      },
      "before" : {
        "value" : {
          "trade" : {
            "tradeIdentifier" : [ {
              "issuerReference" : {
                "globalReference" : "f0d36be0",
                "externalReference" : "party1"
              },
              "assignedIdentifier" : [ {
                "identifier" : {
                  "value" : "EQUITYOPTIONPRICER02R17E16C15I14R13P12N11O10I9T8P7O6",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
                  }
                }
              } ],
              "meta" : {
                "globalKey" : "33baeb68"
              },
              "identifierType" : "UniqueTransactionIdentifier"
            }, {
              "issuerReference" : {
                "globalReference" : "f0d36be0",
                "externalReference" : "party1"
              },
              "assignedIdentifier" : [ {
                "identifier" : {
                  "value" : "EQUITYOPTIONPRICER01",
                  "meta" : {
                    "scheme" : "http://www.PartyA.com/eqd-trade-id"
                  }
                }
              } ],
              "meta" : {
                "globalKey" : "4cef0f99"
              }
            } ],
            "tradeDate" : {
              "value" : "2001-07-13",
              "meta" : {
                "globalKey" : "3e89cd",
                "externalKey" : "TradeDate"
              }
            },
            "tradableProduct" : {
              "product" : {
                "contractualProduct" : {
                  "productTaxonomy" : [ {
                    "primaryAssetClass" : {
                      "value" : "Equity"
                    }
                  }, {
                    "source" : "CFI",
                    "value" : {
                      "name" : {
                        "value" : "HESPXX",
                        "meta" : {
                          "scheme" : "http://www.fpml.org/coding-scheme/external/product-classification/iso10962"
                        }
                      }
                    }
                  }, {
                    "source" : "Other",
                    "value" : {
                      "name" : {
                        "value" : "Equity:Option:PriceReturnBasicPerformance:SingleName"
                      }
                    }
                  }, {
                    "source" : "ISDA",
                    "productQualifier" : "EquityOption_PriceReturnBasicPerformance_SingleName"
                  } ],
                  "economicTerms" : {
                    "payout" : {
                      "optionPayout" : [ {
                        "payerReceiver" : {
                          "payer" : "Party2",
                          "receiver" : "Party1"
                        },
                        "priceQuantity" : {
                          "quantitySchedule" : {
                            "address" : {
                              "scope" : "DOCUMENT",
                              "value" : "quantity-1"
                            }
                          },
                          "meta" : {
                            "globalKey" : "0"
                          }
                        },
                        "settlementTerms" : {
                          "settlementType" : "Election",
                          "settlementCurrency" : {
                            "value" : "EUR"
                          },
                          "meta" : {
                            "globalKey" : "4790ef7b"
                          }
                        },
                        "buyerSeller" : {
                          "buyer" : "Party1",
                          "seller" : "Party2"
                        },
                        "optionType" : "Call",
                        "exerciseTerms" : {
                          "optionStyle" : {
                            "americanExercise" : {
                              "commencementDate" : {
                                "adjustableDate" : {
                                  "unadjustedDate" : "2001-07-13",
                                  "dateAdjustments" : {
                                    "businessDayConvention" : "NONE",
                                    "meta" : {
                                      "globalKey" : "24a738"
                                    }
                                  },
                                  "meta" : {
                                    "globalKey" : "eae7f1c5"
                                  }
                                },
                                "meta" : {
                                  "globalKey" : "eae7f1c5"
                                }
                              },
                              "expirationDate" : {
                                "adjustableDate" : {
                                  "unadjustedDate" : "2005-09-27",
                                  "dateAdjustments" : {
                                    "businessDayConvention" : "NONE",
                                    "meta" : {
                                      "globalKey" : "24a738"
                                    }
                                  },
                                  "meta" : {
                                    "globalKey" : "eb6226d3"
                                  }
                                },
                                "meta" : {
                                  "globalKey" : "eb6226d3"
                                }
                              },
                              "latestExerciseTime" : {
                                "hourMinuteTime" : "17:15:00",
                                "businessCenter" : {
                                  "value" : "GBLO"
                                }
                              },
                              "expirationTimeType" : "Close",
                              "multipleExercise" : {
                                "integralMultipleAmount" : 1,
                                "minimumNumberOfOptions" : 1,
                                "maximumNumberOfOptions" : 150000
                              },
                              "meta" : {
                                "globalKey" : "221944d2"
                              }
                            }
                          },
                          "strike" : {
                            "strikePrice" : {
                              "value" : 32.00,
                              "unit" : {
                                "currency" : {
                                  "value" : "EUR"
                                }
                              },
                              "perUnitOf" : {
                                "financialUnit" : "Share"
                              },
                              "priceType" : "AssetPrice"
                            }
                          },
                          "exerciseProcedure" : {
                            "automaticExercise" : {
                              "isApplicable" : true
                            }
                          }
                        },
                        "underlier" : {
                          "security" : {
                            "productIdentifier" : [ {
                              "address" : {
                                "scope" : "DOCUMENT",
                                "value" : "productIdentifier-2"
                              }
                            }, {
                              "address" : {
                                "scope" : "DOCUMENT",
                                "value" : "productIdentifier-1"
                              }
                            } ],
                            "securityType" : "Equity"
                          },
                          "meta" : {
                            "globalKey" : "7a5b92c5"
                          }
                        },
                        "meta" : {
                          "globalKey" : "f8356e43"
                        }
                      } ],
                      "meta" : {
                        "globalKey" : "f8356e43"
                      }
                    },
                    "calculationAgent" : {
                      "calculationAgentParty" : "CalculationAgentIndependent"
                    },
                    "nonStandardisedTerms" : false
                  },
                  "meta" : {
                    "globalKey" : "7734109d"
                  }
                },
                "meta" : {
                  "globalKey" : "7734109d"
                }
              },
              "tradeLot" : [ {
                "priceQuantity" : [ {
                  "quantity" : [ {
                    "value" : {
                      "value" : 150000,
                      "unit" : {
                        "financialUnit" : "Contract"
                      },
                      "multiplier" : {
                        "value" : 1.00,
                        "unit" : {
                          "financialUnit" : "Share"
                        }
                      }
                    },
                    "meta" : {
                      "location" : [ {
                        "scope" : "DOCUMENT",
                        "value" : "quantity-1"
                      } ]
                    }
                  } ],
                  "observable" : {
                    "productIdentifier" : [ {
                      "value" : {
                        "identifier" : {
                          "value" : "US0378331005",
                          "meta" : {
                            "scheme" : "http://www.fpml.org/coding-scheme/external/instrument-id-ISIN"
                          }
                        },
                        "source" : "ISIN",
                        "meta" : {
                          "globalKey" : "becf6825"
                        }
                      },
                      "meta" : {
                        "location" : [ {
                          "scope" : "DOCUMENT",
                          "value" : "productIdentifier-2"
                        } ]
                      }
                    }, {
                      "value" : {
                        "identifier" : {
                          "value" : "AAPL"
                        },
                        "source" : "BBGTICKER",
                        "meta" : {
                          "globalKey" : "b53aabd5"
                        }
                      },
                      "meta" : {
                        "location" : [ {
                          "scope" : "DOCUMENT",
                          "value" : "productIdentifier-1"
                        } ]
                      }
                    } ],
                    "meta" : {
                      "globalKey" : "4702c730"
                    }
                  },
                  "meta" : {
                    "globalKey" : "e732a996"
                  }
                } ]
              } ],
              "counterparty" : [ {
                "role" : "Party1",
                "partyReference" : {
                  "globalReference" : "f0d3e05e",
                  "externalReference" : "party2"
                }
              }, {
                "role" : "Party2",
                "partyReference" : {
                  "globalReference" : "f0d36be0",
                  "externalReference" : "party1"
                }
              } ],
              "ancillaryParty" : [ {
                "role" : "CalculationAgentIndependent",
                "partyReference" : [ {
                  "globalReference" : "f0d36be0",
                  "externalReference" : "party1"
                } ]
              } ]
            },
            "party" : [ {
              "partyId" : [ {
                "identifier" : {
                  "value" : "Party A",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/dummy-party-id"
                  }
                },
                "meta" : {
                  "globalKey" : "33f59567"
                }
              }, {
                "identifier" : {
                  "value" : "DUMMY0000000000LEI01",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
                  }
                },
                "identifierType" : "LEI",
                "meta" : {
                  "globalKey" : "60fe4ea7"
                }
              } ],
              "meta" : {
                "globalKey" : "f0d36be0",
                "externalKey" : "party1"
              }
            }, {
              "partyId" : [ {
                "identifier" : {
                  "value" : "Party B",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/dummy-party-id"
                  }
                },
                "meta" : {
                  "globalKey" : "33f59568"
                }
              }, {
                "identifier" : {
                  "value" : "DUMMY0000000000LEI02",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
                  }
                },
                "identifierType" : "LEI",
                "meta" : {
                  "globalKey" : "60fe4ec6"
                }
              } ],
              "meta" : {
                "globalKey" : "f0d3e05e",
                "externalKey" : "party2"
              }
            } ],
            "contractDetails" : {
              "governingLaw" : {
                "value" : "GBEN"
              },
              "meta" : {
                "globalKey" : "2146c4"
              }
            },
            "meta" : {
              "globalKey" : "2b72d3a7"
            }
          },
          "transferHistory" : [ {
            "transfer" : {
              "quantity" : {
                "value" : 405000,
                "unit" : {
                  "currency" : {
                    "value" : "EUR"
                  }
                }
              },
              "payerReceiver" : {
                "payerPartyReference" : {
                  "globalReference" : "f0d3e05e",
                  "externalReference" : "party2"
                },
                "receiverPartyReference" : {
                  "globalReference" : "f0d36be0",
                  "externalReference" : "party1"
                }
              },
              "settlementDate" : {
                "unadjustedDate" : "2001-07-17",
                "dateAdjustments" : {
                  "businessDayConvention" : "NONE",
                  "meta" : {
                    "globalKey" : "24a738"
                  }
                }
              },
              "transferExpression" : {
                "priceTransfer" : "Premium"
              }
            },
            "meta" : {
              "globalKey" : "805c5fcc"
            }
          } ],
          "meta" : {
            "globalKey" : "c0637b3"
          }
        }
      }
    } ],
    "after" : [ {
      "trade" : {
        "tradeIdentifier" : [ {
          "issuerReference" : {
            "globalReference" : "f0d36be0",
            "externalReference" : "party1"
          },
          "assignedIdentifier" : [ {
            "identifier" : {
              "value" : "EQUITYOPTIONPRICER02R17E16C15I14R13P12N11O10I9T8P7O6",
              "meta" : {
                "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
              }
            }
          } ],
          "meta" : {
            "globalKey" : "33baeb68"
          },
          "identifierType" : "UniqueTransactionIdentifier"
        }, {
          "issuerReference" : {
            "globalReference" : "f0d36be0",
            "externalReference" : "party1"
          },
          "assignedIdentifier" : [ {
            "identifier" : {
              "value" : "EQUITYOPTIONPRICER01",
              "meta" : {
                "scheme" : "http://www.PartyA.com/eqd-trade-id"
              }
            }
          } ],
          "meta" : {
            "globalKey" : "4cef0f99"
          }
        } ],
        "tradeDate" : {
          "value" : "2001-07-13",
          "meta" : {
            "globalKey" : "3e89cd",
            "externalKey" : "TradeDate"
          }
        },
        "tradableProduct" : {
          "product" : {
            "contractualProduct" : {
              "productTaxonomy" : [ {
                "primaryAssetClass" : {
                  "value" : "Equity"
                }
              }, {
                "source" : "CFI",
                "value" : {
                  "name" : {
                    "value" : "HESPXX",
                    "meta" : {
                      "scheme" : "http://www.fpml.org/coding-scheme/external/product-classification/iso10962"
                    }
                  }
                }
              }, {
                "source" : "Other",
                "value" : {
                  "name" : {
                    "value" : "Equity:Option:PriceReturnBasicPerformance:SingleName"
                  }
                }
              }, {
                "source" : "ISDA",
                "productQualifier" : "EquityOption_PriceReturnBasicPerformance_SingleName"
              } ],
              "economicTerms" : {
                "payout" : {
                  "optionPayout" : [ {
                    "payerReceiver" : {
                      "payer" : "Party2",
                      "receiver" : "Party1"
                    },
                    "priceQuantity" : {
                      "quantitySchedule" : {
                        "address" : {
                          "scope" : "DOCUMENT",
                          "value" : "quantity-1"
                        }
                      },
                      "meta" : {
                        "globalKey" : "0"
                      }
                    },
                    "settlementTerms" : {
                      "settlementType" : "Election",
                      "settlementCurrency" : {
                        "value" : "EUR"
                      },
                      "meta" : {
                        "globalKey" : "4790ef7b"
                      }
                    },
                    "buyerSeller" : {
                      "buyer" : "Party1",
                      "seller" : "Party2"
                    },
                    "optionType" : "Call",
                    "exerciseTerms" : {
                      "optionStyle" : {
                        "americanExercise" : {
                          "commencementDate" : {
                            "adjustableDate" : {
                              "unadjustedDate" : "2001-07-13",
                              "dateAdjustments" : {
                                "businessDayConvention" : "NONE",
                                "meta" : {
                                  "globalKey" : "24a738"
                                }
                              },
                              "meta" : {
                                "globalKey" : "eae7f1c5"
                              }
                            },
                            "meta" : {
                              "globalKey" : "eae7f1c5"
                            }
                          },
                          "expirationDate" : {
                            "adjustableDate" : {
                              "unadjustedDate" : "2005-09-27",
                              "dateAdjustments" : {
                                "businessDayConvention" : "NONE",
                                "meta" : {
                                  "globalKey" : "24a738"
                                }
                              },
                              "meta" : {
                                "globalKey" : "eb6226d3"
                              }
                            },
                            "meta" : {
                              "globalKey" : "eb6226d3"
                            }
                          },
                          "latestExerciseTime" : {
                            "hourMinuteTime" : "17:15:00",
                            "businessCenter" : {
                              "value" : "GBLO"
                            }
                          },
                          "expirationTimeType" : "Close",
                          "multipleExercise" : {
                            "integralMultipleAmount" : 1,
                            "minimumNumberOfOptions" : 1,
                            "maximumNumberOfOptions" : 150000
                          },
                          "meta" : {
                            "globalKey" : "221944d2"
                          }
                        }
                      },
                      "strike" : {
                        "strikePrice" : {
                          "value" : 32.00,
                          "unit" : {
                            "currency" : {
                              "value" : "EUR"
                            }
                          },
                          "perUnitOf" : {
                            "financialUnit" : "Share"
                          },
                          "priceType" : "AssetPrice"
                        }
                      },
                      "exerciseProcedure" : {
                        "automaticExercise" : {
                          "isApplicable" : true
                        }
                      }
                    },
                    "underlier" : {
                      "security" : {
                        "productIdentifier" : [ {
                          "address" : {
                            "scope" : "DOCUMENT",
                            "value" : "productIdentifier-2"
                          }
                        }, {
                          "address" : {
                            "scope" : "DOCUMENT",
                            "value" : "productIdentifier-1"
                          }
                        } ],
                        "securityType" : "Equity"
                      },
                      "meta" : {
                        "globalKey" : "7a5b92c5"
                      }
                    },
                    "meta" : {
                      "globalKey" : "f8356e43"
                    }
                  } ],
                  "meta" : {
                    "globalKey" : "f8356e43"
                  }
                },
                "calculationAgent" : {
                  "calculationAgentParty" : "CalculationAgentIndependent"
                },
                "nonStandardisedTerms" : false
              },
              "meta" : {
                "globalKey" : "7734109d"
              }
            },
            "meta" : {
              "globalKey" : "7734109d"
            }
          },
          "tradeLot" : [ {
            "priceQuantity" : [ {
              "quantity" : [ {
                "value" : {
                  "value" : 150000,
                  "unit" : {
                    "financialUnit" : "Contract"
                  },
                  "multiplier" : {
                    "value" : 1.00,
                    "unit" : {
                      "financialUnit" : "Share"
                    }
                  }
                },
                "meta" : {
                  "location" : [ {
                    "scope" : "DOCUMENT",
                    "value" : "quantity-1"
                  } ]
                }
              } ],
              "observable" : {
                "productIdentifier" : [ {
                  "value" : {
                    "identifier" : {
                      "value" : "US0378331005",
                      "meta" : {
                        "scheme" : "http://www.fpml.org/coding-scheme/external/instrument-id-ISIN"
                      }
                    },
                    "source" : "ISIN",
                    "meta" : {
                      "globalKey" : "becf6825"
                    }
                  },
                  "meta" : {
                    "location" : [ {
                      "scope" : "DOCUMENT",
                      "value" : "productIdentifier-2"
                    } ]
                  }
                }, {
                  "value" : {
                    "identifier" : {
                      "value" : "AAPL"
                    },
                    "source" : "BBGTICKER",
                    "meta" : {
                      "globalKey" : "b53aabd5"
                    }
                  },
                  "meta" : {
                    "location" : [ {
                      "scope" : "DOCUMENT",
                      "value" : "productIdentifier-1"
                    } ]
                  }
                } ],
                "meta" : {
                  "globalKey" : "4702c730"
                }
              },
              "meta" : {
                "globalKey" : "e732a996"
              }
            } ]
          } ],
          "counterparty" : [ {
            "role" : "Party1",
            "partyReference" : {
              "globalReference" : "f0d3e05e",
              "externalReference" : "party2"
            }
          }, {
            "role" : "Party2",
            "partyReference" : {
              "globalReference" : "f0d36be0",
              "externalReference" : "party1"
            }
          } ],
          "ancillaryParty" : [ {
            "role" : "CalculationAgentIndependent",
            "partyReference" : [ {
              "globalReference" : "f0d36be0",
              "externalReference" : "party1"
            } ]
          } ]
        },
        "party" : [ {
          "partyId" : [ {
            "identifier" : {
              "value" : "Party A",
              "meta" : {
                "scheme" : "http://www.fpml.org/coding-scheme/dummy-party-id"
              }
            },
            "meta" : {
              "globalKey" : "33f59567"
            }
          }, {
            "identifier" : {
              "value" : "DUMMY0000000000LEI01",
              "meta" : {
                "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
              }
            },
            "identifierType" : "LEI",
            "meta" : {
              "globalKey" : "60fe4ea7"
            }
          } ],
          "meta" : {
            "globalKey" : "f0d36be0",
            "externalKey" : "party1"
          }
        }, {
          "partyId" : [ {
            "identifier" : {
              "value" : "Party B",
              "meta" : {
                "scheme" : "http://www.fpml.org/coding-scheme/dummy-party-id"
              }
            },
            "meta" : {
              "globalKey" : "33f59568"
            }
          }, {
            "identifier" : {
              "value" : "DUMMY0000000000LEI02",
              "meta" : {
                "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
              }
            },
            "identifierType" : "LEI",
            "meta" : {
              "globalKey" : "60fe4ec6"
            }
          } ],
          "meta" : {
            "globalKey" : "f0d3e05e",
            "externalKey" : "party2"
          }
        } ],
        "contractDetails" : {
          "documentation" : [ {
            "legalAgreementIdentification" : {
              "agreementName" : {
                "agreementType" : "MasterAgreement",
                "masterAgreementType" : {
                  "value" : "ISDAMaster"
                }
              },
              "vintage" : 2002
            },
            "contractualParty" : [ {
              "globalReference" : "f0d3e05e",
              "externalReference" : "party2"
            }, {
              "globalReference" : "f0d36be0",
              "externalReference" : "party1"
            } ],
            "meta" : {
              "globalKey" : "55a4012a"
            }
          }, {
            "legalAgreementIdentification" : {
              "agreementName" : {
                "agreementType" : "Confirmation",
                "contractualDefinitionsType" : [ {
                  "value" : "ISDA2002EquityDerivatives"
                } ]
              }
            },
            "contractualParty" : [ {
              "globalReference" : "f0d3e05e",
              "externalReference" : "party2"
            }, {
              "globalReference" : "f0d36be0",
              "externalReference" : "party1"
            } ],
            "meta" : {
              "globalKey" : "7001fb6d"
            }
          } ],
          "governingLaw" : {
            "value" : "GBEN"
          },
          "meta" : {
            "globalKey" : "2146c4"
          }
        },
        "meta" : {
          "globalKey" : "2b72d3a7"
        }
      },
      "state" : {
        "positionState" : "Formed"
      },
      "transferHistory" : [ {
        "transfer" : {
          "quantity" : {
            "value" : 405000,
            "unit" : {
              "currency" : {
                "value" : "EUR"
              }
            }
          },
          "payerReceiver" : {
            "payerPartyReference" : {
              "globalReference" : "f0d3e05e",
              "externalReference" : "party2"
            },
            "receiverPartyReference" : {
              "globalReference" : "f0d36be0",
              "externalReference" : "party1"
            }
          },
          "settlementDate" : {
            "unadjustedDate" : "2001-07-17",
            "dateAdjustments" : {
              "businessDayConvention" : "NONE",
              "meta" : {
                "globalKey" : "24a738"
              }
            }
          },
          "transferExpression" : {
            "priceTransfer" : "Premium"
          }
        },
        "meta" : {
          "globalKey" : "805c5fcc"
        }
      } ],
      "meta" : {
        "globalKey" : "c0637b3"
      }
    } ]
  },
  "previousWorkflowStep" : {
    "globalReference" : "acab49c9"
  },
  "messageInformation" : {
    "messageId" : {
      "value" : "SEF123",
      "meta" : {
        "scheme" : "http://www.fpml.org/coding-scheme/external/technical-record-id"
      }
    },
    "sentBy" : {
      "value" : "SEFCORP"
    },
    "sentTo" : [ {
      "value" : "SDR01"
    } ]
  },
  "timestamp" : [ {
    "dateTime" : "2001-07-13T16:42:16Z",
    "qualification" : "eventCreationDateTime"
  }, {
    "dateTime" : "2001-07-13T16:20:47Z",
    "qualification" : "executionDateTime"
  } ],
  "eventIdentifier" : [ {
    "assignedIdentifier" : [ {
      "identifier" : {
        "value" : "SEF123"
      }
    } ],
    "meta" : {
      "globalKey" : "918f217e"
    }
  } ],
  "action" : "New",
  "party" : [ {
    "partyId" : [ {
      "identifier" : {
        "value" : "Party A",
        "meta" : {
          "scheme" : "http://www.fpml.org/coding-scheme/dummy-party-id"
        }
      },
      "meta" : {
        "globalKey" : "33f59567"
      }
    }, {
      "identifier" : {
        "value" : "DUMMY0000000000LEI01",
        "meta" : {
          "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
        }
      },
      "identifierType" : "LEI",
      "meta" : {
        "globalKey" : "60fe4ea7"
      }
    } ],
    "meta" : {
      "globalKey" : "f0d36be0",
      "externalKey" : "party1"
    }
  }, {
    "partyId" : [ {
      "identifier" : {
        "value" : "Party B",
        "meta" : {
          "scheme" : "http://www.fpml.org/coding-scheme/dummy-party-id"
        }
      },
      "meta" : {
        "globalKey" : "33f59568"
      }
    }, {
      "identifier" : {
        "value" : "DUMMY0000000000LEI02",
        "meta" : {
          "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
        }
      },
      "identifierType" : "LEI",
      "meta" : {
        "globalKey" : "60fe4ec6"
      }
    } ],
    "meta" : {
      "globalKey" : "f0d3e05e",
      "externalKey" : "party2"
    }
  } ]
} ,
    proposedEvent: {
      "proposedWorkflowStep":
      {
        "proposedEvent" : {
          "intent" : "CorporateActionAdjustment",
          "corporateActionIntent" : "CashDividend",
          "instruction" : [ {
            "primitiveInstruction" : {
              "observation" : {
                "observationEvent" : {
                  "corporateAction" : {
                    "corporateActionType" : "CashDividend",
                    "exDate" : "2025-02-27",
                    "payDate" : "2025-03-14"
                  }
                }
              }
            },
            "before" : {
              "value" : {
                "trade" : {
                  "tradeIdentifier" : [ {
                    "issuerReference" : {
                      "globalReference" : "f0d36be0",
                      "externalReference" : "party1"
                    },
                    "assignedIdentifier" : [ {
                      "identifier" : {
                        "value" : "EQUITYOPTIONPRICER02R17E16C15I14R13P12N11O10I9T8P7O6",
                        "meta" : {
                          "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
                        }
                      }
                    } ],
                    "meta" : {
                      "globalKey" : "e38feb88"
                    },
                    "identifierType" : "UniqueTransactionIdentifier"
                  }, {
                    "issuerReference" : {
                      "globalReference" : "f0d36be0",
                      "externalReference" : "party1"
                    },
                    "assignedIdentifier" : [ {
                      "identifier" : {
                        "value" : "EQUITYOPTIONPRICER01",
                        "meta" : {
                          "scheme" : "http://www.PartyA.com/eqd-trade-id"
                        }
                      }
                    } ],
                    "meta" : {
                      "globalKey" : "8c698b79"
                    }
                  } ],
                  "tradeDate" : {
                    "value" : "2001-07-13",
                    "meta" : {
                      "globalKey" : "3e89cd",
                      "externalKey" : "TradeDate"
                    }
                  },
                  "party" : [ {
                    "partyId" : [ {
                      "identifier" : {
                        "value" : "Party A",
                        "meta" : {
                          "scheme" : "http://www.fpml.org/coding-scheme/dummy-party-id"
                        }
                      },
                      "meta" : {
                        "globalKey" : "33f59567"
                      }
                    }, {
                      "identifier" : {
                        "value" : "DUMMY0000000000LEI01",
                        "meta" : {
                          "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
                        }
                      },
                      "identifierType" : "LEI",
                      "meta" : {
                        "globalKey" : "60fe4ea7"
                      }
                    } ],
                    "meta" : {
                      "globalKey" : "f0d36be0",
                      "externalKey" : "party1"
                    }
                  }, {
                    "partyId" : [ {
                      "identifier" : {
                        "value" : "Party B",
                        "meta" : {
                          "scheme" : "http://www.fpml.org/coding-scheme/dummy-party-id"
                        }
                      },
                      "meta" : {
                        "globalKey" : "33f59568"
                      }
                    }, {
                      "identifier" : {
                        "value" : "DUMMY0000000000LEI02",
                        "meta" : {
                          "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
                        }
                      },
                      "identifierType" : "LEI",
                      "meta" : {
                        "globalKey" : "60fe4ec6"
                      }
                    } ],
                    "meta" : {
                      "globalKey" : "f0d3e05e",
                      "externalKey" : "party2"
                    }
                  } ],
                  "contractDetails" : {
                    "documentation" : [ {
                      "legalAgreementIdentification" : {
                        "agreementName" : {
                          "agreementType" : "MasterAgreement",
                          "masterAgreementType" : {
                            "value" : "ISDAMaster"
                          }
                        },
                        "vintage" : 2002
                      },
                      "contractualParty" : [ {
                        "globalReference" : "f0d3e05e",
                        "externalReference" : "party2"
                      }, {
                        "globalReference" : "f0d36be0",
                        "externalReference" : "party1"
                      } ],
                      "meta" : {
                        "globalKey" : "32602a28"
                      }
                    }, {
                      "legalAgreementIdentification" : {
                        "agreementName" : {
                          "agreementType" : "Confirmation",
                          "contractualDefinitionsType" : [ {
                            "value" : "ISDA2002EquityDerivatives"
                          } ]
                        }
                      },
                      "contractualParty" : [ {
                        "globalReference" : "f0d3e05e",
                        "externalReference" : "party2"
                      }, {
                        "globalReference" : "f0d36be0",
                        "externalReference" : "party1"
                      } ],
                      "meta" : {
                        "globalKey" : "f1ff3e6b"
                      }
                    } ],
                    "governingLaw" : {
                      "value" : "GBEN"
                    },
                    "meta" : {
                      "globalKey" : "acc95347"
                    }
                  },
                  "meta" : {
                    "globalKey" : "cf422db6"
                  }
                },
                "state" : {
                  "positionState" : "Formed"
                },
                "transferHistory" : [ {
                  "transfer" : {
                    "quantity" : {
                      "value" : 405000,
                      "unit" : {
                        "currency" : {
                          "value" : "EUR"
                        }
                      }
                    },
                    "settlementDate" : {
                      "unadjustedDate" : "2001-07-17",
                      "dateAdjustments" : {
                        "businessDayConvention" : "NONE",
                        "meta" : {
                          "globalKey" : "24a738"
                        }
                      }
                    },
                    "payerReceiver" : {
                      "payerPartyReference" : {
                        "globalReference" : "f0d3e05e",
                        "externalReference" : "party2"
                      },
                      "receiverPartyReference" : {
                        "globalReference" : "f0d36be0",
                        "externalReference" : "party1"
                      }
                    },
                    "transferExpression" : {
                      "priceTransfer" : "Premium"
                    }
                  },
                  "meta" : {
                    "globalKey" : "aa78f85c"
                  }
                } ],
                "meta" : {
                  "globalKey" : "904316b5"
                }
              }
            }
          } ]
        },
        "timestamp" : [ {
          "dateTime" : "2001-07-13T16:42:16Z",
          "qualification" : "eventCreationDateTime"
        }, {
          "dateTime" : "2001-07-13T16:20:47Z",
          "qualification" : "executionDateTime"
        } ],
        "eventIdentifier" : [ {
          "assignedIdentifier" : [ {
            "identifier" : {
              "value" : "DIVGLAX945843"
            }
          } ],
          "meta" : {
            "globalKey" : "4983f80a"
          }
        }, {
          "assignedIdentifier" : [ {
            "identifier" : {
              "value" : "BI0159323322"
            }
          } ],
          "meta" : {
            "globalKey" : "42a33fab"
          }
        } ],
        "party" : [ {
          "partyId" : [ {
            "identifier" : {
              "value" : "Party A",
              "meta" : {
                "scheme" : "http://www.fpml.org/coding-scheme/dummy-party-id"
              }
            },
            "meta" : {
              "globalKey" : "33f59567"
            }
          }, {
            "identifier" : {
              "value" : "DUMMY0000000000LEI01",
              "meta" : {
                "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
              }
            },
            "identifierType" : "LEI",
            "meta" : {
              "globalKey" : "60fe4ea7"
            }
          } ],
          "meta" : {
            "globalKey" : "f0d36be0",
            "externalKey" : "party1"
          }
        }, {
          "partyId" : [ {
            "identifier" : {
              "value" : "Party B",
              "meta" : {
                "scheme" : "http://www.fpml.org/coding-scheme/dummy-party-id"
              }
            },
            "meta" : {
              "globalKey" : "33f59568"
            }
          }, {
            "identifier" : {
              "value" : "DUMMY0000000000LEI02",
              "meta" : {
                "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
              }
            },
            "identifierType" : "LEI",
            "meta" : {
              "globalKey" : "60fe4ec6"
            }
          } ],
          "meta" : {
            "globalKey" : "f0d3e05e",
            "externalKey" : "party2"
          }
        } ],
        "meta" : {
          "globalKey" : "962c9243"
        }
      }},
    acceptedWorkflowStep: {
      "businessEvent" : {
        "intent" : "CorporateActionAdjustment",
        "instruction" : [ {
          "primitiveInstruction" : {
            "observation" : {
              "observationEvent" : {
                "corporateAction" : {
                  "corporateActionType" : "CashDividend",
                  "exDate" : "2025-02-27",
                  "payDate" : "2025-03-14"
                }
              }
            }
          },
          "before" : {
            "value" : {
              "trade" : {
                "tradeIdentifier" : [ {
                  "issuerReference" : {
                    "globalReference" : "f0d36be0",
                    "externalReference" : "party1"
                  },
                  "assignedIdentifier" : [ {
                    "identifier" : {
                      "value" : "EQUITYOPTIONPRICER02R17E16C15I14R13P12N11O10I9T8P7O6",
                      "meta" : {
                        "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
                      }
                    }
                  } ],
                  "meta" : {
                    "globalKey" : "e38feb88"
                  },
                  "identifierType" : "UniqueTransactionIdentifier"
                }, {
                  "issuerReference" : {
                    "globalReference" : "f0d36be0",
                    "externalReference" : "party1"
                  },
                  "assignedIdentifier" : [ {
                    "identifier" : {
                      "value" : "EQUITYOPTIONPRICER01",
                      "meta" : {
                        "scheme" : "http://www.PartyA.com/eqd-trade-id"
                      }
                    }
                  } ],
                  "meta" : {
                    "globalKey" : "8c698b79"
                  }
                } ],
                "tradeDate" : {
                  "value" : "2001-07-13",
                  "meta" : {
                    "globalKey" : "3e89cd",
                    "externalKey" : "TradeDate"
                  }
                },
                "party" : [ {
                  "partyId" : [ {
                    "identifier" : {
                      "value" : "Party A",
                      "meta" : {
                        "scheme" : "http://www.fpml.org/coding-scheme/dummy-party-id"
                      }
                    },
                    "meta" : {
                      "globalKey" : "33f59567"
                    }
                  }, {
                    "identifier" : {
                      "value" : "DUMMY0000000000LEI01",
                      "meta" : {
                        "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
                      }
                    },
                    "identifierType" : "LEI",
                    "meta" : {
                      "globalKey" : "60fe4ea7"
                    }
                  } ],
                  "meta" : {
                    "globalKey" : "f0d36be0",
                    "externalKey" : "party1"
                  }
                }, {
                  "partyId" : [ {
                    "identifier" : {
                      "value" : "Party B",
                      "meta" : {
                        "scheme" : "http://www.fpml.org/coding-scheme/dummy-party-id"
                      }
                    },
                    "meta" : {
                      "globalKey" : "33f59568"
                    }
                  }, {
                    "identifier" : {
                      "value" : "DUMMY0000000000LEI02",
                      "meta" : {
                        "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
                      }
                    },
                    "identifierType" : "LEI",
                    "meta" : {
                      "globalKey" : "60fe4ec6"
                    }
                  } ],
                  "meta" : {
                    "globalKey" : "f0d3e05e",
                    "externalKey" : "party2"
                  }
                } ],
                "contractDetails" : {
                  "documentation" : [ {
                    "legalAgreementIdentification" : {
                      "agreementName" : {
                        "agreementType" : "MasterAgreement",
                        "masterAgreementType" : {
                          "value" : "ISDAMaster"
                        }
                      },
                      "vintage" : 2002
                    },
                    "contractualParty" : [ {
                      "globalReference" : "f0d3e05e",
                      "externalReference" : "party2"
                    }, {
                      "globalReference" : "f0d36be0",
                      "externalReference" : "party1"
                    } ],
                    "meta" : {
                      "globalKey" : "32602a28"
                    }
                  }, {
                    "legalAgreementIdentification" : {
                      "agreementName" : {
                        "agreementType" : "Confirmation",
                        "contractualDefinitionsType" : [ {
                          "value" : "ISDA2002EquityDerivatives"
                        } ]
                      }
                    },
                    "contractualParty" : [ {
                      "globalReference" : "f0d3e05e",
                      "externalReference" : "party2"
                    }, {
                      "globalReference" : "f0d36be0",
                      "externalReference" : "party1"
                    } ],
                    "meta" : {
                      "globalKey" : "f1ff3e6b"
                    }
                  } ],
                  "governingLaw" : {
                    "value" : "GBEN"
                  },
                  "meta" : {
                    "globalKey" : "acc95347"
                  }
                },
                "meta" : {
                  "globalKey" : "cf422db6"
                }
              },
              "state" : {
                "positionState" : "Formed"
              },
              "transferHistory" : [ {
                "transfer" : {
                  "quantity" : {
                    "value" : 405000,
                    "unit" : {
                      "currency" : {
                        "value" : "EUR"
                      }
                    }
                  },
                  "settlementDate" : {
                    "unadjustedDate" : "2001-07-17",
                    "dateAdjustments" : {
                      "businessDayConvention" : "NONE",
                      "meta" : {
                        "globalKey" : "24a738"
                      }
                    }
                  },
                  "payerReceiver" : {
                    "payerPartyReference" : {
                      "globalReference" : "f0d3e05e",
                      "externalReference" : "party2"
                    },
                    "receiverPartyReference" : {
                      "globalReference" : "f0d36be0",
                      "externalReference" : "party1"
                    }
                  },
                  "transferExpression" : {
                    "priceTransfer" : "Premium"
                  }
                },
                "meta" : {
                  "globalKey" : "aa78f85c"
                }
              } ],
              "meta" : {
                "globalKey" : "904316b5"
              }
            }
          }
        } ],
        "eventQualifier" : "CorporateActionDetermined",
        "after" : [ {
          "trade" : {
            "tradeIdentifier" : [ {
              "issuerReference" : {
                "globalReference" : "f0d36be0",
                "externalReference" : "party1"
              },
              "assignedIdentifier" : [ {
                "identifier" : {
                  "value" : "EQUITYOPTIONPRICER02R17E16C15I14R13P12N11O10I9T8P7O6",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
                  }
                }
              } ],
              "meta" : {
                "globalKey" : "e38feb88"
              },
              "identifierType" : "UniqueTransactionIdentifier"
            }, {
              "issuerReference" : {
                "globalReference" : "f0d36be0",
                "externalReference" : "party1"
              },
              "assignedIdentifier" : [ {
                "identifier" : {
                  "value" : "EQUITYOPTIONPRICER01",
                  "meta" : {
                    "scheme" : "http://www.PartyA.com/eqd-trade-id"
                  }
                }
              } ],
              "meta" : {
                "globalKey" : "8c698b79"
              }
            } ],
            "tradeDate" : {
              "value" : "2001-07-13",
              "meta" : {
                "globalKey" : "3e89cd",
                "externalKey" : "TradeDate"
              }
            },
            "party" : [ {
              "partyId" : [ {
                "identifier" : {
                  "value" : "Party A",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/dummy-party-id"
                  }
                },
                "meta" : {
                  "globalKey" : "33f59567"
                }
              }, {
                "identifier" : {
                  "value" : "DUMMY0000000000LEI01",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
                  }
                },
                "identifierType" : "LEI",
                "meta" : {
                  "globalKey" : "60fe4ea7"
                }
              } ],
              "meta" : {
                "globalKey" : "f0d36be0",
                "externalKey" : "party1"
              }
            }, {
              "partyId" : [ {
                "identifier" : {
                  "value" : "Party B",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/dummy-party-id"
                  }
                },
                "meta" : {
                  "globalKey" : "33f59568"
                }
              }, {
                "identifier" : {
                  "value" : "DUMMY0000000000LEI02",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
                  }
                },
                "identifierType" : "LEI",
                "meta" : {
                  "globalKey" : "60fe4ec6"
                }
              } ],
              "meta" : {
                "globalKey" : "f0d3e05e",
                "externalKey" : "party2"
              }
            } ],
            "contractDetails" : {
              "documentation" : [ {
                "legalAgreementIdentification" : {
                  "agreementName" : {
                    "agreementType" : "MasterAgreement",
                    "masterAgreementType" : {
                      "value" : "ISDAMaster"
                    }
                  },
                  "vintage" : 2002
                },
                "contractualParty" : [ {
                  "globalReference" : "f0d3e05e",
                  "externalReference" : "party2"
                }, {
                  "globalReference" : "f0d36be0",
                  "externalReference" : "party1"
                } ],
                "meta" : {
                  "globalKey" : "32602a28"
                }
              }, {
                "legalAgreementIdentification" : {
                  "agreementName" : {
                    "agreementType" : "Confirmation",
                    "contractualDefinitionsType" : [ {
                      "value" : "ISDA2002EquityDerivatives"
                    } ]
                  }
                },
                "contractualParty" : [ {
                  "globalReference" : "f0d3e05e",
                  "externalReference" : "party2"
                }, {
                  "globalReference" : "f0d36be0",
                  "externalReference" : "party1"
                } ],
                "meta" : {
                  "globalKey" : "f1ff3e6b"
                }
              } ],
              "governingLaw" : {
                "value" : "GBEN"
              },
              "meta" : {
                "globalKey" : "acc95347"
              }
            },
            "meta" : {
              "globalKey" : "cf422db6"
            }
          },
          "state" : {
            "positionState" : "Formed"
          },
          "transferHistory" : [ {
            "transfer" : {
              "quantity" : {
                "value" : 405000,
                "unit" : {
                  "currency" : {
                    "value" : "EUR"
                  }
                }
              },
              "settlementDate" : {
                "unadjustedDate" : "2001-07-17",
                "dateAdjustments" : {
                  "businessDayConvention" : "NONE",
                  "meta" : {
                    "globalKey" : "24a738"
                  }
                }
              },
              "payerReceiver" : {
                "payerPartyReference" : {
                  "globalReference" : "f0d3e05e",
                  "externalReference" : "party2"
                },
                "receiverPartyReference" : {
                  "globalReference" : "f0d36be0",
                  "externalReference" : "party1"
                }
              },
              "transferExpression" : {
                "priceTransfer" : "Premium"
              }
            },
            "meta" : {
              "globalKey" : "aa78f85c"
            }
          } ],
          "observationHistory" : [ {
            "corporateAction" : {
              "corporateActionType" : "CashDividend",
              "exDate" : "2025-02-27",
              "payDate" : "2025-03-14"
            }
          } ],
          "meta" : {
            "globalKey" : "2459dab9"
          }
        } ],
        "meta" : {
          "globalKey" : "fd01a58"
        }
      },
      "previousWorkflowStep" : {
        "globalReference" : "962c9243"
      },
      "timestamp" : [ {
        "dateTime" : "2001-07-13T16:42:16Z",
        "qualification" : "eventCreationDateTime"
      }, {
        "dateTime" : "2001-07-13T16:20:47Z",
        "qualification" : "executionDateTime"
      } ],
      "eventIdentifier" : [ {
        "assignedIdentifier" : [ {
          "identifier" : {
            "value" : "DIVGLAX945843"
          }
        } ],
        "meta" : {
          "globalKey" : "4983f80a"
        }
      }, {
        "assignedIdentifier" : [ {
          "identifier" : {
            "value" : "BI0159323322"
          }
        } ],
        "meta" : {
          "globalKey" : "42a33fab"
        }
      } ],
      "party" : [ {
        "partyId" : [ {
          "identifier" : {
            "value" : "Party A",
            "meta" : {
              "scheme" : "http://www.fpml.org/coding-scheme/dummy-party-id"
            }
          },
          "meta" : {
            "globalKey" : "33f59567"
          }
        }, {
          "identifier" : {
            "value" : "DUMMY0000000000LEI01",
            "meta" : {
              "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
            }
          },
          "identifierType" : "LEI",
          "meta" : {
            "globalKey" : "60fe4ea7"
          }
        } ],
        "meta" : {
          "globalKey" : "f0d36be0",
          "externalKey" : "party1"
        }
      }, {
        "partyId" : [ {
          "identifier" : {
            "value" : "Party B",
            "meta" : {
              "scheme" : "http://www.fpml.org/coding-scheme/dummy-party-id"
            }
          },
          "meta" : {
            "globalKey" : "33f59568"
          }
        }, {
          "identifier" : {
            "value" : "DUMMY0000000000LEI02",
            "meta" : {
              "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
            }
          },
          "identifierType" : "LEI",
          "meta" : {
            "globalKey" : "60fe4ec6"
          }
        } ],
        "meta" : {
          "globalKey" : "f0d3e05e",
          "externalKey" : "party2"
        }
      } ],
      "meta" : {
        "globalKey" : "27b7de89"
      }
    },
  },
]