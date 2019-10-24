package scoreboard;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ScoreboardGUI {

	private JFrame frame;
	private JTextField team1text;
	private JTextField team2text;
	private JComboBox minutes;
	private JComboBox seconds;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel label;
	private JLabel lblTimeouts;
	private JComboBox timeouts;
	private JButton startGameButton;
	private JLabel lblGithubAmranddin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreboardGUI window = new ScoreboardGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScoreboardGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow][][][grow][][grow][grow][grow][][][]", "[][][][][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("New Game");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblNewLabel, "cell 6 0");
		
		team1text = new JTextField();
		team1text.setFont(new Font("Tahoma", Font.PLAIN, 18));
		team1text.setText("Team1");
		frame.getContentPane().add(team1text, "cell 2 1,growx");
		team1text.setColumns(10);
		
		team2text = new JTextField();
		team2text.setFont(new Font("Tahoma", Font.PLAIN, 18));
		team2text.setText("Team2");
		frame.getContentPane().add(team2text, "cell 9 1,growx");
		team2text.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Minutes");
		frame.getContentPane().add(lblNewLabel_1, "cell 5 4");
		lblNewLabel_1.setLabelFor(minutes);
		
		lblNewLabel_2 = new JLabel("Seconds");
		frame.getContentPane().add(lblNewLabel_2, "cell 7 4");
		
		minutes = new JComboBox();
		minutes.setModel(new DefaultComboBoxModel(new String[] {"0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"}));
		minutes.setSelectedIndex(25);
		frame.getContentPane().add(minutes, "cell 5 5,growx");
		
		label = new JLabel(":");
		frame.getContentPane().add(label, "cell 6 5,alignx trailing");
		lblNewLabel_2.setLabelFor(seconds);
		
		seconds = new JComboBox();
		seconds.setModel(new DefaultComboBoxModel(new String[] {"00", "10", "20", "30", "40", "50"}));
		frame.getContentPane().add(seconds, "cell 7 5,growx");
		
		lblTimeouts = new JLabel("Timeouts");
		lblTimeouts.setVerticalAlignment(SwingConstants.TOP);
		frame.getContentPane().add(lblTimeouts, "cell 6 8");
		
		timeouts = new JComboBox();
		timeouts.setModel(new DefaultComboBoxModel(new String[] {"7", "6", "5", "4", "3", "2", "1", "0"}));
		frame.getContentPane().add(timeouts, "cell 6 9,growx");
		
		startGameButton = new JButton("Start Game");
		frame.getContentPane().add(startGameButton, "cell 9 10");
		
		lblGithubAmranddin = new JLabel("GitHub: amran-uddin");
		lblGithubAmranddin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(lblGithubAmranddin, "cell 2 11");
		
		startGameButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				String team1 = team1text.getText();
				String team2 = team2text.getText();
				int team1TO = Integer.parseInt((String)timeouts.getSelectedItem());
				int team2TO = team1TO;
				int numOfTimeouts = Integer.parseInt((String) timeouts.getSelectedItem());				
				int minuteToMilli = Integer.parseInt((String) minutes.getSelectedItem()) * 60000;
				int secondToMilli = Integer.parseInt((String)seconds.getSelectedItem()) * 1000;
				
				int total = minuteToMilli + secondToMilli;
				
				Team teamOne = new Team(team1, team1TO);
				Team teamTwo = new Team(team2, team2TO);
				
				// make another GUI window		
				try {
					DisplayControl dis = new DisplayControl(teamOne, teamTwo, total, numOfTimeouts);
					dis.frame.setVisible(true);
				} catch (Exception er) {
					er.printStackTrace();
				}
				//
	        }  
	    });  
	}

}
