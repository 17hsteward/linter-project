package presentation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;

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
		//rescale
		Image i2=i.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		
		JLabel l=new JLabel(i3);
		p.add(l);
		frame.add(p);
		
		frame.setVisible(true);
	}
}
