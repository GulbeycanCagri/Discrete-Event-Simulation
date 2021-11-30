import java.util.Comparator;

public class PhysiotherapistComparator implements Comparator<Physiotherapist> {

	@Override
	public int compare(Physiotherapist p1, Physiotherapist p2) {
	return p1.getID()-p2.getID();
}
}