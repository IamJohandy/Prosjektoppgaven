import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Johan on 04.11.2015.
 */

/**
 * Bitstrenger:
 * En streng kan ha lengde 0 til 24
 * En tom streng skal returnere 0
 * En streng som er lengre enn 24 skal forårsake at en IllegalArgumentException kastes
 * En streng som har andre tegn enn 0 og 1 skal forårsake at en IllegalArgumentException kastes
 * Du skal kunne sende inn en streng med 0 og 1 (kun), heretter kalt bitstreng, og få returnert
 * korresponderende int verdi
 * Du skal kunne sende inn en int og få returnert den som en bitstreng med lengde 24
 *
 * Hex strenger:
 * En streng kan ha lengde 0 til 6
 * En tom streng skal returnere 0
 * En streng som er lengre enn 6 skal forårsake at en IllegalArgumentException kastes
 * En streng som har andre tegn enn 01234567890ABCDEF / abcdef skal forårsake at en
 * IllegalArgumentException kastes
 *
 * Bit operasjoner
 * Operasjonene bitwise OR og bitwise AND skal kunne utføres på to bitstrenger. Retur er en streng på
 * 24 tegn.
 * Det er ikke tillatt å bruke wrapper klasser sin metode parseInt() eller lignende eksisterende metoder
 * for å utføre konvertering til / fra bit / hex strenger til / fra int.
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
        assertThat(utility.bitToInt("         "), is(0));
    }
}