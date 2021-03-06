package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.SimplePlayer;
import view.GameEngineCallbackGUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

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
      if(gameState.isPlayerRolling()) {
        JOptionPane.showMessageDialog(GUI, "Wait for current roll to finish");  
      } else if(gameState.getCurrentPlayer().equals("House")) {
        int noOfPlayers = gameEngine.getAllPlayers().size();
        if(noOfPlayers == 0) {
          JOptionPane.showMessageDialog(GUI, "No players have been added");
        } else if(gameState.haveAllPlayersRolled(noOfPlayers)) {
          houseRoll();  
        } else {
          JOptionPane.showMessageDialog(GUI, "Not all players have rolled yet");  
        }
      } else if(gameState.hasCurrentPlayerRolled()) {
        JOptionPane.showMessageDialog(GUI, "This player has already rolled for this round");
      } else if(!gameState.hasCurrentPlayerPlacedBet()) {
        JOptionPane.showMessageDialog(GUI, "Bet not placed, cannot roll");
      } else {
        playerRoll();  
      }    
    }
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
    String name;
    while(true) {
      if((name = JOptionPane.showInputDialog(GUI, "Enter your name")).equals("")) {
        JOptionPane.showMessageDialog(GUI, "Your name cannot be empty");
        continue;
      }
      break;
    }

    int initPoints;
    while(true) {
      try {
        initPoints = Integer.parseInt(JOptionPane.showInputDialog(GUI, "Enter your initial points")); 
      } catch(NumberFormatException e) {
        JOptionPane.showMessageDialog(GUI, "Not a valid number, try again");
        continue;
      }
      if(initPoints < 500) {
        JOptionPane.showMessageDialog(GUI, "Your initial points have to be at least 500");
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
      if(bet < 100) {
        JOptionPane.showMessageDialog(GUI, "Your bet has to be at least 100");
        continue;
      } else if(gameEngine.placeBet(currentPlayer, bet)) {
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

  public void playerRoll() {
    gameState.startedRolling();
    final Player currentPlayer = gameEngine.getPlayer(gameState.getCurrentPlayer());
    //do this on another thread so that the UI does not block
    new Thread() {
      public void run() {
        gameEngine.rollPlayer(currentPlayer, 1, 100, 20);
      }  
    }.start();
    gameState.setCurrentPlayerRolled();
  }

  public void houseRoll() {
    gameState.startedRolling();
    //do this on another thread so that the UI does not block
    new Thread() {
      public void run() {
        gameEngine.rollHouse(1, 100, 20);
      }  
    }.start();
    //gameState.resetState();
  }
}
