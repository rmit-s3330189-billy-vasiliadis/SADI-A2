package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.SimplePlayer;
import view.GameEngineCallbackGUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameMouseListener implements MouseListener {
  private GameEngineCallbackGUI GUI;
  private GameEngine gameEngine;
  private GameState gameState;
  private int playerId;
  
  public GameMouseListener(GameEngineCallbackGUI GUI, GameEngine gameEngine, GameState gameState) {
    this.GUI = GUI;  
    this.gameEngine = gameEngine;
    this.gameState = gameState;
    playerId = 0;
  }
  
  public void mouseClicked(MouseEvent e) {
    JButton buttonClicked = (JButton)e.getSource();
    if(buttonClicked.getText().equals("Add Player")) {
      addPlayer();
    } else if(buttonClicked.getText().equals("Place Bet")) {
      if(gameState.getCurrentPlayer().equals("House")) {
        JOptionPane.showMessageDialog(GUI, "House cannot place a bet");
      } else if(gameState.hasCurrentPlayerPlacedBet()) {
        JOptionPane.showMessageDialog(GUI, "This player has already placed a bet");  
      } else {
        placeBet();
      }
    } else if(buttonClicked.getText().equals("Roll Dice")) {
      
    }
    //call game engine methods on a new thread
    //new Thread()...
  } 

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
  }

  public void addPlayer() {
    String name = JOptionPane.showInputDialog(GUI, "Enter your name");
    int initPoints;
    while(true) {
      try {
        initPoints = Integer.parseInt(JOptionPane.showInputDialog(GUI, "Enter your initial points")); 
      } catch(NumberFormatException e) {
        JOptionPane.showMessageDialog(GUI, "Not a valid number, try again");
        continue;
      }
      break;
    }
    playerId++;
    GUI.addPlayerToGUI(playerId + ": " + name, initPoints);
    gameEngine.addPlayer(new SimplePlayer(String.valueOf(playerId), name, initPoints));
  }

  public void placeBet() {
    Player currentPlayer = gameEngine.getPlayer(gameState.getCurrentPlayer());
    int bet;
    while(true) {
      try {
        bet = Integer.parseInt(JOptionPane.showInputDialog(GUI, "Enter your bet")); 
      } catch(NumberFormatException e) {
        JOptionPane.showMessageDialog(GUI, "Not a valid number, try again");
        continue;
      }
      if(gameEngine.placeBet(currentPlayer, bet)) {
        JOptionPane.showMessageDialog(GUI, "Your bet has been placed");  
      } else {
        JOptionPane.showMessageDialog(GUI, "Your bet is too high");
        continue;
      }
      break;
    }
    gameState.setBetPlacedForCurrentPlayer();
    GUI.getStatusInfoPanel().updateBetInfo(String.valueOf(bet));
  }
}
