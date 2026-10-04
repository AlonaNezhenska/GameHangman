import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class WordPanel extends JPanel {
	// JLabel that displays the hidden word
	private JLabel wordLabel;
	// current guessed state, '_' for a letter not yet found
	private char[] displayed;
	String hiddenWord;

	private static final String CORRECT_COLOR = "#06d6a0";
	private static final String BLANK_COLOR = "#ffffff";

	// constractor
	public WordPanel(String randomWord) {
		setPreferredSize(new Dimension(900, 120));
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setBackground(new Color(0x1b, 0x1f, 0x3b));

		this.hiddenWord = randomWord;
		displayed = new char[randomWord.length()];
		Arrays.fill(displayed, '_');

		wordLabel = new JLabel();
		wordLabel.setFont(new Font("Consolas", Font.BOLD, 44));
		add(wordLabel);

		updateLabel();
	}

	// reset method for WordPanel
	public void reset(String randomWord) {
		System.out.println("Random Word: " + randomWord);
		this.hiddenWord = randomWord;
		displayed = new char[randomWord.length()];
		Arrays.fill(displayed, '_');
		updateLabel();
	}

	// tries of guessing a letter
	public boolean guess(String letter) {
		// assume - guess is incorrect
		boolean isCorrect = false;
		char c = letter.charAt(0);
		for (int i = 0; i < hiddenWord.length(); i++) {
			// If the letter the user guessed - same as the character in this place
			if (hiddenWord.charAt(i) == c) {
				displayed[i] = c;
				isCorrect = true;
			}
		}
		updateLabel();
		return isCorrect;
	}

	public boolean isWordGuessed1() {
		// comparing the current guessed state with the hidden word
		return new String(displayed).equals(hiddenWord);
	}

	// rebuilds the label with revealed letters highlighted in green
	private void updateLabel() {
		StringBuilder html = new StringBuilder("<html>");
		for (char c : displayed) {
			String color = c == '_' ? BLANK_COLOR : CORRECT_COLOR;
			html.append("<font color='").append(color).append("'>").append(c).append("</font>&nbsp;&nbsp;");
		}
		html.append("</html>");
		wordLabel.setText(html.toString());
	}

	// method to get a random word from the ArrayList
	static String getRandomWord(ArrayList<String> words) {
		Random random = new Random();
		int randomIndex = random.nextInt(words.size());
		return words.get(randomIndex);
	}

}
