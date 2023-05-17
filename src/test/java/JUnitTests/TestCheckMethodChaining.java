package JUnitTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import domain.Check;
import domain.CheckMethodChaining;
import domain.MyClass;

public class TestCheckMethodChaining {
	public List<MyClass> classes;
    public Check check = new CheckMethodChaining();

    @Test
    void has_method_chain() {
        classes = Helper.getClasses("hasMethodChaining");
        String result = check.test(classes);
        assertTrue(result.contains("this.getB().getC().C1()"));
    }
    
    @Test
    void no_method_chain() {
        classes = Helper.getClasses("noUnusedNonPrivate");
        String result = check.test(classes);
        assertTrue(result.equals("no method chaining found"));
    }
}
