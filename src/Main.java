import java.util.Scanner;

public class Main {

	
	private static Scanner sc1;
	private static Scanner sc;

	public static void main(String[] args) 
	{
		System.out.println("Projet de théorie des graphes S6\n");
		
		System.out.print("Choississez le nom d'un graph : ");
		sc = new Scanner(System.in);
		String nom_graph = sc.nextLine();
		Graph graph = new Graph(nom_graph);
		
		int x = -1;
		while(x == -1)
		{
			System.out.println("\nQue voulez faire ?");
			System.out.println("0°) Quitter l'application");
			System.out.println("1°) Afficher le graph");
			System.out.println("2°) Afficher la matrice d'adjacence et la matrice de valeur");
			
			System.out.print("\nNuméro de l'action à faire : ");
			sc1 = new Scanner(System.in);
			x = sc1.nextInt();
			
			switch(x) {
			case 0:
				System.exit(0);
			    break;
			    
			  case 1:
				graph.afficher_graph();
				x = -1;
			    break;
			    
			  case 2:
			    graph.matrice_adjacence();
			    graph.matrice_valeur();
			    x = -1;
			    break;
			    
			  default:
				  System.out.println("Veuillez choisir un numéro cohérent !");
				  x = -1;
			}
		}
	}

}
