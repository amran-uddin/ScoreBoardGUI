package scoreboard;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DisplayControl {

	JFrame frame;
	Team t1;
	Team t2;
	Display d;
	int totalTime;
	int timeouts;
	Timer t;
	Timer shotClock;
	long start;
	JLabel timeLabel;
	Thread clock;
	
	public DisplayControl(Team t1, Team t2, int totalTime, int timeouts) {
		this.t1 = t1;
		this.t2 = t2;
		this.totalTime = totalTime;
		this.timeouts = timeouts;
		t = new Timer(totalTime);
		shotClock = new Timer(24000);
		initialize();
		try {
			d = new Display(t1.getName(), t2.getName(), String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			d.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(50, 50, 1500, 900);
		frame.setResizable(true);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel Team1Label = new JLabel(t1.getName());
		springLayout.putConstraint(SpringLayout.NORTH, Team1Label, 60, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, Team1Label, 100, SpringLayout.WEST, frame.getContentPane());
		Team1Label.setFont(new Font("Tahoma", Font.PLAIN, 30));
		frame.getContentPane().add(Team1Label);
		
		JLabel Team2Label = new JLabel(t2.getName());
		springLayout.putConstraint(SpringLayout.NORTH, Team2Label, 0, SpringLayout.NORTH, Team1Label);
		springLayout.putConstraint(SpringLayout.EAST, Team2Label, -110, SpringLayout.EAST, frame.getContentPane());
		Team2Label.setFont(new Font("Tahoma", Font.PLAIN, 30));
		frame.getContentPane().add(Team2Label);
		
		JLabel Team1Point = new JLabel(String.valueOf(t1.getPoints()));
		Team1Point.setFont(new Font("Tahoma", Font.PLAIN, 60));
		springLayout.putConstraint(SpringLayout.NORTH, Team1Point, 6, SpringLayout.SOUTH, Team1Label);
		springLayout.putConstraint(SpringLayout.EAST, Team1Point, 0, SpringLayout.EAST, Team1Label);
		frame.getContentPane().add(Team1Point);
		
		JLabel Team2Point = new JLabel(String.valueOf(t2.getPoints()));
		Team2Point.setFont(new Font("Tahoma", Font.PLAIN, 60));
		springLayout.putConstraint(SpringLayout.NORTH, Team2Point, 6, SpringLayout.SOUTH, Team2Label);
		springLayout.putConstraint(SpringLayout.WEST, Team2Point, 0, SpringLayout.WEST, Team2Label);
		frame.getContentPane().add(Team2Point);
		
		JButton team1ThreePoint = new JButton("+3");
		frame.getContentPane().add(team1ThreePoint);
		
		JButton team1TwoPoint = new JButton("+2");
		springLayout.putConstraint(SpringLayout.EAST, team1TwoPoint, -24, SpringLayout.WEST, Team1Point);
		springLayout.putConstraint(SpringLayout.SOUTH, team1ThreePoint, -6, SpringLayout.NORTH, team1TwoPoint);
		springLayout.putConstraint(SpringLayout.EAST, team1ThreePoint, 0, SpringLayout.EAST, team1TwoPoint);
		frame.getContentPane().add(team1TwoPoint);
		
		JButton team1OnePoint = new JButton("+1");
		springLayout.putConstraint(SpringLayout.NORTH, team1OnePoint, 165, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, team1OnePoint, -24, SpringLayout.WEST, Team1Point);
		springLayout.putConstraint(SpringLayout.SOUTH, team1TwoPoint, -6, SpringLayout.NORTH, team1OnePoint);
		frame.getContentPane().add(team1OnePoint);
		
		JButton team2ThreePoint = new JButton("+3");
		springLayout.putConstraint(SpringLayout.NORTH, team2ThreePoint, 6, SpringLayout.SOUTH, Team2Label);
		springLayout.putConstraint(SpringLayout.WEST, team2ThreePoint, 38, SpringLayout.EAST, Team2Point);
		frame.getContentPane().add(team2ThreePoint);
		
		JButton team2TwoPoint = new JButton("+2");
		springLayout.putConstraint(SpringLayout.NORTH, team2TwoPoint, 0, SpringLayout.NORTH, team1TwoPoint);
		springLayout.putConstraint(SpringLayout.WEST, team2TwoPoint, 0, SpringLayout.WEST, team2ThreePoint);
		frame.getContentPane().add(team2TwoPoint);
		
		JButton team2OnePoint = new JButton("+1");
		springLayout.putConstraint(SpringLayout.NORTH, team2OnePoint, 0, SpringLayout.NORTH, team1OnePoint);
		springLayout.putConstraint(SpringLayout.WEST, team2OnePoint, 0, SpringLayout.WEST, team2ThreePoint);
		frame.getContentPane().add(team2OnePoint);
		
		JButton team1MinusOnePoint = new JButton("-1");
		springLayout.putConstraint(SpringLayout.NORTH, team1MinusOnePoint, 165, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, team1MinusOnePoint, 6, SpringLayout.EAST, Team1Point);
		frame.getContentPane().add(team1MinusOnePoint);
		
		JButton team2MinusOnePoint = new JButton("-1");
		springLayout.putConstraint(SpringLayout.NORTH, team2MinusOnePoint, 165, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, team2MinusOnePoint, -6, SpringLayout.WEST, Team2Point);
		frame.getContentPane().add(team2MinusOnePoint);
		
		timeLabel = new JLabel(t.showElapsedTime());
		springLayout.putConstraint(SpringLayout.NORTH, timeLabel, 220, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, timeLabel, 0, SpringLayout.HORIZONTAL_CENTER, frame.getContentPane());

		timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		frame.getContentPane().add(timeLabel);
		
		JButton addMinuteBtn = new JButton("+");
		springLayout.putConstraint(SpringLayout.WEST, addMinuteBtn, 0, SpringLayout.WEST, timeLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, addMinuteBtn, -6, SpringLayout.NORTH, timeLabel);
		frame.getContentPane().add(addMinuteBtn);
		
		JButton addSecondBtn = new JButton("+");
		springLayout.putConstraint(SpringLayout.SOUTH, addSecondBtn, -6, SpringLayout.NORTH, timeLabel);
		springLayout.putConstraint(SpringLayout.EAST, addSecondBtn, 0, SpringLayout.EAST, timeLabel);
		frame.getContentPane().add(addSecondBtn);
		
		JButton minusMinuteBtn = new JButton("-");
		springLayout.putConstraint(SpringLayout.NORTH, minusMinuteBtn, 6, SpringLayout.SOUTH, timeLabel);
		springLayout.putConstraint(SpringLayout.WEST, minusMinuteBtn, 0, SpringLayout.WEST, timeLabel);
		frame.getContentPane().add(minusMinuteBtn);
		
		JButton minusSecondBtn = new JButton("-");
		springLayout.putConstraint(SpringLayout.NORTH, minusSecondBtn, 6, SpringLayout.SOUTH, timeLabel);
		springLayout.putConstraint(SpringLayout.EAST, minusSecondBtn, 0, SpringLayout.EAST, timeLabel);
		frame.getContentPane().add(minusSecondBtn);
		
		JLabel shotClockLabel = new JLabel("24");
		shotClockLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		springLayout.putConstraint(SpringLayout.NORTH, shotClockLabel, 134, SpringLayout.SOUTH, timeLabel);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, shotClockLabel, 0, SpringLayout.HORIZONTAL_CENTER, frame.getContentPane());
		frame.getContentPane().add(shotClockLabel);
		
		JButton shotClockReset = new JButton("Reset");
		springLayout.putConstraint(SpringLayout.NORTH, shotClockReset, 6, SpringLayout.SOUTH, shotClockLabel);
		springLayout.putConstraint(SpringLayout.WEST, shotClockReset, 0, SpringLayout.WEST, shotClockLabel);
		frame.getContentPane().add(shotClockReset);
		
		JButton btnGostop = new JButton("Go/Stop");
		springLayout.putConstraint(SpringLayout.NORTH, btnGostop, 250, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnGostop, 6, SpringLayout.EAST, timeLabel);
		frame.getContentPane().add(btnGostop);
		
		JLabel lblTimeoutteam = new JLabel("Timeouts");
		springLayout.putConstraint(SpringLayout.NORTH, lblTimeoutteam, 200, SpringLayout.SOUTH, team1MinusOnePoint);
		springLayout.putConstraint(SpringLayout.WEST, lblTimeoutteam, 0, SpringLayout.WEST, Team1Point);
		lblTimeoutteam.setFont(new Font("Tahoma", Font.PLAIN, 30));
		frame.getContentPane().add(lblTimeoutteam);
		
		JLabel team = new JLabel("Timeouts");
		springLayout.putConstraint(SpringLayout.NORTH, team, 0, SpringLayout.NORTH, lblTimeoutteam);
		springLayout.putConstraint(SpringLayout.EAST, team, 0, SpringLayout.EAST, Team2Point);
		team.setFont(new Font("Tahoma", Font.PLAIN, 30));
		frame.getContentPane().add(team);
		
		JLabel team1TimeoutLabel = new JLabel(String.valueOf(t1.getTimeouts()));
		springLayout.putConstraint(SpringLayout.NORTH, team1TimeoutLabel, 6, SpringLayout.SOUTH, lblTimeoutteam);
		team1TimeoutLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		springLayout.putConstraint(SpringLayout.WEST, team1TimeoutLabel, 0, SpringLayout.WEST, team1MinusOnePoint);
		frame.getContentPane().add(team1TimeoutLabel);
		
		JLabel team2TimeoutLabel = new JLabel(String.valueOf(t2.getTimeouts()));
		springLayout.putConstraint(SpringLayout.NORTH, team2TimeoutLabel, 6, SpringLayout.SOUTH, team);
		team2TimeoutLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		springLayout.putConstraint(SpringLayout.EAST, team2TimeoutLabel, 0, SpringLayout.EAST, team2MinusOnePoint);
		frame.getContentPane().add(team2TimeoutLabel);
		
		JButton team1AddTimeout = new JButton("+");
		springLayout.putConstraint(SpringLayout.NORTH, team1AddTimeout, 0, SpringLayout.NORTH, team1TimeoutLabel);
		springLayout.putConstraint(SpringLayout.EAST, team1AddTimeout, 0, SpringLayout.EAST, Team1Label);
		frame.getContentPane().add(team1AddTimeout);
		
		JButton team2AddTimeout = new JButton("+");
		springLayout.putConstraint(SpringLayout.WEST, team2AddTimeout, 0, SpringLayout.WEST, Team2Label);
		springLayout.putConstraint(SpringLayout.SOUTH, team2AddTimeout, 0, SpringLayout.SOUTH, team1AddTimeout);
		frame.getContentPane().add(team2AddTimeout);
		
		JButton team1MinusTimeout = new JButton("-");
		springLayout.putConstraint(SpringLayout.NORTH, team1MinusTimeout, 6, SpringLayout.SOUTH, team1AddTimeout);
		springLayout.putConstraint(SpringLayout.EAST, team1MinusTimeout, 0, SpringLayout.EAST, Team1Label);
		frame.getContentPane().add(team1MinusTimeout);
		
		JButton team2MinusTimeout = new JButton("-");
		springLayout.putConstraint(SpringLayout.WEST, team2MinusTimeout, 0, SpringLayout.WEST, Team2Label);
		springLayout.putConstraint(SpringLayout.SOUTH, team2MinusTimeout, 0, SpringLayout.SOUTH, team1MinusTimeout);
		frame.getContentPane().add(team2MinusTimeout);
		
		
		//////////////////// BUTTON FUNCTIONS ///////////////////
		team1ThreePoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				t1.threePoint();
				
				String currentScore = String.valueOf(t1.getPoints());
				Team1Point.setText(currentScore);
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
		
		team1TwoPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.twoPoint();
				
				String currentScore = String.valueOf(t1.getPoints());
				Team1Point.setText(currentScore);
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
		
		team1OnePoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.onePoint();
				
				String currentScore = String.valueOf(t1.getPoints());
				Team1Point.setText(currentScore);
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
		
		team2ThreePoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t2.threePoint();
				
				String currentScore = String.valueOf(t2.getPoints());
				Team2Point.setText(currentScore);
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
		
		team2TwoPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t2.twoPoint();
				
				String currentScore = String.valueOf(t2.getPoints());
				Team2Point.setText(currentScore);
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
		
		team2OnePoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t2.onePoint();
				
				String currentScore = String.valueOf(t2.getPoints());
				Team2Point.setText(currentScore);
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
		
		team1MinusOnePoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.minusPoint();
				
				String currentScore = String.valueOf(t1.getPoints());
				Team1Point.setText(currentScore);
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
		
		team2MinusOnePoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t2.minusPoint();
				
				String currentScore = String.valueOf(t2.getPoints());
				Team2Point.setText(currentScore);
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
		
		addMinuteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.addMinute();
				timeLabel.setText(t.getTime());
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
		
		addSecondBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.addSecond();
				timeLabel.setText(t.getTime());
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
		
		minusMinuteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.minusMinute();
				timeLabel.setText(t.getTime());
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
		
		minusSecondBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.minusSecond();
				timeLabel.setText(t.getTime());
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
		
		shotClockReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shotClockLabel.setText(shotClock.resetShotClock());
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
		
		btnGostop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.changeStatus();
				shotClock.changeStatus();
				if (clock == null)
				{
					clock = new Thread()
					{
						public void run()
						{	
							try {
								while (true)
								{
									if (t.getStatus())
									{ 
										String elapsed = t.showElapsedTime();
										timeLabel.setText(elapsed);
										shotClockLabel.setText(shotClock.showShotClock());
										d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
									}
									else
									{
										String elapsed = t.showElapsedTime();
										timeLabel.setText(elapsed);
										shotClockLabel.setText(shotClock.showShotClock());
										d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
									}
									sleep(100);
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					};
					
					clock.start();
				}
			}
		});
		
		team1AddTimeout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.addTimeout();
				team1TimeoutLabel.setText(String.valueOf(t1.getTimeouts()));
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
		
		team2AddTimeout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t2.addTimeout();
				team2TimeoutLabel.setText(String.valueOf(t2.getTimeouts()));
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
		
		team1MinusTimeout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.minusTimeout();
				team1TimeoutLabel.setText(String.valueOf(t1.getTimeouts()));
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
		
		team2MinusTimeout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t2.minusTimeout();
				team2TimeoutLabel.setText(String.valueOf(t2.getTimeouts()));
				d.setChange(String.valueOf(t1.getPoints()), String.valueOf(t2.getPoints()), t.getTime(), shotClock.getShotClockTime(), String.valueOf(t1.getTimeouts()), String.valueOf(t2.getTimeouts()));
			}
		});
	}
}
