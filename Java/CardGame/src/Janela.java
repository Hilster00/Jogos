package src;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseMotionAdapter;

public class Janela extends JFrame {
	public int posicao=0;
	
	private static final long serialVersionUID = 1L;
	public int jogadas=0;
	private JPanel contentPane;
	JLabel cartas[] = new JLabel[3];
	JLabel campo[] = new JLabel[3];
	JPanel panel = new JPanel();
	mao mao_p1 = new mao();
	deck deck_p1 = new deck();
	jogador p1 = new jogador();
	jogador bot = new jogador();
	int q_m,q=5;
	int cartas_campo[] = new int[3];
	int q_campo=0;
	magia acao = new magia();
	
	public Janela() {	
		
		//balanceamento
		int quantidade_inicial=5;
			
		q_m=quantidade_inicial;
		
		for(int i=0;i<3;i++) {
			cartas_campo[i]=0;
		}
		
		for(int i=0;i<quantidade_inicial;i++) {
			mao_p1.adicionar_carta(deck_p1.comprar_carta());
		}	
		
		//configurando a janela do jogo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 477, 326);
		contentPane.add(panel);
		panel.setLayout(null);
		setTitle("CardGame - SENHOR_H");
		
		JLabel vencedor = new JLabel("");
		vencedor.setBounds(275, 145, 180, 14);
		panel.add(vencedor);
		
		
		//dados jogador(player 1)
		JLabel nome_p1 = new JLabel("Jogador");
		nome_p1.setBounds(371, 209, 84, 14);
		panel.add(nome_p1);
		
		JLabel vida_p1 = new JLabel("Vida: "+p1.vida());
		vida_p1.setBounds(371, 232, 84, 14);
		panel.add(vida_p1);
			
		JLabel mana_p1 = new JLabel("Mana: "+p1.mana());
		mana_p1.setBounds(371, 257, 84, 14);
		panel.add(mana_p1);
				
		
		//dados bot(player 2)
		JLabel nome_p2 = new JLabel("Bot");
		nome_p2.setBounds(371, 11, 46, 14);
		panel.add(nome_p2);
		
		JLabel vida_p2 = new JLabel("Vida: "+bot.vida());
		vida_p2.setBounds(371, 34, 150, 14);
		panel.add(vida_p2);
				
		JLabel mana_p2 = new JLabel("Mana: "+bot.mana());
		mana_p2.setBounds(371, 56, 99, 14);
		panel.add(mana_p2);
		
		//dados cartas
		JLabel quantidade_compra = new JLabel(deck_p1.getquantidade()+"");
		quantidade_compra.setBounds(257, 232, 46, 14);
		panel.add(quantidade_compra);

		JLabel deck_compra = new JLabel("");
		
		JLabel previous = new JLabel();
		JLabel next = new JLabel();
		
		deck_compra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					vencedor.setText("Bot venceu! Jogador 1 desistiu!");
					vida_p1.setText("vida: 0");
					mana_p1.setText("manda: 0");
                }
			}
			
		});
		deck_compra.setIcon(new ImageIcon(Janela.class.getResource("/Imagens/fundo.png")));
		deck_compra.setBounds(247, 246, 30, 41);
		panel.add(deck_compra);
		
	
		previous.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() != MouseEvent.BUTTON3 && p1.estado_jogador()) {
					if(posicao!=0) {
						next.setVisible(true);
						posicao-=1;
						if(posicao==0) {
							previous.setVisible(false);
						}
					}				
				}
				definir_cartas();
				definir_campo();
			}
		});
		previous.setIcon(new ImageIcon(Janela.class.getResource("/Imagens/previus.png")));
		previous.setBounds(31, 238, 30, 41);
		previous.setVisible(false);
		panel.add(previous);
		
		for(int i=0;i<3;i++) {
			JLabel lblNewLabel = new JLabel();
			if(mao_p1.carta(posicao+i)!=0) {
				lblNewLabel.setIcon(new ImageIcon(Janela.class.getResource("/Imagens/carta"+mao_p1.carta(posicao+i)+".png")));
			}
			lblNewLabel.setBounds(21+(i+1)*40, 238, 30, 41);
			cartas[i]=lblNewLabel;
			panel.add(cartas[i]);
		}
		
		for(int i=1;i<4;i++) {
			JLabel temp = new JLabel();
			temp.setIcon(new ImageIcon(Janela.class.getResource("/Imagens/campo.png")));
			temp.setBounds(21+i*40, 117, 30, 41);
			campo[i-1]=temp;
			cartas_campo[i-1]=0;
			panel.add(campo[i-1]);
			
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(191, 145, 52, 14);
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		
		JLabel usar_magia = new JLabel("magia");
		usar_magia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int executado=0;
				if(q_campo!=0 && p1.estado_jogador()) {
					if(q_campo==1) {
						p1.recarregar_mana(acao.efeito(cartas_campo[0]));
						executado=1;
					}else {
						if(acao.tipo(cartas_campo[0])=="Cura") {
							if(q_campo==2) {
								if(p1.mana()>acao.custo(cartas_campo[0],cartas_campo[1])) {
									p1.curar(acao.efeito(cartas_campo[0],cartas_campo[1]));
									p1.gastar_mana(acao.custo(cartas_campo[0],cartas_campo[1]));
									executado=1;
								}
							}else{
								if(p1.mana()>acao.custo(cartas_campo[0],cartas_campo[1],cartas_campo[2])) {
									p1.curar(acao.efeito(cartas_campo[0],cartas_campo[1],cartas_campo[2]));
									p1.gastar_mana(acao.custo(cartas_campo[0],cartas_campo[1],cartas_campo[2]));
									executado=1;
								}
							}
						}else {
							if(acao.tipo(cartas_campo[0])=="Dano") {
								if(q_campo==2) {
									if(p1.mana()>acao.custo(cartas_campo[0],cartas_campo[1])) {
										bot.dano(acao.efeito(cartas_campo[0],cartas_campo[1]));
										p1.gastar_mana(acao.custo(cartas_campo[0],cartas_campo[1]));
										executado=1;
									}
								}else{
									if(p1.mana()>acao.custo(cartas_campo[0],cartas_campo[1],cartas_campo[2])) {
										bot.dano(acao.efeito(cartas_campo[0],cartas_campo[1],cartas_campo[2]));
										p1.gastar_mana(acao.custo(cartas_campo[0],cartas_campo[1],cartas_campo[2]));
										executado=1;
									}
								}
								if(!bot.estado_jogador()) {
									q+=1;
									for(int i=0;i<1;i++) {
										jogador temp = new jogador();
										bot=temp;
									}
									bot.curar(1000*(q-5));
									p1.curar(1000*(q-5));
									for(int i=0;i<1;i++) {
										deck temp = new deck();
										deck_p1=temp;
									}
									for(int i=0;i<1;i++) {
										mao temp = new mao();
										mao_p1=temp;
									}
									for(int i=0;i<quantidade_inicial;i++) {
										mao_p1.adicionar_carta(deck_p1.comprar_carta());
									}
								}
							}
							
						}
					}
					if(executado==1) {
						for(int i=0;i<3;i++) {
							cartas_campo[i]=0;
						}
						q_campo=0;
					}
					definir_campo();
					definir_cartas();
					mana_p2.setText("Mana: "+bot.mana());
					vida_p2.setText("Vida: "+bot.vida());
					mana_p1.setText("Mana: "+p1.mana());
					vida_p1.setText("Vida: "+p1.vida());
					if(q_campo==0) {
						panel_1.setVisible(false);
					}
					if(posicao<7) {
						if(mao_p1.carta(posicao+3)==0) {
							next.setVisible(false);
						}else {
							next.setVisible(true);
						}
					}else {
						next.setVisible(false);
					}
				}
			}
		});
		usar_magia.setBounds(0, 0, 52, 14);
		panel_1.add(usar_magia);
		usar_magia.setBackground(Color.CYAN);
		
		
		cartas[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() != MouseEvent.BUTTON3 && p1.estado_jogador()){
					if(q_campo<3) {
						cartas_campo[q_campo]=mao_p1.usar_carta(posicao);
						q_campo+=1;
						panel_1.setVisible(true);
						if(mao_p1.carta(posicao+2)==0) {
							posicao-=1;
							if(posicao<0) {
								posicao=0;
							}
						}
						if(posicao==0) {
							previous.setVisible(false);
						}
						if(posicao<7) {
							if(mao_p1.carta(posicao+3)==0) {
								next.setVisible(false);
							}
						}else {
							next.setVisible(false);
						}
						definir_cartas();
						definir_campo();
						
					}
				}
			}
		});
		cartas[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() != MouseEvent.BUTTON3 && p1.estado_jogador()) {
					if(q_campo<3) {
						cartas_campo[q_campo]=mao_p1.usar_carta(posicao+1);
						q_campo+=1;
						panel_1.setVisible(true);
						if(mao_p1.carta(posicao+2)==0) {
							posicao-=1;
							if(posicao<0) {
								posicao=0;
							}
						}
						if(posicao==0) {
							previous.setVisible(false);
						}
						if(posicao<7) {
							if(mao_p1.carta(posicao+3)==0) {
								next.setVisible(false);
							}
						}else {
							next.setVisible(false);
						}
						definir_cartas();
						definir_campo();
					}
				}
			}
		});
		cartas[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() != MouseEvent.BUTTON3 && p1.estado_jogador()) {
					if(q_campo<3) {
						cartas_campo[q_campo]=mao_p1.usar_carta(posicao+2);
						q_campo+=1;
						panel_1.setVisible(true);
						if(mao_p1.carta(posicao+2)==0) {
							posicao-=1;
							if(posicao<0) {
								posicao=0;
							}
						}
						if(posicao==0) {
							previous.setVisible(false);
						}
						if(posicao<7) {
							if(mao_p1.carta(posicao+3)==0) {
								next.setVisible(false);
							}
						}else {
							next.setVisible(false);
						}
						definir_cartas();
						definir_campo();
					}
				}
			}
		});
		
		campo[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() != MouseEvent.BUTTON3 && p1.estado_jogador()) {
					if(cartas_campo[0]!=0) {
						mao_p1.adicionar_carta(cartas_campo[0]);
						for(int i=1;i<3;i++) {
							cartas_campo[i-1]=cartas_campo[i];
						}
						cartas_campo[2]=0;
						q_campo-=1;
						if(q_campo==0) {
							panel_1.setVisible(false);
						}
						if(posicao<7) {
							if(mao_p1.carta(posicao+3)==0) {
								next.setVisible(false);
							}else {
								next.setVisible(true);
							}
						}else {
							next.setVisible(false);
						}
					}
				}
				definir_cartas();
				definir_campo();
			}
		});
		campo[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() != MouseEvent.BUTTON3 && p1.estado_jogador()) {
					if(cartas_campo[1]!=0) {
						mao_p1.adicionar_carta(cartas_campo[1]);
						cartas_campo[1]=cartas_campo[2];
						cartas_campo[2]=0;
						q_campo-=1;
						if(q_campo==0) {
							panel_1.setVisible(false);
						}
						if(posicao<17) {
							if(mao_p1.carta(posicao+3)==0) {
								next.setVisible(false);
							}else {
								next.setVisible(true);
							}
						}else{
							next.setVisible(false);
						}
					}
				}
				definir_cartas();
				definir_campo();
			}
		});
		campo[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() != MouseEvent.BUTTON3 && p1.estado_jogador()) {
					if(cartas_campo[2]!=0) {
						mao_p1.adicionar_carta(cartas_campo[2]);
						cartas_campo[2]=0;
						q_campo-=1;
						if(q_campo==0) {
							panel_1.setVisible(false);
						}
						if(posicao<7) {
							if(mao_p1.carta(posicao+3)==0) {
								next.setVisible(false);
							}else {
								next.setVisible(true);
							}
						}else {
							next.setVisible(false);
						}
					}
				}
				definir_cartas();
				definir_campo();
			}
		});
		next.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() != MouseEvent.BUTTON3 && p1.estado_jogador()) {
					if(mao_p1.carta(posicao+3)!=0) {
						previous.setVisible(true);
						posicao+=1;
						if(posicao<7) {
							if(mao_p1.carta(posicao+3)==0) {
								next.setVisible(false);
							}else {
								next.setVisible(true);
							}
						}else {
							next.setVisible(false);
						}
					}
				}
				definir_cartas();
				definir_campo();
			}
		});
		
		if(mao_p1.carta(posicao+3)!=0) {
			next.setIcon(new ImageIcon(Janela.class.getResource("/Imagens/next.png")));
		}
		next.setBounds(171, 238, 30, 41);
		panel.add(next);
		
		JLabel lblNewLabel_1 = new JLabel("Quantidade cartas Oponente:"+q_m);
		lblNewLabel_1.setBounds(48, 56, 195, 14);
		panel.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(371, 273, 84, 14);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("End Turn");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(p1.estado_jogador()) {
					q_m+=q;
					while(q_m>0) {
						if(bot.mana()>99) {
							if(q_m>2) {
								if(bot.vida()<p1.vida()) {
									bot.curar(500);
									bot.gastar_mana(100);
									q_m-=3;
								}else {
									p1.dano(500);
									bot.gastar_mana(100);
									q_m-=3;
									if(p1.vida()<=0) {
										vencedor.setText("Jogador perdeu!");
										break;
									}
								}
							}else {
								break;
							}
							
						}else{
							q_m-=1;
							bot.recarregar_mana(500);
						}
					}
					mana_p2.setText("Mana: "+bot.mana());
					vida_p2.setText("Vida: "+bot.vida());
					mana_p1.setText("Mana: "+p1.mana());
					vida_p1.setText("Vida: "+p1.vida());
					quantidade_compra.setText(deck_p1.getquantidade()+"");
					if(p1.estado_jogador()) {
						mao_p1.adicionar_carta(deck_p1.comprar_carta());
						if(q_campo==0) {
							panel_1.setVisible(false);
						}
						if(posicao<7) {
							if(mao_p1.carta(posicao+3)==0) {
								next.setVisible(false);
							}else {
								next.setVisible(true);
							}
						}else {
							next.setVisible(false);
						}
					}else {
						panel_2 .setVisible(false);
					}
					definir_campo();
					definir_cartas();
				}				
			}
		});
		lblNewLabel_2.setBounds(0, 0, 90, 15);
		panel_2.add(lblNewLabel_2);
		
		JLabel mao_oponente[] = new JLabel[3];
		for(int i=1;i<4;i++) {
			JLabel carta = new JLabel();
			carta.setIcon(new ImageIcon(Janela.class.getResource("/Imagens/fundo.png")));
			carta.setBounds(21+i*40, 11, 30, 41);
			mao_oponente[i-1]=carta;
			panel.add(mao_oponente[i-1]);
			
			}
	
	}
	
	public void definir_cartas() {
		for(int i=0;i<3;i++) {
			if(mao_p1.carta(posicao+i)!=0) {
				cartas[i].setVisible(true);
				cartas[i].setIcon(new ImageIcon(Janela.class.getResource("/Imagens/carta"+mao_p1.carta(posicao+i)+".png")));
			}else {
				cartas[i].setVisible(false);
			}
		}
	}
	public void definir_campo() {
		for(int i=0;i<3;i++) {
			if(cartas_campo[i]==0){
				campo[i].setIcon(new ImageIcon(Janela.class.getResource("/Imagens/campo.png")));
			}else {
				campo[i].setIcon(new ImageIcon(Janela.class.getResource("/Imagens/carta"+cartas_campo[i]+".png")));
			}
				
		}		
	}
}