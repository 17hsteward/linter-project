package filesToTest.noObserverPattern;

public class A {
	public B b;
	private int i=0;
	public A() {
		b=new B();
	}
	public void reset() {
		i=0;
	}
	public void update() {
		b.update();
	}
}
