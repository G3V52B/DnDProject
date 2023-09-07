package system.readHandler;

import java.util.ArrayList;

import storage.OtherStats;

public class OtherStatsHandler {
	private static int SPELLSAVEDC = 0;
	private static String MODIFIER;
    private static int COUNT = 0;

    public static OtherStats handleOtherStats(String[] content) {
        String[][] spells = spellHandler(content);
        String[][] regionalEffects = lairHandler(content);

        OtherStats otherStats = new OtherStats(SPELLSAVEDC, MODIFIER, spells, regionalEffects);
        System.out.println(otherStats.toString());
        return otherStats;
    }
    
    private static String[][] lairHandler(String[] content) { // Really similar to findActions in HandleActions.java
    	int start = findBigIndex(content, "Regional Effects")+1;
    	int end = content.length;
    	if (start == content.length) {
			return null;
		}
    	ArrayList<String> effectNames = new ArrayList<>();
    	ArrayList<String> effectDescs = new ArrayList<>();
    	for (int i = start; i < end; i++) {
			if (regionalFirstLineChecker(content[i])) {
				i++;
			}
			if (content[i].length() <= 1) {
				i++;
			}
			char[] chars = content[i].toCharArray();
			effectNames.add(getNameBeforeDot(chars));
			effectDescs.add(getStringFromPoint(chars, COUNT));
		}
    	String[][] result = new String[effectNames.size()][2];
		for (int i = 0; i < result.length; i++) {
			result[i][0] = effectNames.get(i);
			result[i][1] = effectDescs.get(i);
		}
		return result;
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
    
    private static String getStringFromPoint(char[] chars, int index) {
		StringBuilder sBuilder = new StringBuilder();
		for (int i = index+2; i < chars.length; i++) {
			sBuilder.append(chars[i]);
		}
		return sBuilder.toString();
	}
    
    
    private static boolean regionalFirstLineChecker(String content) {
		return content.contains("The region surrounding") &&
		           content.contains("lair is altered") &&
		           content.contains("creating one or more of the following effects");
	}

    private static String[][] spellHandler(String[] content) { // Had to write a completely separate algorythm for this and was a lot more difficult than the others
        int start = findSpellcasting(content);
        int end = findBigIndex(content, "Bonus Actions");
        if (start == content.length) {
            return null;
        }
        ArrayList<String[][]> spells = new ArrayList<>();
        boolean DCFound = false;
        for (int i = start; i < end; i++) {
            if (DCFound == false && spellFirstLineChecker(content[i])) {
                char[] chars = content[i].toCharArray();
                SPELLSAVEDC = getSpellDC(chars);
                MODIFIER = getSpellModifier(chars);
                DCFound = true;
            } else if (content[i].contains(":")) {
                String[][] temp = getSpells(content[i].toCharArray());
                spells.add(temp);
            }
        }
        String[][] result = convertStringArrays(spells);
        return result;

    }
    
    private static String getSpellModifier(char[] chars) {
    	String[] wordsToCheck = {"Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma"};
        String content = new String(chars);
        for (String word : wordsToCheck) {
            if (content.contains(word)) {
                return word;
            }
        }
        
        return null;
    }

    private static String[][] convertStringArrays(ArrayList<String[][]> list) { // DO NOT TOUCH THIS IT TOOK WAY TOO LONG
        String[][] result = new String[list.size()][];
        for (int i = 0; i < result.length; i++) {
            String[][] spellArray = list.get(i);
            result[i] = new String[spellArray[1].length + 1];
            result[i][0] = spellArray[0][0]; // Assign spell use to the first element of the row
            for (int j = 0; j < spellArray[1].length; j++) {
                result[i][j + 1] = spellArray[1][j]; // Assign spell names to subsequent elements of the row
            }
        }
        return result;
    }
    

    private static String[][] getSpells(char[] chars) {
        String content = new String(chars);
        String[] parts = content.split(":"); // THis porved relatively easy
        String spellUse = parts[0].trim(); // Just split the line in two at ":" the first part is the number of uses
        String[] spellNames = parts[1].split(","); // The second part can then be more divivded, because those are the spells
        for (int i = 0; i < spellNames.length; i++) {
			spellNames[i] = spellNames[i].trim();
		}
        String[][] result = new String[2][];
        result[0] = new String[]{spellUse};
        result[1] = new String[spellNames.length];
        for (int i = 0; i < spellNames.length; i++) {
            result[1][i] = spellNames[i].trim();
        }
        return result;
    }

    private static int findSpellcasting(String[] content) {
        for (int i = 0; i < content.length; i++) {
            if (spellFirstLineChecker(content[i])) {
                return i;
            }
        }
        return content.length;
    }

    private static int getSpellDC(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'C' && chars[i - 1] == 'D') {
                COUNT = i + 2;
                StringBuilder sBuilder = new StringBuilder();
                while (Character.isDigit(chars[COUNT])) {
                    sBuilder.append(chars[COUNT]);
                    COUNT++;
                }
                return Integer.valueOf(sBuilder.toString());
            }
        }
        return -1;
    }

    private static int findBigIndex(String[] content, String thingToFind) {
        for (int i = 0; i < content.length; i++) {
            if (content[i].contains(thingToFind)) {
                return i;
            }
        }
        return content.length;
    }

    private static boolean spellFirstLineChecker(String content) {
        return content.contains("spells") &&
                content.contains("spellcasting ability") &&
                content.contains("Spellcasting");
    }
}
