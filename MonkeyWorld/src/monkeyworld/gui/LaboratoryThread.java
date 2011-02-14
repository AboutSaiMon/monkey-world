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

import java.util.Random;

import monkeyworld.core.environment.Laboratory;

/**
 * 
 * @author Deep Blue Team
 */
public class LaboratoryThread extends Thread {

	private Laboratory lab;
	private BottomPanel bottomPanel;

	public LaboratoryThread( Laboratory lab, BottomPanel bottomPanel ) 
	{
		this.lab = lab;
		this.bottomPanel = bottomPanel;
	}

	@Override
	public void run() 
	{
		int stepCount = 0;
		while ( !lab.isDone() ) 
		{
			lab.step();
			lab.penalizeWity(Penalty.WEIGHT);
			bottomPanel.repaint();
			try 
			{				
				sleep( 600 );
				if( lab.isDynamic() )
				{					
					if( stepCount == lab.getIntervalTime() )
					{
						Random r = new Random();
						int oldPosition = lab.getBananasBunch();
						int newPosition = 0;
						do
						{													
							newPosition = r.nextInt( 10 );
						}while( newPosition == oldPosition );
						
						lab.setBananasBunch( newPosition );
						stepCount = 0;
					}
					else
					{
						stepCount++;
					}
				}
			} catch ( InterruptedException e ) 
			{
				e.printStackTrace();
			}
		}
	}
}
