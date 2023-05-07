package domain;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ClassRelation {
	Map<String,String> hasa;
	Map<String,String> extend;
	Map<String,String> implement;
	Map<String,String> dependent;
	public ClassRelation(List<MyClass> classes) {
		this.hasa=new HashMap<>();
		this.extend=new HashMap<>();
		this.implement=new HashMap<>();
		this.dependent=new HashMap<>();
		for(MyClass c:classes) {
			String className=c.getName();
			//get has-a relationship
			//get extend, implement, dependent by adding getters in MyClass
		}
	}
	
}
