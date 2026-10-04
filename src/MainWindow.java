import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//Extend JFrame
public class MainWindow extends JFrame {
	private static final Color BACKGROUND = new Color(0x1b, 0x1f, 0x3b);
	private static final Color ACCENT = new Color(0xff, 0xd1, 0x66);

	// container for GUI components
	Container c;
	HealthPanel healthPanel;
	WordPanel wordPanel;
	ButtonPanel buttonPanel;

	// constaractor
	public MainWindow() {

		c = this.getContentPane();
		// background color of the container
		c.setBackground(BACKGROUND);
		// default close operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// fun title banner
		JLabel titleLabel = new JLabel("🎩 HANGMAN 🎩", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		titleLabel.setForeground(ACCENT);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));

		// Create a new HealthPanel (draws the gallows/hangman figure)
		healthPanel = new HealthPanel(7);

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(BACKGROUND);
		topPanel.add(titleLabel, BorderLayout.NORTH);
		topPanel.add(healthPanel, BorderLayout.CENTER);
		// add to the north
		c.add(topPanel, BorderLayout.NORTH);

		ArrayList<String> words = new ArrayList<>();

		// read dictionary file and add words to array
		File dictionary = new File("wordsA.txt");

		try {
			// print the path of the dictionary file
			System.out.println("Read from file in: " + dictionary.getCanonicalPath());
			// create a new Scanner to read the dictionary file
			Scanner textScanner = new Scanner(dictionary);
			// Loop
			while (textScanner.hasNext()) {
				words.add(textScanner.nextLine());
			}
			textScanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Get a random word from the list
		String randomWord = WordPanel.getRandomWord(words);
		System.out.println("Random Word: " + randomWord);

		// Create a new WordPanel with the random word
		// add to the south
		wordPanel = new WordPanel(randomWord);
		c.add(wordPanel, BorderLayout.SOUTH);
		// create a new ButtonPanel
		buttonPanel = new ButtonPanel(wordPanel, healthPanel, words);
		// add to the center
		c.add(buttonPanel, BorderLayout.CENTER);

		// let the player type letters on the keyboard instead of only clicking
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = Character.toUpperCase(e.getKeyChar());
				if (ch >= 'A' && ch <= 'Z') {
					buttonPanel.pressLetter(ch);
				}
			}
		});
	}

}
