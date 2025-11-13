import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    // Declaration of required variables
    JTextField inputBox;
    JButton[] numButtons = new JButton[10];
    JButton addButton, subButton, mulButton, divButton, clrButton, eqButton;
    JPanel panel;
    String operator = "";
    double firstNum, secondNum, result;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        inputBox = new JTextField();
        inputBox.setEditable(false);
        inputBox.setFont(new Font("Arial", Font.BOLD, 24));
        add(inputBox, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            numButtons[i].addActionListener(this);
            panel.add(numButtons[i]);
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        clrButton = new JButton("C");
        eqButton = new JButton("=");

        JButton[] opButtons = {addButton, subButton, mulButton, divButton, clrButton, eqButton};
        for (JButton b : opButtons) {
            b.setFont(new Font("Arial", Font.BOLD, 20));
            b.addActionListener(this);
            panel.add(b);
        }

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            inputBox.setText(inputBox.getText() + command);
        } else if (command.charAt(0) == 'C') {
            inputBox.setText("");
            operator = "";
            firstNum = 0;
            secondNum = 0;
            result = 0;
        } else if (command.charAt(0) == '=') {
            secondNum = Double.parseDouble(inputBox.getText().substring(inputBox.getText().indexOf(operator) + 1));
            switch (operator) {
                case "+":
                    result = firstNum + secondNum;
                    break;
                case "-":
                    result = firstNum - secondNum;
                    break;
                case "*":
                    result = firstNum * secondNum;
                    break;
                case "/":
                    if (secondNum != 0)
                        result = firstNum / secondNum;
                    else
                        inputBox.setText("Error: Div by 0");
                    return;
            }
            inputBox.setText(String.valueOf(result));
            operator = "";
        } else {
            if (inputBox.getText().length() > 0 && operator.equals("")) {
                firstNum = Double.parseDouble(inputBox.getText());
                operator = command;
                inputBox.setText(inputBox.getText() + operator);
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
