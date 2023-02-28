
public class Mover_torre {
	private int x,y;
	private String cores[][] = new String[8][8];
	private int movimentos[][] = new int[8][8];
	Mover_torre(int y,int x){
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
		for(int j=y+1;j<8;j++) {
			if(cores[j][x]!=cores[y][x]) {
				movimentos[j][x]=1;
				if(cores[j][x]!="Vazio") {
					break;
				}
			}else {
				break;
			}
		}
		for(int j=y-1;j>=0;j--) {
			if(cores[j][x]!=cores[y][x]) {
				movimentos[j][x]=1;
				if(cores[j][x]!="Vazio") {
					break;
				}
			}else {
				break;
			}
		}
		for(int i=x+1;i<8;i++) {
			if(cores[y][i]!=cores[y][x]) {
				movimentos[y][i]=1;
				if(cores[y][i]!="Vazio") {
					break;
				}
			}else {
				break;
			}
		}
		for(int i=x-1;i>=0;i--) {
			if(cores[y][i]!=cores[y][x]) {
				movimentos[y][i]=1;
				if(cores[y][i]!="Vazio") {
					break;
				}
			}else {
				break;
			}
		}
		
	}
}
