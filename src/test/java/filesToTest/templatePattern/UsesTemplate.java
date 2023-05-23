package filesToTest.templatePattern;

import java.util.ArrayList;
import java.util.List;
public abstract class UsesTemplate {
	private Piece p;
	
	public UsesTemplate() {
		this.p = new Piece();
	}
	
	public final void templateMethod() {
		boolean s;
		this.step1();
		this.step2();
		this.step3();
		s = instructionSample("fish");
	}
	
	public abstract void step1();
	
	public abstract void step2();
	
	public abstract void step3();
	
	public boolean instructionSample(String path) {
		int i = 0;
		// 0 usually refers to this so what about getting a field
		if(this.p instanceof Piece) {
			Piece m = (Piece)this.p;
			System.out.println("poop");
		}
		List<String> stringSet;
		stringSet = new ArrayList<>();
		this.p.makeDiversion();
		System.out.println(stringSet);
		System.out.println(path);
		return true;
	}
	
	public void randomSample(String p) {
		int x = 2+2;
		x++;
		p.charAt(0);
		return;
	}
	
	public void dupSample(String x) {
		int p = 3+3;
		p++;
		x.charAt(0);
		return;
	}
	
	public void dumbSample(String x) {
		int p = 3+3;
		p++;
		x.charAt(0);
		return;
	}
	
	public void dumbSample(String x, String c) {
		int p = 3+3;
		p++;
		x.charAt(0);
		return;
	}
	
	public class Piece {
		public void makeDiversion() {
			return;
		}
	}
}
