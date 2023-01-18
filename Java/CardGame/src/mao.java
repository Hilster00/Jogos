package src;

public class mao {
	
	private int mao[] = new int[10];
    private int quantidade;
    
    public mao(){
        this.quantidade=0;
        for(int i=0;i<10;i++){
            this.mao[i]=0;
        }
    }
    
    public int getquantidade(){
        return this.quantidade;
    }

    public void adicionar_carta(int carta){
        if(this.quantidade<10){
            this.mao[this.quantidade]=carta;
            this.quantidade+=1;
        }
    }
    
    public int carta(int posicao){
        return this.mao[posicao];
    }
    
    public int usar_carta(int posicao){
        
        int carta=this.mao[posicao];
        for(int i=posicao;i<quantidade;i++){
            this.mao[i]=this.mao[i+1];
        }
        this.mao[quantidade+1]=0;
        quantidade-=1;
        return carta;
    }
    
    public void ordenar(){
        for(int i=0;i<quantidade;i++){
            int m=i;
            for(int j=i+1;j<quantidade;j++){
                if(this.mao[i]>this.mao[j]){
                    m=j;
                }
            }
            int temp=this.mao[i];
            this.mao[i]=this.mao[m];
            this.mao[m]=temp;
        }
    }
}
