package com.droom.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.droom.world.World;

public class GameUI implements World.CommandActuator {

	private JFrame frame;
	private JLabel image;
	private JPanel logs;
	private JTextField input;

	private Image shown;

	public GameUI() {
		image = new JLabel();
		image.setBackground(Color.BLACK);

		logs = new JPanel(new GridLayout(0, 1));

		JScrollPane scrollLog = new JScrollPane(logs);
		scrollLog.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollLog.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		input = new JTextField();

		JPanel sidePanel = new JPanel(new BorderLayout());
		sidePanel.setMinimumSize(new Dimension(300, 100));
		sidePanel.add(scrollLog, BorderLayout.CENTER);
		sidePanel.add(input, BorderLayout.SOUTH);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, image, sidePanel);

		frame = new JFrame("The Room");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(splitPane, BorderLayout.CENTER);

		image.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				show(shown);
			}
		});
		input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == '\n') {
					String txt = input.getText();
					if(txt != null) {
						if(txt.length() > 1)
							logRight(txt);
						GameUI.this.input(txt);
						input.setText(null);
					}
				}
			}
		});
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				splitPane.setDividerLocation(0.8);
			}
		});

		frame.setSize(300, 300);
		frame.setVisible(true);

		say("> Game Begin");
	}

	private void logLeft(String msg) {
		JLabel box = new JLabel("> " + msg);
		box.setMinimumSize(new Dimension(100, 40));
		box.setMaximumSize(new Dimension(100, 40));

		logs.add(box);
		logs.repaint();
		logs.revalidate();
	}
	private void logRight(String msg) {
		JLabel box = new JLabel(msg + " <");
		box.setHorizontalAlignment(SwingConstants.RIGHT);
		box.setMinimumSize(new Dimension(100, 40));
		box.setMaximumSize(new Dimension(100, 40));

		logs.add(box);
		logs.repaint();
		logs.revalidate();
	}

	@Override
	public void say(String... msgs) {
		for(String msg : msgs)
			logLeft(msg);
	}
	@Override
	public void ask(String msg, Consumer<String> handler) {
		throw new UnsupportedOperationException("No implementation of ask method");
	}
	@Override
	public void show(Image img) {
		shown = img;
		if(img == null) {
			image.setIcon(null);
			return;
		}
		ImageIcon icon = new ImageIcon(img);
		double boxW = image.getWidth(), boxH = image.getHeight(), imgW = icon.getIconWidth(), imgH = icon.getIconHeight();
		double boxRatio = boxW / boxH, imgRatio = imgW / imgH;
		double nW = imgW, nH = imgH;
		if(boxRatio < imgRatio) {
			nW = boxW;
			nH = boxW / imgRatio;
		} else {
			nW = boxH * imgRatio;
			nH = boxH;
		}
		nW = nW < 1 ? 1 : nW;
		nH = nH < 1 ? 1 : nH;
		icon = new ImageIcon(icon.getImage().getScaledInstance((int) nW, (int) nH, Image.SCALE_FAST));
		image.setIcon(icon);
	}
}
