package src;
import java.util.Random;

public class deck {

    private int baralho[] = new int[25];
    private int quantidade;
    
    public deck(){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                this.baralho[(j*5)+i]=(j+1);
            }
        }
        this.quantidade=25;
    }
    
    public int comprar_carta(){
        if(quantidade>0){
            Random random = new Random();
            int p=random.nextInt(this.quantidade+1);
            int carta=this.baralho[p];
            this.baralho[p]=0;
            this.quantidade-=1;
            for(int i=p;i<quantidade;i++){
                this.baralho[i]=baralho[i+1];
            }
            return carta;
        }else{
            return 0;
        }
        
    }
    
    public int getquantidade(){
        return this.quantidade;
    }
}
