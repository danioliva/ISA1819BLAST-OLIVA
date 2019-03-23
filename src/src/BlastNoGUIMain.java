package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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

			//Creación de los dos jpanels en los que dividiremos el jframe inicial
			//JPanel 1
			JPanel p1=new JPanel();
			p1.setLayout(new FlowLayout());
			p1.setBackground(Color.YELLOW);
			//Creacion de etiqueta y situacion dentro del panel
			JLabel label=new JLabel("Escoja el tipo de secuencia: ");
			label.setSize(20, 20);
			//Creamos una fuente para las etiquetas
			Font fuente=new Font("TimesRoman", Font.BOLD, 20);
			label.setFont(fuente);


			//Creacion de los botones para escoger el tipo de secuencia
			//Boton de proteinas
			JRadioButton jBPro=new JRadioButton("PROTEÍNAS");
			jBPro.setBackground(Color.CYAN);
			jBPro.setBorderPainted(true);
			//Boton de nucleotidos
			JRadioButton jBNuc=new JRadioButton("NUCLEÓTIDOS");
			jBNuc.setBackground(Color.CYAN);
			jBNuc.setBorderPainted(true);
			//Situacion de los botones y la etiqueta
			p1.add(label,BorderLayout.WEST);
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
			p2.setVisible(true);
			p2.setBackground(Color.YELLOW);

			//Etiqueta de la secuencia
			JLabel j2=new JLabel("Introduzca aquí la secuencia: ");
			j2.setFont(fuente);

			//ComboBox donde introducir la secuencia a buscar			
			JComboBox<String> cSec=new JComboBox<String>();
			cSec.setEditable(true); //Decimos que es editable para que así se pueda sobreescribir en él 
			p2.add(j2);
			p2.add(cSec);

			//Esto nos permite recuperar algo ya introducido
			cSec.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String seqq= cSec.getEditor().getItem().toString();
					cSec.addItem(seqq);
				}
			});

			//Etiqueta del porcentaje con el que buscar
			JLabel j3=new JLabel("Introduzca el porcentaje: ");
			j3.setFont(fuente);

			//Cuadro de texto del porcentaje
			JTextArea porc=new JTextArea("PORCENTAJE",1,2);
			porc.setEditable(true);
			p2.add(j3,BorderLayout.EAST);
			p2.add(porc,BorderLayout.WEST);

			//Boton de busqueda de resultado
			JButton bResul=new JButton("Pulse para resultado");
			bResul.setBackground(Color.CYAN);
			bResul.setBorderPainted(true);
			//Cuadro de texto del resultado
			JTextArea jtResul=new JTextArea("Aquí aparecerá su resultado",20,28);
			jtResul.setEditable(false);
			//ScrollPane para facilitar la lectura del resultado
			JScrollPane jSec=new JScrollPane(jtResul);

			//Este actionListener nos permite realizar la búsqueda una vez se pulse el boton
			bResul.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String t="Proteins";
					if(jBNuc.isSelected()) {
						t="Nucleotids";
					}
					float porcentaje=Float.parseFloat(porc.getText());
					String secuencia=cSec.getSelectedItem().toString();
					try {
						if(t=="Proteins") {
							String r = bCnt.blastQuery('p', dataBaseFile,dataBaseIndexes, porcentaje, secuencia);
							jtResul.setText(r);
						}else {
							String r = bCnt.blastQuery('n', dataBaseFile,dataBaseIndexes, porcentaje, secuencia);
							jtResul.setText(r);
						}
					}catch(Exception a) {
						jtResul.setText("No existen coincidencias");
					}
				}
				
			});
			//Añadimos el Scroll y el boton resultado al panel 2
			p2.add(jSec,BorderLayout.SOUTH);
			p2.add(bResul, BorderLayout.SOUTH);
			//Añadimos los paneles al jFrame inicial
			jfr.add(p1,BorderLayout.NORTH);
			jfr.add(p2, BorderLayout.CENTER);
			jfr.setVisible(true);
			jfr.setSize(500, 500);

		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
