package pecas;

public class Peca {
	private int i,j;
	
	public Peca(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}
	public Peca() {
		this.i = 0;
		this.j = 0;
		
	}
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}


}
