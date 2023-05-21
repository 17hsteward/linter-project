package JUnitTests;

import domain.Check;
import domain.CheckHollyWoodPrinciple;
import domain.Compiler;
import domain.MyClass;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCheckHollywoodPrinciple {

    @Test
    public void checkSuperClassesForViolation_noViolation() {
        Compiler compiler = new Compiler();
        File[] files = new File[2];
        files[0] = new File("./src/test/java/filesToTest/hollywoodPrinciple/Shape.java");
        files[1] = new File("./src/test/java/filesToTest/hollywoodPrinciple/CubeNoViolation.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckHollywoodPrinciple to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckHollywoodPrinciple.*/
        CheckHollyWoodPrinciple hollyWoodPrincipleCheck = new CheckHollyWoodPrinciple();
        boolean checkSuperClasses = hollyWoodPrincipleCheck.checkSuperClasses(classNodes.get(0));
        assertFalse(checkSuperClasses);
        checkSuperClasses = hollyWoodPrincipleCheck.checkSuperClasses(classNodes.get(1));
        assertFalse(checkSuperClasses);
    }

    @Test
    public void checkSuperClassesForViolation_violation() {
        Compiler compiler = new Compiler();
        File[] files = new File[2];
        files[0] = new File("./src/test/java/filesToTest/hollywoodPrinciple/Shape.java");
        files[1] = new File("./src/test/java/filesToTest/hollywoodPrinciple/Cube.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckHollywoodPrinciple to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckHollywoodPrinciple.*/
        CheckHollyWoodPrinciple hollyWoodPrincipleCheck = new CheckHollyWoodPrinciple();
        boolean checkSuperClasses = hollyWoodPrincipleCheck.checkSuperClasses(classNodes.get(0));
        assertFalse(checkSuperClasses);
        checkSuperClasses = hollyWoodPrincipleCheck.checkSuperClasses(classNodes.get(1));
        assertTrue(checkSuperClasses);
    }

    @Test
    public void checkAbstractClassForViolation_noViolation() {
        Compiler compiler = new Compiler();
        File[] files = new File[2];
        files[0] = new File("./src/test/java/filesToTest/hollywoodPrinciple/ShapeClass.java");
        files[1] = new File("./src/test/java/filesToTest/hollywoodPrinciple/RectangularPrismNoViolation.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckHollywoodPrinciple to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckHollywoodPrinciple.*/
        CheckHollyWoodPrinciple hollyWoodPrincipleCheck = new CheckHollyWoodPrinciple();
        boolean checkAbstractClasses = hollyWoodPrincipleCheck.checkSuperClasses(classNodes.get(0));
        assertFalse(checkAbstractClasses);
        checkAbstractClasses = hollyWoodPrincipleCheck.checkSuperClasses(classNodes.get(1));
        assertFalse(checkAbstractClasses);
    }

    @Test
    public void checkAbstractClassForViolation_violation() {
        Compiler compiler = new Compiler();
        File[] files = new File[2];
        files[0] = new File("./src/test/java/filesToTest/hollywoodPrinciple/ShapeClass.java");
        files[1] = new File("./src/test/java/filesToTest/hollywoodPrinciple/RectangularPrism.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckHollywoodPrinciple to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckHollywoodPrinciple.*/
        CheckHollyWoodPrinciple hollyWoodPrincipleCheck = new CheckHollyWoodPrinciple();
        boolean checkSuperClasses = hollyWoodPrincipleCheck.checkSuperClasses(classNodes.get(0));
        assertFalse(checkSuperClasses);
        checkSuperClasses = hollyWoodPrincipleCheck.checkSuperClasses(classNodes.get(1));
        assertTrue(checkSuperClasses);
    }

    @Test
    public void checkHigherComponent_noViolation(){
        Compiler compiler = new Compiler();
        File[] files = new File[2];
        files[0] = new File("./src/test/java/filesToTest/hollywoodPrinciple/MultiplierNoViolation.java");
        files[1] = new File("./src/test/java/filesToTest/hollywoodPrinciple/Number.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckHollywoodPrinciple to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckHollywoodPrinciple.*/
        CheckHollyWoodPrinciple hollyWoodPrincipleCheck = new CheckHollyWoodPrinciple();
        boolean checkSuperClasses = hollyWoodPrincipleCheck.checkHigherLevelClasses(classNodes.get(0),classNodes);
        assertFalse(checkSuperClasses);
        checkSuperClasses = hollyWoodPrincipleCheck.checkHigherLevelClasses(classNodes.get(1),classNodes);
        assertFalse(checkSuperClasses);
    }

    @Test
    public void checkHigherComponent_violation(){
        Compiler compiler = new Compiler();
        File[] files = new File[2];
        files[0] = new File("./src/test/java/filesToTest/hollywoodPrinciple/Multiplier.java");
        files[1] = new File("./src/test/java/filesToTest/hollywoodPrinciple/Number.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckHollywoodPrinciple to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckHollywoodPrinciple.*/
        CheckHollyWoodPrinciple hollyWoodPrincipleCheck = new CheckHollyWoodPrinciple();
        boolean checkSuperClasses = hollyWoodPrincipleCheck.checkHigherLevelClasses(classNodes.get(0),classNodes);
        assertTrue(checkSuperClasses);
        checkSuperClasses = hollyWoodPrincipleCheck.checkHigherLevelClasses(classNodes.get(1),classNodes);
        assertTrue(checkSuperClasses);
    }

    @Test
    public void checkHollywoodPrinciple_noViolation(){
        Compiler compiler = new Compiler();
        File[] files = new File[6];
        files[0] = new File("./src/test/java/filesToTest/hollywoodPrinciple/MultiplierNoViolation.java");
        files[1] = new File("./src/test/java/filesToTest/hollywoodPrinciple/Number.java");
        files[2] = new File("./src/test/java/filesToTest/hollywoodPrinciple/CubeNoViolation.java");
        files[3] = new File("./src/test/java/filesToTest/hollywoodPrinciple/Shape.java");
        files[4] = new File("./src/test/java/filesToTest/hollywoodPrinciple/RectangularPrismNoViolation.java");
        files[5] = new File("./src/test/java/filesToTest/hollywoodPrinciple/ShapeClass.java");
        List<MyClass> classNodes = compiler.read(files);
        Check hollyWoodPrincipleCheck = new CheckHollyWoodPrinciple();
        String violationString = hollyWoodPrincipleCheck.test(classNodes);
        assertEquals("No issues found",violationString);
    }

    @Test
    public void checkHollywoodPrinciple_SuperClassViolation(){
        Compiler compiler = new Compiler();
        File[] files = new File[4];
        files[0] = new File("./src/test/java/filesToTest/hollywoodPrinciple/Cube.java");
        files[1] = new File("./src/test/java/filesToTest/hollywoodPrinciple/Shape.java");
        files[2] = new File("./src/test/java/filesToTest/hollywoodPrinciple/RectangularPrism.java");
        files[3] = new File("./src/test/java/filesToTest/hollywoodPrinciple/ShapeClass.java");
        List<MyClass> classNodes = compiler.read(files);
        Check hollyWoodPrincipleCheck = new CheckHollyWoodPrinciple();
        String violationString = hollyWoodPrincipleCheck.test(classNodes);
        assertEquals("Hollywood principle is violated by Cube which uses a method from its superclass.\nHollywood principle is violated by RectangularPrism which uses a method from its superclass.\n",violationString);
    }

    @Test
    public void checkHollywoodPrinciple_HigherClassViolation(){
        Compiler compiler = new Compiler();
        File[] files = new File[2];
        files[0] = new File("./src/test/java/filesToTest/hollywoodPrinciple/Multiplier.java");
        files[1] = new File("./src/test/java/filesToTest/hollywoodPrinciple/Number.java");
        List<MyClass> classNodes = compiler.read(files);
        Check hollyWoodPrincipleCheck = new CheckHollyWoodPrinciple();
        String violationString = hollyWoodPrincipleCheck.test(classNodes);
        assertEquals("Hollywood principle is violated by Multiplier which uses a method from a class which uses its method.\nHollywood principle is violated by Number which uses a method from a class which uses its method.\n",violationString);
    }

    @Test
    public void checkHollywoodPrinciple_Package(){
        Compiler compiler = new Compiler();
        File[] files = new File[1];
        files[0] = new File("./src/test/java/filesToTest/hollywoodPrinciple");
        List<MyClass> classNodes = compiler.read(files);
        Check hollyWoodPrincipleCheck = new CheckHollyWoodPrinciple();
        String violationString = hollyWoodPrincipleCheck.test(classNodes);
        assertEquals("Hollywood principle is violated by Cube which uses a method from its superclass.\nHollywood principle is violated by Multiplier which uses a method from a class which uses its method.\n" +
                "Hollywood principle is violated by Number which uses a method from a class which uses its method.\nHollywood principle is violated by RectangularPrism which uses a method from its superclass.\n",violationString);
    }



}
