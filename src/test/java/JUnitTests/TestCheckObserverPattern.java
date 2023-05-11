package JUnitTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import domain.Check;
import domain.CheckObserverPattern;
import domain.MyClass;

public class TestCheckObserverPattern {
	public List<MyClass> classes;
    public Check check = new CheckObserverPattern();

    @Test
    void existCanBePrivate_success() {
        classes = Helper.getClasses("noObserverPattern");
        for(MyClass c:classes) {
        	System.out.println(c.getName());
        	System.out.println(c.getFields().get(0).getName());
        }
        String result = check.test(classes);
        assertTrue(result.contains("noObserverPattern.A")&&result.contains("noObserverPattern.B"));
    }
}
