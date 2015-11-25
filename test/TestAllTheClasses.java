import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DataCollectorClassTest.class,
        UtilityClassTest.class,
        ParameterizedBitToIntTest.class,
        ParameterizedHexToIntTest.class,
        ParameterizedIntToBitTest.class,
        ParameterizedIntToHexTest.class
})

public class TestAllTheClasses
{

}