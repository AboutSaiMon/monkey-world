/**
 * Monkey World is an environment where a monkey agent can stole a bunch of bananas and go home.
 * Copyright (C) 2011 Deep Blue Team <see the team details file>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package spa.simone.monkeyworld.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Deep Blue Team
 */
public class PrincipalPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int choice;
	private JFrame frame;

	public PrincipalPanel(JFrame f) {
		super();
		this.frame = f;
		createPanel();
	}

	private void createPanel() {
		this.setLayout(new GridLayout(2, 1));

		final JComboBox mode = new JComboBox();
		mode.addItem("Static Environment");
		mode.addItem("Semi Dynamic Environment");
		mode.addItem("Dynamic Environment");

		mode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choice = mode.getSelectedIndex();
			}
		});

		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				
				JFrame frame = new JFrame();
				frame.setResizable(false);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.setLocation(0, 0);
				frame.setTitle("Monkey World");

				frame.setContentPane(new FinalPanel(frame, choice));
			}
		});

		this.add(mode);
		this.add(start);
	}
}