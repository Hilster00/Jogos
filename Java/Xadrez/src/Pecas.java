
import java.util.ArrayList;
public class Pecas implements Clonar{
	
	private String tipo, cor;
	private int x,y;
	
	public Pecas() {
		this.x=0;
		this.y=0;
		this.tipo="Vazio";
		this.cor="Vazio";
		
	}
	public Pecas(int x,int y,String tipo, String cor){
		this.x=x;
		this.y=y;
		this.tipo=tipo;
		this.cor=cor;
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	
	public String getCor() {
		return this.cor;
	}
	public String getTipo() {
		return this.tipo;
	}
	public Clonar getClonar(){
	        int temporario;
	        temporario=x+1;
	        return new Pecas(temporario,y,tipo,cor);
	}
	
}
