package domain;

import java.util.List;

public class CheckAdapterPattern extends Check{
    @Override
    public String test(List<MyClass> myClasses) {
        return null;
    }

    @Override
    public String getName() {
        return "check for observer pattern";
    }
}
