package domain;

import java.util.List;

public class CheckObserverPattern extends Check {

	@Override
	public String test(List<MyClass> myClasses) {
		// TODO Auto-generated method stub
		//for each two classes, if both of them call each other, suggest the observer pattern
		String result="";
		for(int i=0;i<myClasses.size()-1;i++) {
			MyClass c=myClasses.get(i);
			for(int j=i+1;j<myClasses.size();j++) {
				MyClass c2=myClasses.get(j);
				if(c.getDependent().contains(c2.getName())&&c2.getDependent().contains(c.getName())) {
					result+="class "+c.getName()+" and class "+c2.getName()+" are dependent on each other. Try to apply observer pattern\n";
				}
			}
		}
		if(result.isBlank()) {
			result="no issue found";
		}
		return result;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "check observer pattern";
	}

}
