package JUnitTests;

import domain.CheckAbstractInstance;
import domain.CheckHollyWoodPrinciple;
import domain.Compiler;
import domain.MyClass;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCheckHollywoodPrinciple {
    @Test
    public void checkInterfaceForViolation_Violation() {
        Compiler compiler = new Compiler();
        File[] files = new File[2];
        files[0] = new File("./src/test/java/filesToTest/hollywoodPrinciple/Shape.java");
        files[1] = new File("./src/test/java/filesToTest/hollywoodPrinciple/Square.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckHollywoodPrinciple to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckHollywoodPrinciple.*/
        CheckHollyWoodPrinciple hollyWoodPrincipleCheck = new CheckHollyWoodPrinciple();
        boolean checkInterface = hollyWoodPrincipleCheck.checkInterface(classNodes);
        assertTrue(checkInterface);
    }

    @Test
    public void violatesPrinciple(){


    }
    @Test
    public void followsPrinciple(){

    }

}
