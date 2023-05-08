package domain;

import java.util.List;

public class CheckDataClass extends Check{

    @Override
    public String test(List<MyClass> myClasses) {
        String output = "";
        for(MyClass c: myClasses) {
            boolean allGettersSetters = true;
            for (MyMethod m : c.getMethods()) {
                if (!m.isGetter()) {
                    allGettersSetters = false;
                }
                if (!m.isSetter()) {
                    allGettersSetters = false;
                }
            }
            if (allGettersSetters) {
                output += c.getName() + "is a data class with no functionality.";
            }
        }
        if (output.isEmpty()) {
            output = "There's no data class.";
        }
        return output;
    }

    @Override
    public String getName() {
        return "detect data class";
    }
}
