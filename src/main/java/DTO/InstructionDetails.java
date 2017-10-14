package DTO;

import java.util.Comparator;
import java.util.Objects;

public class InstructionDetails {

	public InstructionDetails(String entity, char flag, String settlementdate,
			double amtIncoming, double amtOutgoing) {
		this.entity = entity;
		this.flag = flag;
		this.settlementdate = settlementdate;
		this.amtIncoming = amtIncoming;
		this.amtOutgoing = amtOutgoing;
	}
	
	public InstructionDetails(String entity, char flag, String settlementdate,
			double amtIncoming, double amtOutgoing, int rank) {
		super();
		this.entity = entity;
		this.flag = flag;
		this.settlementdate = settlementdate;
		this.amtIncoming = amtIncoming;
		this.amtOutgoing = amtOutgoing;
		this.rank = rank;
	}

	public InstructionDetails() {
		// TODO Auto-generated constructor stub
	}

	private String entity;
	private char flag;
	private String settlementdate;
	private double amtIncoming;
	private double amtOutgoing;
	private int rank;

	// Getter and Setter methods
	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public char getFlag() {
		return flag;
	}

	public void setFlag(char flag) {
		this.flag = flag;
	}

	public String getSettlementdate() {
		return settlementdate;
	}

	public void setSettlementdate(String settlementdate) {
		this.settlementdate = settlementdate;
	}

	public double getAmtIncoming() {
		return amtIncoming;
	}

	public void setAmtIncoming(double amtIncoming) {
		this.amtIncoming = amtIncoming;
	}

	public double getAmtOutgoing() {
		return amtOutgoing;
	}

	public void setAmtOutgoing(double amtOutgoing) {
		this.amtOutgoing = amtOutgoing;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		InstructionDetails that = (InstructionDetails) o;
		return Objects.equals(entity, that.entity)
				&& Objects.equals(flag, that.flag)
				&& Objects.equals(settlementdate, that.settlementdate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(entity, flag, settlementdate);
	}

	//To sum Incoming and Outgoing amount for details with same entity, Buy/Sell flag and settlement date
	public InstructionDetails merge(InstructionDetails other) {
		assert (this.equals(other));
		return new InstructionDetails(entity, flag, settlementdate,
				this.amtIncoming + other.amtIncoming, this.amtOutgoing
				+ other.amtOutgoing);
	}

	// Comparator to sort the data in ascending order of Settlement date and Incoming Amount
	public static Comparator<InstructionDetails> incoming = new Comparator<InstructionDetails>() {

		public int compare(InstructionDetails u1, InstructionDetails u2) {

			String date1 = u1.getSettlementdate();
			String date2 = u2.getSettlementdate();

			int dateComp = date1.compareTo(date2);

			if (dateComp != 0) {
				return dateComp;
			} else {
				double amt1 = u1.getAmtIncoming();
				double amt2 = u2.getAmtIncoming();
				return (int) (amt1 - amt2);
			}
		}
	};

	// Comparator to sort the data in ascending order of Settlement date and Outgoing Amount
	public static Comparator<InstructionDetails> outgoing = new Comparator<InstructionDetails>() {

		public int compare(InstructionDetails u1, InstructionDetails u2) {

			String date1 = u1.getSettlementdate();
			String date2 = u2.getSettlementdate();

			int dateComp = date1.compareTo(date2);

			if (dateComp != 0) {
				return dateComp;
			} else {
				double amt1 = u1.getAmtOutgoing();
				double amt2 = u2.getAmtOutgoing();
				return (int) (amt1 - amt2);
			}
		}
	};

	// Comparator to sort the data in ascending order of Settlement date and descending order of Incoming Amount to assign rank
	public static Comparator<InstructionDetails> incomingrank = new Comparator<InstructionDetails>() {

		public int compare(InstructionDetails u1, InstructionDetails u2) {

			String date1 = u1.getSettlementdate();
			String date2 = u2.getSettlementdate();

			int dateComp = date1.compareTo(date2);

			if (dateComp != 0) {
				return dateComp;
			} else {
				double amt1 = u1.getAmtIncoming();
				double amt2 = u2.getAmtIncoming();
				return (int) (amt2 - amt1);
			}
		}
	};

	// Comparator to sort the data in ascending order of Settlement date and descending order of Outgoing Amount to assign rank
	public static Comparator<InstructionDetails> outgoingrank = new Comparator<InstructionDetails>() {

		public int compare(InstructionDetails u1, InstructionDetails u2) {

			String date1 = u1.getSettlementdate();
			String date2 = u2.getSettlementdate();

			int dateComp = date1.compareTo(date2);

			if (dateComp != 0) {
				return dateComp;
			} else {
				double amt1 = u1.getAmtOutgoing();
				double amt2 = u2.getAmtOutgoing();
				return (int) (amt2 - amt1);
			}
		}
	};

	public String toString() {
		return " [Entity = " + entity + ", SettlementDate = " + settlementdate
				+ ",Incoming Amt= " + amtIncoming + ",Outgoing amt = "
				+ amtOutgoing + ",Rank = " + rank + "]";
	}

}
