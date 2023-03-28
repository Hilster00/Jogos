package Janelas;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class JanelaInicial extends JFrame {
	public int tamanho=6;
	private JPanel contentPane;

	public JanelaInicial() {
		
		//construcao da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 243, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		
		
		//textos
		JLabel lblNewLabel = new JLabel("Quantidade Casas");
		lblNewLabel.setBounds(0, 0, 220, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cor Casas 1");
		lblNewLabel_1.setBounds(0, 30, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cor Casas 2");
		lblNewLabel_2.setBounds(0, 60, 89, 14);
		contentPane.add(lblNewLabel_2);
		

		JLabel lblNewLabel_3 = new JLabel("Cor Pecas 1");
		lblNewLabel_3.setBounds(0, 90, 76, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Cor Pecas 2");
		lblNewLabel_4.setBounds(0, 120, 89, 14);
		contentPane.add(lblNewLabel_4);
				
		//comboBox do tamanho do tabuleiro
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(125, 0, 102, 20);
		String opcao[] = new String[5];
		for(int i=6;i<10;i=i+2) {
			opcao[i-6]=i+"X"+i;
			comboBox.addItem(opcao[i-6]);
		}
		
		contentPane.add(comboBox);
		
		String cores_casas[] = new String[11];
		cores_casas[0]= "Amarelo";
		cores_casas[1]= "Azul";
		cores_casas[2]= "Branco";
		cores_casas[3]= "Cinza";
		cores_casas[4]= "Laranja";
		cores_casas[5]= "Marrom";
		cores_casas[6]= "Preto";
		cores_casas[7]= "Rosa";
		cores_casas[8]= "Verde";
		cores_casas[9]= "Vermelho";
		cores_casas[10]= "Vinho";
		
		
		String cores_pecas[] = new String[10];
		cores_pecas[0]= "Amarelo";
		cores_pecas[1]= "Azul";
		cores_pecas[2]= "Cinza";
		cores_pecas[3]= "Laranja";
		cores_pecas[4]= "Marrom";
		cores_pecas[5]= "Preto";
		cores_pecas[6]= "Rosa";
		cores_pecas[7]= "Verde";
		cores_pecas[8]= "Vermelho";
		cores_pecas[9]= "Vinho";
		
		JComboBox casas_1 = new JComboBox();
		
		for(int i=0;i<11;i++) {
			casas_1.addItem(cores_casas[i]);
		}
		
		JComboBox casas_2 = new JComboBox();
		
		for(int i=0;i<11;i++) {
			casas_2.addItem(cores_casas[i]);
		}
		
		casas_1.setBounds(125, 30, 102, 22);
		contentPane.add(casas_1);
		casas_2.setBounds(125, 60, 102, 22);
		contentPane.add(casas_2);
		
		JComboBox pecas_1 = new JComboBox();
		JComboBox pecas_2 = new JComboBox();
		
		for(int i=0;i<10;i++) {
			pecas_1.addItem(cores_pecas[i]);
		}
		
		for(int i=0;i<10;i++) {
			pecas_2.addItem(cores_pecas[i]);
		}
		
		
		pecas_1.setBounds(125, 90, 102, 22);
		contentPane.add(pecas_1);
		pecas_2.setBounds(125, 120, 102, 22);
		contentPane.add(pecas_2);
		
		
		pecas_2.removeItem(pecas_1.getSelectedItem());
		pecas_1.removeItem(pecas_2.getSelectedItem());
		
		
		
		casas_2.removeItem(casas_1.getSelectedItem());
		casas_1.removeItem(casas_2.getSelectedItem());
		
		casas_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox temp = new JComboBox();
				
				temp.addItem(casas_2.getSelectedItem());
				casas_2.removeAllItems();
				
				for(int i=0;i<11;i++) {
					if(cores_casas[i]!=casas_1.getSelectedItem()) {
						casas_2.addItem(cores_casas[i]);
					}
				}
				
				casas_2.setSelectedItem(temp.getSelectedItem());
			}
		});
		
		casas_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JComboBox temp = new JComboBox();
				
				temp.addItem(casas_1.getSelectedItem());
				casas_1.removeAllItems();
				
				for(int i=0;i<11;i++) {
					if(cores_casas[i]!=casas_2.getSelectedItem()) {
						casas_1.addItem(cores_casas[i]);
					}
				}

				casas_1.setSelectedItem(temp.getSelectedItem());
			}
		});
		
		

		pecas_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JComboBox temp = new JComboBox();
				
				temp.addItem(pecas_2.getSelectedItem());
				pecas_2.removeAllItems();
				
				for(int i=0;i<10;i++) {
					if(cores_pecas[i]!=pecas_1.getSelectedItem()) {
						pecas_2.addItem(cores_pecas[i]);
					}
				}

				pecas_2.setSelectedItem(temp.getSelectedItem());
			}
		});
		
		pecas_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JComboBox temp = new JComboBox();
				
				temp.addItem(pecas_1.getSelectedItem());
				pecas_1.removeAllItems();
				
				for(int i=0;i<10;i++) {
					if(cores_pecas[i]!=pecas_2.getSelectedItem()) {
						pecas_1.addItem(cores_pecas[i]);
					}
				}

				pecas_1.setSelectedItem(temp.getSelectedItem());
			}
		});
		
		//botao para salvar as opcoes
		JButton btnNewButton = new JButton("Escolher");
				
		btnNewButton.setBounds(0, 150, 89, 23);
		contentPane.add(btnNewButton);
				
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i;
				
				for(i=6;i<10;i++) {
					if(comboBox.getSelectedItem()==opcao[i-6]) {
						tamanho=i;
						break;
					}
				}
				setVisible(false);
				
				String cor1 = null,cor2 = null,cor3 = null,cor4 = null;
				for(i=0;i<11;i++) {
					if(cores_casas[i]==casas_1.getSelectedItem()) {
						cor1=cores_casas[i];
					}
					if(cores_casas[i]==casas_2.getSelectedItem()) {
						cor2=cores_casas[i];
					}
				}
				
				for(i=0;i<10;i++) {
					if(cores_pecas[i]==pecas_1.getSelectedItem()) {
						cor3=cores_pecas[i];
					}
					if(cores_pecas[i]==pecas_2.getSelectedItem()) {
						cor4=cores_pecas[i];
					}
					
				}
				
				Jogo jogo = new Jogo(tamanho,cor1,cor2,cor3,cor4);
				jogo.setVisible(true);
				
			}
		});
			
	}
}
