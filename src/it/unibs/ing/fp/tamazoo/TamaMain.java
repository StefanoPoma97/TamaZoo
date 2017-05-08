package it.unibs.ing.fp.tamazoo;

import java.util.ArrayList;

import it.unibs.fp.mylib.*;

public class TamaMain {

	private static final String SALUTO_INIZIALE = "Inserisci il nome del tuo tamagotchi :";
	private static final String NUM_TAMA = "Inserisci il numero di tamagotchi: ";
	private static final String GRADO_AFFETTIVITA = "Inserisci il grado iniziale di affettività:";
	private static final String GRADO_SAZIETA = "Inserisci il grado iniziale di sazietà:";
	private static final String NOME_GIOCO = "Gioco del Tamagotchi";
	private static ArrayList<Tamagotchi> elenco = new ArrayList<>();

	public static void main(String[] args) {

		int tamaNumber = InputDati.leggiInteroConMinimo(NUM_TAMA, 1);
		for (int i = 0; i < tamaNumber; i++) {
			Tamagotchi tama;
			int casual=NumeriCasuali.estraiIntero(0, 2);
			switch(casual)
			{
			
			case 0:
				tama=creaTamagotchiBase();
				elenco.add(tama);
				break;
			case 1:
				tama=creaTamagotchiTriste();
				elenco.add(tama);
				break;
			case 2:
				tama=creaTamagotchiGordo();
				elenco.add(tama);
				break;
				
			}
			
		}

		// creo array vociMenu
		String vociMenu[] = { "Dai biscotti", "Dai carezze" };

		// mi appoggio alla classe MyMenu del pacchetto myutil
		MyMenu menu = new MyMenu(NOME_GIOCO, vociMenu);

		boolean gioco = true;
		for (int u=0; u<elenco.size();u++)
		{
			System.out.println(elenco.get(u));
			if (elenco.get(u).sonoMorto()){
				elenco.remove(elenco.get(u));
				u--;
			}
		}

		while (!elenco.isEmpty() && (gioco == true)) {
			
			switch (menu.scegli()) {
			case 1:
				for (Tamagotchi tama : elenco)
					tama.dai_biscotti();
				break;
			case 2:
				for (Tamagotchi tama : elenco)
					tama.dai_carezze();
				break;
			case 0:
				System.out.println("USCITO DAL GIOCO");
				gioco = false;
				break;
			}
				
			
			for (int u=0; u<elenco.size();u++)
			{
				System.out.println(elenco.get(u));
				if (elenco.get(u).sonoMorto()){
					elenco.remove(elenco.get(u));
					u--;
				}
			}
		}
		if (elenco.isEmpty())
			System.out.println("tutti crepati");
		

	}

	/*
	 * creaTamagotchi metodo che permette di creare un nuovo tamagotchi
	 * inserendo il nome, i gradi iniziali di sazietà e di affetto. il metodo si
	 * appoggia alla classe InputDati.
	 */
	public static Tamagotchi creaTamagotchiBase() {
		String nome = InputDati.leggiStringaNonVuota(SALUTO_INIZIALE)+" (Base)";
		double grado_sazieta = InputDati.leggiIntero(GRADO_SAZIETA, 0, 100);
		double grado_affettivita = InputDati.leggiIntero(GRADO_AFFETTIVITA, 0, 100);
		return new Tamagotchi(nome, grado_affettivita, grado_sazieta);
	}
	public static Tamagotchi creaTamagotchiTriste() {
		String nome = InputDati.leggiStringaNonVuota(SALUTO_INIZIALE)+" (Triste)";
		double grado_sazieta = InputDati.leggiIntero(GRADO_SAZIETA, 0, 100);
		double grado_affettivita = InputDati.leggiIntero(GRADO_AFFETTIVITA, 0, 100);
		return new TamagotchiTriste(nome, grado_affettivita, grado_sazieta);
	}
	public static Tamagotchi creaTamagotchiGordo() {
		String nome = InputDati.leggiStringaNonVuota(SALUTO_INIZIALE)+" (Gordo)";
		double grado_sazieta = InputDati.leggiIntero(GRADO_SAZIETA, 0, 100);
		return new TamagotchiGordo(nome, 100, grado_sazieta);
	}

}
