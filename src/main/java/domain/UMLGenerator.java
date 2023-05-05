package domain;

import java.util.List;

public class UMLGenerator {
	List<MyClass> myClasses;
	public UMLGenerator(List<MyClass> myClasses) {
		this.myClasses=myClasses;
	}
	/**
	 * generate UML code from all classes in myClasses
	 * @return UML code that contains all classes and represents all relationship
	 */
	public String generateAllUMLCode() {
		String s="@startuml\n";
		//TODO:package 
		for(MyClass myClass:myClasses) {
			s+=myClass.toClassUML();
		}
		for(MyClass myClass:myClasses) {
			s+=myClass.toRelationUML();
		}
		s+="@enduml\n";
		return s;
	}
}
