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
		//check field
		for(MyClass c:myClasses) {
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
						result+="unnecessary access modifier for "+f.getName()+" in class "+c.getName();
					}
				}
			}
		}
		//check method
		return result;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "check access modifier";
	}

}
