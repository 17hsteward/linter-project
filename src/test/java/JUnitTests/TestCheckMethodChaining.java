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
    void existCanBePrivate_success() {
        classes = Helper.getClasses("hasMethodChaining");
        String result = check.test(classes);
        assertTrue(result.length()>20);
    }
}
