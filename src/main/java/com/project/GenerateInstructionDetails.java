package com.project;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import DTO.InputInstruction;
import DTO.InstructionDetails;
import service.CheckWorkWeek;
import service.NextBusinessDay;

//Check whether Settlement date is within work week based on Currency. If it is not within work week, set settlement date 
// as next business day, calculate USD amount and add amount to Incoming/Outgoing amount based on Sell/Buy flag
public class GenerateInstructionDetails {

	public List<InstructionDetails> createDetails(
			List<InputInstruction> inputList,
			List<InstructionDetails> resultlist) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		Double amtUSD;

		for (InputInstruction items : inputList) {
			InstructionDetails instructiondetail = new InstructionDetails();
			instructiondetail.setEntity(items.getEntity());

			// Check whether Settlement date is within work week. We pass
			// currency as the work week days differ based on currency
			boolean isWorkWeek;
			isWorkWeek = CheckWorkWeek.isWorkWeek(items.getSettlementdate(),
					items.getCurrency().trim());

			// If the Settlement date is not within work week, set the
			// Settlement date to next business day.
			// We pass currency as the next business day will differ based on
			// currency
			if (!isWorkWeek) {

				Date nextBusinessDay = NextBusinessDay.setNextBusinessDay(
						items.getSettlementdate(), items.getCurrency().trim());
				instructiondetail.setSettlementdate(formatter
						.format(nextBusinessDay));
			} else {
				instructiondetail.setSettlementdate(formatter.format(items
						.getSettlementdate()));
			}

			// calculate the USD amount from AgreedFx, Units and Price per unit
			amtUSD = items.getPrice() * items.getUnits() * items.getAgreedfx();

			// Set the USD amount to Incoming Amount or Outgoing Amount based on
			// Buy/Sell Flag
			if (items.getFlag() == 'S') {
				instructiondetail.setAmtIncoming(amtUSD);
			} else {
				instructiondetail.setAmtOutgoing(amtUSD);
			}

			resultlist.add(instructiondetail);
		}
		return resultlist;
	}
}
