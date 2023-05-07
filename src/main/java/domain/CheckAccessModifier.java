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
		for(MyClass c:myClasses) {
			for(MyField f:c.getFields()) {
				//check for not private field
				if((f.getAccess()&Opcodes.ACC_PRIVATE)!=0) {
					for(MyClass c2:myClasses) {
						//c2 get dependency
					}
				}
			}
		}
		return "result of access modifier check respond";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "check access modifier";
	}

}
