package domain;

import java.util.ArrayList;
import java.util.List;

public class CheckAbstractInstance extends Check {

    @Override
    public String test(List<MyClass> myClasses) {
        StringBuilder violationString = new StringBuilder();
        for(MyClass classNode:myClasses){

            if(isInterface(classNode)){
                if(getConcreteClassesInterface(classNode,myClasses).size()<1){
                    String[] name_arr = classNode.getName().split("\\.");
                    String actualName = name_arr[name_arr.length - 1];
                    violationString.append(actualName).append(" interface does not have any concrete implementation\n");
                }
            }
            else if(isAbstractClass(classNode)){
                if(getConcreteClassesAbstractClass(classNode,myClasses).size()<1){
                    String[] name_arr = classNode.getName().split("\\.");
                    String actualName = name_arr[name_arr.length - 1];
                    violationString.append(actualName).append(" abstract class does not have any concrete implementation\n");
                }
            }
        }
        if(violationString.length()==0)
            violationString.append("No issues found");
        return violationString.toString();
    }

    public List<MyClass> getConcreteClassesAbstractClass(MyClass classNode, List<MyClass> classNodes){
        List<MyClass> concreteClasses = new ArrayList<>();
        String className = classNode.getName();
        String[] name_arr = className.split("\\.");
        String classActualName = name_arr[name_arr.length - 1];
        for(MyClass node:classNodes){
            String superClassName = node.getExtend();
            String[] superName_arr = superClassName.split("/");
            String superClassActualName = superName_arr[superName_arr.length - 1];
            if(classActualName.equals(superClassActualName)){
                concreteClasses.add(node);
            }
        }
        return concreteClasses;
    }

    public List<MyClass> getConcreteClassesInterface(MyClass classNode, List<MyClass> classNodes){
        List<MyClass> concreteClasses = new ArrayList<>();
        String className = classNode.getName();
        String[] name_arr = className.split("\\.");
        String classActualName = name_arr[name_arr.length - 1];
        for(MyClass node:classNodes){
            List<String> superClasses = node.getImplement();
            for(String superClass:superClasses){
                String[] superName_arr = superClass.split("/");
                String superClassActualName = superName_arr[superName_arr.length - 1];
                if(classActualName.equals(superClassActualName)){
                    concreteClasses.add(node);
                }
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
    public String getName() {
        return "check abstract instances are implemented";
    }
}
