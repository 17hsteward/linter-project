package domain;

import java.util.Arrays;
import java.util.List;

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
		return "";
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
				result.append("		Package Name {" + packname + "} should start with a lower case character./n");
			}
		}
		if (result.toString().isEmpty()) {
			result.append("		GOOD: All package names are lower case!/n");
		}
		return "";
	}
	
	private String checkClassNaming(MyClass myclass) {
		StringBuilder result = new StringBuilder();
		if (!Character.isUpperCase(myclass.className.charAt(0))) {
			result.append("		Package Name {" + myclass.className + "} should start with an upper case character.\n");
		} else {
			result.append("		GOOD: Class name starts with an upper case character!\n");
		}
		return result.toString();
	}
	
	private String checkMethodNaming(MyClass myclass) {
		return "";
	}
	
	private String checkConstantsNaming(MyClass myclass) {
		return  "";
	}

}
