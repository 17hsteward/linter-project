package domain;

import java.util.List;

public class CheckDRY extends Check {

	@Override
	public String test(List<MyClass> myClasses) {
		// TODO Auto-generated method stub
		// No new exceptions can be thrown
		// Clients shouldnt know about subtype
		// new derived classes should just extend without replacing functionality of old classes
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "check DRY";
	}

}
