package filesToTest.hasMethodChaining;

public class A {
	B b;
	public void a1() {
		b=new B();
	}
	public B getB() {
		return b;
	}
	public void start() {
		this.a1();
		this.getB().getC().C1();
		boolean b=toString().contains("");
	}
	public String toString() {
		return "";
	}
}
