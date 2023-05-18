package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CheckAdapterPattern extends Check {
    @Override
    public String test(List<MyClass> myClasses) {
        StringBuilder violationString = new StringBuilder();
        List<MyClass> possibleTargets = getPossibleTargetsFromPossibleClients(myClasses);
        List<MyClass> possibleAdapters = getPossibleAdaptersFromAdaptees(myClasses);
        List<MyClass> adapters = getAdapters(possibleTargets, possibleAdapters);
        if (adapters.size() != 0) {
            for (MyClass adapter : adapters) {
                String[] name_arr = adapter.getName().split("\\.");
                String adapterName = name_arr[name_arr.length - 1];
                violationString.append("Possible use of adapter pattern using the adapter ").append(adapterName).append("\n");
            }

        } else
            return "No adapter pattern found";
        return violationString.toString();
    }


    public List<MyClass> getPossibleTargetsFromPossibleClients(List<MyClass> classNodes) {
        List<MyClass> targets = new ArrayList<>();
        List<String> classNames = this.getClassNames(classNodes);
        for (MyClass classNode : classNodes) {
            if (!classNode.isAbstract) {
                List<MyField> fieldNodes = classNode.getFields();
                for (MyField fieldNode : fieldNodes) {
                    String fieldType = getFieldType(fieldNode);
                    if (classNames.contains(fieldType)) {
                        MyClass target = getClassNode(fieldType, classNodes);
                        if (Objects.requireNonNull(target).isAbstract) {
                            targets.add(target);
                        }
                    }
                }
            }
        }
        return targets;
    }

    private MyClass getClassNode(String fieldNode, List<MyClass> classNodes) {
        for (MyClass classNode : classNodes) {
            String[] name_arr = classNode.getName().split("\\.");
            String actualName = name_arr[name_arr.length - 1];
            if (actualName.equals(fieldNode)) {
                return classNode;
            }
        }
        return null;
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

    public List<MyClass> getPossibleAdaptersFromAdaptees(List<MyClass> classNodes) {
        List<MyClass> adapters = new ArrayList<>();
        List<String> classNames = this.getClassNames(classNodes);
        for (MyClass classNode : classNodes) {
            List<MyField> fieldNodes = classNode.getFields();
            for (MyField fieldNode : fieldNodes) {
                String fieldType = getFieldType(fieldNode);
                if (classNames.contains(fieldType)) {
                    ;
                    adapters.add(classNode);
                }
            }
        }
        return adapters;
    }

    private String getFieldType(MyField fieldNode) {
        String fieldType = fieldNode.getType();
        String[] name_arr = fieldType.split("/");
        fieldType = name_arr[name_arr.length - 1];
        if (fieldType.contains(";")) {
            fieldType = fieldType.substring(0, fieldType.length() - 1);
        }

        return fieldType;
    }

    public List<MyClass> getAdapters(List<MyClass> possibleTargets, List<MyClass> possibleAdapters) {
        List<MyClass> adapters = new ArrayList<>();
        for (MyClass adapter : possibleAdapters) {
            //For if target is abstract class
            String superClass = adapter.extend;
            String[] name_arr = superClass.split("/");
            String superClassActualName = name_arr[name_arr.length - 1];
            //For if target is interface
            List<String> interfaces = adapter.implement;
            List<String> interfaceActualNames = new ArrayList<>();
            for (String interfaced : interfaces) {
                String[] interfaceName_arr = interfaced.split("/");
                interfaceActualNames.add(interfaceName_arr[interfaceName_arr.length - 1]);
            }
            List<String> targets = getClassNames(possibleTargets);
            if (targets.contains(superClassActualName)) {
                adapters.add(adapter);
            } else {
                for (String interfaced : interfaceActualNames) {
                    if (targets.contains(interfaced)) {
                        adapters.add(adapter);
                    }
                }
            }
        }
        return adapters;
    }

    @Override
    public String getName() {
        return "check adapter pattern";
    }
}
