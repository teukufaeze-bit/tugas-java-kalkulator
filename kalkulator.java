import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class kalkulator extends JFrame implements ActionListener {

    JTextField display;
    String operator = "";
    double angka1 = 0;

    public kalkulator() {
        setTitle("Kalkulator Java");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));

        String[] tombol = {
            "7","8","9","/",
            "4","5","6","*",
            "1","2","3","-",
            "C","0","=","+"
        };

        for (String t : tombol) {
            JButton btn = new JButton(t);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if (input.matches("[0-9]")) {
            display.setText(display.getText() + input);
        } else if (input.matches("[+\\-*/]")) {
            if (!display.getText().isEmpty()) {
                angka1 = Double.parseDouble(display.getText());
                operator = input;
                display.setText("");
            }
        } else if (input.equals("=")) {
            if (display.getText().isEmpty() || operator.isEmpty()) return;

            double angka2 = Double.parseDouble(display.getText());
            double hasil = 0;

            switch (operator) {
                case "+": hasil = angka1 + angka2; break;
                case "-": hasil = angka1 - angka2; break;
                case "*": hasil = angka1 * angka2; break;
                case "/":
                    if (angka2 != 0) hasil = angka1 / angka2;
                    else { display.setText("Error"); return; }
                    break;
            }
            display.setText(String.valueOf(hasil));
            operator = "";
        } else if (input.equals("C")) {
            display.setText("");
            angka1 = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        new kalkulator();
    }
}
