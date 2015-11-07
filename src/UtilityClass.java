/**
 * Created by Johan on 04.11.2015.
 */

/** 1. Vi trenger en «utility» klasse som gir oss mulighet til å konvertere fra en streng representasjon av
 hex eller bit, til en int representasjon av tallet. Konverteringen skal også kunne gå motsatt vei (fra intl
 til hex eller binære strenger). Alle tall som påtreffes er positive, fortegn er ikke en problemstilling i
 oppgaven.
 */


public class UtilityClass {

    public UtilityClass()
    {
        // Constructor
    }

    public UtilityClass(String hex)
    {

    }
    private void checkLength(String check)
    {
        if(check.length() > 24)
        {
            System.out.println("Errors");
        }
    }

    /** Returns the int value of a bit string */
    public int bitToInt(String bitString)
    {
        //if(bitString.isEmpty()) return 0;

        char[] cArray = bitString.toCharArray();
        int result = 0;
        for(int i=cArray.length - 1; i>=0; i--)
            if(cArray[i]=='1')
                result += Math.pow(2, (cArray.length-i - 1));
        return result;
    }

}
