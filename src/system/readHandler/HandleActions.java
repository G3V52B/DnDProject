package system.readHandler;

import java.util.ArrayList;

import storage.Actions;

public class HandleActions {
	static int COUNT = 0;

	public static Actions handleActions(String[] content) {
		String[][] regularActions = findActions(content, "Actions", "Bonus Actions"); // Here not every monster has everything. So we need to find anchor points.
		String[][] bonusActions = findActions(content, "Bonus Actions", "Legendary Actions");
		String[][] legendaryActions = findActions(content, "Legendary Actions", "Lair Actions");
		String[][] lairActions = findActions(content, "Lair Actions", "Regional Effects");
		
		Actions actions = new Actions(regularActions, bonusActions, legendaryActions, lairActions);
		System.out.println(actions.toString());
		return actions;
	} 
	
	private static String[][] findActions(String[] content, String begin, String finish) {
		int start = findBigIndex(content, begin)+1;
		int end = findBigIndex(content, finish);
		if (start == content.length) {
			return null;
		}
		ArrayList<String> actionNames = new ArrayList<>();
		ArrayList<String> actionDescs = new ArrayList<>();
		for (int i = start; i < end; i++) {
			if (lairFirstLineChecker(content[i])) { // We don't want to do anything with the first line since that is just explanation
				i++;
			}
			if (legendaryFirstLineChecker(content[i])) {
				i++;
			}
			if (spellFirstLineChecker(content[i])) {
				i++;
			}
			Boolean foundAction = false;
			char[] chars = content[i].toCharArray();
			for (int j = 0; j < chars.length; j++) {
				if (foundAction == false && chars[j] == '.') { // getting the name and description
					actionNames.add(getNameBeforeDot(chars));
					actionDescs.add(getStringFromPoint(chars, COUNT));
					foundAction = true;
					j = COUNT;
				}else if (foundAction == false && chars[j] == '(') {
					actionNames.add(getNameBeforeBracket(chars));
					actionDescs.add(getStringFromPoint(chars, COUNT));
					foundAction = true;
					j = COUNT;
				}
			}
		}
		String[][] result = new String[actionNames.size()][2];
		for (int i = 0; i < result.length; i++) {
			result[i][0] = actionNames.get(i); // converting it
			result[i][1] = actionDescs.get(i);
		}
		return result;
	}
	
	private static boolean lairFirstLineChecker(String content) { // It is relatively easy to check for the first line, because every explanation follows the same format and uses the same words
		return content.contains("On initiative count 20 (losing initiative ties)") &&
		           content.contains("can take one of the following lair actions") &&
		           content.contains("can't take the same lair action two rounds in a row");
	}
	
	private static boolean legendaryFirstLineChecker(String content) {
		return content.contains("can take 3 legendary actions, choosing from the options below") &&
		           content.contains("Only one legendary action can be used at a time and only at the end of another creature's turn") &&
		           content.contains("regains spent legendary actions at the start of its turn");
	}
	
	private static boolean spellFirstLineChecker(String content) {
		return (content.contains("casts one of the following spells") || content.contains("has the following")) &&
		           content.contains("spellcasting ability") &&
		           content.contains("Spellcasting");
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
	
	private static int findBigIndex (String[] content, String thingToFind) {
		for (int i = 0; i < content.length; i++) {
			if (content[i].contains(thingToFind)) {
				return i;
			}
		}
		return content.length;
	}
}
