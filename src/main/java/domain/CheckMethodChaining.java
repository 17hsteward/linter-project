package domain;

import java.util.List;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class CheckMethodChaining extends Check {

	@Override
	public String test(List<MyClass> myClasses) {
		// TODO Auto-generated method stub
		//check whether one line contains lots of methods, and suggests to split them or gather their classes
		//in one line node, methodInsn, Insn/TypeInsn
		String result="";
		for(MyClass c:myClasses) {
			for(MyMethod m:c.getMethods()) {
				int i=0;
				int line=0;
				boolean method=false;
				InsnList instructions=((ASMMethod)m).getInstructions();
				for(AbstractInsnNode node:instructions) {
					if(!(node instanceof MethodInsnNode)&&!method) {
						i=0;
					}
					if(node instanceof LineNumberNode) {
						i=0;
						line=((LineNumberNode)node).line;
					}else if(node instanceof VarInsnNode) {
						i=0;
					}else if(node instanceof MethodInsnNode) {
						if(method) {
							i=0;
						}else {
							if(!m.getName().equals("<init>")&&!((MethodInsnNode)node).owner.startsWith("java")){//exclude system calls
								i++;
							}
							if(i>1) {
								result+="method chaining detected in class "+c.getName()+" in method "+m.getName()+" at line "+line+"\n";
							}
							method=true;
						}
					}else if(node instanceof FieldInsnNode) {
						i=0;
					}
					if(!(node instanceof MethodInsnNode)) {
						method=false;
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
