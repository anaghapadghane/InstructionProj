

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.project.GenerateInstructionDetails;

import DTO.*;


public class GenerateInstructionDetailsTest {

	@Test
	public final void testNextBusinessDayForSettlementWorkDayForSARAED() {
		
		List<InputInstruction> input = new ArrayList<InputInstruction>();
	    input.add(new InputInstruction("foo", 'B', 0.50, "SAR", new Date("08-OCT-17"), new Date("08-OCT-17"), 200, 100.25));
	    input.add(new InputInstruction("bar", 'S', 0.22, "AED", new Date("12-OCT-17"), new Date("12-OCT-17"), 450, 150.5));
	   
	    List<InstructionDetails> detail = new ArrayList<InstructionDetails>();
	    
	    GenerateInstructionDetails details = new GenerateInstructionDetails();
	    details.createDetails(input, detail);
	    
	    assertEquals(new Date("08-OCT-17"), new Date(detail.get(0).getSettlementdate()));
	    assertEquals(new Date("12-OCT-17"), new Date(detail.get(1).getSettlementdate()));
		
	}
	@Test
	public final void testNextBusinessDayForSettlementFridaySaturdayForSARAED() {
		
		List<InputInstruction> input = new ArrayList<InputInstruction>();
	    input.add(new InputInstruction("foo", 'B', 0.50, "SAR", new Date("13-OCT-17"), new Date("13-OCT-17"), 200, 100.25));
	    input.add(new InputInstruction("bar", 'S', 0.22, "AED", new Date("13-OCT-17"), new Date("14-OCT-17"), 450, 150.5));
	   
	    List<InstructionDetails> detail = new ArrayList<InstructionDetails>();
	    
	    GenerateInstructionDetails details = new GenerateInstructionDetails();
	    details.createDetails(input, detail);
	    
	    assertEquals(new Date("15-OCT-17"), new Date(detail.get(0).getSettlementdate()));
	    assertEquals(new Date("15-OCT-17"), new Date(detail.get(1).getSettlementdate()));
		
	}

	@Test
	public final void testNextBusinessDayForSettlementWorkDayForOtherCurrencies() {
		
		List<InputInstruction> input = new ArrayList<InputInstruction>();
	    input.add(new InputInstruction("foo", 'B', 0.50, "USD", new Date("09-OCT-17"), new Date("09-OCT-17"), 200, 100.25));
	    input.add(new InputInstruction("bar", 'S', 0.22, "SGP", new Date("13-OCT-17"), new Date("13-OCT-17"), 450, 150.5));
	   
	    List<InstructionDetails> detail = new ArrayList<InstructionDetails>();
	    
	    GenerateInstructionDetails details = new GenerateInstructionDetails();
	    details.createDetails(input, detail);
	    
	    assertEquals(new Date("09-OCT-17"), new Date(detail.get(0).getSettlementdate()));
	    assertEquals(new Date("13-OCT-17"), new Date(detail.get(1).getSettlementdate()));
		
	}
	@Test
	public final void testNextBusinessDayForSettlementSaturdaySundayForOtherCurrencies() {
		
		List<InputInstruction> input = new ArrayList<InputInstruction>();
		input.add(new InputInstruction("foo", 'B', 0.50, "USD", new Date("07-OCT-17"), new Date("07-OCT-17"), 200, 100.25));
	    input.add(new InputInstruction("bar", 'S', 0.22, "SGP", new Date("18-OCT-17"), new Date("08-OCT-17"), 450, 150.5));
	   
	    List<InstructionDetails> detail = new ArrayList<InstructionDetails>();
	    
	    GenerateInstructionDetails details = new GenerateInstructionDetails();
	    details.createDetails(input, detail);
	    
	    assertEquals(new Date("09-OCT-17"), new Date(detail.get(0).getSettlementdate()));
	    assertEquals(new Date("09-OCT-17"), new Date(detail.get(1).getSettlementdate()));
		
	}

	@Test
	public final void testUSDIncomingAmount() {
		
		List<InputInstruction> input = new ArrayList<InputInstruction>();
	    input.add(new InputInstruction("foo", 'B', 0.50, "SAR", new Date("08-OCT-17"), new Date("08-OCT-17"), 200, 100.25));
	    input.add(new InputInstruction("bar", 'S', 0.22, "AED", new Date("12-OCT-17"), new Date("12-OCT-17"), 450, 150.5));
	   
	    List<InstructionDetails> detail = new ArrayList<InstructionDetails>();
	    
	    GenerateInstructionDetails details = new GenerateInstructionDetails();
	    details.createDetails(input, detail);
	    assertTrue((detail.get(1).getAmtIncoming()) ==  14899.5);
	}
	
	@Test
	public final void testUSDOutgoingAmount() {
		
		List<InputInstruction> input = new ArrayList<InputInstruction>();
	    input.add(new InputInstruction("foo", 'B', 0.50, "SAR", new Date("13-OCT-17"), new Date("13-OCT-17"), 200, 100.25));
	    input.add(new InputInstruction("bar", 'S', 0.22, "AED", new Date("13-OCT-17"), new Date("14-OCT-17"), 450, 150.5));
	   
	    List<InstructionDetails> detail = new ArrayList<InstructionDetails>();
	    
	    GenerateInstructionDetails details = new GenerateInstructionDetails();
	    details.createDetails(input, detail);
	    
	    System.out.println(detail.get(0).getAmtIncoming());
	    System.out.println(detail.get(0).getAmtOutgoing());

	    assertTrue((detail.get(0).getAmtOutgoing()) == 10025);		
	}
}
