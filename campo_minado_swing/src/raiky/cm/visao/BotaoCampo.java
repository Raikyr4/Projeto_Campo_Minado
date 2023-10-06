package raiky.cm.visao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import raiky.cm.modelo.Campo;
import raiky.cm.modelo.CampoEvento;
import raiky.cm.modelo.CampoObs;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObs, MouseListener{

	private final Color BG_PADRAO = new Color(184,184,184); 
	private final Color BG_MARCADO = new Color(8,179,247);
	private final Color BG_EXPLODIR = new Color(189,66,68);
	private final Color TEXTO_VERDE = new Color(0,100,0);
	
	private Campo campo;
	
	public BotaoCampo(Campo campo) {
		
		this.campo = campo;
		
		setBackground(BG_PADRAO);
		setOpaque(true);
		setBorder(BorderFactory.createBevelBorder(0));
		setSize(new Dimension(10,10));
		addMouseListener(this);
		campo.registrarOBS(this);
	}
	@Override
	public void eventoOcorreu(Campo c, CampoEvento E) {
        switch(E) {
        case ABRIR:
        	aplicarEstiloAbrir();
        	break;
        case MARCAR:
        	aplicarEstiloMarcar();
        	break;
        case EXPLODIR:
        	aplicarEstiloExplodir();
        	break;
        default:
        	aplicarEstiloPadrao();
        }
		SwingUtilities.invokeLater(()->{
			repaint();
			validate();
		});
	}
	 
	public void aplicarEstiloPadrao() {
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		setText("");
	}
	private void aplicarEstiloExplodir() {
		setForeground(Color.WHITE);
		setBackground(BG_EXPLODIR);
		setText("X");
	}
	private void aplicarEstiloMarcar() {
		setBackground(BG_MARCADO);
		setForeground(Color.BLACK);
		setText("?");
	}
	private void aplicarEstiloAbrir() {
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		if(campo.isMinado()) {
			setBackground(BG_EXPLODIR);
			setForeground(Color.BLACK);
			setText("X");
			return;
		}
		setBackground(BG_PADRAO);
		
			 switch (campo.minasNaVizinhanca()) {
			case 1: 
				setForeground(TEXTO_VERDE);
				break;
			case 2:
				setForeground(Color.BLUE);
				break;
			case 3:
				setForeground(Color.YELLOW);
				break;
			case 4:
			case 5:
			case 6:
				setForeground(Color.RED);
				break;
			
			default:
				setForeground(Color.PINK);
				
			}
		 String valor = !campo.vizinhancaSegura() ? campo.minasNaVizinhanca() +"" : "";
		 setText(valor);
		
	}
	
	//Interface dos eventos do mouse
	
	@Override
	public void mousePressed(MouseEvent e) {
	    
		if(e.getButton()== 1) {
			campo.abrir();
		}else {
		 campo.alternarMarcacao();
		}
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	
	
	
	
}
