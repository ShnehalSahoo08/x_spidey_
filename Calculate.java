import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculate extends JFrame implements ActionListener {
    private JTextField display;
    private String operator = "";
    private double num1 = 0, num2 = 0;
    private boolean startNew = true;

    public Calculate() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            panel.add(btn);
        }
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = ((JButton) e.getSource()).getText();
        if (cmd.matches("\\d")) {
            if (startNew) {
                display.setText(cmd);
                startNew = false;
            } else {
                display.setText(display.getText() + cmd);
            }
        } else if ("+-*/".contains(cmd)) {
            operator = cmd;
            num1 = Double.parseDouble(display.getText());
            startNew = true;
        } else if (cmd.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            double result = 0;
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": 
                    if (num2 == 0) {
                        display.setText("Error");
                        startNew = true;
                        return;
                    }
                    result = num1 / num2; 
                    break;
            }
            display.setText("" + result);
            startNew = true;
        } else if (cmd.equals("C")) {
            display.setText("");
            operator = "";
            num1 = num2 = 0;
            startNew = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculate());
    }
}
