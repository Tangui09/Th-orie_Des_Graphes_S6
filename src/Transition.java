
public class Transition 
{

	private String successeur;
	private int valeur;
	
	
	
	/// CONSTRUCTEUR ///
	
	public Transition(String successeur, int valeur_trans) 
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
