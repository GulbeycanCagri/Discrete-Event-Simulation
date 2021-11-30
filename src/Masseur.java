

public class Masseur {
	private int ID;
	private Player currentPlayer;
	
	public Masseur (int ID) {
		this.ID = ID;
	}
	public void giveMassage(Player player, double currentTime) {
		currentPlayer = player;
		currentPlayer.setWaitedForMassage(currentPlayer.getWaitedForMassage()+currentTime-currentPlayer.getStartQueueTime());
		
		//currentPlayer.setMassageTaken(currentPlayer.getMassageTaken() + 1);
		
	}
	public void end(){
		currentPlayer.setCancelled(false);
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

}
