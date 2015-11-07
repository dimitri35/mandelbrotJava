
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

	private double zoom  = 1;
	private double coeffZoom = 2 ;
	private double coeffDezoom = 0.5 ;
	private double centreX = 0 ;
	private double centreY = 0 ;
	private int iteration = 40;
	private Mandelbrot  uneFractale = new Mandelbrot() ;
	private ArrayList<Color> degrade = new ArrayList() ;


	public void paintComponent(Graphics g){
		if(degrade.size()==0)
		{
			for(int i=0;i<iteration+1 ;i++)
			{
				degrade.add(new Color(0, (int)(255/iteration*i ),(int)(255/iteration*i )) ) ;
			}
		}
		g.setColor(Color.black) ;
		//Vous pourrez voir cette phrase à chaque fois que la méthode est invoquée !
		System.out.println("Je suis exécutée ! ! !"); 
		uneFractale.setCoord( (-1/zoom+centreX) ,(1/zoom+centreX) ,(-1/zoom+centreY) ,(1/zoom+centreY) ) ;
		uneFractale.setIteration(this.iteration) ;
		uneFractale.calcul() ;
		int[][] matriceAff = uneFractale.getAxe() ;
		System.out.println("nombre d'iteration"+uneFractale.getIteration()) ;
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

		if(arg0.getButton() == 1)
		{
			this.centreX= abscisse;
			this.centreY= ordonnee ;

			zoom() ;


		}
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
		if(arg0.getWheelRotation() == 1)
		{
			deZoom() ;

		}
		else if(arg0.getWheelRotation() == -1)
		{
			zoom() ;

		}
		this.repaint() ;
	}

	private void zoom()
	{
		this.iteration+=2 ;
		this.zoom*= coeffZoom  ;
		this.creeDegrade() ;
	}
	private void deZoom()
	{
		this.iteration-=2 ;
		this.zoom*= coeffDezoom ;
		this.creeDegrade() ;

	}

	public void creeDegrade()
	{
		degrade.clear() ;
		for(double i=0;i<iteration+1 ;i++)
		{

			degrade.add(new Color(5, (int)(255/(double)iteration*i ),(int)(255/(double)iteration*i )) ) ;
		}
	}

	public void setIte(int iteration)
	{
		this.iteration = iteration ;
	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

		double abscisse =((arg0.getX()-350.0)/350.0 )/this.zoom+this.centreX ;
		double ordonnee = ((700-arg0.getY()-350.0)/350.0)/this.zoom+this.centreY  ;
		this.centreX= abscisse;
		this.centreY= ordonnee ;
		this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}

