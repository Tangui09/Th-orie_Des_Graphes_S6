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
			sc = new Scanner(new File(nom_fichier));							// Trouver le fichier
			
			this.set_nb_sommets(Integer.parseInt(sc.nextLine()));				// La première ligne nous donne le nombre de sommet dans le graph
			this.set_nb_arc(Integer.parseInt(sc.nextLine()));					// La deuxième ligne correspond au nombre d'arcs dans le graph
			
			while(sc.hasNext())
			{
				String ligne = sc.nextLine();    													// Récupérer la ligne
			    String[] strs = ligne.trim().split("\\s+");											// Créer un tableau avec chaque élément de le ligne séparé par un espace
			    
			    if(sommet_existant(strs[0]) == -1)												// Si le sommet n'existe pas encore
			    {
			    	Sommet nouveau_sommet = new Sommet(strs[0]);								// Créer un nouveau sommet
				    liste_sommets.add(nouveau_sommet);											// L'ajouter à la liste
				    nouveau_sommet.nouvel_arc(strs[1], Integer.parseInt(strs[2]));				// Lui ajouter un arc
				    	
				    if(sommet_existant(strs[1]) == -1)											// Si le successeur n'existe pas encore
				    {
				    	Sommet nouveau_sommet1 = new Sommet(strs[1]);							// Créer le sommet du successeur
				    	liste_sommets.add(nouveau_sommet1);										// L'ajouter à la liste
				    }
			    }
			    else
			    {
			    	liste_sommets.get(sommet_existant(strs[0])).nouvel_arc(strs[1], Integer.parseInt(strs[2]));;		// Récupérer le sommet existant pour lui ajouter son nouvel arc
			    		
			    	if(sommet_existant(strs[1]) == -1)											// Si le successeur n'existe pas encore
				    {
				    	Sommet nouveau_sommet1 = new Sommet(strs[1]);							// Créer le sommet du successeur
				    	liste_sommets.add(nouveau_sommet1);										// L'ajouter à la liste
				    }
			    }
			}
		}catch(Exception e) {}
		
		this.classer_liste_sommets();															// Classer la liste
	}
	
	/// CONSTRUCTEURS ///
	
	
	
	/// GETTER AND SETTER ///

	public int get_nb_sommets() 														// Récupérer le nombre de sommet du graph
	{ return nb_sommets; }

	public void set_nb_sommets(int nb_sommets) 
	{ this.nb_sommets = nb_sommets; }
	
	public int get_nb_arc()																// Récupérer le nombre d'arc du graph
	{
		for(int i = 0 ; i < this.get_nb_sommets() ; i++)
		{ this.nb_arc += this.liste_sommets.get(i).get_nb_arc(); }
		
		return nb_arc;
	}
	
	public void set_nb_arc(int nb_arc) 
	{ this.nb_arc = nb_arc; }
	/// GETTER AND SETTER ///
	
	
	
	/// METHODES ///
	
	private void classer_liste_sommets()						// Classer les sommet par ordre croissant
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
	
 	public int sommet_existant(String new_sommet)				// Vérifier si un sommet existe déjà lors de la lecture
	{
		for(int i = 0 ; i < liste_sommets.size() ; i++)
		{
			if(liste_sommets.get(i).getNom().equals(new_sommet))
			{
				return i;										// S'il existe déjà, retourner sa position dans la liste
			}
		}
		return -1;												// Sinon, retourner -1 pour signaler qu'il n'existe pas
	}
	
	public void afficher_graph()
	{
		System.out.println("\nCe graph possède " + this.get_nb_sommets() + " sommets !");
		System.out.println("Ce graph possède " + this.get_nb_arc() + " arcs !\n");
		
		for(int i = 0 ; i < this.get_nb_sommets() ; i++)					// Parcourir la list des sommets du graph
		{
			if(liste_sommets.get(i).get_nb_arc() == 0)						// Si aucun arc ne part de ce sommet
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
	
	public boolean detection_circuit()
	{
		boolean entree_restante = true;
		ArrayList<Sommet> liste_sommets_circuit = new ArrayList<Sommet>();
		liste_sommets_circuit.addAll(this.liste_sommets);
		
		ArrayList<Sommet> liste_sommets_hors_circuit = new ArrayList<Sommet>();		// On supprimera les sommets pouvant faire partie d'un circuit
		
		while(entree_restante == true)
		{
			liste_sommets_hors_circuit.addAll(liste_sommets_circuit);
			
			System.out.println("Points d'entrée :");
			for(int i = 0; i < liste_sommets_circuit.size() ; i++)						// On vérifie pour tous les sommets restant
			{
				if(liste_sommets_circuit.get(i).get_nb_arc() != 0)						// Si des arcs partent depuis le sommet sommet
				{
					for(int j = 0 ; j < liste_sommets_circuit.get(i).get_nb_arc() ; j++)			// On vérifie pour chacun des arcs
					{
						int position = 0;
						for(int k = 0 ; k < liste_sommets_hors_circuit.size() ; k++)
						{
							if(liste_sommets_circuit.get(i).getArc(j).getSuccesseur().equals(liste_sommets_hors_circuit.get(position).getNom()))	// Si le successeur est un sommet encore présent	
							{																														// Alors il peut faire partie d'un circuit
								liste_sommets_hors_circuit.remove(position);																		// On le retire des sommets potentiellement hors circuit
								position -= 1;																										
							}
							position += 1;
						}
					}	
				}
			}
			
			if(liste_sommets_hors_circuit.size() != 0)																		// Si des sommets sont des points d'entrée
			{
				for(int nb_sommet = 0 ; nb_sommet < liste_sommets_hors_circuit.size() ; nb_sommet++)
				{
					System.out.print(liste_sommets_hors_circuit.get(nb_sommet).getNom() + " ");								// Afficher les noms des sommets
					
					for(int elimination = 0 ; elimination < liste_sommets_circuit.size() ; elimination++)					// Chercher la position du sommet à retirer de la liste
					{
						if(liste_sommets_circuit.get(elimination).getNom().equals(liste_sommets_hors_circuit.get(nb_sommet).getNom()))		// Si même nom
						{ 
							liste_sommets_circuit.remove(elimination); 														// Eliminer le sommet correspondant
						}
					}
				}
				
				System.out.println("\nSuppression des points d'entrée !");
				System.out.println("Points restant : ");
				
				if(liste_sommets_circuit.size() != 0)														// S'il reste des sommets, affficher les sommets restants
				{
					for(int restant = 0 ; restant < liste_sommets_circuit.size() ; restant++)
					{
						System.out.print(liste_sommets_circuit.get(restant).getNom() + " ");
					}
					System.out.print("\n");
				}
				else
				{
					System.out.println("Aucun !");														// Il n'y a plus de points restants
				}
				
				liste_sommets_hors_circuit.clear();				// Reset la liste des circuits à retirer
			}
			else																							// S'il n'y a plus de sommets à retirer
			{
				System.out.println("Aucun !");																// Plus de points restants ni de points à retirer
				
				if(liste_sommets_circuit.size() == 0)														// Vérifier s'il reste des sommets dits "hors-circuits" ou non
				{
					System.out.println("Il n'y a pas de circuit dans ce graph !");
					entree_restante = false;
					return false;
				}
				else
				{
					System.out.println("Attention ! Il y a un circuit dans ce graph !");
					entree_restante = false;
					return true;
				}
			}
		}
		return true;
	}
	
	/// METHODES ///
}
