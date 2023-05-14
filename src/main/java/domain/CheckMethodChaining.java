package domain;

import java.util.List;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
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
				boolean found=false;
				InsnList instructions=((ASMMethod)m).getInstructions();
				for(AbstractInsnNode node:instructions) {
					if(!(node instanceof MethodInsnNode)&&!method) {
						i=0;
					}
					if(node instanceof LineNumberNode) {
						i=0;
						line=((LineNumberNode)node).line;
						found=false;
					}else if(node instanceof VarInsnNode) {
						i=0;
					}else if(node instanceof MethodInsnNode) {
						//exclude init, system calls, iterator
						if(!m.getName().equals("<init>")&&!((MethodInsnNode)node).owner.startsWith("java")&&!((MethodInsnNode)node).name.equals("iterator")){
							i++;
						}
						if(method) {
							;
						}else if(i>1&&!found) {
							result+="method chaining detected in class "+c.getName()+" in method "+m.getName()+" at line "+line+":\n";
							result+="\""+c.getCodeByLine(line).strip()+"\"\n\n";
							found=true;
						}
						method=true;
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
			result="no method chaining found";
		}
		return result;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "check method chaining";
	}

}
