package filesToTest.noObserverPattern;

public class B {
	public A a;
	private int i=0;
	public B() {
		a=new A();
	}
	public void reset() {
		i=0;
	}
	public void update() {
		a.update();
	}
}
