package object;

public class Individu {
	private Poule poule1;
	private Poule poule2;
	
	public Individu(Poule poule1, Poule poule2) {
		this.setPoule1(poule1);
		this.setPoule2(poule2);
	}
	
	/**
	 * @return the poule1
	 */
	public Poule getPoule1() {
		return poule1;
	}
	/**
	 * @param poule1 the poule1 to set
	 */
	public void setPoule1(Poule poule1) {
		this.poule1 = poule1;
	}
	/**
	 * @return the poule2
	 */
	public Poule getPoule2() {
		return poule2;
	}
	/**
	 * @param poule2 the poule2 to set
	 */
	public void setPoule2(Poule poule2) {
		this.poule2 = poule2;
	}

	@Override
	public String toString() {
		String res = "";
		res = "Individu [";
		res += "Poule 1 : " + poule1.toString() + "\n";
		res += "Poule 2 : " + poule2.toString() + "\n";
		return res;
	}	
}
