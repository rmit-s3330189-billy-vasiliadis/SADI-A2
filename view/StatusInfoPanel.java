package view;

import java.awt.*;
import javax.swing.*;
import controller.GameState;

public class StatusInfoPanel extends JPanel {
  private JLabel name;
  private JLabel points;
  private JLabel bet;
  private JLabel finalResult;
  private GameState gameState;  

  public StatusInfoPanel(GameState gameState) {
    this.gameState = gameState;

    setLayout(new GridLayout(4,2));

    JLabel nameLabel = new JLabel("Name:");
    name = new JLabel("House");
    add(nameLabel);
    add(name);

    JLabel pointsLabel = new JLabel("Points: ");
    points = new JLabel("N/A");
    add(pointsLabel);
    add(points);
    
    JLabel betLabel = new JLabel("Bet: ");
    bet = new JLabel("N/A");
    add(betLabel);
    add(bet);
    
    JLabel finalResultLabel = new JLabel("Final Result: ");
    finalResult = new JLabel("N/A");
    add(finalResultLabel);
    add(finalResult);    
  }

  public void updateStatusInfo(String name, String points, String bet, String finalResult) {
    this.name.setText(name);
    this.points.setText(points);
    this.bet.setText(bet);
    this.finalResult.setText(finalResult);
  }

  public void updateBetInfo(String bet) {
    this.bet.setText(bet);  
  }

  public void updatePointsInfo(String points) {
    this.points.setText(points);  
  }

  public void updateFinalResult(String playerId, String total) {
    //because the roll runs on a seperate thread, only update the status if the player rolling
    //is the currently selected player
    if(gameState.getCurrentPlayer().equals(playerId)) {
      this.finalResult.setText(total);  
    }
  }

  public void updateFinalHouseResult(String total) {
    this.finalResult.setText(total);  
  }
}
