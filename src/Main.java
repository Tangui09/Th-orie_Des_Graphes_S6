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
		
		System.out.println("\nQue voulez faire ?");
		System.out.println("1°) Afficher le graph");
		
		int x = 0;
		while(x == 0)
		{
			System.out.print("\nNuméro de l'action à faire : ");
			sc1 = new Scanner(System.in);
			x = sc1.nextInt();
			
			switch(x) {
			  case 1:
				Graph graph = new Graph(nom_graph);
				graph.afficher_graph();
			    break;
			  case 2:
			    
			    break;
			  default:
				  System.out.println("\nVeuillez choisir un numéro cohérent !");
				  x = 0;
			}
		}
		
		
		
	}

}
