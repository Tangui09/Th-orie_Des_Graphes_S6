import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Graphe 
{
	private int nb_sommets;
	private int nb_arc;
	private ArrayList<Sommet> liste_sommets = new ArrayList<Sommet>();
	
	private boolean verif_circuit = false;
	private boolean circuit;
	
	private int date_au_plus_tot = -1;
	private int date_au_plus_tard = -1;
	private int marge;
	
	private Scanner sc;

	
	
	
	/// CONSTRUCTEURS ///
	
	public Graphe(String nom_fichier)
	{
		try
		{
			sc = new Scanner(new File(nom_fichier));							// Trouver le fichier
			
			this.set_nb_sommets(Integer.parseInt(sc.nextLine()));				// La première ligne nous donne le nombre de sommet dans le graphe
			this.set_nb_arc(Integer.parseInt(sc.nextLine()));					// La deuxième ligne correspond au nombre d'arcs dans le graphe
			
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

	public int get_nb_sommets() 														// Récupérer le nombre de sommet du graphe
	{ return nb_sommets; }

	public void set_nb_sommets(int nb_sommets) 
	{ this.nb_sommets = nb_sommets; }
	
	public int get_nb_arc()																// Récupérer le nombre d'arc du graphe
	{
		for(int i = 0 ; i < this.get_nb_sommets() ; i++)
		{ this.nb_arc += this.liste_sommets.get(i).get_nb_arc(); }
		
		return nb_arc;
	}
	
	public void set_nb_arc(int nb_arc) 
	{ this.nb_arc = nb_arc; }
	
	public boolean isCircuit() 
	{ return circuit; }

	public void setCircuit(boolean circuit) 
	{ this.circuit = circuit; }
	
	public boolean isVerif_circuit() 
	{ return verif_circuit; }

	public void setVerif_circuit(boolean verif_circuit) 
	{ this.verif_circuit = verif_circuit; }
	
	public int getDate_au_plus_tot() 
	{ return date_au_plus_tot; }
	
	public void setDate_au_plus_tot(int date_au_plus_tot) 
	{ this.date_au_plus_tot = date_au_plus_tot; }

	public int getDate_au_plus_tard() 
	{ return date_au_plus_tard; }

	public void setDate_au_plus_tard(int date_au_plus_tard) 
	{ this.date_au_plus_tard = date_au_plus_tard; }

	public int getMarge() 
	{ return marge; }

	public void setMarge(int marge)
	{ this.marge = marge; }
	
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
	
	public void afficher_graphe()
	{
		System.out.println("\nCe graphe possède " + this.get_nb_sommets() + " sommets !");
		System.out.println("Ce graphe possède " + this.get_nb_arc() + " arcs !\n");
		
		for(int i = 0 ; i < this.get_nb_sommets() ; i++)					// Parcourir la list des sommets du graphe
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
		
		int rang = 0;
		
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
					
					for(int change_rang = 0 ; change_rang < this.liste_sommets.size() ; change_rang++)
					{
						if(this.liste_sommets.get(change_rang).getNom().equals(liste_sommets_hors_circuit.get(nb_sommet).getNom()))
						{
							this.liste_sommets.get(change_rang).setRang(rang);
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
					System.out.println("\nIl n'y a pas de circuit dans ce graphe !");
					this.setCircuit(false);
					this.setVerif_circuit(true);
					return false;
				}
				else
				{
					System.out.println("\nAttention ! Il y a un circuit dans ce graphe !");
					this.setCircuit(true);
					this.setVerif_circuit(true);
					return true;
				}
			}
			rang += 1;
		}
		return true;
	}
	
	public void calcul_rang()
	{
		boolean circuit = this.detection_circuit();
		
		if(circuit == true)																							// S'il y a un circuit, pas de calcul de rang possible
		{
			System.out.println("Calcul du rang impossible car il y a un circuit dans le graphe !");
		}
		else
		{
			System.out.print("Sommet");
			for(int i = 0 ; i < this.liste_sommets.size() ; i++)
			{
				System.out.print("\t" + this.liste_sommets.get(i).getNom());							// Afficher les sommets dans l'ordre
			}
			
			System.out.print("\n");
			
			System.out.print("Rang");
			for(int i = 0 ; i < this.liste_sommets.size() ; i++)										// Afficher les rangs des sommets correspondant
			{
				System.out.print("\t" + this.liste_sommets.get(i).getRang());
			}
		}
		
		System.out.print("\n\n\n");
	}
	
	public void verifier_ordonnancement()
	{
		if(isVerif_circuit() == false)
		{
			this.detection_circuit();
			if(this.circuit == true)
			{
				System.out.println("Il y a un circuit ! Ce n'est donc pas un graphe d'ordonnancement !");
				return;
			}
		}
		else
		{
			if(this.circuit == true)
			{
				System.out.println("Il y a un circuit ! Ce n'est donc pas un graphe d'ordonnancement !");
				return;
			}
		}
		
		System.out.println("La condition de l'absence de circuit est bien vérifiée !");
		
		
		
		
		int rang_0 = 0;
		int position_rang_0 = 0;
		
		int rang_max = 0;
		int position_rang_max = 0;
		int rang_max_valeur = 0;
		
		for(int i = 0; i < this.liste_sommets.size() ; i++)										// Vérifions les rangs de tous les sommets du graphe
		{
			if(this.liste_sommets.get(i).getRang() == 0)										// S'il est de rang 0
			{
				rang_0 += 1;																	// Alors on a un sommet de plus de rang 0
				position_rang_0 = i;															// On enregistre la position pour éventuellement l'afficher plus tard
			}
			else
			{
				if(this.liste_sommets.get(i).getRang() == rang_max_valeur)						// Si le sommet a le même rang que le rang max actuellement connu
				{
					rang_max += 1;																// Un sommet de plus avec le rang max
					position_rang_max = i;														// On enregistre la position pour éventuellement l'afficher plus tard
				}
				else if(this.liste_sommets.get(i).getRang() > rang_max_valeur)					// Si le rang du sommet étudié est plus grand que le rang max connu
				{
					rang_max = 1;																// Alors on a un sommet de rang max pour l'instant
					position_rang_max = i;														// On enregistre la position
					rang_max_valeur = this.liste_sommets.get(i).getRang();						// On garde en mémoire la valeur du rang max pour comparer par la suite
				}
			}
		}
		
		if(rang_0 == 1)																										// Si on a qu'un élément de rang 0
		{
			System.out.println("Un seul point d'entrée : " + this.liste_sommets.get(position_rang_0).getNom());
		}
		else
		{
			System.out.println("Il n'y a pas qu'un seul point d'entrée !");
			System.out.println("Ce n'est donc pas un graphe d'ordonnancement !");
			return;																											// On arrête de vérifier parce que ce n'est pas un graphe d'odonnancement
		}
		
		if(rang_max == 1)																									// Si on a un seul élément de rang max
		{
			System.out.println("Un seul point de sortie : " + this.liste_sommets.get(position_rang_max).getNom());
		}
		else
		{
			System.out.println("Il n'y a pas qu'un seul point de sortie !");
			System.out.println("Ce n'est donc pas un graphe d'ordonnancement !");
			return;																											// On arrête de vérifier parce que ce n'est pas un graphe d'odonnancement
		}
		
		
		
		
		
		
		for(int j = 0 ; j < this.liste_sommets.size() ; j++)							// Vérifions pour tous les arcs si c'est les mêmes valeur pour les arcs partant du sommet
		{
			if(this.liste_sommets.get(j).get_nb_arc() != 0)								// Si ce sommet a des successeurs
			{
				int valeur_arc = this.liste_sommets.get(j).getArc(0).getValeur();		// Enregistrons la valeur du premier arc
				
				for(int nb_arc = 0 ; nb_arc < liste_sommets.get(j).get_nb_arc() ; nb_arc++)			// Vérifions pour tous les arcs de ce sommet
				{
					if(liste_sommets.get(j).getArc(nb_arc).getValeur() != valeur_arc)		// Si la valeur de l'arc étudié est différente de celle enregistrée
					{
						System.out.println("Pas de valeurs identiques pour tous les arcs incidents vers l’extérieur à un sommet !");
						System.out.println("Ce n'est donc pas un graphe d'ordonnancement !");
						return;																				// On arrête de vérifier
					}
				}
			}
		}
		System.out.println("On a des valeurs identiques pour tous les arcs incidents vers l’extérieur d'un sommet !");
		
		
		
		
		
		
		
		for(int verif_rang_0 = 0 ; verif_rang_0 < this.liste_sommets.get(verif_rang_0).get_nb_arc() ; verif_rang_0++)			// Véridions si tous les arcs partant du point d'entrée sont à valeur nulle
		{
			if(this.liste_sommets.get(verif_rang_0).getArc(verif_rang_0).getValeur() != 0)
			{
				System.out.println("Pas de valeurs nulles pour tous les arcs incidents vers l’extérieur au point d’entrée !");
				System.out.println("Ce n'est donc pas un graphe d'ordonnancement !");
				return;	
			}
		}
		System.out.println("Valeurs nulles pour tous les arcs incidents vers l’extérieur du point d’entrée !");
		
		
		
		
		
		
		
		for(int verif_valeur_negative = 0 ; verif_valeur_negative < this.liste_sommets.size() ; verif_valeur_negative++)		// Véridions si aucun arcs n'est à valeur négative
		{
			if(this.liste_sommets.get(verif_valeur_negative).get_nb_arc() != 0)
			{
				for(int k = 0 ; k < this.liste_sommets.get(verif_valeur_negative).get_nb_arc() ; k++)
				{
					if(this.liste_sommets.get(verif_valeur_negative).getArc(k).getValeur() < 0)
					{
						System.out.println("Il y a au moins un arc à valeur négative !");
						System.out.println("Ce n'est donc pas un graphe d'ordonnancement !");
						return;	
					}
				}
			}
		}
		System.out.println("Valeurs positives ou nulles pour tous les arcs !");
		
		System.out.println("\nToutes les conditions sont vérifiées, c'est donc bien un graphe d'ordonnancement !\n\n");
	}


	public void dates_au_plus_tot()
	{
		
	}
	
	/// METHODES ///
}
