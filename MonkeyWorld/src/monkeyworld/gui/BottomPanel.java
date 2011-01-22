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
package monkeyworld.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JPanel;

import monkeyworld.core.agent.Monkey;
import monkeyworld.core.environment.Laboratory;


/**
 *
 * @author Deep Blue Team
 */
public class BottomPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Image monkey;
	private Image box;
	private Image bananas;
	private Laboratory lab;

	private int size = 60;
	private int topPosition = 200;
	private int bottomPosition = 350;
	private boolean edit;
	private int choice;

	public BottomPanel(int c) {
		this.choice = c;
		lab = new Laboratory(new Monkey());
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
			public void mouseClicked(MouseEvent e) 
			{
				int x = e.getX();
				int y = e.getY();
				int i = (x - 100) / 60;
				if( i >= 0 && i < 10 ) 
				{
					if( y > 200 && y < 260 ) 
					{
						if (isEditable() || choice == 2)
							lab.setBananasBunch(i);
					} 
					else if( y > 350 && y < 410 ) 
					{
						if (isEditable())
							lab.setBox(i);
					}
					else if( y > 410 && y < 470 )
					{
						if( isEditable() )
							lab.setHome(i);						
					}
					repaint();
				}
			}
		});
	}

	@Override
	public void paintComponent( Graphics graphics ) {
		super.paintComponent( graphics );
		graphics.drawImage( bananas, lab.getBananasBunch() * size + 105, topPosition + 5, null );
		// arg0.drawImage( box, 0, 100, null );
		for( int i = 0; i < 10; i++ ) 
		{
			graphics.drawRect( i * size + 100, topPosition, size, size );
			if( i == lab.getBox() ) 
			{
				graphics.fillRect( i * size + 100, bottomPosition, size, size );
			}
			else 
			{
				graphics.drawRect( i * size + 100, bottomPosition, size, size );
			}
			if( i == (lab.getHome()-1)% 10 )
			{
				graphics.drawRect( i * size + 100, bottomPosition + 60, size, size );				
			}
		}
		if ( lab.getMonkey() == lab.getBox() ) 
		{
			graphics.drawImage( monkey, lab.getMonkey() * size + 100, ( bottomPosition + topPosition ) / 2, null );
		} 
		else 
		{
			if( lab.getMonkey() >= 10 )
			{				
				graphics.drawImage(monkey, (lab.getMonkey()-1)%10 * size + 104, bottomPosition + 70, null);
			}
			else
			{			
				graphics.drawImage(monkey, lab.getMonkey() * size + 102, bottomPosition + 10, null);
			}
		}
	}

	public void setEditable(boolean edit) {
		this.edit = edit;
	}

	public boolean isEditable() {
		return edit;
	}
}