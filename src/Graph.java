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
		
		this.classer_liste_sommets();
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
	
	private void classer_liste_sommets()
	{
		ArrayList<Sommet> tempo_liste_sommets = new ArrayList<Sommet>();
		
		for(int i = 0 ; i < this.liste_sommets.size() ; i++)
		{
			for(int j = 0 ; j < this.liste_sommets.size() ; j++)
			{	
				if(Integer.parseInt(this.liste_sommets.get(j).getNom()) == i)
				{ tempo_liste_sommets.add(this.liste_sommets.get(j)); }
			}
		}

		this.liste_sommets = tempo_liste_sommets;
	}
	
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
		System.out.println("\nCe graph possède " + this.get_nb_sommets() + " sommets !");
		System.out.println("Ce graph possède " + this.get_nb_transition() + " arcs !\n");
		
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
		System.out.println("\nMatrice d'adjacence\n");
		
		for(int i = 0 ; i < this.liste_sommets.size() ; i++)
		{ System.out.print("\t" + this.liste_sommets.get(i).getNom()); }
		
		System.out.print("\n");
		
		for(int j = 0 ; j < this.liste_sommets.size() ; j++)							//Vérifier pour chaque sommets
		{
			System.out.print(this.liste_sommets.get(j).getNom());						//Afficher le nom du sommet en début de ligne
			
			for(int k = 0 ; k < this.liste_sommets.size() ; k++)						// Vérifier s'il y a un arc vers chacun des autres sommets
			{
				if(this.liste_sommets.get(j).get_nb_arc() == 0)							// Si aucun arc n'a pour départ le sommet étudier
				{
					System.out.print("\tF");											// Se décaler et afficher X
				}
				else
				{
					int condition = 0;
					for(int l = 0 ; l < this.liste_sommets.get(j).get_nb_arc() ; l++)	// Vérifier chacun des arcs partant du sommet
					{
						if(this.liste_sommets.get(j).getArc(l).getSuccesseur().equals(this.liste_sommets.get(k).getNom()))		// Si un arc correspond
						{
							condition = 1;
						}
					}
					if(condition == 1)
					{
						System.out.print("\tV");
					}
					else
					{
						System.out.print("\tF");
					}
				}
			}
			System.out.print("\n");
		}
	}
	
	public void matrice_valeur()
	{
		System.out.println("\nMatrice des valeurs\n");
		
		for(int i = 0 ; i < this.liste_sommets.size() ; i++)
		{ System.out.print("\t" + this.liste_sommets.get(i).getNom()); }
		
		System.out.print("\n");
		
		for(int j = 0 ; j < this.liste_sommets.size() ; j++)							//Vérifier pour chaque sommets
		{
			System.out.print(this.liste_sommets.get(j).getNom());						//Afficher le nom du sommet en début de ligne
			
			for(int k = 0 ; k < this.liste_sommets.size() ; k++)						// Vérifier s'il y a un arc vers chacun des autres sommets
			{
				if(this.liste_sommets.get(j).get_nb_arc() == 0)							// Si aucun arc n'a pour départ le sommet étudier
				{
					System.out.print("\t*");											// Se décaler et afficher X
				}
				else
				{
					int condition = 0;
					int position = 0;
					for(int l = 0 ; l < this.liste_sommets.get(j).get_nb_arc() ; l++)	// Vérifier chacun des arcs partant du sommet
					{
						if(this.liste_sommets.get(j).getArc(l).getSuccesseur().equals(this.liste_sommets.get(k).getNom()))		// Si un arc correspond
						{
							position = l;
							condition = 1;
						}
					}
					if(condition == 1)
					{
						System.out.print("\t" + this.liste_sommets.get(j).getArc(position).getValeur());
					}
					else
					{
						System.out.print("\t*");
					}
				}
			}
			System.out.print("\n");
		}
	}
	
	/// METHODES ///
}
