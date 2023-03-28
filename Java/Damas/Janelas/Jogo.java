package Janelas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Pecas.MovimentosNormais;
import src.Janela;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Jogo extends JFrame {

	private JPanel contentPane;
	private int vez=2,selecao=0,c_x=0,c_y=0;
	
	public Jogo(int tamanho,String cor1,String cor2,String cor3,String cor4) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, tamanho*80+16, tamanho*80+39);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, tamanho*80+80, tamanho*80+80);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel casas[][] = new JLabel[tamanho][tamanho];
		int tabuleiro[][] = new int[tamanho][tamanho];
		int movimentos[][] = new int[tamanho][tamanho];
		for(int i=0;i<tamanho;i++) {
			for(int j=0;j<tamanho;j++) {
				tabuleiro[i][j]=0;
				movimentos[i][j]=0;
			}
		}
		
		int quantidade_pecas;
		
		if(tamanho==6) {
			quantidade_pecas=6;
		}else {
			quantidade_pecas=12;
		}
		
		JLabel pecas_1[][] = new JLabel[tamanho][tamanho];
		JLabel pecas_2[][] = new JLabel[tamanho][tamanho];
		JLabel destino[][] = new JLabel[tamanho][tamanho];
		
		JLabel casa_selecionada = new JLabel();
		casa_selecionada.setIcon(new ImageIcon(Jogo.class.getResource("/Imagens/casa_selecionada.png")));
		casa_selecionada.setVisible(false);
		
		casa_selecionada.setBounds(80,80, 80, 80);
		panel.add(casa_selecionada);
		
		for(int i=0;i<tamanho;i++) {
			for(int j=0;j<tamanho;j++) {
				JLabel casa = new JLabel();
				casa.setIcon(new ImageIcon(Jogo.class.getResource("/Imagens/destino.png")));
				destino[i][j]=casa;
				panel.add(destino[i][j]);
				casa.setBounds(j*80,i*80, 80, 80);
			}
		}
		
		for(int i=0;i<tamanho;i++) {
			for(int j=0;j<tamanho;j++) {
				destino[i][j].setVisible(false);
			}
		}
		
		int n=0,m = 0;
		for(int i=0;i<tamanho;i++) {
			for(int j=0;j<tamanho;j++) {
				
				
					
				JLabel peca = new JLabel();
					
				peca.setIcon(new ImageIcon(Jogo.class.getResource("/Imagens/peca_"+cor3+".png")));
				pecas_1[i][j]=peca;
				panel.add(pecas_1[i][j]);
				peca.setBounds(j*80,i*80, 80, 80);
				pecas_1[i][j].setVisible(false);
				
			}
			
		}
		for(int i=0;i<tamanho;i++) {
			for(int j=0;j<tamanho;j++) {
				
				if(i%2==j%2) {
					
					pecas_1[i][j].setVisible(true);
					panel.add(pecas_1[i][j]);
					n++;
					tabuleiro[j][i]=1;
				
				}
				if(n==quantidade_pecas) {
					m=i;
					break;
				}
			}
			if(n==quantidade_pecas) {
				m=i;
				break;
			}
		}
		n=0;
		
		for(int i=0;i<tamanho;i++) {
			for(int j=0;j<tamanho;j++) {
				JLabel peca = new JLabel();
				peca.setIcon(new ImageIcon(Jogo.class.getResource("/Imagens/peca_"+cor4+".png")));
				pecas_2[i][j]=peca;
				panel.add(pecas_2[i][j]);
				n++;
				peca.setBounds(j*80,i*80, 80, 80);
				pecas_2[i][j].setVisible(false);
			}
		}
		
		for(int i=tamanho-1;i>m+2;i--) {
			for(int j=0;j<tamanho;j++) {
				
				if(i%2==j%2) {
					JLabel peca = new JLabel();
					peca.setIcon(new ImageIcon(Jogo.class.getResource("/Imagens/peca_"+cor4+".png")));
					pecas_2[i][j]=peca;
					panel.add(pecas_2[i][j]);
					n++;
					tabuleiro[j][i]=2;
					peca.setBounds(j*80,i*80, 80, 80);
				
				}
				if(n==quantidade_pecas) {
					break;
				}
			}
			if(n==quantidade_pecas) {
				break;
			}
		}
		
		for(int i=0;i<tamanho;i++) {
			for(int j=0;j<tamanho;j++) {
				JLabel casa = new JLabel();
				String cor = ((i%2==j%2) ?  cor1 : cor2);
				cor="/Imagens/casa_"+cor+".png";
				casa.setBounds(j*80,i*80, 80, 80);
				casa.setIcon(new ImageIcon(Jogo.class.getResource(cor)));
				casas[i][j]=casa;
				panel.add(casas[i][j]);
				
			}
		}
		
		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent clique) {
				
				int x=clique.getX()/80;
				int y=clique.getY()/80;
				
				
				if(selecao==0 && vez==tabuleiro[x][y]) {
					if(x%2==y%2) {
						selecao=1;
						casa_selecionada.setVisible(true);
						casa_selecionada.setBounds(x*80,y*80, 80, 80);
						MovimentosNormais mover = new MovimentosNormais(tamanho,x,y);
						for(int i=0;i<tamanho;i++) {
							for(int j=0;j<tamanho;j++) {
								mover.setTabuleiro(i,j,tabuleiro[j][i]);
							}
						}
						
						
						mover.movimentar();
						for(int i=0;i<tamanho;i++) {
							for(int j=0;j<tamanho;j++) {
								movimentos[i][j]=mover.getMovimento(i, j);
							}
						}
						
						c_x=x;
						c_y=y;
						System.out.println(c_y);
						
					}
				}else {
					selecao=0;
					if (movimentos[y][x]==1) {
						tabuleiro[y][x]=vez;
						tabuleiro[c_x][c_y]=0;
						
						if(vez==1) {
							pecas_1[x][y].setVisible(true);
							pecas_1[c_x][c_y].setVisible(false);
							vez=2;
						}else {
							pecas_2[x][y].setVisible(true);
							pecas_2[c_x][c_y].setVisible(false);
							vez=1;
						}
					}
					for(int i=0;i<tamanho;i++) {
						for(int j=0;j<tamanho;j++) {
							movimentos[i][j]=0;
						}
					}
					for(int i=0;i<tamanho;i++) {
						for(int j=0;j<tamanho;j++) {
							
						}
					}
					casa_selecionada.setVisible(false);
				}
				
				for(int i=0;i<tamanho;i++) {
					for(int j=0;j<tamanho;j++) {
						if(movimentos[i][j]==1) {
							destino[i][j].setVisible(true);
						}else {
							destino[i][j].setVisible(false);
						}
						
					}
				}
				
			}
		});	
	}
}
