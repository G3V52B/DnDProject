package storage;

import java.util.Arrays;

public class OtherStats {
	private int spellSaveDC;
	private String spellModifier;
	private String[][] spells;
	private String[][] regionalEffects;
	
	
	public OtherStats( int spellSaveDC, String spellModifier, String[][] spells, String[][] regionalEffects) {
		this.spellSaveDC = spellSaveDC;
		this.spellModifier = spellModifier;
		this.spells = spells;
		this.regionalEffects = regionalEffects;
	}


	@Override
	public String toString() {
		return "OtherStats [spellSaveDC=" + spellSaveDC + ", spellModifier=" + spellModifier + ", spells=" + Arrays.deepToString(spells) + ", regionalEffects="
				+ Arrays.deepToString(regionalEffects) + "]";
	}


	public int getSpellSaveDC() {
		return spellSaveDC;
	}


	public String getSpellModifier() {
		return spellModifier;
	}


	public String[][] getSpells() {
		return spells;
	}


	public String[][] getRegionalEffects() {
		return regionalEffects;
	}

	
	
	
}
