package ui;

import java.util.Arrays;

import system.ReadAndStore;

public class UIHelper {
	
	public static String generateTextAreaFromNameAndDesc(String[][] array) { // For data such as String[name][desc]
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			sBuilder.append(array[i][0]+": ");
			sBuilder.append(array[i][1]+"\n");
		}
		return sBuilder.toString();
	}

	public static String convertStringArray(String[][] array) { // For data such as String[name][multiple data]
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				sBuilder.append(array[i][j] + " ");
			}
		}
		return sBuilder.toString();
	}
	
	public static String convertStringArray(String[] array) { // For just multiple data FE.: resistances, immunities
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
				sBuilder.append(array[i] + " ");
		}
		return sBuilder.toString();
	}
	
	public static String convertStringArrayWithMod(String[] array) { // Some abilities have modifiers
		if (array == null || array.length < 1) {
			return null;
		}
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
				sBuilder.append(array[i] + " ");
				sBuilder.append("+"+calculateModifier(array[i])+", ");
		}
		return sBuilder.toString();
	}
	
	public static int calculateModifier(String stat) { // This calculates that said modifer
		if (stat.toLowerCase().equals("str")) {
			int number = ReadAndStore.mainStats.get(0).getSTR();
			int result = (int) Math.floor((number - 10) / 2);
			result += ReadAndStore.mainStats.get(0).getProficiencyBonus();
			return result;
		}
		if (stat.toLowerCase().equals("dex")) {
			int number = ReadAndStore.mainStats.get(0).getDEX();
			int result = (int) Math.floor((number - 10) / 2);
			result += ReadAndStore.mainStats.get(0).getProficiencyBonus();
			return result;
		}
		if (stat.toLowerCase().equals("con")) {
			int number = ReadAndStore.mainStats.get(0).getCON();
			int result = (int) Math.floor((number - 10) / 2);
			result += ReadAndStore.mainStats.get(0).getProficiencyBonus();
			return result;
		}
		if (stat.toLowerCase().equals("int")) {
			int number = ReadAndStore.mainStats.get(0).getINT();
			int result = (int) Math.floor((number - 10) / 2);
			result += ReadAndStore.mainStats.get(0).getProficiencyBonus();
			return result;
		}
		if (stat.toLowerCase().equals("wis")) {
			int number = ReadAndStore.mainStats.get(0).getWIS();
			int result = (int) Math.floor((number - 10) / 2);
			result += ReadAndStore.mainStats.get(0).getProficiencyBonus();
			return result;
		}
		if (stat.toLowerCase().equals("cha")) {
			int number = ReadAndStore.mainStats.get(0).getCHA();
			int result = (int) Math.floor((number - 10) / 2);
			result += ReadAndStore.mainStats.get(0).getProficiencyBonus();
			return result;
		}
		String[] strengthBased = {"athletics"}; // Each skill is assigned to a base stat
		String[] dexterityBased = {"Acrobatics", "Sleight of Hand", "Stealth"};
		String[] wisdomBased = {"Animal Handling", "Insight", "Medicine", "Perception", "Survival"};
		String[] intelligenceBased = {"Arcana", "History", "Investigation", "Nature", "Religion"};
		String[] charismaBased = {"Deception", "Intimidation", "Performance", "Persuasion"};
		if (Arrays.asList(strengthBased).contains(stat)) {
			int number = ReadAndStore.mainStats.get(0).getSTR();
			int result = (int) Math.floor((number - 10) / 2);
			result += ReadAndStore.mainStats.get(0).getProficiencyBonus();
			return result;
		}
		if (Arrays.asList(dexterityBased).contains(stat)) {
			int number = ReadAndStore.mainStats.get(0).getDEX();
			int result = (int) Math.floor((number - 10) / 2);
			result += ReadAndStore.mainStats.get(0).getProficiencyBonus();
			return result;
		}
		if (Arrays.asList(wisdomBased).contains(stat)) {
			int number = ReadAndStore.mainStats.get(0).getWIS();
			int result = (int) Math.floor((number - 10) / 2);
			result += ReadAndStore.mainStats.get(0).getProficiencyBonus();
			return result;
		}
		if (Arrays.asList(intelligenceBased).contains(stat)) {
			int number = ReadAndStore.mainStats.get(0).getINT();
			int result = (int) Math.floor((number - 10) / 2);
			result += ReadAndStore.mainStats.get(0).getProficiencyBonus();
			return result;
		}
		if (Arrays.asList(charismaBased).contains(stat)) {
			int number = ReadAndStore.mainStats.get(0).getCHA();
			int result = (int) Math.floor((number - 10) / 2);
			result += ReadAndStore.mainStats.get(0).getProficiencyBonus();
			return result;
		}
		return -1;
	}
}
