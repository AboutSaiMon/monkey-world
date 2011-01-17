package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class FinalPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private int choice;
	private BottomPanel bottomPanel;

	public FinalPanel(JFrame f, int choice) {
		this.frame = f;
		this.choice = choice;
		f.setSize(800, 800);
		createPanel();
	}

	private void createPanel() {
		this.setBackground(Color.WHITE);

		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.CYAN);

		JLabel chooseTimeLabel = new JLabel("Time Banana Move: ");
		JTextField chooseTime = new JTextField("1");
		chooseTime.setPreferredSize(new Dimension(50, 20));
		chooseTime.setToolTipText("Value: Min( 1 ), Max ( 50 )");
		if (choice == 1) {
			topPanel.add(chooseTimeLabel);
			topPanel.add(chooseTime);
		}
		final JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				bottomPanel.setEditable(false);
				start.setEnabled(false);
			}
		});
		topPanel.add(start);

		bottomPanel = new BottomPanel(choice);
		bottomPanel.repaint();

		JSplitPane panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
		panel.setPreferredSize(new Dimension(800, 800));

		panel.setDividerLocation(70);
		panel.setDividerSize(2);
		panel.setEnabled(false);

		this.add(panel);
	}
}
