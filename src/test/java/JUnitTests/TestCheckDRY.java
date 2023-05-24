package JUnitTests;
import domain.CheckDRY;
import domain.Compiler;
import domain.MyClass;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCheckDRY {
	@Test
    public void oneClass_NotUniqueMethods_NoViolations() {
        Compiler compiler = new Compiler();
        File[] files = new File[1];
        files[0] = new File("./src/test/java/filesToTest/hasDuplication/Duplication.java");
        List<MyClass> classNodes = compiler.read(files);
        CheckDRY dryChecker = new CheckDRY();
        boolean checkDups = dryChecker.projectPassDuplication(classNodes);
        assertFalse(checkDups);
    }
	
	@Test
    public void oneClass_UniqueMethods_NoViolations() {
        Compiler compiler = new Compiler();
        File[] files = new File[1];
        files[0] = new File("./src/test/java/filesToTest/noDuplication/NoDups.java");
        List<MyClass> classNodes = compiler.read(files);
        CheckDRY dryChecker = new CheckDRY();
        boolean checkDups = dryChecker.projectPassDuplication(classNodes);
        assertTrue(checkDups);
    }
	
	@Test
    public void twoClass_NonUniqueMethods_HasViolations() {
        Compiler compiler = new Compiler();
        File[] files = new File[2];
        files[0] = new File("./src/test/java/filesToTest/hasDuplication/ADup.java");
        files[1] = new File("./src/test/java/filesToTest/hasDuplication/Duplication.java");
        List<MyClass> classNodes = compiler.read(files);
        CheckDRY dryChecker = new CheckDRY();
        boolean checkDups = dryChecker.projectPassDuplication(classNodes);
        assertFalse(checkDups);
    }
	
	@Test
    public void twoClass_UniqueMethods_NoViolations() {
        Compiler compiler = new Compiler();
        File[] files = new File[2];
        files[0] = new File("./src/test/java/filesToTest/noDuplication/NoDups.java");
        files[1] = new File("./src/test/java/filesToTest/noDuplication/NoDups2.java");
        List<MyClass> classNodes = compiler.read(files);
        CheckDRY dryChecker = new CheckDRY();
        boolean checkDups = dryChecker.projectPassDuplication(classNodes);
        assertTrue(checkDups);
    }
}
