import java.util.Comparator;
/**
 * For Massage Queue
 */
public class SkillComparator implements Comparator<Player>{

	@Override
	public int compare(Player p1, Player p2) {
		if(p1.getSkill() == p2.getSkill()) {
			if(Math.abs(p1.getStartQueueTime()-p2.getStartQueueTime())<0.00000000001) {
				return p1.getID()-p2.getID();
			}
			else if(p1.getStartQueueTime()-p2.getStartQueueTime()<0) {
				return -1;
			}
			else {
				return 1;
			}
		}
		else {
			return p2.getSkill()- p1.getSkill();
		}

}
}