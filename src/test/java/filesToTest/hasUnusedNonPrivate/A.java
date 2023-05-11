package filesToTest.hasUnusedNonPrivate;

public class A {
	public int public_used;
	public int public_unused;
	protected int protected_used,protexted_unused;
	int default1_used,default2_unused;
	private int private1_used;
	private int private2_unused;
	public void f1() {
		public_used=1;
		f4();
	}
	protected void f2() {
		default1_used=0;
	}
	int f3() {
		private1_used=0;
		return private1_used;
	}
	private void f4() {
		protected_used=0;
	}
}
