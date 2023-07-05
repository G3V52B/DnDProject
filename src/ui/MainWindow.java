package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import storage.Actions;
import storage.MainStats;
import storage.NameStats;
import storage.OtherStats;
import system.ReadAndStore;

public class MainWindow implements ActionListener{

	JFrame frame = new JFrame();
	private static ArrayList<NameStats> nameStats = ReadAndStore.nameStats; // Makes it easier to get the data
	private static ArrayList<MainStats> mainStats = ReadAndStore.mainStats;
	private static ArrayList<Actions> actions = ReadAndStore.actions;
	private static ArrayList<OtherStats> otherStats = ReadAndStore.otherStats;
	JButton selectFileButton;
	
	public MainWindow() {
		Color backgroundColor = new Color(34, 34, 34);
		Color goldColor = new Color(184,134,11);
		Font defaultFont = new Font("Arial", Font.PLAIN, 16);
		Font defaultBiggerFont = new Font("Arial", Font.PLAIN, 26);
		Font defaultBoldFont = new Font("Arial", Font.BOLD, 16);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500,1000);
		frame.setLayout(null);
		frame.getContentPane().setBackground(backgroundColor); // making sure you have the "cool looks"
		
		JTextField nameField = new JTextField();
		nameField.setText(nameStats.get(0).getName());
		nameField.setBounds(0, 5, 400, 30);
		nameField.setEditable(false);
		nameField.setBackground(backgroundColor);
		nameField.setForeground(goldColor);
		nameField.setBorder(null);
		nameField.setFont(defaultBiggerFont);
		frame.add(nameField);
		
		JTextField typeAndAlignmentField = new JTextField(); // Now to carefully code every stat's position and content
		typeAndAlignmentField.setText(nameStats.get(0).getSize()+" "+nameStats.get(0).getCreatureType() + " (" + nameStats.get(0).getCreatureSubType() + "), " + nameStats.get(0).getAlignment());
		typeAndAlignmentField.setBounds(0, 40, 400, 20);
		typeAndAlignmentField.setEditable(false);
		typeAndAlignmentField.setBackground(backgroundColor);
		typeAndAlignmentField.setForeground(Color.white);
		typeAndAlignmentField.setBorder(null);
		typeAndAlignmentField.setFont(defaultFont);
		frame.add(typeAndAlignmentField);
		
		JSeparator separator = new JSeparator(); // These are present to improve readability
		separator.setBounds(0, 70, 690, 2);
		separator.setForeground(goldColor);
		separator.setBorder(new LineBorder(goldColor, 2));
		frame.add(separator);
		
		JTextField ACField = new JTextField();
		ACField.setText("Armor class " + nameStats.get(0).getArmorClass() + " (" + nameStats.get(0).getArmorClassName() + ")");
		ACField.setBounds(0, 80, 400, 20);
		ACField.setEditable(false);
		ACField.setBackground(backgroundColor);
		ACField.setForeground(Color.white);
		ACField.setBorder(null);
		ACField.setFont(defaultFont);
		frame.add(ACField);
		
		JTextField HPField = new JTextField();
		HPField.setText("Hit points " + nameStats.get(0).getHitPoints() + " (" + nameStats.get(0).getHitPointsCalculation() + ")");
		HPField.setBounds(0, 100, 400, 20);
		HPField.setEditable(false);
		HPField.setBackground(backgroundColor);
		HPField.setForeground(Color.white);
		HPField.setBorder(null);
		HPField.setFont(defaultFont);
		frame.add(HPField);
		
		JTextField SpeedField = new JTextField();
		SpeedField.setText(UIHelper.convertStringArray(nameStats.get(0).getSpeed()));
		SpeedField.setBounds(0, 120, 400, 20);
		SpeedField.setEditable(false);
		SpeedField.setBackground(backgroundColor);
		SpeedField.setForeground(Color.white);
		SpeedField.setBorder(null);
		SpeedField.setFont(defaultFont);
		frame.add(SpeedField);

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(0, 150, 690, 2);
		separator2.setForeground(goldColor);
		separator2.setBorder(new LineBorder(goldColor, 2));
		frame.add(separator2);
		
		JTextField StatNamesField = new JTextField();
		StatNamesField.setText("STR                    DEX                    CON                    INT                    WIS                    CHA");
		StatNamesField.setBounds(0, 170, 690, 20);
		StatNamesField.setEditable(false);
		StatNamesField.setBackground(backgroundColor);
		StatNamesField.setForeground(Color.white);
		StatNamesField.setBorder(null);
		StatNamesField.setFont(defaultBoldFont);
		frame.add(StatNamesField);
		
		JTextField statNumbersField = new JTextField();
		
		statNumbersField.setText(" "+mainStats.get(0).getSTR()+"                        "+mainStats.get(0).getDEX()+"                         "+mainStats.get(0).getCON()+"                       "+mainStats.get(0).getINT()+"                      "+mainStats.get(0).getWIS()+"                        "+mainStats.get(0).getCHA());
		statNumbersField.setBounds(0, 190, 690, 20);
		statNumbersField.setEditable(false);
		statNumbersField.setBackground(backgroundColor);
		statNumbersField.setForeground(Color.white);
		statNumbersField.setBorder(null);
		statNumbersField.setFont(defaultFont);
		frame.add(statNumbersField);
		
		JSeparator separator3 = new JSeparator();
		separator3.setBounds(0, 230, 690, 2);
		separator3.setForeground(goldColor);
		separator3.setBorder(new LineBorder(goldColor, 2));
		frame.add(separator3);
		
		JTextField savingThrowsField = new JTextField();
		savingThrowsField.setText("Saving Throws: "+UIHelper.convertStringArrayWithMod(mainStats.get(0).getSavingThrows()));
		savingThrowsField.setBounds(0, 250, 690, 20);
		savingThrowsField.setEditable(false);
		savingThrowsField.setBackground(backgroundColor);
		savingThrowsField.setForeground(Color.white);
		savingThrowsField.setBorder(null);
		savingThrowsField.setFont(defaultFont);
		frame.add(savingThrowsField);
		
		JTextField skillsField = new JTextField();
		skillsField.setText("Skills: "+UIHelper.convertStringArrayWithMod(mainStats.get(0).getSkills()));
		skillsField.setBounds(0, 270, 690, 20);
		skillsField.setEditable(false);
		skillsField.setBackground(backgroundColor);
		skillsField.setForeground(Color.white);
		skillsField.setBorder(null);
		skillsField.setFont(defaultFont);
		frame.add(skillsField);
		
		JTextField resistancesField = new JTextField();
		if (mainStats.get(0).getResistances() != null && mainStats.get(0).getResistances().length > 0) {
			resistancesField.setText("Damage Resistances: "+UIHelper.convertStringArray(mainStats.get(0).getResistances()));
		}else {// These checks are present because not every monster possesses these stats
			resistancesField.setText("Damage Resistances: -");
		}
		resistancesField.setBounds(0, 290, 690, 20);
		resistancesField.setEditable(false);
		resistancesField.setBackground(backgroundColor);
		resistancesField.setForeground(Color.white);
		resistancesField.setBorder(null);
		resistancesField.setFont(defaultFont);
		frame.add(resistancesField);
		
		JTextField dmgImmunitieField = new JTextField();
		if (mainStats.get(0).getDamageImmunities() != null && mainStats.get(0).getDamageImmunities().length > 0) {
			dmgImmunitieField.setText("Damage Immunities: "+UIHelper.convertStringArray(mainStats.get(0).getDamageImmunities()));
		}else {
			dmgImmunitieField.setText("Damage Immunities: -");
		}
		dmgImmunitieField.setBounds(0, 310, 690, 20);
		dmgImmunitieField.setEditable(false);
		dmgImmunitieField.setBackground(backgroundColor);
		dmgImmunitieField.setForeground(Color.white);
		dmgImmunitieField.setBorder(null);
		dmgImmunitieField.setFont(defaultFont);
		frame.add(dmgImmunitieField);
		
		JTextField conditionImmunitiesField = new JTextField();
		if (mainStats.get(0).getConditionImmunities() != null && mainStats.get(0).getConditionImmunities().length > 0) {
			conditionImmunitiesField.setText("Condition Immunities: "+UIHelper.convertStringArray(mainStats.get(0).getConditionImmunities()));
		}else {
			conditionImmunitiesField.setText("Condition Immunities: -");
		}
		conditionImmunitiesField.setBounds(0, 330, 690, 20);
		conditionImmunitiesField.setEditable(false);
		conditionImmunitiesField.setBackground(backgroundColor);
		conditionImmunitiesField.setForeground(Color.white);
		conditionImmunitiesField.setBorder(null);
		conditionImmunitiesField.setFont(defaultFont);
		frame.add(conditionImmunitiesField);
		
		JTextField sensesField = new JTextField();
		sensesField.setText("Senses: "+UIHelper.convertStringArray(mainStats.get(0).getSenses()));
		sensesField.setBounds(0, 350, 690, 20);
		sensesField.setEditable(false);
		sensesField.setBackground(backgroundColor);
		sensesField.setForeground(Color.white);
		sensesField.setBorder(null);
		sensesField.setFont(defaultFont);
		frame.add(sensesField);
		
		JTextField languagesField = new JTextField();
		languagesField.setText("Languages: "+UIHelper.convertStringArray(mainStats.get(0).getLanguages()));
		languagesField.setBounds(0, 370, 690, 20);
		languagesField.setEditable(false);
		languagesField.setBackground(backgroundColor);
		languagesField.setForeground(Color.white);
		languagesField.setBorder(null);
		languagesField.setFont(defaultFont);
		frame.add(languagesField);
		
		JTextField challengeField = new JTextField();
		challengeField.setText("Challenge: "+mainStats.get(0).getChallengeRating()+" ("+mainStats.get(0).getExperienceWorth()+" XP)");
		challengeField.setBounds(0, 390, 500, 20);
		challengeField.setEditable(false);
		challengeField.setBackground(backgroundColor);
		challengeField.setForeground(Color.white);
		challengeField.setBorder(null);
		challengeField.setFont(defaultFont);
		frame.add(challengeField);
		
		JTextField proficiencyField = new JTextField();
		proficiencyField.setText("Proficiency Bonus +"+mainStats.get(0).getProficiencyBonus());
		proficiencyField.setBounds(500, 390, 200, 20);
		proficiencyField.setEditable(false);
		proficiencyField.setBackground(backgroundColor);
		proficiencyField.setForeground(Color.white);
		proficiencyField.setBorder(null);
		proficiencyField.setFont(defaultFont);
		frame.add(proficiencyField);
		
		JSeparator separator4 = new JSeparator();
		separator4.setBounds(0, 420, 690, 2);
		separator4.setForeground(goldColor);
		separator4.setBorder(new LineBorder(goldColor, 2));
		frame.add(separator4);
		
		JTextArea traitsArea = new JTextArea();
		traitsArea.setText(UIHelper.generateTextAreaFromNameAndDesc(mainStats.get(0).getTraits()));
		traitsArea.setBounds(0, 430, 600, 80);
		traitsArea.setEditable(false);
		traitsArea.setBackground(backgroundColor);
		traitsArea.setForeground(Color.white);
		traitsArea.setBorder(null);
		traitsArea.setFont(defaultFont);
		traitsArea.setBorder(null);
		frame.add(traitsArea);
		JScrollPane traitJScrollPane = new JScrollPane(traitsArea);
		traitJScrollPane.setBounds(0, 430, 600, 80);
		traitJScrollPane.setBackground(backgroundColor);
		traitJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		traitJScrollPane.setBorder(null);
		frame.add(traitJScrollPane);
		
		JTextField actionsField = new JTextField();
		actionsField.setText("Actions");
		actionsField.setBounds(0, 520, 600, 20);
		actionsField.setEditable(false);
		actionsField.setBackground(backgroundColor);
		actionsField.setForeground(goldColor);
		actionsField.setBorder(null);
		actionsField.setFont(defaultBiggerFont);
		frame.add(actionsField);
		
		JSeparator separator5 = new JSeparator();
		separator5.setBounds(0, 550, 690, 2);
		separator5.setForeground(goldColor);
		separator5.setBorder(new LineBorder(goldColor, 2));
		frame.add(separator5);
		
		
		JTextArea actionsArea = new JTextArea();
		actionsArea.setText(UIHelper.generateTextAreaFromNameAndDesc(actions.get(0).getRegularActions()));
		actionsArea.setBounds(0, 560, 600, 100);
		actionsArea.setEditable(false);
		actionsArea.setBackground(backgroundColor);
		actionsArea.setForeground(Color.white);
		actionsArea.setBorder(null);
		actionsArea.setFont(defaultFont);
		frame.add(actionsArea);
		JScrollPane actionJScrollPane = new JScrollPane(actionsArea);
		actionJScrollPane.setBounds(0, 560, 600, 100);
		actionJScrollPane.setBackground(backgroundColor);
		actionJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		actionJScrollPane.setBorder(null);
		frame.add(actionJScrollPane);
		
		JTextField bonusActionsField = new JTextField();
		bonusActionsField.setText("Bonus Actions");
		bonusActionsField.setBounds(0, 680, 600, 20);
		bonusActionsField.setEditable(false);
		bonusActionsField.setBackground(backgroundColor);
		bonusActionsField.setForeground(goldColor);
		bonusActionsField.setBorder(null);
		bonusActionsField.setFont(defaultBiggerFont);
		frame.add(bonusActionsField);
		
		JSeparator separator6 = new JSeparator();
		separator6.setBounds(0, 710, 690, 2);
		separator6.setForeground(goldColor);
		separator6.setBorder(new LineBorder(goldColor, 2));
		frame.add(separator6);
		
		JTextArea bonusActionsArea = new JTextArea();
		if (actions.get(0).getBonusActions() != null && actions.get(0).getBonusActions().length > 0) {
			bonusActionsArea.setText(UIHelper.generateTextAreaFromNameAndDesc(actions.get(0).getBonusActions()));
		}else {
			bonusActionsArea.setText("-");
		}
		
		bonusActionsArea.setBounds(0, 720, 600, 70);
		bonusActionsArea.setEditable(false);
		bonusActionsArea.setBackground(backgroundColor);
		bonusActionsArea.setForeground(Color.white);
		bonusActionsArea.setBorder(null);
		bonusActionsArea.setFont(defaultFont);
		frame.add(bonusActionsArea);
		JScrollPane bonusJScrollPane = new JScrollPane(bonusActionsArea);
		bonusJScrollPane.setBounds(0, 720, 600, 70);
		bonusJScrollPane.setBackground(backgroundColor);
		bonusJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		bonusJScrollPane.setBorder(null);
		frame.add(bonusJScrollPane);
		
		JTextField legendaryActionsField = new JTextField();
		legendaryActionsField.setText("Legendary Actions");
		legendaryActionsField.setBounds(0, 810, 600, 20);
		legendaryActionsField.setEditable(false);
		legendaryActionsField.setBackground(backgroundColor);
		legendaryActionsField.setForeground(goldColor);
		legendaryActionsField.setBorder(null);
		legendaryActionsField.setFont(defaultBiggerFont);
		frame.add(legendaryActionsField);
		
		JSeparator separator7 = new JSeparator();
		separator7.setBounds(0, 840, 690, 2);
		separator7.setForeground(goldColor);
		separator7.setBorder(new LineBorder(goldColor, 2));
		frame.add(separator7);
		
		JTextArea legendaryActionsArea = new JTextArea();
		if (actions.get(0).getLegendaryActions() != null && actions.get(0).getLegendaryActions().length > 0) {
			legendaryActionsArea.setText(UIHelper.generateTextAreaFromNameAndDesc(actions.get(0).getLegendaryActions()));
		}else {
			legendaryActionsArea.setText("-");
		}
		
		legendaryActionsArea.setBounds(0, 850, 600, 70);
		legendaryActionsArea.setEditable(false);
		legendaryActionsArea.setBackground(backgroundColor);
		legendaryActionsArea.setForeground(Color.white);
		legendaryActionsArea.setBorder(null);
		legendaryActionsArea.setFont(defaultFont);
		frame.add(legendaryActionsArea);
		JScrollPane legendaryJScrollPane = new JScrollPane(legendaryActionsArea);
		legendaryJScrollPane.setBounds(0, 850, 600, 70);
		legendaryJScrollPane.setBackground(backgroundColor);
		legendaryJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		legendaryJScrollPane.setBorder(null);
		frame.add(legendaryJScrollPane);
		
		JTextField lairActionsField = new JTextField();
		lairActionsField.setText("Lair Actions");
		lairActionsField.setBounds(700, 40, 600, 20);
		lairActionsField.setEditable(false);
		lairActionsField.setBackground(backgroundColor);
		lairActionsField.setForeground(goldColor);
		lairActionsField.setBorder(null);
		lairActionsField.setFont(defaultBiggerFont);
		frame.add(lairActionsField);
		
		JSeparator separator8 = new JSeparator();
		separator8.setBounds(700, 70, 690, 2);
		separator8.setForeground(goldColor);
		separator8.setBorder(new LineBorder(goldColor, 2));
		frame.add(separator8);
		
		JTextArea lairActionsArea = new JTextArea();
		if (actions.get(0).getLairActions() != null && actions.get(0).getLairActions().length > 0) {
			lairActionsArea.setText(UIHelper.generateTextAreaFromNameAndDesc(actions.get(0).getLairActions()));
		}else {
			lairActionsArea.setText("-");
		}
		
		lairActionsArea.setBounds(700, 80, 600, 70);
		lairActionsArea.setEditable(false);
		lairActionsArea.setBackground(backgroundColor);
		lairActionsArea.setForeground(Color.white);
		lairActionsArea.setBorder(null);
		lairActionsArea.setFont(defaultFont);
		frame.add(lairActionsArea);
		JScrollPane lairJScrollPane = new JScrollPane(lairActionsArea);
		lairJScrollPane.setBounds(700, 80, 600, 70);
		lairJScrollPane.setBackground(backgroundColor);
		lairJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		lairJScrollPane.setBorder(null);
		frame.add(lairJScrollPane);
		
		JTextField regionalEffectsField = new JTextField();
		regionalEffectsField.setText("Regional Effects");
		regionalEffectsField.setBounds(700, 170, 600, 20);
		regionalEffectsField.setEditable(false);
		regionalEffectsField.setBackground(backgroundColor);
		regionalEffectsField.setForeground(goldColor);
		regionalEffectsField.setBorder(null);
		regionalEffectsField.setFont(defaultBiggerFont);
		frame.add(regionalEffectsField);
		
		JSeparator separator9 = new JSeparator();
		separator9.setBounds(700, 200, 690, 2);
		separator9.setForeground(goldColor);
		separator9.setBorder(new LineBorder(goldColor, 2));
		frame.add(separator9);
		
		JTextArea regionalEffectsArea = new JTextArea();
		if (otherStats.get(0).getRegionalEffects() != null && otherStats.get(0).getRegionalEffects().length > 0) {
			regionalEffectsArea.setText(UIHelper.generateTextAreaFromNameAndDesc(otherStats.get(0).getRegionalEffects()));
		}else {
			regionalEffectsArea.setText("-");
		}
		
		regionalEffectsArea.setBounds(700, 210, 600, 70);
		regionalEffectsArea.setEditable(false);
		regionalEffectsArea.setBackground(backgroundColor);
		regionalEffectsArea.setForeground(Color.white);
		regionalEffectsArea.setBorder(null);
		regionalEffectsArea.setFont(defaultFont);
		frame.add(regionalEffectsArea);
		JScrollPane regionalJScrollPane = new JScrollPane(regionalEffectsArea);
		regionalJScrollPane.setBounds(700, 210, 600, 70);
		regionalJScrollPane.setBackground(backgroundColor);
		regionalJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		regionalJScrollPane.setBorder(null);
		frame.add(regionalJScrollPane);
		
		JTextField spellsField = new JTextField();
		spellsField.setText("Spells");
		spellsField.setBounds(700, 300, 600, 20);
		spellsField.setEditable(false);
		spellsField.setBackground(backgroundColor);
		spellsField.setForeground(goldColor);
		spellsField.setBorder(null);
		spellsField.setFont(defaultBiggerFont);
		frame.add(spellsField);
		
		JSeparator separator10 = new JSeparator();
		separator10.setBounds(700, 330, 690, 2);
		separator10.setForeground(goldColor);
		separator10.setBorder(new LineBorder(goldColor, 2));
		frame.add(separator10);
		
		JTextField spellModifierField = new JTextField();
		if (otherStats.get(0).getSpellModifier() != null) {
			spellModifierField.setText("Spell modifier: "+otherStats.get(0).getSpellModifier());
		}else {
			spellModifierField.setText("Spell modifier: -");
		}
		spellModifierField.setBounds(700, 340, 600, 20);
		spellModifierField.setEditable(false);
		spellModifierField.setBackground(backgroundColor);
		spellModifierField.setForeground(Color.white);
		spellModifierField.setBorder(null);
		spellModifierField.setFont(defaultFont);
		frame.add(spellModifierField);
		
		JTextField spellACField = new JTextField();
		if (otherStats.get(0).getSpellSaveDC() != -1) {
			spellACField.setText("Spell Save DC: "+otherStats.get(0).getSpellSaveDC());
		}else {
			spellACField.setText("Spell Save DC: -");
		}
		spellACField.setBounds(700, 360, 600, 20);
		spellACField.setEditable(false);
		spellACField.setBackground(backgroundColor);
		spellACField.setForeground(Color.white);
		spellACField.setBorder(null);
		spellACField.setFont(defaultFont);
		frame.add(spellACField);
		
		
		JTextArea SpellsArea = new JTextArea();
		if (otherStats.get(0).getSpells() != null && otherStats.get(0).getSpells().length > 0) {
			SpellsArea.setText(UIHelper.convertStringArray(otherStats.get(0).getSpells()));
		}else {
			SpellsArea.setText("-");
		}
		
		SpellsArea.setBounds(700, 390, 600, 70);
		SpellsArea.setEditable(false);
		SpellsArea.setBackground(backgroundColor);
		SpellsArea.setForeground(Color.white);
		SpellsArea.setBorder(null);
		SpellsArea.setFont(defaultFont);
		frame.add(SpellsArea);
		JScrollPane spellJScrollPane = new JScrollPane(SpellsArea);
		spellJScrollPane.setBounds(700, 390, 600, 70);
		spellJScrollPane.setBackground(backgroundColor);
		spellJScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spellJScrollPane.setBorder(null);
		frame.add(spellJScrollPane);
		
		selectFileButton = new JButton("Select Monster txt");
		selectFileButton.setBounds(900, 600, 250, 30);
		selectFileButton.setBackground(backgroundColor);
		selectFileButton.setForeground(Color.white);
		selectFileButton.setFont(defaultFont);
		selectFileButton.addActionListener(this);
		frame.add(selectFileButton);
		
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == selectFileButton) { // Opens the selected monsterblock's txt and closes the current one
			JFileChooser fileChooser = new JFileChooser();
			int response = fileChooser.showOpenDialog(null);
			if (response == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath()); // Your every day filechooser
				System.out.println(file);
				frame.dispose();
				ReadAndStore.mainStats.clear(); // having multiple creatures loaded in is not yet implemented
				ReadAndStore.nameStats.clear();
				ReadAndStore.actions.clear();
				ReadAndStore.otherStats.clear();
				try {
					ReadAndStore.readAndStoreFile(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				MainWindow mainWindow = new MainWindow();
			}
			
		}
		
	}
}
