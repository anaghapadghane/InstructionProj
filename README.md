Instruction Settlement
Instructions are received to be traded in international market. This is a solution to generate reports for Daily Incoming Amount, Daily Outgoing Amount, Rank based on Entity and daily incoming amount and Rank based on daily outgoing amount for the trades

Input
Instruction: An instruction to buy or sell. Input instructions are received in the file named instruction.csv stored in src/main/java/resources

Model/DTO
InputInstruction - To store the input from file as is. Includes below details

Entity: A financial entity whose shares are to be bought or sold 
Buy/Sell flag:  o B – Buy – outgoing o S – Sell – incoming 
Agreed Fx is the foreign exchange rate with respect to USD that was agreed 
Currency: Currency of the country in which insrtruction is received
Instruction Date: Date on which the instruction was sent to JP Morgan by various clients
Settlement Date: The date on which the client wished for the instruction to be settled with respect 
Buy/Sell flag:  o B – Buy – outgoing o S – Sell – incoming 
Units: Number of shares to be bought or sold 
Price per unit

InstructionDetails - To store data after calculations to generate the reports. Includes below details

Entity: A financial entity whose shares are to be bought or sold
Buy/Sell flag:  o B – Buy – outgoing o S – Sell – incoming 
Settlement Date: If the received Settlement Date is within work week, Settlement Date is set as reeived settlement date. If it is not within work week, Settlement date is set as next business day. This is based on currency. For currencies SAR and AED the work week is from Sunday to Thursday and for other currencies it is from Monday to Friday. If the settlement date is not within Sunday to Thursday the settlement date would be set to next business day of Sunday. For other currencies if the settlement date is not within Monday to Friday the settlement date will be set to next business day of Monday
Incoming Amount/Outgoing Amount - Amount in USD is calcualated as Price per unit * Units * Agreed Fx. The USD Amount is stored in Incoming Amount/Outgoing Amount based on Sell/Buy flag.

Service
NextBusinessDay - Sets the next business day if the Settlement Date is not within work week

CheckWorkWeek - Checks whether Settlement Date is in work week based on currency

main/java
MainClass - Main class to check if the input file exists and not empty. Calls method to create the InputInstruction and InstrutionDetails DTO, add them to lists and generate reports
GenerateInputInstruction - Read Input file, add data to InputInstruction DTO as is, add DTO data to arraylist and return arraylist
GenerateInstructionDetails - Check whether Settlement date is within work week based on Currency. If it is not within work week, set settlement date as next business day, calculate USD amount and add amount to Incoming/Outgoing amount based on Sell/Buy flag. Flag S is for Incoming amount and flag B is for Outgoing amount. Set the InstructionDetails DTO and add to list and return list.

GenerateReports - Sorts the list on Settlement date and Incoming amount, based onSettlement date and Outgoing amount, based on ascending order of Settlement date and descending order of Incoming amount and based on ascending order of Settlement date and descending order of Outgoing amount. Adds amount based on Settlement date to generate reports for Daily Incoming and Daily Outgoing Amounts. Sets the rank based on Entity, Settlement Date and Amount to generate reports for Rank for Incoming Amounts and Rank for Outgoing Amounts

Tests - Unit test cases in Junit
InputFileTest - Test to check file exists and not empty

CheckWorkWeekTest - Test to check whether settlement date is within work week 

NextBusinessDayTest - Test to set next business day

GenerateInstructionDetailsTest - Test to check next business day for currencies SAR/AED and USD amounts

GenerateReportsTest - Test to check reports are generated correctly