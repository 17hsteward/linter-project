package domain;
import java.util.List;

public class Test {
	public int a;
	String b;
	protected boolean c;
	private float d;
	int[] e;
	List<Integer> l1;
	List<String> l2;
	final int af=0;
	static int as=0;
	final static int asf=0;
	int[][][] array;
	
	double g;
	long h;
	Integer i;
	char j;
	byte k;
	short l;
	
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
	public List<Integer> f4(List<Integer> l){
		return l;
	}
	public void f5(int i,int j) {
		int k=i+j;
		String s=String.valueOf(k);
		char cha=s.charAt(0);
		int in=this.f2(k);
	}
}
