
public class Mover_cavalo {
	private int x,y;
	private String cores[][] = new String[8][8];
	private int movimentos[][] = new int[8][8];
	Mover_cavalo(int y,int x){
		this.x=x;
		this.y=y;
		for(int j=0;j<8;j++) {
			for(int i=0;i<8;i++) {
				movimentos[j][i]=0;
			}
		}
	}
	public void setCores(int y,int x,String cor) {
		this.cores[y][x]=cor;
	}
	public int getMovimentos(int y,int x) {
		return movimentos[y][x];
	}
	public void setMovimentos() {

		if(y<6) {
			if(y<7) {
				if(x<6) {
					if(cores[y+1][x+2]!=cores[y][x]) {
						movimentos[y+1][x+2]=1;
					}
				}
				if(x>1) {
					if(cores[y+1][x-2]!=cores[y][x]) {
						movimentos[y+1][x-2]=1;
					}
				}
			}
			if(x<7) {
				if(cores[y+2][x+1]!=cores[y][x]) {
					movimentos[y+2][x+1]=1;
				}
			}
			if(x>0) {
				if(cores[y+2][x-1]!=cores[y][x]) {
					movimentos[y+2][x-1]=1;
				}
			}
		}
		if(y>1) {
			if(y>1) {
				if(x<6) {
					if(cores[y-1][x+2]!=cores[y][x]) {
						movimentos[y-1][x+2]=1;
					}
				}
				if(x>1) {
					if(cores[y-1][x-2]!=cores[y][x]) {
						movimentos[y-1][x-2]=1;
					}
				}
			}
			if(x<7) {
				if(cores[y-2][x+1]!=cores[y][x]) {
					movimentos[y-2][x+1]=1;
				}
			}
			if(x>0) {
				if(cores[y-2][x-1]!=cores[y][x]) {
					movimentos[y-2][x-1]=1;
				}
			}
		}
		
		
		
	}
}
