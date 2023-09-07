package system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import storage.Actions;
import storage.MainStats;
import storage.NameStats;
import storage.OtherStats;
import system.readHandler.HandleActions;
import system.readHandler.MainStatsHandler;
import system.readHandler.NameStatsHandler;
import system.readHandler.OtherStatsHandler;

public class ReadAndStore {

	public static ArrayList<NameStats> nameStats = new ArrayList<>();
	public static ArrayList<MainStats> mainStats = new ArrayList<>();
	public static ArrayList<Actions> actions = new ArrayList<>();
	public static ArrayList<OtherStats> otherStats = new ArrayList<>();
	
	public static void readAndStore() throws IOException { // Uses at launch
		String[] content = storeString();
		nameStats.add(NameStatsHandler.handleNameStats(content));
		mainStats.add(MainStatsHandler.handleMainStats(content));
		actions.add(HandleActions.handleActions(content));
		otherStats.add(OtherStatsHandler.handleOtherStats(content));
	}
	
	private static String[] storeString() throws IOException { // Uses at launch
        BufferedReader reader = new BufferedReader(new FileReader("Monster.txt"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        
        String[] lines = sb.toString().split("\n");
        return lines;
	}
	
	public static void readAndStoreFile(File file) throws IOException { // When selecting txt from mainWindow, it uses this
		String[] content = storeStringFile(file);
		nameStats.add(NameStatsHandler.handleNameStats(content)); // basically the statblock is divided into 4 stats(not official)
		mainStats.add(MainStatsHandler.handleMainStats(content));// Each handler class handles 1 par tof it
		actions.add(HandleActions.handleActions(content));
		otherStats.add(OtherStatsHandler.handleOtherStats(content));
	}
	
	private static String[] storeStringFile(File file) throws IOException { // and uses this
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        
        String[] lines = sb.toString().split("\n");
        return lines;
	}
	
}
