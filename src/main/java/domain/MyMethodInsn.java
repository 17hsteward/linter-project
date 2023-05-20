package domain;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;


public abstract  class MyMethodInsn {
    public abstract String getOwner();
    public abstract String getName();
}
