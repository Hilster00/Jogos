package src;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Janela extends JFrame {

	private static final long serialVersionUID = 1L;
	public int jogadas=0;
	private JPanel contentPane;

	public Janela() {
		
		//configurando a janela do jogo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 477, 326);
		contentPane.add(panel);
		panel.setLayout(null);
		setTitle("Jogo Paint - SENHOR_H");
		JLabel mensagem_jogadas = new JLabel("Jogadas:"+jogadas);
		mensagem_jogadas.setBounds(345, 150, 83, 14);
		panel.add(mensagem_jogadas);
		
		JLabel mensagens = new JLabel();
		mensagens.setBounds(320, 170, 150, 14);
		panel.add(mensagens);
	
		//canhoes vermelhos e azuis
		JLabel canhoes_horizontais[][] = new JLabel[4][2];
		for(int j=0;j<2;j++) {
			String cor_canhao=(j==0 ?  "canhao_vermelho.png" : "canhao_azul.png");
			for(int i=0;i<4;i++) {
				JLabel canhao = new JLabel();			
				canhao.setIcon(new ImageIcon(Janela.class.getResource("/Imagens/"+cor_canhao)));
				canhao.setBounds(50*i+55, j*250, 50, 50);
				canhoes_horizontais[i][j]=canhao;
				panel.add(canhoes_horizontais[i][j]);
			}
				
		}
		
		//canhoes amarelos e vermelhos
		JLabel canhoes_verticais[][] = new JLabel[2][4];
		for(int i=0;i<2;i++) {
			String cor_canhao=(i==0 ?  "canhao_amarelo.png" : "canhao_verde.png");
			for(int j=0;j<4;j++) {
				JLabel canhao = new JLabel();			
				canhao.setIcon(new ImageIcon(Janela.class.getResource("/Imagens/"+cor_canhao)));
				canhao.setBounds(255*i, 50*j+50, 50, 50);
				canhoes_verticais[i][j]=canhao;
				panel.add(canhoes_verticais[i][j]);
			}
		}	
		
		//casas que podem assumir qualquer cor
		JLabel casas[][] = new JLabel[4][4];
		for(int y=0;y<4;y++) {
			for(int x=0;x<4;x++) {
				JLabel casa = new JLabel();
				casa.setIcon(new ImageIcon(Janela.class.getResource("/Imagens/inicial.png")));
				casa.setBounds(x*50+50, y*50+50, 50, 50);
				casas[y][x]=casa;
				casas[y][x].setVisible(true);
				panel.add(casas[y][x]);
			}
		}
		
		//variaveis para evitar usar o mesmo canhao vezes seguidas
		int canhoes_utilizaveis_verticais[][] = new int[4][2]; 
		int canhoes_utilizaveis_horizontais[][] = new int[2][4]; 
		for(int i=0;i<4;i++) {
			for(int j=0;j<2;j++) {
				canhoes_utilizaveis_verticais[i][j]=1;
			}
		}
		for(int i=0;i<2;i++) {
			for(int j=0;j<4;j++) {
				canhoes_utilizaveis_horizontais[i][j]=1;
			}
		}
		
		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent clique) {
				
				int x=clique.getX()/50;
				int y=clique.getY()/50;
		
				//pintar linha de amarelo e verde
				if((x==0 || x==5) && (y>0 && y<5)) {
					y=y-1;
					if(canhoes_utilizaveis_verticais[y][x/5]==1) {
						String cor_casa = (x==0 ? "amarelo.png" : "verde.png");
						for(int j=0;j<4;j++) {
							casas[y][j].setIcon(new ImageIcon(Janela.class.getResource("/Imagens/"+cor_casa)));				
							canhoes_utilizaveis_horizontais[0][j]=1;
							canhoes_utilizaveis_horizontais[1][j]=1;
						}
						jogadas++;
						mensagem_jogadas.setText("Jogadas:"+jogadas);
						mensagens.setText("");
						canhoes_utilizaveis_verticais[y][x/5]=0;
						canhoes_utilizaveis_verticais[y][1-x/5]=1;
					}else {
						mensagens.setText("Sem sobrepor cores");
					}
					y=clique.getY()/50;
				}
				
				//pintar linha de azul e vermelho
				if((x>0 && x<5) && (y==0 || y==5)) {
					x=x-1;
					if(canhoes_utilizaveis_horizontais[y/5][x]==1) {						
						String cor_casa = (y==0 ? "vermelho.png" : "azul.png");
						for(int i=0;i<4;i++) {
							casas[i][x].setIcon(new ImageIcon(Janela.class.getResource("/Imagens/"+cor_casa)));
							canhoes_utilizaveis_verticais[i][0]=1;
							canhoes_utilizaveis_verticais[i][1]=1;
						}
						jogadas++;
						mensagem_jogadas.setText("Jogadas:"+jogadas);
						mensagens.setText("");
						canhoes_utilizaveis_horizontais[y/5][x]=0;
						canhoes_utilizaveis_horizontais[1-y/5][x]=1;
					}else {
						mensagens.setText("Sem sobrepor cores");
					}
					x=clique.getX()/50;
				}
			}
		});	
	}
}