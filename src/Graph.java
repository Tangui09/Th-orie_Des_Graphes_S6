import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph 
{
	private int nb_sommets;
	private int nb_arc;
	private ArrayList<Sommet> liste_sommets = new ArrayList<Sommet>();
	
	private Scanner sc;

	
	
	
	/// CONSTRUCTEURS ///
	
	public Graph(String nom_fichier)
	{
		try
		{
			sc = new Scanner(new File(nom_fichier));
			while(sc.hasNext())
			{

				String  ligne = sc.nextLine();    
			    String[] strs = ligne.trim().split("\\s+");
			    
			    if(strs.length == 1)
			    {
			    	Sommet nouveau_sommet = new Sommet(strs[0]);
			    	liste_sommets.add(nouveau_sommet);
			    }
			    else
			    {
			    	if(sommet_existant(strs[0]) == -1)
			    	{
			    		Sommet nouveau_sommet = new Sommet(strs[0]);
				    	liste_sommets.add(nouveau_sommet);
				    	nouveau_sommet.nouvelle_transition(strs[1], Integer.parseInt(strs[2]));
			    	}
			    	else
			    	{
			    		liste_sommets.get(sommet_existant(strs[0])).nouvelle_transition(strs[1], Integer.parseInt(strs[2]));;
			    	}
			    }
			}
		}catch(Exception e) {}
		
		this.set_nb_sommets(liste_sommets.size());
	}
	
	/// CONSTRUCTEURS ///
	
	
	
	/// GETTER AND SETTER ///

	public int get_nb_sommets() 
	{ return nb_sommets; }

	public void set_nb_sommets(int nb_sommets) 
	{ this.nb_sommets = nb_sommets; }
	
	public int get_nb_transition()
	{
		for(int i = 0 ; i < this.get_nb_sommets() ; i++)
		{ this.nb_arc += this.liste_sommets.get(i).get_nb_arc(); }
		
		return nb_arc;
	}
	
	/// GETTER AND SETTER ///
	
	
	
	/// METHODES ///
	
 	public int sommet_existant(String new_sommet)
	{
		for(int i = 0 ; i < liste_sommets.size() ; i++)
		{
			if(liste_sommets.get(i).getNom().equals(new_sommet))
			{
				return i;
			}
		}
		return -1;
	}
	
	public void afficher_graph()
	{
		System.out.println("\nCe graph poss�de " + this.get_nb_sommets() + " sommets !");
		System.out.println("Ce graph poss�de " + this.get_nb_transition() + " arcs !\n");
		
		for(int i = 0 ; i < this.get_nb_sommets() ; i++)
		{
			if(liste_sommets.get(i).get_nb_arc() == 0)
			{
				System.out.println(liste_sommets.get(i).getNom());
			}
			else
			{
				for(int j = 0 ; j < liste_sommets.get(i).get_nb_arc() ; j++)
				{
					Arc t = liste_sommets.get(i).getArc(j);
					System.out.println(liste_sommets.get(i).getNom() + " -> " + t.getSuccesseur() + " = " + t.getValeur());
				}
			}
		}
	}
	
	public void matrice_adjacence()
	{
		
	}
	/// METHODES ///
}
