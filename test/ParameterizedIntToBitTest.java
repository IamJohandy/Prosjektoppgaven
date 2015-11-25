import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ParameterizedIntToBitTest
{
    private static UtilityClass utillity = new UtilityClass();

    private int input;
    private String expected;

    public ParameterizedIntToBitTest(String input, String expected)
    {
        this.input = Integer.parseInt(input);
        this.expected = expected;
    }

    @Parameters
    public static List<String[]> data()
    {
        return Arrays.asList(new String[][]
                {
                        {"1", "000000000000000000000001"},
                        {"24", "000000000000000000011000"},
                        {"777", "000000000000001100001001"},
                        {"9001", "000000000010001100101001"},
                        {"19941", "000000000100110111100101"},
                        {"123456", "000000011110001001000000"},
                        {"7654321", "011101001100101110110001"},
                        {"737747", "000010110100000111010011"},
                        {"2411994", "001001001100110111011010"},
                        {"16777215", "111111111111111111111111"}
                });
    }

    @Test
    public void testIntToHexCases()
    {
        assertThat(utillity.intToBit(input), is(expected));
    }
}