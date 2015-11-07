
public class Mandelbrot {
	//Représente le nombre de fois que l'on repète la formule de Mandelbrot
	private int iteration;
	//Création d'un matrice : axe abscisse - ordonnée
	private int[][] axe;
	//Représente la largeur et la hauteur maximale que la fenêtre aura
	private int lwin;
	private int hwin;
	//Représente les abscisses max et min et ordonnées max et min que l'on peut avoir
	private double ymax,ymin,xmin,xmax;
	public Mandelbrot(){
		//Initialisation des variables
		hwin=700;
		lwin=700;
		axe = new int[lwin][hwin];
	}
	
	/**
	 * Va effectuer les calculs pour remplir la matrice axe servant à afficher la fractale
	 */
	public void calcul(){
		//Va contenir les valeurs qui vont permettre de créer le fractal
		int n;
		/*Parcours de l'hauteur et de la largeur de la fenêtre pour fixer les valeurs dans
	    la matrice axe grâce à la formule de Mandelbrot*/
		for(int i=0;i<hwin;i++)
		{
			for(int j=0;j<lwin;j++)
			{
				//Début de formule de Mandelbrot
				Complexe c = new Complexe((i*(xmax-xmin)/(double)(hwin) + xmin),(ymax - j*(ymax-ymin)/(double)(lwin)));
				Complexe z = new Complexe(0,0);
				n=0;
				/*Tant que le nombre d'itération est supérieur à n et la norme du complexe z
		    est inférieur à 4, on incrémente n et on place dans le complexe z
		    la valeur de la somme du complexe c et le carré du complexe z */
				while((n<iteration) && (z.norme2()<4))
				{
					n++;
					//Fin de formule de Mandelbrot
					z=(z.carre()).somme(c);
				}
				/*On stocke dans la matrice axe les valeurs de n si elles sont inférieures
		    à celle de l'itération ou sinon on fixe 0 à la matrice axe */
				if(n<iteration)
					axe[i][j]=n;
				else
					axe[i][j]=0;
			}
		}
	}
	/**
	 * Permet de modifier les attributs xmin, xmax, ymin, ymax
	 * @param xmin 
	 * @param xmax
	 * @param ymin
	 * @param ymax
	 */
	public void setCoord(double xmin,double xmax, double ymin, double ymax)
	{
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
	}
	/**
	 * Permet de modifier l'attribut itération
	 * @param it Le nombre d'itération
	 */
	public void setIteration(int it)
	{
		this.iteration = it;
	}
	
	/**
	 * Retourne la matrice d'affichage de la fractale
	 * @return Un tableau à deux dimensions contenant la valeur des pixels de la fractale
	 */
	public int[][] getAxe()
	{
		return this.axe ;
	}
	/**
	 * Retourne le nombre d'itération qui sera effectué
	 * @return Un nombre entier représentant le nombre d'itération qui sera effectué
	 */
	public int getIteration()
	{
		return this.iteration ;
	}
}




