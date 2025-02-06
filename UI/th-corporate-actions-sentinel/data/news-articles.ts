export const newsArticles = [
  {
    id: 1,
    impactedTradeName: "equity-option-price-return-stock-ex01-american-call-input.json",
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
  {
    id:2,
    headline: "ExxonMobil Increases Dividend to $0.95 Amid Strong Energy Market",
    impactedTradeName: "equity-swap-parameter-return-dividend-single-name-ex01-new-input.json",
    content: `Irving, TX – February 3, 2025 – ExxonMobil (NYSE: XOM) has announced an increase in its quarterly dividend to $0.95 per share, up 4.4% from the previous payout. The dividend will be payable on March 10, 2025, to shareholders on record as of February 28, 2025, with an ex-dividend date of February 27, 2025.

Key Dividend Details:

New Dividend:  $0.95 per share

Increase: 4.4% from prior $0.91 payout

Annual Yield:  3.87% at current stock price

Payment Date:  March 10, 2025

Ex-Dividend Date: February 27, 2025

ExxonMobil’s dividend hike follows strong earnings, driven by higher refining margins and robust demand for liquefied natural gas (LNG). The company remains committed to shareholder returns while advancing its carbon capture and hydrogen investments.

Analysts note that ExxonMobil’s steady dividend increases make it a top pick for income-focused investors, particularly as oil prices stabilize above $80 per barrel.

For further details, visit ExxonMobil Investor Relations.`,
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
			<CorpActnEvtId>DIVGLAX123234</CorpActnEvtId>
			<OffclCorpActnEvtId>BI015932134564</OffclCorpActnEvtId>
				<EvtTp>
				<Cd>DVCA</Cd>
			</EvtTp>
			<MndtryVlntryEvtTp>
				<Cd>MAND</Cd>
			</MndtryVlntryEvtTp>
			<UndrlygScty>
				<FinInstrmId>
					<ISIN>US30231G1022</ISIN> 
					<OthrId>
						<Id>XOM</Id>     
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
					<Dt>2025-03-10</Dt> 
				</PmtDt>
			</DtDtls>
			<RateAndAmtDtls>
				<IntrstRate>
					<Rate>3.87</Rate>
				</IntrstRate>
			</RateAndAmtDtls>
		</CorpActnDtls>
	</CorpActnNtfctn>
</Document>

`,
    impactedOpenTrade: {
    "businessEvent" : {
      "intent" : "ContractFormation",
      "eventDate" : "2020-03-17",
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
                "globalReference" : "60fe4ec6",
                "externalReference" : "PartyB"
              }, {
                "globalReference" : "60fe4ea7",
                "externalReference" : "PartyA"
              } ],
              "meta" : {
                "globalKey" : "8241872a"
              }
            } ]
          }
        },
        "before" : {
          "value" : {
            "trade" : {
              "tradeIdentifier" : [ {
                "issuer" : {
                  "value" : "DUMMY0000000000LEI01",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/external/issuer-identifier"
                  }
                },
                "assignedIdentifier" : [ {
                  "identifier" : {
                    "value" : "EQUITYSWAPPARAMETE01",
                    "meta" : {
                      "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
                    }
                  }
                } ],
                "meta" : {
                  "globalKey" : "6240739f"
                },
                "identifierType" : "UniqueTransactionIdentifier"
              } ],
              "tradeDate" : {
                "value" : "2019-01-10",
                "meta" : {
                  "globalKey" : "3f184a"
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
                          "value" : "SESDXC",
                          "meta" : {
                            "scheme" : "http://www.fpml.org/coding-scheme/external/product-classification/iso10962"
                          }
                        }
                      }
                    }, {
                      "source" : "ISDA",
                      "productQualifier" : "EquitySwap_ParameterReturnDividend_SingleName"
                    } ],
                    "productIdentifier" : [ {
                      "value" : {
                        "identifier" : {
                          "value" : "Equity:Swap:ParameterReturnDividend:SingleName",
                          "meta" : {
                            "scheme" : "http://www.fpml.org/coding-scheme/product-taxonomy"
                          }
                        },
                        "source" : "Other",
                        "meta" : {
                          "globalKey" : "81589d99"
                        }
                      }
                    } ],
                    "economicTerms" : {
                      "effectiveDate" : {
                        "adjustableDate" : {
                          "unadjustedDate" : "2022-01-02",
                          "meta" : {
                            "globalKey" : "3f3042"
                          }
                        },
                        "meta" : {
                          "globalKey" : "3f3042"
                        }
                      },
                      "terminationDate" : {
                        "adjustableDate" : {
                          "unadjustedDate" : "2022-12-31",
                          "meta" : {
                            "globalKey" : "3f331f"
                          }
                        },
                        "meta" : {
                          "globalKey" : "3f331f"
                        }
                      },
                      "payout" : {
                        "fixedPricePayout" : [ {
                          "payerReceiver" : {
                            "payer" : "Party1",
                            "receiver" : "Party2"
                          },
                          "priceQuantity" : {
                            "quantitySchedule" : {
                              "address" : {
                                "scope" : "DOCUMENT",
                                "value" : "quantity-2"
                              }
                            },
                            "meta" : {
                              "globalKey" : "0"
                            }
                          },
                          "settlementTerms" : {
                            "settlementDate" : {
                              "adjustableOrRelativeDate" : {
                                "relativeDate" : {
                                  "periodMultiplier" : 3,
                                  "period" : "D",
                                  "meta" : {
                                    "globalKey" : "d9fe26b1"
                                  },
                                  "dayType" : "Calendar",
                                  "businessDayConvention" : "FOLLOWING",
                                  "businessCenters" : {
                                    "businessCenter" : [ {
                                      "value" : "CATO"
                                    } ],
                                    "meta" : {
                                      "globalKey" : "1f7359"
                                    }
                                  },
                                  "dateRelativeTo" : {
                                    "externalReference" : "e1"
                                  }
                                }
                              },
                              "meta" : {
                                "globalKey" : "d9fe26b1"
                              }
                            },
                            "meta" : {
                              "globalKey" : "d9fe26b1"
                            }
                          },
                          "fixedPrice" : {
                            "price" : {
                              "value" : {
                                "value" : 0.045,
                                "priceType" : "Dividend"
                              }
                            }
                          },
                          "meta" : {
                            "globalKey" : "c14be9aa",
                            "externalKey" : "d1"
                          }
                        }, {
                          "payerReceiver" : {
                            "payer" : "Party1",
                            "receiver" : "Party2"
                          },
                          "fixedPrice" : {
                            "price" : {
                              "value" : {
                                "value" : 0.045,
                                "priceType" : "Dividend"
                              }
                            }
                          },
                          "meta" : {
                            "globalKey" : "57514bfb",
                            "externalKey" : "d2"
                          }
                        } ],
                        "performancePayout" : [ {
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
                            "settlementCurrency" : {
                              "value" : "CAD"
                            },
                            "meta" : {
                              "globalKey" : "103a6"
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
                          "returnTerms" : {
                            "dividendReturnTerms" : {
                              "dividendPayoutRatio" : [ {
                                "cashRatio" : 1.0,
                                "nonCashRatio" : 1.0
                              } ],
                              "dividendPeriod" : [ {
                                "startDate" : {
                                  "dividendDate" : {
                                    "value" : {
                                      "adjustableDate" : {
                                        "unadjustedDate" : "2010-12-20",
                                        "meta" : {
                                          "globalKey" : "3ed314"
                                        }
                                      },
                                      "meta" : {
                                        "globalKey" : "3ed314"
                                      }
                                    }
                                  }
                                },
                                "endDate" : {
                                  "dividendDate" : {
                                    "value" : {
                                      "adjustableDate" : {
                                        "unadjustedDate" : "2011-12-19",
                                        "meta" : {
                                          "globalKey" : "3edb13"
                                        }
                                      },
                                      "meta" : {
                                        "globalKey" : "3edb13"
                                      }
                                    }
                                  }
                                },
                                "dateAdjustments" : {
                                  "businessDayConvention" : "FOLLOWING",
                                  "businessCenters" : {
                                    "businessCenter" : [ {
                                      "value" : "EUTA"
                                    } ],
                                    "meta" : {
                                      "globalKey" : "20a71d"
                                    }
                                  },
                                  "meta" : {
                                    "globalKey" : "211dc30c"
                                  }
                                },
                                "dividendPaymentDate" : {
                                  "dividendDate" : {
                                    "value" : {
                                      "relativeDate" : {
                                        "periodMultiplier" : 3,
                                        "period" : "D",
                                        "meta" : {
                                          "globalKey" : "815062c0"
                                        },
                                        "dayType" : "Calendar",
                                        "businessDayConvention" : "FOLLOWING",
                                        "dateRelativeTo" : {
                                          "externalReference" : "e1"
                                        }
                                      },
                                      "meta" : {
                                        "globalKey" : "815062c0",
                                        "externalKey" : "p1"
                                      }
                                    }
                                  }
                                }
                              }, {
                                "startDate" : {
                                  "dividendDate" : {
                                    "value" : {
                                      "adjustableDate" : {
                                        "unadjustedDate" : "2011-12-20",
                                        "meta" : {
                                          "globalKey" : "3edb14"
                                        }
                                      },
                                      "meta" : {
                                        "globalKey" : "3edb14"
                                      }
                                    }
                                  }
                                },
                                "endDate" : {
                                  "dividendDate" : {
                                    "value" : {
                                      "adjustableDate" : {
                                        "unadjustedDate" : "2012-12-19",
                                        "meta" : {
                                          "globalKey" : "3ee313"
                                        }
                                      },
                                      "meta" : {
                                        "globalKey" : "3ee313"
                                      }
                                    }
                                  }
                                },
                                "dateAdjustments" : {
                                  "businessDayConvention" : "FOLLOWING",
                                  "businessCenters" : {
                                    "businessCenter" : [ {
                                      "value" : "EUTA"
                                    } ],
                                    "meta" : {
                                      "globalKey" : "20a71d"
                                    }
                                  },
                                  "meta" : {
                                    "globalKey" : "211dc30c"
                                  }
                                },
                                "dividendPaymentDate" : {
                                  "dividendDate" : {
                                    "value" : {
                                      "relativeDate" : {
                                        "periodMultiplier" : 3,
                                        "period" : "D",
                                        "meta" : {
                                          "globalKey" : "815062c1"
                                        },
                                        "dayType" : "Calendar",
                                        "businessDayConvention" : "FOLLOWING",
                                        "dateRelativeTo" : {
                                          "externalReference" : "e2"
                                        }
                                      },
                                      "meta" : {
                                        "globalKey" : "815062c1",
                                        "externalKey" : "p2"
                                      }
                                    }
                                  }
                                }
                              } ]
                            }
                          },
                          "meta" : {
                            "globalKey" : "5a195161"
                          }
                        } ],
                        "meta" : {
                          "globalKey" : "e3fb3a86"
                        }
                      },
                      "nonStandardisedTerms" : false
                    },
                    "meta" : {
                      "globalKey" : "1caa4f40"
                    }
                  },
                  "meta" : {
                    "globalKey" : "1caa4f40"
                  }
                },
                "tradeLot" : [ {
                  "priceQuantity" : [ {
                    "quantity" : [ {
                      "value" : {
                        "value" : 656000.00,
                        "unit" : {
                          "financialUnit" : "Share"
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
                            "value" : "XOM",
                            "meta" : {
                              "scheme" : "http://www.fpml.org/coding-scheme/external/instrument-id-Bloomberg"
                            }
                          },
                          "source" : "BBGTICKER",
                          "meta" : {
                            "globalKey" : "8bdfd5cb"
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
                            "value" : "US30231G1022",
                            "meta" : {
                              "scheme" : "http://www.fpml.org/spec/2002/instrument-id-ISIN"
                            }
                          },
                          "source" : "ISIN",
                          "meta" : {
                            "globalKey" : "968a4117"
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
                        "globalKey" : "a53af354"
                      }
                    },
                    "meta" : {
                      "globalKey" : "f3a8144c"
                    }
                  }, {
                    "quantity" : [ {
                      "value" : {
                        "value" : 682240.00,
                        "unit" : {
                          "currency" : {
                            "value" : "CAD"
                          }
                        }
                      },
                      "meta" : {
                        "location" : [ {
                          "scope" : "DOCUMENT",
                          "value" : "quantity-2"
                        } ]
                      }
                    } ],
                    "meta" : {
                      "globalKey" : "f0e85498"
                    }
                  } ]
                } ],
                "counterparty" : [ {
                  "role" : "Party1",
                  "partyReference" : {
                    "globalReference" : "60fe4ec6",
                    "externalReference" : "PartyB"
                  }
                }, {
                  "role" : "Party2",
                  "partyReference" : {
                    "globalReference" : "60fe4ea7",
                    "externalReference" : "PartyA"
                  }
                } ]
              },
              "party" : [ {
                "partyId" : [ {
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
                  "globalKey" : "60fe4ea7",
                  "externalKey" : "PartyA"
                }
              }, {
                "partyId" : [ {
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
                  "globalKey" : "60fe4ec6",
                  "externalKey" : "PartyB"
                }
              } ],
              "meta" : {
                "globalKey" : "dde2fad8"
              }
            },
            "meta" : {
              "globalKey" : "dde2fad8"
            }
          }
        }
      } ],
      "after" : [ {
        "trade" : {
          "tradeIdentifier" : [ {
            "issuer" : {
              "value" : "DUMMY0000000000LEI01",
              "meta" : {
                "scheme" : "http://www.fpml.org/coding-scheme/external/issuer-identifier"
              }
            },
            "assignedIdentifier" : [ {
              "identifier" : {
                "value" : "EQUITYSWAPPARAMETE01",
                "meta" : {
                  "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
                }
              }
            } ],
            "meta" : {
              "globalKey" : "6240739f"
            },
            "identifierType" : "UniqueTransactionIdentifier"
          } ],
          "tradeDate" : {
            "value" : "2019-01-10",
            "meta" : {
              "globalKey" : "3f184a"
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
                      "value" : "SESDXC",
                      "meta" : {
                        "scheme" : "http://www.fpml.org/coding-scheme/external/product-classification/iso10962"
                      }
                    }
                  }
                }, {
                  "source" : "ISDA",
                  "productQualifier" : "EquitySwap_ParameterReturnDividend_SingleName"
                } ],
                "productIdentifier" : [ {
                  "value" : {
                    "identifier" : {
                      "value" : "Equity:Swap:ParameterReturnDividend:SingleName",
                      "meta" : {
                        "scheme" : "http://www.fpml.org/coding-scheme/product-taxonomy"
                      }
                    },
                    "source" : "Other",
                    "meta" : {
                      "globalKey" : "81589d99"
                    }
                  }
                } ],
                "economicTerms" : {
                  "effectiveDate" : {
                    "adjustableDate" : {
                      "unadjustedDate" : "2022-01-02",
                      "meta" : {
                        "globalKey" : "3f3042"
                      }
                    },
                    "meta" : {
                      "globalKey" : "3f3042"
                    }
                  },
                  "terminationDate" : {
                    "adjustableDate" : {
                      "unadjustedDate" : "2022-12-31",
                      "meta" : {
                        "globalKey" : "3f331f"
                      }
                    },
                    "meta" : {
                      "globalKey" : "3f331f"
                    }
                  },
                  "payout" : {
                    "fixedPricePayout" : [ {
                      "payerReceiver" : {
                        "payer" : "Party1",
                        "receiver" : "Party2"
                      },
                      "priceQuantity" : {
                        "quantitySchedule" : {
                          "address" : {
                            "scope" : "DOCUMENT",
                            "value" : "quantity-2"
                          }
                        },
                        "meta" : {
                          "globalKey" : "0"
                        }
                      },
                      "settlementTerms" : {
                        "settlementDate" : {
                          "adjustableOrRelativeDate" : {
                            "relativeDate" : {
                              "periodMultiplier" : 3,
                              "period" : "D",
                              "meta" : {
                                "globalKey" : "d9fe26b1"
                              },
                              "dayType" : "Calendar",
                              "businessDayConvention" : "FOLLOWING",
                              "businessCenters" : {
                                "businessCenter" : [ {
                                  "value" : "CATO"
                                } ],
                                "meta" : {
                                  "globalKey" : "1f7359"
                                }
                              },
                              "dateRelativeTo" : {
                                "externalReference" : "e1"
                              }
                            }
                          },
                          "meta" : {
                            "globalKey" : "d9fe26b1"
                          }
                        },
                        "meta" : {
                          "globalKey" : "d9fe26b1"
                        }
                      },
                      "fixedPrice" : {
                        "price" : {
                          "value" : {
                            "value" : 0.045,
                            "priceType" : "Dividend"
                          }
                        }
                      },
                      "meta" : {
                        "globalKey" : "c14be9aa",
                        "externalKey" : "d1"
                      }
                    }, {
                      "payerReceiver" : {
                        "payer" : "Party1",
                        "receiver" : "Party2"
                      },
                      "fixedPrice" : {
                        "price" : {
                          "value" : {
                            "value" : 0.045,
                            "priceType" : "Dividend"
                          }
                        }
                      },
                      "meta" : {
                        "globalKey" : "57514bfb",
                        "externalKey" : "d2"
                      }
                    } ],
                    "performancePayout" : [ {
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
                        "settlementCurrency" : {
                          "value" : "CAD"
                        },
                        "meta" : {
                          "globalKey" : "103a6"
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
                      "returnTerms" : {
                        "dividendReturnTerms" : {
                          "dividendPayoutRatio" : [ {
                            "cashRatio" : 1.0,
                            "nonCashRatio" : 1.0
                          } ],
                          "dividendPeriod" : [ {
                            "startDate" : {
                              "dividendDate" : {
                                "value" : {
                                  "adjustableDate" : {
                                    "unadjustedDate" : "2010-12-20",
                                    "meta" : {
                                      "globalKey" : "3ed314"
                                    }
                                  },
                                  "meta" : {
                                    "globalKey" : "3ed314"
                                  }
                                }
                              }
                            },
                            "endDate" : {
                              "dividendDate" : {
                                "value" : {
                                  "adjustableDate" : {
                                    "unadjustedDate" : "2011-12-19",
                                    "meta" : {
                                      "globalKey" : "3edb13"
                                    }
                                  },
                                  "meta" : {
                                    "globalKey" : "3edb13"
                                  }
                                }
                              }
                            },
                            "dateAdjustments" : {
                              "businessDayConvention" : "FOLLOWING",
                              "businessCenters" : {
                                "businessCenter" : [ {
                                  "value" : "EUTA"
                                } ],
                                "meta" : {
                                  "globalKey" : "20a71d"
                                }
                              },
                              "meta" : {
                                "globalKey" : "211dc30c"
                              }
                            },
                            "dividendPaymentDate" : {
                              "dividendDate" : {
                                "value" : {
                                  "relativeDate" : {
                                    "periodMultiplier" : 3,
                                    "period" : "D",
                                    "meta" : {
                                      "globalKey" : "815062c0"
                                    },
                                    "dayType" : "Calendar",
                                    "businessDayConvention" : "FOLLOWING",
                                    "dateRelativeTo" : {
                                      "externalReference" : "e1"
                                    }
                                  },
                                  "meta" : {
                                    "globalKey" : "815062c0",
                                    "externalKey" : "p1"
                                  }
                                }
                              }
                            }
                          }, {
                            "startDate" : {
                              "dividendDate" : {
                                "value" : {
                                  "adjustableDate" : {
                                    "unadjustedDate" : "2011-12-20",
                                    "meta" : {
                                      "globalKey" : "3edb14"
                                    }
                                  },
                                  "meta" : {
                                    "globalKey" : "3edb14"
                                  }
                                }
                              }
                            },
                            "endDate" : {
                              "dividendDate" : {
                                "value" : {
                                  "adjustableDate" : {
                                    "unadjustedDate" : "2012-12-19",
                                    "meta" : {
                                      "globalKey" : "3ee313"
                                    }
                                  },
                                  "meta" : {
                                    "globalKey" : "3ee313"
                                  }
                                }
                              }
                            },
                            "dateAdjustments" : {
                              "businessDayConvention" : "FOLLOWING",
                              "businessCenters" : {
                                "businessCenter" : [ {
                                  "value" : "EUTA"
                                } ],
                                "meta" : {
                                  "globalKey" : "20a71d"
                                }
                              },
                              "meta" : {
                                "globalKey" : "211dc30c"
                              }
                            },
                            "dividendPaymentDate" : {
                              "dividendDate" : {
                                "value" : {
                                  "relativeDate" : {
                                    "periodMultiplier" : 3,
                                    "period" : "D",
                                    "meta" : {
                                      "globalKey" : "815062c1"
                                    },
                                    "dayType" : "Calendar",
                                    "businessDayConvention" : "FOLLOWING",
                                    "dateRelativeTo" : {
                                      "externalReference" : "e2"
                                    }
                                  },
                                  "meta" : {
                                    "globalKey" : "815062c1",
                                    "externalKey" : "p2"
                                  }
                                }
                              }
                            }
                          } ]
                        }
                      },
                      "meta" : {
                        "globalKey" : "5a195161"
                      }
                    } ],
                    "meta" : {
                      "globalKey" : "e3fb3a86"
                    }
                  },
                  "nonStandardisedTerms" : false
                },
                "meta" : {
                  "globalKey" : "1caa4f40"
                }
              },
              "meta" : {
                "globalKey" : "1caa4f40"
              }
            },
            "tradeLot" : [ {
              "priceQuantity" : [ {
                "quantity" : [ {
                  "value" : {
                    "value" : 656000.00,
                    "unit" : {
                      "financialUnit" : "Share"
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
                        "value" : "XOM",
                        "meta" : {
                          "scheme" : "http://www.fpml.org/coding-scheme/external/instrument-id-Bloomberg"
                        }
                      },
                      "source" : "Other",
                      "meta" : {
                        "globalKey" : "8bdfd5cb"
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
                        "value" : "US30231G1022",
                        "meta" : {
                          "scheme" : "http://www.fpml.org/spec/2002/instrument-id-ISIN"
                        }
                      },
                      "source" : "ISIN",
                      "meta" : {
                        "globalKey" : "968a4117"
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
                    "globalKey" : "a53af354"
                  }
                },
                "meta" : {
                  "globalKey" : "f3a8144c"
                }
              }, {
                "quantity" : [ {
                  "value" : {
                    "value" : 682240.00,
                    "unit" : {
                      "currency" : {
                        "value" : "CAD"
                      }
                    }
                  },
                  "meta" : {
                    "location" : [ {
                      "scope" : "DOCUMENT",
                      "value" : "quantity-2"
                    } ]
                  }
                } ],
                "meta" : {
                  "globalKey" : "f0e85498"
                }
              } ]
            } ],
            "counterparty" : [ {
              "role" : "Party1",
              "partyReference" : {
                "globalReference" : "60fe4ec6",
                "externalReference" : "PartyB"
              }
            }, {
              "role" : "Party2",
              "partyReference" : {
                "globalReference" : "60fe4ea7",
                "externalReference" : "PartyA"
              }
            } ]
          },
          "party" : [ {
            "partyId" : [ {
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
              "globalKey" : "60fe4ea7",
              "externalKey" : "PartyA"
            }
          }, {
            "partyId" : [ {
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
              "globalKey" : "60fe4ec6",
              "externalKey" : "PartyB"
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
                "globalReference" : "60fe4ec6",
                "externalReference" : "PartyB"
              }, {
                "globalReference" : "60fe4ea7",
                "externalReference" : "PartyA"
              } ],
              "meta" : {
                "globalKey" : "8241872a"
              }
            } ]
          },
          "meta" : {
            "globalKey" : "dde2fad8"
          }
        },
        "state" : {
          "positionState" : "Formed"
        },
        "meta" : {
          "globalKey" : "dde2fad8"
        }
      } ]
    },
    "previousWorkflowStep" : {
      "globalReference" : "c5b4d9eb"
    },
    "messageInformation" : {
      "messageId" : {
        "value" : "745ec9f9271945d49e6915e9076ad6c9",
        "meta" : {
          "scheme" : "http://www.fpml.org/coding-scheme/external/technical-record-id"
        }
      },
      "sentBy" : {
        "value" : "DUMMY0000000000LEI01"
      },
      "sentTo" : [ {
        "value" : "DTCCGTR"
      } ]
    },
    "timestamp" : [ {
      "dateTime" : "2021-11-09T07:47:51.4265352Z",
      "qualification" : "eventCreationDateTime"
    }, {
      "dateTime" : "2020-03-17T08:03:27Z",
      "qualification" : "executionDateTime"
    } ],
    "eventIdentifier" : [ {
      "assignedIdentifier" : [ {
        "identifier" : {
          "value" : "745ec9f9271945d49e6915e9076ad6c9"
        }
      } ],
      "meta" : {
        "globalKey" : "db50b0d5"
      }
    } ],
    "action" : "New",
    "party" : [ {
      "partyId" : [ {
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
        "globalKey" : "60fe4ea7",
        "externalKey" : "PartyA"
      }
    }, {
      "partyId" : [ {
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
        "globalKey" : "60fe4ec6",
        "externalKey" : "PartyB"
      }
    } ]
  },
    proposedEvent: {
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
              "payDate" : "2025-03-10"
            }
          }
        }
      },
      "before" : {
        "value" : {
          "trade" : {
            "tradeIdentifier" : [ {
              "issuer" : {
                "value" : "DUMMY0000000000LEI01",
                "meta" : {
                  "scheme" : "http://www.fpml.org/coding-scheme/external/issuer-identifier"
                }
              },
              "assignedIdentifier" : [ {
                "identifier" : {
                  "value" : "EQUITYSWAPPARAMETE01",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
                  }
                }
              } ],
              "meta" : {
                "globalKey" : "6240739f"
              },
              "identifierType" : "UniqueTransactionIdentifier"
            } ],
            "tradeDate" : {
              "value" : "2019-01-10",
              "meta" : {
                "globalKey" : "3f184a"
              }
            },
            "party" : [ {
              "partyId" : [ {
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
                "globalKey" : "60fe4ea7",
                "externalKey" : "PartyA"
              }
            }, {
              "partyId" : [ {
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
                "globalKey" : "60fe4ec6",
                "externalKey" : "PartyB"
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
                  "globalReference" : "60fe4ec6",
                  "externalReference" : "PartyB"
                }, {
                  "globalReference" : "60fe4ea7",
                  "externalReference" : "PartyA"
                } ],
                "meta" : {
                  "globalKey" : "f53a5215"
                }
              } ],
              "meta" : {
                "globalKey" : "f53a5215"
              }
            },
            "meta" : {
              "globalKey" : "6df39be1"
            }
          },
          "state" : {
            "positionState" : "Formed"
          },
          "meta" : {
            "globalKey" : "3b6f1404"
          }
        }
      }
    } ]
  },
  "timestamp" : [ {
    "dateTime" : "2021-11-09T07:47:51.4265352Z",
    "qualification" : "eventCreationDateTime"
  }, {
    "dateTime" : "2020-03-17T08:03:27Z",
    "qualification" : "executionDateTime"
  } ],
  "eventIdentifier" : [ {
    "assignedIdentifier" : [ {
      "identifier" : {
        "value" : "DIVGLAX123234"
      }
    } ],
    "meta" : {
      "globalKey" : "3bc004ae"
    }
  }, {
    "assignedIdentifier" : [ {
      "identifier" : {
        "value" : "BI015932134564"
      }
    } ],
    "meta" : {
      "globalKey" : "23694a6c"
    }
  } ],
  "party" : [ {
    "partyId" : [ {
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
      "globalKey" : "60fe4ea7",
      "externalKey" : "PartyA"
    }
  }, {
    "partyId" : [ {
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
      "globalKey" : "60fe4ec6",
      "externalKey" : "PartyB"
    }
  } ],
  "meta" : {
    "globalKey" : "d53d2f4"
  }
},
    acceptedWorkflowStep: {},
},
{
  id:3,
  impactedTradeName: "equity-swap-price-return-single-name-ex01-amend-input.json",
  headline: "Johnson & Johnson Increases Quarterly Dividend to $1.24 as Earnings Show Strong Growth",
  content: `New Brunswick, NJ – February 3, 2025 – Johnson & Johnson (NYSE: JNJ) announced today that its Board of Directors has approved a quarterly cash dividend of $1.24 per share, marking a 5% increase from the previous payout of $1.18. The dividend is set to be paid on March 12, 2025, to shareholders of record as of February 26, with an ex-dividend date of February 25.

The dividend hike extends Johnson & Johnson’s 62-year streak of consecutive annual increases, reinforcing the company’s status as a Dividend Aristocrat and a staple in income-focused portfolios. With a forward dividend yield of 2.85%, the healthcare giant continues to prioritize shareholder returns while maintaining robust investments in research and innovation.

The announcement follows a strong fourth-quarter earnings report, where J&J posted a 6.8% year-over-year revenue increase, driven by solid growth in its pharmaceutical and MedTech divisions. The company has seen particular momentum in its oncology and immunology segments, with blockbuster drugs such as Stelara and Carvykti exceeding expectations. Meanwhile, its medical device unit benefited from increasing demand for robotic-assisted surgery and advanced cardiovascular solutions.

Following the announcement, shares of JNJ were up 1.3% in pre-market trading, reflecting investor confidence in the company’s financial outlook. Analysts have noted that J&J’s post-Kenvue restructuring has allowed the company to focus on high-margin businesses, setting the stage for continued expansion in biopharmaceuticals and cutting-edge medical technology.

With a history of resilience and consistent returns, Johnson & Johnson remains a key player in the dividend growth space, appealing to both income and growth-oriented investors.

For more details on the company’s financial performance, visit Johnson & Johnson Investor Relations.

`,
  impactedOpenTrade: {
      "businessEvent" : {
        "intent" : "ContractFormation",
        "eventDate" : "2020-12-16",
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
                  "globalReference" : "60fe4ea7",
                  "externalReference" : "PartyB"
                }, {
                  "globalReference" : "60fe4ec6",
                  "externalReference" : "PartyA"
                } ],
                "meta" : {
                  "globalKey" : "8241872a"
                }
              } ]
            }
          },
          "before" : {
            "value" : {
              "trade" : {
                "tradeIdentifier" : [ {
                  "issuer" : {
                    "value" : "DUMMY0000000000LEI02",
                    "meta" : {
                      "scheme" : "http://www.fpml.org/coding-scheme/external/issuer-identifier"
                    }
                  },
                  "assignedIdentifier" : [ {
                    "identifier" : {
                      "value" : "EQUITYSWAPPRICERET01",
                      "meta" : {
                        "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
                      }
                    }
                  } ],
                  "meta" : {
                    "globalKey" : "a2ba17f1"
                  },
                  "identifierType" : "UniqueTransactionIdentifier"
                } ],
                "tradeDate" : {
                  "value" : "2020-12-10",
                  "meta" : {
                    "globalKey" : "3f230a"
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
                            "value" : "SEBPXX",
                            "meta" : {
                              "scheme" : "http://www.fpml.org/coding-scheme/external/product-classification/iso10962"
                            }
                          }
                        }
                      }, {
                        "source" : "ISDA",
                        "productQualifier" : "EquitySwap_PriceReturnBasicPerformance_Basket"
                      } ],
                      "productIdentifier" : [ {
                        "value" : {
                          "identifier" : {
                            "value" : "Equity:Swap:PriceReturnBasicPerformance:SingleName",
                            "meta" : {
                              "scheme" : "http://www.fpml.org/coding-scheme/product-taxonomy"
                            }
                          },
                          "source" : "Other",
                          "meta" : {
                            "globalKey" : "353be9ec"
                          }
                        }
                      } ],
                      "economicTerms" : {
                        "effectiveDate" : {
                          "adjustableDate" : {
                            "unadjustedDate" : "2020-12-14",
                            "meta" : {
                              "globalKey" : "3f230e"
                            }
                          },
                          "meta" : {
                            "globalKey" : "3f230e"
                          }
                        },
                        "payout" : {
                          "interestRatePayout" : [ {
                            "payerReceiver" : {
                              "payer" : "Party1"
                            },
                            "priceQuantity" : {
                              "quantitySchedule" : {
                                "address" : {
                                  "scope" : "DOCUMENT",
                                  "value" : "quantity-3"
                                }
                              },
                              "meta" : {
                                "globalKey" : "0"
                              }
                            },
                            "rateSpecification" : {
                              "floatingRate" : {
                                "rateOption" : {
                                  "address" : {
                                    "scope" : "DOCUMENT",
                                    "value" : "rateOption-1"
                                  }
                                },
                                "meta" : {
                                  "globalKey" : "0"
                                }
                              }
                            },
                            "dayCountFraction" : {
                              "value" : "ACT/365.FIXED",
                              "meta" : {
                                "scheme" : "http://www.fpml.org/coding-scheme/day-count-fraction"
                              }
                            },
                            "calculationPeriodDates" : {
                              "effectiveDate" : {
                                "adjustableDate" : {
                                  "unadjustedDate" : "2020-12-14",
                                  "meta" : {
                                    "globalKey" : "3f230e"
                                  }
                                },
                                "meta" : {
                                  "globalKey" : "3f230e"
                                }
                              },
                              "terminationDate" : {
                                "adjustableDate" : {
                                  "unadjustedDate" : "2022-12-19",
                                  "meta" : {
                                    "globalKey" : "3f3313"
                                  }
                                },
                                "meta" : {
                                  "globalKey" : "3f3313"
                                }
                              },
                              "meta" : {
                                "globalKey" : "b38f8d45",
                                "externalKey" : "float1"
                              }
                            },
                            "paymentDates" : {
                              "paymentDateSchedule" : {
                                "interimPaymentDates" : [ {
                                  "periodicDates" : {
                                    "periodFrequency" : {
                                      "periodMultiplier" : 3,
                                      "period" : "M",
                                      "meta" : {
                                        "globalKey" : "aa"
                                      }
                                    }
                                  },
                                  "meta" : {
                                    "globalKey" : "aa"
                                  }
                                } ]
                              },
                              "meta" : {
                                "globalKey" : "aa"
                              }
                            },
                            "meta" : {
                              "globalKey" : "5508a1c7"
                            }
                          } ],
                          "performancePayout" : [ {
                            "payerReceiver" : {
                              "payer" : "Party2"
                            },
                            "priceQuantity" : {
                              "quantitySchedule" : {
                                "address" : {
                                  "scope" : "DOCUMENT",
                                  "value" : "quantity-2"
                                }
                              },
                              "meta" : {
                                "globalKey" : "0"
                              }
                            },
                            "settlementTerms" : {
                              "settlementCurrency" : {
                                "value" : "CAD"
                              },
                              "meta" : {
                                "globalKey" : "103a6"
                              }
                            },
                            "valuationDates" : {
                              "valuationDatesInterim" : {
                                "valuationDates" : {
                                  "periodicDates" : {
                                    "periodFrequency" : {
                                      "periodMultiplier" : 1,
                                      "period" : "T",
                                      "meta" : {
                                        "globalKey" : "73"
                                      }
                                    }
                                  },
                                  "meta" : {
                                    "globalKey" : "73"
                                  }
                                },
                                "meta" : {
                                  "globalKey" : "73"
                                }
                              },
                              "valuationDatesFinal" : {
                                "valuationDate" : {
                                  "adjustableDate" : {
                                    "unadjustedDate" : "2022-12-19",
                                    "meta" : {
                                      "globalKey" : "3f3313"
                                    }
                                  },
                                  "meta" : {
                                    "globalKey" : "3f3313"
                                  }
                                },
                                "meta" : {
                                  "globalKey" : "3f3313"
                                }
                              }
                            },
                            "underlier" : {
                              "basket" : {
                                "basketConstituent" : [ {
                                  "security" : {
                                    "productIdentifier" : [ {
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
                                } ]
                              },
                              "meta" : {
                                "globalKey" : "7a5b92c5"
                              }
                            },
                            "returnTerms" : {
                              "priceReturnTerms" : {
                                "returnType" : "Price"
                              }
                            },
                            "meta" : {
                              "globalKey" : "82bed1cb"
                            }
                          } ],
                          "meta" : {
                            "globalKey" : "a75438d2"
                          }
                        },
                        "nonStandardisedTerms" : false
                      },
                      "meta" : {
                        "globalKey" : "66dd03fe"
                      }
                    },
                    "meta" : {
                      "globalKey" : "66dd03fe"
                    }
                  },
                  "tradeLot" : [ {
                    "priceQuantity" : [ {
                      "quantity" : [ {
                        "value" : {
                          "value" : 8291615.00
                        },
                        "meta" : {
                          "location" : [ {
                            "scope" : "DOCUMENT",
                            "value" : "quantity-1"
                          } ]
                        }
                      }, {
                        "value" : {
                          "value" : 8291615.00,
                          "unit" : {
                            "currency" : {
                              "value" : "CAD"
                            }
                          }
                        },
                        "meta" : {
                          "location" : [ {
                            "scope" : "DOCUMENT",
                            "value" : "quantity-2"
                          } ]
                        }
                      } ],
                      "meta" : {
                        "globalKey" : "4b92941a"
                      }
                    }, {
                      "quantity" : [ {
                        "value" : {
                          "value" : 1,
                          "unit" : {
                            "financialUnit" : "Weight"
                          }
                        },
                        "meta" : {
                          "location" : [ {
                            "scope" : "DOCUMENT",
                            "value" : "quantity-4"
                          } ]
                        }
                      } ],
                      "observable" : {
                        "productIdentifier" : [ {
                          "value" : {
                            "identifier" : {
                              "value" : "US4781601046",
                              "meta" : {
                                "scheme" : "http://www.fpml.org/coding-scheme/external/instrument-id-ISIN"
                              }
                            },
                            "source" : "ISIN",
                            "meta" : {
                              "globalKey" : "6ff6a0c6"
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
                          "globalKey" : "8edd77fa"
                        }
                      },
                      "meta" : {
                        "globalKey" : "f285ed63"
                      }
                    }, {
                      "quantity" : [ {
                        "value" : {
                          "value" : 8291615.00,
                          "unit" : {
                            "currency" : {
                              "value" : "CAD"
                            }
                          }
                        },
                        "meta" : {
                          "location" : [ {
                            "scope" : "DOCUMENT",
                            "value" : "quantity-3"
                          } ]
                        }
                      } ],
                      "observable" : {
                        "rateOption" : {
                          "value" : {
                            "floatingRateIndex" : {
                              "value" : "CAD-BA-CDOR"
                            },
                            "indexTenor" : {
                              "periodMultiplier" : 3,
                              "period" : "M",
                              "meta" : {
                                "globalKey" : "aa"
                              }
                            }
                          },
                          "meta" : {
                            "location" : [ {
                              "scope" : "DOCUMENT",
                              "value" : "rateOption-1"
                            } ]
                          }
                        },
                        "meta" : {
                          "globalKey" : "4ae10981"
                        }
                      },
                      "meta" : {
                        "globalKey" : "ce61ea9f"
                      }
                    } ]
                  } ],
                  "counterparty" : [ {
                    "role" : "Party1",
                    "partyReference" : {
                      "globalReference" : "60fe4ea7",
                      "externalReference" : "PartyB"
                    }
                  }, {
                    "role" : "Party2",
                    "partyReference" : {
                      "globalReference" : "60fe4ec6",
                      "externalReference" : "PartyA"
                    }
                  } ]
                },
                "party" : [ {
                  "partyId" : [ {
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
                    "globalKey" : "60fe4ec6",
                    "externalKey" : "PartyA"
                  }
                }, {
                  "partyId" : [ {
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
                    "globalKey" : "60fe4ea7",
                    "externalKey" : "PartyB"
                  }
                } ],
                "meta" : {
                  "globalKey" : "7e2136c4"
                }
              },
              "meta" : {
                "globalKey" : "7e2136c4"
              }
            }
          }
        } ],
        "after" : [ {
          "trade" : {
            "tradeIdentifier" : [ {
              "issuer" : {
                "value" : "DUMMY0000000000LEI02",
                "meta" : {
                  "scheme" : "http://www.fpml.org/coding-scheme/external/issuer-identifier"
                }
              },
              "assignedIdentifier" : [ {
                "identifier" : {
                  "value" : "EQUITYSWAPPRICERET01",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
                  }
                }
              } ],
              "meta" : {
                "globalKey" : "a2ba17f1"
              },
              "identifierType" : "UniqueTransactionIdentifier"
            } ],
            "tradeDate" : {
              "value" : "2020-12-10",
              "meta" : {
                "globalKey" : "3f230a"
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
                        "value" : "SEBPXX",
                        "meta" : {
                          "scheme" : "http://www.fpml.org/coding-scheme/external/product-classification/iso10962"
                        }
                      }
                    }
                  }, {
                    "source" : "ISDA",
                    "productQualifier" : "EquitySwap_PriceReturnBasicPerformance_Basket"
                  } ],
                  "productIdentifier" : [ {
                    "value" : {
                      "identifier" : {
                        "value" : "Equity:Swap:PriceReturnBasicPerformance:SingleName",
                        "meta" : {
                          "scheme" : "http://www.fpml.org/coding-scheme/product-taxonomy"
                        }
                      },
                      "source" : "Other",
                      "meta" : {
                        "globalKey" : "353be9ec"
                      }
                    }
                  } ],
                  "economicTerms" : {
                    "effectiveDate" : {
                      "adjustableDate" : {
                        "unadjustedDate" : "2020-12-14",
                        "meta" : {
                          "globalKey" : "3f230e"
                        }
                      },
                      "meta" : {
                        "globalKey" : "3f230e"
                      }
                    },
                    "payout" : {
                      "interestRatePayout" : [ {
                        "payerReceiver" : {
                          "payer" : "Party1"
                        },
                        "priceQuantity" : {
                          "quantitySchedule" : {
                            "address" : {
                              "scope" : "DOCUMENT",
                              "value" : "quantity-3"
                            }
                          },
                          "meta" : {
                            "globalKey" : "0"
                          }
                        },
                        "rateSpecification" : {
                          "floatingRate" : {
                            "rateOption" : {
                              "address" : {
                                "scope" : "DOCUMENT",
                                "value" : "rateOption-1"
                              }
                            },
                            "meta" : {
                              "globalKey" : "0"
                            }
                          }
                        },
                        "dayCountFraction" : {
                          "value" : "ACT/365.FIXED",
                          "meta" : {
                            "scheme" : "http://www.fpml.org/coding-scheme/day-count-fraction"
                          }
                        },
                        "calculationPeriodDates" : {
                          "effectiveDate" : {
                            "adjustableDate" : {
                              "unadjustedDate" : "2020-12-14",
                              "meta" : {
                                "globalKey" : "3f230e"
                              }
                            },
                            "meta" : {
                              "globalKey" : "3f230e"
                            }
                          },
                          "terminationDate" : {
                            "adjustableDate" : {
                              "unadjustedDate" : "2022-12-19",
                              "meta" : {
                                "globalKey" : "3f3313"
                              }
                            },
                            "meta" : {
                              "globalKey" : "3f3313"
                            }
                          },
                          "meta" : {
                            "globalKey" : "b38f8d45",
                            "externalKey" : "float1"
                          }
                        },
                        "paymentDates" : {
                          "paymentDateSchedule" : {
                            "interimPaymentDates" : [ {
                              "periodicDates" : {
                                "periodFrequency" : {
                                  "periodMultiplier" : 3,
                                  "period" : "M",
                                  "meta" : {
                                    "globalKey" : "aa"
                                  }
                                }
                              },
                              "meta" : {
                                "globalKey" : "aa"
                              }
                            } ]
                          },
                          "meta" : {
                            "globalKey" : "aa"
                          }
                        },
                        "meta" : {
                          "globalKey" : "5508a1c7"
                        }
                      } ],
                      "performancePayout" : [ {
                        "payerReceiver" : {
                          "payer" : "Party2"
                        },
                        "priceQuantity" : {
                          "quantitySchedule" : {
                            "address" : {
                              "scope" : "DOCUMENT",
                              "value" : "quantity-2"
                            }
                          },
                          "meta" : {
                            "globalKey" : "0"
                          }
                        },
                        "settlementTerms" : {
                          "settlementCurrency" : {
                            "value" : "CAD"
                          },
                          "meta" : {
                            "globalKey" : "103a6"
                          }
                        },
                        "valuationDates" : {
                          "valuationDatesInterim" : {
                            "valuationDates" : {
                              "periodicDates" : {
                                "periodFrequency" : {
                                  "periodMultiplier" : 1,
                                  "period" : "T",
                                  "meta" : {
                                    "globalKey" : "73"
                                  }
                                }
                              },
                              "meta" : {
                                "globalKey" : "73"
                              }
                            },
                            "meta" : {
                              "globalKey" : "73"
                            }
                          },
                          "valuationDatesFinal" : {
                            "valuationDate" : {
                              "adjustableDate" : {
                                "unadjustedDate" : "2022-12-19",
                                "meta" : {
                                  "globalKey" : "3f3313"
                                }
                              },
                              "meta" : {
                                "globalKey" : "3f3313"
                              }
                            },
                            "meta" : {
                              "globalKey" : "3f3313"
                            }
                          }
                        },
                        "underlier" : {
                          "basket" : {
                            "basketConstituent" : [ {
                              "security" : {
                                "productIdentifier" : [ {
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
                            } ]
                          },
                          "meta" : {
                            "globalKey" : "7a5b92c5"
                          }
                        },
                        "returnTerms" : {
                          "priceReturnTerms" : {
                            "returnType" : "Price"
                          }
                        },
                        "meta" : {
                          "globalKey" : "82bed1cb"
                        }
                      } ],
                      "meta" : {
                        "globalKey" : "a75438d2"
                      }
                    },
                    "nonStandardisedTerms" : false
                  },
                  "meta" : {
                    "globalKey" : "66dd03fe"
                  }
                },
                "meta" : {
                  "globalKey" : "66dd03fe"
                }
              },
              "tradeLot" : [ {
                "priceQuantity" : [ {
                  "quantity" : [ {
                    "value" : {
                      "value" : 8291615.00
                    },
                    "meta" : {
                      "location" : [ {
                        "scope" : "DOCUMENT",
                        "value" : "quantity-1"
                      } ]
                    }
                  }, {
                    "value" : {
                      "value" : 8291615.00,
                      "unit" : {
                        "currency" : {
                          "value" : "CAD"
                        }
                      }
                    },
                    "meta" : {
                      "location" : [ {
                        "scope" : "DOCUMENT",
                        "value" : "quantity-2"
                      } ]
                    }
                  } ],
                  "meta" : {
                    "globalKey" : "4b92941a"
                  }
                }, {
                  "quantity" : [ {
                    "value" : {
                      "value" : 1,
                      "unit" : {
                        "financialUnit" : "Weight"
                      }
                    },
                    "meta" : {
                      "location" : [ {
                        "scope" : "DOCUMENT",
                        "value" : "quantity-4"
                      } ]
                    }
                  } ],
                  "observable" : {
                    "productIdentifier" : [ {
                      "value" : {
                        "identifier" : {
                          "value" : "US4781601046",
                          "meta" : {
                            "scheme" : "http://www.fpml.org/coding-scheme/external/instrument-id-ISIN"
                          }
                        },
                        "source" : "ISIN",
                        "meta" : {
                          "globalKey" : "6ff6a0c6"
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
                      "globalKey" : "8edd77fa"
                    }
                  },
                  "meta" : {
                    "globalKey" : "f285ed63"
                  }
                }, {
                  "quantity" : [ {
                    "value" : {
                      "value" : 8291615.00,
                      "unit" : {
                        "currency" : {
                          "value" : "CAD"
                        }
                      }
                    },
                    "meta" : {
                      "location" : [ {
                        "scope" : "DOCUMENT",
                        "value" : "quantity-3"
                      } ]
                    }
                  } ],
                  "observable" : {
                    "rateOption" : {
                      "value" : {
                        "floatingRateIndex" : {
                          "value" : "CAD-BA-CDOR"
                        },
                        "indexTenor" : {
                          "periodMultiplier" : 3,
                          "period" : "M",
                          "meta" : {
                            "globalKey" : "aa"
                          }
                        }
                      },
                      "meta" : {
                        "location" : [ {
                          "scope" : "DOCUMENT",
                          "value" : "rateOption-1"
                        } ]
                      }
                    },
                    "meta" : {
                      "globalKey" : "4ae10981"
                    }
                  },
                  "meta" : {
                    "globalKey" : "ce61ea9f"
                  }
                } ]
              } ],
              "counterparty" : [ {
                "role" : "Party1",
                "partyReference" : {
                  "globalReference" : "60fe4ea7",
                  "externalReference" : "PartyB"
                }
              }, {
                "role" : "Party2",
                "partyReference" : {
                  "globalReference" : "60fe4ec6",
                  "externalReference" : "PartyA"
                }
              } ]
            },
            "party" : [ {
              "partyId" : [ {
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
                "globalKey" : "60fe4ec6",
                "externalKey" : "PartyA"
              }
            }, {
              "partyId" : [ {
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
                "globalKey" : "60fe4ea7",
                "externalKey" : "PartyB"
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
                  "globalReference" : "60fe4ea7",
                  "externalReference" : "PartyB"
                }, {
                  "globalReference" : "60fe4ec6",
                  "externalReference" : "PartyA"
                } ],
                "meta" : {
                  "globalKey" : "8241872a"
                }
              } ]
            },
            "meta" : {
              "globalKey" : "7e2136c4"
            }
          },
          "state" : {
            "positionState" : "Formed"
          },
          "meta" : {
            "globalKey" : "7e2136c4"
          }
        } ]
      },
      "previousWorkflowStep" : {
        "globalReference" : "503ff026"
      },
      "messageInformation" : {
        "messageId" : {
          "value" : "56b4dc698dc540c49d70869f48dcf74d",
          "meta" : {
            "scheme" : "http://www.fpml.org/coding-scheme/external/technical-record-id"
          }
        },
        "sentBy" : {
          "value" : "DUMMY0000000000LEI02"
        },
        "sentTo" : [ {
          "value" : "DTCCGTR"
        } ]
      },
      "timestamp" : [ {
        "dateTime" : "2022-01-29T06:39:48.0169097Z",
        "qualification" : "eventCreationDateTime"
      }, {
        "dateTime" : "2020-12-16T23:11:47Z",
        "qualification" : "executionDateTime"
      }, {
        "dateTime" : "2021-02-05T21:52:08Z",
        "qualification" : "confirmationDateTime"
      } ],
      "eventIdentifier" : [ {
        "assignedIdentifier" : [ {
          "identifier" : {
            "value" : "56b4dc698dc540c49d70869f48dcf74d"
          }
        } ],
        "meta" : {
          "globalKey" : "8e5bdd33"
        }
      } ],
      "action" : "New",
      "party" : [ {
        "partyId" : [ {
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
          "globalKey" : "60fe4ec6",
          "externalKey" : "PartyA"
        }
      }, {
        "partyId" : [ {
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
          "globalKey" : "60fe4ea7",
          "externalKey" : "PartyB"
        }
      } ]
    },
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
              <CorpActnEvtId>DIVGLAX123234</CorpActnEvtId>
              <OffclCorpActnEvtId>BI015932134564</OffclCorpActnEvtId>
                  <EvtTp>
                  <Cd>DVCA</Cd>
              </EvtTp>
              <MndtryVlntryEvtTp>
                  <Cd>MAND</Cd>
              </MndtryVlntryEvtTp>
              <UndrlygScty>
                  <FinInstrmId>
                      <ISIN>US30231G1022</ISIN> 
                      <OthrId>
                          <Id>XOM</Id>     
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
                      <Dt>2025-03-10</Dt> 
                  </PmtDt>
              </DtDtls>
              <RateAndAmtDtls>
                  <IntrstRate>
                      <Rate>3.87</Rate>
                  </IntrstRate>
              </RateAndAmtDtls>
          </CorpActnDtls>
      </CorpActnNtfctn>
  </Document>
  
  `,
  proposedEvent: {
  "proposedWorkflowStep": {
"proposedEvent" : {
  "intent" : "CorporateActionAdjustment",
  "corporateActionIntent" : "CashDividend",
  "instruction" : [ {
    "primitiveInstruction" : {
      "observation" : {
        "observationEvent" : {
          "corporateAction" : {
            "corporateActionType" : "CashDividend",
            "exDate" : "2025-02-25",
            "payDate" : "2025-03-12"
          }
        }
      }
    },
    "before" : {
      "value" : {
        "trade" : {
          "tradeIdentifier" : [ {
            "issuer" : {
              "value" : "DUMMY0000000000LEI02",
              "meta" : {
                "scheme" : "http://www.fpml.org/coding-scheme/external/issuer-identifier"
              }
            },
            "assignedIdentifier" : [ {
              "identifier" : {
                "value" : "EQUITYSWAPPRICERET01",
                "meta" : {
                  "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
                }
              }
            } ],
            "meta" : {
              "globalKey" : "a2ba17f1"
            },
            "identifierType" : "UniqueTransactionIdentifier"
          } ],
          "tradeDate" : {
            "value" : "2020-12-10",
            "meta" : {
              "globalKey" : "3f230a"
            }
          },
          "party" : [ {
            "partyId" : [ {
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
              "globalKey" : "60fe4ec6",
              "externalKey" : "PartyA"
            }
          }, {
            "partyId" : [ {
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
              "globalKey" : "60fe4ea7",
              "externalKey" : "PartyB"
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
                "globalReference" : "60fe4ea7",
                "externalReference" : "PartyB"
              }, {
                "globalReference" : "60fe4ec6",
                "externalReference" : "PartyA"
              } ],
              "meta" : {
                "globalKey" : "60f5e6d5"
              }
            } ],
            "meta" : {
              "globalKey" : "60f5e6d5"
            }
          },
          "meta" : {
            "globalKey" : "6e828573"
          }
        },
        "state" : {
          "positionState" : "Formed"
        },
        "meta" : {
          "globalKey" : "53e9e116"
        }
      }
    }
  } ]
},
"timestamp" : [ {
  "dateTime" : "2022-01-29T06:39:48.0169097Z",
  "qualification" : "eventCreationDateTime"
}, {
  "dateTime" : "2020-12-16T23:11:47Z",
  "qualification" : "executionDateTime"
}, {
  "dateTime" : "2021-02-05T21:52:08Z",
  "qualification" : "confirmationDateTime"
} ],
"eventIdentifier" : [ {
  "assignedIdentifier" : [ {
    "identifier" : {
      "value" : "DIVGLAX17566"
    }
  } ],
  "meta" : {
    "globalKey" : "8e52d222"
  }
}, {
  "assignedIdentifier" : [ {
    "identifier" : {
      "value" : "BI0159327655"
    }
  } ],
  "meta" : {
    "globalKey" : "42a51cca"
  }
} ],
"party" : [ {
  "partyId" : [ {
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
    "globalKey" : "60fe4ec6",
    "externalKey" : "PartyA"
  }
}, {
  "partyId" : [ {
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
    "globalKey" : "60fe4ea7",
    "externalKey" : "PartyB"
  }
} ],
"meta" : {
  "globalKey" : "33a91631"
}
}},
  acceptedWorkflowStep: {}
},
{
  id:4,
  impactedTradeName: "equity-option-price-return-stock-ex03-european-call-averaging-input.json",
  headline: "Microsoft Announces $0.75 Quarterly Dividend as Cloud Revenue Surges",
  content: `Redmond, WA – February 3, 2025 – Microsoft Corporation (NASDAQ: MSFT) has declared a quarterly dividend of $0.75 per share, reinforcing its commitment to returning capital to shareholders amid record-breaking revenue from its cloud and AI segments.

The dividend will be payable on March 20, 2025, to shareholders of record as of March 6, 2025, with an ex-dividend date of March 5, 2025.

CEO Statement

Satya Nadella, CEO of Microsoft, stated:

"With continued strong demand for our AI-powered cloud services and enterprise solutions, we are pleased to reward our shareholders with another dividend increase. Microsoft's commitment to innovation and responsible growth ensures long-term value creation."

Market Performance & Future Outlook

Microsoft’s stock has surged 23% over the past 12 months, fueled by growing adoption of Azure AI services and Copilot integrations across its product suite. The company’s dividend yield now stands at 0.85%, maintaining its position as a key dividend player in the tech sector.

Analysts expect Microsoft’s aggressive investments in AI infrastructure and cybersecurity to drive further revenue growth in 2025, with its cloud division continuing to outperform expectations.

For more details on Microsoft’s earnings and investor updates, visit Microsoft Investor Relations.`,
  impactedOpenTrade: {
  "businessEvent" : {
    "intent" : "ContractFormation",
    "eventDate" : "2021-12-06",
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
              "globalReference" : "63bc007e",
              "externalReference" : "party2"
            }, {
              "globalReference" : "ab6fd453",
              "externalReference" : "party1"
            } ],
            "meta" : {
              "globalKey" : "55a4012a"
            }
          } ]
        }
      },
      "before" : {
        "value" : {
          "trade" : {
            "tradeIdentifier" : [ {
              "issuer" : {
                "value" : "213800WWTABZ1GOJHH37",
                "meta" : {
                  "scheme" : "http://www.fpml.org/coding-scheme/external/issuer-identifier"
                }
              },
              "assignedIdentifier" : [ {
                "identifier" : {
                  "value" : "EQUITYOPTIONPRICER02",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
                  }
                }
              } ],
              "meta" : {
                "globalKey" : "ddfffad1"
              },
              "identifierType" : "UniqueTransactionIdentifier"
            }, {
              "issuerReference" : {
                "globalReference" : "ab6fd453",
                "externalReference" : "party1"
              },
              "assignedIdentifier" : [ {
                "identifier" : {
                  "value" : "EQUITYOPTIONPRICER01",
                  "meta" : {
                    "scheme" : "http://www.dtcc.com/internal_Referenceid"
                  }
                }
              } ],
              "meta" : {
                "globalKey" : "4cef0f99"
              }
            } ],
            "tradeDate" : {
              "value" : "2021-12-06",
              "meta" : {
                "globalKey" : "3f2b06"
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
                    "source" : "EMIR",
                    "value" : {
                      "name" : {
                        "value" : "OT",
                        "meta" : {
                          "scheme" : "http://www.dtcc.com/coding-scheme/external/product-classification/emir-contract-type"
                        }
                      }
                    }
                  }, {
                    "source" : "ISDA",
                    "productQualifier" : "EquityOption_PriceReturnBasicPerformance_SingleName"
                  } ],
                  "productIdentifier" : [ {
                    "value" : {
                      "identifier" : {
                        "value" : "Equity:Option:PriceReturnBasicPerformance:SingleName",
                        "meta" : {
                          "scheme" : "http://www.fpml.org/coding-scheme/product-taxonomy"
                        }
                      },
                      "source" : "Other",
                      "meta" : {
                        "globalKey" : "b460058e"
                      }
                    }
                  }, {
                    "value" : {
                      "identifier" : {
                        "value" : "US5949181045",
                        "meta" : {
                          "scheme" : "http://www.dtcc.com/coding-scheme/external/underlying-id/ISIN"
                        }
                      },
                      "source" : "ISIN",
                      "meta" : {
                        "globalKey" : "6acd261e"
                      }
                    }
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
                          "settlementType" : "Cash",
                          "settlementCurrency" : {
                            "value" : "USD"
                          },
                          "meta" : {
                            "globalKey" : "7610bbd9"
                          }
                        },
                        "buyerSeller" : {
                          "buyer" : "Party1",
                          "seller" : "Party2"
                        },
                        "optionType" : "Call",
                        "exerciseTerms" : {
                          "optionStyle" : {
                            "europeanExercise" : {
                              "expirationDate" : [ {
                                "adjustableDate" : {
                                  "unadjustedDate" : "2019-05-17",
                                  "meta" : {
                                    "globalKey" : "3f1951"
                                  }
                                },
                                "meta" : {
                                  "globalKey" : "3f1951"
                                }
                              } ],
                              "meta" : {
                                "globalKey" : "3f1951"
                              }
                            }
                          },
                          "strike" : {
                            "strikePrice" : {
                              "value" : 70,
                              "unit" : {
                                "currency" : {
                                  "value" : "USD"
                                }
                              },
                              "perUnitOf" : {
                                "financialUnit" : "Share"
                              },
                              "priceType" : "AssetPrice"
                            }
                          }
                        },
                        "underlier" : {
                          "security" : {
                            "productIdentifier" : [ {
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
                          "globalKey" : "853f069f"
                        }
                      } ],
                      "meta" : {
                        "globalKey" : "853f069f"
                      }
                    },
                    "nonStandardisedTerms" : false
                  },
                  "meta" : {
                    "globalKey" : "8d3b570f"
                  }
                },
                "meta" : {
                  "globalKey" : "8d3b570f"
                }
              },
              "tradeLot" : [ {
                "priceQuantity" : [ {
                  "quantity" : [ {
                    "value" : {
                      "value" : 0,
                      "unit" : {
                        "financialUnit" : "Share"
                      }
                    },
                    "meta" : {
                      "location" : [ {
                        "scope" : "DOCUMENT",
                        "value" : "quantity-2"
                      } ]
                    }
                  }, {
                    "value" : {
                      "value" : 86308.02,
                      "unit" : {
                        "currency" : {
                          "value" : "USD"
                        }
                      }
                    },
                    "meta" : {
                      "location" : [ {
                        "scope" : "DOCUMENT",
                        "value" : "quantity-3"
                      } ]
                    }
                  }, {
                    "value" : {
                      "value" : 0,
                      "unit" : {
                        "financialUnit" : "Contract"
                      },
                      "multiplier" : {
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
                          "value" : "US5949181045",
                          "meta" : {
                            "scheme" : "http://www.fpml.org/spec/2002/instrument-id-ISIN"
                          }
                        },
                        "source" : "ISIN",
                        "meta" : {
                          "globalKey" : "6acd261e"
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
                      "globalKey" : "eed79da2"
                    }
                  },
                  "meta" : {
                    "globalKey" : "158e7941"
                  }
                } ]
              } ],
              "counterparty" : [ {
                "role" : "Party1",
                "partyReference" : {
                  "globalReference" : "63bc007e",
                  "externalReference" : "party2"
                }
              }, {
                "role" : "Party2",
                "partyReference" : {
                  "globalReference" : "ab6fd453",
                  "externalReference" : "party1"
                }
              } ]
            },
            "party" : [ {
              "partyId" : [ {
                "identifier" : {
                  "value" : "213800IV9PCAC364HN60",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
                  }
                },
                "identifierType" : "LEI",
                "meta" : {
                  "globalKey" : "365954bc"
                }
              } ],
              "name" : {
                "value" : "Broadridge MessageAutomation"
              },
              "businessUnit" : [ {
                "contactInformation" : {
                  "address" : [ {
                    "country" : {
                      "value" : "GB"
                    }
                  } ]
                },
                "meta" : {
                  "globalKey" : "8db"
                }
              } ],
              "contactInformation" : {
                "address" : [ {
                  "country" : {
                    "value" : "GB",
                    "meta" : {
                      "scheme" : "http://www.fpml.org/coding-scheme/external/iso3166"
                    }
                  }
                } ]
              },
              "meta" : {
                "globalKey" : "ab6fd453",
                "externalKey" : "party1"
              }
            }, {
              "partyId" : [ {
                "identifier" : {
                  "value" : "213800WWTABZ1GOJHH37",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
                  }
                },
                "identifierType" : "LEI",
                "meta" : {
                  "globalKey" : "fdb29bf2"
                }
              } ],
              "name" : {
                "value" : "Broadridge Financial Solutions Limited"
              },
              "businessUnit" : [ {
                "contactInformation" : {
                  "address" : [ {
                    "country" : {
                      "value" : "GB"
                    }
                  } ]
                },
                "meta" : {
                  "globalKey" : "8db"
                }
              } ],
              "contactInformation" : {
                "address" : [ {
                  "country" : {
                    "value" : "GB",
                    "meta" : {
                      "scheme" : "http://www.fpml.org/coding-scheme/external/iso3166"
                    }
                  }
                } ]
              },
              "meta" : {
                "globalKey" : "63bc007e",
                "externalKey" : "party2"
              }
            } ],
            "meta" : {
              "globalKey" : "98d7cef5"
            }
          },
          "transferHistory" : [ {
            "transfer" : {
              "quantity" : {
                "value" : 86308.02,
                "unit" : {
                  "currency" : {
                    "value" : "USD"
                  }
                }
              },
              "payerReceiver" : {
                "payerPartyReference" : {
                  "globalReference" : "63bc007e",
                  "externalReference" : "party2"
                },
                "receiverPartyReference" : {
                  "globalReference" : "ab6fd453",
                  "externalReference" : "party1"
                }
              },
              "settlementDate" : {
                "unadjustedDate" : "2019-05-01"
              },
              "transferExpression" : {
                "priceTransfer" : "Premium"
              }
            },
            "meta" : {
              "globalKey" : "c767c17c"
            }
          } ],
          "meta" : {
            "globalKey" : "b5750671"
          }
        }
      }
    } ],
    "after" : [ {
      "trade" : {
        "tradeIdentifier" : [ {
          "issuer" : {
            "value" : "213800WWTABZ1GOJHH37",
            "meta" : {
              "scheme" : "http://www.fpml.org/coding-scheme/external/issuer-identifier"
            }
          },
          "assignedIdentifier" : [ {
            "identifier" : {
              "value" : "EQUITYOPTIONPRICER02",
              "meta" : {
                "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
              }
            }
          } ],
          "meta" : {
            "globalKey" : "ddfffad1"
          },
          "identifierType" : "UniqueTransactionIdentifier"
        }, {
          "issuerReference" : {
            "globalReference" : "ab6fd453",
            "externalReference" : "party1"
          },
          "assignedIdentifier" : [ {
            "identifier" : {
              "value" : "EQUITYOPTIONPRICER01",
              "meta" : {
                "scheme" : "http://www.dtcc.com/internal_Referenceid"
              }
            }
          } ],
          "meta" : {
            "globalKey" : "4cef0f99"
          }
        } ],
        "tradeDate" : {
          "value" : "2021-12-06",
          "meta" : {
            "globalKey" : "3f2b06"
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
                "source" : "EMIR",
                "value" : {
                  "name" : {
                    "value" : "OT",
                    "meta" : {
                      "scheme" : "http://www.dtcc.com/coding-scheme/external/product-classification/emir-contract-type"
                    }
                  }
                }
              }, {
                "source" : "ISDA",
                "productQualifier" : "EquityOption_PriceReturnBasicPerformance_SingleName"
              } ],
              "productIdentifier" : [ {
                "value" : {
                  "identifier" : {
                    "value" : "Equity:Option:PriceReturnBasicPerformance:SingleName",
                    "meta" : {
                      "scheme" : "http://www.fpml.org/coding-scheme/product-taxonomy"
                    }
                  },
                  "source" : "Other",
                  "meta" : {
                    "globalKey" : "b460058e"
                  }
                }
              }, {
                "value" : {
                  "identifier" : {
                    "value" : "US5949181045",
                    "meta" : {
                      "scheme" : "http://www.dtcc.com/coding-scheme/external/underlying-id/ISIN"
                    }
                  },
                  "source" : "ISIN",
                  "meta" : {
                    "globalKey" : "6acd261e"
                  }
                }
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
                      "settlementType" : "Cash",
                      "settlementCurrency" : {
                        "value" : "USD"
                      },
                      "meta" : {
                        "globalKey" : "7610bbd9"
                      }
                    },
                    "buyerSeller" : {
                      "buyer" : "Party1",
                      "seller" : "Party2"
                    },
                    "optionType" : "Call",
                    "exerciseTerms" : {
                      "optionStyle" : {
                        "europeanExercise" : {
                          "expirationDate" : [ {
                            "adjustableDate" : {
                              "unadjustedDate" : "2019-05-17",
                              "meta" : {
                                "globalKey" : "3f1951"
                              }
                            },
                            "meta" : {
                              "globalKey" : "3f1951"
                            }
                          } ],
                          "meta" : {
                            "globalKey" : "3f1951"
                          }
                        }
                      },
                      "strike" : {
                        "strikePrice" : {
                          "value" : 70,
                          "unit" : {
                            "currency" : {
                              "value" : "USD"
                            }
                          },
                          "perUnitOf" : {
                            "financialUnit" : "Share"
                          },
                          "priceType" : "AssetPrice"
                        }
                      }
                    },
                    "underlier" : {
                      "security" : {
                        "productIdentifier" : [ {
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
                      "globalKey" : "853f069f"
                    }
                  } ],
                  "meta" : {
                    "globalKey" : "853f069f"
                  }
                },
                "nonStandardisedTerms" : false
              },
              "meta" : {
                "globalKey" : "8d3b570f"
              }
            },
            "meta" : {
              "globalKey" : "8d3b570f"
            }
          },
          "tradeLot" : [ {
            "priceQuantity" : [ {
              "quantity" : [ {
                "value" : {
                  "value" : 0,
                  "unit" : {
                    "financialUnit" : "Share"
                  }
                },
                "meta" : {
                  "location" : [ {
                    "scope" : "DOCUMENT",
                    "value" : "quantity-2"
                  } ]
                }
              }, {
                "value" : {
                  "value" : 86308.02,
                  "unit" : {
                    "currency" : {
                      "value" : "USD"
                    }
                  }
                },
                "meta" : {
                  "location" : [ {
                    "scope" : "DOCUMENT",
                    "value" : "quantity-3"
                  } ]
                }
              }, {
                "value" : {
                  "value" : 0,
                  "unit" : {
                    "financialUnit" : "Contract"
                  },
                  "multiplier" : {
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
                      "value" : "US5949181045",
                      "meta" : {
                        "scheme" : "http://www.fpml.org/spec/2002/instrument-id-ISIN"
                      }
                    },
                    "source" : "ISIN",
                    "meta" : {
                      "globalKey" : "6acd261e"
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
                  "globalKey" : "eed79da2"
                }
              },
              "meta" : {
                "globalKey" : "158e7941"
              }
            } ]
          } ],
          "counterparty" : [ {
            "role" : "Party1",
            "partyReference" : {
              "globalReference" : "63bc007e",
              "externalReference" : "party2"
            }
          }, {
            "role" : "Party2",
            "partyReference" : {
              "globalReference" : "ab6fd453",
              "externalReference" : "party1"
            }
          } ]
        },
        "party" : [ {
          "partyId" : [ {
            "identifier" : {
              "value" : "213800IV9PCAC364HN60",
              "meta" : {
                "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
              }
            },
            "identifierType" : "LEI",
            "meta" : {
              "globalKey" : "365954bc"
            }
          } ],
          "name" : {
            "value" : "Broadridge MessageAutomation"
          },
          "businessUnit" : [ {
            "contactInformation" : {
              "address" : [ {
                "country" : {
                  "value" : "GB"
                }
              } ]
            },
            "meta" : {
              "globalKey" : "8db"
            }
          } ],
          "contactInformation" : {
            "address" : [ {
              "country" : {
                "value" : "GB",
                "meta" : {
                  "scheme" : "http://www.fpml.org/coding-scheme/external/iso3166"
                }
              }
            } ]
          },
          "meta" : {
            "globalKey" : "ab6fd453",
            "externalKey" : "party1"
          }
        }, {
          "partyId" : [ {
            "identifier" : {
              "value" : "213800WWTABZ1GOJHH37",
              "meta" : {
                "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
              }
            },
            "identifierType" : "LEI",
            "meta" : {
              "globalKey" : "fdb29bf2"
            }
          } ],
          "name" : {
            "value" : "Broadridge Financial Solutions Limited"
          },
          "businessUnit" : [ {
            "contactInformation" : {
              "address" : [ {
                "country" : {
                  "value" : "GB"
                }
              } ]
            },
            "meta" : {
              "globalKey" : "8db"
            }
          } ],
          "contactInformation" : {
            "address" : [ {
              "country" : {
                "value" : "GB",
                "meta" : {
                  "scheme" : "http://www.fpml.org/coding-scheme/external/iso3166"
                }
              }
            } ]
          },
          "meta" : {
            "globalKey" : "63bc007e",
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
              "globalReference" : "63bc007e",
              "externalReference" : "party2"
            }, {
              "globalReference" : "ab6fd453",
              "externalReference" : "party1"
            } ],
            "meta" : {
              "globalKey" : "55a4012a"
            }
          } ]
        },
        "meta" : {
          "globalKey" : "98d7cef5"
        }
      },
      "state" : {
        "positionState" : "Formed"
      },
      "transferHistory" : [ {
        "transfer" : {
          "quantity" : {
            "value" : 86308.02,
            "unit" : {
              "currency" : {
                "value" : "USD"
              }
            }
          },
          "payerReceiver" : {
            "payerPartyReference" : {
              "globalReference" : "63bc007e",
              "externalReference" : "party2"
            },
            "receiverPartyReference" : {
              "globalReference" : "ab6fd453",
              "externalReference" : "party1"
            }
          },
          "settlementDate" : {
            "unadjustedDate" : "2019-05-01"
          },
          "transferExpression" : {
            "priceTransfer" : "Premium"
          }
        },
        "meta" : {
          "globalKey" : "c767c17c"
        }
      } ],
      "meta" : {
        "globalKey" : "b5750671"
      }
    } ]
  },
  "previousWorkflowStep" : {
    "globalReference" : "db597ad2"
  },
  "messageInformation" : {
    "messageId" : {
      "value" : "056282018555123459991234569BR777123456789",
      "meta" : {
        "scheme" : "http://www.fpml.org/coding-scheme/external/technical-record-id"
      }
    },
    "sentBy" : {
      "value" : "213800IV9PCAC364HN60"
    },
    "sentTo" : [ {
      "value" : "DTCCUS3"
    } ]
  },
  "timestamp" : [ {
    "dateTime" : "2021-12-06T17:09:12Z",
    "qualification" : "eventCreationDateTime"
  }, {
    "dateTime" : "2021-12-06T17:09:12Z",
    "qualification" : "executionDateTime"
  } ],
  "eventIdentifier" : [ {
    "assignedIdentifier" : [ {
      "identifier" : {
        "value" : "056282018555123459991234569BR777123456789"
      }
    } ],
    "meta" : {
      "globalKey" : "d639a945"
    }
  }, {
    "issuerReference" : {
      "globalReference" : "ab6fd453",
      "externalReference" : "party1"
    },
    "assignedIdentifier" : [ {
      "identifier" : {
        "value" : "BDM-635937-635937"
      }
    } ],
    "meta" : {
      "globalKey" : "9d4312e0"
    }
  } ],
  "action" : "New",
  "party" : [ {
    "partyId" : [ {
      "identifier" : {
        "value" : "213800IV9PCAC364HN60",
        "meta" : {
          "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
        }
      },
      "identifierType" : "LEI",
      "meta" : {
        "globalKey" : "365954bc"
      }
    } ],
    "name" : {
      "value" : "Broadridge MessageAutomation"
    },
    "businessUnit" : [ {
      "contactInformation" : {
        "address" : [ {
          "country" : {
            "value" : "GB"
          }
        } ]
      },
      "meta" : {
        "globalKey" : "8db"
      }
    } ],
    "contactInformation" : {
      "address" : [ {
        "country" : {
          "value" : "GB",
          "meta" : {
            "scheme" : "http://www.fpml.org/coding-scheme/external/iso3166"
          }
        }
      } ]
    },
    "meta" : {
      "globalKey" : "ab6fd453",
      "externalKey" : "party1"
    }
  }, {
    "partyId" : [ {
      "identifier" : {
        "value" : "213800WWTABZ1GOJHH37",
        "meta" : {
          "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
        }
      },
      "identifierType" : "LEI",
      "meta" : {
        "globalKey" : "fdb29bf2"
      }
    } ],
    "name" : {
      "value" : "Broadridge Financial Solutions Limited"
    },
    "businessUnit" : [ {
      "contactInformation" : {
        "address" : [ {
          "country" : {
            "value" : "GB"
          }
        } ]
      },
      "meta" : {
        "globalKey" : "8db"
      }
    } ],
    "contactInformation" : {
      "address" : [ {
        "country" : {
          "value" : "GB",
          "meta" : {
            "scheme" : "http://www.fpml.org/coding-scheme/external/iso3166"
          }
        }
      } ]
    },
    "meta" : {
      "globalKey" : "63bc007e",
      "externalKey" : "party2"
    }
  } ]
},
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
    <CorpActnEvtId>DIVGLAX123234</CorpActnEvtId>
    <OffclCorpActnEvtId>BI015932134564</OffclCorpActnEvtId>
      <EvtTp>
      <Cd>DVCA</Cd>
    </EvtTp>
    <MndtryVlntryEvtTp>
      <Cd>MAND</Cd>
    </MndtryVlntryEvtTp>
    <UndrlygScty>
      <FinInstrmId>
        <ISIN>US30231G1022</ISIN> 
        <OthrId>
          <Id>XOM</Id>     
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
        <Dt>2025-03-10</Dt> 
      </PmtDt>
    </DtDtls>
    <RateAndAmtDtls>
      <IntrstRate>
        <Rate>3.87</Rate>
      </IntrstRate>
    </RateAndAmtDtls>
  </CorpActnDtls>
</CorpActnNtfctn>
</Document>

`,
  proposedEvent: {
      "proposedWorkflowStep":{
    "proposedEvent" : {
      "intent" : "CorporateActionAdjustment",
      "corporateActionIntent" : "CashDividend",
      "instruction" : [ {
        "primitiveInstruction" : {
          "observation" : {
            "observationEvent" : {
              "corporateAction" : {
                "corporateActionType" : "CashDividend",
                "exDate" : "2025-03-05",
                "payDate" : "2025-03-20"
              }
            }
          }
        },
        "before" : {
          "value" : {
            "trade" : {
              "tradeIdentifier" : [ {
                "issuer" : {
                  "value" : "213800WWTABZ1GOJHH37",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/external/issuer-identifier"
                  }
                },
                "assignedIdentifier" : [ {
                  "identifier" : {
                    "value" : "EQUITYOPTIONPRICER02",
                    "meta" : {
                      "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
                    }
                  }
                } ],
                "meta" : {
                  "globalKey" : "ddfffad1"
                },
                "identifierType" : "UniqueTransactionIdentifier"
              }, {
                "issuerReference" : {
                  "globalReference" : "ab6fd453",
                  "externalReference" : "party1"
                },
                "assignedIdentifier" : [ {
                  "identifier" : {
                    "value" : "EQUITYOPTIONPRICER01",
                    "meta" : {
                      "scheme" : "http://www.dtcc.com/internal_Referenceid"
                    }
                  }
                } ],
                "meta" : {
                  "globalKey" : "58e6826c"
                }
              } ],
              "tradeDate" : {
                "value" : "2021-12-06",
                "meta" : {
                  "globalKey" : "3f2b06"
                }
              },
              "party" : [ {
                "partyId" : [ {
                  "identifier" : {
                    "value" : "213800IV9PCAC364HN60",
                    "meta" : {
                      "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
                    }
                  },
                  "identifierType" : "LEI",
                  "meta" : {
                    "globalKey" : "365954bc"
                  }
                } ],
                "name" : {
                  "value" : "Broadridge MessageAutomation"
                },
                "businessUnit" : [ {
                  "contactInformation" : {
                    "address" : [ {
                      "country" : {
                        "value" : "GB"
                      }
                    } ]
                  },
                  "meta" : {
                    "globalKey" : "8db"
                  }
                } ],
                "contactInformation" : {
                  "address" : [ {
                    "country" : {
                      "value" : "GB",
                      "meta" : {
                        "scheme" : "http://www.fpml.org/coding-scheme/external/iso3166"
                      }
                    }
                  } ]
                },
                "meta" : {
                  "globalKey" : "ab6fd453",
                  "externalKey" : "party1"
                }
              }, {
                "partyId" : [ {
                  "identifier" : {
                    "value" : "213800WWTABZ1GOJHH37",
                    "meta" : {
                      "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
                    }
                  },
                  "identifierType" : "LEI",
                  "meta" : {
                    "globalKey" : "fdb29bf2"
                  }
                } ],
                "name" : {
                  "value" : "Broadridge Financial Solutions Limited"
                },
                "businessUnit" : [ {
                  "contactInformation" : {
                    "address" : [ {
                      "country" : {
                        "value" : "GB"
                      }
                    } ]
                  },
                  "meta" : {
                    "globalKey" : "8db"
                  }
                } ],
                "contactInformation" : {
                  "address" : [ {
                    "country" : {
                      "value" : "GB",
                      "meta" : {
                        "scheme" : "http://www.fpml.org/coding-scheme/external/iso3166"
                      }
                    }
                  } ]
                },
                "meta" : {
                  "globalKey" : "63bc007e",
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
                    "globalReference" : "63bc007e",
                    "externalReference" : "party2"
                  }, {
                    "globalReference" : "ab6fd453",
                    "externalReference" : "party1"
                  } ],
                  "meta" : {
                    "globalKey" : "e0400035"
                  }
                } ],
                "meta" : {
                  "globalKey" : "e0400035"
                }
              },
              "meta" : {
                "globalKey" : "707a33b"
              }
            },
            "state" : {
              "positionState" : "Formed"
            },
            "transferHistory" : [ {
              "transfer" : {
                "quantity" : {
                  "value" : 86308.02,
                  "unit" : {
                    "currency" : {
                      "value" : "USD"
                    }
                  }
                },
                "settlementDate" : {
                  "unadjustedDate" : "2019-05-01"
                },
                "payerReceiver" : {
                  "payerPartyReference" : {
                    "globalReference" : "63bc007e",
                    "externalReference" : "party2"
                  },
                  "receiverPartyReference" : {
                    "globalReference" : "ab6fd453",
                    "externalReference" : "party1"
                  }
                },
                "transferExpression" : {
                  "priceTransfer" : "Premium"
                }
              },
              "meta" : {
                "globalKey" : "95a312e9"
              }
            } ],
            "meta" : {
              "globalKey" : "8e0d4bc7"
            }
          }
        }
      } ]
    },
    "timestamp" : [ {
      "dateTime" : "2021-12-06T17:09:12Z",
      "qualification" : "eventCreationDateTime"
    }, {
      "dateTime" : "2021-12-06T17:09:12Z",
      "qualification" : "executionDateTime"
    } ],
    "eventIdentifier" : [ {
      "assignedIdentifier" : [ {
        "identifier" : {
          "value" : "DIVGLAX4598"
        }
      } ],
      "meta" : {
        "globalKey" : "fc5696ad"
      }
    }, {
      "assignedIdentifier" : [ {
        "identifier" : {
          "value" : "BI0239587"
        }
      } ],
      "meta" : {
        "globalKey" : "1c413ae5"
      }
    } ],
    "party" : [ {
      "partyId" : [ {
        "identifier" : {
          "value" : "213800IV9PCAC364HN60",
          "meta" : {
            "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
          }
        },
        "identifierType" : "LEI",
        "meta" : {
          "globalKey" : "365954bc"
        }
      } ],
      "name" : {
        "value" : "Broadridge MessageAutomation"
      },
      "businessUnit" : [ {
        "contactInformation" : {
          "address" : [ {
            "country" : {
              "value" : "GB"
            }
          } ]
        },
        "meta" : {
          "globalKey" : "8db"
        }
      } ],
      "contactInformation" : {
        "address" : [ {
          "country" : {
            "value" : "GB",
            "meta" : {
              "scheme" : "http://www.fpml.org/coding-scheme/external/iso3166"
            }
          }
        } ]
      },
      "meta" : {
        "globalKey" : "ab6fd453",
        "externalKey" : "party1"
      }
    }, {
      "partyId" : [ {
        "identifier" : {
          "value" : "213800WWTABZ1GOJHH37",
          "meta" : {
            "scheme" : "http://www.fpml.org/coding-scheme/external/iso17442"
          }
        },
        "identifierType" : "LEI",
        "meta" : {
          "globalKey" : "fdb29bf2"
        }
      } ],
      "name" : {
        "value" : "Broadridge Financial Solutions Limited"
      },
      "businessUnit" : [ {
        "contactInformation" : {
          "address" : [ {
            "country" : {
              "value" : "GB"
            }
          } ]
        },
        "meta" : {
          "globalKey" : "8db"
        }
      } ],
      "contactInformation" : {
        "address" : [ {
          "country" : {
            "value" : "GB",
            "meta" : {
              "scheme" : "http://www.fpml.org/coding-scheme/external/iso3166"
            }
          }
        } ]
      },
      "meta" : {
        "globalKey" : "63bc007e",
        "externalKey" : "party2"
      }
    } ],
    "meta" : {
      "globalKey" : "5de48703"
    }
  }},
  acceptedWorkflowStep: {},
},
{
    id:5,
    impactedTradeName: "equity-option-price-return-basket-ex01-new-input.json",
    headline: "JPMorgan Chase Boosts Dividend Amid Strong Earnings, Signals Confidence in Market Outlook",
    content: `New York, NY – February 3, 2025 – JPMorgan Chase & Co. (NYSE: JPM) has announced a quarterly dividend increase to $1.10 per share, up from the previous $1.05, reflecting continued confidence in the bank’s financial strength and long-term strategy.

The dividend is payable on April 1, 2025, to shareholders of record as of March 15, 2025, with an ex-dividend date of March 14, 2025. This marks the third consecutive annual increase in the bank’s dividend payout.

Why It Matters

JPMorgan’s latest dividend hike comes on the heels of record-breaking Q4 earnings, fueled by:

Robust consumer spending

A strong investment banking rebound

Solid growth in commercial lending

The firm’s net income surged 12% year-over-year, driven by higher interest rates and increased trading revenues.

CEO Statement

Chairman & CEO Jamie Dimon commented:

"Our diversified business model continues to perform exceptionally well, even amid economic uncertainty. We remain committed to delivering sustainable returns to our shareholders while investing in the future of our business."

Key Takeaways for Investors

New Dividend: $1.10 per share (4.8% increase)

Forward Dividend Yield: 2.95%

Stock Performance: Up 18% over the last 12 months

Earnings Outlook: Analysts project continued growth in deposit base and credit card revenues

With JPMorgan’s stock hovering near all-time highs, analysts believe the bank’s strong capital position and strategic investments in AI-powered financial services will further drive profitability in 2025 and beyond.

For full earnings details and investor insights, visit JPMorgan Chase Investor Relations.`,
    impactedOpenTrade: {
      "businessEvent" : {
        "intent" : "ContractFormation",
        "eventDate" : "2020-09-17",
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
                  "globalReference" : "60fe4ea7",
                  "externalReference" : "PartyA"
                }, {
                  "globalReference" : "60fe4ec6",
                  "externalReference" : "PartyB"
                } ],
                "meta" : {
                  "globalKey" : "8241870c"
                }
              } ]
            }
          },
          "before" : {
            "value" : {
              "trade" : {
                "tradeIdentifier" : [ {
                  "issuer" : {
                    "value" : "DUMMY0000000000LEI01",
                    "meta" : {
                      "scheme" : "http://www.fpml.org/coding-scheme/external/issuer-identifier"
                    }
                  },
                  "assignedIdentifier" : [ {
                    "identifier" : {
                      "value" : "EQUITYOPTIONPRICER01",
                      "meta" : {
                        "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
                      }
                    }
                  } ],
                  "meta" : {
                    "globalKey" : "4114fdd"
                  },
                  "identifierType" : "UniqueTransactionIdentifier"
                } ],
                "tradeDate" : {
                  "value" : "2020-09-17",
                  "meta" : {
                    "globalKey" : "3f2251"
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
                            "value" : "HEBPXX",
                            "meta" : {
                              "scheme" : "http://www.fpml.org/coding-scheme/external/product-classification/iso10962"
                            }
                          }
                        }
                      }, {
                        "source" : "ISDA",
                        "productQualifier" : "EquityOption_PriceReturnBasicPerformance_Basket"
                      } ],
                      "productIdentifier" : [ {
                        "value" : {
                          "identifier" : {
                            "value" : "Equity:Option:PriceReturnBasicPerformance:Basket",
                            "meta" : {
                              "scheme" : "http://www.fpml.org/coding-scheme/product-taxonomy"
                            }
                          },
                          "source" : "Other",
                          "meta" : {
                            "globalKey" : "710f59b"
                          }
                        }
                      } ],
                      "economicTerms" : {
                        "payout" : {
                          "optionPayout" : [ {
                            "payerReceiver" : {
                              "payer" : "Party2",
                              "receiver" : "Party1"
                            },
                            "settlementTerms" : {
                              "settlementCurrency" : {
                                "value" : "EUR"
                              },
                              "meta" : {
                                "globalKey" : "10da2"
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
                                      "unadjustedDate" : "2020-09-17",
                                      "meta" : {
                                        "globalKey" : "3f2251"
                                      }
                                    },
                                    "meta" : {
                                      "globalKey" : "3f2251"
                                    }
                                  },
                                  "expirationDate" : {
                                    "adjustableDate" : {
                                      "unadjustedDate" : "2020-10-16",
                                      "meta" : {
                                        "globalKey" : "3f2290"
                                      }
                                    },
                                    "meta" : {
                                      "globalKey" : "3f2290"
                                    }
                                  },
                                  "meta" : {
                                    "globalKey" : "b339929f"
                                  }
                                }
                              },
                              "strike" : {
                                "strikePrice" : {
                                  "value" : 128.00000000,
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
                              }
                            },
                            "underlier" : {
                              "basket" : {
                                "basketConstituent" : [ {
                                  "security" : {
                                    "productIdentifier" : [ {
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
                                }, {
                                  "security" : {
                                    "productIdentifier" : [ {
                                      "address" : {
                                        "scope" : "DOCUMENT",
                                        "value" : "productIdentifier-2"
                                      }
                                    } ],
                                    "securityType" : "Equity"
                                  },
                                  "meta" : {
                                    "globalKey" : "7a5b92c5"
                                  }
                                } ]
                              },
                              "meta" : {
                                "globalKey" : "cc1d884a"
                              }
                            },
                            "observationTerms" : {
                              "pricingTime" : {
                                "hourMinuteTime" : "16:00:00",
                                "businessCenter" : {
                                  "value" : "EUTA"
                                }
                              }
                            },
                            "meta" : {
                              "globalKey" : "256b7cc5"
                            }
                          } ],
                          "meta" : {
                            "globalKey" : "256b7cc5"
                          }
                        },
                        "nonStandardisedTerms" : false
                      },
                      "meta" : {
                        "globalKey" : "25584ff"
                      }
                    },
                    "meta" : {
                      "globalKey" : "25584ff"
                    }
                  },
                  "tradeLot" : [ {
                    "priceQuantity" : [ {
                      "quantity" : [ {
                        "value" : {
                          "value" : 10252800.00,
                          "unit" : {
                            "currency" : {
                              "value" : "EUR"
                            }
                          }
                        },
                        "meta" : {
                          "location" : [ {
                            "scope" : "DOCUMENT",
                            "value" : "quantity-2"
                          } ]
                        }
                      } ],
                      "meta" : {
                        "globalKey" : "fd6aa506"
                      }
                    } ]
                  }, {
                    "priceQuantity" : [ {
                      "quantity" : [ {
                        "value" : {
                          "value" : 0.3333333333,
                          "unit" : {
                            "financialUnit" : "Weight"
                          }
                        },
                        "meta" : {
                          "location" : [ {
                            "scope" : "DOCUMENT",
                            "value" : "quantity-3"
                          } ]
                        }
                      } ],
                      "observable" : {
                        "productIdentifier" : [ {
                          "value" : {
                            "identifier" : {
                              "value" : "US46625H1005",
                              "meta" : {
                                "scheme" : "http://www.fpml.org/coding-scheme/external/instrument-id-ISIN"
                              }
                            },
                            "source" : "ISIN",
                            "meta" : {
                              "globalKey" : "e88f4f83"
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
                          "globalKey" : "295aa0dd"
                        }
                      },
                      "meta" : {
                        "globalKey" : "6ee4d233"
                      }
                    }, {
                      "quantity" : [ {
                        "value" : {
                          "value" : 0.6666666667,
                          "unit" : {
                            "financialUnit" : "Weight"
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
                              "value" : "US1912161007",
                              "meta" : {
                                "scheme" : "http://www.fpml.org/coding-scheme/external/instrument-id-ISIN"
                              }
                            },
                            "source" : "ISIN",
                            "meta" : {
                              "globalKey" : "72c11c93"
                            }
                          },
                          "meta" : {
                            "location" : [ {
                              "scope" : "DOCUMENT",
                              "value" : "productIdentifier-2"
                            } ]
                          }
                        } ],
                        "meta" : {
                          "globalKey" : "e56275cd"
                        }
                      },
                      "meta" : {
                        "globalKey" : "189693c4"
                      }
                    } ]
                  } ],
                  "counterparty" : [ {
                    "role" : "Party1",
                    "partyReference" : {
                      "globalReference" : "60fe4ea7",
                      "externalReference" : "PartyA"
                    }
                  }, {
                    "role" : "Party2",
                    "partyReference" : {
                      "globalReference" : "60fe4ec6",
                      "externalReference" : "PartyB"
                    }
                  } ]
                },
                "party" : [ {
                  "partyId" : [ {
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
                    "globalKey" : "60fe4ea7",
                    "externalKey" : "PartyA"
                  }
                }, {
                  "partyId" : [ {
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
                    "globalKey" : "60fe4ec6",
                    "externalKey" : "PartyB"
                  }
                } ],
                "meta" : {
                  "globalKey" : "e5cfb348"
                }
              },
              "transferHistory" : [ {
                "transfer" : {
                  "quantity" : {
                    "value" : 48011.94000,
                    "unit" : {
                      "currency" : {
                        "value" : "EUR"
                      }
                    }
                  },
                  "payerReceiver" : {
                    "payerPartyReference" : {
                      "globalReference" : "60fe4ea7",
                      "externalReference" : "PartyA"
                    }
                  },
                  "settlementDate" : {
                    "unadjustedDate" : "2020-09-21"
                  },
                  "transferExpression" : {
                    "priceTransfer" : "Premium"
                  }
                },
                "meta" : {
                  "globalKey" : "c21dee2"
                }
              } ],
              "meta" : {
                "globalKey" : "ccb2342a"
              }
            }
          }
        } ],
        "after" : [ {
          "trade" : {
            "tradeIdentifier" : [ {
              "issuer" : {
                "value" : "DUMMY0000000000LEI01",
                "meta" : {
                  "scheme" : "http://www.fpml.org/coding-scheme/external/issuer-identifier"
                }
              },
              "assignedIdentifier" : [ {
                "identifier" : {
                  "value" : "EQUITYOPTIONPRICER01",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
                  }
                }
              } ],
              "meta" : {
                "globalKey" : "4114fdd"
              },
              "identifierType" : "UniqueTransactionIdentifier"
            } ],
            "tradeDate" : {
              "value" : "2020-09-17",
              "meta" : {
                "globalKey" : "3f2251"
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
                        "value" : "HEBPXX",
                        "meta" : {
                          "scheme" : "http://www.fpml.org/coding-scheme/external/product-classification/iso10962"
                        }
                      }
                    }
                  }, {
                    "source" : "ISDA",
                    "productQualifier" : "EquityOption_PriceReturnBasicPerformance_Basket"
                  } ],
                  "productIdentifier" : [ {
                    "value" : {
                      "identifier" : {
                        "value" : "Equity:Option:PriceReturnBasicPerformance:Basket",
                        "meta" : {
                          "scheme" : "http://www.fpml.org/coding-scheme/product-taxonomy"
                        }
                      },
                      "source" : "Other",
                      "meta" : {
                        "globalKey" : "710f59b"
                      }
                    }
                  } ],
                  "economicTerms" : {
                    "payout" : {
                      "optionPayout" : [ {
                        "payerReceiver" : {
                          "payer" : "Party2",
                          "receiver" : "Party1"
                        },
                        "settlementTerms" : {
                          "settlementCurrency" : {
                            "value" : "EUR"
                          },
                          "meta" : {
                            "globalKey" : "10da2"
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
                                  "unadjustedDate" : "2020-09-17",
                                  "meta" : {
                                    "globalKey" : "3f2251"
                                  }
                                },
                                "meta" : {
                                  "globalKey" : "3f2251"
                                }
                              },
                              "expirationDate" : {
                                "adjustableDate" : {
                                  "unadjustedDate" : "2020-10-16",
                                  "meta" : {
                                    "globalKey" : "3f2290"
                                  }
                                },
                                "meta" : {
                                  "globalKey" : "3f2290"
                                }
                              },
                              "meta" : {
                                "globalKey" : "b339929f"
                              }
                            }
                          },
                          "strike" : {
                            "strikePrice" : {
                              "value" : 128.00000000,
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
                          }
                        },
                        "underlier" : {
                          "basket" : {
                            "basketConstituent" : [ {
                              "security" : {
                                "productIdentifier" : [ {
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
                            }, {
                              "security" : {
                                "productIdentifier" : [ {
                                  "address" : {
                                    "scope" : "DOCUMENT",
                                    "value" : "productIdentifier-2"
                                  }
                                } ],
                                "securityType" : "Equity"
                              },
                              "meta" : {
                                "globalKey" : "7a5b92c5"
                              }
                            } ]
                          },
                          "meta" : {
                            "globalKey" : "cc1d884a"
                          }
                        },
                        "observationTerms" : {
                          "pricingTime" : {
                            "hourMinuteTime" : "16:00:00",
                            "businessCenter" : {
                              "value" : "EUTA"
                            }
                          }
                        },
                        "meta" : {
                          "globalKey" : "256b7cc5"
                        }
                      } ],
                      "meta" : {
                        "globalKey" : "256b7cc5"
                      }
                    },
                    "nonStandardisedTerms" : false
                  },
                  "meta" : {
                    "globalKey" : "25584ff"
                  }
                },
                "meta" : {
                  "globalKey" : "25584ff"
                }
              },
              "tradeLot" : [ {
                "priceQuantity" : [ {
                  "quantity" : [ {
                    "value" : {
                      "value" : 10252800.00,
                      "unit" : {
                        "currency" : {
                          "value" : "EUR"
                        }
                      }
                    },
                    "meta" : {
                      "location" : [ {
                        "scope" : "DOCUMENT",
                        "value" : "quantity-2"
                      } ]
                    }
                  } ],
                  "meta" : {
                    "globalKey" : "fd6aa506"
                  }
                } ]
              }, {
                "priceQuantity" : [ {
                  "quantity" : [ {
                    "value" : {
                      "value" : 0.3333333333,
                      "unit" : {
                        "financialUnit" : "Weight"
                      }
                    },
                    "meta" : {
                      "location" : [ {
                        "scope" : "DOCUMENT",
                        "value" : "quantity-3"
                      } ]
                    }
                  } ],
                  "observable" : {
                    "productIdentifier" : [ {
                      "value" : {
                        "identifier" : {
                          "value" : "US46625H1005",
                          "meta" : {
                            "scheme" : "http://www.fpml.org/coding-scheme/external/instrument-id-ISIN"
                          }
                        },
                        "source" : "ISIN",
                        "meta" : {
                          "globalKey" : "e88f4f83"
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
                      "globalKey" : "295aa0dd"
                    }
                  },
                  "meta" : {
                    "globalKey" : "6ee4d233"
                  }
                }, {
                  "quantity" : [ {
                    "value" : {
                      "value" : 0.6666666667,
                      "unit" : {
                        "financialUnit" : "Weight"
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
                          "value" : "US1912161007",
                          "meta" : {
                            "scheme" : "http://www.fpml.org/coding-scheme/external/instrument-id-ISIN"
                          }
                        },
                        "source" : "ISIN",
                        "meta" : {
                          "globalKey" : "72c11c93"
                        }
                      },
                      "meta" : {
                        "location" : [ {
                          "scope" : "DOCUMENT",
                          "value" : "productIdentifier-2"
                        } ]
                      }
                    } ],
                    "meta" : {
                      "globalKey" : "e56275cd"
                    }
                  },
                  "meta" : {
                    "globalKey" : "189693c4"
                  }
                } ]
              } ],
              "counterparty" : [ {
                "role" : "Party1",
                "partyReference" : {
                  "globalReference" : "60fe4ea7",
                  "externalReference" : "PartyA"
                }
              }, {
                "role" : "Party2",
                "partyReference" : {
                  "globalReference" : "60fe4ec6",
                  "externalReference" : "PartyB"
                }
              } ]
            },
            "party" : [ {
              "partyId" : [ {
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
                "globalKey" : "60fe4ea7",
                "externalKey" : "PartyA"
              }
            }, {
              "partyId" : [ {
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
                "globalKey" : "60fe4ec6",
                "externalKey" : "PartyB"
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
                  "globalReference" : "60fe4ea7",
                  "externalReference" : "PartyA"
                }, {
                  "globalReference" : "60fe4ec6",
                  "externalReference" : "PartyB"
                } ],
                "meta" : {
                  "globalKey" : "8241870c"
                }
              } ]
            },
            "meta" : {
              "globalKey" : "e5cfb348"
            }
          },
          "state" : {
            "positionState" : "Formed"
          },
          "transferHistory" : [ {
            "transfer" : {
              "quantity" : {
                "value" : 48011.94000,
                "unit" : {
                  "currency" : {
                    "value" : "EUR"
                  }
                }
              },
              "payerReceiver" : {
                "payerPartyReference" : {
                  "globalReference" : "60fe4ea7",
                  "externalReference" : "PartyA"
                }
              },
              "settlementDate" : {
                "unadjustedDate" : "2020-09-21"
              },
              "transferExpression" : {
                "priceTransfer" : "Premium"
              }
            },
            "meta" : {
              "globalKey" : "c21dee2"
            }
          } ],
          "meta" : {
            "globalKey" : "ccb2342a"
          }
        } ]
      },
      "previousWorkflowStep" : {
        "globalReference" : "d09ceb3c"
      },
      "messageInformation" : {
        "messageId" : {
          "value" : "6fc2c8a831dd480e9c1df82d02cb396a",
          "meta" : {
            "scheme" : "http://www.fpml.org/coding-scheme/external/technical-record-id"
          }
        },
        "sentBy" : {
          "value" : "DUMMY0000000000LEI01"
        },
        "sentTo" : [ {
          "value" : "DTCCGTR"
        } ]
      },
      "timestamp" : [ {
        "dateTime" : "2020-10-05T15:20:38.7635176Z",
        "qualification" : "eventCreationDateTime"
      }, {
        "dateTime" : "2020-09-17T16:25:19Z",
        "qualification" : "executionDateTime"
      }, {
        "dateTime" : "2020-09-17T16:25:19Z",
        "qualification" : "executionDateTime"
      } ],
      "eventIdentifier" : [ {
        "assignedIdentifier" : [ {
          "identifier" : {
            "value" : "6fc2c8a831dd480e9c1df82d02cb396a"
          }
        } ],
        "meta" : {
          "globalKey" : "658d7ef5"
        }
      } ],
      "action" : "New",
      "party" : [ {
        "partyId" : [ {
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
          "globalKey" : "60fe4ea7",
          "externalKey" : "PartyA"
        }
      }, {
        "partyId" : [ {
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
          "globalKey" : "60fe4ec6",
          "externalKey" : "PartyB"
        }
      } ]
    },
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
			<CorpActnEvtId>DIVGLAX459844</CorpActnEvtId>
			<OffclCorpActnEvtId>BI023958345</OffclCorpActnEvtId>
				<EvtTp>
				<Cd>DVCA</Cd>
			</EvtTp>
			<MndtryVlntryEvtTp>
				<Cd>MAND</Cd>
			</MndtryVlntryEvtTp>
			<UndrlygScty>
				<FinInstrmId>
					<ISIN>US46625H1005</ISIN> 
					<OthrId>
						<Id>JPM</Id>     
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
					<Dt>2025-03-14</Dt>   
				</ExDvddDt>
				<PmtDt>
					<Dt>2025-04-01</Dt> 
				</PmtDt>
			</DtDtls>
			<RateAndAmtDtls>
				<IntrstRate>
					<Rate>2.95</Rate>
				</IntrstRate>
			</RateAndAmtDtls>
		</CorpActnDtls>
	</CorpActnNtfctn>
</Document>

`,
    proposedEvent: {
      "proposedWorkflowStep":{
    "proposedEvent" : {
      "intent" : "CorporateActionAdjustment",
      "corporateActionIntent" : "CashDividend",
      "instruction" : [ {
        "primitiveInstruction" : {
          "observation" : {
            "observationEvent" : {
              "corporateAction" : {
                "corporateActionType" : "CashDividend",
                "exDate" : "2025-03-14",
                "payDate" : "2025-04-01"
              }
            }
          }
        },
        "before" : {
          "value" : {
            "trade" : {
              "tradeIdentifier" : [ {
                "issuer" : {
                  "value" : "DUMMY0000000000LEI01",
                  "meta" : {
                    "scheme" : "http://www.fpml.org/coding-scheme/external/issuer-identifier"
                  }
                },
                "assignedIdentifier" : [ {
                  "identifier" : {
                    "value" : "EQUITYOPTIONPRICER01",
                    "meta" : {
                      "scheme" : "http://www.fpml.org/coding-scheme/external/unique-transaction-identifier"
                    }
                  }
                } ],
                "meta" : {
                  "globalKey" : "4114fdd"
                },
                "identifierType" : "UniqueTransactionIdentifier"
              } ],
              "tradeDate" : {
                "value" : "2020-09-17",
                "meta" : {
                  "globalKey" : "3f2251"
                }
              },
              "party" : [ {
                "partyId" : [ {
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
                  "globalKey" : "60fe4ea7",
                  "externalKey" : "PartyA"
                }
              }, {
                "partyId" : [ {
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
                  "globalKey" : "60fe4ec6",
                  "externalKey" : "PartyB"
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
                    "globalReference" : "60fe4ea7",
                    "externalReference" : "PartyA"
                  }, {
                    "globalReference" : "60fe4ec6",
                    "externalReference" : "PartyB"
                  } ],
                  "meta" : {
                    "globalKey" : "2c0fab95"
                  }
                } ],
                "meta" : {
                  "globalKey" : "2c0fab95"
                }
              },
              "meta" : {
                "globalKey" : "e669599f"
              }
            },
            "state" : {
              "positionState" : "Formed"
            },
            "transferHistory" : [ {
              "transfer" : {
                "quantity" : {
                  "value" : 48011.94,
                  "unit" : {
                    "currency" : {
                      "value" : "EUR"
                    }
                  }
                },
                "settlementDate" : {
                  "unadjustedDate" : "2020-09-21"
                },
                "payerReceiver" : {
                  "payerPartyReference" : {
                    "globalReference" : "60fe4ea7",
                    "externalReference" : "PartyA"
                  }
                },
                "transferExpression" : {
                  "priceTransfer" : "Premium"
                }
              },
              "meta" : {
                "globalKey" : "afbf43c5"
              }
            } ],
            "meta" : {
              "globalKey" : "2fe9ee43"
            }
          }
        }
      } ]
    },
    "timestamp" : [ {
      "dateTime" : "2020-10-05T15:20:38.7635176Z",
      "qualification" : "eventCreationDateTime"
    }, {
      "dateTime" : "2020-09-17T16:25:19Z",
      "qualification" : "executionDateTime"
    }, {
      "dateTime" : "2020-09-17T16:25:19Z",
      "qualification" : "executionDateTime"
    } ],
    "eventIdentifier" : [ {
      "assignedIdentifier" : [ {
        "identifier" : {
          "value" : "DIVGLAX459844"
        }
      } ],
      "meta" : {
        "globalKey" : "410ba5ed"
      }
    }, {
      "assignedIdentifier" : [ {
        "identifier" : {
          "value" : "BI023958345"
        }
      } ],
      "meta" : {
        "globalKey" : "10de0d22"
      }
    } ],
    "party" : [ {
      "partyId" : [ {
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
        "globalKey" : "60fe4ea7",
        "externalKey" : "PartyA"
      }
    }, {
      "partyId" : [ {
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
        "globalKey" : "60fe4ec6",
        "externalKey" : "PartyB"
      }
    } ],
    "meta" : {
      "globalKey" : "ce15057d"
    }
  }},
    acceptedWorkflowStep: {},
}
]