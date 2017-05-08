package it.unibs.ing.fp.tamazoo;

import it.unibs.fp.mylib.NumeriCasuali;

public class TamagotchiGordo extends Tamagotchi {

	public TamagotchiGordo(String _nome, double _grado_affettivita, double _grado_sazieta) {
		super(_nome, _grado_affettivita, _grado_sazieta);
		
	}
	
	public boolean sonoMorto ()
	{
		if ((getGrado_sazieta()<=0)||(getGrado_affettivita()<=0))
			return true;
		else
			return false;
	}
	
	//metodo per indicare se il Tamagotchi è triste
	public boolean sonoTriste ()
	{
		if ((getGrado_sazieta()<=CARENZA_CIBO)||(getGrado_affettivita()<=CARENZA_AFFETTIVA))
			return true;
		else
			return false;
	}
	
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
		setContatore_biscotti(getContatore_biscotti()+n);
		setContatore_carezze(0);
		if (getContatore_biscotti()>LIMITE_BISCOTTI)
			{
				n=n/2;
				System.out.printf("hai dato troppi biscotti verranno ridotti a  %d %n", n);
			}
		
		for (int i=0; i<n; i++)
		{
			setGrado_sazieta(getGrado_sazieta()+(INCREMENTO_BISCOTTI*getGrado_sazieta()));
		}
		setGrado_sazieta(Math.min(getGrado_sazieta(), 100));
	}
	
	
	/*Metodo per modificare i gradi
	 * se il contatore_carezze raggiunge un certo massimo 
	 * l'effetto delle carezze sul tamagotchi diminuisce
	 * il metodo è private, per come è concepito il test su Junit si potrebbe impostarlo a public
	 * per verificare che il test funzioni correttamente
	 */
	private void modifica_carezze(int n)
	{
		setContatore_carezze(getContatore_carezze()+n);
		setContatore_carezze(0);
		if (getContatore_carezze()>LIMITE_CAREZZE)
		{
			n=n/2;
			System.out.printf("hai dato troppe carezze verranno ridotte a  %d %n", n);
			
		}
		
		setGrado_sazieta(Math.max((getGrado_sazieta()-(2*n/PARAMETRO_CAREZZE)), 0));
		
	}

}
