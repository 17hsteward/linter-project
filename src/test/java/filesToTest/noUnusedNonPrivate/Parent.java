package filesToTest.noUnusedNonPrivate;

public class Parent {
	private String name;
	public Parent() {
		this.name="parent";
	}
	private void callChild() {
		Child child=new Child();
		child.getName();
	}
}
