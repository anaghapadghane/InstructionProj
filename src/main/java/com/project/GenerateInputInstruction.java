package com.project;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import DTO.InputInstruction;

//Read Input file, add data to InputInstruction DTO as is, add DTO data to arraylist and return arraylist

public class GenerateInputInstruction {

	private static final String COMMA_DELIMITER = ",";
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");

	public List<InputInstruction> parseInputData(String file,
			List<InputInstruction> list) throws IOException, ParseException {

		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "";
		line = br.readLine();

		while ((line = br.readLine()) != null) {
			String[] input = line.split(COMMA_DELIMITER);

			if (input.length > 0) {
				InputInstruction instrcution = new InputInstruction();

				instrcution.setEntity(input[0]);
				instrcution.setFlag(input[1].charAt(0));
				instrcution.setAgreedfx(Double.parseDouble(input[2]));
				instrcution.setCurrency(input[3]);
				instrcution.setInstructiondate(formatter.parse(input[4]));
				instrcution.setSettlementdate(formatter.parse(input[5]));
				instrcution.setUnits(Integer.parseInt(input[6]));
				instrcution.setPrice(Double.parseDouble(input[7]));

				list.add(instrcution);
			}
		}
		br.close();
		return list;
	}
}
