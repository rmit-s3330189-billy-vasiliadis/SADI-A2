package view;

import java.awt.*;
import javax.swing.*;

public class RollResultPanel extends JPanel {
  JLabel intermediateDiceOne;
  JLabel intermediateDiceTwo;
  JLabel intermediateTotal;
  JLabel finalDiceOne;
  JLabel finalDiceTwo;
  JLabel finalTotal;

  public RollResultPanel() {
    setLayout(new GridLayout(8,2));

    JLabel intermediateLabel = new JLabel("Intermediate Result:");
    JLabel intBuffer = new JLabel();
    add(intermediateLabel);
    add(intBuffer);

    JLabel intermediateDiceOneLabel = new JLabel("Dice 1: ");
    intermediateDiceOne = new JLabel("N/A");
    add(intermediateDiceOneLabel);
    add(intermediateDiceOne);

    JLabel intermediateDiceTwoLabel = new JLabel("Dice 2: ");
    intermediateDiceTwo = new JLabel("N/A");
    add(intermediateDiceTwoLabel);
    add(intermediateDiceTwo);

    JLabel intermediateTotalLabel = new JLabel("Total: ");
    intermediateTotal = new JLabel("N/A");
    add(intermediateTotalLabel);
    add(intermediateTotal);

    JLabel finalLabel = new JLabel("Final Result:");
    JLabel finalBuffer = new JLabel();
    add(finalLabel);
    add(finalBuffer);

    JLabel finalDiceOneLabel = new JLabel("Dice 1: ");
    finalDiceOne = new JLabel("N/A");
    add(finalDiceOneLabel);
    add(finalDiceOne);

    JLabel finalDiceTwoLabel = new JLabel("Dice 2: ");
    finalDiceTwo = new JLabel("N/A");
    add(finalDiceTwoLabel);
    add(finalDiceTwo);

    JLabel finalTotalLabel = new JLabel("Total: ");
    finalTotal = new JLabel("N/A");
    add(finalTotalLabel);
    add(finalTotal);
 }

 public void updateIntermediateResult(String dice1, String dice2, String total) {
   intermediateDiceOne.setText(dice1);
   intermediateDiceTwo.setText(dice2);
   intermediateTotal.setText(total);
 }

 public void updateFinalResult(String dice1, String dice2, String total) {
   finalDiceOne.setText(dice1);
   finalDiceTwo.setText(dice2);
   finalTotal.setText(total);
 }
}
