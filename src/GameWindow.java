import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GameWindow{

	JFrame window;
	
	JPanel hiraganaPanel;
	JLabel hiraganaLabel;
	
	JLabel scoreLabel;
	JTextField textField;
	
	char[] hiraganaArray = {'あ', 'い', 'う', 'え', 'お'};
	
	int score = 0;
	
	LinkedList<Character> previousHiraganaList = new LinkedList<>();
	
	public GameWindow() {
		initialize();
	}
	
	private void initialize() {
		//Initialize Window
		window = new JFrame();
		window.setTitle("Kana Game");
		window.setSize(500,500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setLayout(null);
		window.getContentPane().setBackground(Color.DARK_GRAY.darker());
		window.setVisible(true);
		window.setResizable(false);
	
		//Initialize Hiragana Area
		hiraganaPanel = new JPanel();
		hiraganaLabel = new JLabel("あ");
		
		hiraganaPanel.setBounds(150, 100, 200, 200);
		hiraganaPanel.setBackground(Color.DARK_GRAY);
		hiraganaPanel.setBorder(new LineBorder(Color.gray, 5));
		
		hiraganaLabel.setFont(new Font(hiraganaLabel.getFont().getName(), Font.PLAIN, 150));
		hiraganaLabel.setForeground(Color.white);
		
		hiraganaPanel.add(hiraganaLabel);
		window.add(hiraganaPanel);
		
		
		//Initialize Score Label
		scoreLabel = new JLabel("Score: " + 0);
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
		scoreLabel.setForeground(Color.white);
		scoreLabel.setBounds(185, 20, 200, 100);
		
		window.add(scoreLabel);
		
		//Initialize Textfield
		textField = new JTextField(1);
		
		textField.setBounds(150, 320, 200, 50);
		textField.setFont(new Font("Arial", Font.PLAIN, 30));
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setBackground(Color.DARK_GRAY);
		textField.setBorder(new LineBorder(Color.GRAY, 1));
		textField.setForeground(Color.white);
		textField.setCaretColor(Color.white);
		textField.setText("Enter romaji");
		
		//Game Logic
		textField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				//Make sure the same Hiragana Character doesn't display twice in a row
				char randomKana = pickRandomHiragana();
				if(!previousHiraganaList.isEmpty()) {
					while(previousHiraganaList.getLast().equals(randomKana)) {
						randomKana = pickRandomHiragana();
					}
				}
				previousHiraganaList.add(randomKana);
				
				//Check if user enters correct character according to hiragana character displayed
				//Increase score for every correct guess
				switch(hiraganaLabel.getText()) {
				case "あ":
					if(textField.getText().length() == 1 && (textField.getText().charAt(0) == 'a'||textField.getText().charAt(0) == 'A')) {
						hiraganaLabel.setText(Character.toString(randomKana));
						scoreLabel.setText("Score: " + ++score);
					}
					else {
						//Restart game if user enters wrong character
						restartGame();
					}
					break;
				case "い":
					if(textField.getText().length() == 1 && (textField.getText().charAt(0) == 'i'||textField.getText().charAt(0) == 'I')) {
						hiraganaLabel.setText(Character.toString(randomKana));
						scoreLabel.setText("Score: " + ++score);
					}else {
						restartGame();
					}
					break;
				case "う":
					if(textField.getText().length() == 1 && (textField.getText().charAt(0) == 'u'||textField.getText().charAt(0) == 'U')) {
						hiraganaLabel.setText(Character.toString(randomKana));
						scoreLabel.setText("Score: " + ++score);
					}else {
						restartGame();
					}
					break;
				case "え":
					if(textField.getText().length() == 1 && (textField.getText().charAt(0) == 'e'||textField.getText().charAt(0) == 'E')) {
						hiraganaLabel.setText(Character.toString(randomKana));
						scoreLabel.setText("Score: " + ++score);
					}else {
						restartGame();
					}
					break;
				case "お":
					if(textField.getText().length() == 1 && (textField.getText().charAt(0) == 'o' ||textField.getText().charAt(0) == 'O')) {
						hiraganaLabel.setText(Character.toString(randomKana));
						scoreLabel.setText("Score: " + ++score);
					}else {
						restartGame();
					}
					break;
				}			
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		window.add(textField);
		
		
	}
	
	//Pick Random Hiragana from Hiragana Array
	private char pickRandomHiragana() {
		Random rand = new Random();
		int randomNumber = rand.nextInt(5);
		return hiraganaArray[randomNumber];
	}
	
	//What happens when game restarts
	private void restartGame() {
		JOptionPane.showMessageDialog(window, "You Lose! Your Score: " + score);
		new GameWindow();
		window.dispose();
	}
	
	
}
