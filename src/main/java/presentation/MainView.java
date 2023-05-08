package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;

import domain.*;
import domain.Compiler;
import domain.MyClass;
import domain.UMLGenerator;

public class MainView {
	List<MyClass> myClasses;
	Compiler c;
	List<Check> checks;
	public MainView() {
		this.checks=new LinkedList<>();
		this.checks.add(new CheckAccessModifier());
		this.checks.add(new CheckMethodChaining());
		this.checks.add(new CheckObserverPattern());
		this.checks.add(new CheckDataClass());
		this.checks.add(new CheckAbstractInstance());
		this.checks.add(new CheckHollyWoodPrinciple());
		this.checks.add(new CheckAdapterPattern());

		this.c=new Compiler();
		JFrame frame=new JFrame();
		frame.setTitle("main view");
		frame.setSize(1440,540);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		JPanel p1=new JPanel();
		frame.add(p1,BorderLayout.SOUTH);
		JPanel p2=new JPanel();
		p2.setLayout(new FlowLayout());
		frame.add(p2,BorderLayout.NORTH);
		
		JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);
		frame.add(scrollPane);
		
		
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
		    	l1.setText("loading");
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
		
		JButton b2=new JButton("import src/test/java/domain/Test.java");
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				l1.setText("loading");
		    	File[] files=new File[]{new File("./src/test/others/domain/Test.java")};//choose specific file to test accessing
//		    	File[] files=new File[]{new File("./")};
		    	try {
					myClasses=c.read(files);
				} catch (IOException exception) {
					// TODO Auto-generated catch block
					exception.printStackTrace();
				}
		    	l1.setText("test file imported");
			}
			
		});
		p1.add(b2);
		
		JButton b2Linter=new JButton("import this project");
		b2Linter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				l1.setText("loading");
//		    	File[] files=new File[]{new File("./src/test/java/domain/Test.java")};//choose specific file to test accessing
		    	File[] files=new File[]{new File("./src/main/java")};
		    	try {
					myClasses=c.read(files);
				} catch (IOException exception) {
					// TODO Auto-generated catch block
					exception.printStackTrace();
				}
		    	l1.setText("test file imported");
			}
			
		});
		p1.add(b2Linter);
		
		JButton b3=new JButton("print UML code in console");
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(myClasses==null||myClasses.size()==0) {
					l1.setText("please import files first");
				}else {
					//open UMLViewer
					UMLGenerator uml=new UMLGenerator(myClasses);
					String code=uml.generateAllUMLCode();
			    	System.out.println(code);
			    	l1.setText("UML printed");
			    	textArea.setText(code);
				}
			}
			
		});
		p1.add(b3);
		
		JButton b4=new JButton("display UML code in window");
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(myClasses==null||myClasses.size()==0) {
					l1.setText("please import files first");
				}else {
					//open UMLViewer
					UMLGenerator uml=new UMLGenerator(myClasses);
					String code=uml.generateAllUMLCode();
			    	l1.setText("UML displayed");
			    	new UMLViewer(code);
				}
			}
			
		});
		p1.add(b4);
		
		JLabel l2=new JLabel("select checks:        select all");
		p2.add(l2);
		JCheckBox all=new JCheckBox();
		p2.add(all);
		List<JCheckBox> checkBoxes=new LinkedList<>();
		all.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				for(JCheckBox check:checkBoxes) {
					check.setSelected(all.isSelected());
				}
			}
			
		});
		
		for(Check check:checks) {
			JLabel boxLabel=new JLabel("    "+check.getName());
			JCheckBox box=new JCheckBox();
			checkBoxes.add(box);
			p2.add(boxLabel);
			p2.add(box);
		}
		JButton start=new JButton("start");
		p2.add(start);
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String checkResult="";
				for(int i=0;i<checkBoxes.size();i++) {
					if(checkBoxes.get(i).isSelected()) {
						checkResult+=checks.get(i).getName()+":\n"+checks.get(i).test(myClasses)+"\n";
					}
				}
				textArea.setText(checkResult);
			}
			
		});
		
		
		frame.setVisible(true);
		
	}
}
