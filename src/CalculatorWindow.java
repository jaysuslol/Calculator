import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CalculatorWindow extends JFrame {
    private JPanel mainPanel;
    private JLabel numbers;
    private JButton oneButton;
    private JButton twoButton;
    private JButton threeButton;
    private JButton zeroButton;
    private JButton fourButton;
    private JButton fiveButton;
    private JButton sixButton;
    private JButton sevenButton;
    private JButton eightButton;
    private JButton nineButton;
    private JButton minusButton;
    private JButton plusButton;
    private JButton divideButton;
    private JButton multiplyButton;
    private JButton clearButton;
    private JButton calcButton;
    private JButton pointButton;
    private JButton backButton;

    public CalculatorWindow(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 600);
        this.setContentPane(mainPanel);
        this.pack();

        zeroButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText(numbers.getText() + zeroButton.getText());
            }
        });

        oneButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText(numbers.getText() + oneButton.getText());
            }
        });

        twoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText(numbers.getText() + twoButton.getText());
            }
        });

        threeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText(numbers.getText() + threeButton.getText());
            }
        });

        fourButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText(numbers.getText() + fourButton.getText());
            }
        });

        fiveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText(numbers.getText() + fiveButton.getText());
            }
        });

        sixButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText(numbers.getText() + sixButton.getText());
            }
        });

        sevenButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText(numbers.getText() + sevenButton.getText());
            }
        });

        eightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText(numbers.getText() + eightButton.getText());
            }
        });

        nineButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText(numbers.getText() + nineButton.getText());
            }
        });

        divideButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText(numbers.getText() + divideButton.getText());
            }
        });

        multiplyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText(numbers.getText() + multiplyButton.getText());
            }
        });

        plusButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText(numbers.getText() + plusButton.getText());
            }
        });

        minusButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText(numbers.getText() + minusButton.getText());
            }
        });

        pointButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText(numbers.getText() + pointButton.getText());
            }
        });

        clearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText("");
            }
        });

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String text = numbers.getText();
                String newText = text.substring(0, text.length() - 1);

                numbers.setText(newText);
            }
        });

        calcButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                numbers.setText(numbers.getText() + " = " + eval(numbers.getText()));
            }
        });
    }

    public static double eval(final String str) {
        // Written by Boann (StackOverflow)
        // https://stackoverflow.com/users/964243/boann
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName `(` expression `)` | functionName factor
            //        | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    x = switch (func) {
                        case "sqrt" -> Math.sqrt(x);
                        case "sin" -> Math.sin(Math.toRadians(x));
                        case "cos" -> Math.cos(Math.toRadians(x));
                        case "tan" -> Math.tan(Math.toRadians(x));
                        default -> throw new RuntimeException("Unknown function: " + func);
                    };
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}
