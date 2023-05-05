package domain;
import java.util.List;

public class Test {
	public int a;
	String b;
	protected boolean c;
	private float d;
	int[] e;
	List<Integer> f;
	final int af=0;
	static int as=0;
	
	public void f1() {
		a=0;
	}
	int f2(int g) {
		f1();
		return a;
	}
	public String f3(String s,int i,float f) {
		return s;
	}
}
