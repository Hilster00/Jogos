
public class Mover_rei {
	private int x,y;
	private String cores[][] = new String[8][8];
	private int movimentos[][] = new int[8][8];
	Mover_rei(int y,int x){
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
	public void setMovimentos() {
		
		if(y<7) {
			if(cores[y+1][x]!=cores[y][x]) {
				movimentos[y+1][x]=1;
			}
			if(x<7) {
				if(cores[y+1][x+1]!=cores[y][x]) {
					movimentos[y+1][x+1]=1;
				}
			}
			if(x>0) {
				if(cores[y+1][x-1]!=cores[y][x]) {
					movimentos[y+1][x-1]=1;
				}
			}
		}
		if(y>0) {
			if(cores[y-1][x]!=cores[y][x]) {
				movimentos[y-1][x]=1;
			}
			if(x<7) {
				if(cores[y-1][x+1]!=cores[y][x]) {
					movimentos[y-1][x+1]=1;
				}
			}
			if(x>0) {
				if(cores[y-1][x-1]!=cores[y][x]) {
					movimentos[y-1][x-1]=1;
				}
			}
		}
		if(x<7) {
			if(cores[y][x+1]!=cores[y][x]) {
				movimentos[y][x+1]=1;
			}
		}
		if(x>0) {
			if(cores[y][x-1]!=cores[y][x]) {
				movimentos[y][x-1]=1;
			}
		}
		
	}
	public int getMovimentos(int y,int x) {
		return movimentos[y][x];
	}
}
