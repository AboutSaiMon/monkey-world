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
package monkeyworld.agents;

import org.oreilly.is.Percept;

import java.lang.Math;

import monkeyworld.core.agent.ActionType;
import monkeyworld.core.agent.Monkey;
import monkeyworld.core.agent.MonkeyAction;
import monkeyworld.core.agent.MonkeyPerception;

/**
 *
 * @author Deep Blue Team
 */
public class ThirdAgent extends Monkey
{
	
	//This enum needs to know in which step we are. 
	private enum CURRENTSTEP{ INIT, FIND_BOX, FIND_BANANA, KEEP_BANANA, BACK_BOX, BACK_AT_HOME, FINISH };
	
	//Current step
	private CURRENTSTEP step;
	private int homePosition;
	private int initialBoxPosition;
	
	
	/**
	 * Only invoke the super constructor and set the initial step.
	 */
	public ThirdAgent() 
	{
		super();
		step = CURRENTSTEP.INIT;
	}
	
	@Override
	public MonkeyAction execute( Percept percept ) 
	{
		MonkeyAction a = new MonkeyAction( ActionType.NO_OP );
		
		MonkeyPerception monkeyPerception;
		
		if( percept instanceof MonkeyPerception )
			monkeyPerception = ( MonkeyPerception ) percept;
		else
		{
			return a;
		}
		
		if( monkeyPerception.getMonkey() >= 10 )
		{
			a = new MonkeyAction( ActionType.GO_OUT );
			return a;
		}
	
		if( step == CURRENTSTEP.INIT )
		{
			homePosition = monkeyPerception.getMonkey();
			initialBoxPosition = monkeyPerception.getBox();
			step = CURRENTSTEP.FIND_BOX;			
		}		
		else if( step == CURRENTSTEP.FIND_BOX )
		{
			return findBox( monkeyPerception.getBox(), monkeyPerception.getMonkey() );
		}
		else if( step == CURRENTSTEP.FIND_BANANA )
		{
			return findBanana( monkeyPerception.getBox(), monkeyPerception.getMonkey(), monkeyPerception.getBananasBunch() );
		}
		else if( step == CURRENTSTEP.KEEP_BANANA )
		{			
			return keepBanana( monkeyPerception.getBox(), monkeyPerception.getMonkey(), monkeyPerception.getBananasBunch(), monkeyPerception.isGrabbed() );
		}
		else if( step == CURRENTSTEP.BACK_BOX )
		{
			return backBox( monkeyPerception.getBox(), monkeyPerception.getMonkey(), monkeyPerception.getBananasBunch() ); 
		}		
		else if( step == CURRENTSTEP.BACK_AT_HOME )
		{
			return backAtHome( monkeyPerception.getMonkey() );
		}
		else if( step == CURRENTSTEP.FINISH )
		{
			a = new MonkeyAction( ActionType.GO_HOME );
			this.setAlive( false );
		}
		return a;
	}
	
	private MonkeyAction findBox( int boxPosition, int monkeyPosition )
	{		
		if( Math.abs( boxPosition - monkeyPosition ) == 1 )
		{
			step = CURRENTSTEP.FIND_BANANA;
			MonkeyAction a = new MonkeyAction( ActionType.NO_OP );
			return a;
		}
		
		MonkeyAction a;
		if( boxPosition > monkeyPosition )
		{
			a = new MonkeyAction( ActionType.MOVE_RIGHT );
		}
		else
		{
			a = new MonkeyAction( ActionType.MOVE_LEFT );
		}
		return a;
	}
		
	private MonkeyAction findBanana( int boxPosition, int monkeyPosition, int bananaPosition )
	{
		MonkeyAction a;
		if( boxPosition == bananaPosition )
		{
			if( boxPosition > monkeyPosition )
			{
				a = new MonkeyAction( ActionType.MOVE_RIGHT );
			}
			else
			{
				a = new MonkeyAction( ActionType.MOVE_LEFT );
			}
			step = CURRENTSTEP.KEEP_BANANA;
			return a;
		}
		else
		{
			if( boxPosition > bananaPosition )
			{
				if( boxPosition > monkeyPosition )
				{
					a = new MonkeyAction( ActionType.PULL_LEFT );
				}
				else
				{
					a = new MonkeyAction( ActionType.PUSH_LEFT );
				}
			}
			else
			{
				if( boxPosition > monkeyPosition )
				{
					a = new MonkeyAction( ActionType.PUSH_RIGHT );
				}
				else
				{
					a = new MonkeyAction( ActionType.PULL_RIGHT );
				}
			}
		}
		
		return a;
	}
	
	private MonkeyAction keepBanana( int boxPosition, int monkeyPosition, int bananaPosition, boolean grabbed )
	{
		MonkeyAction a;
		
		if( grabbed )
		{			
			step = CURRENTSTEP.BACK_BOX;
		}
		else
		{
			step = CURRENTSTEP.FIND_BANANA;
		}
		
		if( boxPosition == monkeyPosition && monkeyPosition == bananaPosition )
		{
			a = new MonkeyAction( ActionType.GRAB );
		}
		else
		{
			if( boxPosition == monkeyPosition )
			{
				if( monkeyPosition > bananaPosition )
					a = new MonkeyAction( ActionType.MOVE_RIGHT );
				else
					a = new MonkeyAction( ActionType.MOVE_LEFT );
			}
			else
			{
				a = new MonkeyAction( ActionType.NO_OP );
			}
		}
		
		return a;
	}
	
	private MonkeyAction backBox( int boxPosition, int monkeyPosition, int bananaPosition )
	{
		MonkeyAction a;
		//TODO Insert a boolean tag to know if banana is grabbed or less.
		
		if( boxPosition == initialBoxPosition )
		{
			step = CURRENTSTEP.BACK_AT_HOME;
			a = new MonkeyAction( ActionType.NO_OP );			
			return a;
		}
		
		if( boxPosition == monkeyPosition )
		{
			if( monkeyPosition > initialBoxPosition )
			{
				a = new MonkeyAction( ActionType.MOVE_RIGHT );
			}
			else
			{
				a = new MonkeyAction( ActionType.MOVE_LEFT );
			}
			return a;
		}
		
		if( boxPosition > initialBoxPosition )
		{
			if( boxPosition > monkeyPosition )
			{
				a = new MonkeyAction( ActionType.PULL_LEFT );
			}
			else
			{
				a = new MonkeyAction( ActionType.PUSH_LEFT );
			}
		}
		else
		{
			if( boxPosition > monkeyPosition )
			{
				a = new MonkeyAction( ActionType.PUSH_RIGHT );
			}
			else
			{
				a = new MonkeyAction( ActionType.PULL_RIGHT );
			}
		}
		return a;		
	}
	
	private MonkeyAction backAtHome( int monkeyPosition )
	{
		MonkeyAction a;
		if( monkeyPosition == homePosition )
		{
			step = CURRENTSTEP.FINISH;
			a = new MonkeyAction( ActionType.NO_OP );			
			return a;
		}
		
		if( homePosition > monkeyPosition )
		{
			a = new MonkeyAction( ActionType.MOVE_RIGHT );
		}
		else
		{
			a = new MonkeyAction( ActionType.MOVE_LEFT );
		}
		return a;
	}	
}
