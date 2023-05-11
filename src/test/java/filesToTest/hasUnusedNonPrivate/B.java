package filesToTest.hasUnusedNonPrivate;

public class B {
	public A a;
	public B() {
		a=new A();
		a.f1();
		a.f2();
		a.f3();
		int a1=a.public_used;
		int a2=a.default_used;
		int a3=a.protected_used;
	}
}
