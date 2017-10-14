

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import DTO.InputInstruction;
import DTO.InstructionDetails;

import com.project.GenerateInstructionDetails;
import com.project.GenerateReports;

public class GenerateReportsTest {

	@Test
	public final void testIncomingAmtZero() throws IOException,
			ParseException {

		List<InstructionDetails> detail = new ArrayList<InstructionDetails>();
		detail.add(new InstructionDetails("foo", 'S', "04-OCT-17", 0.0, 0.0));
		detail.add(new InstructionDetails("foo", 'S', "07-OCT-17", 0.0, 0.0));

		GenerateReports report = new GenerateReports();
		report.generateIncomingAmtReport(detail);

		assertTrue("04-OCT-170.0".equals(detail.get(0).getSettlementdate()
				+ detail.get(0).getAmtIncoming()));
		assertTrue("07-OCT-170.0".equals(detail.get(1).getSettlementdate()
				+ detail.get(1).getAmtIncoming()));

	}

	@Test
	public final void testIncomingAmtSingleRecordDaily() throws IOException,
			ParseException {

		List<InstructionDetails> detail = new ArrayList<InstructionDetails>();
		detail.add(new InstructionDetails("foo", 'S', "04-OCT-17", 1500.0, 0.0));
		detail.add(new InstructionDetails("foo", 'S', "07-OCT-17", 1500.0, 0.0));

		GenerateReports report = new GenerateReports();
		report.generateIncomingAmtReport(detail);

		assertTrue("04-OCT-171500.0".equals(detail.get(0).getSettlementdate()
				+ detail.get(0).getAmtIncoming()));
		assertTrue("07-OCT-171500.0".equals(detail.get(1).getSettlementdate()
				+ detail.get(1).getAmtIncoming()));

	}

	@Test
	public final void testIncomingAmtMultipleRecordDaily() throws IOException,
			ParseException {

		List<InstructionDetails> detail = new ArrayList<InstructionDetails>();
		detail.add(new InstructionDetails("foo", 'S', "04-OCT-17", 1000.0, 0.0));
		detail.add(new InstructionDetails("bar", 'S', "04-OCT-17", 25000.0, 0.0));
		detail.add(new InstructionDetails("foo", 'S', "07-OCT-17", 14899.5, 0.0));
		detail.add(new InstructionDetails("foo", 'S', "07-OCT-17", 2200, 0.0));

		GenerateReports report = new GenerateReports();
		detail = report.addAmtIncoming(detail);

		assertTrue("04-OCT-1726000.0".equals(detail.get(0).getSettlementdate()
				+ detail.get(0).getAmtIncoming()));
		assertTrue("07-OCT-1717099.5".equals(detail.get(1).getSettlementdate()
				+ detail.get(1).getAmtIncoming()));

	}
	
	@Test
	public final void testOutgoingAmtZero() throws IOException,
			ParseException {

		List<InstructionDetails> detail = new ArrayList<InstructionDetails>();
		detail.add(new InstructionDetails("foo", 'S', "04-OCT-17", 0.0, 0.0));
		detail.add(new InstructionDetails("foo", 'S', "07-OCT-17", 0.0, 0.0));

		GenerateReports report = new GenerateReports();
		report.generateIncomingAmtReport(detail);

		assertTrue("04-OCT-170.0".equals(detail.get(0).getSettlementdate()
				+ detail.get(0).getAmtIncoming()));
		assertTrue("07-OCT-170.0".equals(detail.get(1).getSettlementdate()
				+ detail.get(1).getAmtIncoming()));

	}

	@Test
	public final void testOutgoingAmtSingleRecordDaily() throws IOException,
			ParseException {

		List<InstructionDetails> detail = new ArrayList<InstructionDetails>();
		detail.add(new InstructionDetails("foo", 'B', "04-OCT-17", 0.0, 10025));
		detail.add(new InstructionDetails("foo", 'B', "07-OCT-17", 0.0, 500));

		GenerateReports report = new GenerateReports();
		report.generateOutgoingAmtReport(detail);

		assertTrue("04-OCT-1710025.0".equals(detail.get(0).getSettlementdate()
				+ detail.get(0).getAmtOutgoing()));
		assertTrue("07-OCT-17500.0".equals(detail.get(1).getSettlementdate()
				+ detail.get(1).getAmtOutgoing()));

	}

	@Test
	public final void testOutgoingAmtMultipleRecordDaily() throws IOException,
			ParseException {

		List<InstructionDetails> detail = new ArrayList<InstructionDetails>();
		detail.add(new InstructionDetails("foo", 'B', "04-OCT-17", 0.0, 1000));
		detail.add(new InstructionDetails("bar", 'B', "04-OCT-17", 0.0, 25000));
		detail.add(new InstructionDetails("foo", 'B', "07-OCT-17", 0.0, 14899.5));
		detail.add(new InstructionDetails("foo", 'B', "07-OCT-17", 0.0, 200));
		detail.add(new InstructionDetails("foo", 'B', "08-OCT-17", 0.0, 2200));

		GenerateReports report = new GenerateReports();
		detail = report.addAmtOutgoing(detail);

		assertTrue("04-OCT-1726000.0".equals(detail.get(0).getSettlementdate()
				+ detail.get(0).getAmtOutgoing()));
		assertTrue("07-OCT-1715099.5".equals(detail.get(1).getSettlementdate()
				+ detail.get(1).getAmtOutgoing()));
		assertTrue("08-OCT-172200.0".equals(detail.get(2).getSettlementdate()
				+ detail.get(2).getAmtOutgoing()));
	}

	@Test
	public final void testRankIncomingAmtSingleRecordDaily()
			throws IOException, ParseException {

		List<InstructionDetails> detail = new ArrayList<InstructionDetails>();
		detail.add(new InstructionDetails("foo", 'S', "04-OCT-17", 1000.0, 0.0));
		detail.add(new InstructionDetails("bar", 'S', "05-OCT-17", 25000.0, 0.0));
		detail.add(new InstructionDetails("foo", 'S', "06-OCT-17", 1500.0, 0.0));
		detail.add(new InstructionDetails("foo", 'S', "07-OCT-17", 100.0, 0.0));

		GenerateReports report = new GenerateReports();
		report.generateRankIncomingAmtReport(detail);

		assertTrue(1 == detail.get(0).getRank());
		assertTrue(1 == detail.get(1).getRank());
		assertTrue(1 == detail.get(2).getRank());
		assertTrue(1 == detail.get(3).getRank());

	}

	@Test
	public final void testRankIncomingAmtMultipleRecordDaily()
			throws IOException, ParseException {

		List<InstructionDetails> detail = new ArrayList<InstructionDetails>();
		detail.add(new InstructionDetails("foo", 'S', "04-OCT-17", 2000.0, 0.0));
		detail.add(new InstructionDetails("bar", 'S', "04-OCT-17", 35000.0, 0.0));
		detail.add(new InstructionDetails("foo", 'S', "07-OCT-17", 2500.0, 0.0));
		detail.add(new InstructionDetails("foo", 'S', "07-OCT-17", 200.0, 0.0));

		GenerateReports report = new GenerateReports();
		report.generateRankIncomingAmtReport(detail);

		assertTrue(1 == detail.get(0).getRank());
		assertTrue(2 == detail.get(1).getRank());
		assertTrue(1 == detail.get(2).getRank());
		assertTrue(2 == detail.get(3).getRank());

	}
	
	@Test
	public final void testRankOutgoingAmtSingleRecordDaily()
			throws IOException, ParseException {

		List<InstructionDetails> detail = new ArrayList<InstructionDetails>();
		detail.add(new InstructionDetails("foo", 'B', "04-OCT-17", 0.0, 1000.0));
		detail.add(new InstructionDetails("bar", 'B', "05-OCT-17", 0.0, 25000.0));
		detail.add(new InstructionDetails("foo", 'B', "06-OCT-17", 0.0, 1500.0));
		detail.add(new InstructionDetails("foo", 'B', "07-OCT-17", 0.0, 100.0));

		GenerateReports report = new GenerateReports();
		report.generateRankOutgoingAmtReport(detail);

		assertTrue(1 == detail.get(0).getRank());
		assertTrue(1 == detail.get(1).getRank());
		assertTrue(1 == detail.get(2).getRank());
		assertTrue(1 == detail.get(3).getRank());

	}

	@Test
	public final void testRankOutgoingAmtMultipleRecordDaily()
			throws IOException, ParseException {

		List<InstructionDetails> detail = new ArrayList<InstructionDetails>();
		detail.add(new InstructionDetails("foo", 'B', "04-OCT-17", 0.0, 3000.0));
		detail.add(new InstructionDetails("bar", 'B', "04-OCT-17", 0.0, 45000.0));
		detail.add(new InstructionDetails("foo", 'B', "07-OCT-17", 0.0, 5500.0));
		detail.add(new InstructionDetails("foo", 'B', "07-OCT-17", 0.0, 500.0));

		GenerateReports report = new GenerateReports();
		report.generateRankOutgoingAmtReport(detail);
		assertTrue(1 == detail.get(0).getRank());
		assertTrue(2 == detail.get(1).getRank());
		assertTrue(1 == detail.get(2).getRank());
		assertTrue(2 == detail.get(3).getRank());

	}
}
