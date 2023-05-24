package domain;

import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

public abstract class MyMethod implements UML{
    String name;
	int access;
	String desc;
	List<String> parameters;
	String returnType;
	boolean isStatic;
	boolean isFinal;
	boolean isAbstract;
	List<String> dependent;
	List<Integer> methodLineNumbers;

	public List<String> getDependent(){
		return this.dependent;
	}
	public String getName() {
		return this.name;
	}
	public int getAccess() {
		return this.access;
	}
	public abstract InsnList getInstructions();
	public abstract boolean isGetter();
	public abstract boolean isSetter();
	public abstract List<MyMethodInsn> getMethodInstructions();
	public abstract List<Integer> getMethodLineNumbers();
}
