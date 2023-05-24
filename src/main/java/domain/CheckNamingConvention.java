package domain;

import java.util.Arrays;
import java.util.List;

import net.sourceforge.plantuml.argon2.blake2.Blake2b.Param;


public class CheckNamingConvention extends Check {

	@Override
	public String test(List<MyClass> myClasses) {
		// TODO Auto-generated method stub
		StringBuilder result = new StringBuilder();
		for(MyClass m : myClasses) {
			result.append("Package Naming:\n");
			result.append(checkPackageNaming(m));
			result.append("Class Naming:\n");
			result.append(checkClassNaming(m));
			result.append("Field Naming:\n");
			result.append(checkFieldNaming(m));
			result.append("Method Naming:\n");
			result.append(checkMethodNaming(m));
		}
		//
		return result.toString();
	}

	private String checkFieldNaming(MyClass m) {
		// TODO Auto-generated method stub
		StringBuilder b = new StringBuilder();
		for(MyField field : m.getConstants()) {
			if(!upperOrUnder(field.getName())) {
				b.append("\tConstant: "+ field.getName()+" should be all uppercase letters or underscore.\n");
			} else {
				b.append("\tConstant: "+ field.getName()+" GOOD.\n");
			}
		}
		for(MyField field : m.getFields()) {
			if(!mixedCase(field.getName())) {
				b.append("\tField: "+ field.getName()+" should start with a lowercase letter and each word after should only have the first letter capital.\n");
			} else {
				b.append("\tField: "+ field.getName()+" GOOD.\n");
			}
		}
		return b.toString();
	}

	private boolean upperOrUnder(String param) {
		// TODO Auto-generated method stub
		for  (int i = 0; i < param.length(); i++) {
			if((!Character.isUpperCase(param.charAt(i)) && !(param.charAt(i) =='_'))) {
				return false;
			}
		} 
		return true; 
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Check Naming Convention";
	}
	
	private String checkPackageNaming(MyClass myclass) {
		StringBuilder result = new StringBuilder();
		List<String> packNames = Arrays.asList(myclass.packageName.split("."));
		for(String packname : packNames) {
			if (!Character.isLowerCase(packname.charAt(0))) {
				result.append("		Package Name {" + packname + "} should start with a lower case character.\n");
			}
			if (!packname.equals(packname.toLowerCase())) {
				result.append("\tPackage Name {" + packname + "} should be all lower case.\n");
			}
		}
		if (result.toString().isEmpty()) {
			result.append("		GOOD: All package names are lower case!\n");
		}
		return result.toString();
	}
	
	private String checkClassNaming(MyClass myclass) {
		StringBuilder result = new StringBuilder();
		if (!Character.isUpperCase(myclass.className.charAt(0))) {
			result.append("		Class Name {" + myclass.className + "} should start with an upper case character.\n");
		} else {
			result.append("		GOOD: Class name starts with an upper case character!\n");
		}
		return result.toString();
	}
	
	private String checkMethodNaming(MyClass myclass) {
		List<MyMethod> meths = myclass.methods;
		StringBuilder b = new StringBuilder();
		for (MyMethod m: meths) {
			if(!m.getName().equals("<init>")) {
				b.append("\tMethod Name: " + m.name + "\n");
				if(!checkForMixedCasing(m.name)) {
					b.append("\t\tFailed: Method names should start with a lowercase letter and each word after should only have the first letter capital.\n");
				} else {
					b.append("\t\tPassed: Method name is good.\n");
				}
			}
		}
		return b.toString();
	}
	
	private boolean checkForMixedCasing(String param) {
		return startLowerCase(param) && mixedCase(param);
	}
	
	private boolean startLowerCase(String param) {
		return Character.isLowerCase(param.charAt(0));
	}
	
	private boolean mixedCase(String param) {
		for  (int i = 0; i < param.length(); i++) {
			if(Character.isUpperCase(param.charAt(i))) {
				if(i == param.length()-1) {
					return false;
				} else if(Character.isUpperCase(param.charAt(i+1))) {
					return false;
				}
			}
		} 
		return true;
	}

}