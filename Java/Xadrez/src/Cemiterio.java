
public class Cemiterio {
	private int cemiterio_b_x,cemiterio_p_x,cemiterio_b_y,cemiterio_p_y;
	
	public Cemiterio() {
		cemiterio_b_x=600;
		cemiterio_p_x=600;
		cemiterio_b_y=280;
		cemiterio_p_y=0;
	}
	
	public int getX(String cor) {
		if(cor=="Branco") {
			return cemiterio_b_x;
		}else {
			return cemiterio_p_x;
		}
	}
	public int getY(String cor) {
		if(cor=="Branco") {
			return cemiterio_b_y;
		}else {
			return cemiterio_p_y;
		}
	}
	public void ocupar_casa(String cor) {
		if(cor=="Branco") {
			cemiterio_b_x=cemiterio_b_x-40;
			if(cemiterio_b_x==280) {
				cemiterio_b_y=cemiterio_b_y-40;
				cemiterio_b_x=600;
			}
		}else {
			cemiterio_p_x=cemiterio_p_x-40;
			if(cemiterio_p_x==280) {
				cemiterio_p_y=cemiterio_p_y+40;
				cemiterio_p_x=600;
			}
		}
	}
}
