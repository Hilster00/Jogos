
public class Mover_pecas {
	private int x,y;
	private String cores[][] = new String[8][8];
	private int destino[][] = new int[8][8];
	Mover_pecas(int y,int x){
		this.x=x;
		this.y=y;
		for(int j=0;j<8;j++) {
			for(int i=0;i<8;i++) {
				destino[j][i]=0;
			}
		}
	}
	public void setCores(int y,int x,String cor) {
		cores[y][x]=cor;
	}
	public int getMovimentos(int y,int x) {
		return destino[y][x];
	}
	public void setMovimentos(String tipo) {
		
		if(tipo=="Peao") {
			Mover_peao mover = new Mover_peao(y,x);
			for(int j=0;j<8;j++) {
				for(int i=0;i<8;i++) {
					mover.setCores(j, i, cores[j][i]);
				}
			}
			mover.setMovimentos();			
			
			for(int j=0;j<8;j++) {
				for(int i=0;i<8;i++) {
					destino[j][i]=mover.getMovimentos(j,i);
					
				}
			}
			
		}
		if(tipo=="Rei") {
			Mover_rei mover = new Mover_rei(y,x);
			for(int j=0;j<8;j++) {
				for(int i=0;i<8;i++) {
					
					mover.setCores(j, i, cores[j][i]);
				}
			}
			mover.setMovimentos();
			for(int j=0;j<8;j++) {
				for(int i=0;i<8;i++) {
					destino[j][i]=mover.getMovimentos(j,i);
				}
			}
		}
		if(tipo=="Cavalo") {
			Mover_cavalo mover = new Mover_cavalo(y,x);
			for(int j=0;j<8;j++) {
				for(int i=0;i<8;i++) {
					
					mover.setCores(j, i, cores[j][i]);
				}
			}
			mover.setMovimentos();
			for(int j=0;j<8;j++) {
				for(int i=0;i<8;i++) {
					destino[j][i]=mover.getMovimentos(j,i);
				}
			}
		}
		if(tipo=="Torre") {
			Mover_torre mover = new Mover_torre(y,x);
			for(int j=0;j<8;j++) {
				for(int i=0;i<8;i++) {
					
					mover.setCores(j, i, cores[j][i]);
				}
			}
			mover.setMovimentos();
			for(int j=0;j<8;j++) {
				for(int i=0;i<8;i++) {
					destino[j][i]=mover.getMovimentos(j,i);
				}
			}
		}
		if(tipo=="Bispo") {
			Mover_bispo mover = new Mover_bispo(y,x);
			for(int j=0;j<8;j++) {
				for(int i=0;i<8;i++) {
					
					mover.setCores(j, i, cores[j][i]);
				}
			}
			mover.setMovimentos();
			for(int j=0;j<8;j++) {
				for(int i=0;i<8;i++) {
					destino[j][i]=mover.getMovimentos(j,i);
				}
			}
		}
		if(tipo=="Rainha") {
			Mover_rainha mover = new Mover_rainha(y,x);
			for(int j=0;j<8;j++) {
				for(int i=0;i<8;i++) {
					mover.setCores(j, i, cores[j][i]);
				}
			}
			mover.setMovimentos();			
			
			for(int j=0;j<8;j++) {
				for(int i=0;i<8;i++) {
					destino[j][i]=mover.getMovimentos(j,i);
					
				}
			}
			
		}	
	}
}
