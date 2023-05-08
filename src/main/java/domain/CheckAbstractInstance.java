package domain;

import java.util.ArrayList;
import java.util.List;

public class CheckAbstractInstance extends Check {

    public List<MyClass> getConcreteClasses(MyClass classNode, List<MyClass> classNodes){
        List<MyClass> concreteClasses = new ArrayList<>();
        String className = classNode.getName();
        for(MyClass node:classNodes){
            String superClassName = node.getName();
            if(className.equals(superClassName)){
                concreteClasses.add(node);
            }
        }
        return concreteClasses;
    }

    public boolean isInterface(MyClass classNode){
        return classNode.isInterface;
    }
    public boolean isAbstractClass(MyClass classNode){
        return classNode.isAbstract;
    }

    @Override
    public String test(List<MyClass> myClasses) {
        StringBuilder violationString = new StringBuilder();
        for(MyClass classNode:myClasses){
            if(isAbstractClass(classNode)||isInterface(classNode)){
                if(getConcreteClasses(classNode,myClasses).size()<1){
                    violationString.append(classNode.getName()).append(" does not have any concrete implementation\n");
                }
            }
        }
        return violationString.toString();
    }

    @Override
    public String getName() {
        return "check abstract instances are implemented";
    }
}
