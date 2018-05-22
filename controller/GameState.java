package controller;

public class GameState {
  private String playerCurrentlySelected;
  private ArrayList<String> betPlaced;
  private ArrayList<String> hasRolled;
  
  public GameState() {
    playerCurrentlySelected = "House";  
    betPlaced = new ArrayList<String>();
    hasRolled = new ArrayList<String>();
  }

  public String getCurrentPlayer() {
    return playerCurrentlySelected;
  }

  public void setCurrentPlayer(String playerId) {
    playerCurrentlySelected = name;  
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

  public void resetState() {}
}
