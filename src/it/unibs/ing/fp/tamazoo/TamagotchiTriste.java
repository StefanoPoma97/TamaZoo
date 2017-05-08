package it.unibs.ing.fp.tamazoo;


public class TamagotchiTriste extends Tamagotchi{
	
	public TamagotchiTriste (String _nome, double _grado_affettivita, double _grado_sazieta)
	{
		super(_nome, _grado_affettivita, _grado_sazieta);
	}
	

	
	public boolean sonoMorto ()
	{
		if ((getGrado_sazieta()<=0)||(getGrado_sazieta()>=100))
			return true;
		else
			return false;
	}
	
	//metodo per indicare se il Tamagotchi è triste
	public boolean sonoTriste ()
	{
			return true;
	}

}
