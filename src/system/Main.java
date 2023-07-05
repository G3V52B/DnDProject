package system;

import java.io.IOException;

import ui.MainWindow;

public class Main {

	public static void main(String[] args) throws IOException {
		ReadAndStore.readAndStore(); // This is used so a default monster is loaded every time you open the program
		MainWindow mainWindow = new MainWindow();
		System.out.println("done! :)");
	}

}
