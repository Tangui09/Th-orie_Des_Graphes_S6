import java.util.ArrayList;

public class Sommet 
{
	private String nom;
	private ArrayList<Arc> liste_arcs = new ArrayList<Arc>();
	private int rang;
	
	
	
	/// CONSTRUCTEURS ///
	
	public Sommet(String nom)
	{
		this.setNom(nom);
	}
	
	/// CONSTRUCTEURS ///

	
	
	/// METHODES ///
	
	public void nouvel_arc(String successeur, int valeur_trans)
	{
		Arc trans = new Arc(successeur, valeur_trans);
		liste_arcs.add(trans);
	}
	
	/// METHODES ///
	
	
	
	/// GETTER ET SETTER ///
	
	public String getNom() 
	{ return nom; }

	public void setNom(String nom) 
	{ this.nom = nom; }

	public int get_nb_arc() 
	{ return liste_arcs.size(); }
	
	public Arc getArc(int position)
	{ return liste_arcs.get(position); }

	public int getRang() 
	{ return rang; }

	public void setRang(int rang) 
	{ this.rang = rang; }
	
	/// GETTER ET SETTER ///
}
