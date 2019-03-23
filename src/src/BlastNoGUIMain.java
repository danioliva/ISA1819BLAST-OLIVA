package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import blast.BlastController;

public class BlastNoGUIMain {

	private static final String dataBaseFile = new String("resources/yeast.aa");
	private static final String dataBaseIndexes = new String("resources/yeast.aa.indexs");


	public static void main(String args[]){
		BlastController bCnt = new BlastController();
		try{
			//Creamos el jframe al que le iremos añadiendo las respectivas utilidades requeridas
			JFrame jfr=new JFrame("BlastNoGui");
			jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jfr.setVisible(true);
			jfr.setSize(500, 500);



			//Creación de los dos jpanels en los que dividiremos el jframe inicial
			//JPanel 1
			JPanel p1=new JPanel();
			p1.setLayout(new FlowLayout());
			//Creacion de etiqueta y situacion dentro del panel
			JLabel label=new JLabel("Escoja el tipo de secuencia: ");
			label.setSize(20, 20);
			Font fuente=new Font("TimesRoman", Font.BOLD, 12);
			label.setFont(fuente);
			p1.add(label,BorderLayout.WEST);

			//Creacion de los botones para escoger el tipo de secuencia
			//Boton de proteinas
			JRadioButton jBPro=new JRadioButton("PROTEÍNAS");
			jBPro.setBackground(Color.CYAN);
			jBPro.setBorderPainted(true);
			//Boton de nucleotidos
			JRadioButton jBNuc=new JRadioButton("NUCLEÓTIDOS");
			jBNuc.setBackground(Color.YELLOW);
			jBNuc.setBorderPainted(true);
			//Situacion de los botones
			p1.add(jBPro,BorderLayout.EAST);
			p1.add(jBNuc,BorderLayout.EAST);
			
			//El siguiente codigo representa que si pulsamos un boton, el otro no puede estar activado
			jBPro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(jBPro.isSelected()) {
						jBNuc.setSelected(false);
					}
				}
			});
			
			jBNuc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(jBNuc.isSelected()) {
						jBPro.setSelected(false);
					}
				}
			});


			//JPanel 2
			JPanel p2=new JPanel();
			p2.setLayout(new FlowLayout());



			jfr.add(p1,BorderLayout.NORTH);

		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
