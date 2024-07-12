package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;

public class Game extends JFrame {
    private JPanel userPanel, computerPanel;
    private JTextField inputField;
    private JButton submitButton, resetButton;
    private JLabel userLabel, computerLabel, scoreLabel;
    private Random random;
    private int userScore, computerScore;
    private boolean userBatting;

    public Game() {
        setTitle("Hand Cricket Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        userPanel = new JPanel();
        computerPanel = new JPanel();
        userLabel = new JLabel();
        computerLabel = new JLabel();

        userPanel.add(userLabel);
        computerPanel.add(computerLabel);

        add(userPanel, BorderLayout.WEST);
        add(computerPanel, BorderLayout.EAST);

        JPanel inputPanel = new JPanel();
        inputField = new JTextField(5);
        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset Game");
        scoreLabel = new JLabel("Score: 0 - 0");

        inputPanel.add(new JLabel("Enter your number (0-6): "));
        inputPanel.add(inputField);
        inputPanel.add(submitButton);
        inputPanel.add(resetButton);

        add(inputPanel, BorderLayout.SOUTH);
        add(scoreLabel, BorderLayout.NORTH);

        random = new Random();
        userScore = 0;
        computerScore = 0;
        userBatting = true;

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUserInput();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
    }

    private void handleUserInput() {
        try {
            int userNumber = Integer.parseInt(inputField.getText());
            if (userNumber < 0 || userNumber > 6) {
                JOptionPane.showMessageDialog(this, "Please enter a number between 0 and 6");
                return;
            }
            int computerNumber = random.nextInt(6) + 1;

            // Load and display the corresponding videos or GIFs
            displayHandAnimation(userLabel, userNumber);
            displayHandAnimation(computerLabel, computerNumber);

            // Apply the game rules and update scores
            if (userNumber == 0 && computerNumber != 0) {
                userScore += computerNumber;
            } else if (userNumber == computerNumber) {
                if (userBatting) {
                    userBatting = false;
                    JOptionPane.showMessageDialog(this, "You are out! Now it's the computer's turn to bat.");
                } else {
                    JOptionPane.showMessageDialog(this, "Computer is out! Game over.");
                    determineWinner();
                    return;
                }
            } else {
                if (userBatting) {
                    userScore += userNumber;
                } else {
                    computerScore += computerNumber;
                }
            }
            updateScore();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number between 0 and 6.");
        }
    }

    private void displayHandAnimation(JLabel label, int number) {
        // Assuming you have files named "0.gif", "1.gif", ..., "6.gif" in the resources directory
        String filePath = "/" + number + ".gif";
        URL imgURL = getClass().getResource(filePath);
        if (imgURL != null) {
            Icon icon = new ImageIcon(imgURL);
            label.setIcon(icon);
        } else {
            System.err.println("Error: File " + filePath + " not found.");
        }
    }

    private void updateScore() {
        scoreLabel.setText("Score: " + userScore + " - " + computerScore);
    }

    private void determineWinner() {
        if (userScore > computerScore) {
            JOptionPane.showMessageDialog(this, "You win! Final Score: " + userScore + " - " + computerScore);
        } else if (userScore < computerScore) {
            JOptionPane.showMessageDialog(this, "Computer wins! Final Score: " + userScore + " - " + computerScore);
        } else {
            JOptionPane.showMessageDialog(this, "It's a tie! Final Score: " + userScore + " - " + computerScore);
        }
        resetGame();
    }

    private void resetGame() {
        userScore = 0;
        computerScore = 0;
        userBatting = true;
        updateScore();
        userLabel.setIcon(null);
        computerLabel.setIcon(null);
        JOptionPane.showMessageDialog(this, "Game has been reset. You are batting first.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game().setVisible(true);
            }
        });
    }
}
