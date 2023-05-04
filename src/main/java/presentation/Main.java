package presentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import domain.MyClass;

public class Main {

    public static void main(String[] args) throws IOException {
    	List<MyClass> myClasses=new LinkedList<>();
    	//choose a java file to compile
    	JFileChooser chooser=new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java Files", "java");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
    	File[] files=chooser.getSelectedFiles();
    	
    	for(File file:files) {
    		System.out.println(file.getAbsolutePath());
    		
    		JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            int result = javac.run(null, null, null, file.getAbsolutePath());
            if(result!=0) {
            	return;
            }
            
            String className=file.getAbsolutePath().replace(".java", ".class");
            File from=new File(className);
    		File to=new File("./src/main/resources/"+from.getName());
    		
    		System.out.println("create class file in "+to.getAbsolutePath());
    		try {
        		FileInputStream fi = new FileInputStream(from);
    	    	FileOutputStream fo = new FileOutputStream(to);
    	    	Integer by = 0;
    	    	while((by = fi.read()) != -1) {
    			    fo.write(by);
    		    }
    		    fi.close();
    		    fo.close();
        	}catch(Exception e1) {
        		;
        	}
    		from.delete();
    		
            InputStream in = null;
    		try {
    			in = new FileInputStream(to);
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

    		ClassReader reader=new ClassReader(in);

    		// Step 2. ClassNode is just a data container for the parsed class
    		ClassNode classNode = new ClassNode();
        	
    		reader.accept(classNode, ClassReader.EXPAND_FRAMES);
    		
    		printClass(classNode);

			printFields(classNode);
			
			printMethods(classNode);
			
			MyClass mc=new MyClass(classNode);
			myClasses.add(mc);
    	}
    	
    }

    //code from MyFirstLinter for test input validity
    private static void printClass(ClassNode classNode) {
		System.out.println("Class's Internal JVM name: " + classNode.name);
		System.out.println("User-friendly name: "
				+ Type.getObjectType(classNode.name).getClassName());
		System.out.println("public? "
				+ ((classNode.access & Opcodes.ACC_PUBLIC) != 0));
		System.out.println("Extends: " + classNode.superName);
		System.out.println("Implements: " + classNode.interfaces);
		// TODO: how do I write a lint check to tell if this class has a bad name?
	}
    
    private static void printFields(ClassNode classNode) {
		// Print all fields (note the cast; ASM doesn't store generic data with its Lists)
		List<FieldNode> fields = (List<FieldNode>) classNode.fields;
		for (FieldNode field : fields) {
			System.out.println("	Field: " + field.name);
			System.out.println("	Internal JVM type: " + field.desc);
			System.out.println("	User-friendly type: "
					+ Type.getObjectType(field.desc).getClassName());
			// Query the access modifiers with the ACC_* constants.

			System.out.println("	public? "
					+ ((field.access & Opcodes.ACC_PUBLIC) != 0));
			// TODO: how do you tell if something has package-private access? (ie no access modifiers?)
			
			// TODO: how do I write a lint check to tell if this field has a bad name?
			
			System.out.println();
		}
	}

	private static void printMethods(ClassNode classNode) {
		List<MethodNode> methods = (List<MethodNode>) classNode.methods;
		for (MethodNode method : methods) {
			System.out.println("	Method: " + method.name);
			System.out
					.println("	Internal JVM method signature: " + method.desc);

			System.out.println("	Return type: "
					+ Type.getReturnType(method.desc).getClassName());

			System.out.println("	Args: ");
			for (Type argType : Type.getArgumentTypes(method.desc)) {
				System.out.println("		" + argType.getClassName());
				// FIXME: what is the argument's *variable* name?
			}

			System.out.println("	public? "
					+ ((method.access & Opcodes.ACC_PUBLIC) != 0));
			System.out.println("	static? "
					+ ((method.access & Opcodes.ACC_STATIC) != 0));
			// How do you tell if something has default access? (ie no access modifiers?)

			System.out.println();

			// Print the method's instructions
			printInstructions(method);
		}
	}
	
	private static void printInstructions(MethodNode methodNode) {
		InsnList instructions = methodNode.instructions;
		for (int i = 0; i < instructions.size(); i++) {

			// We don't know immediately what kind of instruction we have.
			AbstractInsnNode insn = instructions.get(i);

			// FIXME: Is instanceof the best way to deal with the instruction's type?
			if (insn instanceof MethodInsnNode) {
				// A method call of some sort; what other useful fields does this object have?
				MethodInsnNode methodCall = (MethodInsnNode) insn;
				System.out.println("		Call method: " + methodCall.owner + " "
						+ methodCall.name);
			} else if (insn instanceof VarInsnNode) {
				// Some some kind of variable *LOAD or *STORE operation.
				VarInsnNode varInsn = (VarInsnNode) insn;
				int opCode = varInsn.getOpcode();
				// See VarInsnNode.setOpcode for the list of possible values of
				// opCode. These are from a variable-related subset of Java
				// opcodes.
			}
			// There are others...
			// This list of direct known subclasses may be useful:
			// http://asm.ow2.org/asm50/javadoc/user/org/objectweb/asm/tree/AbstractInsnNode.html
			
			// TODO: how do I write a lint check to tell if this method has a bad name?
		}
	}
}
