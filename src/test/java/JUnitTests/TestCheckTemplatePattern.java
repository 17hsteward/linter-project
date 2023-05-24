package JUnitTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.CheckTemplatePattern;
import domain.Compiler;
import domain.MyClass;

public class TestCheckTemplatePattern {
	@Test
    public void hasFinal_NoAbsractDef_DoesNotFollowTemplate() {
        Compiler compiler = new Compiler();
        File[] files = new File[1];
        files[0] = new File("./src/test/java/filesToTest/templatePattern/HasFinalNoAbstractDef.java");
        List<MyClass> classNodes = compiler.read(files);
        CheckTemplatePattern templateChecker = new CheckTemplatePattern();
        boolean checkTemp = templateChecker.test(classNodes).equals("Looking for An Abstract Class\n"
        		+ "\n"
        		+ "Looking for Final Methods in Class: HasFinalNoAbstractDef\n"
        		+ "		HasFinalNoAbstractDef has no abstract methods to be called by a template method.\n"
        		+ "");
        assertTrue(checkTemp);
    }
	
	@Test
    public void hasFinal_NoAbstractUsed_DoesNotFollowTemplate() {
        Compiler compiler = new Compiler();
        File[] files = new File[1];
        files[0] = new File("./src/test/java/filesToTest/templatePattern/HasFinalNoAbstractUsed.java");
        List<MyClass> classNodes = compiler.read(files);
        CheckTemplatePattern templateChecker = new CheckTemplatePattern();
        boolean checkTemp = templateChecker.test(classNodes).equals("Looking for An Abstract Class\n"
        		+ "\n"
        		+ "Looking for Final Methods in Class: HasFinalNoAbstractUsed\n"
        		+ "		Final Method, templateMethod, does not utilize the template pattern. Try calling an abstract method within this method.\n"
        		+ "		HasFinalNoAbstractUsed has no final method that calls at least one abstract method.\n");
        assertTrue(checkTemp);
    }
	
	@Test
    public void noFinal_DoesNotFollowTemplate() {
        Compiler compiler = new Compiler();
        File[] files = new File[1];
        files[0] = new File("./src/test/java/filesToTest/templatePattern/NoFinal.java");
        List<MyClass> classNodes = compiler.read(files);
        CheckTemplatePattern templateChecker = new CheckTemplatePattern();
        boolean checkTemp = templateChecker.test(classNodes).equals("Looking for An Abstract Class\n"
        		+ "\n"
        		+ "Looking for Final Methods in Class: NoFinal\n"
        		+ "		NoFinal has no final methods.\n");
        assertTrue(checkTemp);
    }
	
	@Test
    public void hasFinal_hasAbstract_FollowsTemplate() {
        Compiler compiler = new Compiler();
        File[] files = new File[1];
        files[0] = new File("./src/test/java/filesToTest/templatePattern/UsesTemplate.java");
        List<MyClass> classNodes = compiler.read(files);
        CheckTemplatePattern templateChecker = new CheckTemplatePattern();
        boolean checkTemp = templateChecker.test(classNodes).equals("Looking for An Abstract Class\n"
        		+ "\n"
        		+ "Looking for Final Methods in Class: UsesTemplate\n"
        		+ "		Final Method, templateMethod, utilizes the template pattern.\n");
        assertTrue(checkTemp);
    }
}
