package old;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class World extends DynamicConsumer<CommandInfo> {

	private JLabel imageScreen;
	private JTextArea textScreen;
	private JTextField inputField;
	private JButton doButton;
	private JFrame frame;
	private JScrollPane textScreenScrollPane;
	private JSplitPane screenSplitPane;
	private JPanel bottomPane;

	private Image displayedImage;
	private String displayedLog;

	private volatile boolean block;
	private volatile String answer;

	public static final World UI = new World();

	private World() {
		imageScreen = new JLabel();
		imageScreen.setOpaque(true);
		imageScreen.setBackground(Color.BLACK);

		textScreen = new JTextArea();
		textScreen.setOpaque(true);
		textScreen.setBackground(Color.DARK_GRAY);
		textScreen.setForeground(Color.WHITE);
		textScreen.setText("  ");
		textScreen.setEditable(false);

		inputField = new JTextField();
		doButton = new JButton("DO");

		textScreenScrollPane = new JScrollPane(textScreen);

		screenSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, imageScreen, textScreenScrollPane);

		bottomPane = new JPanel(new BorderLayout());
		bottomPane.add(inputField, BorderLayout.CENTER);
		bottomPane.add(doButton, BorderLayout.EAST);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame = new JFrame("Escape Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize((int) (screenSize.width * 0.9), (int) (screenSize.height * 0.9));
		frame.setLayout(new BorderLayout());
		frame.add(screenSplitPane, BorderLayout.CENTER);
		frame.add(bottomPane, BorderLayout.SOUTH);

		imageScreen.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				display(displayedImage);
			}
		});
		inputField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\n')
					getDoButton().doClick();
			}
		});
		doButton.addActionListener(a -> {
			if(!block) {
				List<CommandInfo> cmds = TextAnalyzer.analyze(textScreen.getText());
				inputField.setText(null);
				for(CommandInfo cmd : cmds) {
					try {
						accept(cmd);
					} catch(WorldSceneAbortException e) {
						e.run();
						break;
					} catch(WorldSceneSkipException e) {
						e.run();
						continue;
					}
				}
			} else {
				answer = textScreen.getText();
				block = false;
			}
		});

		frame.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				int size = e.getComponent().getHeight() / 20;

				Font oldFont = textScreen.getFont();
				getTextScreen().setFont(new Font(oldFont.getFontName(), oldFont.getStyle(), size));

				getInputField().getFont();
				getInputField().setFont(new Font(oldFont.getFontName(), oldFont.getStyle(), size));

				getDoButton().getFont();
				getDoButton().setFont(new Font(oldFont.getFontName(), oldFont.getStyle(), size));
			}
		});

		screenSplitPane.setOneTouchExpandable(true);
		frame.setVisible(true);
	}

	// UI getters
	public JLabel getImageScreen() {
		return imageScreen;
	}
	public JTextArea getTextScreen() {
		return textScreen;
	}
	public JTextField getInputField() {
		return inputField;
	}
	public JButton getDoButton() {
		return doButton;
	}
	public JFrame getFrame() {
		return frame;
	}
	public JScrollPane getTextScreenScrollPane() {
		return textScreenScrollPane;
	}
	public JSplitPane getScreenSplitPane() {
		return screenSplitPane;
	}
	public JPanel getBottomPane() {
		return bottomPane;
	}

	// State getters
	public Image getDisplayedImage() {
		return displayedImage;
	}
	public String getDisplayedLog() {
		return displayedLog;
	}

	// Utility functions
	private void _log(String msg) {
		String entire = textScreen.getText();
		textScreen.setText((displayedLog == null ? "" : (entire + "\n")) + "> " + msg);	
		displayedLog = msg;
	}
	private void _pause() {
		block = true;
		while(block);
	}
	private void _display(Image img) {
		this.displayedImage = img;
		if(img != null) {
			int w = imageScreen.getWidth(), h = imageScreen.getHeight();
			w = w <= 0 ? 1 : w;
			h = h <= 0 ? 1 : h;
			imageScreen.setIcon(new ImageIcon(img.getScaledInstance(w, h, Image.SCALE_DEFAULT)));
		} else
			imageScreen.setIcon(null);
	}
	private void _say(String... msgs) {
		if(msgs.length == 1)
			_log(msgs[0]);
		else if(msgs.length > 1) {
			doButton.setText("NXT");
			for(String msg : msgs) {
				if(msg != null)
					_log(msg);
				_pause();
			}
			doButton.setText("DO");
			/*for(int i = 0; i < msgs.length - 1; i++) {
				String msg = msgs[i];
				if(msg != null)
					_log(msg);
				_pause();
			}
			_log(msgs[msgs.length - 1]);*/
		}
	}
	private void _ask(String msg, Consumer<String> handler) {
		_log(msg);
		_pause();
		handler.accept(answer);
	}
	private <T extends Consumer<CommandInfo>> void _goTo(Class<T> clz) {
		String name = clz.getSimpleName();
		if(get(name) == null) {
			try {
				put(name, clz.newInstance());
			} catch (Exception e) {
				throw new RuntimeException("Empty constructor may be not available or private", e);
			}
		}
		if(current() != null && current() instanceof WorldScene)
			((WorldScene) current()).finalization();
		current(name);
		if(current() != null && current() instanceof WorldScene)
			((WorldScene) current()).initialization();
	}

	public static void log(String msg) {
		World.UI._log(msg);
	}
	public static void pause() {
		World.UI._pause();
	}
	public static void display(Image img) {
		World.UI._display(img);
	}
	public static void say(String... msgs) {
		World.UI._say(msgs);
	}
	public static void ask(String msg, Consumer<String> handler) {
		World.UI._ask(msg, handler);
	}
	public static <T extends Consumer<CommandInfo>> void goTo(Class<T> clz) {
		World.UI._goTo(clz);
	}
}
