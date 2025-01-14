import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame implements ActionListener, ComponentListener {
	FlowLayout flowLayout = new FlowLayout();

	JTextField widthTextField, heightTextField;

	JCheckBox resizable;

	JPanel buttonPanel;
	JButton resizeButton, exitButton;

	int widthSize, heightSize;

	public MainFrame() {
		flowLayout.setAlignment(FlowLayout.LEFT);

		// Width panel
		JPanel widthPanel = setUpPanel(0);
		widthTextField = setUpTextField(widthPanel);

		JLabel widthLabel = new JLabel("px (Width)");
		widthPanel.add(widthLabel);

		// Height panel
		JPanel heightPanel = setUpPanel(1);
		heightTextField = setUpTextField(heightPanel);

		JLabel heightLabel = new JLabel("px (Height)");
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
		this.setSize(325, 180);
		this.setMinimumSize(this.getSize());
		this.setResizable(false);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.addComponentListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == resizeButton || e.getSource() == widthTextField || e.getSource() == heightTextField) {
			try {
				widthSize = Integer.parseInt(widthTextField.getText().replaceAll("\\s", ""));
				heightSize = Integer.parseInt(heightTextField.getText().replaceAll("\\s", ""));
			} catch (NumberFormatException exception) {
				JOptionPane.showMessageDialog(null, String.format(
						"Empty textfields/textboxes, letters, decimals, symbols, or numbers larger than %s are not allowed!",
						Integer.MAX_VALUE), "CANNOT RESIZE!", JOptionPane.ERROR_MESSAGE);
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

	@Override
	public void componentResized(ComponentEvent e) {
		Dimension windowSize = this.getSize();

		widthTextField.setText(String.valueOf(windowSize.width));
		heightTextField.setText(String.valueOf(windowSize.height));
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}
}
