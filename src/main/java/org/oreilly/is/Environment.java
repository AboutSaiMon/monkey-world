package org.oreilly.is;

/**
 * An abstract description of possible discrete Environments in which Agent(s)
 * can perceive and act.
 * 
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 */
public interface Environment {

	/**
	 * Move the Environment one time step forward.
	 */
	public abstract void step();

	/**
	 * Move the Environment n time steps forward.
	 * 
	 * @param n
	 *            the number of time steps to move the Environment forward.
	 */
	public abstract void step(int n);

	/**
	 * Step through time steps until the Environment has no more tasks.
	 */
	public abstract void stepUntilDone();

	/**
	 * @return true if the Environment is finished with its current task(s).
	 */
	boolean isDone();

	/**
	 * Retrieve the performance measure associated with an Agent.
	 * 
	 * @return the performance measure associated with the Agent.
	 */
	double getPerformanceMeasure();
}
