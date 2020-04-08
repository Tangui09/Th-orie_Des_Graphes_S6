import java.util.ArrayList;

public class Sommet 
{
	private String nom;
	private ArrayList<Transition> liste_transitions = new ArrayList<Transition>();
	
	
	
	/// CONSTRUCTEURS ///
	
	public Sommet(String nom)
	{
		this.setNom(nom);
	}
	
	/// CONSTRUCTEURS ///

	
	
	/// METHODES ///
	
	public void nouvelle_transition(String successeur, int valeur_trans)
	{
		Transition trans = new Transition(successeur, valeur_trans);
		liste_transitions.add(trans);
	}
	
	public int nombre_de_transitions()
	{ return liste_transitions.size(); }
	
	/// METHODES ///
	
	
	
	/// GETTER ET SETTER ///
	
	public String getNom() 
	{ return nom; }

	public void setNom(String nom) 
	{ this.nom = nom; }

	public int get_nb_transitions() 
	{ return liste_transitions.size(); }
	
	public Transition getTransition(int position)
	{ return liste_transitions.get(position); }
	
	/// GETTER ET SETTER ///
}
