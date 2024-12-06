import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame implements ActionListener {
    int minWidth = 330;
    int minHeight = 180;

    FlowLayout flowLayout = new FlowLayout();

    JTextField widthTextField;
    JTextField heightTextField;

    JCheckBox resizable;

    JPanel buttonPanel;
    JButton resizeButton;
    JButton exitButton;

    int widthSize;
    int heightSize;

    public MainFrame() {
        flowLayout.setAlignment(FlowLayout.LEFT);

        // Width panel
        JPanel widthPanel = new JPanel();
        setUpPanel(widthPanel, 0);

        widthTextField = new JTextField();
        setUpTextField(widthTextField, widthPanel);

        JLabel widthLabel = new JLabel(String.format("px (Width) Minimum is %s", minWidth));
        widthPanel.add(widthLabel);

        // Height panel
        JPanel heightPanel = new JPanel();
        setUpPanel(heightPanel, 1);

        heightTextField = new JTextField();
        setUpTextField(heightTextField, heightPanel);

        JLabel heightLabel = new JLabel(String.format("px (Height) Minimum is %s", minHeight));
        heightPanel.add(heightLabel);

        // Checkbox panel
        JPanel checkPanel = new JPanel();
        setUpPanel(checkPanel, 2);

        resizable = new JCheckBox("Resizable via mouse and maximize/restore button");
        resizable.setFocusable(false);
        resizable.setOpaque(false);
        resizable.addActionListener(this);
        checkPanel.add(resizable);

        // Button panel
        buttonPanel = new JPanel();
        setUpPanel(buttonPanel, 3);

        resizeButton = setUpButton("Resize");
        exitButton = setUpButton("Exit");

        // Window set up
        this.setTitle("Textfield resizes window");
        this.setSize(minWidth, minHeight);
        this.setMinimumSize(this.getSize());
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resizeButton || e.getSource() == widthTextField || e.getSource() == heightTextField) {
            try {
                widthSize = Integer.parseInt(widthTextField.getText().replaceAll("\\s", ""));
                heightSize = Integer.parseInt(heightTextField.getText().replaceAll("\\s", ""));

                this.setSize(widthSize, heightSize);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null,
                        String.format(
                                "Textfields/textboxes cannot be empty! They also cannot contain any letters, decimals, symbols, or a number larger than %s!",
                                Integer.MAX_VALUE),
                        "Cannot resize", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == resizable) {
            this.setResizable(!this.isResizable());
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    // Setting up GUIs that have the same properties

    private void setUpTextField(JTextField textField, JPanel panel) {
        textField.setPreferredSize(new Dimension(50, 26));
        textField.addActionListener(this);
        panel.add(textField);
    }

    private JButton setUpButton(String text) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.addActionListener(this);
        buttonPanel.add(button);
        return button;
    }

    private void setUpPanel(JPanel panel, int level) {
        panel.setOpaque(false);
        panel.setBounds(0, 35 * level, 250, 35);
        panel.setLayout(flowLayout);
        this.add(panel);
    }
}
