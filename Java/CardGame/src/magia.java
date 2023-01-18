package src;

public class magia {
	private int cura_nivel3[][][] = new int[2][5][5];
    private int dano_nivel3[][][] = new int[2][5][5];
    private int custo_nivel3[][][] = new int[2][5][5];
    
    private int cura_nivel2[][] = new int[2][5];
    private int dano_nivel2[][] = new int[2][5];
    private int custo_nivel2[][] = new int[2][5];
    
    private int recuperar_mana[] = new int[5];
    
    public String tipo(int carta1){
        if(carta1<=2){
            return "Cura";
        }else{
            if(carta1<=4){
                return "Dano";
            }else{
            	//uso futuro
                return "Especial";
            }            
        }
    }
    
    //magias de nivel 3
    public int custo(int carta1,int carta2,int carta3){
    	if(carta1<=2){
    		return this.custo_nivel3[carta1-1][carta2-1][carta3-1];
        }
    	return this.custo_nivel3[carta1-3][carta2-1][carta3-1];
    }
    public int efeito(int carta1,int carta2,int carta3){
        if(carta1<=2){
            return this.dano_nivel3[carta1-1][carta2-1][carta3-1];
        }
        return this.dano_nivel3[carta1-3][carta2-1][carta3-1];
    }
    
    
    //magias de nivel 2
    public int custo(int carta1,int carta2){
    	if(carta1<=2){
    		return this.custo_nivel2[carta1-1][carta2-1];
        }
    	return this.custo_nivel2[carta1-3][carta2-1];
    }
    public int efeito(int carta1,int carta2){
        if(carta1<=2){
            return this.dano_nivel2[carta1-1][carta2-1];
        }
        return this.dano_nivel2[carta1-3][carta2-1];
    }
    
    //magias de nivel 1
    public int efeito(int carta1){
        return this.recuperar_mana[carta1-1];
    }
    
    //inicializador de magias
    public magia(){
        
        for(int i=0;i<2;i++){
            for(int j=0;j<5;j++){
                for(int k=0;k<5;k++){
                    this.cura_nivel3[i][j][k]=(i+1)*100+(j+1)*50+(k+1)*100;
                    this.dano_nivel3[i][j][k]=(i+1)*100+(j+1)*25+(k+1)*75;
                    this.custo_nivel3[i][j][k]=(i+1)*50+(j+1)*15+(k+1)*50;
                }
            }
        }
        
        for(int i=0;i<2;i++){
            for(int j=0;j<5;j++){
                this.cura_nivel2[i][j]=(i+1)*100+(j+1)*50;
                this.dano_nivel2[i][j]=(i+1)*100+(j+1)*25;
                this.custo_nivel2[i][j]=(i+1)*50+(j+1)*15;
            }
        }
        
       this.recuperar_mana[0]=200;
       this.recuperar_mana[1]=200;
       this.recuperar_mana[2]=300;
       this.recuperar_mana[3]=300;
       this.recuperar_mana[4]=600;
    }
}
