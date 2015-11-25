import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class DataCollectorClass
{
    private FileReader reader;
    private HashMap<String, Data> dataHashMap;
    private ArrayList<String> errorLog, duplicates;

    public DataCollectorClass(FileReader reader)
    {
        this.reader = reader;
        dataHashMap = new HashMap<>();
        errorLog = new ArrayList<>();
        duplicates = new ArrayList<>();
    }

    public void readDataFromFile()
    {
        String dataLine;
        while((dataLine = readDataFromLine()) != null)
        {
            try
            {
                writeData(dataLine);
            }
            catch (IllegalArgumentException e)
            {
                if(e.getMessage().equals("Exception: Duplicate Data ID"))
                {
                    errorLog.add("Error: Data Line <" + dataLine + "> added to duplicates");
                }
                else
                {
                    errorLog.add("Error: Data Line <" + dataLine + "> is incorrect");
                }
            }
            catch (IndexOutOfBoundsException e)
            {
                errorLog.add("Error: Data Line <" + dataLine + "> has too many arguments");
            }
        }
    }

    public String readDataFromLine()
    {
        String line = reader.readLine();
        return line;
    }

    private void checkString(String string)
    {
        if(string.length() != 58)
        {
            throw new IllegalArgumentException("Exception: Given Data Contains Errors");
        }
    }

    private void checkData(String[] dataArray)
    {
        UtilityClass utillity = new UtilityClass();

        String tempHexString = utillity.processHexString(dataArray[0]);

        utillity.checkOperator(dataArray[1]);
        utillity.checkBitString(dataArray[2]);
        utillity.checkBitLength(dataArray[2]);
        utillity.checkBitString(dataArray[3]);
        utillity.checkBitLength(dataArray[3]);
    }

    private void checkIsDuplicate(String[] dataArray)
    {
        if (dataHashMap.containsKey(dataArray[0]))
        {
            duplicates.add("" + dataArray[0] + " " + dataArray[1] + " " + dataArray[2] + " " + dataArray[3]);
            throw new IllegalArgumentException("Exception: Duplicate Data ID");
        }
    }

    private void saveData(String[] dataArray)
    {
        UtilityClass utility = new UtilityClass();

        String bitStringValue;
        int bitIntValue;
        if(dataArray[1].equals("1"))
        {
            bitStringValue = utility.binaryANDOperation(dataArray[2], dataArray[3]);
            bitIntValue = utility.bitToInt(bitStringValue);
        }
        else
        {
            bitStringValue = utility.binaryOROperation(dataArray[2], dataArray[3]);
            bitIntValue = utility.bitToInt(bitStringValue);
        }

        Data collectedData = new Data(dataArray[1], dataArray[2], dataArray[3], bitStringValue, bitIntValue);
        dataHashMap.put(dataArray[0], collectedData);
    }

    protected void writeData(String dataLine)
    {
        checkString(dataLine);
        String[] dataArray = splitLine(dataLine);
        checkData(dataArray);
        checkIsDuplicate(dataArray);
        saveData(dataArray);
    }

    protected String[] splitLine(String line)
    {
        StringTokenizer tokenizer = new StringTokenizer(line);
        String[] values = new String[4];

        for(int i = 0;tokenizer.hasMoreElements();i++)
        {
            values[i] = tokenizer.nextToken();
        }

        return values;
    }

    public HashMap getHashMap()
    {
        return dataHashMap;
    }

    public ArrayList getErrorLog()
    {
        return errorLog;
    }

    public ArrayList getDuplicates()
    {
        return duplicates;
    }

    public Data getData(String keyValue)
    {
        return dataHashMap.get(keyValue);
    }
}

class Data
{
    private String operation, bitString_1, bitString_2, bitValue;
    private int intValue;

    public Data(String operation, String bitString_1, String bitString_2, String bitValue, int intValue)
    {
        this.operation = operation;
        this.bitString_1 = bitString_1;
        this.bitString_2 = bitString_2;
        this.bitValue = bitValue;
        this.intValue = intValue;
    }

    public String getOperation()
    {
        return operation;
    }

    public String getBitString_1()
    {
        return bitString_1;
    }

    public String getBitString_2()
    {
        return bitString_2;
    }

    public String getBitValue()
    {
        return bitValue;
    }

    public int getIntValue()
    {
        return intValue;
    }

    public String toString()
    {
        return getOperation() + " " + getBitString_1() + " " +
                getBitString_2() + " Result is: " +
                getBitValue() + " (" +
                getIntValue() + ")";
    }
}