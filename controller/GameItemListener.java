package controller;

import java.awt.event.*;
import javax.swing.*;
import view.GameEngineCallbackGUI;
import model.interfaces.Player;
import model.interfaces.GameEngine;

public class GameItemListener implements ItemListener {
  private GameEngineCallbackGUI GUI;
  private GameEngine gameEngine;
  private GameState gameState;

  public GameItemListener(GameEngineCallbackGUI GUI, GameEngine gameEngine, GameState gameState) {
    this.GUI = GUI;  
    this.gameEngine = gameEngine;
    this.gameState = gameState;
  }
  
  public void itemStateChanged(ItemEvent e) {
    if(e.getStateChange() == 1) {
      String item = (String)e.getItem();
      if(item.equals("House")) {
        gameState.setCurrentPlayer("House");
        GUI.getStatusInfoPanel().updateStatusInfo(item, "N/A", "N/A", "N"); 
      } else {
        String playerId = item.split(":")[0].trim();
        String name = item.split(":")[1].trim();
        Player playerSelected = gameEngine.getPlayer(playerId);
        gameState.setCurrentPlayer(playerId);
        String points = String.valueOf(playerSelected.getPoints());
        String bet = String.valueOf(playerSelected.getBet());
        GUI.getStatusInfoPanel().updateStatusInfo(name, points, bet, "N");
      }
    }
  }
}
