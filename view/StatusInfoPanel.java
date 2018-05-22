package view;

import java.awt.*;
import javax.swing.*;

public class StatusInfoPanel extends JPanel {
  JLabel name;
  JLabel points;
  JLabel bet;
  JLabel rolled;

  public StatusInfoPanel() {
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
    
    JLabel rolledLabel = new JLabel("Rolled: ");
    rolled = new JLabel("N");
    add(rolledLabel);
    add(rolled);    
  }

  public void updateStatusInfo(String name, String points, String bet, String rolled) {
    this.name.setText(name);
    this.points.setText(points);
    this.bet.setText(bet);
    this.rolled.setText(rolled);
  }

  public void updateBetInfo(String bet) {
    this.bet.setText(bet);  
  }

  public void updateRolledInfo(String rolled) {
    this.rolled.setText(rolled);  
  }

  public void updatePointsInfo(String points) {
    this.points.setText(points);  
  }
}
