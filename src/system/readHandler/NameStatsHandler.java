package system.readHandler;

import java.util.ArrayList;


import storage.NameStats;

public class NameStatsHandler {
	private static int COUNT;
	
	public static NameStats handleNameStats(String[] content) {
		String name = findName(content);
		String[] sizeTypeAlignment = handleSizeTypeAlignment(content);
		String size = sizeTypeAlignment[0]; 
		String type = sizeTypeAlignment[1];
		String subType = sizeTypeAlignment[2];
		String alignment = sizeTypeAlignment[3];
		String[] ACs = handleACAll(content);
		int AC = Integer.parseInt(ACs[0]);
		String armorClassType = ACs[1];
		String[] HPs = handleHPAll(content);
		int hitPoints = Integer.parseInt(HPs[0]);
		String hitPointsCalculation = HPs[1];
		String[][] speed = findSpeed(content);
		
		NameStats nameStats = new NameStats(name, type, size, subType, alignment, AC, armorClassType, hitPoints, hitPointsCalculation, speed);
		System.out.println(nameStats.toString());
		COUNT = 0;
		return nameStats;
	}
	
	private static String[] handleSizeTypeAlignment(String[] content) {
		String size = findSize(content);
		String creatureType = findCreatureType(content);
		String subType = findSubType(content); // null if not found
		String alignment = findAlignment(content);
		
		String[] result = {size, creatureType, subType, alignment};
		return result;
	}

	private static String findName(String[] content) {
		String name = content[0];
		return name;
	}
	
	private static String findSize(String[] content) {  
		char[] chars;
		if (content[2].contains("p")) { // Some statblocks(for some unknown reason) do not have page numbers on the 2nd line, so every line can move a bit.
			chars = content[3].toCharArray();
		}else {
			chars = content[2].toCharArray();
		}
		
		StringBuffer sBuffer = new StringBuffer();
		
		while (chars[COUNT] != ' ') {
			sBuffer.append(chars[COUNT]);
			COUNT++;
		}
		COUNT++;
		return sBuffer.toString();
		}
	
	private static String findSubType(String[] content) { // null if not found
		int num;
		if (content[2].contains("p")) {
			num = 3;
		}else {
			num = 2;
		}
		
		if (content[num].contains("(")) {
			StringBuilder sBuilder = new StringBuilder();
			char[] chars = content[3].toCharArray();
			while (chars[COUNT] != ')') {
				sBuilder.append(chars[COUNT]);
				COUNT++;
			}
			COUNT += 3;
			return sBuilder.toString();
		}
		return null;
	}
	
	
	private static String findCreatureType(String[] content) {
		int num;
		if (content[2].contains("p")) {
			num = 3;
		}else {
			num = 2;
		}
		
		char[] chars = content[num].toCharArray();
		StringBuffer sBuffer = new StringBuffer();
		
		while (chars[COUNT] != ' ' && chars[COUNT] != ',') {
			sBuffer.append(chars[COUNT]);
			COUNT++;
		}
		COUNT += 2;
		return sBuffer.toString();
	}
	
	private static String findAlignment(String[] content) {
		int num;
		if (content[2].contains("p")) {
			num = 3;
		}else {
			num = 2;
		}
		
		char[] chars = content[num].toCharArray();
		StringBuilder sBuilder = new StringBuilder();
		for (int i = COUNT; i < chars.length; i++) {
			sBuilder.append(chars[i]);
		}
		return sBuilder.toString();
	}
	
	private static String[] handleACAll(String[] content) {
		int num;
		if (content[2].contains("p")) {
			num = 4;
		}else {
			num = 3;
		}
		
		COUNT = 0;
		char[] chars = content[num].toCharArray();
		int AC = findAC(chars);
		String ACType = findArmorClassName(chars);
		
		String[] results = {String.valueOf(AC), ACType};
		return results;
	}
	
	private static String[] handleHPAll(String[] content) {
		int num;
		if (content[2].contains("p")) {
			num = 5;
		}else {
			num = 4;
		}
		
		COUNT = 0;
		char[] chars = content[num].toCharArray();
		int HP = findAC(chars);
		String HPCalculation = findArmorClassName(chars);
		
		String[] result = {String.valueOf(HP), HPCalculation};
		return result;
	}
	
	private static int findAC(char[] chars) {
		while (!Character.isDigit(chars[COUNT])) {
			COUNT++;
		}
		StringBuilder sBuilder = new StringBuilder();
		while (COUNT < chars.length && Character.isDigit(chars[COUNT])) {
			sBuilder.append(chars[COUNT]);
			COUNT++;
		}
		COUNT += 2;
		return Integer.parseInt(sBuilder.toString());
	}
	
	private static String findArmorClassName(char[] chars) { // returns null if it doesn't exist
		String str = new String(chars);
		if (!str.contains("(")) {
			return null;
		}
		StringBuilder sBuilder = new StringBuilder();
		while (chars[COUNT] != ')') {
			sBuilder.append(chars[COUNT]);
			COUNT++;
		}
		return sBuilder.toString();
	}
	
	private static String[][] findSpeed(String[] content) {
		COUNT = 0;
		char[] chars;
		if (content[2].contains("p")) {
			chars = content[6].toCharArray();
		}else {
			chars = content[5].toCharArray();
		}
		ArrayList<String> speedNames = new ArrayList<>();
		ArrayList<String> speedNumbers = new ArrayList<>();
		StringBuilder sBuilder = new StringBuilder();
		
		for (int i = 0; i < chars.length; i++) {
			if (Character.isLetter(chars[i]) && isThereNumberAfterName(chars, i)) { // This check is needed to ensure the value we are reading is actually a speed and not some explanation
				COUNT = i;
				while (Character.isLetter(chars[COUNT])) { // So when it is an actual speed, it first gets it's name
					sBuilder.append(chars[COUNT]);
					COUNT++;
				}
				speedNames.add(sBuilder.toString());
				sBuilder.setLength(0);
				i = COUNT;
			}else if (Character.isDigit(chars[i])) {
				COUNT = i;
				while (Character.isDigit(chars[COUNT])) { // then the value of that speed
					sBuilder.append(chars[COUNT]);
					COUNT++;
				}
				speedNumbers.add(sBuilder.toString());
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
		String[][] result = new String[speedNumbers.size()][2]; // Now to convert it to the desired format and it's done
		for (int i = 0; i < result.length; i++) {
			result[i][0] = speedNames.get(i);
			result[i][1] = speedNumbers.get(i);
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
}
