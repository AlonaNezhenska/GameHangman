public class Run {
	public static void main(String[] args) {
		// Create a new MainWindow object
		MainWindow frame = new MainWindow();
		// Set the size
		frame.setSize(950, 700);
		// Set the title
		frame.setTitle("Hangman");
		// center the window on screen
		frame.setLocationRelativeTo(null);
		// make visible on the screen
		frame.setVisible(true);
		// so keyboard letter guesses work right away
		frame.requestFocusInWindow();

	}

}
