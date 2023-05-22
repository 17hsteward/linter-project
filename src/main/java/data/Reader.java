package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Reader {
	public File getClassFromJava(String javaPath) {
		String classPath=javaPath.replace(".java", ".class");
        return new File(classPath);
	}
	
	public void deleteClassFromJava(String javaPath) {
		this.getClassFromJava(javaPath).deleteOnExit();
	}
	
	public List<String> getCode(File file){
		List<String> a=new ArrayList<>();
		Scanner s;
		try {
			s = new Scanner(file);
			while(s.hasNextLine()) {
		    	a.add(s.nextLine());
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
}
