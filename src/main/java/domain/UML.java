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
		int i=0;
		char[] array=temp.toCharArray();
		for(int j=0;j<array.length;j++) {
			char c=array[j];
			if(c=='<') {
				i++;
			}else if(c=='>') {
				i--;
			}else if(c==';'&&i!=0) {
				array[j]=',';
			}
		}
		temp=String.valueOf(array);
		
		temp=temp.replaceAll("Ljava/util/List<", "List<").replaceAll("Ljava/util/Map<", "Map<").replaceAll(",>",">");
		
		for(String s:temp.split(";")) {
			if(s.isBlank()) {
				continue;
			}
			while(true) {
				if(s.startsWith("B")) {
					l.add("boolean");
				}else if(s.startsWith("C")) {
					l.add("char");
				}else if(s.startsWith("D")) {
					l.add("double");
				}else if(s.startsWith("F")) {
					l.add("float");
				}else if(s.startsWith("I")) {
					l.add("int");
				}else if(s.startsWith("J")) {
					l.add("long");
				}else if(s.startsWith("S")) {
					l.add("short");
				}else if(s.startsWith("V")) {
					l.add("void");
				}else if(s.startsWith("Z")) {
					l.add("boolean");
				}else {
					break;
				}
				s=s.substring(1);
			}
			//BCDFIJSVZ
			s=replace(s);
			l.add(s);
		}
		
		
		return l;
	}
	public static String returnFromSignature(String signature) {
		String s=signature.substring(signature.lastIndexOf(")")+1);
		s=s.replaceAll(";>",">").replaceAll(";",",");
		s=replace(s);
		return typeConvert(s);
	}
	
	public static String replace(String in) {
		String s=in;
		Map<String,String> types=new HashMap<>();
		types.put("Ljava/util/List<", "List<");
		types.put("Ljava/util/Map<", "Map<");
		types.put("Ljava/lang/String","String");
		types.put("Ljava/lang/Integer","Integer");
		types.put("Ljava/io/File","File");
		types.put("Ljava.util.List<", "List<");
		types.put("Ljava.util.Map<", "Map<");
		types.put("Ljava.lang.String","String");
		types.put("Ljava.lang.Integer","Integer");
		types.put("Ljava.io.File","File");
		for(String s1:types.keySet()) {
			s=s.replaceAll(s1, types.get(s1));
		}
		return s;
	}
	
	public static String typeConvert(String type) {
		type=replace(type);
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
		if(types.containsKey(type)) {
			return types.get(type);
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
