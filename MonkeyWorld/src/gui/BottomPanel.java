package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JPanel;

import core.Support;

public class BottomPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Image monkey;
	private Image box;
	private Image bananas;
	private Support s;

	private int size = 60;
	private int topPosition = 200;
	private int bottomPosition = 350;
	private boolean edit;
	private int choice;

	public BottomPanel(int c) {
		this.choice = c;
		this.s = new Support();
		setEditable(true);

		this.setBackground(Color.WHITE);

		Toolkit t = Toolkit.getDefaultToolkit();
		monkey = t.getImage("src" + File.separator + "resources" + File.separator + "scimmia.jpg");
		box = t.getImage("src" + File.separator + "resources" + File.separator + "box.png");
		bananas = t.getImage("src" + File.separator + "resources" + File.separator + "bananas.jpg");
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(monkey, 0);
		mt.addImage(box, 1);
		mt.addImage(bananas, 2);
		try {
			mt.waitForID(0);
			mt.waitForID(1);
			mt.waitForID(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int i = (x - 100) / 60;
				if (i >= 0 && i < 10) {
					if (y > 200 && y < 260) {
						if (isEditable() || choice == 2)
							s.setBananasPosition(i);
					} else if (y > 350 && y < 410) {
						if (isEditable())
							s.setBoxPosition(i);
					}
					repaint();
				}
			}
		});
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphics.drawImage(bananas, s.getBananasPosition() * size + 105, topPosition + 5, null);
		// arg0.drawImage( box, 0, 100, null );
		for (int i = 0; i < 10; i++) {
			graphics.drawRect(i * size + 100, topPosition, size, size);
			if (i == s.getBoxPosition()) {
				graphics.fillRect(i * size + 100, bottomPosition, size, size);
			} else {
				graphics.drawRect(i * size + 100, bottomPosition, size, size);
			}
		}
		if (s.getMonkeyPosition() == s.getBoxPosition()) {
			graphics.drawImage(monkey, s.getMonkeyPosition() * size + 100, (bottomPosition + topPosition) / 2, null);
		} else {
			graphics.drawImage(monkey, s.getMonkeyPosition() * size + 102, bottomPosition + 10, null);
		}
	}

	public void setEditable(boolean edit) {
		this.edit = edit;
	}

	public boolean isEditable() {
		return edit;
	}
}