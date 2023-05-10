package domain;

import java.util.List;
import java.util.LinkedList;

public class UMLGenerator {
	private List<MyClass> myClasses;
	private List<String> myClassNames;
	public UMLGenerator(List<MyClass> myClasses) {
		this.myClasses=myClasses;
		this.myClassNames=new LinkedList<>();
		for(MyClass c:this.myClasses) {
			myClassNames.add(c.getName());
		}
	}
	/**
	 * generate UML code from all classes in myClasses
	 * @return UML code that contains all classes and represents all relationship
	 */
	public String generateAllUMLCode() {
		String s="@startuml\n";
		for(MyClass myClass:myClasses) {
			s+=((ASMClass)myClass).toClassUML();
		}
		for(MyClass myClass:myClasses) {
			s+=((ASMClass)myClass).toRelationUML(this.myClassNames);
		}
		s+="@enduml\n";
		return s;
	}
}
