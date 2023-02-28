
public class Mover_bispo {
	private int x,y;
	private String cores[][] = new String[8][8];
	private int movimentos[][] = new int[8][8];
	Mover_bispo(int y,int x){
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
		for(int i=1;i<8;i++) {
			if(y+i >7 || x+i >7) {
				break;
			}
			if(cores[y+i][x+i]!=cores[y][x]) {
				movimentos[y+i][x+i]=1;
				if(cores[y+i][x+i]!="Vazio") {
					break;
				}
			}else {
				break;
			}
		}
		for(int i=1;i<8;i++) {
			if(y+i >7 || x-i <0) {
				break;
			}
			if(cores[y+i][x-i]!=cores[y][x]) {
				movimentos[y+i][x-i]=1;
				if(cores[y+i][x-i]!="Vazio") {
					break;
				}
			}else {
				break;
			}
		}
		for(int i=1;i<8;i++) {
			if(y-i <0 || x-i <0) {
				break;
			}
			if(cores[y-i][x-i]!=cores[y][x]) {
				movimentos[y-i][x-i]=1;
				if(cores[y-i][x-i]!="Vazio") {
					break;
				}
			}else {
				break;
			}
		}
		
		for(int i=1;i<8;i++) {
			if(y-i <0 || x+i >7) {
				break;
			}
			if(cores[y-i][x+i]!=cores[y][x]) {
				movimentos[y-i][x+i]=1;
				if(cores[y-i][x+i]!="Vazio") {
					break;
				}
			}else {
				break;
			}
		}	
		
		
		
		
		
	}
}
