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
		//add package later, or package is already in name? 
		for(MyClass myClass:myClasses) {
			s+=myClass.toClassUML();
		}
		for(MyClass myClass:myClasses) {
			s+=myClass.toRelationUML();
		}
		s+="@enduml\n";
		return s;
	}
	//may have other methods to convert some asm type to normal type, then each MyClass needs to return something other than string
	//can use hashmap to convert string, or include it in MyClass
}
