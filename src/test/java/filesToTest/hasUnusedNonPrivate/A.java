package filesToTest.hasUnusedNonPrivate;

public class A {
	public int public_used,public_unused;
	protected int protected_used,protected_unused;
	int default_used,default_unused;
	private int private_used,private_unused;
	public void f1() {
		public_used=1;
		f4();
	}
	protected void f2() {
		default_used=0;
	}
	int f3() {
		private_used=0;
		return private_used;
	}
	private void f4() {
		protected_used=0;
	}
	public void notCalled() {
		;
	}
}
