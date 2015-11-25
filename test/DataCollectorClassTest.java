import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.nullValue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataCollectorClassTest
{
    FileReader mockitoReader;
    DataCollectorClass dataCollector;
    String testString_1 = "0F030A 2 001011111000110011101110 011100000100110101000000";
    String testString_2 = "0F10B4 1 011100000100110101000000 001011111000110011101110";

    @Before
    public void beforeEach()
    {
        mockitoReader = mock(FileReader.class);
        dataCollector = new DataCollectorClass(mockitoReader);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void readLine_shouldReturnCorrectString_whenGivenALine()
    {
        when(mockitoReader.readLine()).thenReturn(testString_1);
        assertThat(dataCollector.readDataFromLine(), is(testString_1));
    }

    @Test
    public void readFile_shouldKnowThatReadingIsDone_whenReceivingNull()
    {
        when(mockitoReader.readLine()).thenReturn(testString_1, testString_2, null);
        dataCollector.readDataFromFile();
        assertThat(dataCollector.readDataFromLine(), nullValue());
    }

    @Test
    public void getHashMap_shouldReturnAValidMap()
    {
        dataCollector.readDataFromFile();
        assertThat(dataCollector.getHashMap().isEmpty(), is(true));
    }

    @Test
    public void splitString_shouldSplitStringAndReturnArray()
    {
        String[] testArray = {"0F030A", "2", "001011111000110011101110", "011100000100110101000000"};
        assertThat(dataCollector.splitLine(testString_1), is(testArray));
    }

    @Test
    public void splitString_shouldThrowException_ifTooManyElements()
    {
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        String tooLongString = "0F030A 2 001011111000110011101110 011100000100110101000000 tooManyTokens";
        dataCollector.splitLine(tooLongString);
    }

    @Test
    public void writeData_ifStringIsNotCorrectLength_throwException()
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Exception: Given Data Contains Errors");
        String tooLongString = "0F030A 2 001011111000110011101110 011100000100110101000000 thisStringIsTooLong";
        dataCollector.writeData(tooLongString);
    }

    @Test
    public void writeData_shouldRunWithoutErrors_ifGivenCorrectString()
    {
        dataCollector.writeData(testString_1);
        dataCollector.writeData(testString_2);
    }

    @Test
    public void writeData_shouldThrowException_IfGivenDataContainsErrors()
    {
        thrown.expect(IllegalArgumentException.class);
        dataCollector.writeData("0F0X0A 1 001011111000110011101110 011100000100110101000000");
        dataCollector.writeData("0F030A 3 001011111000110011101110 011100000100110101000000");
        dataCollector.writeData("0F030A 2 0010111110003110011101110 011100000100110101000000");
        dataCollector.writeData("0F030A 2 001011111000110011101110 0111000004100110101000000");
    }

    @Test
    public void readDataFromFile_shouldAddDataAndKeyToHashMap()
    {
        when(mockitoReader.readLine()).thenReturn(testString_1, testString_2, null);
        dataCollector.readDataFromFile();
        assertThat(dataCollector.getHashMap().size(), is(2));
    }

    @Test
    public void readDataFromFile_shouldAddDataToDuplicatesAndErrorLog_ifDuplicateDataIsReceived()
    {
        when(mockitoReader.readLine()).thenReturn(testString_1,testString_1, null);

        dataCollector.readDataFromFile();
        assertThat(dataCollector.getDuplicates().size(), is(1));
        assertThat(dataCollector.getErrorLog().size(), is(1));
        assertThat(dataCollector.getHashMap().size(), is(1));

        assertThat(dataCollector.getDuplicates().toString(), is("[0F030A 2 001011111000110011101110 011100000100110101000000]"));
        assertThat(dataCollector.getErrorLog().toString(), is("[Error: Data Line <0F030A 2 001011111000110011101110 011100000100110101000000> added to duplicates]"));
    }

    @Test
    public void readDataFromFile_shouldAddDataToErrorLog_ifThereAreErrors()
    {
        when(mockitoReader.readLine()).thenReturn(
                "0F0X0A 1 001011111000110011101110 011100000100110101000000",
                "0F030A 3 001011111000110011101110 011100000100110101000000",
                "0F030A 2 0010111110003110011101110 011100000100110101000000",
                "0F030A 2 001011111000110011101110 0111000004100110101000000",
                null);

        dataCollector.readDataFromFile();

        assertThat(dataCollector.getErrorLog().size(), is(4));
        assertThat(dataCollector.getHashMap().size(), is(0));
        assertThat(dataCollector.getDuplicates().size(), is(0));

        assertThat(dataCollector.getErrorLog().toString(), is("[" +
                "Error: Data Line <0F0X0A 1 001011111000110011101110 011100000100110101000000> is incorrect, " +
                "Error: Data Line <0F030A 3 001011111000110011101110 011100000100110101000000> is incorrect, " +
                "Error: Data Line <0F030A 2 0010111110003110011101110 011100000100110101000000> is incorrect, " +
                "Error: Data Line <0F030A 2 001011111000110011101110 0111000004100110101000000> is incorrect" +
                "]"));
    }

    @Test
    public void readDataFromFile_shouldAddDataToErrorLog_ifThereAreTooMuchData()
    {
        when(mockitoReader.readLine()).thenReturn("0F00AB 1 001011111000110011101110 011100000100110100 11011", null);

        dataCollector.readDataFromFile();

        assertThat(dataCollector.getErrorLog().size(), is(1));
        assertThat(dataCollector.getHashMap().size(), is(0));
        assertThat(dataCollector.getDuplicates().size(), is(0));

        assertThat(dataCollector.getErrorLog().toString(), is("[Error: Data Line <0F00AB 1 001011111000110011101110 011100000100110100 11011> has too many arguments]"));
    }

    @Test
    public void DataClassShouldReturnRightElements()
    {
        when(mockitoReader.readLine()).thenReturn(testString_1, testString_2, null);

        dataCollector.readDataFromFile();
        assertThat(dataCollector.getDuplicates().size(), is(0));
        assertThat(dataCollector.getErrorLog().size(), is(0));
        assertThat(dataCollector.getHashMap().size(), is(2));

        assertThat(dataCollector.getData("0F030A").toString(), is("2 001011111000110011101110" +
                " 011100000100110101000000 Result is: 011111111100110111101110 (8375790)"));
    }
}