package com.bryan;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe extends JFrame implements ActionListener {
    JLabel label = new JLabel("Tic Tac Toe");//Title before the game starts
    JLabel xScore = new JLabel(); //Label score of Player1
    JLabel oScore = new JLabel();// Label score of Player2
    JLabel score = new JLabel("Score"); //Score
    JLabel score2 = new JLabel("Score");//Label
    JLabel player1 = new JLabel("Player 1");//these are
    JLabel player2 = new JLabel("Player 2");// the label of players
    JLabel title = new JLabel("TIC - TAC - TOE");// Title label at the bottom
    JPanel boardPanel = new JPanel(); //Panel of the board at the top of the game
    JPanel buttonPanel = new JPanel();// Panel for all the buttons
    JPanel southPanel = new JPanel(); // Panel at the bottom of the game
    JPanel panelX = new JPanel(); //Panel for the player1 score
    JPanel panelO = new JPanel();// Panel for the player2 score
    JButton[] buttons = new JButton[9];//Buttons of the tic tac toe game
    JButton resetButton = new JButton("Reset"); // the reset button
    Font font = new Font("Comic Sans MS",Font.BOLD,80); // font style and font size
    Font font_score = new Font("Comic Sans MS", Font.BOLD,40);// font style and font size
    boolean player; //decision fo who will get the first turn
    int scoreO = 0; // number of score of player2
    int scoreX = 0; // number of score of player1
    TicTacToe(){
        this.setTitle("2 Player Tic Tac Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(font);
        label.setForeground(Color.GRAY);
        label.setBorder(new LineBorder(Color.BLACK,5,false));
        label.setOpaque(true);

        score.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        score.setHorizontalAlignment(JLabel.CENTER);

        player1.setHorizontalAlignment(JLabel.CENTER);
        player1.setVerticalAlignment(JLabel.BOTTOM);
        player1.setFont(new Font("Comic Sans MS",Font.BOLD,20));

        xScore.setFont(font_score);
        xScore.setText(String.valueOf(scoreX));
        xScore.setHorizontalAlignment(JLabel.CENTER);
        xScore.setVerticalAlignment(JLabel.TOP);

        panelX.setLayout(new GridLayout(3,0));
        panelX.add(score);
        panelX.add(player1);
        panelX.add(xScore);

        score2.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        score2.setHorizontalAlignment(JLabel.CENTER);

        player2.setHorizontalAlignment(JLabel.CENTER);
        player2.setVerticalAlignment(JLabel.BOTTOM);
        player2.setFont(new Font("Comic Sans MS",Font.BOLD,20));

        oScore.setFont(font_score);
        oScore.setText(String.valueOf(scoreX));
        oScore.setVerticalAlignment(JLabel.TOP);
        oScore.setHorizontalAlignment(JLabel.CENTER);

        panelO.setLayout(new GridLayout(3,0));
        panelO.add(score2);
        panelO.add(player2);
        panelO.add(oScore);

        boardPanel.setLayout(new BorderLayout());
        boardPanel.setBounds(0,0,600,100);

        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setBackground(Color.BLACK);
        boardPanel.add(label);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(font);
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);

        }

        resetButton.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        resetButton.addActionListener(this);
        resetButton.setEnabled(false);

        title.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        title.setHorizontalAlignment(JLabel.CENTER);

        southPanel.setLayout(new FlowLayout());
        southPanel.add(title);
        southPanel.add(resetButton);


        this.add(boardPanel,BorderLayout.NORTH);
        this.add(buttonPanel,BorderLayout.CENTER);
        this.add(panelX,BorderLayout.WEST);
        this.add(panelO,BorderLayout.EAST);
        this.add(southPanel,BorderLayout.SOUTH);

        firstTurn();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 9; i++) {
            if(e.getSource() == buttons[i]){//Action command of every buttons
                if(player){
                    if(buttons[i].getText().equals("O")){
                        //if the button contains "0" then Player1 will get a warning that button already chosen by the opponent
                        JOptionPane.showMessageDialog(this,"Already Chosen","Title",JOptionPane.WARNING_MESSAGE);
                        buttons[i].setText("O");
                    }
                    else if(buttons[i].getText().equals("X")){
                        //if the button contains "X" then Player1 will get a warning that you will choose another button
                        JOptionPane.showMessageDialog(this,"Try Another Button", "Title", JOptionPane.WARNING_MESSAGE);
                        buttons[i].setText("X");
                    }
                    else {
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("X");
                        player = false;
                        label.setText("O turn");
                    }
                }
                else {
                    if(buttons[i].getText().equals("X")){
                        //if the button contains "0" then Player2 will get a warning that button already chosen by the opponent
                        JOptionPane.showMessageDialog(this,"Already Chosen","Title",JOptionPane.WARNING_MESSAGE);
                        buttons[i].setText("X");
                    }
                    else if(buttons[i].getText().equals("O")){
                        //if the button contains "O" then Player1 will get a warning that you will choose another button
                        JOptionPane.showMessageDialog(this,"Try Another Button", "Title", JOptionPane.WARNING_MESSAGE);
                        buttons[i].setText("O");

                    }
                    else {
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("O");
                        player = true;
                        label.setText("X turn");
                    }
                }
                checkResults();
            }

        }

        if(e.getSource() == resetButton){
            int a = JOptionPane.showConfirmDialog(this,"Are you sure?", "Reset",JOptionPane.YES_NO_OPTION);
            if(a == 0){
                resetButton.setEnabled(false);
                for (int i = 0; i < 9; i++) {
                    buttons[i].setText("");
                }
                Random random = new Random();
                if(random.nextInt(2) == 0){
                    player = true;
                    label.setText("X Turn");
                }
                else{
                    player = false;
                    label.setText("O turn");
                }
                scoreX = 0;
                scoreO = 0;
                xScore.setText(String.valueOf(scoreX));
                oScore.setText(String.valueOf(scoreO));
            }
        }
    }
    public void firstTurn(){
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(true);
        }
        Random random = new Random();
        if(random.nextInt(2) == 0){
            player = true;
            label.setText("X Turn");
        }
        else{
            player = false;
            label.setText("O turn");
        }

    }
    public void xWins(int a, int b, int c){
        buttons[a].setForeground(Color.GREEN);
        buttons[b].setForeground(Color.GREEN);
        buttons[c].setForeground(Color.GREEN);
        scoreX ++;
        xScore.setText(String.valueOf(scoreX));

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        label.setText("X Wins");
        int round = JOptionPane.showConfirmDialog(this,"Play Again?", "Another Game",JOptionPane.YES_NO_OPTION);
        if(round == 0){
            for (int i = 0; i < 9; i++) {
                buttons[i].setText("");
                buttons[i].setEnabled(true);
                label.setText("O Turn");
                resetButton.setEnabled(true);
            }
        }
        else {
            System.exit(0);
        }
    }
    public void oWins(int a, int b, int c){
        buttons[a].setForeground(Color.GREEN);
        buttons[b].setForeground(Color.GREEN);
        buttons[c].setForeground(Color.GREEN);
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        label.setText("O Wins");
        scoreO++;
        oScore.setText(String.valueOf(scoreO));
        int round = JOptionPane.showConfirmDialog(this,"Play Again?", "Another Game",JOptionPane.YES_NO_OPTION);
        if(round == 0){
            for (int i = 0; i < 9; i++) {
                buttons[i].setText("");
                buttons[i].setEnabled(true);
                label.setText("X Turn");
                resetButton.setEnabled(true);
            }
        }
        else{
            System.exit(0);
        }
    }
    public void checkResults(){

        //X Wins Condition
        if((buttons[0].getText().equals("X")) &&
                (buttons[1].getText().equals("X")) &&
                (buttons[2].getText().equals("X"))
        ){
            xWins(0,1,2);

        }else if((buttons[3].getText().equals("X")) &&
                (buttons[4].getText().equals("X")) &&
                (buttons[5].getText().equals("X"))
        ){
            xWins(3,4,5);
        }
        else if((buttons[6].getText().equals("X")) &&
                (buttons[7].getText().equals("X")) &&
                (buttons[8].getText().equals("X"))
        ){
            xWins(6,7,8);
        }
        else if((buttons[0].getText().equals("X")) &&
                (buttons[3].getText().equals("X")) &&
                (buttons[6].getText().equals("X"))
        ){
            xWins(0,3,6);
        }
        else if((buttons[1].getText().equals("X")) &&
                (buttons[4].getText().equals("X")) &&
                (buttons[7].getText().equals("X"))
        ){
            xWins(1,4,7);
        }
        else if((buttons[2].getText().equals("X")) &&
                (buttons[5].getText().equals("X")) &&
                (buttons[8].getText().equals("X"))
        ){
            xWins(2,5,8);
        }
        else if((buttons[0].getText().equals("X")) &&
                (buttons[4].getText().equals("X")) &&
                (buttons[8].getText().equals("X"))

        ){
            xWins(0,4,8);
        }
        else if((buttons[2].getText().equals("X")) &&
                (buttons[4].getText().equals("X")) &&
                (buttons[6].getText().equals("X")))
        {
            xWins(2,4,6);
        }

        if((buttons[0].getText().equals("O")) &&
                (buttons[1].getText().equals("O")) &&
                (buttons[2].getText().equals("O"))
        ){
            oWins(0,1,2);

        }else if((buttons[3].getText().equals("O")) &&
                (buttons[4].getText().equals("O")) &&
                (buttons[5].getText().equals("O"))
        ){
            oWins(3,4,5);
        }
        else if((buttons[6].getText().equals("O")) &&
                (buttons[7].getText().equals("O")) &&
                (buttons[8].getText().equals("O"))
        ){
            oWins(6,7,8);
        }
        else if((buttons[0].getText().equals("O")) &&
                (buttons[3].getText().equals("O")) &&
                (buttons[6].getText().equals("O"))
        ){
            oWins(0,3,6);
        }
        else if((buttons[1].getText().equals("O")) &&
                (buttons[4].getText().equals("O")) &&
                (buttons[7].getText().equals("O"))
        ){
            oWins(1,4,7);
        }
        else if((buttons[2].getText().equals("O")) &&
                (buttons[5].getText().equals("O")) &&
                (buttons[8].getText().equals("O"))
        ){
            oWins(2,5,8);
        }
        else if((buttons[0].getText().equals("O")) &&
                (buttons[4].getText().equals("O")) &&
                (buttons[8].getText().equals("O"))

        ){
            oWins(0,4,8);
        }
        else if((buttons[2].getText().equals("O")) &&
                (buttons[4].getText().equals("O")) &&
                (buttons[6].getText().equals("O")))
        {
            oWins(2,4,6);
        }
        else {
            draw();
        }

    }
    public void draw(){
        if(
                (buttons[0].getText().equals("X") || buttons[0].getText().equals("O")) &&
                (buttons[1].getText().equals("X") || buttons[1].getText().equals("O")) &&
                (buttons[2].getText().equals("X") || buttons[2].getText().equals("O")) &&
                (buttons[3].getText().equals("X") || buttons[3].getText().equals("O")) &&
                (buttons[4].getText().equals("X") || buttons[4].getText().equals("O")) &&
                (buttons[5].getText().equals("X") || buttons[5].getText().equals("O")) &&
                (buttons[6].getText().equals("X") || buttons[6].getText().equals("O")) &&
                (buttons[7].getText().equals("X") || buttons[7].getText().equals("O")) &&
                (buttons[8].getText().equals("X") || buttons[8].getText().equals("O"))
        ){
            label.setText("Tic Tac Toe");
            int round = JOptionPane.showConfirmDialog(this,"It's A DRAW!! Play Again?", "Another Game",JOptionPane.YES_NO_OPTION);
            if(round == 0){
                for (int i = 0; i < 9; i++) {
                    buttons[i].setText("");
                    buttons[i].setEnabled(true);
                }
                if(player){
                    label.setText("X Turn");
                }
                else{
                    label.setText("O Turn");
                }
            }
            else{
                System.exit(0);
            }

        }
    }
}
