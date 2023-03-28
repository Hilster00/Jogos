package Pecas;

public class MovimentosNormais {

	private int tamanho,x,y;
	
	

	public MovimentosNormais(int tamanho,int x,int y) {
		this.tamanho=tamanho;
		this.x=x;
		this.y=y;
	}
	
	private int movimentos[][] = new int[8][8];
	private int tabuleiro[][] = new int[8][8];
	
	
	public int getMovimento(int i,int j) {
		
		return this.movimentos[i][j];
		
		
	}
	
	public void setTabuleiro(int i, int j,int pecas) {
		this.tabuleiro[i][j]=pecas;
	}
	
	public void limparMovimentos() {
		for(int i=0;i<tamanho;i++) {
			for(int j=0;j<tamanho;j++) {
				movimentos[i][j]=0;
			}
		}
	}
	
	public void movimentar() {
		
		
		if(x-1>=0 && y-1>=0) {
			if(tabuleiro[y-1][x-1]==0) {
				movimentos[y-1][x-1]=1;
			}else {
				if(tabuleiro[y-1][x-1]!=tabuleiro[y][x]) {
					if(x-2>=0 && y-2>=0) {
						if(tabuleiro[y-2][x-2]==0) {
							movimentos[y-2][x-2]=1;
						}
						
					}
				}
			}
			
		}

		if(x-1>=0 && y+1<tamanho) {
			if(tabuleiro[y+1][x-1]==0) {
				movimentos[y+1][x-1]=1;
			}else {
				if(tabuleiro[y+1][x-1]!=tabuleiro[y][x]) {
					if(x-2>=0 && y+2<tamanho ) {
						if(tabuleiro[y+2][x-2]==0) {
							movimentos[y+2][x-2]=1;
						}
						
					}
				}
			}
			
		}
		if(x+1<tamanho && y-1>=0) {
			if(tabuleiro[y-1][x+1]==0) {
				movimentos[y-1][x+1]=1;
			}else {
				if(tabuleiro[y-1][x+1]!=tabuleiro[y][x]) {
					if(x+2<tamanho && y-2>=0 ) {
						if(tabuleiro[y-2][x+2]==0) {
							movimentos[y-2][x+2]=1;
						}
						
					}
				}
			}
			
			
		}
		
		if(x+1<tamanho && y+1<tamanho) {
			if(tabuleiro[y+1][x+1]==0) {
				movimentos[y+1][x+1]=1;
			}else {
				if(tabuleiro[y+1][x+1]!=tabuleiro[y][x]) {
					if(x+2<tamanho && y+2<tamanho ) {
						if(tabuleiro[y+2][x+2]==0) {
							movimentos[y+2][x+2]=1;
						}
						
					}
				}
			}
			
			
		}
	}
}
