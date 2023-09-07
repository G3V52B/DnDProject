package storage;


public class NameStats {
	private String name;
	private String creatureType; 
	private String size; 
	private String creatureSubType; 
	private String alignment; 
	private int armorClass; 
	private String armorClassName; 
	private int hitPoints;
	private String hitPointsCalculation; 
	private String[][] speed; 
	
	
	public NameStats(String name, String creatureType, String size, String creatureSubType, String alignment,
			int armorClass, String armorClassName, int hitPoints, String hitPointsCalculation, String[][] speed) {
		this.name = name;
		this.creatureType = creatureType;
		this.size = size;
		this.creatureSubType = creatureSubType;
		this.alignment = alignment;
		this.armorClass = armorClass;
		this.armorClassName = armorClassName;
		this.hitPoints = hitPoints;
		this.hitPointsCalculation = hitPointsCalculation;
		this.speed = speed;
	}


	@Override
	public String toString() {
		return "NameStats [name=" + name + ", creatureType=" + creatureType + ", size=" + size + ", creatureSubType="
				+ creatureSubType + ", alignment=" + alignment + ", armorClass=" + armorClass + ", armorClassName="
				+ armorClassName + ", hitPoints=" + hitPoints + ", hitPointsCalculation=" + hitPointsCalculation
				+ ", speed=" + generateStringFromArray(speed) + "]";
	}
	
	private static String generateStringFromArray(String[][] string) {
		StringBuilder sb = new StringBuilder();
	    for (String[] row : string) {
	        for (String element : row) {
	            sb.append(element).append(" ");
	        }
	    }
	    return sb.toString();
	}


	public String getName() {
		return name;
	}


	public String getCreatureType() {
		return creatureType;
	}


	public String getSize() {
		return size;
	}


	public String getCreatureSubType() {
		return creatureSubType;
	}


	public String getAlignment() {
		return alignment;
	}


	public int getArmorClass() {
		return armorClass;
	}


	public String getArmorClassName() {
		return armorClassName;
	}


	public int getHitPoints() {
		return hitPoints;
	}


	public String getHitPointsCalculation() {
		return hitPointsCalculation;
	}


	public String[][] getSpeed() {
		return speed;
	}

	
}
