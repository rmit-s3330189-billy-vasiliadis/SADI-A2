package controller;

import java.util.*;

public class GameState {
  private String playerCurrentlySelected;
  private ArrayList<String> betPlaced;
  private ArrayList<String> hasRolled;
  private boolean currentlyRolling;

  public GameState() {
    playerCurrentlySelected = "House";  
    betPlaced = new ArrayList<String>();
    hasRolled = new ArrayList<String>();
    currentlyRolling = false;
  }

  public String getCurrentPlayer() {
    return playerCurrentlySelected;
  }

  public void setCurrentPlayer(String playerId) {
    playerCurrentlySelected = playerId;  
  }

  public boolean hasCurrentPlayerPlacedBet() {
    if(betPlaced.contains(playerCurrentlySelected)) {
      return true;    
    } 
    return false;
  }

  public void setBetPlacedForCurrentPlayer() {
    betPlaced.add(playerCurrentlySelected);
  }

  public boolean hasCurrentPlayerRolled() {
    if(hasRolled.contains(playerCurrentlySelected)) {
      return true;  
    }
    return false;
  }

  public void setCurrentPlayerRolled() {
    hasRolled.add(playerCurrentlySelected);  
  }

  public boolean haveAllPlayersRolled(int noOfPlayers) {
    if(hasRolled.size() == noOfPlayers) {
      return true;  
    }  
    return false;
  }

  public void startedRolling() {
    currentlyRolling = true;  
  }

  public void finishedRolling() {
    currentlyRolling = false;
  }

  public boolean isPlayerRolling() {
    return currentlyRolling;  
  }

  public void resetState() {
    betPlaced.clear();
    hasRolled.clear();
  }
}
