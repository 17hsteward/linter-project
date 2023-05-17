package JUnitTests;

import domain.Compiler;
import domain.MyClass;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class Helper {

    public static List<MyClass> getClasses(String packageName){
        JTextArea textArea = new JTextArea();
        Compiler c = new Compiler(textArea);
        File[] files=new File[]{new File("./src/test/java/filesToTest/"+packageName)};
        return c.read(files);
    }
    
}
