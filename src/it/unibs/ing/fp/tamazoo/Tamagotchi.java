package it.unibs.ing.fp.tamazoo;
import it.unibs.fp.mylib.*;

public class Tamagotchi 
{
	private double grado_affettivita;
	private double grado_sazieta;
	public double getGrado_soddisfazione() {
		return grado_soddisfazione;
	}


	public void setGrado_soddisfazione(double grado_soddisfazione) {
		this.grado_soddisfazione = grado_soddisfazione;
	}


	public int getContatore_carezze() {
		return contatore_carezze;
	}


	public void setContatore_carezze(int contatore_carezze) {
		this.contatore_carezze = contatore_carezze;
	}


	public int getContatore_biscotti() {
		return contatore_biscotti;
	}


	public void setContatore_biscotti(int contatore_biscotti) {
		this.contatore_biscotti = contatore_biscotti;
	}


	public double getGrado_affettivita() {
		return grado_affettivita;
	}


	public void setGrado_affettivita(double grado_affettivita) {
		this.grado_affettivita = grado_affettivita;
	}


	public double getGrado_sazieta() {
		return grado_sazieta;
	}


	public void setGrado_sazieta(double grado_sazieta) {
		this.grado_sazieta = grado_sazieta;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	private String nome;
	
	//grado_soddisfazione è la media tra grado_affettivita e grado_sazieta
	private double grado_soddisfazione;
	
	//due contatori per capire quante carezze/biscotti sono stati dati consecutivamente
	private  int contatore_carezze =0;
	private int contatore_biscotti =0;
	
	//limite carezze/biscotti consecutivi, superato il limite il loro effetto sul Tamagotchi diminuisce
	public static final double LIMITE_CAREZZE =30;
	public static final double LIMITE_BISCOTTI =10;
	
	//parametri per incrementare o diminuire i vari gradi in funzione di carezze/biscotti
	public static final double PARAMETRO_CAREZZE =2;
	public static final double PARAMETRO_BISCOTTI =4;
	public static final double INCREMENTO_BISCOTTI =0.1;
	
	//parametri utili per la generazione pseudocasuale di carezze/biscotti
	public static final int MIN_CAREZZE =1;
	public static final int MAX_CAREZZE =20;
	public static final int MIN_BISCOTTI =1;
	public static final int MAX_BISCOTTI =5;
	public static final int CARENZA_AFFETTIVA =30;
	public static final int CARENZA_CIBO =30;
	public static final int ECCESSO_CIBO =90;
	
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
	 * il metodo è private, per come è concepito il test su Junit si potrebbe impostarlo a public
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
	 * il metodo è private, per come è concepito il test su Junit si potrebbe impostarlo a public
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
	
	
	//metodo per indicare se il Tamagotchi è morto
	public boolean sonoMorto ()
	{
		if ((grado_sazieta<=0)||(grado_sazieta>=100)||(grado_affettivita<=0))
			return true;
		else
			return false;
	}
	
	//metodo per indicare se il Tamagotchi è triste
	public boolean sonoTriste ()
	{
		if ((grado_sazieta>=ECCESSO_CIBO)||(grado_sazieta<=CARENZA_CIBO)||(grado_affettivita<=CARENZA_AFFETTIVA))
			return true;
		else
			return false;
	}

	/*
	 *Metodo toString
	 *ritorna a video tutti i parametri del Tamagotchi, indicando anche se è triste o morto
	 */
	public String toString ()
	{
		if (sonoMorto()==true)
			return String.format("Tamagotchi: %s %n Grado affettività: %.1f %n Grado sazietà: %.1f %n IL TAMAGOTCHI E' MORTO %n%n",nome,grado_affettivita, grado_sazieta);

		else if (sonoTriste()==true)
			return String.format("Tamagotchi: %s %n Grado affettività: %.1f %n Grado sazietà: %.1f %n Grado soddisfazione: %.1f %n IL TAMAGOTCHI E' TRISTE %n%n ",nome,grado_affettivita, grado_sazieta,grado_soddisfazione);

		else
			return String.format("Tamagotchi: %s %n Grado affettività: %.1f %n Grado sazietà: %.1f %n Grado soddisfazione: %.1f %n%n",nome,grado_affettivita, grado_sazieta,grado_soddisfazione);
	}
	

}
