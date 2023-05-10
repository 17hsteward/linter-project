package domain;

import java.util.List;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class CheckMethodChaining extends Check {

	@Override
	public String test(List<MyClass> myClasses) {
		// TODO Auto-generated method stub
		//check whether one line contains lots of methods, and suggests to split them or gather their classes
		String result="";
		for(MyClass c:myClasses) {
			for(MyMethod m:c.getMethods()) {
				int i=0;
				int line = 0;
//				System.out.println(((ASMMethod)m).getInstructions());
				InsnList instructions=((ASMMethod)m).getInstructions();
				for(AbstractInsnNode node:instructions) {
//					System.out.println(node.getClass());
					if(node instanceof LabelNode) {
//						System.out.println();
					}else if(node instanceof LineNumberNode) {
						i=0;
						line=((LineNumberNode)node).line;
//						System.out.println("line "+((LineNumberNode)node).line);
					}else if(node instanceof VarInsnNode) {
						i=0;
//						System.out.println("	var:"+((VarInsnNode)node).var);
//						System.out.println("	var type:"+((VarInsnNode)node).getType());
					}else if(node instanceof InsnNode) {
//						System.out.println("	opcode:"+((InsnNode)node).getOpcode());
					}else if(node instanceof MethodInsnNode) {
//						System.out.println("    method class"+((MethodInsnNode)node).owner+"	"+((MethodInsnNode)node).name+"(?)");
						i++;
						if(i>2&&!m.getName().equals("<init>")) {
							result+="method chain detected in class "+c.getName()+" in method "+m.getName()+" at line "+line+"\n";
						}
					}else if(node instanceof FieldInsnNode) {
						i=0;
//						System.out.println("	var name:"+((FieldInsnNode)node).name);
//						System.out.println("	var class:"+((FieldInsnNode)node).owner);
//						String f=UML.typeConvert(((FieldInsnNode)node).desc);
//						System.out.println("    type"+f);
					}else if(node instanceof TypeInsnNode) {
//						System.out.println("	type:"+((TypeInsnNode)node).desc);
					}
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
		return "check method chaining";
	}

}
