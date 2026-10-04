import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

public class ButtonPanel extends JPanel implements ActionListener {
	private JButton[] letterButtons;
	private WordPanel wordPanel;
	private HealthPanel healthPanel;
	private ArrayList<String> words;

	private static final Color BG = new Color(0x1b, 0x1f, 0x3b);
	private static final Color BUTTON_BG = new Color(0x2b, 0x2f, 0x56);
	private static final Color BUTTON_HOVER = new Color(0x3d, 0x43, 0x73);
	private static final Color CORRECT = new Color(0x06, 0xd6, 0xa0);
	private static final Color WRONG = new Color(0xef, 0x47, 0x6f);

	// constractor
	public ButtonPanel(WordPanel wordPanel, HealthPanel healthPanel, ArrayList<String> words) {
		// Set the preferred size
		setPreferredSize(new Dimension(900, 180));
		// Set the list of words for the game
		this.words = words;
		// Set the WordPanel and HealthPanel objects
		this.wordPanel = wordPanel;
		this.healthPanel = healthPanel;
		// Initialize the button array
		letterButtons = new JButton[26];
		setLayout(new GridLayout(2, 13, 6, 6));
		setBackground(BG);
		setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

		// new font
		Font myFont = new Font("SansSerif", Font.BOLD, 22);
		// Loop through letters A-Z
		for (char c = 'A'; c <= 'Z'; c++) {
			// Create a new JButton for the current letter
			JButton button = new JButton(Character.toString(c));
			button.setBackground(BUTTON_BG);
			button.setForeground(Color.WHITE);
			button.setFocusPainted(false);
			button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			// add ActionListener
			button.addActionListener(this);
			button.setFont(myFont);
			// friendly hover highlight
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					if (button.isEnabled()) {
						button.setBackground(BUTTON_HOVER);
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (button.isEnabled()) {
						button.setBackground(BUTTON_BG);
					}
				}
			});
			// Get the current index of the button
			int currentIndex = c - 'A';
			// add the button to thearray
			letterButtons[currentIndex] = button;
			// button to the panel
			add(button);
		}
	}

	// allows the keyboard to drive the same guess logic as clicking a button
	public void pressLetter(char letter) {
		int idx = letter - 'A';
		if (idx < 0 || idx >= letterButtons.length) {
			return;
		}
		JButton button = letterButtons[idx];
		if (button.isEnabled()) {
			button.doClick();
		}
	}

	// reset method for the buttons
	public void reset() {
		for (JButton button : letterButtons) {
			button.setEnabled(true);
			button.setBackground(BUTTON_BG);
		}
	}

	@Override
	// ActionListener method for button functions
	public void actionPerformed(ActionEvent e) {
		// get the button that was clicked
		JButton button = (JButton) e.getSource();
		// get the letter of cliked button
		String letter = button.getText();
		// call the guess method of the WordPanel object
		boolean isCorrect = wordPanel.guess(letter);

		if (isCorrect) {
			button.setBackground(CORRECT);
			button.setEnabled(false);

			// Check for a win
			if (wordPanel.isWordGuessed1()) {
				System.out.println("You win!");
				JOptionPane.showMessageDialog(null,
						"🎉 Woohoo! You saved the day and guessed \"" + wordPanel.hiddenWord + "\"! 🎉",
						"You Win!", JOptionPane.PLAIN_MESSAGE);
				offerReplay();
			}
		} else {
			button.setBackground(WRONG);
			button.setEnabled(false);

			// Check for a lose
			if (healthPanel.removeLife()) {
				System.out.println("You lose! The word was " + wordPanel.hiddenWord);
				JOptionPane.showMessageDialog(null,
						"💀 Oh no, the poor fella didn't make it! The word was \"" + wordPanel.hiddenWord
								+ "\". Better luck next time!",
						"Game Over", JOptionPane.PLAIN_MESSAGE);
				offerReplay();
			}
		}
	}

	// asks the player whether to start a new round or quit
	private void offerReplay() {
		String[] options = { "Yes, let's go! 🙌", "No, I'm done 👋" };
		int selection = JOptionPane.showOptionDialog(null, "Play again?", "One more round?", 0,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (selection == 0) {
			wordPanel.reset(WordPanel.getRandomWord(words));
			healthPanel.reset(7);
			reset();
		} else {
			System.exit(0);
		}
	}
}
