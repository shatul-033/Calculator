package Lab;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class Calculator extends JFrame implements ActionListener {
        private JTextField textField;
        private JButton[] buttons;
        private JButton onButton;
        private JButton offButton;
        private String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "π", "+",
                "%", "√", "^", "="
        };
        private double num1, num2, result;
        private char operator;
        private boolean calculatorOn = false;

        public Calculator() {
            setTitle("SIMPLE CALCULATOR");
            setSize(300, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            getContentPane().setBackground(Color.WHITE);

            textField = new JTextField();
            textField.setEditable(false);
            textField.setBackground(Color.BLACK);
            textField.setForeground(Color.WHITE);
            textField.setPreferredSize(new Dimension(400, 50));

            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.setBackground(Color.WHITE);

            onButton = new JButton("ON");
            onButton.addActionListener(this);
            onButton.setBackground(new Color(70, 123, 70));
            offButton = new JButton("OFF");
            offButton.addActionListener(this);
            offButton.setBackground(new Color(250, 50, 50));
            offButton.setForeground(Color.WHITE);
            offButton.setEnabled(false);

            JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
            buttonPanel.setBackground(Color.WHITE);

            buttons = new JButton[buttonLabels.length];
            for (int i = 0; i < buttonLabels.length; i++) {
                buttons[i] = new JButton(buttonLabels[i]);
                buttons[i].addActionListener(this);
                if (Character.isDigit(buttonLabels[i].charAt(0)) && !buttonLabels[i].equals("0")) {
                    buttons[i].setBackground(Color.WHITE);
                    buttons[i].setForeground(Color.BLACK);
                } else if (buttonLabels[i].equals("0") || buttonLabels[i].equals(".") || buttonLabels[i].equals("π")) {
                    buttons[i].setBackground(Color.WHITE);
                    buttons[i].setForeground(Color.BLACK);
                } else if (buttonLabels[i].equals("=")) {
                    buttons[i].setBackground(new Color(205, 92, 92));
                    buttons[i].setForeground(Color.BLACK);
                } else if (buttonLabels[i].equals("/") || buttonLabels[i].equals("*") || buttonLabels[i].equals("-") ||
                        buttonLabels[i].equals("+") || buttonLabels[i].equals("%") || buttonLabels[i].equals("√") ||
                        buttonLabels[i].equals("^")) {
                    buttons[i].setBackground(Color.GRAY);
                    buttons[i].setForeground(Color.BLACK);
                } else {
                    buttons[i].setBackground(new Color(44, 44, 90));
                    buttons[i].setForeground(Color.WHITE);
                }
                buttonPanel.add(buttons[i]);
            }

            // Separate panel for DEL and AC buttons
            JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
            bottomPanel.setBackground(Color.WHITE);

            JButton delButton = new JButton("DEL");
            delButton.addActionListener(this);
            delButton.setBackground(new Color(30, 78, 90));
            delButton.setForeground(Color.WHITE);
            bottomPanel.add(delButton);

            JButton acButton = new JButton("AC");
            acButton.addActionListener(this);
            acButton.setBackground(new Color(30, 90, 90));
            acButton.setForeground(Color.WHITE);
            bottomPanel.add(acButton);

            JPanel onOffPanel = new JPanel();
            onOffPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            onOffPanel.setBackground(Color.WHITE);
            onOffPanel.add(onButton);
            onOffPanel.add(offButton);

            topPanel.add(onOffPanel, BorderLayout.NORTH);
            topPanel.add(textField, BorderLayout.SOUTH);

            add(topPanel, BorderLayout.NORTH);
            add(buttonPanel, BorderLayout.CENTER);
            add(bottomPanel, BorderLayout.SOUTH);


            toggleCalculator(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("ON")) {
                toggleCalculator(true);
            } else if (command.equals("OFF")) {
                toggleCalculator(false);
            } else if (!calculatorOn) {
                return;
            }
            if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
                textField.setText(textField.getText() + command);
            } else if (command.equals("=")) {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0)
                            result = num1 / num2;
                        else
                            result = 0; 
                        break;
                    case '%':
                        result = num1 * (num2 / 100);
                        break;
                    case '√':
                        result = Math.sqrt(num1);
                        break;
                    case '^':
                        result = Math.pow(num1, num2);
                        break;
                }
                textField.setText(String.valueOf(result));
            } else if (command.equals("DEL")) {
                String currentText = textField.getText();
                if (currentText.length() > 0) {
                    textField.setText(currentText.substring(0, currentText.length() - 1));
                }
            } else if (command.equals("AC")) {


                textField.setText("");
            }



            else if (command.equals("π")) {

                textField.setText(String.valueOf(Math.PI));
            } else {
                operator = command.charAt(0);
                num1 = Double.parseDouble(textField.getText());
                textField.setText("");
            }
        }

        // Method to toggle calculator state
        private void toggleCalculator(boolean on) {
            calculatorOn = on;
            if (calculatorOn) {
                onButton.setEnabled(false);
                offButton.setEnabled(true);
                textField.setText("");
                for (JButton button : buttons) {
                    button.setEnabled(true);
                }
            } else {
                onButton.setEnabled(true);
                offButton.setEnabled(false);
                textField.setText("");
                for (JButton button : buttons) {
                    button.setEnabled(false);
                }
            }
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new Calculator().setVisible(true);
                }
            });
        }
    }

