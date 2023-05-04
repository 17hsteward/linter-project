package presentation;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class MarcusPrototype {
	public static void main(String[] args) throws IOException { 
		for (String className: args) {
			ClassReader reader = new ClassReader(className);
			ClassNode classNode = new ClassNode();
			reader.accept(classNode, ClassReader.EXPAND_FRAMES);
			String fullName = Type.getObjectType(classNode.name).getClassName();
			int nameStart = fullName.lastIndexOf('.');
			String nameNoPackages = fullName.substring(nameStart + 1);
			System.out.println("Name without packages: " + nameNoPackages);
			System.out.println("Does the class start with an upper case letter?" + Character.isUpperCase(nameNoPackages.charAt(0)));
			
		}
	}
}
// model classes properly
// moddel output properly
// supporting new style, pattern, and principle check
// three layers