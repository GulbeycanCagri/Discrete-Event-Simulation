import java.util.Comparator;

public class TrainingTimeComparator implements Comparator<Player>{

	@Override
	public int compare(Player p1, Player p2) {
		if(Math.abs(p1.getLastTrainingTime() - p2.getLastTrainingTime())<0.00000000001) {
			if(Math.abs(p1.getStartQueueTime() - p2.getStartQueueTime())<0.00000000001) {
				return p1.getID()-p2.getID();
			}
			else if(p1.getStartQueueTime()-p2.getStartQueueTime()>0) {
				return 1;
			}
			else {
				return -1;
			}
		}
		else if (p1.getLastTrainingTime() -p2.getLastTrainingTime()<0) {
			return 1;
		}
		else {
			return -1;
		}
	}

}
