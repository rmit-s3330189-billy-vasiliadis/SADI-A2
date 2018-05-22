package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

public class SimplePlayer implements Player {
	private String playerId;
	private String playerName;
	private int points;
	private int bet;
	private DicePair rollResult;

	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		this.playerId = playerId;
		this.playerName = playerName;
    this.bet = 0;
		points = initialPoints;
	}
	
	public String getPlayerName() {
		return playerName;	
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPoints() {
		return points;
	}	

	public void setPoints(int points) {
		this.points = points;
	}

	public String getPlayerId() {
		return playerId;
	}	

	public boolean placeBet(int bet) {
		if(points >= bet) {
			this.bet = bet;
			return true;
		} 
		//this.bet = 0;
		return false;
	}

	public int getBet() {
		return bet;
	}

	public DicePair getRollResult() {
		return rollResult;
	}

	public void setRollResult(DicePair rollResult) {
		this.rollResult = rollResult;
	}

	@Override
	public String toString() {
		return String.format("Player: id=%s, name=%s, points=%d", playerId, playerName, points);
	}
}
