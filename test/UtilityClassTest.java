import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UtilityClassTest
{
    UtilityClass utility = new UtilityClass();

    @Test
    public void bitToInt_shouldReturnCorrectInt()
    {
        assertThat(utility.bitToInt("1010101010"), is(682));
    }

    @Test
    public void bitToInt_shouldReturnZeroIfStringIsEmpty()
    {
        assertThat(utility.bitToInt(""), is(0));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void bitToInt_shouldTrowAnIllegalArgumentException_ifGivenStringWasTooLong()
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Exception: Given String Was Too Long");
        assertThat(utility.bitToInt("101010101010101010101010101"), is(1337));
    }

    @Test
    public void bitToInt_shouldThrowException_IfStringContainsSomethingBogus()
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Exception: Given Data Contains Errors");
        utility.bitToInt("1010rty101010");
    }

    @Test
    public void intToBit_shouldReturnCorrectValue()
    {
        assertThat(utility.intToBit(682), is("000000000000001010101010"));
    }

    @Test
    public void intToBit_shouldReturnStringOfCorrectLength()
    {
        String bitString = utility.intToBit(69);
        assertThat(bitString.length(), is(24));
    }

    @Test
    public void intToBit_shouldReturnIllegalArgumentException_IfWrongValue()
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Exception: Given Data Is Out Of Bounds");
        assertThat(utility.intToBit(-1), is(""));
        assertThat(utility.intToBit(16777219), is(""));
    }

    @Test
    public void hexToInt_shouldReturnZero_IfStringIsEmpty()
    {
        assertThat(utility.hexToInt(""), is(0));
    }

    @Test
    public void hexToInt_shouldThrowException_IfStringIsTooLong()
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Exception: Given Data Contains Errors");
        assertThat(utility.hexToInt("ABC123A"), is(24));
    }

    @Test
    public void hexToInt_shouldThrowException_IfStringContainsSomethingBogus()
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Exception: Given Data Contains Errors");
        utility.hexToInt("?");
        utility.hexToInt("W");
    }

    @Test
    public void hexToInt_shouldReturnValidInt_IfInputIsCorrect()
    {
        assertThat(utility.hexToInt("ABCDEF"), is(11259375));
        assertThat(utility.hexToInt("00000A"), is(10));
    }

    @Test
    public void intToHex_shouldReturnValidHexString_IfInputIsCorrect()
    {
        assertThat(utility.intToHex(11259375), is("ABCDEF"));
        assertThat(utility.intToHex(10), is("00000A"));
    }

    @Test
    public void bitWiseOR_shouldReturnCorrectString_GivenStringInput()
    {
        assertThat(utility.binaryOROperation("010010000100100001001000", "101110001011100010111000"), is("111110001111100011111000"));
    }

    @Test
    public void bitWiseOR_shouldReturnCorrectString_GivenIntegerInput()
    {
        assertThat(utility.binaryOROperation(4737096, 12105912), is("111110001111100011111000"));
    }

    @Test
    public void bitWiseAND_shouldReturnCorrectString_GivenStringInput()
    {
        assertThat(utility.binaryANDOperation("010010000100100001001000", "101110001011100010111000"), is("000010000000100000001000"));
    }

    @Test
    public void bitWiseAND_shouldReturnCorrectString_GivenIntegerInput()
    {
        assertThat(utility.binaryANDOperation(4737096, 12105912), is("000010000000100000001000"));
    }

    @Test
    public void checkOperator_shouldNOThrowException_ifValidInput()
    {
        utility.checkOperator("1");
        utility.checkOperator("2");
    }

    @Test
    public void checkOperator_shouldThrowException_ifInvalidInput()
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Exception: Unexpected Operator");
        utility.checkOperator("0");
        utility.checkOperator("3");
        utility.checkOperator("K");
    }
}