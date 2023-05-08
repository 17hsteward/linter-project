package domain;

import java.util.List;

import org.objectweb.asm.Opcodes;

public class CheckAccessModifier extends Check{

	@Override
	public String test(List<MyClass> myClasses) {
		// TODO Auto-generated method stub
		//for all class
		//for all field and method
		//for all class, check their path relationship
		//for all method call
		//check whether exists same field or method and compare privacy
		String result="";
		for(MyClass c:myClasses) {
			//check field
			for(MyField f:c.getFields()) {
				//check for not private field
				if((f.getAccess()&Opcodes.ACC_PRIVATE)!=0) {
					boolean has=false;
					for(MyClass c2:myClasses) {
						if(c==c2) {
							continue;
						}
						//c2 get dependency
						if(c2.getDependent().contains(c.getName())) {
							has=true;
						}
					}
					if(!has) {
						result+="unnecessary access modifier privacy for field "+f.getName()+" in class "+c.getName();
					}
				}
			}
			//check method
			for(MyMethod m:c.getMethods()) {
				if((m.getAccess()&Opcodes.ACC_PRIVATE)!=0) {
					boolean has=false;
					for(MyClass c2:myClasses) {
						if(c==c2) {
							continue;
						}
						//requires further analysis on the method body
//						if(c2.getDependent())
					}
				}
			}
		}
		
		
		return result;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "check access modifier";
	}

}
