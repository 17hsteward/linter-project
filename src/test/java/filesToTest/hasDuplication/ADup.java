package filesToTest.hasDuplication;

import java.util.ArrayList;
import java.util.List;

public class ADup {
	public void lessLonelyMethod() {
		List<Integer> someInts = new ArrayList<>();
		someInts.add(4);
		someInts.add(3);
		someInts.add(2);
	}
	
	public void specialVoid() {
		Duplication d = new Duplication();
		d.addEm(2, 3);
		d.addEm(3, 4);
	}
}
