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
		types.put("Ljava/lang/String;","string");
		types.put("Ljava/util/List;","List<>");
		types.put("Ljava/lang/Integer;","Integer");
		if(types.containsKey(type)) {
			return types.get(type);
		}
		return type;
	}
}
