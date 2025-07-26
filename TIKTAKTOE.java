import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TIKTAKTOE extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private boolean xTurn = true;
    private JLabel statusLabel;

    public TIKTAKTOE() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLayout(new BorderLayout());

        JPanel board = new JPanel();
        board.setLayout(new GridLayout(3, 3));
        Font font = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                board.add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel("X's Turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JButton resetButton = new JButton("Restart");
        resetButton.addActionListener(e -> resetGame());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(resetButton, BorderLayout.EAST);

        add(board, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (!btn.getText().equals("")) return;

        btn.setText(xTurn ? "X" : "O");
        btn.setForeground(xTurn ? Color.BLUE : Color.RED);

        if (checkWin()) {
            statusLabel.setText((xTurn ? "X" : "O") + " Wins!");
            disableButtons();
        } else if (isDraw()) {
            statusLabel.setText("Draw!");
        } else {
            xTurn = !xTurn;
            statusLabel.setText((xTurn ? "X" : "O") + "'s Turn");
        }
    }

    private boolean checkWin() {
        String player = xTurn ? "X" : "O";
        // Rows and columns
        for (int i = 0; i < 3; i++) {
            if (player.equals(buttons[i][0].getText()) &&
                player.equals(buttons[i][1].getText()) &&
                player.equals(buttons[i][2].getText())) return true;
            if (player.equals(buttons[0][i].getText()) &&
                player.equals(buttons[1][i].getText()) &&
                player.equals(buttons[2][i].getText())) return true;
        }
        // Diagonals
        if (player.equals(buttons[0][0].getText()) &&
            player.equals(buttons[1][1].getText()) &&
            player.equals(buttons[2][2].getText())) return true;
        if (player.equals(buttons[0][2].getText()) &&
            player.equals(buttons[1][1].getText()) &&
            player.equals(buttons[2][0].getText())) return true;
        return false;
    }

    private boolean isDraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().equals("")) return false;
        return true;
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].setEnabled(false);
    }

    private void resetGame() {
        xTurn = true;
        statusLabel.setText("X's Turn");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TIKTAKTOE());
    }
}
