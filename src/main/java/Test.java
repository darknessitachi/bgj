/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * GridBagLayoutDemo.java requires no other files.
 */

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Test {
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;

	public static void addComponentsToPane(Container pane) {
		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		JButton button;
		pane.setLayout(new GridBagLayout());

		for (int i = 0; i < 18; i++) {
			GridBagConstraints c = getConstraints(i % 6, i / 6);
			c.weightx = 0.5;
			c.anchor = GridBagConstraints.NORTHEAST;
			if (i % 2 == 0) {
				pane.add(getCompoment(i), c);
			} else {
				JTextField abc = new JTextField(10);
				pane.add(abc, c);
			}
		}

		// JLabel stockID = new JLabel("Stock ID");
		//
		// button = new JButton("5");
		// c.fill = GridBagConstraints.HORIZONTAL;
		// c.ipady = 0; // reset to default
		// c.weighty = 1.0; // request any extra vertical space
		// c.anchor = GridBagConstraints.PAGE_END; // bottom of space
		// c.insets = new Insets(10, 10, 10, 10); // top padding
		// c.gridx = 1; // aligned with button 2
		// c.gridwidth = 2; // 2 columns wide
		// c.gridy = 2; // third row
		// pane.add(button, c);

	}

	private static JComponent getCompoment(int i) {

		JComponent result = new JLabel("Button");
		if (i == 0) {
			result = new JButton("Button");
		}
		if (i == 1) {
			result = new JLabel("Button");
		}
		if (i == 2) {
			result = new JTextField("Button");
		}
		if (i == 3) {
			result = new JCheckBox("Button");
		}
		if (i == 4) {
			String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
			result = new JComboBox(petStrings);

			((JComboBox) result).setSelectedIndex(4);

		}
		if (i == 5) {
			result = new JButton("Button");
		}

		return result;
	}

	private static GridBagConstraints getConstraints(int gridx, int gridy) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 10, 5, 10); // top padding
		c.gridx = gridx; // aligned with button 2
		c.gridy = gridy; // third row
		return c;
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("GridBagLayoutDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel buttonPanel = new JPanel();
		
		JButton button = new JButton("Button 2");
		FlowLayout fl = new FlowLayout();
		buttonPanel.setLayout(fl);
		fl.setAlignment(FlowLayout.TRAILING);
		buttonPanel.add(new JButton("Button 1"));
		buttonPanel.add(new JButton("Button 2"));
		buttonPanel.add(new JButton("Button 3"));				
		buttonPanel
		.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		
		
		JPanel contentPanel = new JPanel();
		
		// Set up the content pane.
		addComponentsToPane(contentPanel);
		JPanel mainPanel = (JPanel)frame.getContentPane();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(buttonPanel, BorderLayout.NORTH);
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void constructMainPanel(Container mainContainer) {
		// Create and set up the window.
		JFrame frame = new JFrame("GridBagLayoutDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel buttonPanel = new JPanel();
		
		JButton button = new JButton("Button 2");
		FlowLayout fl = new FlowLayout();
		buttonPanel.setLayout(fl);
		fl.setAlignment(FlowLayout.TRAILING);
		buttonPanel.add(new JButton("Button 1"));
		buttonPanel.add(new JButton("Button 2"));
		buttonPanel.add(new JButton("Button 3"));				
		buttonPanel
		.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		
		
		JPanel contentPanel = new JPanel();
		
		// Set up the content pane.
		addComponentsToPane(contentPanel);
		JPanel mainPanel = (JPanel)frame.getContentPane();
		mainContainer.setLayout(new BorderLayout());
		mainContainer.add(buttonPanel, BorderLayout.NORTH);
		mainContainer.add(contentPanel, BorderLayout.CENTER);
		

	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
