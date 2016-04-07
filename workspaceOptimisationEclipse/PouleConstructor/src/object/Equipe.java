package object;

import java.util.Vector;

public class Equipe {
	private String name;
	private Vector<Integer> matrice;
	
	public Equipe() {
		this.setName("");
		this.setMatrice(new Vector<Integer>());
	}
	
	public Equipe(String name, Vector<Integer> matrice) {
		this.setName(name);
		this.setMatrice(matrice);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the matrice
	 */
	public Vector<Integer> getMatrice() {
		return this.matrice;
	}

	/**
	 * @param matrice the matrice to set
	 */
	public void setMatrice(Vector<Integer> matrice) {
		this.matrice = matrice;
	}

	@Override
	public String toString() {
		return "Equipe [name=" + name + ", matrice=" + matrice + "]";
	}	
}
