import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainFrame extends JFrame implements ActionListener, ComponentListener, ChangeListener, KeyListener {
	JSpinner widthTextField, heightTextField;

	JCheckBox resizable;

	JPanel buttonPanel;
	JButton resizeButton, exitButton;

	int widthSize = 325, heightSize = 180;

	public MainFrame() {
		// Width panel
		JPanel widthPanel = setUpPanel(0);
		widthTextField = setUpSpinner(widthPanel, widthSize);
		widthPanel.add(new JLabel("px (Width)"));

		// Height panel
		JPanel heightPanel = setUpPanel(1);
		heightTextField = setUpSpinner(heightPanel, heightSize);
		heightPanel.add(new JLabel("px (Height)"));

		// Checkbox
		JPanel checkPanel = setUpPanel(2);
		resizable = new JCheckBox("Resize with mouse and maximize/restore button", true);
		resizable.setOpaque(false);
		resizable.addActionListener(this);
		checkPanel.add(resizable);

		// Buttons
		buttonPanel = setUpPanel(3);
		resizeButton = setUpButton("Resize");
		exitButton = setUpButton("Exit");

		// Window setup
		ImageIcon imageIcon = new ImageIcon("TextfieldResizesWindowIcon.png");

		this.setTitle("Textfield resizes window");
		this.setIconImage(imageIcon.getImage());
		changeWindowSize();
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.addComponentListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == resizeButton) {
			changeWindowSize();
		} else if (e.getSource() == resizable) {
			this.setResizable(!this.isResizable());
		} else if (e.getSource() == exitButton) {
			System.exit(0);
		}
	}

	private void changeWindowSize() {
		this.setSize(widthSize, heightSize);
	}

	// Setting up GUIs that have the same properties

	private JSpinner setUpSpinner(JPanel panel, int initialValue) {
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(initialValue, 0, Integer.MAX_VALUE, 1));
		spinner.setPreferredSize(new Dimension(50, 26));
		spinner.addChangeListener(this);
		spinner.getEditor().getComponent(0).addKeyListener(this);
		panel.add(spinner);
		return spinner;
	}

	private JButton setUpButton(String text) {
		JButton button = new JButton(text);
		button.addActionListener(this);
		buttonPanel.add(button);
		return button;
	}

	private JPanel setUpPanel(int level) {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 35 * level, 385, 35);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(panel);
		return panel;
	}

	// Interface methods

	@Override
	public void componentResized(ComponentEvent e) {
		widthTextField.setValue(this.getWidth());
		heightTextField.setValue(this.getHeight());
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

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == widthTextField) {
			widthSize = (int) widthTextField.getValue();
		} else if (e.getSource() == heightTextField) {
			heightSize = (int) heightTextField.getValue();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 10) {
			changeWindowSize();
		}
	}
}
