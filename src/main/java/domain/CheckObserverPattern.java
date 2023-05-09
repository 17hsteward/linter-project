package domain;

import java.util.List;

public class CheckObserverPattern extends Check {

	@Override
	public String test(List<MyClass> myClasses) {
		// TODO Auto-generated method stub
		//for each two classes, if both of them call each other, suggest the observer pattern
		String result="";
		for(MyClass c:myClasses) {
//			System.out.println(c.getName());
//			for(String s:c.getDependent()) {
//				System.out.print("	"+s+"	");
//			}
//			System.out.println();
			for(MyClass c2:myClasses) {
				if(c!=c2) {
					if(c.getDependent().contains(c2.getName())&&c2.getDependent().contains(c.getName())) {
						result+="class "+c.getName()+" and class "+c2.getName()+" are dependent on each other. Try to apply observer pattern\n";
					}
				}
			}
		}
		if(result.isBlank()) {
			result="null";
		}
		return result;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "check observer pattern";
	}

}
