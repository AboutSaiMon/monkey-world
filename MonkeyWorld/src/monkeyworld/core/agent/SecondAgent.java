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
package monkeyworld.core.agent;


import org.oreilly.is.Agent;
import org.oreilly.is.Percept;

/**
 * Is the agent for the second type of the environment.
 * 
 * @author Deep Blue Team
 */
public class SecondAgent implements Agent
{
	// This enum needs to know in which step we are.
	private enum CURRENTSTEP { INIT, FIND_BOX, FIND_BANANA, KEEP_BANANA, DESCEND, BACK_BOX, BACK_AT_HOME, FINISH	};

	private boolean alive = true;
	
	// Current step
	private CURRENTSTEP step;
	private int homePosition;
	private int initialBoxPosition;
	private int initialBananaPosition;
	private int stepChangedNumber;
	boolean firstIteration = true;

	/**
	 * Only invoke the super constructor and set the initial step.
	 */
	public SecondAgent() 
	{
		super();
		step = CURRENTSTEP.INIT;
		stepChangedNumber = 0;
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
		
		if( step == CURRENTSTEP.INIT ) 
		{
			if( firstIteration )
			{
				initialBananaPosition = monkeyPerception.getBananasBunch();
				firstIteration = false;
			}
			if( monkeyPerception.getBananasBunch() == initialBananaPosition )
			{
				stepChangedNumber++;
			}
			else
			{				
				if( monkeyPerception.isAtHome() && step != CURRENTSTEP.FINISH ) 
				{
					a = new MonkeyAction( ActionType.GO_OUT );
					return a;
				}
				homePosition = monkeyPerception.getMonkey();
				initialBoxPosition = monkeyPerception.getBox();
				step = CURRENTSTEP.FIND_BOX;
			}
		} 
		else if( step == CURRENTSTEP.FIND_BOX ) 
		{
			return findBox( monkeyPerception.getBox(), monkeyPerception.getMonkey() );
		} 
		else if( step == CURRENTSTEP.FIND_BANANA ) 
		{
			return findBanana( monkeyPerception.getBox(), monkeyPerception.getMonkey(), monkeyPerception.getBananasBunch() );
		} 
		else if(step == CURRENTSTEP.KEEP_BANANA) 
		{
			return keepBanana( monkeyPerception.getBox(), monkeyPerception.getMonkey(), monkeyPerception.getBananasBunch() );
		} 
		else if(step == CURRENTSTEP.DESCEND )
		{
			step = CURRENTSTEP.BACK_BOX;
			return new MonkeyAction( ActionType.DESCEND );			
		}
		else if(step == CURRENTSTEP.BACK_BOX) 
		{
			return backBox( monkeyPerception.getBox(), monkeyPerception.getMonkey(), monkeyPerception.getBananasBunch(), monkeyPerception.isGrabbed() );
		} 
		else if( step == CURRENTSTEP.BACK_AT_HOME ) 
		{
			return backHome( monkeyPerception.getMonkey() );
		}
		else if( step == CURRENTSTEP.FINISH )
		{
			this.setAlive( false );
		}
		return a;
	}

	private MonkeyAction findBox( int boxPosition, int monkeyPosition ) 
	{
		MonkeyAction a = new MonkeyAction( ActionType.NO_OP );
		if( boxPosition == monkeyPosition ) 
		{
			step = CURRENTSTEP.FIND_BANANA;			
			return a;
		}
		
		if( monkeyPosition < boxPosition ) 
		{
			a = new MonkeyAction( ActionType.GO_RIGHT );
		} 
		else
		{
			a = new MonkeyAction(ActionType.GO_LEFT);
		}
		return a;
	}

	private MonkeyAction findBanana( int boxPosition, int monkeyPosition, int bananaPosition ) 
	{
		MonkeyAction a;
		if( ( stepChangedNumber - 1 ) == 1 )
		{
			a = new MonkeyAction( ActionType.CLIMB );
			step = CURRENTSTEP.KEEP_BANANA;			
			return a;
		}
		
		if( Math.abs( bananaPosition - monkeyPosition ) +2 > ( stepChangedNumber-1 ) )
		{
			a = new MonkeyAction( ActionType.NO_OP );
			return a;
		}		
		
		if( boxPosition == bananaPosition ) 
		{
			a = new MonkeyAction( ActionType.CLIMB );
			step = CURRENTSTEP.KEEP_BANANA;
		} 
		else 
		{
			if( bananaPosition < boxPosition ) 
			{
				a = new MonkeyAction( ActionType.MOVE_BOX_LEFT );
			} 
			else 
			{
				a = new MonkeyAction( ActionType.MOVE_BOX_RIGHT );
			}
		}
		return a;
	}

	private MonkeyAction keepBanana( int boxPosition, int monkeyPosition, int bananaPosition ) 
	{
		MonkeyAction a;

		if( boxPosition == monkeyPosition && monkeyPosition == bananaPosition ) 
		{
			a = new MonkeyAction( ActionType.GRAB );
			step = CURRENTSTEP.DESCEND;
		}
		else
		{
			if( ( stepChangedNumber - 1 ) > 1 )
			{
				a = new MonkeyAction( ActionType.DESCEND );
				step = CURRENTSTEP.FIND_BANANA;
			}
			else
			{
				a = new MonkeyAction( ActionType.NO_OP );
			}
		}
		return a;
	}

	private MonkeyAction backBox( int boxPosition, int monkeyPosition, int bananaPosition, boolean isGrabbed ) 
	{
		MonkeyAction a;
		
		if( !isGrabbed )
		{
			step = CURRENTSTEP.KEEP_BANANA;
		}
		else
		{					
			if( boxPosition == initialBoxPosition ) 
			{
				step = CURRENTSTEP.BACK_AT_HOME;				
			}
			else
			{
				if( boxPosition > initialBoxPosition )
				{					
					a = new MonkeyAction( ActionType.MOVE_BOX_LEFT );
					return a;
				}
				else
				{
					a = new MonkeyAction( ActionType.MOVE_BOX_RIGHT );
					return a;
				}				
			}
		}		

		a = new MonkeyAction( ActionType.NO_OP );
		return a;
	}
	
	private MonkeyAction backHome( int monkeyPosition ) 
	{
		MonkeyAction a;
		
		if( monkeyPosition != homePosition )
		{
			if( monkeyPosition > homePosition )
			{					
				a = new MonkeyAction( ActionType.GO_LEFT );
				return a;
			}
			else
			{
				a = new MonkeyAction( ActionType.GO_RIGHT );
				return a;
			}				
		}		

		step = CURRENTSTEP.FINISH;
		a = new MonkeyAction( ActionType.GO_HOME );		
		return a;
	}

	@Override
	public boolean isAlive() {
		return alive;
	}

	@Override
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
