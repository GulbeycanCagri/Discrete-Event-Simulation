import java.util.Comparator;

public class MasseurComparator implements Comparator<Masseur>{

	@Override
	public int compare(Masseur m1, Masseur m2) {
		return m1.getID()-m2.getID();
}
}