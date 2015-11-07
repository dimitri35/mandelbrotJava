
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JPanel;
 
public class Panneau extends JPanel implements MouseListener , MouseWheelListener ,MouseMotionListener{
	
		//attributs privés
		private double zoom  = 1;
		private double coeffZoom = 2 ;
		private double coeffDezoom = 0.5 ;
		private double centreX = 0 ;
		private double centreY = 0 ;
		private int iteration = 40;
		private Mandelbrot  uneFractale = new Mandelbrot() ;
		private ArrayList<Color> degrade = new ArrayList<Color>() ;
		
		//méthode qui redésinnent le panneau
        public void paintComponent(Graphics g){
        	
        		//si le dégradé n'a pas été intialisé on initialise le le dégradé
        		if(degrade.size()==0)
        		{
	        		for(int i=0;i<iteration+1 ;i++)
	        		{
	        			degrade.add(new Color(0, (int)(255/iteration*i ),(int)(255/iteration*i )) ) ;
	        		}
        		}
        		//couleur de fond
        	 	g.setColor(Color.black) ;
              //Vous pourrez voir cette phrase à chaque fois que la méthode est invoquée !
               System.out.println("Je suis exécutée ! ! !"); 
               //on ajoute les coord à la fractale
               uneFractale.setCoord( (-1/zoom+centreX) ,(1/zoom+centreX) ,(-1/zoom+centreY) ,(1/zoom+centreY) ) ;
               //on ajoute l'itération à la fractale
               uneFractale.setIteration(this.iteration) ;
               //on calcul la fractale
               uneFractale.calcul() ;
               int[][] matriceAff = uneFractale.getAxe() ;
               System.out.println("nombre d'iteration"+uneFractale.getIteration()) ;
               
               //on ajoute la matrice calculé à la fractale au panneau
               for(int i=0 ;i<matriceAff.length;i++)
               {
            	   for(int j=0;j<matriceAff.length;j++)
            	   {
            		  if(matriceAff[i][j]==0)
            		  {
            			  g.setColor(Color.black) ;
            			  g.drawLine(i,j,i, j) ;
            		  }
            		  else if(matriceAff[i][j]>0)
            		  {
            			 // g.setColor(new Color(0, (int)(255/uneFractale.getIteration()*matriceAff[i][j] ),(int)(255/uneFractale.getIteration()*matriceAff[i][j] )));
            			  g.setColor(degrade.get(matriceAff[i][j])) ;
            			  g.drawLine(i,j,i,j); 

            			  
            		  }
            		  
            		  
            		  
            	   }
               } 
               
               
               
        }
        
        //souris
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
		
			double abscisse =((arg0.getX()-350.0)/350.0 )/this.zoom+this.centreX ;
			double ordonnee = ((700-arg0.getY()-350.0)/350.0)/this.zoom+this.centreY  ;
			System.out.println("click : "+arg0.getButton()+" : position x : "+abscisse+" : position y : "+ordonnee) ;
			
			//si on clique gauche alors on zoom 
			if(arg0.getButton() == 1)
			{
				this.centreX= abscisse;
				this.centreY= ordonnee ;
				
				zoom() ;
				
				
			}
			//si on clique droit alors on dézoom
			else if(arg0.getButton() == 3)
			{
				this.centreX= abscisse ;
				this.centreY= ordonnee ;
				
				deZoom() ;
				
			}
			
			this.repaint() ;
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			

			
			
			
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("Scroll : "+arg0.getWheelRotation()) ;
			//si on fait tourner la roulette vers le bas on dézoom
			if(arg0.getWheelRotation() == 1)
			{
				
				deZoom() ;
			
			}
			//si onfait tourner la roulette vers le haut on zoom
			else if(arg0.getWheelRotation() == -1)
			{
				zoom() ;
				
			}
			this.repaint() ;
		}
		
		private void zoom()
		{
			//on augmente le nombre d'itération
			this.iteration+=2 ;
			//on augmente le coeff de zoom
			this.zoom*= coeffZoom  ;
			//on crée un nouveau dégradé 
			this.creeDegrade() ;
		}
		private void deZoom()
		{
			//on baisse le nombre d'itération
			this.iteration-=2 ;
			//on baisse  le coeff de zoom
			this.zoom*= coeffDezoom ;
			this.creeDegrade() ;
			
		}
		
		//méthode permettant de régénerer un dégrdé à partir du nombre d'itération
		public void creeDegrade()
		{
			degrade.clear() ;
			for(double i=0;i<iteration+1 ;i++)
    		{
				
    			degrade.add(new Color(5, (int)(255/(double)iteration*i ),(int)(255/(double)iteration*i )) ) ;
    		}
		}
		
		//méthode permettant de modifier le nombre d'itération
		public void setIte(int iteration)
		{
			this.iteration = iteration ;
		}

		
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			//on déplace la fractale lors d'un  glisser de souris
			double abscisse =((arg0.getX()-350.0)/350.0 )/this.zoom+this.centreX ;
			double ordonnee = ((700-arg0.getY()-350.0)/350.0)/this.zoom+this.centreY  ;
			this.centreX= abscisse;
			this.centreY= ordonnee ;
			//on redessine la fractale
			this.repaint();
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
}

