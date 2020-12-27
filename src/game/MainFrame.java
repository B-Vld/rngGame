package game;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

class MainFrame extends JFrame {

	private static final long serialVersionUID = 3833691730578025471L;
	private JPanel contentPane;
	private JTextField textBox_number;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void add_picture(JLabel pictureBox, int generated_number) {
		pictureBox.setIcon(new ImageIcon(assetsPath + String.valueOf(generated_number) + ".png"));
	}

	private static void generateNo_for_bot(GenerateNo bot, JLabel pictureBox_number_bot, JTextArea textArea) {
		bot.setNumber(bot.roll(bot.getNumber()));
		initial_number = bot.getNumber();
		textArea.append("Bot rolled : " + bot.getNumber() + "\n");
		add_picture(pictureBox_number_bot, bot.getNumber());
		if (bot.equalToOne == true) {
			gameEnded = true;
		}
	}

	private static void generateNo_for_player(GenerateNo player, JLabel pictureBox_number_player, JTextArea textArea) {
		player.setNumber(player.roll(player.getNumber()));
		initial_number = player.getNumber();
		textArea.append("You rolled : " + player.getNumber() + "\n");
		add_picture(pictureBox_number_player, player.getNumber());
		if (player.equalToOne == true) {
			gameEnded = true;
		}
	}

	private static int initial_number;
	private static boolean gameEnded = false;
	private static JButton btnNewButton = new JButton("Play");
	private static int playerWon=0;
	private static int botWon=0;

	public static String assetsPath = System.getProperty("user.dir")+ "\\assets\\";

	
	public MainFrame() {
		setTitle("RNG_WoW_Inspired");
		setResizable(false);
		setForeground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);

		ImageIcon diceIcon = new ImageIcon(assetsPath + "dice.png");
		setIconImage(diceIcon.getImage());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter a number between 10 and 100 :");
		lblNewLabel.setBounds(10, 16, 210, 24);
		contentPane.add(lblNewLabel);

		textBox_number = new JTextField();
		textBox_number.setBounds(230, 16, 69, 24);
		contentPane.add(textBox_number);
		textBox_number.setColumns(10);

		JLabel pictureBox_number_bot = new JLabel("");
		pictureBox_number_bot.setHorizontalAlignment(SwingConstants.CENTER);
		pictureBox_number_bot.setBounds(20, 79, 135, 100);
		contentPane.add(pictureBox_number_bot);
		pictureBox_number_bot.setIcon(diceIcon);

		JLabel pictureBox_number_player = new JLabel("");
		pictureBox_number_player.setHorizontalAlignment(SwingConstants.CENTER);
		pictureBox_number_player.setBounds(230, 79, 135, 100);
		contentPane.add(pictureBox_number_player);
		pictureBox_number_player.setIcon(diceIcon);

		JLabel lblNewLabel_1 = new JLabel("Bot rolled :");
		lblNewLabel_1.setBounds(20, 71, 69, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("You rolled :");
		lblNewLabel_1_1.setBounds(230, 71, 69, 13);
		contentPane.add(lblNewLabel_1_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 236, 345, 100);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		JLabel label1 = new JLabel("Bot score :");
		label1.setBounds(30, 189, 79, 13);
		contentPane.add(label1);
		
		JLabel lblYourScore = new JLabel("Your score :");
		lblYourScore.setBounds(240, 189, 79, 13);
		contentPane.add(lblYourScore);
		
		JLabel score_bot = new JLabel("");
		score_bot.setBounds(119, 189, 22, 13);
		contentPane.add(score_bot);
		
		JLabel score_player = new JLabel("");
		score_player.setBounds(317, 189, 22, 13);
		contentPane.add(score_player);

		JButton b_generate_number = new JButton("Roll a number");
		b_generate_number.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GenerateNo bot = new GenerateNo(initial_number);
				generateNo_for_bot(bot, pictureBox_number_bot, textArea);
				if (gameEnded) {
					textArea.append("You won ! \n");
					b_generate_number.setEnabled(false);
					btnNewButton.setEnabled(true);
					playerWon++;
					score_player.setText(String.valueOf(playerWon));
					return;
				}

				GenerateNo player = new GenerateNo(initial_number);
				generateNo_for_player(player, pictureBox_number_player, textArea);
				if (gameEnded) {
					textArea.append("You lost ! \n");
					b_generate_number.setEnabled(false);
					btnNewButton.setEnabled(true);
					botWon++;
					score_bot.setText(String.valueOf(botWon));
					return;
				}
			}

		});
		b_generate_number.setBounds(119, 212, 125, 21);
		b_generate_number.setEnabled(false);
		contentPane.add(b_generate_number);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					initial_number = Integer.parseInt(textBox_number.getText());
					
					if(initial_number<10 || initial_number>100 ) {
						throw new IllegalAccessException();
					}
					else
					{
						add_picture(pictureBox_number_bot, initial_number);
						add_picture(pictureBox_number_player, initial_number);
						textBox_number.setText("");
						btnNewButton.setEnabled(false);
						b_generate_number.setEnabled(true);
						gameEnded = false;
						textArea.setText("");
					}
					}catch(IllegalArgumentException | IllegalAccessException e)
					{
						textBox_number.setText("");
						b_generate_number.setEnabled(false);
						btnNewButton.setEnabled(true);
						textArea.append("Please enter a number in the range of [10,100].\n");
					}
			}
		});
		btnNewButton.setBounds(309, 15, 57, 25);
		contentPane.add(btnNewButton);
		
	}
}
