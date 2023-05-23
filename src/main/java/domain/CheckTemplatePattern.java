package domain;

import java.util.LinkedList;
import java.util.List;

public class CheckTemplatePattern extends Check {

	@Override
	public String test(List<MyClass> myClasses) {
		// TODO Auto-generated method stub
		// Look for abstract class
		// Look for public final method
		// Make sure method calls at least one abstract method belonging to itself
		StringBuilder b = new StringBuilder();
		b.append("Looking for An Abstract Class\n");
		List<MyClass> abstractClasses = findAbstractClasses(myClasses);
		if (abstractClasses.size() <= 0) {
			b.append("		No abstract classes found.\n");
		} else {
			for(MyClass mc : abstractClasses) {
				b.append(classFollowsTemplateMethod(mc));
			}
		}
		return b.toString();
	}
	
	private List<MyClass> findAbstractClasses(List<MyClass> classes) {
		List<MyClass> abstractClasses = new LinkedList<>();
		for(MyClass mc : classes) {
			if (mc.isAbstract) abstractClasses.add(mc);
		}
		return abstractClasses;
	}
	
	private String classFollowsTemplateMethod(MyClass mc) {
		StringBuilder res = new StringBuilder();
		res.append("\n");
		res.append("Looking for Final Methods in Class: " + mc.className + "\n");
		List<MyMethod> finalMethods = new LinkedList<>();
		finalMethods.addAll(mc.methods);
		finalMethods.removeIf((MyMethod m) -> {
			return !m.isFinal;
		});
		if(finalMethods.size() <= 0) {
			res.append("		" + mc.className + " has no final methods.\n");
		} else {
			// add in all known abstractMethods
			List<String> abstractMethods = new LinkedList<>();
			for(MyMethod m: mc.getMethods()) {
				if (m.isAbstract) abstractMethods.add(m.name);
			}
			
			if(abstractMethods.isEmpty()) {
				res.append("		" + mc.className + " has no abstract methods to be called by a template method.\n");
			} else {
				int absUsageCount = 0;
				for(MyMethod m: finalMethods) {
					// need to find instructions that call in abstract method
					if(this.callsAbstract(m, abstractMethods)) {
						res.append("		Final Method, " + m.name + ", utilizes the template pattern.\n");
						absUsageCount++;
					} else {
						res.append("		Final Method, " + m.name + ", does not utilize the template pattern. Try calling an abstract method within this method.\n");
					}
				}
				if (absUsageCount == 0) {
					res.append("		" + mc.className + " has no final method that calls at least one abstract method.");
				}
			}
		}
		return res.toString();
		
	}
	
	private boolean callsAbstract(MyMethod m, List<String> abstractMrthods) {
		// May want to check if it calls two unique abstract methods
		for(MyMethodInsn mi :m.getMethodInstructions()) {
			if(abstractMrthods.contains(mi.getName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "check template pattern";
	}

}
