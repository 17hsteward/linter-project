package data;

import java.io.File;

public class Reader {
	public File getClassFromJava(String javaPath) {
		String classPath=javaPath.replace(".java", ".class");
        return new File(classPath);
	}
}
