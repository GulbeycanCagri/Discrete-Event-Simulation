
public class Player {
	private int ID;
	private int skillRating;
	private int massageTaken=0;
	private double waitedForMassage = 0;
	private double waitedForPhysiotherapy =0;
	private double duration;
	private boolean isCancelled = false;;
	private double lastTrainingTime =0;
	private double startQueueTime = 0;
	


	
	public Player (int ID, int skillRating) {
		this.ID = ID;
		this.skillRating = skillRating; 
	}
	public void train() {
		
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return the skill
	 */
	public int getSkill() {
		return skillRating;
	}

	/**
	 * @param skill the skill to set
	 */
	public void setSkill(int skill) {
		this.skillRating = skill;
	}
	/**
	 * @return the durationOfCurentTraining
	 */
	public double getDuration() {
		return duration;
	}
	/**
	 * @param durationOfCurentTraining the durationOfCurentTraining to set
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}
	/**
	 * @return the isCancelled
	 */
	public boolean isCancelled() {
		return isCancelled;
	}
	/**
	 * @param isCancelled the isCancelled to set
	 */
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	/**
	 * @return the massageTaken
	 */
	public int getMassageTaken() {
		return massageTaken;
	}
	/**
	 * @param massageTaken the massageTaken to set
	 */
	public void setMassageTaken(int massageTaken) {
		this.massageTaken = massageTaken;
	}
	/**
	 * @return the waitedForPhysiotherapy
	 */
	public double getWaitedForPhysiotherapy() {
		return waitedForPhysiotherapy;
	}
	/**
	 * @param waitedForPhysiotherapy the waitedForPhysiotherapy to set
	 */
	public void setWaitedForPhysiotherapy(double waitedForPhysiotherapy) {
		this.waitedForPhysiotherapy = waitedForPhysiotherapy;
	}
	/**
	 * @return the waitedForMassage
	 */
	public double getWaitedForMassage() {
		return waitedForMassage;
	}
	/**
	 * @param waitedForMassage the waitedForMassage to set
	 */
	public void setWaitedForMassage(double waitedForMassage) {
		this.waitedForMassage = waitedForMassage;
	}
	
	/**
	 * @return the lastTrainingTime
	 */
	public double getLastTrainingTime() {
		return lastTrainingTime;
	}
	/**
	 * @param lastTrainingTime the lastTrainingTime to set
	 */
	public void setLastTrainingTime(double lastTrainingTime) {
		this.lastTrainingTime = lastTrainingTime;
	}
	/**
	 * @return the startQueueTime
	 */
	public double getStartQueueTime() {
		return startQueueTime;
	}
	/**
	 * @param startQueueTime the startQueueTime to set
	 */
	public void setStartQueueTime(double startQueueTime) {
		this.startQueueTime = startQueueTime;
	}

}
