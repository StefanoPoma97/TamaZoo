package it.unibs.ing.fp.tamazoo;

import it.unibs.fp.mylib.*;
public class TamaMain 
{

	private static final String SALUTO_INIZIALE= "Inserisci il nome del tuo tamagotchi :";
	private static final String GRADO_AFFETTIVITA = "Inserisci il grado iniziale di affettività:";
	private static final String GRADO_SAZIETA = "Inserisci il grado iniziale di sazietà:";
	private static final String NOME_GIOCO = "Gioco del Tamagotchi";
	

	
	public static void main(String[] args) 
	{
		//richiama il metodo creaTamagotchi(), per rendere più snello il main
		Tamagotchi tama = creaTamagotchi();
		
		// creo array vociMenu
		String vociMenu []= { "Dai biscotti", "Dai carezze"};
		
		//mi appoggio alla classe MyMenu del pacchetto myutil
		MyMenu menu= new MyMenu(NOME_GIOCO, vociMenu);
		
		boolean gioco=true;
		
		while ((!tama.sonoMorto())&&(gioco==true))
		{
			System.out.println(tama);
			switch (menu.scegli())
			{
			case 1:
				tama.dai_biscotti();
				break;
			case 2:
				tama.dai_carezze();
				break;
			case 0:
				System.out.println("USCITO DAL GIOCO");
				gioco=false;
				break;
			}
				
		}
		System.out.println(tama);

	}
	
	/*creaTamagotchi
	 * metodo che permette di creare un nuovo tamagotchi inserendo il nome,
	 * i gradi iniziali di sazietà e di affetto.
	 * il metodo si appoggia alla classe InputDati.
	 */
	public static Tamagotchi creaTamagotchi()
	{
		String nome = InputDati.leggiStringaNonVuota(SALUTO_INIZIALE);
		double grado_sazieta= InputDati.leggiIntero(GRADO_SAZIETA, 0, 100);
		double grado_affettivita= InputDati.leggiIntero(GRADO_AFFETTIVITA, 0, 100);
		return new Tamagotchi (nome, grado_affettivita, grado_sazieta );
	}

}
