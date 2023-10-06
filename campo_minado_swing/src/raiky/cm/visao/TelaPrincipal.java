package raiky.cm.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import raiky.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {
	
	public TelaPrincipal() {
		
		Integer Q = 8;
		Tabuleiro Tab = new Tabuleiro(10, 10, Q);
		
		JPanel BordaDeCima = new JPanel();
		BordaDeCima.setPreferredSize(new Dimension(250,70));
		BordaDeCima.setBackground(Color.WHITE);
		
		JLabel QdMarcas = new JLabel("O campo possui "+Q.toString()+ " bombas, cuidado !!!");
		QdMarcas.setBounds(10,10,350,22); //TODO arrumar a posição
		QdMarcas.setBackground(Color.BLUE);
		
		/*JButton B =new JButton("New Game");
		B.setBackground(Color.GRAY);
		B.setPreferredSize(new Dimension(70,70));
		B.setBorder(BorderFactory.createBevelBorder(0));
		BordaDeCima.add(B);
		*/
		
		add(QdMarcas);
		add(BordaDeCima,BorderLayout.NORTH);
		//add(B,BorderLayout.NORTH);
		
	    add(new PainelTabuleiro(Tab),BorderLayout.CENTER);	
	    
		
		setTitle("Campo Minado");
		setVisible(true);
		setSize(390,450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		
		
	}
	
  
			
	public static void main(String[] args) {
		
		new TelaPrincipal();
	}
	
	
}
