import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessGame extends JFrame implements ActionListener {

    // Panel Start
    private JPanel panelStartGame;
    private ImageIcon image;
    private JLabel labelForPhoto;
    private JButton startGame;

    // Panel Game
    private JPanel game;
    private JLabel attemptsLabel;
    private JLabel guessTheNum;
    private JLabel hint;
    private JTextField putNumber;
    private JButton guess;
    private final Random random = new Random();
    private int attempts = 3;
    private int robotNumber;
    private int userGuess;

    // Panel Win
    private JPanel finalPanel;
    private JLabel messageLabel;
    private ImageIcon gifWin;
    private JLabel labelGifWin;
    private JButton restartWin;

    // Panel Lose
    private JPanel finalPanelFail;
    private JLabel messageLabelFail;
    private JLabel result;
    private ImageIcon gifLose;
    private JLabel labelGifLose;
    private JButton restartLose;

    CardLayout cardLayout;
    JPanel container;

    GuessGame() {
        generateRobotNumber();
        setupStartPanel();
        setupGamePanel();
        setupWinPanel();
        setupFailPanel();
        setupCardLayout();

        this.setTitle("Guess the Number");
        this.setSize(500, 510);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void setupStartPanel() {
        image = new ImageIcon("src/images/2ee9625a733381b5f2cfb4123ecb7d3d.jpg");
        labelForPhoto = new JLabel(image);
        labelForPhoto.setBounds(0, 0, 500, 350);

        startGame = new JButton("Start Game");
        startGame.setFont(new Font("Arial", Font.BOLD, 20));
        startGame.setBounds(175, 385, 150, 55);
        startGame.setBackground(new Color(0xC71295E6, true));
        startGame.setForeground(Color.BLACK);
        startGame.addActionListener(this);
        startGame.setBorder(null);
        startGame.setFocusable(false);

        panelStartGame = new JPanel();
        panelStartGame.setSize(500, 510);
        panelStartGame.setBackground(new Color(21, 15, 49));
        panelStartGame.setLayout(null);
        panelStartGame.setVisible(true);
        panelStartGame.add(labelForPhoto);
        panelStartGame.add(startGame);

        this.add(panelStartGame);
    }

    private void setupGamePanel() {
        attemptsLabel = new JLabel("You have " + attempts + " attempts", JLabel.CENTER);
        attemptsLabel.setSize(480, 100);
        attemptsLabel.setFont(new Font("Arial", Font.BOLD, 45));
        attemptsLabel.setForeground(Color.ORANGE);

        guessTheNum = new JLabel("Guess the Number:");
        guessTheNum.setBounds(25, 80, 350, 50);
        guessTheNum.setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 30));
        guessTheNum.setForeground(new Color(0x0251A1));

        putNumber = new JTextField();
        putNumber.setBounds(230, 150, 100, 100);
        putNumber.setBackground(new Color(21, 15, 49));
        putNumber.setFont(new Font("Arial", Font.BOLD, 50));
        putNumber.setForeground(Color.WHITE);
        putNumber.setBorder(null);

        hint = new JLabel("", JLabel.CENTER);
        hint.setBounds(150, 300, 200, 75);
        hint.setFont(new Font("Arial", Font.BOLD, 25));
        hint.setForeground(Color.WHITE);
        hint.setVisible(true);

        guess = new JButton("Guess");
        guess.setBounds(175, 380, 150, 55);
        guess.setBackground(new Color(0xC71295E6));
        guess.setForeground(new Color(0x000000));
        guess.setFont(new Font("Arial", Font.BOLD, 25));
        guess.addActionListener(this);
        guess.setFocusable(false);
        guess.setBorder(null);

        game = new JPanel();
        game.setBounds(0, 0, 500, 510);
        game.setBackground(new Color(21, 15, 49));
        game.setLayout(null);
        game.add(attemptsLabel);
        game.add(guessTheNum);
        game.add(putNumber);
        game.add(guess);
        game.add(hint);

        this.add(game);
    }

    private void setupWinPanel() {
        messageLabel = new JLabel("<html><div align='center'>You Won! <br>Congratulation!</div></html>", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 35));
        messageLabel.setForeground(new Color(29, 113, 4));
        messageLabel.setBounds(10, 50, 480, 100);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVerticalAlignment(JLabel.CENTER);

        gifWin = new ImageIcon("src/images/giphy.gif");
        labelGifWin = new JLabel(gifWin, JLabel.CENTER);
        labelGifWin.setBounds(100, 175, 300, 150);

        restartWin = createRestartButton();

        finalPanel = new JPanel();
        finalPanel.setBounds(0, 0, 500, 510);
        finalPanel.setBackground(new Color(21, 15, 49));
        finalPanel.setLayout(null);
        finalPanel.add(messageLabel);
        finalPanel.add(labelGifWin);
        finalPanel.add(restartWin);

        this.add(finalPanel);
    }

    private void setupFailPanel() {
        messageLabelFail = new JLabel("You Lost Motherfucker", JLabel.CENTER);
        messageLabelFail.setFont(new Font("Arial", Font.BOLD, 35));
        messageLabelFail.setForeground(new Color(143, 4, 4));
        messageLabelFail.setBounds(0, 10, 480, 100);

        result = new JLabel("", JLabel.CENTER);
        result.setFont(new Font("Arial", Font.BOLD, 35));
        result.setForeground(new Color(143, 4, 4));
        result.setBounds(35, 65, 400, 100);

        gifLose = new ImageIcon("src/images/lose.gif");
        labelGifLose = new JLabel(gifLose, JLabel.CENTER);
        labelGifLose.setBounds(20, 175, 450, 120);

        restartLose = createRestartButton();

        finalPanelFail = new JPanel();
        finalPanelFail.setBounds(0, 0, 500, 510);
        finalPanelFail.setBackground(new Color(21, 15, 49));
        finalPanelFail.setLayout(null);
        finalPanelFail.add(messageLabelFail);
        finalPanelFail.add(result);
        finalPanelFail.add(labelGifLose);
        finalPanelFail.add(restartLose);
    }

    private JButton createRestartButton() {
        JButton restartButton = new JButton("Restart");
        restartButton.setBounds(175, 365, 150, 55);
        restartButton.setBackground(new Color(0xC71295E6));
        restartButton.setForeground(new Color(0x000000));
        restartButton.setFont(new Font("Arial", Font.BOLD, 25));
        restartButton.addActionListener(this);
        restartButton.setFocusable(false);
        restartButton.setBorder(null);
        return restartButton;
    }


    private void setupCardLayout() {
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        container.add(panelStartGame, "Start");
        container.add(game, "Game");
        container.add(finalPanel, "Win");
        container.add(finalPanelFail, "Lose");

        this.add(container);
    }

    private void generateRobotNumber() {
        robotNumber = random.nextInt(10) + 1;
    }

    public void switchPanels(String panelName) {
        CardLayout cl = (CardLayout) container.getLayout();
        cl.show(container, panelName);
    }

    private void checkGuess() {
        if (userGuess > robotNumber) {
            attempts--;
            attemptsLabel.setText("You have " + attempts + " attempts");
            hint.setText("Lower!");
            hint.setForeground(Color.WHITE);
            putNumber.setText("");
        } else if (userGuess < robotNumber) {
            attempts--;
            attemptsLabel.setText("You have " + attempts + " attempts");
            hint.setText("Higher!");
            hint.setForeground(Color.WHITE);
            putNumber.setText("");
        } else {
            switchPanels("Win");
        }

        if (attempts == 0) {
            result.setText("The number was: " + robotNumber);
            switchPanels("Lose");
        }

        hint.setVisible(true);
    }

    private boolean isValidGuess(String input) {
        try {
            userGuess = Integer.parseInt(putNumber.getText());
            return true;
        } catch (NumberFormatException ex) {
            hint.setForeground(Color.RED);
            hint.setText("Enter a number!");
            putNumber.setText("");
            return false;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startGame) {
            switchPanels("Game");
        }

        if (e.getSource() == guess) {
            if (attempts > 0) {
                String input = putNumber.getText();

                if (isValidGuess(input)) {
                    userGuess = Integer.parseInt(input);
                    checkGuess();
                }
            }
        }

        if (e.getSource() == restartWin) {
            switchPanels("Start");

            generateRobotNumber();
            attempts = 5;
            attemptsLabel.setText("You have " + attempts + " attempts");

            putNumber.setText("");
            hint.setText("");
            hint.setForeground(Color.WHITE);
        }

        if (e.getSource() == restartLose) {
            switchPanels("Start");

            generateRobotNumber();
            attempts = 5;
            attemptsLabel.setText("You have " + attempts + " attempts");

            putNumber.setText("");
            hint.setText("");
            hint.setForeground(Color.WHITE);
        }
    }
}
