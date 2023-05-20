package domain;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

import java.util.ArrayList;
import java.util.List;

public class CheckHollyWoodPrinciple extends Check{
    @Override
    public String test(List<MyClass> myClasses) {
        StringBuilder violationString = new StringBuilder();
        if(violationString.isEmpty()){
            violationString.append("No issues found");
        }
        return violationString.toString();
    }

    @Override
    public String getName() {
        return "check hollywood principle";
    }

    public boolean checkInterface(List<MyClass> classNodes){
        for(MyClass classNode:classNodes){
            List<String> interfaces = classNode.implement;
            List<MyMethod> methodNodes = classNode.methods;
            for(MyMethod methodNode:methodNodes){
                List<MyMethodInsn> insns = methodNode.getMethodInstructions();
                for(MyMethodInsn insn: insns){
                    if(interfaces.contains(insn.getOwner()) && !insn.getName().equals("<init>")){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkAbstract(){
        return false;
    }
}
