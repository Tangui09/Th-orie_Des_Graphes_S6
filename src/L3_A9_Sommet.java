import java.util.ArrayList;

public class L3_A9_Sommet 
{
	private String nom;
	private ArrayList<L3_A9_Arc> liste_arcs = new ArrayList<L3_A9_Arc>();
	private int rang;
	
	private boolean point_entree = false;
	private boolean point_sortie = false;
	
	private int date_au_plus_tot_sommet;
	private int date_au_plus_tard_sommet;
	
	private int marge_totale;
	private int marge_libre;
	
	
	/// CONSTRUCTEURS ///
	
	public L3_A9_Sommet(String nom)
	{
		this.setNom(nom);
	}
	
	/// CONSTRUCTEURS ///

	
	
	/// METHODES ///
	
	public void nouvel_arc(String successeur, int valeur_trans)
	{
		L3_A9_Arc trans = new L3_A9_Arc(successeur, valeur_trans);			// Créer un nouvel arc
		liste_arcs.add(trans);												// L'ajouter à la liste des arcs de ce sommet
	}
	
	/// METHODES ///
	
	
	
	/// GETTER ET SETTER ///
	
	public String getNom() 
	{ return nom; }

	public void setNom(String nom) 
	{ this.nom = nom; }

	public int get_nb_arc() 
	{ return liste_arcs.size(); }
	
	public L3_A9_Arc getArc(int position)
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

	public int getDate_au_plus_tot_sommet() 
	{ return date_au_plus_tot_sommet; }

	public void setDate_au_plus_tot_sommet(int date_au_plus_tot_sommet) 
	{ this.date_au_plus_tot_sommet = date_au_plus_tot_sommet; }

	public int getDate_au_plus_tard_sommet() 
	{ return date_au_plus_tard_sommet; }

	public void setDate_au_plus_tard_sommet(int date_au_plus_tard_sommet) 
	{ this.date_au_plus_tard_sommet = date_au_plus_tard_sommet; }

	public int getMarge_totale() 
	{ return marge_totale; }

	public void setMarge_totale(int marge_totale) 
	{ this.marge_totale = marge_totale; }

	public int getMarge_libre() 
	{ return marge_libre; }

	public void setMarge_libre(int marge_libre) 
	{ this.marge_libre = marge_libre; }
	
	/// GETTER ET SETTER ///
}
