package domain;

import java.util.List;

public abstract class MyMethod implements UML{
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

	public abstract boolean isGetter();
	public abstract boolean isSetter();
}
