package filesToTest.hasDuplication;

public class Duplication {
	public void dupMethod1() {
		String b = "1";
		String c = "2";
		Integer.parseInt(b);
		Integer.parseInt(c);
		int d = 1;
		int e = 2;
		addEm(d, e);
	}
	
	public void dupMethod2() {
		String b = "1";
		String c = "2";
		Integer.parseInt(b);
		Integer.parseInt(c);
		int d = 1;
		int e = 2;
		addEm(d, e);
		addEm(e, d);
	}
	
	public void uniqueMethod() {
		System.out.println("pop");
		double c = 0;
		double e = 2;
		Double.max(c, e);
	}
	
	public int addEm(int a, int b) {
		return a + b;
	}
}
