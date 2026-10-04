import javax.swing.*;
import java.awt.*;

// defines the HealthPanel class - draws the gallows/hangman figure and a lives readout
public class HealthPanel extends JPanel {
	private int remainingLives;
	private int totalLives;
	private JLabel livesLabel;

	private static final Color LINE_COLOR = new Color(0xff, 0xd1, 0x66);
	private static final Color FIGURE_COLOR = new Color(0xef, 0x47, 0x6f);
	private static final String[] FACES = { "💀", "😱", "😨", "😟",
			"😐", "🙂", "😀", "😄" };

	// Constructor for the number of total lives
	public HealthPanel(int totalLives) {
		this.totalLives = totalLives;
		this.remainingLives = totalLives;

		// size of the HealthPanel
		setPreferredSize(new Dimension(900, 220));
		setLayout(new BorderLayout());
		setBackground(new Color(0x1b, 0x1f, 0x3b));

		livesLabel = new JLabel(livesText(), SwingConstants.CENTER);
		livesLabel.setForeground(Color.WHITE);
		livesLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		add(livesLabel, BorderLayout.SOUTH);
	}

	private String livesText() {
		int idx = Math.max(0, Math.min(FACES.length - 1, remainingLives));
		return "Lives left: " + remainingLives + "   " + FACES[idx];
	}

	// reset method
	public void reset(int totalLives) {
		this.totalLives = totalLives;
		this.remainingLives = totalLives;
		livesLabel.setText(livesText());
		repaint();
	}

	// Method to remove a life. Returns true once all lives have been lost
	public boolean removeLife() {
		if (remainingLives > 0) {
			remainingLives--;
		}
		livesLabel.setText(livesText());
		repaint();
		return remainingLives == 0;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int baseX = (getWidth() / 2) - 60;
		int groundY = 150;

		// gallows
		g2.setColor(LINE_COLOR);
		g2.setStroke(new BasicStroke(5));
		g2.drawLine(baseX - 40, groundY, baseX + 100, groundY); // ground
		g2.drawLine(baseX, groundY, baseX, 15); // pole
		g2.drawLine(baseX, 15, baseX + 70, 15); // beam
		g2.drawLine(baseX + 70, 15, baseX + 70, 40); // rope

		int mistakes = totalLives - remainingLives;
		int headSize = 32;
		int headX = baseX + 70;
		int headY = 40;

		g2.setColor(FIGURE_COLOR);
		g2.setStroke(new BasicStroke(4));

		if (mistakes >= 1) {
			g2.drawOval(headX - headSize / 2, headY, headSize, headSize);
		}
		if (mistakes >= 2) {
			g2.drawLine(headX, headY + headSize, headX, headY + headSize + 55);
		}
		if (mistakes >= 3) {
			g2.drawLine(headX, headY + headSize + 15, headX - 28, headY + headSize + 35);
		}
		if (mistakes >= 4) {
			g2.drawLine(headX, headY + headSize + 15, headX + 28, headY + headSize + 35);
		}
		if (mistakes >= 5) {
			g2.drawLine(headX, headY + headSize + 55, headX - 22, headY + headSize + 95);
		}
		if (mistakes >= 6) {
			g2.drawLine(headX, headY + headSize + 55, headX + 22, headY + headSize + 95);
		}

		// face
		if (mistakes >= 1) {
			g2.setStroke(new BasicStroke(2));
			int eyeY = headY + headSize / 3;
			boolean dead = mistakes >= totalLives;
			if (dead) {
				g2.drawLine(headX - 10, eyeY - 3, headX - 4, eyeY + 3);
				g2.drawLine(headX - 10, eyeY + 3, headX - 4, eyeY - 3);
				g2.drawLine(headX + 4, eyeY - 3, headX + 10, eyeY + 3);
				g2.drawLine(headX + 4, eyeY + 3, headX + 10, eyeY - 3);
				g2.drawArc(headX - 7, eyeY + 8, 14, 8, 0, 180);
			} else {
				g2.fillOval(headX - 9, eyeY, 3, 3);
				g2.fillOval(headX + 6, eyeY, 3, 3);
				g2.drawArc(headX - 7, eyeY + 4, 14, 8, 0, -180);
			}
		}
	}
}
