package scoreboard;

public class Timer {
	long startTime = 0;
	long endTime = 0;
	boolean pause = true;
	long totalTimeInSeconds = 0;
	long tenthMilliseconds = 0;
	
	Timer(long time)
	{
		this.startTime = System.currentTimeMillis();
		this.endTime = this.startTime + time;
		this.pause = true;
		this.totalTimeInSeconds = time/1000;
		this.tenthMilliseconds = time/100;
	}
	
	String showElapsedTime()
	{		
		long minutes = this.totalTimeInSeconds/60;
		long seconds = this.totalTimeInSeconds%60;
		
		String minute;
		String second;
		
		if (minutes <= 9)
		{
			minute = "0" + String.valueOf(minutes);
		}
		else
		{
			minute = String.valueOf(minutes);
		}
		
		if (seconds <= 9)
		{
			second = "0" + String.valueOf(seconds);
		}
		else
		{
			second = String.valueOf(seconds);
		}
		
		if (this.tenthMilliseconds > 0 && !pause)
		{
			this.tenthMilliseconds--;
			
		}
		
		if (this.totalTimeInSeconds > 0 && !pause && this.tenthMilliseconds%10 == 0)
		{
			this.totalTimeInSeconds--;
			
		}
		
		return minute + ":" + second;
	}
	
	void play()
	{
		this.pause = false;
	}
	
	void pause()
	{
		this.pause = true;
	}
	
	boolean getStatus()
	{
		return this.pause;
	}
	
	void changeStatus()
	{
		this.pause = !this.pause;
	}
	
	long getEndTime()
	{
		return this.endTime;
	}
	
	long getCounter()
	{
		return this.totalTimeInSeconds;
	}
	
	boolean timeEnd()
	{
		if (System.currentTimeMillis() >= this.endTime)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	void addMinute()
	{
		this.totalTimeInSeconds = this.totalTimeInSeconds + 60;
		this.tenthMilliseconds = this.tenthMilliseconds + 600;
	}
	
	void addSecond()
	{
		this.totalTimeInSeconds++;
		this.tenthMilliseconds = this.tenthMilliseconds + 10;
	}
	
	void minusMinute()
	{
		if (this.totalTimeInSeconds >= 62)
		{
			this.totalTimeInSeconds = this.totalTimeInSeconds - 60;
			this.tenthMilliseconds = this.tenthMilliseconds - 60;
		}
	}
	
	void minusSecond()
	{
		if (this.totalTimeInSeconds >= 1)
		{
			this.totalTimeInSeconds--;
			this.tenthMilliseconds = this.tenthMilliseconds - 10;
		}
	}
	
	String getTime()
	{
		long minutes = this.totalTimeInSeconds/60;
		long seconds = this.totalTimeInSeconds%60;
		
		String minute;
		String second;
		
		if (minutes <= 9)
		{
			minute = "0" + String.valueOf(minutes);
		}
		else
		{
			minute = String.valueOf(minutes);
		}
		
		if (seconds <= 9)
		{
			second = "0" + String.valueOf(seconds);
		}
		else
		{
			second = String.valueOf(seconds);
		}
		
		return minute + ":" + second;
	}
	
	String getShotClockTime()
	{
		long seconds = this.totalTimeInSeconds%60;
		
		String second;
		
		if (seconds <= 9)
		{
			second = "0" + String.valueOf(seconds);
		}
		else
		{
			second = String.valueOf(seconds);
		}
		
		return second;
	}
	
	String resetShotClock()
	{
		this.totalTimeInSeconds = 24;
		this.tenthMilliseconds = this.totalTimeInSeconds * 10;
		return String.valueOf(this.totalTimeInSeconds);
	}
	
	String showShotClock()
	{
		long seconds = this.totalTimeInSeconds%60;
		
		String second;
		
		if (seconds <= 9)
		{
			second = "0" + String.valueOf(seconds);
		}
		else
		{
			second = String.valueOf(seconds);
		}
		
		if (this.tenthMilliseconds > 0 && !pause)
		{
			this.tenthMilliseconds--;
			
		}
		
		if (this.totalTimeInSeconds > 0 && !pause && this.tenthMilliseconds%10 == 0)
		{
			this.totalTimeInSeconds--;
			
		}
		
		return second;
	}
}
