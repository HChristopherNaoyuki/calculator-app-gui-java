package Solutions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AUTHOR: Naoyuki Christopher H.
 * GITHUB: https://github.com/HChristopherNaoyuki
 */

public class Calculator extends JFrame implements ActionListener
{
    private final JTextField display;
    private final JButton[] numberButtons;
    private final JButton addButton;
    private final JButton subtractButton;
    private final JButton multiplyButton;
    private final JButton divideButton;
    private final JButton decimalButton;
    private final JButton equalButton;
    private final JButton clearButton;
    private final JButton deleteButton;
    private final JButton factorialButton;
    private final JButton ansButton;

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;
    private double ans = 0;

    public Calculator()
    {
        setTitle("Github: HChristopherNaoyuki");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(50, 50, 50));

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Monospaced", Font.BOLD, 36));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(new Color(0, 0, 0));
        display.setForeground(new Color(0, 255, 0));
        display.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 5, 5, 5));
        buttonPanel.setBackground(new Color(100, 100, 100));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++)
        {
            numberButtons[i] = createButton(String.valueOf(i), new Color(0, 100, 200));
        }

        clearButton = createButton("AC", new Color(255, 100, 100));
        deleteButton = createButton("DEL", new Color(255, 150, 0));
        addButton = createButton("+", new Color(0, 150, 0));
        subtractButton = createButton("-", new Color(0, 150, 0));
        multiplyButton = createButton("X", new Color(0, 150, 0));
        divideButton = createButton("÷", new Color(0, 150, 0));
        decimalButton = createButton(".", new Color(0, 100, 200));
        equalButton = createButton("=", new Color(0, 200, 0));
        factorialButton = createButton("!", new Color(200, 0, 200));
        ansButton = createButton("ANS", new Color(200, 0, 200));

        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(divideButton);

        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);

        buttonPanel.add(decimalButton);
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(factorialButton);
        buttonPanel.add(ansButton);
        buttonPanel.add(equalButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String text, Color color)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            for (int i = 0; i < 10; i++)
            {
                if (e.getSource() == numberButtons[i])
                {
                    display.setText(display.getText().concat(String.valueOf(i)));
                }
            }

            if (e.getSource() == decimalButton)
            {
                if (!display.getText().contains("."))
                {
                    display.setText(display.getText().concat("."));
                }
            }

            if (e.getSource() == clearButton)
            {
                display.setText("");
                num1 = num2 = result = 0;
                operator = ' ';
            }

            if (e.getSource() == deleteButton)
            {
                String currentText = display.getText();
                if (!currentText.isEmpty())
                {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
            }

            if (e.getSource() == addButton || e.getSource() == subtractButton ||
                e.getSource() == multiplyButton || e.getSource() == divideButton)
            {
                if (!display.getText().isEmpty())
                {
                    num1 = Double.parseDouble(display.getText());
                    operator = e.getActionCommand().charAt(0);
                    display.setText("");
                }
            }

            if (e.getSource() == equalButton)
            {
                if (!display.getText().isEmpty())
                {
                    num2 = Double.parseDouble(display.getText());
                    switch (operator)
                    {
                        case '+':
                            result = num1 + num2;
                            break;
                        case '-':
                            result = num1 - num2;
                            break;
                        case 'X':
                            result = num1 * num2;
                            break;
                        case '÷':
                            if (num2 != 0)
                            {
                                result = num1 / num2;
                            }
                            else
                            {
                                display.setText("Error");
                                return;
                            }
                            break;
                    }
                    display.setText(formatResult(result));
                    ans = result;
                }
            }

            if (e.getSource() == ansButton)
            {
                display.setText(formatResult(ans));
            }

            if (e.getSource() == factorialButton)
            {
                if (!display.getText().isEmpty())
                {
                    int temp = Integer.parseInt(display.getText());
                    display.setText(String.valueOf(factorial(temp)));
                }
            }
        }
        catch (NumberFormatException ex)
        {
            display.setText("Error");
        }
        catch (Exception ex)
        {
            display.setText("Error");
        }
    }

    private String formatResult(double value)
    {
        if (value % 1 == 0)
        {
            return String.valueOf((int) value);
        }
        else
        {
            return String.format("%.3f", value);
        }
    }

    private int factorial(int n)
    {
        if (n < 0)
        {
            return -1;
        }
        if (n == 0)
        {
            return 1;
        }
        int result = 1;
        for (int i = 1; i <= n; i++)
        {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args)
    {
        Calculator calculator = new Calculator();
        calculator.setVisible(true);
    }
}
