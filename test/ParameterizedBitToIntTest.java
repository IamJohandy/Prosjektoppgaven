import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class ParameterizedBitToIntTest
{
    private static UtilityClass utillity = new UtilityClass();

    private int expected;
    private String input;

    public ParameterizedBitToIntTest(String input, String expected)
    {
        this.expected = Integer.parseInt(expected);
        this.input = input;
    }

    @Parameters
    public static List<String[]> data()
    {
        return Arrays.asList(new String[][]
                {
                        {"000000000000000000000001", "1"},
                        {"000000000000000000011000", "24"},
                        {"000000000000001100001001", "777"},
                        {"000000000010001100101001", "9001"},
                        {"000000000100110111100101", "19941"},
                        {"000000011110001001000000", "123456"},
                        {"011101001100101110110001", "7654321"},
                        {"000010110100000111010011", "737747"},
                        {"001001001100110111011010", "2411994"},
                        {"111111111111111111111111", "16777215"}
                });
    }

    @Test
    public void testIntToHexCases()
    {
        assertThat(utillity.bitToInt(input), is(expected));
    }
}