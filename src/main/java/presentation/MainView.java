package presentation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.List;
import java.util.LinkedList;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;

import domain.*;
import domain.Compiler;

public class MainView {
	List<MyClass> myClasses;
	Compiler c;
	List<Check> checks;
	JTextArea textArea;
	public MainView() {
		this.checks=new LinkedList<>();
		//add checks here
		this.checks.add(new CheckAccessModifier());
		this.checks.add(new CheckMethodChaining());
		this.checks.add(new CheckObserverPattern());
		this.checks.add(new CheckDataClass());
		this.checks.add(new CheckAbstractInstance());
		this.checks.add(new CheckHollyWoodPrinciple());
		this.checks.add(new CheckAdapterPattern());
		this.checks.add(new CheckCouplingCohesion());
		this.checks.add(new CheckThreeLayer());
		this.checks.add(new CheckNamingConvention());
//		this.checks.add(new CheckTemplatePattern());

		this.c=new Compiler();
		JFrame frame=new JFrame();
		frame.setTitle("linter");
		frame.setSize(1440,810);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel p1=new JPanel();
		frame.add(p1,BorderLayout.EAST);
		JPanel p2=new JPanel();
		frame.add(p2,BorderLayout.WEST);
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		JPanel p3=new JPanel();
		frame.add(p3);
		
		textArea = new JTextArea(45,80);
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);
		p3.add(scrollPane);
		
		JLabel l1=new JLabel("please import java files");
		p1.add(l1);
		
		JButton b0=new JButton("import java files");
		b0.addActionListener(new ActionListener() {

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
		    	myClasses=c.read(files);
		    	l1.setText("files imported");
		    	showClasses();
			}
			
		});
		p1.add(b0);
		
		JButton b1=new JButton("import src/test/java/domain/Test.java");
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		    	File[] files=new File[]{new File("./src/test/java/others/domain/Test.java")};//choose specific file to test accessing
				myClasses=c.read(files);
		    	l1.setText("test file imported");
		    	showClasses();
			}
			
		});
		p1.add(b1);
		
		JButton b2=new JButton("import this linter project");
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		    	File[] files=new File[]{new File("./src/main/java")};
		    	myClasses=c.read(files);
		    	l1.setText("test file imported");
		    	showClasses();
			}
			
		});
		p1.add(b2);
		
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
		
		JButton b4=new JButton("display UML in new window");
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
		
		JButton b5=new JButton("show class names");
		b5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showClasses();
			}
			
		});
		p1.add(b5);
		
		p2.add(new JLabel("select checks:"));
		List<JCheckBox> checkBoxes=new LinkedList<>();
		
		for(Check check:checks) {
			JPanel subPanel=new JPanel();
			JLabel boxLabel=new JLabel("    "+check.getName());
			JCheckBox box=new JCheckBox();
			checkBoxes.add(box);
			subPanel.add(boxLabel);
			subPanel.add(box);
			p2.add(subPanel);
		}
		
		p2.add(new JLabel("select all"));
		JCheckBox all=new JCheckBox();
		p2.add(all);
		all.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				for(JCheckBox check:checkBoxes) {
					check.setSelected(all.isSelected());
				}
			}
			
		});
		JButton start=new JButton("start");
		p2.add(start);
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String checkResult="";
				if(myClasses==null) {
					textArea.setText("please load files first");
					return;
				}
				for(int i=0;i<checkBoxes.size();i++) {
					if(checkBoxes.get(i).isSelected()) {
						checkResult+=checks.get(i).getName()+":\n"+checks.get(i).test(myClasses)+"\n\n";
					}
				}
				textArea.setText(checkResult);
			}
			
		});
		
		frame.setVisible(true);
		
	}
	
	private void showClasses() {
		String s="";
		if(this.myClasses==null) {
			return;
		}
		for(MyClass c:this.myClasses) {
			s+=c.getName()+"\n";
		}
		this.textArea.setText(s);
	}
}
