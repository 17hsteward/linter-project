package presentation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;

import domain.Compiler;
import domain.MyClass;
import domain.UMLGenerator;

public class MainView {
	List<MyClass> myClasses;
	Compiler c;
	public MainView() {
		c=new Compiler();
		
		JFrame frame=new JFrame();
		frame.setTitle("main view");
		frame.setSize(960,540);
		JPanel p1=new JPanel();
		
		JLabel l1=new JLabel("please import java files");
		p1.add(l1);
		
		JButton b1=new JButton("import java files");
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser chooser=new JFileChooser("./");
		        chooser.setMultiSelectionEnabled(true);
		        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java Files", "java");
		        chooser.setFileFilter(filter);
		        chooser.showOpenDialog(null);
		    	File[] files=chooser.getSelectedFiles();
//		    	File[] files=new File[]{new File("./src/test/java/domain/Test.java")};//choose specific file to test accessing
//		    	File[] files=new File[]{new File("./")};
		    	
		    	try {
					myClasses=c.read(files);
				} catch (IOException exception) {
					// TODO Auto-generated catch block
					exception.printStackTrace();
				}
		    	l1.setText("files imported");
			}
			
		});
		p1.add(b1);
		
		JButton b2=new JButton("import text.java");
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		    	File[] files=new File[]{new File("./src/test/java/domain/Test.java")};//choose specific file to test accessing
//		    	File[] files=new File[]{new File("./")};
		    	try {
					myClasses=c.read(files);
				} catch (IOException exception) {
					// TODO Auto-generated catch block
					exception.printStackTrace();
				}
		    	l1.setText("test imported");
			}
			
		});
		p1.add(b2);
		
		JButton b3=new JButton("display UML code");
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(myClasses==null||myClasses.size()==0) {
					System.out.println("please import files first");
					l1.setText("please import files first");
				}else {
					//open UMLViewer
					UMLGenerator uml=new UMLGenerator(myClasses);
					String code=uml.generateAllUMLCode();
			    	System.out.println(code);
			    	l1.setText("UML printed");
			    	new UMLViewer(code);
				}
			}
			
		});
		p1.add(b3);
		
		frame.add(p1,BorderLayout.NORTH);
		frame.setVisible(true);
		
	}
}
