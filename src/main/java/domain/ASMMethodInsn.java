package domain;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

public class ASMMethodInsn extends MyMethodInsn{


    private final MethodInsnNode methodInsnNode;

    public ASMMethodInsn(AbstractInsnNode insnNode) {
        this.methodInsnNode = (MethodInsnNode)insnNode;
    }

    @Override
    public String getOwner() {
        return methodInsnNode.owner;
    }

    @Override
    public String getName() {
        return methodInsnNode.name;
    }
}
