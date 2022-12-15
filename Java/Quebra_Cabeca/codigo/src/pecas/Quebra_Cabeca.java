package pecas;
//import java.util.Random;
public class Quebra_Cabeca {
	public Peca pecas[][] = new Peca[4][4];
	//private int x,y;
	//private Random r = new Random();
    
    public Quebra_Cabeca() {
    	    for(int i=0;i<4;i++) {
    		 for(int j=0;j<4;j++) {
    			/*
    			while(true) {
    				x = r.nextInt( 4 ) ;
        			y = r.nextInt( 4 ) ;        			
                	if(pecas[y][x]==null) {
                		pecas[y][x]=new Peca(i,j);
                		break;
                	}
                	
    			}*/
	    		//testes
	         	
    			pecas[i][j]=new Peca(i,j);
        	 } 
    	 }    	  
    }
}
   


