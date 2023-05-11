package filesToTest.hasUnusedNonPrivate;

public class B {
	public A a;
	public B() {
		a=new A();
		a.f1();
		a.f2();
		a.f3();
	}
}
