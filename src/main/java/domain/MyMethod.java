package domain;

import java.util.List;

public abstract class MyMethod {
	public abstract String toUML();//remove it later
	public abstract List<String> getDependent();
	public abstract String getName();

	public abstract boolean isGetter();
	public abstract boolean isSetter();

}
