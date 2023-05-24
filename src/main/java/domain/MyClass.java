package domain;

import java.util.List;

public abstract class MyClass {
	String className;
	String packageName;
	String extend;
	List<String> implement;
	List<MyField> fields;
	List<MyField> constants;
	List<MyMethod> methods;
	List<String> dependent;
	boolean isAbstract;
	boolean isInterface;
	String path;
	List<String> code;
	public abstract void setDependent(List<String> classNames);
	
	public List<MyField> getFields(){
		return this.fields;
	}
	public List<MyField> getConstants(){
		return this.constants;
	}
	public List<MyMethod> getMethods(){
		return this.methods;
	}
	public String getExtend() {
		return this.extend;
	}
	public List<String> getImplement() {
		return this.implement;
	}
	public String getName() {
		return this.packageName+"."+this.className;
	}
	public List<String> getDependent() {
		return this.dependent;
	}
	public void setCode(List<String> code) {
		this.code=code;
	}
	public String getCodeByLine(int line) {
		return this.code.get(line-1);
	}
}
