package domain;

import org.objectweb.asm.tree.ClassNode;

public class MyClass {
	ClassNode classNode;
	//list of field, method, other myclass
	public MyClass(ClassNode cn) {
		this.classNode=cn;
	}
}
