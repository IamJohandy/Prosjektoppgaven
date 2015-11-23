import java.io.File;

/**
 * Created by Johan on 17.11.2015.
 */

public class DataCollectorClass
{
    private FileReader reader;

    public DataCollectorClass(FileReader reader)
    {
        this.reader = reader;
    }

    public void readDataFromFile()
    {
        String dataLine;
        while((dataLine = readDataFromLine()) != null)
        {
            writeData(dataLine);
        }
    }

    public String readDataFromLine()
    {
        String line = reader.readLine();
        checkString(line);
        return line;
    }

    public void checkString(String string)
    {
        //Invalid input returns error
    }

    public void writeData(String dataLine)
    {

    }


}
