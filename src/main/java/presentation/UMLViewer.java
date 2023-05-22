package presentation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import javax.swing.*;
import net.sourceforge.plantuml.SourceStringReader;

public class UMLViewer {
	public UMLViewer(String code) {
		//https://plantuml.com/api
		JFrame frame=new JFrame("UML viewer");
		frame.setTitle("UML view");
		frame.setSize(1440,810);
		OutputStream png = null;
		SourceStringReader reader = new SourceStringReader(code);
		// Write the first image to "png"
		try {
			png = new FileOutputStream(new File("./src/main/resources/uml.png"));
			String desc = reader.outputImage(png).getDescription();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel p=new JPanel();
		ImageIcon i=new ImageIcon("./src/main/resources/uml.png");
		
		JLabel l=new JLabel(i);
		p.add(l);
		
		JScrollPane scrollPane = new JScrollPane(p);
		frame.add(scrollPane);
		
		frame.setVisible(true);
	}
}
