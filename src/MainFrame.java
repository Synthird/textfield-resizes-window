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
	FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);

	JSpinner widthField, heightField;

	JCheckBox resizable;

	JPanel buttonPanel;
	JButton resizeButton, exitButton;

	int widthSize = 325, heightSize = 180;

	public MainFrame() {
		// Width panel
		JPanel widthPanel = setUpPanel(0);
		widthField = setUpSpinner(widthPanel, widthSize);
		widthPanel.add(new JLabel("px (Width)"));

		// Height panel
		JPanel heightPanel = setUpPanel(1);
		heightField = setUpSpinner(heightPanel, heightSize);
		heightPanel.add(new JLabel("px (Height)"));

		// Resizable checkbox
		resizable = setUpCheckBox("Resize with mouse and maximize/restore button", true);
		setUpPanel(2).add(resizable);

		// Buttons
		buttonPanel = setUpPanel(3);
		resizeButton = setUpButton("Resize");
		exitButton = setUpButton("Exit");

		// Window setup
		this.setTitle("Textfield resizes window");
		this.setIconImage((new ImageIcon("TextfieldResizesWindowIcon.png")).getImage());
		changeWindowSize();
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.addComponentListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void changeWindowSize() {
		this.setSize(widthSize, heightSize);
	}

	// Setting up GUIs that have the same properties

	private JSpinner setUpSpinner(JPanel panel, int initialValue) {
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(initialValue, 0, Integer.MAX_VALUE, 1));
		spinner.setPreferredSize(new Dimension(63, 26));
		spinner.addChangeListener(this);
		spinner.getEditor().getComponent(0).addKeyListener(this);
		panel.add(spinner);
		return spinner;
	}

	private JCheckBox setUpCheckBox(String text, boolean selected) {
		JCheckBox checkbox = new JCheckBox(text, selected);
		checkbox.setOpaque(false);
		checkbox.addActionListener(this);
		return checkbox;
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
		panel.setLayout(flowLayout);
		this.add(panel);
		return panel;
	}

	// Interface methods

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == resizeButton) {
			changeWindowSize();
		} else if (source == resizable) {
			this.setResizable(!this.isResizable());
		} else if (source == exitButton) {
			System.exit(0);
		}
	}

	@Override
	public void componentResized(ComponentEvent e) {
		widthField.setValue(this.getWidth());
		heightField.setValue(this.getHeight());
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
		Object source = e.getSource();

		if (source == widthField) {
			widthSize = (int) widthField.getValue();
		} else if (source == heightField) {
			heightSize = (int) heightField.getValue();
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
