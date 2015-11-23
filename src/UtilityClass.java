/**
 * Created by Johan on 04.11.2015.
 */

/** 1. Vi trenger en �utility� klasse som gir oss mulighet til � konvertere fra en streng representasjon av
 hex eller bit, til en int representasjon av tallet. Konverteringen skal ogs� kunne g� motsatt vei (fra intl
 til hex eller bin�re strenger). Alle tall som p�treffes er positive, fortegn er ikke en problemstilling i
 oppgaven.
 */


public class UtilityClass {
    private final String ALLOWED_HEX_CHARACTERS = "0123456789ABCDEF";
    private final int HEX_LENGTH = 6;
    private final int BIT_LENGTH = 24;

    public UtilityClass()
    {
        // Constructor
    }

    private void checkBitLenth(String checkBit)
    {
        //String oneZero = "[01]+";
        if(checkBit.length() > 24)
        {
            throw new IllegalArgumentException("String was too long");
        }
    }

    private void checkBitString(String checkBit)
    {
        if(!checkBit.matches("[01]+") && checkBit.length() != 0)
        {
            throw new IllegalArgumentException("String did not contain only 1 and 0");
        }
    }

    private int convertFromBitToInt(String bitString)
    {
        char[] cArray = bitString.toCharArray();
        int result = 0;
        for(int i=cArray.length - 1; i>=0; i--)
            if(cArray[i]=='1')
                result += Math.pow(2, (cArray.length-i - 1));
        return result;
    }

    /** Returns the int value of a bit string */
    public int bitToInt(String bitString)
    {
        checkBitLenth(bitString);
        checkBitString(bitString);
        return convertFromBitToInt(bitString);
    }

    //Fjern extra kode!!!
    private String convertFromIntToBit(int value)
    {
        String bitString = "";
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i < BIT_LENGTH; i++)
        {
            if (value % 2 == 1)
            {
                bitString = '1' + bitString;
                builder.append('1');
            }
            else if (value % 2 == 0)
            {
                bitString = '0' + bitString;
                builder.append('0');
            }
            value = value / 2;
        }
        return builder.reverse().toString();
    }

    private void checkIntValue(int value)
    {
        if(value < 0 || value > 16777215)
        {
            throw new IllegalArgumentException("Integer contains invalid value");
        }
    }

    public String intToBit(int value)
    {
        checkIntValue(value);
        return convertFromIntToBit(value);
    }






    private String processHexString(String hexString)
    {
        hexString = hexString.toUpperCase();
        if(hexString.equals(""))
        {
            return "";
        }
        if(!hexString.matches("[0-9A-F]+") || hexString.length() > 6)
        {
            throw new IllegalArgumentException("Invalid Hex String");
        }
        else
        {
            return hexString;
        }
    }

    private int convertFromHexToInt(String hexString)
    {
        hexString = hexString.toUpperCase();
        int value = 0;
        for (int i = 0; i < hexString.length(); i++)
        {
            char c = hexString.charAt(i);
            int d = ALLOWED_HEX_CHARACTERS.indexOf(c);
            value = 16 * value + d;
        }
        return value;
    }

    public int hexToInt(String hexString)
    {
        String correctString = processHexString(hexString);
        return convertFromHexToInt(correctString);
    }

    private String convertFromIntToHex(int value)
    {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < HEX_LENGTH; i++)
        {
            int digit = value % 16;
            builder.append(ALLOWED_HEX_CHARACTERS.charAt(digit));
            value /= 16;
        }

        return builder.reverse().toString();
    }

    public String intToHex(int value)
    {
        checkIntValue(value);
        return convertFromIntToHex(value);
    }

    public String binaryOROperation(String first, String second)
    {
        checkBitLenth(first);
        checkBitString(first);
        checkBitLenth(second);
        checkBitString(second);
        return bitWiseOR(first, second);
    }

    public String binaryOROperation(int first, int second)
    {
        return bitWiseOR(intToBit(first), intToBit(second));
    }

    private String bitWiseOR(String first, String second)
    {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < BIT_LENGTH; i++)
        {
            if(first.charAt(i) == '1' || second.charAt(i) == '1')
            {
                builder.append('1');
            }
            else
            {
                builder.append('0');
            }
        }
        return builder.toString();
    }

    public String binaryANDOperation(String first, String second)
    {
        checkBitLenth(first);
        checkBitString(first);
        checkBitLenth(second);
        checkBitString(second);
        return bitWiseAND(first, second);
    }

    public String binaryANDOperation(int first, int second)
    {
        return bitWiseAND(intToBit(first), intToBit(second));
    }

    private String bitWiseAND(String first, String second)
    {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < BIT_LENGTH; i++)
        {
            if(first.charAt(i) == '1' && second.charAt(i) == '1')
            {
                builder.append('1');
            }
            else
            {
                builder.append('0');
            }
        }
        return builder.toString();
    }

}
