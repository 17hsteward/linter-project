package domain;

import java.util.ArrayList;
import java.util.List;

public class AbstractInstanceCheck {

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
}
