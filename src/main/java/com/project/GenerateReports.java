package com.project;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import DTO.InstructionDetails;

//Generate reports for Daily Incoming Amt, Daily Outgoing Amt, Rank based on Entity and Incoming Amount and Rank based 
//on Entity and Outgoing amount 
public class GenerateReports {

	public static void generateIncomingAmtReport(List<InstructionDetails> list)
			throws IOException, ParseException {

		// Sort based on Settlement date and Incoming amount
		Collections.sort(list, InstructionDetails.incoming);

		System.out.println("Incoming Amt based on daily Settlement");
		System.out.println("--------------------------------------");
		System.out.printf("%1$-20s %2$-20s", "Settlement Date",
				"Incoming Amount");
		System.out.println();

		list = addAmtIncoming(list);

		for (InstructionDetails incoming : list) {
			if (incoming.getAmtIncoming() > 0.0) {

				System.out
						.printf("%1$-20s %2$10.2f",
								incoming.getSettlementdate(),
								incoming.getAmtIncoming());
				System.out.println();
			}
		}
	}

	public static void generateOutgoingAmtReport(List<InstructionDetails> list)
			throws IOException, ParseException {
		// Sort based on Settlement date and Outgoing amount
		Collections.sort(list, InstructionDetails.outgoing);

		System.out.println("");
		System.out.println("Outgoing Amt based on daily settlement");
		System.out.println("--------------------------------------");
		System.out.printf("%1$-20s %2$-20s", "Settlement Date",
				"Outgoing Amount");
		System.out.println();

		list = addAmtOutgoing(list);

		for (InstructionDetails outgoing : list) {
			if (outgoing.getAmtOutgoing() > 0.0) {
				System.out
						.printf("%1$-20s %2$10.2f",
								outgoing.getSettlementdate(),
								outgoing.getAmtOutgoing());
				System.out.println();
			}
		}
	}

	public static void generateRankIncomingAmtReport(
			List<InstructionDetails> list) throws IOException, ParseException {

		// Sort based on ascending order of Settlement date and
		// descending order of Incoming amount
		Collections.sort(list, InstructionDetails.incomingrank);

		list = setRank(list);

		System.out.println("");
		System.out.println("Ranking based on Incoming Amount");
		System.out
				.println("---------------------------------------------------------");
		System.out.printf("%1$-10s %2$-20s %3$-20s %4$-5s", "Entity",
				"Settlement Date", "Incoming Amount", "Rank");
		System.out.println();

		for (InstructionDetails incomingrank : list) {
			if (incomingrank.getAmtIncoming() > 0.0) {

				System.out.printf("%1$-10s %2$-20s %3$10.2f %4$12d",
						incomingrank.getEntity(),
						incomingrank.getSettlementdate(),
						incomingrank.getAmtIncoming(), incomingrank.getRank());
				System.out.println();
			}
		}
	}

	public static void generateRankOutgoingAmtReport(
			List<InstructionDetails> list) throws IOException, ParseException {

		// Sort based on ascending order of Settlement date and
		// descending order of Outgoing amount
		Collections.sort(list, InstructionDetails.outgoingrank);

		list = setRank(list);

		System.out.println("");
		System.out.println("Ranking based on Outgoing Amount");
		System.out
				.println("---------------------------------------------------------");
		System.out.printf("%1$-10s %2$-20s %3$-20s %4$-5s", "Entity",
				"Settlement Date", "Outgoing Amount", "Rank");
		System.out.println();

		for (InstructionDetails outgoingrank : list) {
			if (outgoingrank.getAmtOutgoing() > 0.0) {

				System.out.printf("%1$-10s %2$-20s %3$10.2f %4$12d",
						outgoingrank.getEntity(),
						outgoingrank.getSettlementdate(),
						outgoingrank.getAmtOutgoing(), outgoingrank.getRank());
				System.out.println();

			}
		}
	}

	public static List<InstructionDetails> setRank(List<InstructionDetails> list) {
		int rank = 1;
		String temp = list.get(0).getSettlementdate();
		list.get(0).setRank(1);

		// Set rank based on Entity, descending order of Outgoing amount for
		// each Settlement date and add the Rank to list
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).getSettlementdate().equals(temp)) {
				list.get(i).setRank(++rank);
			} else {
				rank = 1;
				list.get(i).setRank(1);
				temp = list.get(i).getSettlementdate();
			}

		}
		return list;
	}

	// Method to sum the Incoming amount based on Settlement date
	public static List<InstructionDetails> addAmtIncoming(
			List<InstructionDetails> list) {
		List<InstructionDetails> listfinal = new ArrayList<InstructionDetails>();
		InstructionDetails detail = new InstructionDetails();

		double tempamt = list.get(0).getAmtIncoming();
		String tempdate = list.get(0).getSettlementdate();

		for (int i = 1; i < list.size(); i++) {

			if (list.get(i).getSettlementdate()
					.equals(list.get(i - 1).getSettlementdate())) {
				tempamt = tempamt + list.get(i).getAmtIncoming();
			} else {
				InstructionDetails detail1 = new InstructionDetails();
				detail1.setSettlementdate(tempdate);
				detail1.setAmtIncoming(tempamt);

				listfinal.add(detail1);

				tempdate = list.get(i).getSettlementdate();
				tempamt = list.get(i).getAmtIncoming();
			}

		}
		detail.setSettlementdate(tempdate);
		detail.setAmtIncoming(tempamt);

		listfinal.add(detail);

		return listfinal;
	}

	// Method to sum the Outgoing amount based on Settlement date
	public static List<InstructionDetails> addAmtOutgoing(
			List<InstructionDetails> list) {
		List<InstructionDetails> listfinal = new ArrayList<InstructionDetails>();
		InstructionDetails detail = new InstructionDetails();

		double tempamt = list.get(0).getAmtOutgoing();
		String tempdate = list.get(0).getSettlementdate();

		for (int i = 1; i < list.size(); i++) {

			if (list.get(i).getSettlementdate()
					.equals(list.get(i - 1).getSettlementdate())) {
				tempamt = tempamt + list.get(i).getAmtOutgoing();
			} else {
				InstructionDetails detail1 = new InstructionDetails();
				detail1.setSettlementdate(tempdate);
				detail1.setAmtOutgoing(tempamt);

				listfinal.add(detail1);

				tempdate = list.get(i).getSettlementdate();
				tempamt = list.get(i).getAmtOutgoing();
			}

		}
		detail.setSettlementdate(tempdate);
		detail.setAmtOutgoing(tempamt);

		listfinal.add(detail);

		return listfinal;
	}
}
