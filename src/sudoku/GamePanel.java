package sudoku;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class GamePanel extends JPanel implements ActionListener {
    
    MoveListener listener;

    JButton[][] jogo = new JButton[9][9];
    JButton[] selec = new JButton[10];

    JPanel gridPanel = new JPanel();
    JPanel selecPanel = new JPanel();

    Color corFundoJogo1 = new Color(255, 255, 255);
    Color corFundoJogo2 = new Color(255, 255, 160);
    Color corFundoSelec1 = new Color(215, 255, 220);
    Color corFundoSelec2 = Color.GREEN;

    int numSelec = 1;
    
    int[][] values;
    
    public GamePanel() {

        setSize(400, 400);

        setLayout(new BorderLayout());
        gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        gridPanel.setLayout(new GridLayout(9, 9));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JButton btn = new JButton();
                btn.setBackground(corFundoJogo1);
                btn.setFont(new java.awt.Font("Tahoma", 1, 24));
                btn.addActionListener(this);
                jogo[i][j] = btn;
                gridPanel.add(btn);
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 6; j++) {
                jogo[i][j].setBackground(corFundoJogo2);
            }
        }
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                jogo[i][j].setBackground(corFundoJogo2);
            }
        }

        for (int i = 3; i < 6; i++) {
            for (int j = 6; j < 9; j++) {
                jogo[i][j].setBackground(corFundoJogo2);
            }
        }
        for (int i = 6; i < 9; i++) {
            for (int j = 3; j < 6; j++) {
                jogo[i][j].setBackground(corFundoJogo2);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        selecPanel.setLayout(new GridLayout(1, 10));
        for (int i = 0; i < 10; i++) {
            String text = String.valueOf(i);
            if (i == 0) {
                text = "-";
            }

            JButton btn = new JButton(text);
            btn.addActionListener(this);
            btn.setSelected(true);
            btn.setBackground(corFundoSelec1);
            selec[i] = btn;
            selecPanel.add(btn);
        }

        selec[1].setBackground(corFundoSelec2);

        add(selecPanel, BorderLayout.SOUTH);
    }
    
    public void setMoveListener(MoveListener move){
        listener=move;
    }
    
    public void actionPerformed(ActionEvent e) {
        
        if(values == null || values.length == 0) {
            return;
        }

        JButton source = (JButton) e.getSource();

        int i = 0;
        int j = 0;
        boolean achou = false;
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                if (jogo[i][j] == source && !jogo[i][j].getText().equalsIgnoreCase(String.valueOf(values[i][j]))) {
                    achou = true;
                    break;
                }
            }
            if (achou) {
                break;
            }
        }
        if (achou) {
            String message = "jogo: " + String.valueOf(i) + "-" + String.valueOf(j) + "- num " + String.valueOf(numSelec);
            System.out.println(message);
            

            if (numSelec == 0) {
                jogo[i][j].setText("");
            } else {
                jogo[i][j].setText(String.valueOf(numSelec));
                jogo[i][j].setForeground(Color.BLUE);
            }
            
            listener.onMove(new Move(i,j,numSelec));
            
        } else {
            for (i = 0; i < 10; i++) {
                if (selec[i] == source) {
                    achou = true;
                    break;
                }
            }

            if (achou) {
                numSelec = i;
                for (int k = 0; k < 10; k++) {
                    selec[k].setBackground(corFundoSelec1);
                }
                source.setBackground(corFundoSelec2);

            }
        }

    }

    public void cleanBoard() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                jogo[i][j].setForeground(Color.BLACK);
                jogo[i][j].setText("");
            }
        }
    }
    
    public void fillBoard(int[][] values) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(values[i][j] == 0) {
                    jogo[i][j].setText("");
                } else {
                    jogo[i][j].setText(String.valueOf(values[i][j]));
                }
            }
        }
        
        this.values = values;
    }
    
    public void addValue(Move move) {
        values[move.getLine()][move.getColumn()] = move.getValue();
    }

}
