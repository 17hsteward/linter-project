package domain;

import java.util.ArrayList;
import java.util.List;

public class CheckHollyWoodPrinciple extends Check{
    @Override
    public String test(List<MyClass> myClasses) {
        StringBuilder violationString = new StringBuilder();
        for(MyClass classNode: myClasses) {
            if (this.checkSuperClasses(classNode)){
                String[] name_arr = classNode.getName().split("\\.");
                String actualName = name_arr[name_arr.length - 1];
                violationString.append("Hollywood principle is violated by ").append(actualName).append(" which uses a method from its superclass.\n");
            }
            else if(this.checkHigherLevelClasses(classNode,myClasses)){
                String[] name_arr = classNode.getName().split("\\.");
                String actualName = name_arr[name_arr.length - 1];
                violationString.append("Hollywood principle is violated by ").append(actualName).append(" which uses a method from a class which uses its method.\n");
            }
        }
        if (violationString.isEmpty()) {
            violationString.append("No issues found");
        }
        return violationString.toString();
    }

    @Override
    public String getName() {
        return "check hollywood principle";
    }

    public boolean checkSuperClasses(MyClass classNode){
            List<String> interfaces = classNode.implement;
            String superclass = classNode.extend;
            List<MyMethod> methodNodes = classNode.methods;
            for(MyMethod methodNode:methodNodes){
                List<MyMethodInsn> insns = methodNode.getMethodInstructions();
                for(MyMethodInsn insn: insns){
                    if((interfaces.contains(insn.getOwner()) || superclass.equals(insn.getOwner())) && !insn.getName().equals("<init>")){
                        return true;
                    }
                }
            }
        return false;
    }

    public boolean checkHigherLevelClasses(MyClass classNode, List<MyClass> classNodes){
        List<String> classNodeNames = getClassNames(classNodes);
            List<String> owners = new ArrayList<>();
            List<MyMethod> methodNodes = classNode.methods;
            for(MyMethod methodNode:methodNodes) {
                List<MyMethodInsn> insns = methodNode.getMethodInstructions();
                for (MyMethodInsn insn : insns) {
                    String[] name_arr = insn.getOwner().split("/");
                    String actualName = name_arr[name_arr.length - 1];
                    String[] classname_arr = classNode.getName().split("\\.");
                    String classactualName = classname_arr[classname_arr.length - 1];
                    if (!classactualName.equals(actualName)&&classNodeNames.contains(actualName)) {
                        owners.add(insn.getOwner());
                    }
                }
                List<MyClass> ownerClasses = getClassNode(owners, classNodes);
                for (MyClass owner : ownerClasses) {
                    List<MyMethod> ownerMethodNodes = owner.methods;
                    for (MyMethod methodNoded : ownerMethodNodes) {
                        List<MyMethodInsn> instructions = methodNoded.getMethodInstructions();
                        for (MyMethodInsn insn : instructions) {
                            String[] name_arr = insn.getOwner().split("/");
                            String actualName = name_arr[name_arr.length - 1];
                            String[] className_arr = classNode.getName().split("\\.");
                            String classActualName = className_arr[className_arr.length - 1];
                            if (classActualName.equals(actualName) && !insn.getName().equals("<init>")) {
                                return true;
                            }
                        }
                    }
                }
            }
        return false;
    }

    private List<String> getClassNames(List<MyClass> classNodes) {
        List<String> names = new ArrayList<>();
        for (MyClass classNode : classNodes) {
            String[] name_arr = classNode.getName().split("\\.");
            String actualName = name_arr[name_arr.length - 1];
            names.add(actualName);
        }
        return names;
    }

    private List<MyClass> getClassNode(List<String> nodes, List<MyClass> classNodes) {
        List<MyClass> owners = new ArrayList<>();
        for(String node:nodes) {
            for (MyClass classNode : classNodes) {
                String[] name_arr = classNode.getName().split("\\.");
                String actualName = name_arr[name_arr.length - 1];
                String[] nodeName_arr = node.split("/");
                String nodeActualName = nodeName_arr[nodeName_arr.length - 1];
                if (actualName.equals(nodeActualName)) {
                    owners.add(classNode);
                }
            }
        }
        return owners;
    }
}
