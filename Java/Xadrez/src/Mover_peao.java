
public class Mover_peao {
	private int x,y;
	private String cores[][]=new String[8][8];
	private int movimentos[][] = new int[8][8];
	
	Mover_peao(int y,int x){
		this.x=x;
		this.y=y;		
		for(int j=0;j<8;j++) {
			for(int i=0;i<8;i++) {
				movimentos[j][i]=0;
			}
			
		}
	}
	
	public void setCores(int y,int x,String cor) {		
		cores[y][x]=cor;
	}
	public void setMovimentos() {
		if(cores[y][x] =="Branco") {
			if(y==1 && cores[y+2][x]=="Vazio" && cores[y+1][x]=="Vazio" ) {				
				this.movimentos[y+2][x]=1;
			}
			if(y<7 && cores[y+1][x]=="Vazio" ) {
				this.movimentos[y+1][x]=1;
			}			
			if(x<7 && y<7 && cores[y+1][x+1]=="Preto" ) {
				this.movimentos[y+1][x+1]=1;
			}
			if(x>0 && y<7 && cores[y+1][x-1]=="Preto" ) {
				this.movimentos[y+1][x-1]=1;
			}
			
		}else {
			if(y==6 && cores[y-2][x]=="Vazio" && cores[y-1][x]=="Vazio" ) {
				this.movimentos[y-2][x]=1;
			}
			if(y>0 && cores[y-1][x]=="Vazio" ) {
				this.movimentos[y-1][x]=1;
			}
			if(x>0 && y>0 && cores[y-1][x-1]=="Branco" ) {
				this.movimentos[y-1][x-1]=1;
			}
			if(x<7 && y>0 && cores[y-1][x+1]=="Branco" ) {
				this.movimentos[y-1][x+1]=1;
			}
		}
		
	}
	public int getMovimentos(int y,int x) {
		return movimentos[y][x];
	}
}
