package JUnitTests;

import domain.Compiler;
import domain.MyClass;

import java.io.File;
import java.util.List;

public class Helper {

    public static List<MyClass> getClasses(String packageName){
        Compiler c = new Compiler();
        File[] files=new File[]{new File("./src/test/java/filesToTest/"+packageName)};
        return c.read(files);
    }
    
}
