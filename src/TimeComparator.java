
import java.util.Comparator;

public class TimeComparator implements Comparator<String[]>{

	@Override
	public int compare(String[] s1, String[] s2) {
		Double time1 = Double.parseDouble(s1[2]);
		Double time2 = Double.parseDouble(s2[2]);
		int ID1 = Integer.parseInt(s1[1]);
		int ID2 = Integer.parseInt(s2[1]);
		if(Math.abs(time1-time2)<0.00000000001) {	
			return ID1-ID2;
		}

		else if(time1 - time2 > 0) {
			return 1;
		}
		else {
			return -1;
		}
	}



}
