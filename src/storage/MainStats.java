package storage;

import java.util.Arrays;

public class MainStats {
	private int STR; 
	private int DEX;
	private int CON;
	private int INT;
	private int WIS;
	private int CHA;
	
	private String[] savingThrows; 
	private String[] skills; 
	private String[] resistances; 
	private String[] damageImmunities; 
	private String[] conditionImmunities; 
	private String[][] senses; 
	private String[] languages; 
	private int challengeRating; 
	private int experienceWorth; 
	private int proficiencyBonus; 
	
	private String[][] traits; 

	public MainStats(int sTR, int dEX, int cON, int iNT, int wIS, int cHA, String[] savingThrows, String[] skills,
			String[] resistances, String[] damageImmunities, String[] conditionImmunities, String[][] senses,
			String[] languages, int challengeRating, int experienceWorth, int proficiencyBonus, String[][] traits) {
		STR = sTR;
		DEX = dEX;
		CON = cON;
		INT = iNT;
		WIS = wIS;
		CHA = cHA;
		this.savingThrows = savingThrows;
		this.skills = skills;
		this.resistances = resistances;
		this.damageImmunities = damageImmunities;
		this.conditionImmunities = conditionImmunities;
		this.senses = senses;
		this.languages = languages;
		this.challengeRating = challengeRating;
		this.experienceWorth = experienceWorth;
		this.proficiencyBonus = proficiencyBonus;
		this.traits = traits;
	}

	@Override
	public String toString() {
		return "MainStats [STR=" + STR + ", DEX=" + DEX + ", CON=" + CON + ", INT=" + INT + ", WIS=" + WIS + ", CHA="
				+ CHA + ", savingThrows=" + Arrays.toString(savingThrows) + ", skills=" + Arrays.toString(skills)
				+ ", resistances=" + Arrays.toString(resistances) + ", damageImmunities="
				+ Arrays.toString(damageImmunities) + ", conditionImmunities=" + Arrays.toString(conditionImmunities)
				+ ", senses=" + Arrays.deepToString(senses) + ", languages=" + Arrays.toString(languages)
				+ ", challengeRating=" + challengeRating + ", experienceWorth=" + experienceWorth
				+ ", proficiencyBonus=" + proficiencyBonus + ", traits=" + Arrays.deepToString(traits) + "]";
	}

	public int getSTR() {
		return STR;
	}

	public int getDEX() {
		return DEX;
	}

	public int getCON() {
		return CON;
	}

	public int getINT() {
		return INT;
	}

	public int getWIS() {
		return WIS;
	}

	public int getCHA() {
		return CHA;
	}

	public String[] getSavingThrows() {
		return savingThrows;
	}

	public String[] getSkills() {
		return skills;
	}

	public String[] getResistances() {
		return resistances;
	}

	public String[] getDamageImmunities() {
		return damageImmunities;
	}

	public String[] getConditionImmunities() {
		return conditionImmunities;
	}

	public String[][] getSenses() {
		return senses;
	}

	public String[] getLanguages() {
		return languages;
	}

	public int getChallengeRating() {
		return challengeRating;
	}

	public int getExperienceWorth() {
		return experienceWorth;
	}

	public int getProficiencyBonus() {
		return proficiencyBonus;
	}

	public String[][] getTraits() {
		return traits;
	}
	
	
}
