package storage;

import java.util.Arrays;

public class Actions { // For String[][] : first [] is the name, and the second[] is the description
	private String[][] regularActions; // Each monster has 1 action/round, these are it's possible actions
	private String[][] bonusActions; // Each monster also has 1 bonus action/round but not every monster has abilities that use said action
	private String[][] legendaryActions;// Usually only bosses have legendary actions, to balance out action economy
	private String[][] lairActions; // Same with legendaryActions but if it has a special lair(or base)
	
	
	public Actions(String[][] regularActions, String[][] bonusActions, String[][] legendaryActions,
			String[][] lairActions) {
		this.regularActions = regularActions;
		this.bonusActions = bonusActions;
		this.legendaryActions = legendaryActions;
		this.lairActions = lairActions;
	}


	@Override
	public String toString() {
		return "Actions [regularActions=" + Arrays.deepToString(regularActions) + ", bonusActions="
				+ Arrays.deepToString(bonusActions) + ", legendaryActions=" + Arrays.deepToString(legendaryActions)
				+ ", lairActions=" + Arrays.deepToString(lairActions) + "]";
	}


	public String[][] getRegularActions() {
		return regularActions;
	}


	public String[][] getBonusActions() {
		return bonusActions;
	}


	public String[][] getLegendaryActions() {
		return legendaryActions;
	}


	public String[][] getLairActions() {
		return lairActions;
	}
	
	
}
