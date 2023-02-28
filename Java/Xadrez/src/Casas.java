
public class Casas extends Pecas{
	
	int destino[][] = new int[8][8];
	Pecas pecas[][] = new Pecas[8][8];
	public Casas() {
		for(int y=0;y<8;y++) {
			for(int x=0;x<8;x++) {
				Pecas vazio = new Pecas();
				pecas[y][x]=vazio;
				destino[y][x]=0;
				
			}
		}
		
		//peoes brancos
		
		Pecas peao_b = new Pecas(0,1,"Peao","Branco");
		pecas[1][0]=peao_b;
		for(int i=0;i<7;i++) {
			Pecas peao_copy = (Pecas)pecas[1][i].getClonar();
			pecas[1][i+1]=peao_copy;
		}
		
		//peoes pretos
		Pecas peao_p = new Pecas(0,7,"Peao","Preto");
		pecas[6][0]=peao_p;
		for(int i=0;i<7;i++) {
			Pecas peao_copy = (Pecas)pecas[6][i].getClonar();
			pecas[6][i+1]=peao_copy;
		}
		
		//rainhas
		pecas[0][3] = new Pecas(3,0,"Rainha","Branco");
		pecas[7][3] = new Pecas(3,7,"Rainha","Preto");
		
		//reis
		pecas[0][4] = new Pecas(4,0,"Rei","Branco");
		pecas[7][4] = new Pecas(4,7,"Rei","Preto");
		
		//bispos
		pecas[0][2] = new Pecas(2,0,"Bispo","Branco");
		pecas[0][5] = new Pecas(5,0,"Bispo","Branco");
		pecas[7][2] = new Pecas(2,7,"Bispo","Preto");
		pecas[7][5] = new Pecas(5,7,"Bispo","Preto");
		
		//cavalos		
		pecas[0][1] = new Pecas(1,0,"Cavalo","Branco");
		pecas[0][6] = new Pecas(6,0,"Cavalo","Branco");
		pecas[7][1] = new Pecas(1,7,"Cavalo","Preto");
		pecas[7][6] = new Pecas(6,7,"Cavalo","Preto");
		
		//torres
		pecas[0][0] = new Pecas(7,0,"Torre","Branco");
		pecas[0][7] = new Pecas(7,0,"Torre","Branco");
		pecas[7][0] = new Pecas(0,7,"Torre","Preto");
		pecas[7][7] = new Pecas(7,7,"Torre","Preto");
		
	}
	
	
	//setar possiveis destinos
	public void setDestino(int x,int y) {
		
		//mover peca
		Mover_pecas mover = new Mover_pecas(y,x);
		for(int j=0;j<8;j++) {
			for(int i=0;i<8;i++) {
				mover.setCores(j, i, pecas[j][i].getCor());
			}	
		}
		mover.setMovimentos(pecas[y][x].getTipo());
		for(int j=0;j<8;j++) {
			for(int i=0;i<8;i++) {
				destino[j][i]=mover.getMovimentos(j, i);
				
			}	
		}			

	}
}
