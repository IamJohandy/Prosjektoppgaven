import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ParameterizedHexToIntTest
{
    private static UtilityClass utillity = new UtilityClass();

    private String input;
    private int expected;

    public ParameterizedHexToIntTest(String input, String expected)
    {
        this.expected = Integer.parseInt(expected);
        this.input = input;
    }

    @Parameters
    public static List<String[]> data()
    {
        return Arrays.asList(new String[][]
                {
                        {"000001","1"},
                        {"000018", "24"},
                        {"000063", "99"},
                        {"00007F", "127"},
                        {"0007CA", "1994"},
                        {"015F90", "90000"},
                        {"01E240", "123456"},
                        {"74CBB1", "7654321"},
                        {"17B791", "1554321"},
                        {"24CDDA", "2411994"}
                });
    }

    @Test
    public void testIntToHexCases()
    {
        assertThat(utillity.hexToInt(input), is(expected));
    }
}