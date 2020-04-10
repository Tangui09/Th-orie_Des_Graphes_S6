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
		System.out.println("\nMatrice d'adjacence\n");
		
		for(int i = 0 ; i < this.liste_sommets.size() ; i++)
		{ System.out.print("\t" + this.liste_sommets.get(i).getNom()); }
		
		System.out.print("\n");
		
		for(int j = 0 ; j < this.liste_sommets.size() ; j++)							//V�rifier pour chaque sommets
		{
			System.out.print(this.liste_sommets.get(j).getNom());						//Afficher le nom du sommet en d�but de ligne
			
			for(int k = 0 ; k < this.liste_sommets.size() ; k++)						// V�rifier s'il y a un arc vers chacun des autres sommets
			{
				if(this.liste_sommets.get(j).get_nb_arc() == 0)							// Si aucun arc n'a pour d�part le sommet �tudier
				{
					System.out.print("\tF");											// Se d�caler et afficher X
				}
				else
				{
					int condition = 0;
					for(int l = 0 ; l < this.liste_sommets.get(j).get_nb_arc() ; l++)	// V�rifier chacun des arcs partant du sommet
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
		
		for(int j = 0 ; j < this.liste_sommets.size() ; j++)							//V�rifier pour chaque sommets
		{
			System.out.print(this.liste_sommets.get(j).getNom());						//Afficher le nom du sommet en d�but de ligne
			
			for(int k = 0 ; k < this.liste_sommets.size() ; k++)						// V�rifier s'il y a un arc vers chacun des autres sommets
			{
				if(this.liste_sommets.get(j).get_nb_arc() == 0)							// Si aucun arc n'a pour d�part le sommet �tudier
				{
					System.out.print("\t*");											// Se d�caler et afficher X
				}
				else
				{
					int condition = 0;
					int position = 0;
					for(int l = 0 ; l < this.liste_sommets.get(j).get_nb_arc() ; l++)	// V�rifier chacun des arcs partant du sommet
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
	
	public void detection_circuit()
	{
		boolean entree_restante = true;
		ArrayList<Sommet> liste_sommets_circuit = this.liste_sommets;
		
		while(entree_restante == true)
		{
			ArrayList<Sommet> liste_sommets_hors_circuit = liste_sommets_circuit;		// On supprimera les sommets pouvant faire partie d'un circuit
			
			System.out.println("Points d'entr�e :");
			for(int i = 0; i < liste_sommets_circuit.size() ; i++)						// On v�rifie pour tous les sommets restant
			{
				if(liste_sommets_circuit.get(i).get_nb_arc() != 0)						// Si des arcs partent depuis le sommet sommet
				{
					for(int j = 0 ; j < this.liste_sommets.get(i).get_nb_arc() ; j++)			// On v�rifie pour chacun des arcs
					{
						for(int k = 0 ; k < liste_sommets_circuit.size() ; k++)
						{
							if(liste_sommets_circuit.get(i).getArc(j).getSuccesseur().equals(liste_sommets_circuit.get(k).getNom()))	// Si le successeur est un sommet encore pr�sent	
							{																											// Alors il peut faire partie d'un circuit
								liste_sommets_hors_circuit.remove(k);																	// On le retire des sommets potentiellement hors circuit
							}
						}
					}
					
					if(liste_sommets_hors_circuit.size() != 0)																		// Si des sommets sont des points d'entr�e
					{
						for(int nb_sommet = 0 ; nb_sommet < liste_sommets_hors_circuit.size() ; nb_sommet++)
						{
							System.out.print(liste_sommets_hors_circuit.get(nb_sommet).getNom() + " ");								// Afficher les noms des sommets
							
							for(int elimination = 0 ; elimination < liste_sommets_circuit.size() ; elimination++)
							{
								if(liste_sommets_circuit.get(elimination).getNom().equals(liste_sommets_hors_circuit.get(nb_sommet).getNom()))		// Eliminer les sommets correspondant
								{ liste_sommets_circuit.remove(elimination); }
							}
						}
						System.out.print("\n");
					}
					else
					{
						System.out.print("Aucun !");
						entree_restante = false;
					}
				}
			}
		}
		
	}
	/// METHODES ///
}
