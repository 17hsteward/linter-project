package domain;

import java.util.List;

public class CheckMethodChaining extends Check {

	@Override
	public String test(List<MyClass> myClasses) {
		// TODO Auto-generated method stub
		return "result of method chaining check; ";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "check method chaining";
	}

}
