package JUnitTests;

import domain.Check;
import domain.CheckAbstractInstance;
import domain.Compiler;
import domain.MyClass;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCheckAbstractInstance {

    @Test
    public void checkIfClassAbstract_Success(){
        Compiler compiler = new Compiler();
        File[] files = new File[1];
        files[0]= new File("./src/test/java/filesToTest/AbstractInstance/Duck.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAbstractInstance to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAbstractInstance abstractInstanceCheck = new CheckAbstractInstance();
        boolean isAbstract = abstractInstanceCheck.isAbstractClass(classNodes.get(0));
        assertTrue(isAbstract);
    }
    @Test
    public void checkIfClassAbstract_Failed(){
        Compiler compiler = new Compiler();
        File[] files = new File[1];
        files[0]= new File("./src/test/java/filesToTest/abstractInstance/Mallard.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAbstractInstance to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAbstractInstance abstractInstanceCheck = new CheckAbstractInstance();
        boolean isAbstract = abstractInstanceCheck.isAbstractClass(classNodes.get(0));
        assertFalse(isAbstract);
    }

    @Test
    public void checkIfInterface_Success(){
        Compiler compiler = new Compiler();
        File[] files = new File[1];
        files[0]= new File("./src/test/java/filesToTest/abstractInstance/Animal.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAbstractInstance to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAbstractInstance abstractInstanceCheck = new CheckAbstractInstance();
        boolean isInterface = abstractInstanceCheck.isInterface(classNodes.get(0));
        assertTrue(isInterface);
    }
    @Test
    public void checkIfInterface_Failed(){
        Compiler compiler = new Compiler();
        File[] files = new File[1];
        files[0]= new File("./src/test/java/filesToTest/abstractInstance/Dog.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAbstractInstance to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAbstractInstance abstractInstanceCheck = new CheckAbstractInstance();
        boolean isInterface = abstractInstanceCheck.isInterface(classNodes.get(0));
        assertFalse(isInterface);
    }
    @Test
    public void returnConcreteClassesOfAbstractClass(){
        Compiler compiler = new Compiler();
        File[] files = new File[4];
        files[0]= new File("./src/test/java/filesToTest/abstractInstance/RedHead.java");
        files[1]= new File("./src/test/java/filesToTest/abstractInstance/Duck.java");
        files[2]= new File("./src/test/java/filesToTest/abstractInstance/Dog.java");
        files[3]= new File("./src/test/java/filesToTest/abstractInstance/Mallard.java");

        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAbstractInstance to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAbstractInstance abstractInstanceCheck = new CheckAbstractInstance();
        List<MyClass> concreteClasses = abstractInstanceCheck.getConcreteClassesAbstractClass(classNodes.get(1),classNodes);
        assertEquals(2,concreteClasses.size());
        assertEquals("filesToTest.AbstractInstance.RedHead",concreteClasses.get(0).getName());
        assertEquals("filesToTest.AbstractInstance.Mallard",concreteClasses.get(1).getName());
    }
    @Test
    public void returnConcreteClassesOfInterface(){
        Compiler compiler = new Compiler();
        File[] files = new File[5];
        files[0]= new File("./src/test/java/filesToTest/abstractInstance/Dog.java");
        files[1]= new File("./src/test/java/filesToTest/abstractInstance/Mallard.java");
        files[2]= new File("./src/test/java/filesToTest/abstractInstance/Cat.java");
        files[3]= new File("./src/test/java/filesToTest/abstractInstance/Tiger.java");
        files[4]= new File("./src/test/java/filesToTest/abstractInstance/Animal.java");

        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAbstractInstance to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAbstractInstance abstractInstanceCheck = new CheckAbstractInstance();
        List<MyClass> concreteClasses = abstractInstanceCheck.getConcreteClassesInterface(classNodes.get(4),classNodes);
        assertEquals(3,concreteClasses.size());
        assertEquals("filesToTest.AbstractInstance.Dog",concreteClasses.get(0).getName());
        assertEquals("filesToTest.AbstractInstance.Cat",concreteClasses.get(1).getName());
        assertEquals("filesToTest.AbstractInstance.Tiger",concreteClasses.get(2).getName());
    }

    @Test
    public void returnConcreteClassesOfInterface_ConcreteClassesHaveMultipleInterfaces(){
        Compiler compiler = new Compiler();
        File[] files = new File[6];
        files[0]= new File("./src/test/java/filesToTest/abstractInstance/Dog.java");
        files[1]= new File("./src/test/java/filesToTest/abstractInstance/Mallard.java");
        files[2]= new File("./src/test/java/filesToTest/abstractInstance/Cat.java");
        files[3]= new File("./src/test/java/filesToTest/abstractInstance/Tiger.java");
        files[4]= new File("./src/test/java/filesToTest/abstractInstance/Animal.java");
        files[5]= new File("./src/test/java/filesToTest/abstractInstance/Pet.java");
        List<MyClass> classNodes = compiler.read(files);
        /*Using direct CheckAbstractInstance to test specific method in this class which will not be and doesn't need to be implemented
        in the Check interface since it's only used in CheckAbstractInstance.*/
        CheckAbstractInstance abstractInstanceCheck = new CheckAbstractInstance();
        //First interface Animal
        List<MyClass> concreteClasses = abstractInstanceCheck.getConcreteClassesInterface(classNodes.get(4),classNodes);
        assertEquals(3,concreteClasses.size());
        assertEquals("filesToTest.AbstractInstance.Dog",concreteClasses.get(0).getName());
        assertEquals("filesToTest.AbstractInstance.Cat",concreteClasses.get(1).getName());
        assertEquals("filesToTest.AbstractInstance.Tiger",concreteClasses.get(2).getName());
        //Second interface Pet
        concreteClasses = abstractInstanceCheck.getConcreteClassesInterface(classNodes.get(5),classNodes);
        assertEquals(2,concreteClasses.size());
        assertEquals("filesToTest.AbstractInstance.Dog",concreteClasses.get(0).getName());
        assertEquals("filesToTest.AbstractInstance.Cat",concreteClasses.get(1).getName());
    }

    @Test
    public void checkIfAbstractClassImplemented_ReturnEmptyString(){
        Compiler compiler = new Compiler();
        File[] files = new File[3];
        files[0]= new File("./src/test/java/filesToTest/abstractInstance/Duck.java");
        files[1]= new File("./src/test/java/filesToTest/abstractInstance/Mallard.java");
        files[2]= new File("./src/test/java/filesToTest/abstractInstance/RedHead.java");
        List<MyClass> classNodes = compiler.read(files);
        Check abstractInstanceCheck = new CheckAbstractInstance();
        String violationString = abstractInstanceCheck.test(classNodes);
        assertEquals("No issues found",violationString);
    }

    @Test
    public void checkIfAbstractClassImplemented_ReturnOneString(){
        Compiler compiler = new Compiler();
        File[] files = new File[3];
        files[0]= new File("./src/test/java/filesToTest/abstractInstance/Duck.java");
        files[1]= new File("./src/test/java/filesToTest/abstractInstance/Animal.java");
        files[2]= new File("./src/test/java/filesToTest/abstractInstance/Dog.java");
        List<MyClass> classNodes = compiler.read(files);
        Check abstractInstanceCheck = new CheckAbstractInstance();
        String violationString = abstractInstanceCheck.test(classNodes);
        assertEquals("Duck abstract class does not have any concrete implementation\n",violationString);
    }
    @Test
    public void checkIfAbstractClassImplemented_ReturnMultipleString(){
        Compiler compiler = new Compiler();
        File[] files = new File[5];
        files[0]= new File("./src/test/java/filesToTest/abstractInstance/Duck.java");
        files[1]= new File("./src/test/java/filesToTest/abstractInstance/Animal.java");
        files[2]= new File("./src/test/java/filesToTest/abstractInstance/Dog.java");
        files[3]= new File("./src/test/java/filesToTest/abstractInstance/Coffee.java");
        files[4]= new File("./src/test/java/filesToTest/abstractInstance/Student.java");
        List<MyClass> classNodes = compiler.read(files);
        Check abstractInstanceCheck = new CheckAbstractInstance();
        String violationString = abstractInstanceCheck.test(classNodes);
        assertEquals("Duck abstract class does not have any concrete implementation\nCoffee abstract class does not have any concrete implementation\nStudent abstract class does not have any concrete implementation\n",violationString);
    }

    @Test
    public void checkIfInterfaceImplemented_ReturnEmptyString(){
        Compiler compiler = new Compiler();
        File[] files = new File[5];
        files[0]= new File("./src/test/java/filesToTest/abstractInstance/Animal.java");
        files[1]= new File("./src/test/java/filesToTest/abstractInstance/Cat.java");
        files[2]= new File("./src/test/java/filesToTest/abstractInstance/Pet.java");
        files[3]= new File("./src/test/java/filesToTest/abstractInstance/Dog.java");
        files[4]= new File("./src/test/java/filesToTest/abstractInstance/Tiger.java");
        List<MyClass> classNodes = compiler.read(files);
        Check abstractInstanceCheck = new CheckAbstractInstance();
        String violationString = abstractInstanceCheck.test(classNodes);
        assertEquals("No issues found",violationString);
    }

    @Test
    public void checkIfInterfaceImplemented_ReturnOneString(){
        Compiler compiler = new Compiler();
        File[] files = new File[3];
        files[0]= new File("./src/test/java/filesToTest/abstractInstance/Animal.java");
        files[1]= new File("./src/test/java/filesToTest/abstractInstance/Pet.java");
        files[2]= new File("./src/test/java/filesToTest/abstractInstance/Tiger.java");
        List<MyClass> classNodes = compiler.read(files);
        Check abstractInstanceCheck = new CheckAbstractInstance();
        String violationString = abstractInstanceCheck.test(classNodes);
        assertEquals("Pet interface does not have any concrete implementation\n",violationString);
    }

    @Test
    public void checkIfInterfaceImplemented_ReturnMultipleString(){
        Compiler compiler = new Compiler();
        File[] files = new File[3];
        files[0]= new File("./src/test/java/filesToTest/abstractInstance/Animal.java");
        files[1]= new File("./src/test/java/filesToTest/abstractInstance/Mallard.java");
        files[2]= new File("./src/test/java/filesToTest/abstractInstance/Pet.java");
        List<MyClass> classNodes = compiler.read(files);
        Check abstractInstanceCheck = new CheckAbstractInstance();
        String violationString = abstractInstanceCheck.test(classNodes);
        assertEquals("Animal interface does not have any concrete implementation\nPet interface does not have any concrete implementation\n",violationString);
    }


}
