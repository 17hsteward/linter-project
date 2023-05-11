package domain;

import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
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
			if(c.isAbstract||c.isInterface) {
				//ignore class if is not concrete
				continue;
			}
			for(MyField f:c.getFields()) {
				//check for not private field
				if((f.getAccess()&Opcodes.ACC_PRIVATE)==0) {
					boolean has=false;
					for(MyClass c2:myClasses) {
						if(c==c2) {
							continue;
						}
						//c2 get dependency
						//use getInstructions
						for(MyMethod m:c2.getMethods()) {
							for(AbstractInsnNode node:((ASMMethod)m).getInstructions()) {
								if(node instanceof FieldInsnNode) {
									String name=((FieldInsnNode)node).name;
									String owner=((FieldInsnNode)node).owner.replaceAll("/",".");
									if(f.getName().equals(name)&&c.getName().equals(owner)) {
										has=true;
										break;
									}
								}
							}
						}
					}
					if(!has) {
						result+="field \""+f.getName()+"\" in class \""+c.getName()+"\" can be private\n";
					}
				}
			}
			//check method
			for(MyMethod m:c.getMethods()) {
				if(m.getName().equals("<init>")) {
					continue;
				}
				if(!c.getExtend().contains("java")||!c.getImplement().isEmpty()) {
					//extend or implement other classes
					continue;
				}
				String methodName=c.getName()+"."+m.getName();
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
									String name=((MethodInsnNode)node).name;
									String owner=((MethodInsnNode)node).owner.replaceAll("/",".");
									if((owner+"."+name).equals(methodName)){
										has=true;
										break;
									}
								}
							}
						}
					}
					if(!has) {
						result+="method \""+m.getName()+"\" in class \""+c.getName()+"\" can be private\n";
					}
				}
			}
		}
		if(result.isBlank()) {
			result="no issue found";
		}else {
			result="following fields or methods can be private\n"+result;
		}
		return result;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "check access modifier";
	}

}
