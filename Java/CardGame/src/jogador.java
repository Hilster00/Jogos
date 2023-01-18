package src;

public class jogador {
		private int vida, mana, itens;
	    private String tipo_item="dobro";
	    
		public  jogador(int vida,int mana){
		    this.mana=mana;
		    this.vida=vida;
		    this.itens=0;
		}
		
		public jogador(){
		    this.mana=2000;
		    this.vida=4000;
		    this.itens=0;
		}
		
		//funcoes relacionadas a vida
		
		public int vida(){
		    return this.vida;
		}
		
		public void curar(int cura){
		    this.vida+=cura;
		}
		
		//retorna se o jogador esta vivo
		public boolean dano(int dano){
		    if((this.itens!=0)){
		        if(this.tipo_item=="escudo"){
		            this.itens-=1;
		        }else{
		            this.vida-=(dano/2);
		        }
		    }else{
		        this.vida-=dano;
		        if(vida<0){
	    	        this.vida=0;
	    	        return false;
		        }
		    }
		    return true;
		}
		
		public int mana(){
		    return this.mana;
		}
		
		public void recarregar_mana(int mana){
		    this.mana+=mana;
		}
		
		//retorna se gasto pode ser feito
		public boolean gastar_mana(int custo){
		    if(custo<=this.mana){
		        this.mana-=custo;
		        return true;
		    }else{
		        return false;
		    }
		}
		
		//estado do jogador
		public boolean estado_jogador(){
		    if(this.vida>0){
		        return true;
		    }else{
		        return false;
		    }
		}
		
		//itens jogador
		
		//retorna se é possivel adicionar item
		public boolean adicionar_item(String tipo_item,int quantidade){
		    if((tipo_item == this.tipo_item)||(this.itens==0)){
		        if(quantidade>0){
		            this.itens+=quantidade;
		            this.tipo_item=tipo_item;
		            return true;
		        }
		       return false;
		    }else{
		        return false;
		    }
		    
		}
}
