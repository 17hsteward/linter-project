package domain;

import java.util.List;

public abstract class MyClass {
	public abstract List<MyField> getFields();
	public abstract List<MyMethod> getMethods();
	public abstract String getName();
	public abstract String getExtend();
	public abstract List<String> getImplement();
	public abstract List<String> getDependent();
}
