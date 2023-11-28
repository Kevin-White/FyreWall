import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;

class LevelSelect extends JFrame {
    private static final long serialVersionUID = 1L;
    private FyreWall fyreWall;
    // Add more buttons as needed...

    public LevelSelect() {
    	JPanel levelPanel = new JPanel(new GridBagLayout());
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a panel for the back button with a FlowLayout
        GridBagConstraints gbc = new GridBagConstraints(); // Create a GridBagConstraints object

        JButton tutorialButton = new JButton("Tutorial");
        JButton backButton = new JButton("<-");
        JButton level1Button = new JButton("Level 1");
        JButton level2Button = new JButton("Level 2");
        JButton level3Button = new JButton("Level 3");
        JButton level4Button = new JButton("Level 4");
        JButton level5Button = new JButton("Level 5");
        JButton level6Button = new JButton("Level 6");
        JButton level7Button = new JButton("Level 7");
        JButton level8Button = new JButton("Level 8");
        JButton level9Button = new JButton("Level 9");
        JButton level10Button = new JButton("Level 10");



        
        ImageIcon background =  new ImageIcon("menuImages/background.png");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(screenSize)); // Set to your preferred size
        layeredPane.setLayout(null);

        Image img = background.getImage() ;  
        Image newimg = img.getScaledInstance(screenSize.width, screenSize.height,  java.awt.Image.SCALE_SMOOTH ) ; 
        background = new ImageIcon(newimg);
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, screenSize.width, screenSize.height); // Set bounds to match the layeredPane size
        
        
    	backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	fyreWall = new FyreWall();

                // Use SwingUtilities.invokeLater() to ensure LevelSelect is ready
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        // Dispose the current JFrame here
                    	LevelSelect.this.dispose();
                    }
                });
            }
        });
    	
    	
        // Initialize level selection menu
    	tutorialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
            		initTutorialButton();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	layeredPane.setVisible(false);
                dispose(); // Dispose the frame
                setUndecorated(true); // Now you can set undecorated state
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setVisible(true); // Make the frame visible again
                levelPanel.requestFocusInWindow(); // Request focus on the levelPanel
            }
        });

    	level1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					initLevel1();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                layeredPane.setVisible(false);
                dispose(); // Dispose the frame
                setUndecorated(true); // Now you can set undecorated state
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setVisible(true); // Make the frame visible again
            }
        });

        level2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					initLevel2();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	layeredPane.setVisible(false);
                dispose(); // Dispose the frame
                setUndecorated(true); // Now you can set undecorated state
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setVisible(true); // Make the frame visible again
            }
        });
        
        level3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					initLevel3();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	layeredPane.setVisible(false);
                dispose(); // Dispose the frame
                setUndecorated(true); // Now you can set undecorated state
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setVisible(true); // Make the frame visible again
            }
        });
        
        level4Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					initLevel4();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	layeredPane.setVisible(false);
                dispose(); // Dispose the frame
                setUndecorated(true); // Now you can set undecorated state
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setVisible(true); // Make the frame visible again
            }
        });
        
        level5Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					initLevel5();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	layeredPane.setVisible(false);
                dispose(); // Dispose the frame
                setUndecorated(true); // Now you can set undecorated state
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setVisible(true); // Make the frame visible again
            }
        });
        
    	level6Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					initLevel6();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                layeredPane.setVisible(false);
                dispose(); // Dispose the frame
                setUndecorated(true); // Now you can set undecorated state
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setVisible(true); // Make the frame visible again
            }
        });
    	
      	level7Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					initLevel7();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                layeredPane.setVisible(false);
                dispose(); // Dispose the frame
                setUndecorated(true); // Now you can set undecorated state
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setVisible(true); // Make the frame visible again
            }
        });
      	
        level8Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					initLevel8();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	layeredPane.setVisible(false);
                dispose(); // Dispose the frame
                setUndecorated(true); // Now you can set undecorated state
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setVisible(true); // Make the frame visible again
            }
        });
        
    	level9Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					initLevel9();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                layeredPane.setVisible(false);
                dispose(); // Dispose the frame
                setUndecorated(true); // Now you can set undecorated state
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setVisible(true); // Make the frame visible again
            }
        });
    	
    	level10Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					initLevel10();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                layeredPane.setVisible(false);
                dispose(); // Dispose the frame
                setUndecorated(true); // Now you can set undecorated state
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setVisible(true); // Make the frame visible again
            }
        });

        tutorialButton = mainButtonStyle(tutorialButton);
        level1Button = mainButtonStyle(level1Button);
        level2Button = mainButtonStyle(level2Button);
        level3Button = mainButtonStyle(level3Button);
        level4Button = mainButtonStyle(level4Button);
        level5Button = mainButtonStyle(level5Button);
        level6Button = mainButtonStyle(level6Button);
        level7Button = mainButtonStyle(level7Button);
        level8Button = mainButtonStyle(level8Button);
        level9Button = mainButtonStyle(level9Button);
        level10Button = mainButtonStyle(level10Button);


        
        backButton = backButtonStyle(backButton);


        gbc.gridx = 0; // Set gridx to 0 for the first button
        gbc.gridy = GridBagConstraints.RELATIVE; // Each component's gridy is one more than previous
        gbc.anchor = GridBagConstraints.CENTER; // Center component in the cell
        gbc.insets = new Insets(0, 10, 10, 0); // Add some space between the buttons

        gbc.gridx = 0; // Set gridx to 1 for the second button
        levelPanel.add(tutorialButton, gbc); // Add gbc as a parameter
        
        gbc.gridx = 1; // Set gridx to 1 for the second button
        levelPanel.add(level1Button, gbc); // Add gbc as a parameter

        gbc.gridx = 2; // Set gridx to 1 for the second button
        levelPanel.add(level2Button, gbc); // Add gbc as a parameter

        gbc.gridx = 0; // Set gridx to 2 for the third button
        levelPanel.add(level3Button, gbc); // Add gbc as a parameter

        gbc.gridx = 1; // Set gridx back to 0 for the fourth button
        levelPanel.add(level4Button, gbc); // Add gbc as a parameter
        
        gbc.gridx = 2; // Set gridx back to 0 for the fourth button
        levelPanel.add(level5Button, gbc); // Add gbc as a parameter
        
        gbc.gridx = 0; // Set gridx back to 0 for the fourth button
        levelPanel.add(level6Button, gbc); // Add gbc as a parameter
        
        gbc.gridx = 1; // Set gridx back to 0 for the fourth button
        levelPanel.add(level7Button, gbc); // Add gbc as a parameter
        
        gbc.gridx = 2; // Set gridx back to 0 for the fourth button
        levelPanel.add(level8Button, gbc); // Add gbc as a parameter
        
        gbc.gridx = 0; // Set gridx back to 0 for the fourth button
        levelPanel.add(level9Button, gbc); // Add gbc as a parameter
        
        gbc.gridx = 1; // Set gridx back to 0 for the fourth button
        levelPanel.add(level10Button, gbc); // Add gbc as a parameter

        backButtonPanel.add(backButton); // Add backButton to the backButtonPanel
        backButtonPanel.setBounds(0, 0, screenSize.width, screenSize.height); // Set bounds to match the layeredPane size
        backButtonPanel.setOpaque(false); // Make backButtonPanel transparent

        levelPanel.setBounds(0, 0, screenSize.width, screenSize.height); // Set bounds to match the layeredPane size
        levelPanel.setOpaque(false); // Make levelPanel transparent

        // Add the backButtonPanel and levelPanel to the top layer of the layeredPane
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(backButtonPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(levelPanel, JLayeredPane.PALETTE_LAYER);

        add(layeredPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true); // Make the frame visible
    }

    
    private JButton mainButtonStyle(JButton button){
    	ImageIcon buttonIcon = new ImageIcon("menuImages/backButton.png"); // Load the image
        Image img = buttonIcon.getImage() ;  
        Image newimg = img.getScaledInstance(200, 72,  java.awt.Image.SCALE_SMOOTH ) ;  
        buttonIcon = new ImageIcon(newimg);
        
        Font pressStart2P;
		try {
			pressStart2P = Font.createFont(Font.TRUETYPE_FONT, new File("menuImages/PressStart2P.ttf"));
	        pressStart2P = pressStart2P.deriveFont(14f);

		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			pressStart2P = new Font("Dialog", Font.PLAIN, 12);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			pressStart2P = new Font("Dialog", Font.PLAIN, 12);
		}
    	
    	button.setIcon(buttonIcon); // Set the button icon
    	button.setHorizontalTextPosition(JButton.CENTER); // Center the text horizontally
    	button.setVerticalTextPosition(JButton.CENTER); // Center the text vertically
    	button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
    	button.setContentAreaFilled(false); // Make the button background transparent
    	button.setBorderPainted(false);
    	button.setFont(pressStart2P);
    	
    	return button;
    }
    
    private JButton backButtonStyle (JButton button){
    	ImageIcon buttonIcon = new ImageIcon("menuImages/backButton.png"); // Load the image
        Image img = buttonIcon.getImage() ;  
        Image newimg = img.getScaledInstance(72, 72,  java.awt.Image.SCALE_SMOOTH ) ;  
        buttonIcon = new ImageIcon(newimg);
        
        Font pressStart2P;
		try {
			pressStart2P = Font.createFont(Font.TRUETYPE_FONT, new File("menuImages/PressStart2P.ttf"));
	        pressStart2P = pressStart2P.deriveFont(14f);

		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			pressStart2P = new Font("Dialog", Font.PLAIN, 12);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			pressStart2P = new Font("Dialog", Font.PLAIN, 12);
		}
    	
    	button.setIcon(buttonIcon); // Set the button icon
    	button.setHorizontalTextPosition(JButton.CENTER); // Center the text horizontally
    	button.setVerticalTextPosition(JButton.CENTER); // Center the text vertically
    	button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
    	button.setContentAreaFilled(false); // Make the button background transparent
    	button.setBorderPainted(false);
    	button.setFont(pressStart2P);
    	
    	return button;
    }
    
    private void initTutorialButton()throws FileNotFoundException {
    	Map mapOne;
    	Map mapTwo;
	    int[][] sampleMapDataOne = new int[1][1];
	    int[][] sampleMapDataTwo = new int[1][1];
	    sampleMapDataOne = levelLoader("Levels/Tutorial/Part_1.txt", sampleMapDataOne);
	    sampleMapDataTwo = levelLoader("Levels/Tutorial/Part_2.txt", sampleMapDataTwo);
		mapOne = new Map(sampleMapDataOne, 50);
		mapTwo = new Map(sampleMapDataTwo, 50);
		// Add the game panel
		Level gamePanel = new Level("Tutorial",100, 100, mapOne, mapTwo, this);
		gamePanel.addPrompt("Quick Don't let the FyreWall catch you!",200, 100);
		gamePanel.addPrompt("Press \"D\" OR \"->\" to move right.",200, 150);
		gamePanel.addPrompt("Press \"A\" OR \"<-\" to move left.", 900, 150);
		gamePanel.addPrompt("Press \"W\" OR \"^\" to jump.", 1000, 600);
		gamePanel.addPrompt("Press \"CTRL\" switch dimensions.", 2000, 300);
		gamePanel.addPrompt("Some blocks slow you down...", 2700, 600);
		gamePanel.addPrompt("Some blocks speed you up!", 3500, 600);
		gamePanel.addPrompt("You will need all types of blocks to help you on your way!", 5000, 600); 
		add(gamePanel); 
    }

    private void initLevel1() throws FileNotFoundException {
    	Map mapOne;
    	Map mapTwo;
	    int[][] sampleMapDataOne = new int[1][1];
	    int[][] sampleMapDataTwo = new int[1][1];
	    sampleMapDataOne = levelLoader("Levels/Level_9/Part_1.txt", sampleMapDataOne);
	    sampleMapDataTwo = levelLoader("Levels/Level_9/Part_2.txt", sampleMapDataTwo);
		mapOne = new Map(sampleMapDataOne, 50);
		mapTwo = new Map(sampleMapDataTwo, 50);
		Level gamePanel = new Level("Level1", 100, 100, mapOne, mapTwo, this);
		add(gamePanel); 
    }

    private void initLevel2() throws FileNotFoundException {
    	Map mapOne;
    	Map mapTwo;
	    int[][] sampleMapDataOne = new int[1][1];
	    int[][] sampleMapDataTwo = new int[1][1];
	    sampleMapDataOne = levelLoader("Levels/Level_2/Part_1.txt", sampleMapDataOne);
	    sampleMapDataTwo = levelLoader("Levels/Level_2/Part_2.txt", sampleMapDataTwo);
		mapOne = new Map(sampleMapDataOne, 50);
		mapTwo = new Map(sampleMapDataTwo, 50);
		Level gamePanel = new Level("Level2", 100, 100, mapOne, mapTwo, this);
		add(gamePanel); 
    	}
    
    	private void initLevel3() throws FileNotFoundException {
        	Map mapOne;
        	Map mapTwo;
    	    int[][] sampleMapDataOne = new int[1][1];
    	    int[][] sampleMapDataTwo = new int[1][1];
    	    sampleMapDataOne = levelLoader("Levels/Level_3/Part_1.txt", sampleMapDataOne);
    	    sampleMapDataTwo = levelLoader("Levels/Level_3/Part_2.txt", sampleMapDataTwo);
    		mapOne = new Map(sampleMapDataOne, 50);
    		mapTwo = new Map(sampleMapDataTwo, 50);
    		Level gamePanel = new Level("Level3", 100, 100, mapOne, mapTwo, this);
    		add(gamePanel); 
    }
    	
        private void initLevel4() throws FileNotFoundException { // Moderate
        	Map mapOne;
        	Map mapTwo;
    	    int[][] sampleMapDataOne = new int[1][1];
    	    int[][] sampleMapDataTwo = new int[1][1];
    	    sampleMapDataOne = levelLoader("Levels/Level_9/Part_1.txt", sampleMapDataOne);
    	    sampleMapDataTwo = levelLoader("Levels/Level_9/Part_2.txt", sampleMapDataTwo);
    		mapOne = new Map(sampleMapDataOne, 50);
    		mapTwo = new Map(sampleMapDataTwo, 50);
    		Level gamePanel = new Level("Level4", 100, 100, mapOne, mapTwo, this);
    		add(gamePanel); 
        }

   
    private void initLevel5() throws FileNotFoundException { // Moderate
    	Map mapOne;
    	Map mapTwo;
	    int[][] sampleMapDataOne = new int[1][1];
	    int[][] sampleMapDataTwo = new int[1][1];
	    sampleMapDataOne = levelLoader("Levels/Level_5/Part_1.txt", sampleMapDataOne);
	    sampleMapDataTwo = levelLoader("Levels/Level_5/Part_2.txt", sampleMapDataTwo);
		mapOne = new Map(sampleMapDataOne, 50);
		mapTwo = new Map(sampleMapDataTwo, 50);
		Level gamePanel = new Level("Level5", 100, 100, mapOne, mapTwo, this);
		add(gamePanel); 
    }
    
    private void initLevel6() throws FileNotFoundException { // Moderate
    	Map mapOne;
    	Map mapTwo;
	    int[][] sampleMapDataOne = new int[1][1];
	    int[][] sampleMapDataTwo = new int[1][1];
	    sampleMapDataOne = levelLoader("Levels/Level_6/Part_1.txt", sampleMapDataOne);
	    sampleMapDataTwo = levelLoader("Levels/Level_6/Part_2.txt", sampleMapDataTwo);
		mapOne = new Map(sampleMapDataOne, 50);
		mapTwo = new Map(sampleMapDataTwo, 50);
		Level gamePanel = new Level("Level6", 100, 100, mapOne, mapTwo, this);
		add(gamePanel); 
    }
    
    private void initLevel7() throws FileNotFoundException { // Moderate
    	Map mapOne;
    	Map mapTwo;
	    int[][] sampleMapDataOne = new int[1][1];
	    int[][] sampleMapDataTwo = new int[1][1];
	    sampleMapDataOne = levelLoader("Levels/Level_7/Part_1.txt", sampleMapDataOne);
	    sampleMapDataTwo = levelLoader("Levels/Level_7/Part_2.txt", sampleMapDataTwo);
		mapOne = new Map(sampleMapDataOne, 50);
		mapTwo = new Map(sampleMapDataTwo, 50);
		Level gamePanel = new Level("Level7", 100, 100, mapOne, mapTwo, this);
		add(gamePanel); 
    }
    
    private void initLevel8() throws FileNotFoundException { // Moderate
    	Map mapOne;
    	Map mapTwo;
	    int[][] sampleMapDataOne = new int[1][1];
	    int[][] sampleMapDataTwo = new int[1][1];
	    sampleMapDataOne = levelLoader("Levels/Level_8/Part_1.txt", sampleMapDataOne);
	    sampleMapDataTwo = levelLoader("Levels/Level_8/Part_2.txt", sampleMapDataTwo);
		mapOne = new Map(sampleMapDataOne, 50);
		mapTwo = new Map(sampleMapDataTwo, 50);
		Level gamePanel = new Level("Level8", 100, 100, mapOne, mapTwo, this);
		add(gamePanel); 
    }
    
    
    private void initLevel9() throws FileNotFoundException { // Moderate
    	Map mapOne;
    	Map mapTwo;
	    int[][] sampleMapDataOne = new int[1][1];
	    int[][] sampleMapDataTwo = new int[1][1];
	    sampleMapDataOne = levelLoader("Levels/Level_4/Part_1.txt", sampleMapDataOne);
	    sampleMapDataTwo = levelLoader("Levels/Level_4/Part_2.txt", sampleMapDataTwo);
		mapOne = new Map(sampleMapDataOne, 50);
		mapTwo = new Map(sampleMapDataTwo, 50);
		Level gamePanel = new Level("Level9", 100, 100, mapOne, mapTwo, this);
		add(gamePanel); 
    }
    

    
    private void initLevel10() throws FileNotFoundException { // Moderate
    	Map mapOne;
    	Map mapTwo;
	    int[][] sampleMapDataOne = new int[1][1];
	    int[][] sampleMapDataTwo = new int[1][1];
	    sampleMapDataOne = levelLoader("Levels/Level_10/Part_1.txt", sampleMapDataOne);
	    sampleMapDataTwo = levelLoader("Levels/Level_10/Part_2.txt", sampleMapDataTwo);
		mapOne = new Map(sampleMapDataOne, 50);
		mapTwo = new Map(sampleMapDataTwo, 50);
		Level gamePanel = new Level("Level10", 100, 100, mapOne, mapTwo, this);
		add(gamePanel); 
    }
    
   
    
    public int[][] levelLoader(String file, int[][]mapData) throws FileNotFoundException // Takes the needed location of the level files as well as the arrays to draw the level.
    {
    	Scanner sc = new Scanner(new BufferedReader(new FileReader(file))); // Reads Part 1 and places it into the array        
        int rows = 0;
        int cols = 0;
        
        while(sc.hasNextLine()) {
            String[] line = sc.nextLine().trim().split(",");
            rows++;
            cols = line.length;
        }   
        sc.close();
    	sc = new Scanner(new BufferedReader(new FileReader(file))); // Reads Part 1 and places it into the array        

    	mapData = new int[rows][cols];
    	
	      while(sc.hasNextLine()) {
		         for (int i=0; i<rows; i++) {
		            String[] line = sc.nextLine().trim().split(",");

		            for (int j=0; j<cols; j++) {
		            	mapData[i][j] = Integer.parseInt(line[j]);

		            }
		         }
		      }
          sc.close();
          return mapData;
	       
    }
}
