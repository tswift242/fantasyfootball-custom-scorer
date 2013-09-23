package football.players;

import java.text.DecimalFormat;

import football.Stat;

public abstract class Player implements Comparable<Player>
{
	protected String name;
	protected double score; //calculated and stored by evaluate

	public Player(String name)
	{
		this.name = name;
		score = 0.0; //initial value
	}

	//TODO: get rid of this??
	public abstract int getNumStats();

	//evaluate player by assigning them a score
	public abstract double evaluate(double[] ... coeffs);

	public int compareTo(Player other) {
		if((this.score == 0) || (other.score == 0)) {
			System.out.println("Warning: one of these players may not have been evaluated");
		}
		return Double.compare(this.score,other.score);
	}

	public String toString() {
		DecimalFormat dfmt = new DecimalFormat(".##");
		return (name + "\t\t" + dfmt.format(score));
	}

	//TODO: get rid of this and use enum.toString()
	public abstract String statsCats();

	//helper function for evalute()
	protected <T extends Enum<T>> double dot(Stat<T>[] a, double[] b) {
		if(a.length != b.length) {
			System.out.println("Error: arrays don't have same length");
			System.exit(1);
		}

		double sum = 0.0;
		for(int i = 0; i < a.length; i++) {
			sum += (a[i].getValue()*b[i]);
		}
		return sum;
	}
}