import java.util.Scanner;

public class Main {

	
	private static Scanner sc1;
	private static Scanner sc;

	public static void main(String[] args) 
	{
		System.out.println("Projet de théorie des graphees S6\n");
		
		System.out.print("Choississez le nom d'un graphe : ");
		sc = new Scanner(System.in);
		String nom_graphe = sc.nextLine();
		Graphe graphe = new Graphe(nom_graphe);
		
		int x = -1;
		while(x == -1)
		{
			System.out.println("\nQue voulez faire ?");
			System.out.println("0°) Quitter l'application");
			System.out.println("1°) Afficher le graphe");
			System.out.println("2°) Afficher la matrice d'adjacence et la matrice de valeur");
			System.out.println("3°) Détéction de circuit");
			System.out.println("4°) Calculer le rang");
			
			System.out.print("\nNuméro de l'action à faire : ");
			sc1 = new Scanner(System.in);
			x = sc1.nextInt();
			
			switch(x) {
			case 0:
				System.exit(0);
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
					graphe.calcul_rang();;
					x = -1;
				    break;    
				    
			  default:
				  System.out.println("Veuillez choisir un numéro cohérent !");
				  x = -1;
			}
		}
	}

}
