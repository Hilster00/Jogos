package pecas;
import java.util.Random;
public class Quebra_Cabeca {
	public Peca pecas[][] = new Peca[4][4];
	@SuppressWarnings("unused")
	private Random r = new Random();
    
    public Quebra_Cabeca() {
    	    for(int i=0;i<4;i++) {
    		 for(int j=0;j<4;j++) {
    			
    			pecas[i][j]=new Peca(i,j);
        	 } 
    	 }    	  
    }
}
   

