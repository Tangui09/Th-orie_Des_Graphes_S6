import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class L3_A9_ChoixGraphe extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public L3_A9_ChoixGraphe()
	{
		this.setTitle("Projet de Theorie des Graphes - S6");			
		this.setPreferredSize(new Dimension(800, 600));				
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setResizable(false);									
		
		
		
		JPanel mainPanel = new JPanel();			
		setContentPane(mainPanel);							
		mainPanel.setLayout(new BorderLayout(0,0));			
		BorderLayout layout = (BorderLayout)mainPanel.getLayout();
		
		
		///TOP PANEL
		
			JPanel topPanel = new JPanel();						
			mainPanel.add(topPanel, BorderLayout.NORTH);			
			topPanel.setLayout(new BorderLayout(0,0));				
				
			JLabel grand_titre = new JLabel("Projet de Theorie des Graphes S6");			
			topPanel.add(grand_titre, BorderLayout.NORTH);				
			grand_titre.setFont(new Font("Tahoma", Font.PLAIN, 30));
			grand_titre.setHorizontalAlignment(SwingConstants.CENTER);
			grand_titre.setBorder(new EmptyBorder(25, 0, 0, 0));
			
			JLabel nos_noms = new JLabel("BARTIER Marion     CARDOSO Nicolas     SEDRAOUI Selma");				
			topPanel.add(nos_noms, BorderLayout.SOUTH);			
			nos_noms.setFont(new Font("Tahoma", Font.PLAIN, 24));
			nos_noms.setHorizontalAlignment(SwingConstants.CENTER);
			
		///TOP PANEL
			
			
			
			
			
		///CENTER PANEL
			
			JPanel centerPanel = new JPanel();							
			mainPanel.add(centerPanel, BorderLayout.CENTER);			
			centerPanel.setLayout(new GridBagLayout());					
			
			
			GridBagConstraints gbcMain = new GridBagConstraints();	
			gbcMain.gridx = 1;							
			gbcMain.gridy = 0;							
			gbcMain.fill = GridBagConstraints.HORIZONTAL;	
			gbcMain.insets = new Insets(10,10,10,10);		
				
			JLabel titre = new JLabel("Choisissez le numero d'un graphe : ");	
			titre.setFont(new Font("Tahoma", Font.PLAIN, 18));			
			titre.setHorizontalAlignment(SwingConstants.RIGHT);		
			centerPanel.add(titre,gbcMain);							
			
			gbcMain.gridy = 1;
			gbcMain.gridx = 0;									
			JLabel groupe = new JLabel("L3-A9-Graphe");
			groupe.setFont(new Font("Tahoma", Font.PLAIN, 18));
			groupe.setHorizontalAlignment(SwingConstants.RIGHT);
			centerPanel.add(groupe,gbcMain);
			
			gbcMain.gridx = 1;
			JTextField numero = new JTextField(10);											// Saisir le numero du graph ici
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
			JButton submitButton = new JButton("Valider");			
			centerPanel.add(submitButton,gbcMain);
			submitButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if((Integer.parseInt(numero.getText()) <= 13) && (Integer.parseInt(numero.getText()) >= 0))			// Eviter un choix de graphe impossible
					{
						String nom_graphe = "A9-Graphe" + numero.getText() + ".txt";									// Ajouter automatiquement le A9_Graphe et le .txt
						new L3_A9_Graphe(nom_graphe);																	// Creer le nouveau graphe et ouvrir sa fenêtre
						dispose();																						// Fermer la fenetre de choix de graphe
					}
					else													// Afficher un petit message d'erreur
					{
						JPanel topPanel = new JPanel();					
						mainPanel.add(topPanel, BorderLayout.NORTH);		
						topPanel.setLayout(new BorderLayout(0,0));
						
						getContentPane().remove(layout.getLayoutComponent(BorderLayout.SOUTH));
						JPanel new_southPanel = new JPanel();
						new_southPanel.setLayout(new BorderLayout(0,0));
						
						JLabel pas_bien = new JLabel("Choisissez un numero de graphe valide");
						pas_bien.setFont(new Font("Tahoma", Font.PLAIN, 21));
						pas_bien.setHorizontalAlignment(SwingConstants.CENTER);
						pas_bien.setBorder(new EmptyBorder(0, 0, 30, 0));
						new_southPanel.add(pas_bien, BorderLayout.CENTER);
						
						mainPanel.add(new_southPanel, BorderLayout.SOUTH);
						setContentPane(mainPanel);
						pack();
					}
				}
			}
			);
			
		///CENTER PANEL
			
		
			
			
		/// SOUTH PANEL ///	
			
			
		JPanel southPanel = new JPanel();
		mainPanel.add(southPanel, BorderLayout.SOUTH);
			
			
		/// SOUTH PANEL ///		
			
			
		pack();								
		this.setLocationRelativeTo(null);		
		this.setVisible(true);					
	}	

	public static L3_A9_Graphe choisir_graphe(String new_graphe)
	{
		L3_A9_Graphe graphe = new L3_A9_Graphe(new_graphe);
		return graphe;
	}

}
