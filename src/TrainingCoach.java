

public class TrainingCoach {
	private int ID;
	private Player currentPlayer;
	public TrainingCoach(int ID) {
		this.setID(ID);
	}

	public void train(Player player) {
		currentPlayer = player;
		currentPlayer.setLastTrainingTime(currentPlayer.getDuration());
		
		
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
