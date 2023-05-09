package domain;

import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

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
				if((f.getAccess()&Opcodes.ACC_PRIVATE)==0) {
					boolean has=false;
					for(MyClass c2:myClasses) {
						if(c==c2) {
							continue;
						}
						//c2 get dependency
						if(c2.getDependent().contains(c.getName())) {
							has=true;
							break;
						}
					}
					if(!has) {
						result+="unnecessary access modifier for field "+f.getName()+" in class "+c.getName()+"\n";
					}
				}
			}
			//check method
			for(MyMethod m:c.getMethods()) {
				String methodName=c.getName()+"."+m.getName();
				if(m.getName().equals("<init>")) {
					continue;
				}
				if((m.getAccess()&Opcodes.ACC_PRIVATE)==0) {
					boolean has=false;
					for(MyClass c2:myClasses) {
						if(c==c2) {
							continue;
						}
						//requires further analysis on the method body
						for(MyMethod m2:c2.getMethods()) {
							
							for(AbstractInsnNode node:((ASMMethod)m2).getInstructions()) {
								if(node instanceof MethodInsnNode) {
									if((((MethodInsnNode)node).owner.replaceAll("/",".")+"."+((MethodInsnNode)node).name).equals(methodName)){
										has=true;
										break;
									}
								}
							}
						}
					}
					if(!has) {
						result+="unnecessary access modifier for method "+m.getName()+" in class "+c.getName()+"\n";
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
		return "check access modifier";
	}

}
