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
package monkeyworld.core;

import monkeyworld.core.agent.MonkeyActionTest;
import monkeyworld.core.agent.MonkeyTest;
import monkeyworld.core.environment.EnvStatusModifierTest;
import monkeyworld.core.environment.EnvStatusTest;
import monkeyworld.core.environment.LaboratoryTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * 
 * @author Deep Blue Team
 */
@RunWith(Suite.class)
@SuiteClasses({ PlannerTest.class, MonkeyActionTest.class, MonkeyTest.class,
	EnvStatusModifierTest.class, EnvStatusTest.class, LaboratoryTest.class })
public class TestSuite {

}
