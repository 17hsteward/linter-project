package domain;

import java.util.List;

public abstract class MyMethod {
	String name;
	int access;
	String desc;
	List<String> parameters;
	String returnType;
	boolean isStatic;
	boolean isFinal;
	List<String> dependent;

	public List<String> getDependent(){
		return this.dependent;
	}
	public String getName() {
		return this.name;
	}
	public int getAccess() {
		return this.access;
	}
}
