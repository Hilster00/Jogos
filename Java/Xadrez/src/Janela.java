import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class Janela extends JFrame {

	private JPanel contentPane;
	public boolean selecao=false;
	public int x_old,y_old;
	public int id;
	public String vez="Branco";
	public boolean win=false;
	
	public Janela() {
		setTitle("Xadrez");
		
		//janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 678, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		JPanel painel = new JPanel();
		painel.setBounds(0, 0, 660, 360);
		contentPane.add(painel);
		painel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Jogador atual: "+vez);
		lblNewLabel_2.setBounds(420, 153, 180, 14);
		painel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel();
		
		Casas casas = new Casas();
		Cemiterio cemiterio = new Cemiterio();
		int registros[][]=new int[8][8];
		
		//colocando pecas no tabuleiro
		JLabel tabuleiro[]= new JLabel[64];
		for(int j=0;j<8;j++) {
			for(int i=0;i<8;i++) {
				JLabel pecas = new JLabel();
				if(casas.pecas[j][i].getTipo()!="Vazio") {
					String nome;
					nome="/imagens/"+casas.pecas[j][i].getTipo()+"_"+casas.pecas[j][i].getCor()+".png";
					pecas.setIcon(new ImageIcon(Janela.class.getResource(nome)));
					registros[j][i]=j*8+i;
				}else {
					registros[j][i]=99;
				}
				pecas.setBounds((-12+i*40),280-j*40, 54, 40);
				tabuleiro[j*8+i]=pecas;
				
			}
			
		}
		
		for(int i=0;i<64;i++) {
			painel.add(tabuleiro[i]);
		}
		
		
		//casas selecionadas		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setIcon(new ImageIcon(Janela.class.getResource("/imagens/campo_selecionado.jpg")));
		lblNewLabel_1.setBounds(0, 0, 54, 40);
		painel.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		//colocando casas para futuros destinos
		JLabel destino[][] = new JLabel[8][8];
		for(int y=0;y<8;y++) {
			for(int x=0;x<8;x++) {
				JLabel casa = new JLabel();
				if((x%2==0 && y%2==0)||x%2!=0 && y%2!=0) {
					casa.setIcon(new ImageIcon(Janela.class.getResource("/imagens/campo_destino.jpg")));			
				}else {
					casa.setIcon(new ImageIcon(Janela.class.getResource("/imagens/campo_destino_c.jpg")));			
				}
				casa.setBounds(40*x, (280-40*y), 54, 40);
				casa.setVisible(false);
				destino[y][x]=casa;
			}

		}
		for(int j=0;j<8;j++) {
			for(int i=0;i<8;i++) {
				painel.add(destino[j][i]);
			}			
		}		
		
		
		String name = "/imagens/tabuleiro.gif";
		lblNewLabel.setIcon(new ImageIcon(Janela.class.getResource(name)));
		lblNewLabel.setBounds(0, 0, 320, 320);
		painel.add(lblNewLabel);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("3");
		lblNewLabel_3_1_2.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 20));
		lblNewLabel_3_1_2.setBounds(320, 210, 54, 25);
		painel.add(lblNewLabel_3_1_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("4");
		lblNewLabel_3_3.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 20));
		lblNewLabel_3_3.setBounds(320, 170, 54, 25);
		painel.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_1_3 = new JLabel("1");
		lblNewLabel_3_1_3.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 20));
		lblNewLabel_3_1_3.setBounds(320, 290, 54, 25);
		painel.add(lblNewLabel_3_1_3);
		
		JLabel lblNewLabel_3_4 = new JLabel("2");
		lblNewLabel_3_4.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 20));
		lblNewLabel_3_4.setBounds(320, 250, 54, 25);
		painel.add(lblNewLabel_3_4);
		
		JLabel lblNewLabel_3_1_2_1 = new JLabel("7");
		lblNewLabel_3_1_2_1.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 20));
		lblNewLabel_3_1_2_1.setBounds(320, 50, 54, 25);
		painel.add(lblNewLabel_3_1_2_1);
		
		JLabel lblNewLabel_3_3_1 = new JLabel("8");
		lblNewLabel_3_3_1.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 20));
		lblNewLabel_3_3_1.setBounds(320, 10, 54, 25);
		painel.add(lblNewLabel_3_3_1);
		
		JLabel lblNewLabel_3_4_1 = new JLabel("6");
		lblNewLabel_3_4_1.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 20));
		lblNewLabel_3_4_1.setBounds(320, 90, 54, 25);
		painel.add(lblNewLabel_3_4_1);
		
		JLabel lblNewLabel_3_1_3_1 = new JLabel("5");
		lblNewLabel_3_1_3_1.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 20));
		lblNewLabel_3_1_3_1.setBounds(320, 130, 54, 25);
		painel.add(lblNewLabel_3_1_3_1);
		
		JLabel lblNewLabel_3_1_3_2 = new JLabel("a");
		lblNewLabel_3_1_3_2.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 20));
		lblNewLabel_3_1_3_2.setBounds(10, 324, 54, 25);
		painel.add(lblNewLabel_3_1_3_2);
		
		JLabel lblNewLabel_3_1_3_3 = new JLabel("b");
		lblNewLabel_3_1_3_3.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 20));
		lblNewLabel_3_1_3_3.setBounds(50, 324, 54, 25);
		painel.add(lblNewLabel_3_1_3_3);
		
		JLabel lblNewLabel_3_1_3_2_1 = new JLabel("c");
		lblNewLabel_3_1_3_2_1.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 20));
		lblNewLabel_3_1_3_2_1.setBounds(90, 324, 54, 25);
		painel.add(lblNewLabel_3_1_3_2_1);
		
		JLabel lblNewLabel_3_1_3_3_1 = new JLabel("d");
		lblNewLabel_3_1_3_3_1.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 20));
		lblNewLabel_3_1_3_3_1.setBounds(130, 324, 54, 25);
		painel.add(lblNewLabel_3_1_3_3_1);
		
		JLabel lblNewLabel_3_1_3_2_2 = new JLabel("e");
		lblNewLabel_3_1_3_2_2.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 20));
		lblNewLabel_3_1_3_2_2.setBounds(170, 324, 54, 25);
		painel.add(lblNewLabel_3_1_3_2_2);
		
		JLabel lblNewLabel_3_1_3_3_2 = new JLabel("f");
		lblNewLabel_3_1_3_3_2.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 20));
		lblNewLabel_3_1_3_3_2.setBounds(210, 324, 54, 25);
		painel.add(lblNewLabel_3_1_3_3_2);
		
		JLabel lblNewLabel_3_1_3_2_3 = new JLabel("g");
		lblNewLabel_3_1_3_2_3.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 20));
		lblNewLabel_3_1_3_2_3.setBounds(250, 324, 54, 25);
		painel.add(lblNewLabel_3_1_3_2_3);
		
		JLabel lblNewLabel_3_1_3_3_3 = new JLabel("h");
		lblNewLabel_3_1_3_3_3.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 20));
		lblNewLabel_3_1_3_3_3.setBounds(290, 324, 54, 25);
		painel.add(lblNewLabel_3_1_3_3_3);
		
		
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x=e.getX()/40;
				int y=e.getY()/-40;				
				y=y+7;
				System.out.println(e.getX());
				//nao tem casa selecionada e ninguem ganhou
				if(selecao==false && win == false) {
					if(casas.pecas[y][x].getCor()==vez) {
						
						lblNewLabel_1.setBounds((x*40),280-y*40, 54, 40);
						lblNewLabel_1.setVisible(true);
						selecao=true;
						
						x_old=x;
						y_old=y;										
						id=registros[y][x];
						casas.setDestino(x, y);
						
						for(int j=0;j<8;j++) {
							for(int i=0;i<8;i++) {
								if(casas.destino[j][i]==1) {
									destino[j][i].setVisible(true);
								}								
							}
						}
					}
					
				}else {
					
					//caso tenha algum vencedor
					if(win==true) {
						lblNewLabel_2.setText("Jogador : "+vez+" Ganhou");
						setTitle("Xadrez - jogo encerrado");
					}else {
					
					//ja tem casa selecionada
					if((y_old != y || x_old !=x) && casas.destino[y][x]==1) {
						
						//verifica se a casa selecionada e diferente do jogador atual
						if(casas.pecas[y][x].getCor()!=vez) {
							
							//definir vencedor e comer peca
							if(casas.pecas[y][x].getCor()!="Vazio") {
								if(casas.pecas[y][x].getTipo()=="Rei") {
									lblNewLabel_2.setText("Jogador : "+vez+" Ganhou");
									win=true;
								}
								//colocando peca no cemiterio e ocupando um espaco no cemiterop
								tabuleiro[registros[y][x]].setBounds(cemiterio.getX(vez)+12, cemiterio.getY(vez), 54, 40);
								cemiterio.ocupar_casa(vez);						
							}
							
							//mover peca
							tabuleiro[id].setBounds(-12+40*x, (280-40*y), 54, 40);
							casas.pecas[y][x]=casas.pecas[y_old][x_old];
							casas.pecas[y_old][x_old]=new Pecas();
							registros[y][x]=id;
							
							//trocar a vez
							if(win!=true) {
								if(vez=="Branco") {
									vez="Preto";
								}else {
									vez="Branco";
								}
							}
							
						}					
					}
					
					//atualizar caixa de texto
					if(win!=true) {
						lblNewLabel_2.setText("Jogador atual: "+vez);
					}
					
					//zera casas destino
					for(int j=0;j<8;j++) {
						for(int i=0;i<8;i++) {							
							destino[j][i].setVisible(false);
															
						}
					}
					
					//tira a selecao da casa
					lblNewLabel_1.setVisible(false);
					selecao=false;
					
					}
					
				}
			}
		});
		
		}
	}
