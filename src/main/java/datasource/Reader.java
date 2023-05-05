package datasource;

import java.io.File;

public class Reader {
	public File getClassFromJava(String javaPath) {
		String classPath=javaPath.replace(".java", ".class");
        File classFile=new File(classPath);
        return classFile;
	}
}
