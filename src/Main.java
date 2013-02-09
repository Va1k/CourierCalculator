import javax.swing.*;
import java.awt.event.*;
import java.text.*;
import java.util.Locale;

public class Main extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtLength;
    private JTextField txtWidth;
    private JTextField txtHeight;
    private JRadioButton radioStandard;
    private JRadioButton radioExpress;
    private JTextField txtResult;
    private JLabel labelBulky;
    private JTextField txtWeight;

    public Main() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
    // Definitions :D
        String width = txtWidth.getText();
        String height = txtHeight.getText();
        String length = txtLength.getText();
        String weight = txtWeight.getText();
        Double result = 0.00;
        Double price = 0.00;
    // Check everything is filled out and is actually a number GG Data Verification
        if (!width.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
            JOptionPane.showMessageDialog(null, "Please type in a *valid* width.", "Alert", JOptionPane.ERROR_MESSAGE);
            return;
        }   else if (!height.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
            JOptionPane.showMessageDialog(null, "Please type in a *valid* height.", "Alert", JOptionPane.ERROR_MESSAGE);
            return;
        }   else if (!length.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
            JOptionPane.showMessageDialog(null, "Please type in a *valid* length.", "Alert", JOptionPane.ERROR_MESSAGE);
            return;
        }   else if (!weight.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
            JOptionPane.showMessageDialog(null, "Please type in a *valid* weight.", "Alert", JOptionPane.ERROR_MESSAGE);
            return;
        }
    // Convert everything into numbers so we can do math
        double dwidth = Double.parseDouble(width);
        double dheight = Double.parseDouble(height);
        double dlength = Double.parseDouble(length);
        double dweight = Double.parseDouble(weight);
        double sum = (dwidth + dheight + dlength); // Sums the object's size so we know it later

    // Let's get down to business!
        // Express or standard?
        if (radioExpress.isSelected()) {
            price = 0.0065;
        } else {
            price = 0.0042;
        }
        // Do the math
        result = (price * dweight);

        // But what if it's bulky!!
        if (sum > 800) {
            result = ((result * 0.2) + result);
            labelBulky.setVisible(true); // Sets the bulky message to visible :)
        }
        if (sum < 800) labelBulky.setVisible(false); // What if it's not bulky~~~~!!!!
        // Output the result
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);  // Formats the result into a cute currency :3
        String resultS = n.format(result);
        txtResult.setText(resultS);
    }

    private void onCancel() {
// Rage quits
        dispose();
    }

    public static void main(String[] args) {     // Starts everything
        Main dialog = new Main();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
