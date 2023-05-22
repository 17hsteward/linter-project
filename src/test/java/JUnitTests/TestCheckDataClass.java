package JUnitTests;

import domain.Check;
import domain.CheckDataClass;
import domain.MyClass;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCheckDataClass {
    public List<MyClass> classes;
    public Check check = new CheckDataClass();

    @Test
    void detectDataClass_success() {
        classes = Helper.getClasses("hasDataClass");
        String result = check.test(classes);
        assertEquals("There's no data class.", result);
    }

    @Test
    void detectDataClass_fail() {
        classes = Helper.getClasses("noDataClass");
        String result = check.test(classes);
        assertEquals("There's no data class.", result);
    }
}