import java.util.Scanner;

public class Main {

	
	private static Scanner sc1;
	private static Scanner sc;

	public static void main(String[] args) 
	{
		System.out.println("Projet de théorie des graphees S6\n");
		
		Graphe graphe = choisir_graphe();
		
		int x = -1;
		while(x == -1)
		{
			System.out.println("\nQue voulez faire ?");
			System.out.println("0°) Choisir un autre graphe");
			System.out.println("1°) Afficher le graphe");
			System.out.println("2°) Afficher la matrice d'adjacence et la matrice de valeur");
			System.out.println("3°) Détéction de circuit");
			System.out.println("4°) Calculer le rang");
			System.out.println("5°) Vérifier si c'est un graphe d'ordonnancement");
			System.out.println("6°) Calendrier");
			System.out.println("7°) Tout afficher");
			
			System.out.print("\nNuméro de l'action à faire : ");
			sc1 = new Scanner(System.in);
			x = sc1.nextInt();
			
			switch(x) {
			case 0:
				graphe = choisir_graphe();
				x = -1;
			    break;
			    
			  case 1:
				graphe.afficher_graphe();
				x = -1;
			    break;
			    
			  case 2:
			    graphe.matrice_adjacence();
			    graphe.matrice_valeur();
			    x = -1;
			    break;
			    
			  case 3:
					graphe.detection_circuit();
					x = -1;
				    break;
			 
			  case 4:
					graphe.calcul_rang();
					x = -1;
				    break;  
				    
			  case 5:
					graphe.verifier_ordonnancement();;
					x = -1;
				    break;  
				    
			  case 6:
					graphe.dates_au_plus_tot();
					graphe.dates_au_plus_tard();
					graphe.marges_totales();
					graphe.marges_libres();
					x = -1;
				    break; 
				    
			  case 7:
				  	System.out.println("/// AFFICHAGE DU GRAPHE ///\n");
				  	graphe.afficher_graphe();
				  	System.out.print("\n\n");
				  	System.out.println("/// AFFICHAGE DU GRAPHE ///\n\n");
				  	
				  	System.out.println("/// AFFICHAGE DES MATRICES ///\n");
				  	graphe.matrice_adjacence();
				  	System.out.print("\n\n");
				  	graphe.matrice_valeur();
				  	System.out.print("\n\n");
				  	System.out.println("/// AFFICHAGE DES MATRICES ///\n\n");
				    
				  	System.out.println("/// CALCUL DU RANG ///\n\n");
				    graphe.calcul_rang();
				    System.out.println("/// CALCUL DU RANG ///\n\n");
				    
				    System.out.println("/// ORDONNANCEMENT ///\n\n");
				    graphe.calendrier();
				    System.out.print("\n\n");
				    System.out.println("/// ORDONNANCEMENT ///\n\n");
					x = -1;
				    break; 
				    
			  default:
				  System.out.println("Veuillez choisir un numéro cohérent !");
				  x = -1;
			}
		}
	}
	
	public static Graphe choisir_graphe()
	{
		System.out.print("\nChoississez un numéro de graphe : ");
		sc = new Scanner(System.in);
		String nom_graphe = "A9-graphe" + sc.nextLine() + ".txt";
		Graphe graphe = new Graphe(nom_graphe);
		return graphe;
	}

}
