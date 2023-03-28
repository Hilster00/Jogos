package Pecas;

public class pecas {
	private int x,y;
	
	public pecas() {
		this.x=0;
		this.y=0;
	}
	
	public pecas(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public void sety(int y) {
		this.y=y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
