package DTO;

import java.util.Date;

public class InputInstruction {

	private String entity;
	private char flag;
	private double agreedfx;
	private String currency;
	private Date instructiondate;
	private Date settlementdate;
	private int units;
	private double price;

	public InputInstruction(String entity, char flag, double agreedfx,
			String currency, Date instructiondate, Date settlementdate,
			int units, double price) {
		super();
		this.entity = entity;
		this.flag = flag;
		this.agreedfx = agreedfx;
		this.currency = currency;
		this.instructiondate = instructiondate;
		this.settlementdate = settlementdate;
		this.units = units;
		this.price = price;
	}

	public InputInstruction() {
		// TODO Auto-generated constructor stub
	}

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

	public double getAgreedfx() {
		return agreedfx;
	}

	public void setAgreedfx(double agreedfx) {
		this.agreedfx = agreedfx;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getInstructiondate() {
		return instructiondate;
	}

	public void setInstructiondate(Date instructiondate) {
		this.instructiondate = instructiondate;
	}

	public Date getSettlementdate() {
		return settlementdate;
	}

	public void setSettlementdate(Date settlementdate) {
		this.settlementdate = settlementdate;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return " [Entity = " + entity + ", Buy/Sell = " + flag
				+ ", AgreedFx = " + agreedfx + ", Currency = " + currency
				+ ", InstructionDate = " + instructiondate
				+ ", SettlementDate = " + settlementdate + ",Units= " + units
				+ ",Price = " + price + "]";
	}

}