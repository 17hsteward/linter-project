package domain;

import java.util.HashMap;
import java.util.List;

public class CheckDRY extends Check {

	@Override
	public String test(List<MyClass> myClasses) {
		// TODO Auto-generated method stub
		// Doesn't particularly matter if the variables are the same
		// Need to check every class combination
		//	Inside that check every method combination
		StringBuilder b = new StringBuilder();
		for(int classAdex = 0; classAdex < myClasses.size(); classAdex++) {
			for(int classBdex = classAdex; classBdex < myClasses.size();classBdex++) {
				b.append("Classes: " + myClasses.get(classAdex).className  + ", " + myClasses.get(classBdex).className+ "\n");
				b.append(getDupResMethods(myClasses.get(classAdex), myClasses.get(classBdex)));
			}
		}
		return b.toString();
	}
	
	private String getDupResMethods(MyClass classA, MyClass classB) {
		StringBuilder b = new StringBuilder();
		for(int methodAdex = 0; methodAdex < classA.getMethods().size(); methodAdex++) {
			for(int methodBdex = methodAdex; methodBdex < classB.getMethods().size(); methodBdex++) {
				if(classA.className.equals(classB.className) && methodAdex == methodBdex) {
					continue;
				} else {
					// b.append("	Checking Methods: "+ classA.getMethods().get(methodAdex).name + ", "+ classB.getMethods().get(methodBdex).name +"\n");
					b.append(methodComparison(classA.getMethods().get(methodAdex), classB.getMethods().get(methodBdex)));
				}
			}
		}
		return b.toString();
	}
	
	private String methodComparison(MyMethod methodA, MyMethod methodB) {
		StringBuilder b = new StringBuilder();
		List<MyMethodInsn> instrA = methodA.getMethodInstructions();
		List<MyMethodInsn> instrB = methodB.getMethodInstructions();
		int totalSubsetMatches = 0;
		int min = Integer.min(instrA.size(), instrB.size());
		for (int subsetSize = 2; subsetSize < min; subsetSize++) {
			for (int startDexA = 0; startDexA < (instrA.size()-subsetSize)+1; startDexA++) {
				for (int startDexB = 0; startDexB < (instrB.size()-subsetSize)+1; startDexB++) {
					List<MyMethodInsn> aSubset = instrA.subList(startDexA, startDexA+subsetSize);
					List<MyMethodInsn> bSubset = instrB.subList(startDexB, startDexB+subsetSize);
					int matchSize = 0;
					for (int i = 0; i < subsetSize; i++) {
						if(myEquals(aSubset.get(i), bSubset.get(i))) {
							matchSize++;
							// b.append("			Instructions: "+ aSubset.get(i).getOwner()+"-"+aSubset.get(i).getName()+" and " +bSubset.get(i).getOwner()+"-"+bSubset.get(i).getName()+" matches\n");
						}
					}
					if(matchSize == subsetSize) {
						totalSubsetMatches++;
						b.append("\n");
						b.append("		Methods, " + methodA.name + " and " + methodB.name+ ", Show Duplication for an Instruction Subset of Size: " + Integer.toString(subsetSize) +"\n");
						b.append("			First Instruction in Each Respectively Starts with "+ aSubset.get(0).getOwner()+"."+aSubset.get(0).getName()+" and " +bSubset.get(0).getOwner()+"."+bSubset.get(0).getName()+"\n");
					}
				}
			}
		}
//		if(totalSubsetMatches == 0) {
//			b.append("		The Following Methods Don't Show Signs of Duplication: " +methodA.name +", "+ methodB.name +"\n");
//		}
		return b.toString();
	}
	
	
	private boolean myEquals(MyMethodInsn a, MyMethodInsn b) {
		if(a.getName().equals(b.getName()) && a.getOwner().equals(b.getOwner())) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "check DRY";
	}

}
