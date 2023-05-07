package domain;

import java.util.List;

public class CheckObserverPattern extends Check {

	@Override
	public String test(List<MyClass> myClasses) {
		// TODO Auto-generated method stub
		return "result of observer pattern check; ";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "check observer pattern";
	}

}
