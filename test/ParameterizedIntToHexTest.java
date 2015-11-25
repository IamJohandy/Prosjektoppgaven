import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ParameterizedIntToHexTest
{
    private static UtilityClass utillity = new UtilityClass();

    private int input;
    private String expected;

    public ParameterizedIntToHexTest(String input, String expected)
    {
        this.input = Integer.parseInt(input);
        this.expected = expected;
    }

    @Parameters
    public static List<String[]> data()
    {
        return Arrays.asList(new String[][]
        {
                {"1", "000001"},
                {"24", "000018"},
                {"99", "000063"},
                {"127", "00007F"},
                {"1994", "0007CA"},
                {"90000", "015F90"},
                {"123456", "01E240"},
                {"7654321", "74CBB1"},
                {"1554321", "17B791"},
                {"2411994", "24CDDA"}
        });
    }

    @Test
    public void testIntToHexCases()
    {
        assertThat(utillity.intToHex(input), is(expected));
    }
}