import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gui extends JFrame implements ActionListener {
    private JTextField result = new JTextField();
    static String[] buttonString = { "Sqrt", "Cbrt", "abs", "log", "Del", "C", "sin", "cos", "tan", "asin", "acos", "atan", "^", "^2", "10^", "1/", "(", ")", "7", "8", "9", "e", "/", "*", "4", "5", "6", "pi", "-", "+", "1", "2", "3", "0", ".", "=" };

    public void buttons(int x, int y, int w, JButton button, JPanel pan, GridBagConstraints constr) {
        constr.gridx = x;
        constr.gridy = y;
        constr.gridwidth = w;
        constr.fill = GridBagConstraints.HORIZONTAL;
        pan.add(button, constr);
        button.setActionCommand(button.getText());
        button.addActionListener(this);
    }

    public Gui() {
        setTitle("Calculator");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        result.setEditable(false);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        textArea(0, 0, 6, result, panel, constraints);
        for (int k = 0; k < buttonString.length; k++) {
            JButton button = new JButton(buttonString[k]);
            buttons(k % 6, k / 6 + 1, 1, button, panel, constraints);
        }
        add(panel);
    }

    public static void textArea(int x, int y, int w, JTextField field, JPanel pan, GridBagConstraints constr) {
        constr.gridx = x;
        constr.gridy = y;
        constr.gridwidth = w;
        constr.fill = GridBagConstraints.HORIZONTAL;
        pan.add(field, constr);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        result.setText(result.getText() + e.getActionCommand());
        if ("C".equals(e.getActionCommand())) result.setText("");
        if ("Del".equals(e.getActionCommand())) result.setText(result.getText().substring(0, result.getText().length() - 4));
        if ("=".equals(e.getActionCommand())) {
            try {
                result.setText("" + BasicFunctions.eval(result.getText()));
            } catch (Exception e1) {
                result.setText("Error");
            }
        }
    }
    public static void main(String[] args) {
        Gui gui = new Gui();
        gui.setVisible(true);
    }
}