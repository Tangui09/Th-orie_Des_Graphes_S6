
public class L3_A9_Arc 
{
	private String successeur;
	private int valeur;
	
	
	/// CONSTRUCTEUR ///
	
	public L3_A9_Arc(String successeur, int valeur_trans) 
	{
		this.setSuccesseur(successeur);
		this.setValeur(valeur_trans);
	}

	/// CONSTRUCTEUR ///

	
	
	/// GETTER ET SETTER ///
	
	public String getSuccesseur() 
	{ return successeur; }

	public void setSuccesseur(String successeur) 
	{ this.successeur = successeur; }

	public int getValeur() 
	{ return valeur; }

	public void setValeur(int valeur) 
	{ this.valeur = valeur; }
	
	/// GETTER ET SETTER ///

}
