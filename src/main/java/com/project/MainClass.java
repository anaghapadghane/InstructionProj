package com.project;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import DTO.InputInstruction;
import DTO.InstructionDetails;

public class MainClass {
	private static final String filePath = "src/main/resources/InputFile/instruction.csv";

	public static void main(String[] args) throws IOException, ParseException {

		boolean check;
		check = checkFileExists(filePath);
		if (check) {
			GenerateInputInstruction inputdata = new GenerateInputInstruction();

			// Create a new List to store the data from input file
			List<InputInstruction> inputList = new ArrayList<InputInstruction>();
			inputList = inputdata.parseInputData(filePath, inputList);

			// Create a new list to loop through the input data, calculate
			// business day, USD amount and store relevant data as per as per structure
			// of InstructionDetails
			List<InstructionDetails> resultList = new ArrayList<InstructionDetails>();
			GenerateInstructionDetails details = new GenerateInstructionDetails();
			resultList = details.createDetails(inputList, resultList);

			// Create a new List to store unique values based on Entity,
			// Buy/Sell flag, Settlement date and add the Incoming or Outgoing amounts

			List<InstructionDetails> listFinal = new ArrayList<InstructionDetails>();
			for (InstructionDetails p : resultList) {
				int index = listFinal.indexOf(p);
				if (index != -1) {
					listFinal.set(index, listFinal.get(index).merge(p));
				} else {
					listFinal.add(p);
				}

			}

			// Generate the reports
			GenerateReports.generateIncomingAmtReport(listFinal);
			GenerateReports.generateOutgoingAmtReport(listFinal);
			GenerateReports.generateRankIncomingAmtReport(listFinal);
			GenerateReports.generateRankOutgoingAmtReport(listFinal);
		}
	}

	public static boolean checkFileExists(String filePath) throws IOException {
		boolean check = true;
		File file = new File((filePath));

		if (!file.exists()) {
			System.out.println("Input file does not exist");
			check = false;
		} else if (file.length() == 0) {
			System.out.println("Input file is empty");
			check = false;
		} else {
			check = true;
		}
		return check;
	}
}