import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;


public class Fenetre extends JFrame {
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu param = new JMenu("Parametres");
	private Panneau pan  ;
		
	private JMenuItem item1 = new JMenuItem("Parametres");
	private JButton bouton ;
	public Fenetre(){
		
		//on crée un nouveau bouton 
		//bouton = new JButton("couleur") ;
		//bouton.setSize(50,50) ;
		//titre de la fenetre
        this.setTitle("Mandelbrot");
        //taille
        this.setSize(700, 700);
        //redimensionable
        this.setResizable(false); 
        this.setLocationRelativeTo(null);   
        //opération de fermeture par défaut 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //on crée un nouveau panneau (hérite de JPanel)
        pan= new Panneau() ;
        //on ajoute des écouteur d'évenements à ce panneau
        pan.addMouseListener(pan) ;
        pan.addMouseWheelListener(pan) ;
        pan.addMouseMotionListener(pan) ;
        
       //on ajoute un Layout à la fenetre       
        this.setLayout(new BorderLayout()) ;
        //on ajoute le panneau à la fenetre 
        this.getContentPane().add(pan,BorderLayout.CENTER) ;


        //on rend visible la fenetre
        this.setVisible(true);
        //création du menu 
        this.param.add(item1) ;
        this.menuBar.add(param) ;
        //on ajoute un écouteur d'évenements à l'élement itération
        item1.addActionListener(new PersoActionListener("iteration")) ;
        this.setJMenuBar(menuBar) ;
        
        
        
	}
	
	//écouteur d'évenement personalisé qui implémente l'interface ActionListener
	public class PersoActionListener implements ActionListener
	{
		private String action;
		
		//constructeur
		public PersoActionListener(String action )
		{
			this.action = action ;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//lorsqu'une action est lancée on lance un nouvelle boite de dialogue .
			JOptionPane jop = new JOptionPane();
			String nom = jop.showInputDialog(null, "nombre d'itérations", "Paramètres/nombre d'itérations", JOptionPane.QUESTION_MESSAGE);
			//on essaye de convertir le texte récupéré en nombre .
			try
			{
				
				int nbIte = Integer.parseInt(nom); 
				//si le nombre récupéré est supérieur à zéro.
				if(nbIte > 0 )
				{
					//on réactualise le panneau en le redesinnant
					pan.setIte(nbIte) ;
					pan.creeDegrade() ;
					pan.repaint() ;
				}
				
			}
			catch(Exception e1)
			{
				
			}
		}
		
	}
	
	
	
}
