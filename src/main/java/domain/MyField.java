package domain;

public abstract class MyField implements UML{
	String name;
	String desc;//field type
	boolean isList;
	String listType;
	boolean isStatic;
	boolean isFinal;
	int access;
	
	public String getType() {
		if(isList) {
			return this.listType;
		}else {
			return this.desc;
		}
	}
	public String getName() {
		return this.name;
	}
	public int getAccess() {
		return this.access;
	}
}
