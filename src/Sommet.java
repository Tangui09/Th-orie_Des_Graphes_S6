import java.util.ArrayList;

public class Sommet 
{
	private String nom;
	private ArrayList<Arc> liste_arcs = new ArrayList<Arc>();
	private int rang;
	
	private boolean point_entree = false;
	private boolean point_sortie = false;
	
	
	
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
	
	public boolean isPoint_entree() 
	{ return point_entree; }

	public void setPoint_entree(boolean point_entree) 
	{ this.point_entree = point_entree; }

	public boolean isPoint_sortie() 
	{ return point_sortie; }

	public void setPoint_sortie(boolean point_sortie) 
	{ this.point_sortie = point_sortie; }
	
	/// GETTER ET SETTER ///
}
