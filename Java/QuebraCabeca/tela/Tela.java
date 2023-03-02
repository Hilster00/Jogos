package tela;


import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import pecas.*;
import java.awt.Color;
public class Tela extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private int vazio[] = new int[2];
	
	private JPanel contentPane;
	public int movimentos=0;
	public int jogo_rodando=1;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Tela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 381, 199);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(221, 234, 255));
		panel.setBounds(0, 0, 384, 160);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Quantidade de movimentos: 0");
		lblNewLabel_1.setBounds(170, 80, 214, 14);
		panel.add(lblNewLabel_1);
		
		JLabel quebra_cabeca[][] = new JLabel[4][4];
		Quebra_Cabeca pecas = new Quebra_Cabeca();
		vazio[0]=0;
		vazio[1]=0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {				
				JLabel peca= new JLabel();					
				peca.setBounds(j*40,i*40,40,40);
				
				peca.setIcon(new ImageIcon(Tela.class.getResource("/imagens/pc_"+pecas.pecas[i][j].getI()+"_"+pecas.pecas[i][j].getJ()+".png")));	
				if(pecas.pecas[i][j].getI()==0 && pecas.pecas[i][j].getJ()==0) {
					vazio[0]=j;
					vazio[1]=i;
				}
				
				quebra_cabeca[i][j]=peca;
			}
		}
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				panel.add(quebra_cabeca[i][j]);
			}
		}
		
		setResizable(false);
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(jogo_rodando==1) {
					if(e.getX()<=160 && e.getY()<=160) {
						int x=e.getX()/40;
						int y=e.getY()/40;
						int sinal=0;
						int linha_x=0,linha_y=0,disntancia_x=0,disntancia_y=0;
						
						if(x-1==vazio[0] ||x-2==vazio[0]||x-3==vazio[0] ||y-1==vazio[1] ||y-2==vazio[1]||y-3==vazio[1]){
	    					sinal=0;						    			
	    				}
	    				if(x+1==vazio[0] ||x+2==vazio[0]||x+3==vazio[0] ||y+1==vazio[1] ||y+2==vazio[1]||y+3==vazio[1]) {
	    					sinal=1;
	    				}
	    				if(y==vazio[1]) {
	    					linha_x=+(2*sinal-1);
	    						    					
	    				}else {
	    					linha_y=+(2*sinal-1);
	    					
	    				}
	    				disntancia_x=x-vazio[0];
	    				disntancia_y=y-vazio[1];
	    				if(disntancia_x<0) {
	    					disntancia_x=-disntancia_x;
    					}
	    				if(disntancia_y<0) {
	    					disntancia_y=-disntancia_y;
    					}
	    				
	    				
	    				quebra_cabeca[y][x].setIcon(new ImageIcon(Tela.class.getResource("/imagens/pc_0_0.png")));	
	    				quebra_cabeca[vazio[1]][vazio[0]].setIcon(new ImageIcon(Tela.class.getResource("/imagens/pc_"+pecas.pecas[y+linha_y*(disntancia_y-1)][x+linha_x*(disntancia_x-1)].getI()+"_"+pecas.pecas[y+linha_y*(disntancia_y-1)][x+linha_x*(disntancia_x-1)].getJ()+".png")));	
	    				
	    				pecas.pecas[vazio[1]][vazio[0]].setI(pecas.pecas[y+linha_y*(disntancia_y-1)][x+linha_x*(disntancia_x-1)].getI());
			    		pecas.pecas[vazio[1]][vazio[0]].setJ(pecas.pecas[y+linha_y*(disntancia_y-1)][x+linha_x*(disntancia_x-1)].getJ());
			    		 
						if((x!=vazio[0] && vazio[1]==y)||(vazio[0]==x && y!=vazio[1]) ) {
				    		if((x-1==vazio[0]||x+1==vazio[0]) || (y-1==vazio[1]||y+1==vazio[1])) {
				    			
				    			pecas.pecas[vazio[1]][vazio[0]].setI(pecas.pecas[y][x].getI());
				    			pecas.pecas[vazio[1]][vazio[0]].setJ(pecas.pecas[y][x].getJ());
				    			
				    			
				    		}else {
				    			quebra_cabeca[y+linha_y*1][x+linha_x*1].setIcon(new ImageIcon(Tela.class.getResource("/imagens/pc_"+pecas.pecas[y][x].getI()+"_"+pecas.pecas[y][x].getJ()+".png")));	
			    				
				    			if((x-2==vazio[0]||x+2==vazio[0]) || (y-2==vazio[1]||y+2==vazio[1])) {
				    				
				    				pecas.pecas[vazio[1]][vazio[0]].setI(pecas.pecas[y+linha_y*1][x+linha_x*1].getI());
						    		pecas.pecas[vazio[1]][vazio[0]].setJ(pecas.pecas[y+linha_y*1][x+linha_x*1].getJ());
						    		pecas.pecas[vazio[1]-linha_y*1][vazio[0]-linha_x*1].setI(pecas.pecas[y][x].getI());
						    		pecas.pecas[vazio[1]-linha_y*1][vazio[0]-linha_x*1].setJ(pecas.pecas[y][x].getJ());
						    		
				    				
				    			}else {				    			
				    				quebra_cabeca[y+linha_y*2][x+linha_x*2].setIcon(new ImageIcon(Tela.class.getResource("/imagens/pc_"+pecas.pecas[y+linha_y*1][x+linha_x*1].getI()+"_"+pecas.pecas[y+linha_y*1][x+linha_x*1].getJ()+".png")));	
				 
				    				pecas.pecas[vazio[1]][vazio[0]].setI(pecas.pecas[y+linha_y*2][x+linha_x*2].getI());
						    		pecas.pecas[vazio[1]][vazio[0]].setJ(pecas.pecas[y+linha_y*2][x+linha_x*2].getJ());
						    		pecas.pecas[vazio[1]-linha_y*1][vazio[0]-linha_x*1].setI(pecas.pecas[y+linha_y*1][x+linha_x*1].getI());
						    		pecas.pecas[vazio[1]-linha_y*1][vazio[0]-linha_x*1].setJ(pecas.pecas[y+linha_y*1][x+linha_x*1].getJ());
						    		pecas.pecas[vazio[1]-linha_y*2][vazio[0]-linha_x*2].setI(pecas.pecas[y][x].getI());
						    		pecas.pecas[vazio[1]-linha_y*2][vazio[0]-linha_x*2].setJ(pecas.pecas[y][x].getJ());
						    		
				    			}
				    		}
				    		pecas.pecas[y][x].setI(4);
			    			pecas.pecas[y][x].setJ(4);
			    			vazio[0]=x;
			    			vazio[1]=y;
			    			movimentos++;
				    		lblNewLabel_1.setText("Quantidade de movimentos: "+movimentos);
						}
						int verificacao=0;
						for(int i=0;i<4;i++) {
							for(int j=0;j<4;j++) {
								if(pecas.pecas[i][j].getI()!=i && pecas.pecas[i][j].getJ()!=j) {
									verificacao=1;
									break;
								}
							}
						}
						if(verificacao==0) {
							jogo_rodando=0;
						}
					}
				}
				
			}
		});
			
		
	}
}
