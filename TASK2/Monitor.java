package OS_PROGASN3.TASK2;

/**
 * Class Monitor
 * To synchronize dining philosophers.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
public class Monitor
{
	/*
	 * ------------
	 * Data members
	 * ------------
	 */
	private enum PhilosopherState {TALKING, THINKING, EATING, HUNGRY};
	private int chopsticks;
	private int NoOfPhilosophers;
	private PhilosopherState [] state;


	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		// TODO: set appropriate number of chopsticks based on the # of philosophers
		System.out.println("The number of philosopher is " + piNumberOfPhilosophers);
		chopsticks = piNumberOfPhilosophers;
		NoOfPhilosophers = piNumberOfPhilosophers;
		this.state = new PhilosopherState[piNumberOfPhilosophers];
		System.out.println("The number of elements is " + state.length);
		for (int i = 0; i < piNumberOfPhilosophers; ++i){
			state[i] = PhilosopherState.THINKING;
		}
		PrintState();
	}

	public synchronized void PrintState (){
		System.out.println("-----------------");
		for (PhilosopherState each_state: this.state){
			System.out.println(each_state);
		}
		System.out.println("-----------------");
	}

	/*
	 * -------------------------------
	 * User-defined monitor procedures
	 * -------------------------------
	 */

	/**
	 * Grants request (returns) to eat when both chopsticks/forks are available.
	 * Else forces the philosopher to wait()
	 */
	public synchronized void pickUp(final int piTID)
	{	state[piTID] = PhilosopherState.HUNGRY;
		PrintState();

		if(test(piTID)) 
		{	
			// System.out.println("Before Eating");
			// PrintState();
			state[piTID] = PhilosopherState.EATING;
			// System.out.println("Started Eating");
			PrintState();
			//notifyAll();
		}
		else {
			try{
				wait();
			} catch (InterruptedException e) {
				System.err.println("Philiospher " + " has to wait");
			}
		}
	}

	public boolean test(final int piTID){
		return (
		state[(piTID + NoOfPhilosophers - 1)%NoOfPhilosophers] != PhilosopherState.EATING && 	//Check if left neighbour eating
		state[(piTID + 1)%NoOfPhilosophers] != PhilosopherState.EATING && 						//Check if right neighbour eating 
		state[piTID] == PhilosopherState.HUNGRY); 												//check if the middle philosopher is hungry 
	}

	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{
		if (state[piTID] == PhilosopherState.EATING) {
			state[piTID] = PhilosopherState.THINKING;
		}
		PrintState();
		notifyAll();
	}

	/**
	 * Only one philopher at a time is allowed to philosophy
	 * (while she is not eating).
	 */
	public synchronized void requestTalk()
	{	
		int TID = ((Philosopher)Thread.currentThread()).getTID() - 1;

		if(state[TID]!=PhilosopherState.EATING && NoOneTalking()){
		state[TID] = PhilosopherState.TALKING;
		} else {
			try{
				wait();
			} catch (InterruptedException e){
				System.err.println("A philisopher waiting to talk");
			}
		}
	}

	public synchronized boolean NoOneTalking(){
		for (PhilosopherState each_state: state){
			if(each_state == PhilosopherState.TALKING) {
				return false;
			}
		}
		return true;
	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk()
	{
		int TID = ((Philosopher)Thread.currentThread()).getTID() - 1;

		if(state[TID]!=PhilosopherState.TALKING){
		state[TID] = PhilosopherState.THINKING;
		}
		notifyAll();
	}
}

// EOF
