package system.readHandler;

import java.util.ArrayList;
import storage.MainStats;

public class MainStatsHandler {
	static int COUNT = 0;

	public static MainStats handleMainStats(String[] content) {
		int[] stats = findStats(content);
		String[] savingThrows = findUninterruptedNames(content, "Saving Throws", 13);
		String[] skills = findUninterruptedNames(content, "Skills", 6);
		String[] resistances = findUninterruptedNames(content, "Damage Resistances", 18);
		String[] damageImmunities = findUninterruptedNames(content, "Damage Immunities", 18);
		String[] conditionImmunities = findUninterruptedNames(content, "Condition Immunities", 20);
		String[][] senses = findSenses(content);
		String[] languages = findUninterruptedNames(content, "Languages", 9);
		int[] CRStuff = handleChallengeAndProficiency(content);
		int challengeRating = CRStuff[0];
		int experienceWorth = CRStuff[1];
		int proficiencyBonus = CRStuff[2];
		String[][] traits = getTraits(content);
		
		MainStats mainStats = new MainStats(stats[0], stats[1], stats[2], stats[3], stats[4], stats[5], savingThrows, skills, resistances, damageImmunities, conditionImmunities, senses, languages, challengeRating, experienceWorth, proficiencyBonus, traits);
		System.out.println(mainStats.toString());
		return mainStats;
	}
	
	private static String[][] getTraits(String[] content) {
		int start = findBigIndex(content, "Challenge")+1;
		int end = findBigIndex(content, "Actions");
		ArrayList<String> names = new ArrayList<>();
		ArrayList<String> desc = new ArrayList<>();
		for (int i = start; i < end; i++) {
			Boolean foundTrait = false;
			char[] chars = content[i].toCharArray();
					for (int j = 0; j < chars.length; j++) {
						if (foundTrait == false && chars[j] == '.') {
							names.add(getNameBeforeDot(chars));
							desc.add(getStringFromPoint(chars, COUNT));
							foundTrait = true;
							j = COUNT;
						}else if (foundTrait == false && chars[j] == '(') {
							names.add(getNameBeforeBracket(chars));
							desc.add(getStringFromPoint(chars, COUNT));
							foundTrait = true;
							j = COUNT;
						}
					}
		}
		String[][] result = new String[names.size()][2];
		for (int i = 0; i < result.length; i++) {
			result[i][0] = names.get(i);
			result[i][1] = desc.get(i);
		}
		return result;
	}
	
	private static String[] findUninterruptedNames(String[] content, String thingToFind, int smallIndex) {
		int bigIndex = 0;
		for (int i = 0; i < content.length; i++) {
			if (content[i].contains(thingToFind)) {
				bigIndex = i;
				break;
			}
		}
		if (bigIndex == 0) {
			return null;
		}
		char[] chars = content[bigIndex].toCharArray();
		ArrayList<String> names = new ArrayList<>();
		for (int i = smallIndex; i < chars.length; i++) {
			if (Character.isLetter(chars[i])) {
				names.add(getName(chars, i));
				i = COUNT;
				if (names.get(names.size()-1).equals("ft")) {
					names.remove(names.size()-1);
				}
			}
		}
		String[] result = names.toArray(new String[names.size()]);
		return result;
		
	}
	
	private static int[] handleChallengeAndProficiency(String[] content) {
		char[] chars = content[findBigIndex(content, "Challenge")].toCharArray();
		COUNT = 0;
		int CR = -1;
		int exp = -1;
		int prof = -1;
		for (int i = 0; i < chars.length; i++) {
			if (CR == -1 && Character.isDigit(chars[i])) {
				CR = Integer.parseInt(getNumber(chars, i));
				i = COUNT;
			}else if (exp == -1 && Character.isDigit(chars[i])) {
				exp = Integer.parseInt(getSeparatedNumber(chars, i));
				i = COUNT;
			}else if (prof == -1 && chars[i] == '+') {
				i++;
				prof = Integer.parseInt(getNumber(chars, i));
				i = COUNT;
			}
		}
		int[] results = {CR, exp, prof};
		return results;
	}
	
	private static int findBigIndex (String[] content, String thingToFind) {
		int bigIndex = 0;
		for (int i = 0; i < content.length; i++) {
			if (content[i].contains(thingToFind)) {
				bigIndex = i;
				break;
			}
		}
		return bigIndex;
	}
	
	private static int[] findStats(String[] content) {
		char[] chars = content[8].toCharArray();
		int[] stats = new int[6];
		int count = 0;
		
		for (int i = 0; i < chars.length; i++) {
			if (Character.isDigit(chars[i])) {
				stats[count] = Integer.parseInt(getNumber(chars, i));
				count++;
				i = COUNT;
			}else if (chars[i] == '(') {
				i = skipBrackets(chars, i);
			}
		}
		return stats;
	}
	
	private static String getNumber(char[] chars, int index) {
		StringBuilder sBuilder = new StringBuilder();
		COUNT = index;
		while (COUNT < chars.length && Character.isDigit(chars[COUNT])) {
			sBuilder.append(chars[COUNT]);
			COUNT++;
		}
		return sBuilder.toString();
	}
	
	private static String getSeparatedNumber(char[] chars, int index) {
		StringBuilder sBuilder = new StringBuilder();
		COUNT = index;
		while (COUNT < chars.length && Character.isDigit(chars[COUNT]) || chars[COUNT] == ',') {
			if (chars[COUNT] != ',') {
				sBuilder.append(chars[COUNT]);
			}
			COUNT++;
		}
		return sBuilder.toString();
	}
	
	private static String getName(char[] chars, int index) {
		StringBuilder sBuilder = new StringBuilder();
		COUNT = index;
		while (COUNT < chars.length && Character.isLetter(chars[COUNT])) {
			sBuilder.append(chars[COUNT]);
			COUNT++;
		}
		return sBuilder.toString();
	}
	
	private static String[][] findSenses(String[] content) {
		int bigIndex = 0;
		for (int i = 0; i < content.length; i++) {
			if (content[i].contains("Senses")) {
				bigIndex = i;
				break;
			}
		}
		if (bigIndex == 0) {
			return null;}
		
		COUNT = 0;
		char[] chars = content[bigIndex].toCharArray();
		ArrayList<String> senseNames = new ArrayList<>();
		ArrayList<String> senseNumbers = new ArrayList<>();
		StringBuilder sBuilder = new StringBuilder();
		
		for (int i = 6; i < chars.length; i++) {
			if (Character.isLetter(chars[i]) && isThereNumberAfterName(chars, i)) {
				COUNT = i;
				while (Character.isLetter(chars[COUNT])) {
					sBuilder.append(chars[COUNT]);
					COUNT++;
				}
				senseNames.add(sBuilder.toString());
				sBuilder.setLength(0);
				i = COUNT;
			}else if (Character.isDigit(chars[i])) {
				COUNT = i;
				while (COUNT < chars.length && Character.isDigit(chars[COUNT])) {
					sBuilder.append(chars[COUNT]);
					COUNT++;
				}
				senseNumbers.add(sBuilder.toString());
				sBuilder.setLength(0);
				i = COUNT;
			}else if (chars[i] == '(') {
				COUNT = i;
				while (chars[COUNT] == ')') {
					COUNT++; 
				}
				i = COUNT;
			}
		}
		String[][] result = new String[senseNumbers.size()][2];
		for (int i = 0; i < result.length; i++) {
			result[i][0] = senseNames.get(i);
			result[i][1] = senseNumbers.get(i);
		}
		return result;
	}
	
	private static boolean isThereNumberAfterName(char[] chars, int index) {
		int count = index;
		while (Character.isLetter(chars[count])) {
			count++;
		}
		for (int i = count; i < chars.length; i++) {
			if (Character.isLetter(chars[i])) {
				return false;
			}else if (Character.isDigit(chars[i])) {
				return true;
			}
		}
		return false;
	}
	
	private static int skipBrackets(char[] chars, int index) {
		COUNT = index;
		while (chars[COUNT] != ')') {
			COUNT++;
		}
		return COUNT;
	}
	
	private static String getNameBeforeDot(char[] chars) {
		COUNT = 0;
		StringBuilder sBuilder = new StringBuilder();
		while (chars[COUNT] != '.') {
			sBuilder.append(chars[COUNT]);
			COUNT++;
		}
		return sBuilder.toString();
	}
	
	private static String getNameBeforeBracket(char[] chars) {
		COUNT = 0;
		StringBuilder sBuilder = new StringBuilder();
		while (chars[COUNT] != ')') {
			sBuilder.append(chars[COUNT]);
			COUNT++;
		}
		sBuilder.append(')');
		return sBuilder.toString();
	}
	
	private static String getStringFromPoint(char[] chars, int index) {
		StringBuilder sBuilder = new StringBuilder();
		for (int i = index+2; i < chars.length; i++) {
			sBuilder.append(chars[i]);
		}
		return sBuilder.toString();
	}
}
