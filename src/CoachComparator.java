import java.util.Comparator;

public class CoachComparator implements Comparator<TrainingCoach> {

	@Override
	public int compare(TrainingCoach c1, TrainingCoach c2) {
	return c1.getID() - c2.getID();
	
}
}