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

import monkeyworld.core.agent.Monkey;
import monkeyworld.core.agent.MonkeyAction;
import monkeyworld.core.agent.MonkeyPerception;

import org.oreilly.is.Environment;

/**
 * 
 * @author Deep Blue Team
 */
public class Laboratory implements Environment {

	private Monkey monkey;
	private EnvType envType;
	private EnvStatus envStatus;
	private EnvStatusModifier envModifier;
	private boolean userDefined = false;

	public Laboratory(Monkey monkey) {
		this(monkey, EnvType.STATIC, 0);
	}

	public Laboratory(Monkey monkey, EnvType envType, int time) {
		this.monkey = monkey;
		this.envType = envType;
		envModifier = new EnvStatusModifier();
		envStatus = envModifier.getStatus();
		// if the world is dynamic, creates a thread
		// that changes the bananas bunch position
		if (envType.equals(EnvType.DYNAMIC)) {
			Thread t = new ThreadBanana(this, time);
			t.start();
		} else if (envType.equals(EnvType.USER_DEFINED)) {
			userDefined = true;
		}
	}
	
	public boolean isUserDefined() {
		return userDefined;
	}

	public int getLength() {
		return envStatus.getLength();
	}

	public void setBananasBunch(int position) {
		envStatus.setBananasBunch(position);
	}

	public int getBananasBunch() {
		return envStatus.getBananasBunch();
	}

	public boolean isGrabbed() {
		return envStatus.isGrabbed();
	}

	public void setBox(int position) {
		envStatus.setBox(position);
	}

	public int getBox() {
		return envStatus.getBox();
	}

	public void setHome(int position) {
		envStatus.setHome(position);
	}

	public int getHome() {
		return envStatus.getHome();
	}

	public int getMonkey() {
		return envStatus.getMonkey();
	}

	@Override
	public boolean isDone() {
		return !monkey.isAlive();
	}

	@Override
	public void step() {
		if (isDone()) {
			return;
		}
		MonkeyPerception perception = genPerception();
		MonkeyAction action = monkey.execute(perception);
		if (action.isGoOut()) {
			envModifier.goOut();
		} else if (action.isGoHome()) {
			envModifier.goHome();
		} else if (action.isMoveLeft()) {
			envModifier.moveLeft();
		} else if (action.isMoveRight()) {
			envModifier.moveRight();
		} else if (action.isPushLeft()) {
			envModifier.pushLeft();
		} else if (action.isPushRight()) {
			envModifier.pushRight();
		} else if (action.isPullLeft()) {
			envModifier.pullLeft();
		} else if (action.isPullRight()) {
			envModifier.pullRight();
		} else if (action.isGrab()) {
			envModifier.grab();
		}
	}

	private MonkeyPerception genPerception() {
		MonkeyPerception perception = new MonkeyPerception();
		perception.setBananasBunch(envStatus.getBananasBunch());
		perception.setBox(envStatus.getBox());
		perception.setHome(envStatus.getHome());
		perception.setMonkey(envStatus.getMonkey());
		return perception;
	}

	@Override
	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
		}
	}

	@Override
	public void stepUntilDone() {
		while (!isDone()) {
			step();
		}
	}

	@Override
	public double getPerformanceMeasure() {
		return 0;
	}

}