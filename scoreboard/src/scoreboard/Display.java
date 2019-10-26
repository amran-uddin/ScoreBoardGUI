package scoreboard;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.Font;
import javax.swing.SpringLayout;

public class Display {

	JFrame frame;
	JLabel team1Label;
	JLabel team2Label;
	JLabel team1Score;
	JLabel team2Score;
	JLabel timeLabel;
	JLabel shotClockLabel;
	JLabel team1TO;
	JLabel team2TO;
	

	public Display(String t1, String t2, String p1, String p2, String time, String shotClock, String to1, String to2) {

		this.team1Label = new JLabel(t1);
		this.team2Label = new JLabel(t2);
		this.team1Score = new JLabel(p1);
		this.team2Score = new JLabel(p2);
		this.timeLabel = new JLabel(time);
		this.shotClockLabel = new JLabel(shotClock);
		this.team1TO = new JLabel(to1);
		this.team2TO = new JLabel(to2);
		initialize();
		
	}
	
	void setChange(String t1s, String t2s, String tl, String scl, String t1to, String t2to)
	{
		this.team1Score.setText(t1s);
		this.team2Score.setText(t2s);
		this.timeLabel.setText(tl);
		this.shotClockLabel.setText(scl);
		this.team1TO.setText(t1to);
		this.team2TO.setText(t2to);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Score Board");
		frame.getContentPane().setBackground(Color.BLACK);
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		frame.setResizable(true);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		//JLabel team2Label = new JLabel("New label");
		team2Label.setFont(new Font("Tahoma", Font.PLAIN, 130));
		team2Label.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.NORTH, team2Label, 35, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, team2Label, -45, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(team2Label);
		
		//JLabel team1Label = new JLabel("New label");
		team1Label.setFont(new Font("Tahoma", Font.PLAIN, 130));
		team1Label.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.NORTH, team1Label, 0, SpringLayout.NORTH, team2Label);
		springLayout.putConstraint(SpringLayout.WEST, team1Label, 45, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(team1Label);
		
		//JLabel team1Score = new JLabel("New label");
		team1Score.setFont(new Font("Tahoma", Font.PLAIN, 300));
		team1Score.setForeground(Color.YELLOW);
		springLayout.putConstraint(SpringLayout.NORTH, team1Score, 6, SpringLayout.SOUTH, team1Label);
		springLayout.putConstraint(SpringLayout.WEST, team1Score, 0, SpringLayout.WEST, team1Label);
		frame.getContentPane().add(team1Score);
		
		//JLabel team2Score = new JLabel("New label");
		team2Score.setFont(new Font("Tahoma", Font.PLAIN, 300));
		team2Score.setForeground(Color.YELLOW);
		springLayout.putConstraint(SpringLayout.NORTH, team2Score, 6, SpringLayout.SOUTH, team2Label);
		springLayout.putConstraint(SpringLayout.EAST, team2Score, 0, SpringLayout.EAST, team2Label);
		frame.getContentPane().add(team2Score);
		
		//JLabel timeLabel = new JLabel("New label");
		timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 250));
		timeLabel.setForeground(new Color(50, 205, 50));
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, timeLabel, 0, SpringLayout.HORIZONTAL_CENTER, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, timeLabel, 0, SpringLayout.VERTICAL_CENTER, frame.getContentPane());
		frame.getContentPane().add(timeLabel);
		
		//JLabel shotClockLabel = new JLabel("New label");
		shotClockLabel.setFont(new Font("Tahoma", Font.PLAIN, 175));
		shotClockLabel.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.SOUTH, shotClockLabel, -46, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, shotClockLabel, 0, SpringLayout.HORIZONTAL_CENTER, frame.getContentPane());
		frame.getContentPane().add(shotClockLabel);
		
		//JLabel team1TO = new JLabel("New label");
		team1TO.setFont(new Font("Tahoma", Font.PLAIN, 120));
		team1TO.setForeground(Color.YELLOW);
		springLayout.putConstraint(SpringLayout.WEST, team1TO, 0, SpringLayout.WEST, team1Label);
		springLayout.putConstraint(SpringLayout.SOUTH, team1TO, -10, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(team1TO);
		
		//JLabel team2TO = new JLabel("New label");
		team2TO.setFont(new Font("Tahoma", Font.PLAIN, 120));
		springLayout.putConstraint(SpringLayout.NORTH, team2TO, 0, SpringLayout.NORTH, team1TO);
		springLayout.putConstraint(SpringLayout.EAST, team2TO, 0, SpringLayout.EAST, team2Label);
		team2TO.setForeground(Color.YELLOW);
		frame.getContentPane().add(team2TO);
		
		JLabel lblNewLabel_8 = new JLabel("Timeout");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 80));
		lblNewLabel_8.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_8, 0, SpringLayout.WEST, team1Label);
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_8, -6, SpringLayout.NORTH, team1TO);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Timeout");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 80));
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_9, -6, SpringLayout.NORTH, team2TO);
		lblNewLabel_9.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_9, 0, SpringLayout.EAST, team2Label);
		frame.getContentPane().add(lblNewLabel_9);
		
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
