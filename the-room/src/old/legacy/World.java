package old.legacy;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import old.legacy.scenes.WorldScene;

public class World {

	private Map<String, WorldScene> scenes;
	private WorldScene currentScene;

	private Image image;

	private JLabel imageScreen;
	private JLabel textScreen;
	private JTextField inputField;
	private JButton doButton;
	private JFrame frame;

	private static World world;
	public static World instance() {
		if(world == null)
			world = new World();
		return world;
	}

	public World() {
		imageScreen = new JLabel();
		imageScreen.setOpaque(true);
		imageScreen.setBackground(Color.BLACK);

		textScreen = new JLabel();
		textScreen.setOpaque(true);
		textScreen.setBackground(Color.RED);

		inputField = new JTextField();
		doButton = new JButton("DO");

		frame = new JFrame("Escape Game");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 40;
		gbc.gridheight = 100;
		gbc.weightx = gbc.gridwidth;
		gbc.weighty = gbc.gridheight;
		frame.add(imageScreen, gbc);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy += gbc.gridheight;
		gbc.gridheight = 1;
		gbc.weightx = gbc.gridwidth;
		gbc.weighty = gbc.gridheight;
		frame.add(textScreen, gbc);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy += gbc.gridheight;
		gbc.gridwidth--;
		gbc.gridheight = 1;
		gbc.weightx = gbc.gridwidth;
		gbc.weighty = gbc.gridheight;
		frame.add(inputField, gbc);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = gbc.gridwidth;
		gbc.weighty = gbc.gridheight;
		frame.add(doButton, gbc);

		imageScreen.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				setImage(image);
			}
		});
		inputField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\n')
					doButton.doClick();
			}
		});
		doButton.addActionListener(a -> {
			perform(inputField.getText());
		});
		frame.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				int size = e.getComponent().getHeight() / 20;

				Font oldFont = textScreen.getFont();
				textScreen.setFont(new Font(oldFont.getFontName(), oldFont.getStyle(), size));

				inputField.getFont();
				inputField.setFont(new Font(oldFont.getFontName(), oldFont.getStyle(), size));

				doButton.getFont();
				doButton.setFont(new Font(oldFont.getFontName(), oldFont.getStyle(), size));
			}
		});

		frame.setVisible(true);
	}

	public List<WorldScene> getScenes() {
		return new ArrayList<>(scenes.values());
	}
	public World addScene(WorldScene scene) {
		Objects.requireNonNull(scene, "Scene cannot be null");
		scenes.put(scene.getName(), scene);
		return this;
	}
	public World removeScene(String name) {
		scenes.remove(name);
		return this;
	}

	public WorldScene getCurrentScene() {
		return currentScene;
	}
	public World setCurrentScene(String name) {
		if(!scenes.containsKey(name))
			throw new IllegalArgumentException("No such scene: " + name);
		currentScene.onExit();
		currentScene = scenes.get(name);
		currentScene.onEntry();
		return  this;
	}

	public Image getImage() {
		return image;
	}
	public World setImage(Image image) {
		this.image = image;
		if(image != null) {
			int w = imageScreen.getWidth(), h = imageScreen.getHeight();
			w = w <= 0 ? 1 : w;
			h = h <= 0 ? 1 : h;
			imageScreen.setIcon(new ImageIcon(image.getScaledInstance(w, h, Image.SCALE_DEFAULT)));
		} else
			imageScreen.setIcon(null);
		return this;
	}

	public String getDialogue() {
		return textScreen.getText();
	}
	public World setDialogue(String dialogue) {
		textScreen.setText(dialogue);
		return this;
	}
	
	public String getInputText() {
		return inputField.getText();
	}
	public World setInputText(String text) {
		inputField.setText(text);
		return this;
	}

	public JLabel getImageScreen() {
		return imageScreen;
	}
	public JLabel getTextScreen() {
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
	
	public World refresh() {
		Objects.requireNonNull(currentScene, "CurrentScene is not yet initialized");
		setImage(currentScene.getLocalImage());
		return this;
	}
	public World perform(String command) {
		Objects.requireNonNull(currentScene, "CurrentScene is not yet initialized");
		currentScene.perform(command);
		return this;
	}

	public static void main(String[] args) throws IOException {
		instance().setDialogue("What will you do?").setImage(ImageIO.read(new File("res/Q4N.png")));
	}
}
