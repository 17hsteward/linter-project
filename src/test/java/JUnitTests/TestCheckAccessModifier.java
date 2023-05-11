package JUnitTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import domain.Check;
import domain.CheckAccessModifier;
import domain.MyClass;

public class TestCheckAccessModifier {
	public List<MyClass> classes;
    public Check check = new CheckAccessModifier();

    @Test
    void existCanBePrivate_success() {
        classes = Helper.getClasses("hasUnusedNonPrivate");
        String result = check.test(classes);
        assertTrue(result.contains("public_unused")&&result.contains("protected_unused")&&result.contains("default_unused")&&result.contains("notCalled"));
    }
}
