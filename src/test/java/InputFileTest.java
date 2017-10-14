
import static org.junit.Assert.*;

import com.project.*;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;


public class InputFileTest {
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Test
	public void testFileDoesNotExists() throws IOException {
				
		String file = "src/InputFile/instruction1.csv";
		assertFalse(MainClass.checkFileExists(file));
	}

	@Test
	public void testFileExistsAndNotEmpty() throws IOException {
				
		String file = "src/main/resources/InputFile/instruction.csv";
		assertTrue(MainClass.checkFileExists(file));
	}
	
}
