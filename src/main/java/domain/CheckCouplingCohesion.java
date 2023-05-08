package domain;

import java.util.List;

public class CheckCouplingCohesion extends Check{

    @Override
    public String test(List<MyClass> myClasses) {
        return null;
    }

    @Override
    public String getName() {
        return "check coupling and cohesion";
    }
}
