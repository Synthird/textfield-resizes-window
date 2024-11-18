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
    private static int minWidth = 330;
    private static int minHeight = 180;

    FlowLayout flowLayout = new FlowLayout();

    JTextField xTextField;
    JTextField yTextField;
    JCheckBox resizable;

    JButton resizeButton;
    JButton exitButton;

    int widthSize;
    int heightSize;

    public MainFrame() {
        flowLayout.setAlignment(FlowLayout.LEFT);

        // Width panel
        JPanel widthPanel = new JPanel();
        widthPanel.setOpaque(false);
        widthPanel.setLayout(flowLayout);
        widthPanel.setBounds(0, 0, 250, 35);
        this.add(widthPanel);

        xTextField = new JTextField();
        xTextField.setPreferredSize(new Dimension(50, 26));
        xTextField.addActionListener(this);
        widthPanel.add(xTextField);

        JLabel xLabel = new JLabel(String.format("px (Width) Minimum is %s", minWidth));
        widthPanel.add(xLabel);

        // Height panel
        JPanel heightPanel = new JPanel();
        heightPanel.setOpaque(false);
        heightPanel.setLayout(flowLayout);
        heightPanel.setBounds(0, 35, 250, 35);
        this.add(heightPanel);

        yTextField = new JTextField();
        yTextField.setPreferredSize(new Dimension(50, 26));
        yTextField.addActionListener(this);
        heightPanel.add(yTextField);

        JLabel yLabel = new JLabel(String.format("px (Height) Minimum is %s", minHeight));
        heightPanel.add(yLabel);

        // Checkbox panel
        JPanel checkPanel = new JPanel();
        checkPanel.setBounds(0, 35 * 2, 310, 35);
        checkPanel.setOpaque(false);
        checkPanel.setLayout(flowLayout);
        this.add(checkPanel);

        resizable = new JCheckBox("Resizable via mouse and maximize/restore button");
        resizable.setFocusable(false);
        resizable.setOpaque(false);
        resizable.addActionListener(this);
        checkPanel.add(resizable);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(flowLayout);
        buttonPanel.setBounds(0, 35 * 3, 250, 35);
        this.add(buttonPanel);

        resizeButton = new JButton("Resize");
        resizeButton.setFocusable(false);
        resizeButton.addActionListener(this);
        buttonPanel.add(resizeButton);

        exitButton = new JButton("Exit");
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);

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
        if (e.getSource() == resizeButton || e.getSource() == xTextField || e.getSource() == yTextField) {
            try {
                widthSize = Integer.parseInt(xTextField.getText().replaceAll("\\s", ""));
                heightSize = Integer.parseInt(yTextField.getText().replaceAll("\\s", ""));

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
}
