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
package monkeyworld.core.environment;

/**
 *
 * @author Deep Blue Team
 */
public class EnvStatusModifier {

	private EnvStatus status;
	private int length;
	
	public EnvStatusModifier() {
		status = new EnvStatus();
		length = status.getLength();
	}
	
	public EnvStatusModifier(int length) {
		status = new EnvStatus(length);
		length = status.getLength();
	}
	
	public EnvStatus getStatus() {
		return status;
	}
	
	public void goOut() {
		if( canGoOut() ) {
			status.setMonkey(status.getMonkey() - length - 1);
		}
	}
	
	public void goHome() {
		if( canGoHome() ) {
			status.setMonkey(status.getMonkey() + length + 1);
		}
	}
	
	public void moveLeft() {
		if( canMoveLeft() ) {
			status.setMonkey(status.getMonkey() - 1);
		}
	}
	
	public void moveRight() {
		if( canMoveRight() ) {
			status.setMonkey(status.getMonkey() + 1);
		}
	}
	
	private void left() {
		status.setMonkey(status.getMonkey() - 1);
		status.setBox(status.getBox() - 1);
	}
	
	private void right() {
		status.setMonkey(status.getMonkey() + 1);
		status.setBox(status.getBox() + 1);
	}
	
	public void pushLeft() {
		if( canPushLeft() ) {
			left();
		}
	}
	
	public void pushRight() {
		if( canPushRight() ) {
			right();
		}
	}
	
	public void pullLeft() {
		if( canPullLeft() ) {
			left();
		}
	}
	
	public void pullRight() {
		if( canPullRight() ) {
			right();
		}
	}
	
	public void grab() {
		if( canGrab() ) {
			status.grabBananasBunch();
		}
	}

	/**
	 * Tells if the monkey can go out.
	 * 
	 * @return true if the monkey is at home
	 */
	public boolean canGoOut() {
		int monkey = status.getMonkey();
		int home = status.getHome();
		return monkey == home;
	}

	/**
	 * Tells if the monkey can go home.
	 * 
	 * @return true if the monkey is adjacent at the home
	 */
	public boolean canGoHome() {
		int monkey = status.getMonkey();
		int home = status.getHome();
		return monkey + length + 1 == home;
	}
	
	public boolean canMoveLeft() {
		int monkey = status.getMonkey();
		return monkey > 0 && monkey < length;
	}
	
	public boolean canMoveRight() {
		int monkey = status.getMonkey();
		return monkey >= 0 && monkey < length - 1;
	}
	
	public boolean canPushLeft() {
		int monkey = status.getMonkey();
		int box = status.getBox();
		return box == monkey - 1 && box > 0;
	}
	
	public boolean canPushRight() {
		int monkey = status.getMonkey();
		int box = status.getBox();
		return box == monkey + 1 && box < length - 1;
	}
	
	public boolean canPullLeft() {
		int monkey = status.getMonkey();
		int box = status.getBox();
		return monkey == box - 1 && monkey > 0;
	}
	
	public boolean canPullRight() {
		int monkey = status.getMonkey();
		int box = status.getBox();
		return monkey == box + 1 && monkey < length - 1;
	}
	
	public boolean canGrab() {
		int monkey = status.getMonkey();
		int box = status.getBox();
		int bananasBunch = status.getBananasBunch();
		boolean grabbed = status.isGrabbed();
		return !grabbed && monkey == box && monkey == bananasBunch;
	}
	
}