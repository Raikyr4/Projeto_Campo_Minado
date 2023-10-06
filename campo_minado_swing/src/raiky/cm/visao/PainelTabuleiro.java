package raiky.cm.visao;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import raiky.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel{

	public PainelTabuleiro(Tabuleiro X) {
		
		setLayout(new GridLayout(X.getLinhas(),X.getColunas()));
	
		X.ForEach(C->add(new BotaoCampo(C)));
		X.registrarObservador(e->{
			
			SwingUtilities.invokeLater(()->{
				if(e.isGanhou()) {
				JOptionPane.showMessageDialog(this, "GANHOU !! :)");
			}else {
				JOptionPane.showMessageDialog(this, "PERDEU !! :(");
			}
			X.reiniciar();
			});
		});
	}
	
}
