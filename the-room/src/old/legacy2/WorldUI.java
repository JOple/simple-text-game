package old.legacy2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WorldUI implements WorldContext {

	private WorldModel model;

	private JLabel imageScreen;
	private JLabel textScreen;
	private JTextField inputField;
	private JButton doButton;
	private JFrame frame;

	public WorldUI() {
		model = new WorldModel();

		imageScreen = new JLabel();
		imageScreen.setOpaque(true);
		imageScreen.setBackground(Color.BLACK);

		textScreen = new JLabel();
		textScreen.setOpaque(true);
		textScreen.setBackground(Color.DARK_GRAY);
		textScreen.setForeground(Color.WHITE);
		textScreen.setText("  ");

		inputField = new JTextField();
		doButton = new JButton("DO");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame = new JFrame("Escape Game");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize((int) (screenSize.width * 0.9), (int) (screenSize.height * 0.9));
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

		gbc.gridy += gbc.gridheight;
		gbc.gridheight = 1;
		gbc.weightx = gbc.gridwidth;
		gbc.weighty = gbc.gridheight;
		frame.add(textScreen, gbc);

		gbc.gridy += gbc.gridheight;
		gbc.gridwidth--;
		gbc.gridheight = 1;
		gbc.weightx = gbc.gridwidth;
		gbc.weighty = gbc.gridheight;
		frame.add(inputField, gbc);

		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = gbc.gridwidth;
		gbc.weighty = gbc.gridheight;
		frame.add(doButton, gbc);

		imageScreen.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				displayImage(model.getDisplayedImage());
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

	private void displayImage(Image image) {
		if(image != null) {
			int w = imageScreen.getWidth(), h = imageScreen.getHeight();
			w = w <= 0 ? 1 : w;
			h = h <= 0 ? 1 : h;
			imageScreen.setIcon(new ImageIcon(image.getScaledInstance(w, h, Image.SCALE_DEFAULT)));
		} else
			imageScreen.setIcon(null);
	}
	private void displayDialogue(String dialogue) {
		textScreen.setText(dialogue);
	}
	private void perform(String command) {
		if(model.getCurrentScene() != null)
			model.getCurrentScene().perform(command, this);
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

	@Override
	public Map<String, Object> getInventory() {
		return model.getInventory();
	}

	@Override
	public List<String> getSceneIDs() {
		return model.getSceneIDs();
	}
	@Override
	public Scene<WorldContext> getScene(String id) {
		return model.getScene(id);
	}
	@Override
	public void addScene(Scene<WorldContext> scene) {
		Objects.requireNonNull(scene, "Scene cannot be null");
		model.addScene(scene);
	}
	@Override
	public void delScene(String id) {
		model.delScene(id);
	}

	@Override
	public Scene<WorldContext> getCurrentScene() {
		return model.getCurrentScene();
	}
	@Override
	public void setCurrentScene(String id) {
		if(model.getCurrentScene() != null)
			model.getCurrentScene().onSceneExit(this);

		model.setCurrentScene(id);

		if(model.getCurrentScene() == null)
			throw new IllegalArgumentException("Scene does not exist");

		model.getCurrentScene().onSceneEntry(this);
	}

	@Override
	public int getGlobalActionCount() {
		return model.getGlobalActionCount();
	}
	@Override
	public SceneAction<WorldContext> getGlobalAction(int index) {
		return model.getGlobalAction(index);
	}
	@Override
	public void addGlobalAction(SceneAction<WorldContext> action) {
		model.addGlobalAction(action);
	}
	@Override
	public void addGlobalAction(int index, SceneAction<WorldContext> action) {
		model.addGlobalAction(index, action);
	}
	@Override
	public void delGlobalAction(int index) {
		model.delGlobalAction(index);
	}

	@Override
	public BiConsumer<String, WorldContext> getGlobalDefaultAction() {
		return model.getGlobalDefaultAction();
	}
	@Override
	public void setGlobalDefaultAction(BiConsumer<String, WorldContext> globalDefaultAction) {
		model.setGlobalDefaultAction(globalDefaultAction);
	}

	@Override
	public Image getDisplayedImage() {
		return model.getDisplayedImage();
	}
	@Override
	public void setDisplayedImage(Image displayedImage) {
		model.setDisplayedImage(displayedImage);
		CompletableFuture.runAsync(() -> displayImage(displayedImage));
	}

	@Override
	public String getDisplayedDialogue() {
		return model.getDisplayedDialogue();
	}
	@Override
	public void setDisplayedDialogue(String displayedDialogue) {
		model.setDisplayedDialogue(displayedDialogue);
		displayDialogue("  " + displayedDialogue);
	}

	@Override
	public String getInputText() {
		model.setInputText(inputField.getText());
		return model.getInputText();
	}
	@Override
	public void setInputText(String inputText) {
		model.setInputText(inputText);
		inputField.setText(model.getInputText());
	}
}
