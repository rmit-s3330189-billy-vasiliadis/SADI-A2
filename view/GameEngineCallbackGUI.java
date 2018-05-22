package view;

import model.interfaces.GameEngine;
import controller.GameMouseListener;
import controller.GameState;
import controller.GameItemListener;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class GameEngineCallbackGUI extends JFrame{
  private JComboBox<String> playerSelect;
  private GameEngine gameEngine;
  private GameState gameState;
  private StatusInfoPanel statusInfoPanel;

  public GameEngineCallbackGUI(GameEngine gameEngine, GameState gameState) {
    super("Dice Game");
    this.gameEngine = gameEngine;
    this.gameState = gameState;
    
    //set layout of the content pane
    setLayout(new BorderLayout());
    
    //first, we create a toolbar which does the basic functionality
    JToolBar toolBar = new JToolBar("Actions");
    //add buttons to the toolbar
    JButton addPlayerButton = new JButton("Add Player");
    toolBar.add(addPlayerButton);
    JButton placeBetButton = new JButton("Place Bet");
    toolBar.add(placeBetButton);
    JButton rollButton = new JButton("Roll Dice");
    toolBar.add(rollButton);

    //add toolbar to north area of content pane
    add(toolBar, BorderLayout.NORTH);

    //JPanel for the main part of the UI, make it the center of the content pane
    JPanel main = new JPanel();
    main.setLayout(new GridLayout(1,2));
    add(main, BorderLayout.CENTER);

    //JPanel for displaying the status of the currently selected player
    JPanel statusPanel = new JPanel();
    statusPanel.setLayout(new BorderLayout());
    //create a combo box which is used to select a player
    playerSelect = new JComboBox<String>();
    playerSelect.addItem("House");
    statusPanel.add(playerSelect, BorderLayout.NORTH);
    
    statusInfoPanel = new StatusInfoPanel();
    statusPanel.add(statusInfoPanel, BorderLayout.CENTER);  
    main.add(statusPanel);

    //JPanel for showing dice roll results
    JPanel rollDicePanel = new JPanel();
    rollDicePanel.add(new JTextArea("Roll Dice",10,20)); 
    main.add(rollDicePanel);

    //add event listeners
    GameMouseListener mouseListener = new GameMouseListener(this, gameEngine, gameState);
    GameItemListener itemListener = new GameItemListener(this, gameEngine, gameState);
    addPlayerButton.addMouseListener(mouseListener);
    placeBetButton.addMouseListener(mouseListener);
    rollButton.addMouseListener(mouseListener);
    playerSelect.addItemListener(itemListener);
    
    //make visible
    setBounds(100,100,1000,200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }  

  public void addPlayerToGUI(String name, int initPoints) {
    playerSelect.addItem(name);
  }

  public StatusInfoPanel getStatusInfoPanel() {
    return statusInfoPanel;  
  }
}
