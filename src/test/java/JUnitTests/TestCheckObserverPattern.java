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
    void hasObserverPattern_mutuallyCalled() {
        classes = Helper.getClasses("noObserverPattern");
        String result = check.test(classes);
        assertTrue(result.contains("noObserverPattern.A")&&result.contains("noObserverPattern.B"));
    }
    
    @Test
    void noObserverPattern_oneWayCalled() {
    	classes = Helper.getClasses("noUnusedNonPrivate");
        String result = check.test(classes);
        assertTrue(result.equals("no observer pattern issue found"));
    }
}
