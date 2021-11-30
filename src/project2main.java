
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class project2main {


	public static void main (String args[]) throws FileNotFoundException {
		Scanner input = new Scanner(new File(args[0])).useLocale(Locale.US);
		PrintStream output = new PrintStream(new File(args[1]));
		int physiotherapistNumber;
		int coachNumber;
		int masseurNumber;
		int invalidAttemps =0;
		double currentTime=0;
		/*
		 * max values are for storing the maximum value of waiting in order to take the service. 
		 */
		int maxPhysiotherapy =0;
		int maxTraining =0;
		int maxMassage =0;
		int numOfMassage =0;
		int numOfTraining =0;
		int numOfPhysiotherapy =0;
		double totalTraining =0;
		double totalMassage =0;
		double totalPhysiotherapy=0;
		double minWaitingMassage =1999999999;
		double maxWaitingPhysioTherapy=0;
		/*
		 * waiting... variables are for storing the time passed while waiting for the service
		 */
		double waitingForMassage=0;
		double waitingForTraining=0;
		double waitingForPhysiotherapy=0;
		int smallestID = 0;
		int cancelledAttempts =0;
		//  For Temporary Usage
		TrainingCoach coach;
		Masseur masseur;
		Physiotherapist physiotherapist;
		Player player;
		// I use Queue instead of Priortiy Queue because I will sort events afterwards. 
		Queue<Player> trainingQueue = new LinkedList<Player>();
		PriorityQueue<Player> physiotherapyQueue = new PriorityQueue<Player>(new TrainingTimeComparator());
		PriorityQueue<Player> massageQueue = new PriorityQueue<Player>(new SkillComparator());
		/**
		 * PriorityQueue of events
		 */
		PriorityQueue<String[]> times = new PriorityQueue<String[]>(new TimeComparator());

		PriorityQueue<TrainingCoach> availableCoaches = new PriorityQueue<TrainingCoach>(new CoachComparator());
		PriorityQueue<Masseur> availableMasseurs = new PriorityQueue<Masseur>(new MasseurComparator());
		PriorityQueue<Physiotherapist> availablePhysiotherapists = new PriorityQueue<Physiotherapist>(new PhysiotherapistComparator());

		HashMap<Integer, Player> players = new HashMap<Integer,Player>();
		HashMap<Integer, TrainingCoach> coaches = new HashMap<Integer,TrainingCoach>();
		HashMap<Integer, Masseur> masseurs = new HashMap<Integer,Masseur>();
		HashMap<Integer, Physiotherapist> physiotherapists = new HashMap<Integer,Physiotherapist>();
		// For statistics
		Player mostWaitingForPhysio = null;
		Player leastWaitingForMassage = null;



		// getting the data
		int n = input.nextInt();

		for(int i=0;i<n;i++) {
			player = new Player(input.nextInt(), input.nextInt());
			players.put(player.getID(),player);
		}

		int arrivals = input.nextInt();
		input.nextLine();
		for (int i=0;i<arrivals;i++) {
			String[] info = input.nextLine().split(" ",4);
			times.add(info);	
		}

		physiotherapistNumber = input.nextInt();
		for(int i = 0;i<physiotherapistNumber;i++) {
			physiotherapist = new Physiotherapist(i, input.nextDouble());
			physiotherapists.put(i,physiotherapist);
			availablePhysiotherapists.add(physiotherapist);
		}

		coachNumber = input.nextInt();
		for(int i =0;i<coachNumber;i++) {
			coach = new TrainingCoach(i);
			coaches.put(i,coach);
			availableCoaches.add(coach);
		}

		masseurNumber = input.nextInt();
		for (int i =0;i<masseurNumber;i++) {
			masseur = new Masseur(i);
			masseurs.put(i,masseur);
			availableMasseurs.add(masseur);

		}
		// Events
		while(!times.isEmpty()) {

			String[] s = times.poll();

			currentTime = Double.valueOf(s[2]);

			if(s[0].equals("t")) {
				player = players.get(Integer.valueOf(s[1]));
				if(player.isCancelled()) {
					cancelledAttempts++;
					continue;
				}
				else {
					numOfTraining++;
					player.setDuration(Double.valueOf(s[3]));
					player.setCancelled(true);
					player.setStartQueueTime(currentTime);
					trainingQueue.add(player);
					if (!availableCoaches.isEmpty()) {
						coach = availableCoaches.poll();
						player = trainingQueue.poll();
						waitingForTraining+= currentTime-player.getStartQueueTime();
						coach.train(player);
						String[] str = new String[5];
						str[0] = "e";
						str[1] = String.valueOf(player.getID());
						str[2] = String.valueOf(currentTime+player.getDuration());
						str[3] = "t";
						str[4] = String.valueOf(coach.getID());
						times.add(str);
					}
				}
			}
			// Starting
			else if (s[0].equals("m")){

				player = players.get(Integer.valueOf(s[1]));
				if (player.getMassageTaken()==3) {
					invalidAttemps++;
					continue;
				}
				else {
					if(player.isCancelled()) {
						cancelledAttempts++;
						continue;
					}
					else {
						player.setDuration(Double.valueOf(s[3]));
						totalMassage +=  player.getDuration();
						player.setCancelled(true);
						player.setMassageTaken(player.getMassageTaken()+1);
						numOfMassage++;
						player.setStartQueueTime(currentTime);
						massageQueue.add(player);
						if(!availableMasseurs.isEmpty()) {
							player = massageQueue.poll();
							masseur = availableMasseurs.poll();
							masseur.giveMassage(player,currentTime);
							String[] str = new String[5];
							str[0] = "e";
							str[1] = String.valueOf(masseur.getID());
							str[2] = String.valueOf(currentTime+player.getDuration());
							str[3] = "m";
							str[4] = String.valueOf(masseur.getID());
							times.add(str);
						}
					}
				}

			}


			// Ending
			else {

				if(s[3].equals("t")) {

					availableCoaches.add(coaches.get(Integer.valueOf(s[4])));
					String[] st = new String[5];
					player = players.get(Integer.valueOf(s[1]));
					totalTraining += player.getDuration();
					numOfPhysiotherapy++;
					player.setStartQueueTime(currentTime);
					physiotherapyQueue.add(player);
					if(!availablePhysiotherapists.isEmpty()) {
						physiotherapist = availablePhysiotherapists.poll();
						player = physiotherapyQueue.poll();
						physiotherapist.providePhysiotherapy(player,currentTime);
						st[0] = "e";
						st[1] = String.valueOf(physiotherapist.getID());
						st[2] = String.valueOf(currentTime+physiotherapist.getServiceTime());
						st[3] = "p";
						st[4] = String.valueOf(physiotherapist.getID());
						times.add(st);
					}

					if(!trainingQueue.isEmpty()) {
						String[] str = new String[5] ;
						coach = availableCoaches.poll();
						player = trainingQueue.poll();
						waitingForTraining+= currentTime-player.getStartQueueTime();
						coach.train(player);
						str[0] = "e";
						str[1] = String.valueOf(player.getID());
						str[2] = String.valueOf(currentTime+player.getDuration());
						str[3] = "t";
						str[4] = String.valueOf(coach.getID());
						times.add(str);


					}

				}
				else if(s[3].equals("m")) {
					availableMasseurs.add(masseurs.get(Integer.valueOf(s[4])));
					masseurs.get(Integer.valueOf(s[4])).end();
					if(!massageQueue.isEmpty()){
						masseur = availableMasseurs.poll();
						player = massageQueue.poll();
						masseur.giveMassage(player,currentTime);
						String[] str = new String[5];
						str[0] = "e";
						str[1] = String.valueOf(player.getID());
						str[2] = String.valueOf(currentTime+player.getDuration());
						str[3] = "m";
						str[4] = String.valueOf(masseur.getID());
						times.add(str);
					}

				}
				else {
					String[] str = new String[5] ;
					availablePhysiotherapists.add(physiotherapists.get(Integer.valueOf(s[4])));
					physiotherapists.get(Integer.valueOf(s[4])).end();
					totalPhysiotherapy += physiotherapists.get(Integer.valueOf(s[4])).getServiceTime();
					if(!physiotherapyQueue.isEmpty()) {
						physiotherapist = availablePhysiotherapists.poll();
						player = physiotherapyQueue.poll();
						physiotherapist.providePhysiotherapy(player,currentTime);
						str[0] = "e";
						str[1] = String.valueOf(player.getID());
						str[2] = String.valueOf(currentTime+physiotherapist.getServiceTime());
						str[3] = "P";
						str[4] = String.valueOf(physiotherapist.getID());
						times.add(str);
					}

				}
			}

			// Max numbers in the queues
			if(trainingQueue.size()>maxTraining) {
				maxTraining = trainingQueue.size();
			}
			if(physiotherapyQueue.size()>maxPhysiotherapy) {
				maxPhysiotherapy = physiotherapyQueue.size();
			}
			if(massageQueue.size()>maxMassage) {
				maxMassage = massageQueue.size();
			}

		}

		// Player statistics 
		for (Player p: players.values()) {
			waitingForMassage += p.getWaitedForMassage();
			waitingForPhysiotherapy += p.getWaitedForPhysiotherapy();


			if(mostWaitingForPhysio != null) {
				if(Math.abs(maxWaitingPhysioTherapy - p.getWaitedForPhysiotherapy()) < 0.00000000001) {
					if (mostWaitingForPhysio.getID()>p.getID()) {
						maxWaitingPhysioTherapy = p.getWaitedForPhysiotherapy();
						mostWaitingForPhysio = p;
					}
				}
				else if(maxWaitingPhysioTherapy - p.getWaitedForPhysiotherapy() < 0){
					maxWaitingPhysioTherapy = p.getWaitedForPhysiotherapy();
					mostWaitingForPhysio = p;
				}
			}
			else {
				mostWaitingForPhysio = p;
				maxWaitingPhysioTherapy = p.getWaitedForPhysiotherapy();
			}
			if(p.getMassageTaken()== 3) {
				if(leastWaitingForMassage != null) {
					if(Math.abs(minWaitingMassage - p.getWaitedForMassage()) < 0.00000000001) {
						if(leastWaitingForMassage.getID()>p.getID()) {
							leastWaitingForMassage = p;
							minWaitingMassage = p.getWaitedForMassage();
						}
					}
					else if (minWaitingMassage - p.getWaitedForMassage() > 0) {
						leastWaitingForMassage = p;
						minWaitingMassage = p.getWaitedForMassage();
					}
				}
				else {
					leastWaitingForMassage = p;
					minWaitingMassage = p.getWaitedForMassage();
				}
			}

		}

		//printing
		output.println(maxTraining);
		output.println(maxPhysiotherapy);
		output.println(maxMassage);
		// dividing 0 check
		if(numOfTraining != 0) {
			output.printf("%.3f\n",(waitingForTraining/numOfTraining));
		}
		else {
			output.printf("%.3f\n",waitingForTraining);
		}
		if(numOfPhysiotherapy != 0) {
			output.printf("%.3f\n",(waitingForPhysiotherapy/numOfPhysiotherapy));
		}
		else {
			output.printf("%.3f\n",waitingForPhysiotherapy);
		}
		if(numOfMassage != 0) {
			output.printf("%.3f\n",(waitingForMassage/numOfMassage));
		}
		else {
			output.printf("%.3f\n",waitingForMassage);
		}
		// dividing 0 check
		if(numOfTraining==0 ) {
			output.printf("%.3f\n",totalTraining);
		}
		else {
			output.printf("%.3f\n", totalTraining/numOfTraining);
		}
		if(numOfPhysiotherapy==0) {
			output.printf("%.3f\n",totalPhysiotherapy);
		}
		else {
			output.printf("%.3f\n", totalPhysiotherapy/numOfPhysiotherapy);	
		}
		if(numOfMassage==0) {
			output.printf("%.3f\n",totalMassage);
		}
		else {
			output.printf("%.3f\n", totalMassage/numOfMassage);
		}
		if(numOfTraining == 0) {
			output.printf("%.3f\n",0);
		}
		else {
			output.printf("%.3f\n",(totalTraining+waitingForTraining+waitingForPhysiotherapy+totalPhysiotherapy)/numOfTraining);
		}

		if(mostWaitingForPhysio == null) {
			output.printf("%d %d\n",smallestID,0);
		}
		else {
			output.printf("%d %.3f\n",mostWaitingForPhysio.getID(), maxWaitingPhysioTherapy);
		}
		// print -1, if there is no player that took 3 massage service.
		if(leastWaitingForMassage == null) {
			output.printf("%d %d\n",-1,-1);
		}
		else {
			output.printf("%d %.3f\n",leastWaitingForMassage.getID(), minWaitingMassage);
		}

		output.println(invalidAttemps);
		output.println(cancelledAttempts);
		output.printf("%.3f",currentTime);


	}


}
