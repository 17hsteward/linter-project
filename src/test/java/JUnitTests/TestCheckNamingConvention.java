package JUnitTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.CheckNamingConvention;
import domain.Compiler;
import domain.MyClass;

public class TestCheckNamingConvention {
	@Test
    public void properNaming_Passes() {
        Compiler compiler = new Compiler();
        File[] files = new File[1];
        files[0] = new File("./src/test/java/filesToTest/namingConventions/ProperNamingClass.java");
        List<MyClass> classNodes = compiler.read(files);
        CheckNamingConvention templateChecker = new CheckNamingConvention();
        boolean checkTemp = templateChecker.test(classNodes).equals("Package Naming:\n"
        		+ "		GOOD: All package names are lower case!\n"
        		+ "Class Naming:\n"
        		+ "		GOOD: Class name starts with an upper case character!\n"
        		+ "Field Naming:\n"
        		+ "	Constant: MARKS GOOD.\n"
        		+ "	Field: goodInt GOOD.\n"
        		+ "Method Naming:\n"
        		+ "	Method Name: thisNameIsCorrect\n"
        		+ "		Passed: Method name is good.\n");
        assertTrue(checkTemp);
    }
	
	@Test
    public void poorNamingForAll_Fails() {
        Compiler compiler = new Compiler();
        File[] files = new File[1];
        files[0] = new File("./src/test/java/filesToTest/namingConventions/badNaming.java");
        List<MyClass> classNodes = compiler.read(files);
        CheckNamingConvention templateChecker = new CheckNamingConvention();
        boolean checkTemp = templateChecker.test(classNodes).equals("Package Naming:\n"
        		+ "		GOOD: All package names are lower case!\n"
        		+ "Class Naming:\n"
        		+ "		Class Name {badNaming} should start with an upper case character.\n"
        		+ "Field Naming:\n"
        		+ "	Constant: marks should be all uppercase letters or underscore.\n"
        		+ "	Field: P should start with a lowercase letter and each word after should only have the first letter capital.\n"
        		+ "Method Naming:\n"
        		+ "	Method Name: SlowlyFADING\n"
        		+ "		Failed: Method names should start with a lowercase letter and each word after should only have the first letter capital.\n");
        assertTrue(checkTemp);
    }
}
