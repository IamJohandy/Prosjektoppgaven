import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Johan on 04.11.2015.
 */

/**
 * Bitstrenger:
 * En streng kan ha lengde 0 til 24
 * En tom streng skal returnere 0
 * En streng som er lengre enn 24 skal for�rsake at en IllegalArgumentException kastes
 * En streng som har andre tegn enn 0 og 1 skal for�rsake at en IllegalArgumentException kastes
 * Du skal kunne sende inn en streng med 0 og 1 (kun), heretter kalt bitstreng, og f� returnert
 * korresponderende int verdi
 * Du skal kunne sende inn en int og f� returnert den som en bitstreng med lengde 24
 *
 * Hex strenger:
 * En streng kan ha lengde 0 til 6
 * En tom streng skal returnere 0
 * En streng som er lengre enn 6 skal for�rsake at en IllegalArgumentException kastes
 * En streng som har andre tegn enn 01234567890ABCDEF / abcdef skal for�rsake at en
 * IllegalArgumentException kastes
 *
 * Bit operasjoner
 * Operasjonene bitwise OR og bitwise AND skal kunne utf�res p� to bitstrenger. Retur er en streng p�
 * 24 tegn.
 * Det er ikke tillatt � bruke wrapper klasser sin metode parseInt() eller lignende eksisterende metoder
 * for � utf�re konvertering til / fra bit / hex strenger til / fra int.
 *
 * navnP�MetodeSomTestes_InputParametre_ForventetResultat()
 *
 */
public class UtilityClassTest
{
    UtilityClass utility = new UtilityClass();

    String someRandomBit = "1010101010";

    @Test
    public void bitToInt_shouldReturnCorrectInt()
    {
        assertThat(utility.bitToInt(someRandomBit), is(682));
    }

    @Test
    public void bitToInt_shouldReturnZeroIfStringIsEmpty()
    {
        assertThat(utility.bitToInt(""), is(0));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void bitToInt_shouldTrowAnIllegalArgumentException()
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("String was too long");
        assertThat(utility.bitToInt("101010101010101010101010101"), is(1337));
    }

    @Test
    public void bitToInt_shouldThrowExceptionIfStringContainsSomethingBogus()
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("String did not contain only 1 and 0");
        assertThat(utility.bitToInt("1010rty101010"), is(1337));
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
    public void intToBit_shouldReturnIllegalArgumentExceptionIfWrongValue()
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Integer contains invalid value");
        assertThat(utility.intToBit(-1), is(""));
        assertThat(utility.intToBit(16777219), is(""));
    }

    @Test
    public void hexToInt_shouldReturnZero_IfStringIsEmpty()
    {
        assertThat(utility.hexToInt(""), is(0));
    }

    @Test
    public void hexToInt_shouldThrowExeption_IfStringIsTooLong()
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid Hex String");
        assertThat(utility.hexToInt("ABC123A"), is(24));
    }

    @Test
    public void hexToInt_shouldThrowException_IfStringCOntainsSomethingBogus()
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid Hex String");
        assertThat(utility.hexToInt("?"), is(24));
        assertThat(utility.hexToInt("W"), is(24));
    }

    @Test
    public void hexToInt_shouldReturnValidInt_IfInputIsCorrect()
    {
        assertThat(utility.hexToInt("ABCDEF"), is(11259375));
        assertThat(utility.hexToInt("00000A"), is(10));
    }

    @Test
    public void intTohex_shouldReturnValindHexString_IfInputIsCorrect()
    {
        assertThat(utility.intToHex(11259375), is("ABCDEF"));
        assertThat(utility.intToHex(10), is("00000A"));
    }

    @Test
    public void bitWiseOR_shouldReturnCorrectString_GivenInput()
    {
        assertThat(utility.bitWiseOR("010010000100100001001000", "101110001011100010111000"), is("111110001111100011111000"));
    }

    @Test
    public void bitWiseAND_shouldReturnCorrectString_GivenInput()
    {
        assertThat(utility.bitWiseAND("010010000100100001001000", "101110001011100010111000"), is("000010000000100000001000"));
    }

}