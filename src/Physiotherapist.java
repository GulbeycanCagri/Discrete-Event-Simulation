

public class Physiotherapist {
	private int ID;
	private double serviceTime;
	private Player currentPlayer;
	public Physiotherapist(int ID, double serviceTime) {
		this.setID(ID);
		this.setServiceTime(serviceTime);
	}
	public void providePhysiotherapy(Player player,double currentTime) {
		
		currentPlayer = player;
		currentPlayer.setWaitedForPhysiotherapy(currentPlayer.getWaitedForPhysiotherapy()+ currentTime-currentPlayer.getStartQueueTime());
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
	 * @return the serviceTime
	 */
	public double getServiceTime() {
		return serviceTime;
	}
	/**
	 * @param serviceTime the serviceTime to set
	 */
	public void setServiceTime(double serviceTime) {
		this.serviceTime = serviceTime;
	}
	
	public void end() {
		currentPlayer.setDuration(0);
		currentPlayer.setLastTrainingTime(0);
		currentPlayer.setCancelled(false);
		currentPlayer = null;
	}
	/**
	 * @return the currentPlayer
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	/**
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
}
