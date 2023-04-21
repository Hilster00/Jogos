import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Janela extends JFrame {

	private JPanel contentPane;
	int vez=1,casas=9;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela frame = new Janela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Janela() {
		
		int tabuleiro[][] = new int[3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				tabuleiro[i][j]=0;
			}
		}
		Random gerador = new Random();
		if(gerador.nextInt(5)%2==0) {
			vez=-vez;
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 359, 165);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 130, 130);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel[][]= new JLabel [3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				JLabel temp = new JLabel("");
				temp.setBounds(0+(j*45), 0+(i*45), 40, 40);
				temp.setIcon(new ImageIcon(Janela.class.getResource("/img/X.png")));
				temp.setVisible(false);
				lblNewLabel[i][j]=temp;
				panel.add(lblNewLabel[i][j]);
			}
		}
		
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setIcon(new ImageIcon(Janela.class.getResource("/img/tabuleiro.png")));
		lblNewLabel1.setBounds(0, 0, 130, 130);
		panel.add(lblNewLabel1);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(144, 52, 189, 14);
		contentPane.add(lblNewLabel_1);
		if(vez == 1) {
			lblNewLabel_1.setText("Vez do X");
		}else {
			lblNewLabel_1.setText("Vez do O");
		}
		
		
		
		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent clique) {
				
				int x=clique.getX()/45;
				int y=clique.getY()/45;
				if(tabuleiro[y][x]==0 && vez!=0 && casas!=0) {
					tabuleiro[y][x]=vez;
					lblNewLabel[y][x].setVisible(true);
					if(vez == -1) {
						lblNewLabel[y][x].setIcon(new ImageIcon(Janela.class.getResource("/img/o.png")));
						lblNewLabel_1.setText("Vez do X");
					}else {
						lblNewLabel_1.setText("Vez do O");
					}
					vez=-vez;
					for(int i=0;i<3;i++) {
						if(tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][0] == tabuleiro[i][2] && tabuleiro[i][0] != 0) {
							if(vez == 1) {
								lblNewLabel_1.setText("O venceu");
							}else {
								lblNewLabel_1.setText("X venceu");
							}
							vez=0;
						}
					}
					for(int i=0;i<3;i++) {
						if(tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[0][i] == tabuleiro[2][i] && tabuleiro[0][i] != 0) {
							if(vez == 1) {
								lblNewLabel_1.setText("O venceu");
							}else {
								lblNewLabel_1.setText("X venceu");
							}
							vez=0;
						}
					}
					if(tabuleiro[0][0]==tabuleiro[1][1] && tabuleiro[0][0]==tabuleiro[2][2] && tabuleiro[0][0]!=0) {
						if(vez == 1) {
							lblNewLabel_1.setText("O venceu");
						}else {
							lblNewLabel_1.setText("X venceu");
						}
						vez=0;
					}
					if(tabuleiro[0][2]==tabuleiro[1][1] && tabuleiro[0][2]==tabuleiro[2][0] && tabuleiro[0][2]!=0) {
						if(vez == 1) {
							lblNewLabel_1.setText("O venceu");
						}else {
							lblNewLabel_1.setText("X venceu");
						}
						vez=0;
					}
					if(vez!=0) {
						casas--;
					}
				}
				if(casas==0) {
					lblNewLabel_1.setText("Velha");
				}
			}
			
		});	
	}
}


