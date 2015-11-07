public class Complexe {
	
	//partie réel du nombre complexe
	public double re;
	//partie imaginaire du nombre complexe
	public double im;
	
	public Complexe(double r, double i){
		this.re = r;
		this.im = i;
	}
	
	public Complexe (Complexe z)
	{
		this.re = z.re;
		this.im = z.im;
	}
	
	/**
	 * Calcule la somme avec un autre nombre complexe
	 * @param a Un nombre complexe
	 * @return Retourne un nombre complexe correspondant à la somme
	 */
	public Complexe somme(Complexe a){
		return new Complexe(a.re+re,a.im+im);
	}
	/**
	 * Calcule le carré du nombre complexe
	 * @return Un nombre complexe correspondant au carré du nombre complexe
	 */
	public Complexe carre(){
		return new Complexe(re*re-im*im,2*re*im);
	}
	/**
	 *  Retourne le module sans la racine
	 * @return Un double représentant le module sans la racine
	 */
	public double norme2(){
		return re*re+im*im;
	}
	
	public String toString(){
		if(im>0)
			return re+"+i "+im;
		if(im<0)
			return re+"-i "+(-im);
		return re+"";
	}
} 
