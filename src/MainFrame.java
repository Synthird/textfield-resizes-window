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
	final int minWidth = 394;
	final int minHeight = 180;

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
		JPanel widthPanel = setUpPanel(0);
		widthTextField = setUpTextField(widthPanel);

		JLabel widthLabel = new JLabel(showMinAndMaxLength("Width", minWidth));
		widthPanel.add(widthLabel);

		// Height panel
		JPanel heightPanel = setUpPanel(1);
		heightTextField = setUpTextField(heightPanel);

		JLabel heightLabel = new JLabel(showMinAndMaxLength("Height", minHeight));
		heightPanel.add(heightLabel);

		// Checkbox
		JPanel checkPanel = setUpPanel(2);

		resizable = new JCheckBox("Resize with mouse and maximize/restore button");
		resizable.setFocusable(false);
		resizable.setOpaque(false);
		resizable.addActionListener(this);
		checkPanel.add(resizable);

		// Buttons
		buttonPanel = setUpPanel(3);

		resizeButton = setUpButton("Resize");
		exitButton = setUpButton("Exit");

		// Window setup
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
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(null, String.format(
						"Empty textfields/textboxes, letters, decimals, symbols, or numbers larger than %s are not allowed!",
						Integer.MAX_VALUE), "CANNOT RESIZE", JOptionPane.ERROR_MESSAGE);
			} finally {
				this.setSize(widthSize, heightSize);
			}
		} else if (e.getSource() == resizable) {
			this.setResizable(!this.isResizable());
		} else if (e.getSource() == exitButton) {
			System.exit(0);
		}
	}

	// Setting up GUIs that have the same properties

	private JTextField setUpTextField(JPanel panel) {
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(50, 26));
		textField.addActionListener(this);
		panel.add(textField);
		return textField;
	}

	private JButton setUpButton(String text) {
		JButton button = new JButton(text);
		button.setFocusable(false);
		button.addActionListener(this);
		buttonPanel.add(button);
		return button;
	}

	private JPanel setUpPanel(int level) {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 35 * level, 385, 35);
		panel.setLayout(flowLayout);
		this.add(panel);
		return panel;
	}

	private String showMinAndMaxLength(String length, int minimum) {
		return String.format("px (%s) | minimum is %s, maximum is %s", length, minimum, Integer.MAX_VALUE);
	}
}
