package JUnitTests;

import domain.Check;
import domain.CheckAdapterPattern;
import domain.Compiler;
import domain.MyClass;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCheckAdapterPattern {
    @Test
    public void isClientAndTargetPresent_NotPresent(){
        JTextArea textArea = new JTextArea();
        Compiler compiler = new Compiler(textArea);
        File[] files = new File[2];
        files[0]= new File("./src/test/java/filesToTest/adapterPattern/NotClient.java");
        files[1]= new File("./src/test/java/filesToTest/adapterPattern/NotTarget.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAdapterPattern object to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAdapterPattern adapterPatternCheck = new  CheckAdapterPattern();
        List<MyClass> possibleTargets = adapterPatternCheck.getPossibleTargetsFromPossibleClients(classNodes);
        assertTrue(possibleTargets.isEmpty());
    }

    @Test
    public void isClientAndTargetPresent_OnePresent(){
        JTextArea textArea = new JTextArea();
        Compiler compiler = new Compiler(textArea);
        File[] files = new File[2];
        files[0]= new File("./src/test/java/filesToTest/adapterPattern/Client.java");
        files[1]= new File("./src/test/java/filesToTest/adapterPattern/Target.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAdapterPattern object to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAdapterPattern adapterPatternCheck = new  CheckAdapterPattern();
        List<MyClass> possibleTargets = adapterPatternCheck.getPossibleTargetsFromPossibleClients(classNodes);
        assertFalse(possibleTargets.isEmpty());
        assertEquals(1,possibleTargets.size());
        assertEquals("filesToTest.adapterPattern.Target",possibleTargets.get(0).getName());
    }

    @Test
    public void isClientAndTargetPresent_MultiplePresent(){
        JTextArea textArea = new JTextArea();
        Compiler compiler = new Compiler(textArea);
        File[] files = new File[4];
        files[0]= new File("./src/test/java/filesToTest/adapterPattern/Client.java");
        files[1]= new File("./src/test/java/filesToTest/adapterPattern/Target.java");
        files[2]= new File("./src/test/java/filesToTest/adapterPattern/AnotherClient.java");
        files[3]= new File("./src/test/java/filesToTest/adapterPattern/Duck.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAdapterPattern object to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAdapterPattern adapterPatternCheck = new  CheckAdapterPattern();
        List<MyClass> possibleTargets = adapterPatternCheck.getPossibleTargetsFromPossibleClients(classNodes);
        assertFalse(possibleTargets.isEmpty());
        assertEquals(2,possibleTargets.size());
        assertEquals("filesToTest.adapterPattern.Target",possibleTargets.get(0).getName());
        assertEquals("filesToTest.adapterPattern.Duck",possibleTargets.get(1).getName());
    }

    @Test
    public void isAdapterAndAdpteePresent_NonePresent(){
        JTextArea textArea = new JTextArea();
        Compiler compiler = new Compiler(textArea);
        File[] files = new File[2];
        files[0]= new File("./src/test/java/filesToTest/adapterPattern/NotAdapter.java");
        files[1]= new File("./src/test/java/filesToTest/adapterPattern/NotAdaptee.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAdapterPattern object to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAdapterPattern adapterPatternCheck = new  CheckAdapterPattern();
        List<MyClass> possibleAdapters = adapterPatternCheck.getPossibleAdaptersFromAdaptees(classNodes);
        assertTrue(possibleAdapters.isEmpty());
    }
    @Test
    public void isAdapterAndAdpteePresent_OnePresent(){
        JTextArea textArea = new JTextArea();
        Compiler compiler = new Compiler(textArea);
        File[] files = new File[2];
        files[0]= new File("./src/test/java/filesToTest/adapterPattern/Adapter.java");
        files[1]= new File("./src/test/java/filesToTest/adapterPattern/Adaptee.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAdapterPattern object to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAdapterPattern adapterPatternCheck = new CheckAdapterPattern();
        List<MyClass> possibleAdapters = adapterPatternCheck.getPossibleAdaptersFromAdaptees(classNodes);
        assertFalse(possibleAdapters.isEmpty());
        assertEquals(1,possibleAdapters.size());
        assertEquals("filesToTest.adapterPattern.Adapter",possibleAdapters.get(0).getName());
    }
    @Test
    public void isAdapterAndAdopteePresent_MultiplePresent(){
        JTextArea textArea = new JTextArea();
        Compiler compiler = new Compiler(textArea);
        File[] files = new File[4];
        files[0]= new File("./src/test/java/filesToTest/adapterPattern/Adapter.java");
        files[1]= new File("./src/test/java/filesToTest/adapterPattern/Adaptee.java");
        files[2]= new File("./src/test/java/filesToTest/adapterPattern/AnotherAdapter.java");
        files[3]= new File("./src/test/java/filesToTest/adapterPattern/Turkey.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAdapterPattern object to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAdapterPattern adapterPatternCheck = new CheckAdapterPattern();
        List<MyClass> possibleAdapters = adapterPatternCheck.getPossibleAdaptersFromAdaptees(classNodes);
        assertFalse(possibleAdapters.isEmpty());
        assertEquals(2,possibleAdapters.size());
        assertEquals("filesToTest.adapterPattern.Adapter",possibleAdapters.get(0).getName());
        assertEquals("filesToTest.adapterPattern.AnotherAdapter",possibleAdapters.get(1).getName());
    }

    @Test
    public void isTargetandAdapterConnected_NoConnection(){
        JTextArea textArea = new JTextArea();
        Compiler compiler = new Compiler(textArea);
        File[] files = new File[5];
        files[0]= new File("./src/test/java/filesToTest/adapterPattern/Client.java");
        files[1]= new File("./src/test/java/filesToTest/adapterPattern/Target.java");
        files[2]= new File("./src/test/java/filesToTest/adapterPattern/Adapter.java");
        files[3]= new File("./src/test/java/filesToTest/adapterPattern/Adaptee.java");
        files[4]= new File("./src/test/java/filesToTest/adapterPattern/Duck.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAdapterPattern object to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAdapterPattern adapterPatternCheck = new  CheckAdapterPattern();
        List<MyClass> possibleTargets = adapterPatternCheck.getPossibleTargetsFromPossibleClients(classNodes);
        List<MyClass> possibleAdapters = adapterPatternCheck.getPossibleAdaptersFromAdaptees(classNodes);
        List<MyClass> adapters = adapterPatternCheck.getAdapters(possibleTargets,possibleAdapters);
        assertTrue(adapters.isEmpty());
    }

    @Test
    public void isTargetandAdapterConnected_Connection(){
        JTextArea textArea = new JTextArea();
        Compiler compiler = new Compiler(textArea);
        File[] files = new File[4];
        files[0]= new File("./src/test/java/filesToTest/adapterPattern/AnotherClient.java");
        files[1]= new File("./src/test/java/filesToTest/adapterPattern/Duck.java");
        files[2]= new File("./src/test/java/filesToTest/adapterPattern/AnotherAdapter.java");
        files[3]= new File("./src/test/java/filesToTest/adapterPattern/Turkey.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAdapterPattern object to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAdapterPattern adapterPatternCheck = new  CheckAdapterPattern();
        List<MyClass> possibleTargets = adapterPatternCheck.getPossibleTargetsFromPossibleClients(classNodes);
        List<MyClass> possibleAdapters = adapterPatternCheck.getPossibleAdaptersFromAdaptees(classNodes);
        List<MyClass> adapters = adapterPatternCheck.getAdapters(possibleTargets,possibleAdapters);
        assertFalse(adapters.isEmpty());
        assertEquals(1,adapters.size());
        assertEquals("filesToTest.adapterPattern.AnotherAdapter",adapters.get(0).getName());
    }

    @Test
    public void isTargetandAdapterConnected_ConnectionWithAbstractClass(){
        JTextArea textArea = new JTextArea();
        Compiler compiler = new Compiler(textArea);
        File[] files = new File[4];
        files[0]= new File("./src/test/java/filesToTest/adapterPattern/AbstractClient.java");
        files[1]= new File("./src/test/java/filesToTest/adapterPattern/AbstractTarget.java");
        files[2]= new File("./src/test/java/filesToTest/adapterPattern/AbstractAdapter.java");
        files[3]= new File("./src/test/java/filesToTest/adapterPattern/AbstractAdaptee.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAdapterPattern object to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAdapterPattern adapterPatternCheck = new  CheckAdapterPattern();
        List<MyClass> possibleTargets = adapterPatternCheck.getPossibleTargetsFromPossibleClients(classNodes);
        List<MyClass> possibleAdapters = adapterPatternCheck.getPossibleAdaptersFromAdaptees(classNodes);
        List<MyClass> adapters = adapterPatternCheck.getAdapters(possibleTargets,possibleAdapters);
        assertFalse(adapters.isEmpty());
        assertEquals(1,adapters.size());
        assertEquals("filesToTest.adapterPattern.AbstractAdapter",adapters.get(0).getName());
    }

    @Test
    public void isTargetandAdapterConnected_MultipleConnection(){
        JTextArea textArea = new JTextArea();
        Compiler compiler = new Compiler(textArea);
        File[] files = new File[1];
        files[0]= new File("./src/test/java/filesToTest/adapterPattern");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAdapterPattern object to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAdapterPattern adapterPatternCheck = new  CheckAdapterPattern();
        List<MyClass> possibleTargets = adapterPatternCheck.getPossibleTargetsFromPossibleClients(classNodes);
        List<MyClass> possibleAdapters = adapterPatternCheck.getPossibleAdaptersFromAdaptees(classNodes);
        List<MyClass> adapters = adapterPatternCheck.getAdapters(possibleTargets,possibleAdapters);
        assertFalse(adapters.isEmpty());
        assertEquals(3,adapters.size());
        assertEquals("filesToTest.adapterPattern.AbstractAdapter",adapters.get(0).getName());
        assertEquals("filesToTest.adapterPattern.Adapter",adapters.get(1).getName());
        assertEquals("filesToTest.adapterPattern.AnotherAdapter",adapters.get(2).getName());
    }

    @Test
    public void isAdapterPatternPresent_NotPresent(){
        JTextArea textArea = new JTextArea();
        Compiler compiler = new Compiler(textArea);
        File[] files = new File[5];
        files[0]= new File("./src/test/java/filesToTest/adapterPattern/Client.java");
        files[1]= new File("./src/test/java/filesToTest/adapterPattern/Target.java");
        files[2]= new File("./src/test/java/filesToTest/adapterPattern/Adapter.java");
        files[3]= new File("./src/test/java/filesToTest/adapterPattern/Adaptee.java");
        files[4]= new File("./src/test/java/filesToTest/adapterPattern/Duck.java");
        List<MyClass> classNodes = compiler.read(files);
        Check adapterPatternCheck = new  CheckAdapterPattern();
        String  violationString = adapterPatternCheck.test(classNodes);
        assertEquals("No adapter pattern found",violationString);
    }

    @Test
    public void isAdapterPatternPresent_Present(){
        JTextArea textArea = new JTextArea();
        Compiler compiler = new Compiler(textArea);
        File[] files = new File[1];
        files[0]= new File("./src/test/java/filesToTest/adapterPattern");
        List<MyClass> classNodes = compiler.read(files);
        Check adapterPatternCheck = new  CheckAdapterPattern();
        String  violationString = adapterPatternCheck.test(classNodes);
        assertEquals("Possible use of adapter pattern using the adapter AbstractAdapter\nPossible use of adapter pattern using the adapter Adapter\nPossible use of adapter pattern using the adapter AnotherAdapter\n",violationString);
    }
}
