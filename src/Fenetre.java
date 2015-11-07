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

		bouton = new JButton("couleur") ;
		bouton.setSize(50,50) ;
		this.setTitle("Mandelbrot");
		this.setSize(700, 700);
		this.setResizable(false); 
		this.setLocationRelativeTo(null);               
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pan= new Panneau() ;

		pan.addMouseListener(pan) ;
		pan.addMouseWheelListener(pan) ;
		pan.addMouseMotionListener(pan) ;

		this.setLayout(new BorderLayout()) ;
		this.getContentPane().add(pan,BorderLayout.CENTER) ;

		this.setVisible(true);
		//création du menu 
		this.param.add(item1) ;
		this.menuBar.add(param) ;
		item1.addActionListener(new PersoActionListener("iteration")) ;
		this.setJMenuBar(menuBar) ;



	}

	public class PersoActionListener implements ActionListener
	{
		private String action;

		public PersoActionListener(String action )
		{
			this.action = action ;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JOptionPane jop = new JOptionPane();
			String nom = jop.showInputDialog(null, "nombre d'itérations", "Paramètres/nombre d'itérations", JOptionPane.QUESTION_MESSAGE);
			try
			{
				int nbIte = Integer.parseInt(nom); 
				if(nbIte > 0 )
				{
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
