package it.unibs.ing.fp.tamazoo;
import mylib.*;

public class Tamagotchi 
{
	private double grado_affettivita;
	private double grado_sazieta;
	private String nome;
	
	//grado_soddisfazione � la media tra grado_affettivita e grado_sazieta
	private double grado_soddisfazione;
	
	//due contatori per capire quante carezze/biscotti sono stati dati consecutivamente
	private static int contatore_carezze =0;
	private static int contatore_biscotti =0;
	
	//limite carezze/biscotti consecutivi, superato il limite il loro effetto sul Tamagotchi diminuisce
	private static final double LIMITE_CAREZZE =30;
	private static final double LIMITE_BISCOTTI =10;
	
	//parametri per incrementare o diminuire i vari gradi in funzione di carezze/biscotti
	private static final double PARAMETRO_CAREZZE =2;
	private static final double PARAMETRO_BISCOTTI =4;
	private static final double INCREMENTO_BISCOTTI =0.1;
	
	//parametri utili per la generazione pseudocasuale di carezze/biscotti
	private static final int MIN_CAREZZE =1;
	private static final int MAX_CAREZZE =20;
	private static final int MIN_BISCOTTI =1;
	private static final int MAX_BISCOTTI =5;
	private static final int CARENZA_AFFETTIVA =30;
	private static final int CARENZA_CIBO =30;
	private static final int ECCESSO_CIBO =90;
	
	/*Costruttore
	 * riceve una string e due double
	 * da errore se viene inserito uno o due zeri
	 * immediatamente viene aggiornato il grado_soddisfazione (media dei due parametri inseriti)
	 */
	public Tamagotchi (String _nome, double _grado_affettivita, double _grado_sazieta)
	{
		if(_grado_sazieta<0 || _grado_affettivita<0)
		{
			throw new IllegalArgumentException();
		}
			
		nome=_nome;
		grado_affettivita=_grado_affettivita;
		grado_sazieta=_grado_sazieta;
		aggiorna_soddisfazione();
	}
	
	
	//aggiorna il valore di soddisfazione generale (media)
	public void aggiorna_soddisfazione()
	{
		grado_soddisfazione=(grado_affettivita+grado_sazieta)/2;
	}
	
	
	/*Metodo per dare carezze
	 * il numero (n) di carezze viene scelto in modo casuale
	 * il metodo si appoggia a {mylib.NumeriCasuali#estraiIntero(int,int) estraiIntero}
	 */
	public void dai_carezze ()
	{
		int n =NumeriCasuali.estraiIntero(MIN_CAREZZE, MAX_CAREZZE);
		System.out.printf("verranno date %d carezze %n%n", n);
		modifica_carezze(n);
		aggiorna_soddisfazione();	
	}
	
	
	/*Metodo per dare biscotti
	 * il numero (n) di biscotti viene scelto in modo casuale
	 * il metodo si appoggia a {mylib.NumeriCasuali#estraiIntero(int,int) estraiIntero}
	 */
	public void dai_biscotti ()
	{
		int n =NumeriCasuali.estraiIntero(MIN_BISCOTTI, MAX_BISCOTTI);
		System.out.printf("verranno dati %d biscotti %n%n", n);
		modifica_biscotti(n);
		aggiorna_soddisfazione();
		
	}
	
	
	/*Metodo per modificare i gradi
	 * se il contatore_biscotti raggiunge un certo massimo 
	 * l'effetto dei biscotti sul tamagotchi diminuisce
	 * il metodo � private, per come � concepito il test su Junit si potrebbe impostarlo a public
	 * per verificare che il test funzioni correttamente
	 */
	private void modifica_biscotti(int n)
	{
		contatore_biscotti=contatore_biscotti+n;
		contatore_carezze= 0;
		if (contatore_biscotti>LIMITE_BISCOTTI)
			{
				n=n/2;
				System.out.printf("hai dato troppi biscotti verranno ridotti a  %d %n", n);
			}
		grado_affettivita= Math.max((grado_affettivita-(n/PARAMETRO_BISCOTTI)), 0) ;
		
		for (int i=0; i<n; i++)
		{
			grado_sazieta= grado_sazieta+(INCREMENTO_BISCOTTI*grado_sazieta);
		}
		grado_sazieta=Math.min(grado_sazieta, 100);
	}
	
	
	/*Metodo per modificare i gradi
	 * se il contatore_carezze raggiunge un certo massimo 
	 * l'effetto delle carezze sul tamagotchi diminuisce
	 * il metodo � private, per come � concepito il test su Junit si potrebbe impostarlo a public
	 * per verificare che il test funzioni correttamente
	 */
	private void modifica_carezze(int n)
	{
		contatore_carezze=contatore_carezze+n;
		contatore_biscotti= 0;
		if (contatore_carezze>LIMITE_CAREZZE)
		{
			n=n/2;
			System.out.printf("hai dato troppe carezze verranno ridotte a  %d %n", n);
			
		}
		
		grado_affettivita= Math.min((grado_affettivita+n), 100);
		
		grado_sazieta= Math.max((grado_sazieta-(n/PARAMETRO_CAREZZE)), 0);
		
	}
	
	
	//metodo per indicare se il Tamagotchi � morto
	public boolean sonoMorto ()
	{
		if ((grado_sazieta<=0)||(grado_sazieta>=100)||(grado_affettivita<=0))
			return true;
		else
			return false;
	}
	
	//metodo per indicare se il Tamagotchi � triste
	public boolean sonoTriste ()
	{
		if ((grado_sazieta>=ECCESSO_CIBO)||(grado_sazieta<=CARENZA_CIBO)||(grado_affettivita<=CARENZA_AFFETTIVA))
			return true;
		else
			return false;
	}

	/*
	 *Metodo toString
	 *ritorna a video tutti i parametri del Tamagotchi, indicando anche se � triste o morto
	 */
	public String toString ()
	{
		if (sonoMorto()==true)
			return String.format("Tamagotchi: %s %n Grado affettivit�: %.1f %n Grado saziet�: %.1f %n IL TAMAGOTCHI E' MORTO ",nome,grado_affettivita, grado_sazieta);

		else if (sonoTriste()==true)
			return String.format("Tamagotchi: %s %n Grado affettivit�: %.1f %n Grado saziet�: %.1f %n %n Grado soddisfazione: %.1f %n IL TAMAGOTCHI E' TRISTE ",nome,grado_affettivita, grado_sazieta,grado_soddisfazione);

		else
			return String.format("Tamagotchi: %s %n Grado affettivit�: %.1f %n Grado saziet�: %.1f %n Grado soddisfazione: %.1f",nome,grado_affettivita, grado_sazieta,grado_soddisfazione);
	}
	

}
