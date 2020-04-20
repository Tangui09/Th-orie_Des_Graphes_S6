import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class MainGraphe extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static Scanner sc1;
	private static Scanner sc;

	public MainGraphe()
	{
		this.setTitle("Projet de Théorie des Graphes - S6");			
		this.setPreferredSize(new Dimension(800, 600));				
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setResizable(false);									
		
		
		
		JPanel mainPanel = new JPanel();							//Global panel of the frame
		setContentPane(mainPanel);									//Setting the previous panel as the global panel of the frame
		mainPanel.setLayout(new BorderLayout(0,0));					//Change the panel type to a NORTH-SOUTH-EAST-WEST-CENTER model
		
		
		
		///TOP PANEL
		
			JPanel topPanel = new JPanel();								//New panel, which will be on the top of the frame
			mainPanel.add(topPanel, BorderLayout.NORTH);				//Setting its postion to be on the NORTH of the mainPanel
			topPanel.setLayout(new BorderLayout(0,0));					//Change the panel type to a NORTH-SOUTH-EAST-WEST-CENTER model
				
			JLabel grand_titre = new JLabel("Projet de Théorie des Graphes S6");					//Create a button to exit the entire program
			topPanel.add(grand_titre, BorderLayout.NORTH);				//Setting its postion to be on the EASt of the topPanel
			grand_titre.setFont(new Font("Tahoma", Font.PLAIN, 30));
			grand_titre.setHorizontalAlignment(SwingConstants.CENTER);
			grand_titre.setBorder(new EmptyBorder(25, 0, 0, 0));
			
			JLabel nos_noms = new JLabel("BARTIER Marion     CARDOSO Nicolas     SEDRAOUI Selma");					//Create a button to exit the entire program
			topPanel.add(nos_noms, BorderLayout.SOUTH);				//Setting its postion to be on the EASt of the topPanel
			nos_noms.setFont(new Font("Tahoma", Font.PLAIN, 24));
			nos_noms.setHorizontalAlignment(SwingConstants.CENTER);
			
		///TOP PANEL
			
			
			
			
			
		///CENTER PANEL
			
			JPanel centerPanel = new JPanel();							//New panel, which will be on the middle of the frame
			mainPanel.add(centerPanel, BorderLayout.CENTER);			//Setting its position to be on the CENTER of the mainPanel
			centerPanel.setLayout(new GridBagLayout());					//Change the panel type to a custom placing model
			
			
			GridBagConstraints gbcMain = new GridBagConstraints();		//Create the constraint that will help us to place a component on the panel (by its coordinates)
			gbcMain.gridx = 1;											//Set default coordinate x on 0 (column 1)
			gbcMain.gridy = 0;											//Set default coordinate x on 0 (line 1)
			gbcMain.fill = GridBagConstraints.HORIZONTAL;				//If the component is too big for one cell, fill horizontally to the next cell
			gbcMain.insets = new Insets(10,10,10,10);					//Define the borders of the component
				
			JLabel titre = new JLabel("Choisissez le numéro d'un graphe : ");							//Create a new JLabel, which is a zone to write something on the Frame. Inthis case, we want to write : " ID : "
			titre.setFont(new Font("Tahoma", Font.PLAIN, 18));				//Set the text font on Tahoma, the font size on 18 and define the plain style
			titre.setHorizontalAlignment(SwingConstants.RIGHT);			//Align the text to the right of the JLabel
			centerPanel.add(titre,gbcMain);								//Add our Label to our centerPanel, at the GridBagConstraint coordinates
			
			gbcMain.gridy = 1;
			gbcMain.gridx = 0;											//Go down one line (to the place our new JLabel on the next line
			JLabel groupe = new JLabel("L3-A9-Graphe");
			groupe.setFont(new Font("Tahoma", Font.PLAIN, 18));
			groupe.setHorizontalAlignment(SwingConstants.RIGHT);
			centerPanel.add(groupe,gbcMain);
			
			gbcMain.gridx = 1;
			JTextField numero = new JTextField(10);						//Create a new TextField, to write text in it (size 10 for the TextField)
			numero.setFont(new Font("Tahoma", Font.PLAIN, 18));
			numero.setHorizontalAlignment(SwingConstants.CENTER);
			centerPanel.add(numero,gbcMain);
			
			gbcMain.gridx = 2;
			JLabel txt = new JLabel(".txt");
			txt.setFont(new Font("Tahoma", Font.PLAIN, 18));
			txt.setHorizontalAlignment(SwingConstants.LEFT);
			centerPanel.add(txt,gbcMain);
			
			
			gbcMain.gridx = 1;
			gbcMain.gridy = 2;
			JButton submitButton = new JButton("Valider");				//Create a button to submit the login try, and verify if the user can effectively log in
			centerPanel.add(submitButton,gbcMain);
			submitButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String nom_graphe = "L3-A9-Graphe" + numero.getText() + ".txt";
					Graphe graphe = new Graphe(nom_graphe);
					graphe.afficher_graphe();
					dispose();
				}
			}
			);
			
		///CENTER PANEL
			
			
			
			
		pack();										//pack all the components together, to create the frame
		this.setLocationRelativeTo(null);			//Center the frame on the screen
		this.setVisible(true);						//Set the frame visible
	}	
		/*
		
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
					graphe.calcul_rang();;
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
				    
			  default:
				  System.out.println("Veuillez choisir un numéro cohérent !");
				  x = -1;
			}
		}
	}
	*/
	public static Graphe choisir_graphe(String new_graphe)
	{
		Graphe graphe = new Graphe(new_graphe);
		return graphe;
	}

}
