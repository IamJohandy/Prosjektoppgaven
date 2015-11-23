import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.nullValue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Johan on 17.11.2015.
 */
public class DataCollectorClassTest
{
    FileReader mockitoReader;
    DataCollectorClass dataCollector;
    String testString_1 = "0x030A 2 001011111000110011101110 011100000100110101000000";
    String testString_2 = "0x10B4 1 011100000100110101000000 001011111000110011101110";

    @Before
    public void beforeEach()
    {
        mockitoReader = mock(FileReader.class);
        dataCollector = new DataCollectorClass(mockitoReader);
    }

    @Test
    public void readLine_shouldReturnCorrectString_whenGivenALine()
    {
        when(mockitoReader.readLine()).thenReturn(testString_1);
        assertThat(dataCollector.readDataFromLine(), is(testString_1));
    }

    @Test
    public void readFile_shouldKnowThatReadingIsDone_whenReceivingNull()
    {
        when(mockitoReader.readLine()).thenReturn(testString_1);
        when(mockitoReader.readLine()).thenReturn(testString_2);
        when(mockitoReader.readLine()).thenReturn(null);
        dataCollector.readDataFromFile();
        assertThat(dataCollector.readDataFromLine(), nullValue());
    }

}