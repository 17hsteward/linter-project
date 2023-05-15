package domain;

import java.util.Map;
import java.util.HashMap;

public interface UML {
	public abstract String toUML();
	public static String typeConvert(String type) {
		if(type.charAt(0)=='[') {
			return typeConvert(type.substring(1))+"[]";
		}
		Map<String,String> types=new HashMap<>();
		types.put("I","int");
		types.put("Z","boolean");
		types.put("F","float");
		types.put("D", "double");
		types.put("J","long");
		types.put("V","void");
		types.put("C","char");
		types.put("B","byte");
		types.put("S","short");
		types.put("Ljava/lang/String;","String");
		types.put("Ljava/lang/String","String");
		types.put("Ljava/util/List;","List<>");//
		types.put("Ljava/util/List","List<");//
		types.put("Ljava/lang/Integer;","Integer");
		types.put("Ljava/lang/Integer","Integer");
		types.put("Ljava/io/File;","File");
		types.put("Ljava/io/File","File");
//		types.put("java/lang/String;","String");
//		types.put("java/lang/String","String");
//		types.put("java/util/List;","List<>");//
//		types.put("java/util/List","List<");//
//		types.put("java/lang/Integer;","Integer");
//		types.put("java/lang/Integer","Integer");
//		types.put("java/io/File;","File");
//		types.put("java/io/File","File");
		if(types.containsKey(type)) {
			return types.get(type);
		}
		
		if(type.startsWith("Ljava/util/List<")) {
			type=type.replaceFirst("Ljava.util.List<", "");
			type=type.replaceAll(">","");
			type="List<"+typeConvert(type)+">";
		}
		type=type.replaceAll(";", ",").replaceAll("/",".");
		if(type.charAt(type.length()-1)==',') {
			type=type.substring(0,type.length()-1);
		}
		if(type.charAt(0)=='L'&&!type.startsWith("List")) {
			type=type.substring(1);
		}
		return type;
	}
}
