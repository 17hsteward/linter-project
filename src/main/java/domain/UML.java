package domain;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public interface UML {
	public abstract String toUML();
	
	public static List<String> inputFromSignature(String signature) {
		List<String> l=new LinkedList<String>();
		String temp=signature.substring(1,signature.lastIndexOf(")"));
		temp=temp.replaceAll("Ljava/util/List<", "List<").replaceAll("Ljava/util/Map<", "Map<").replaceAll(";>",">");
		
		for(String s:temp.split(";")) {
			if(s.isBlank()) {
				continue;
			}
			
			if(s.contains("/")) {
				s=typeConvert(s);
			}else {
				for(char c:s.toCharArray()) {
					l.add(String.valueOf(c));
				}
				continue;
			}
			if(s.equals("List<")) {
				//add list type
				s+=">";
			}
			l.add(s);
		}
		
		
		return l;
	}
	public static String returnFromSignature(String signature) {
		String s=signature.substring(signature.lastIndexOf(")")+1);
		s=s.replaceAll(";>",">").replaceAll(";",",");
		
		Map<String,String> types=new HashMap<>();
		types.put("Ljava/util/List<", "List<");
		types.put("Ljava/util/Map<", "Map<");
		types.put("Ljava/lang/String","String");
		types.put("Ljava/lang/Integer","Integer");
		types.put("Ljava/io/File","File");
		for(String s1:types.keySet()) {
			s=s.replaceAll(s1, types.get(s1));
		}
		
		return typeConvert(s);
	}
	
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
		types.put("Ljava/util/List;","List<>");
		types.put("Ljava/util/List","List<");
		types.put("Ljava/util/Map;","Map<>");
		types.put("Ljava/util/Map","Map<");
		types.put("Ljava/lang/Integer;","Integer");
		types.put("Ljava/lang/Integer","Integer");
		types.put("Ljava/io/File;","File");
		types.put("Ljava/io/File","File");
		if(types.containsKey(type)) {
			return types.get(type);
		}
		
		
		
		if(type.startsWith("Ljava/util/List<")) {
			type=type.replaceAll("Ljava.util.List<", "");
			type=type.replaceAll(";>",";");
			type="List<"+typeConvert(type)+">";
		}
		if(type.startsWith("Ljava/util/Map<")) {
			type=type.replaceAll("Ljava.util.Map<", "");
			type=type.replaceAll(";>",";");
			type="Map<"+typeConvert(type)+">";
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
