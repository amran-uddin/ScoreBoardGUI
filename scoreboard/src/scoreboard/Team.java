package scoreboard;

public class Team {
	int score;
	int timeouts;
	int foul;
	String name;
	
	Team()
	{
		this.foul = 0;
		this.name = "";
		this.timeouts = 0;
		this.score = 0;
	}
	
	Team(String name, int timeouts)
	{
		this.foul = 0;
		this.name = name;
		this.timeouts = timeouts;
		this.score = 0;
	}
	
	void onePoint()
	{
		this.score++;
	}
	
	void twoPoint()
	{
		this.score = this.score + 2;
	}
	
	void threePoint()
	{
		this.score = this.score + 3;
	}
	
	void minusPoint()
	{
		if (this.score != 0)
		{
			this.score--;
		}
	}
	
	String getName()
	{
		return this.name;
	}
	
	int getPoints()
	{
		return this.score;
	}
	
	int getTimeouts()
	{
		return this.timeouts;
	}
	
	void addTimeout()
	{
		this.timeouts++;
	}
	
	void minusTimeout()
	{
		if (this.timeouts > 0)
		{
			this.timeouts--;
		}
	}
}
