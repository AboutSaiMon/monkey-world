/**
 * Monkey World is an environment where a monkey agent can stole a bunch of bananas and go home.
 * Copyright (C) 2011 Deep Blue Team <see the team details file>
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package spa.simone.monkeyworld.core.agent;

import org.junit.Before;
import org.junit.Test;
import spa.simone.monkeyworld.core.environment.EnvStatus;

import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Deep Blue Team
 */
public class MonkeyTest {

    private Monkey monkey;
    private EnvStatus status;
    private LinkedList<MonkeyAction> plan;

    @Before
    public void setUp() {
        monkey = new Monkey();
        status = new EnvStatus();
        status.setHome(1);
        status.setBox(4);
        status.setBananasBunch(4);
        plan = new LinkedList<MonkeyAction>();
        plan.add(new MonkeyAction(ActionType.GO_OUT));
        plan.add(new MonkeyAction(ActionType.GO_RIGHT));
        plan.add(new MonkeyAction(ActionType.GO_RIGHT));
        plan.add(new MonkeyAction(ActionType.GO_RIGHT));
        plan.add(new MonkeyAction(ActionType.CLIMB));
        plan.add(new MonkeyAction(ActionType.GRAB));
        plan.add(new MonkeyAction(ActionType.DESCEND));
        plan.add(new MonkeyAction(ActionType.GO_LEFT));
        plan.add(new MonkeyAction(ActionType.GO_LEFT));
        plan.add(new MonkeyAction(ActionType.GO_LEFT));
        plan.add(new MonkeyAction(ActionType.GO_HOME));
    }

    @Test
    public void test() {
        MonkeyPerception percept = new MonkeyPerception(status);
        MonkeyAction action = monkey.execute(percept);
        while (monkey.isAlive()) {
            assertThat(action, equalTo(plan.remove()));
            action = monkey.execute(percept);
        }
        assertThat(monkey.isAlive(), equalTo(false));
    }
}