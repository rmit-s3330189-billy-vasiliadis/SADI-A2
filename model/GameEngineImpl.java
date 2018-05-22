package model;

import java.util.Random;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngineImpl implements GameEngine {
	private Random randomNumber = new Random();
	private Collection<Player> players = new ArrayList<Player>();
	private Collection<GameEngineCallback> gameEngineCallbacks = new ArrayList<GameEngineCallback>();
	private Player currentPlayer;
	private int currentHighestTotal = 0;
	private boolean draw;
	
	public boolean placeBet(Player player, int bet) {
		return player.placeBet(bet);
	}

	public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {
		if(player.getBet() == 0) {
			System.out.println(player.getPlayerName() + " not in this round, bet not placed or too high!\n");
		} else {
			currentPlayer = player;
			DicePair rollResult = rollDice(initialDelay, finalDelay, delayIncrement);
			player.setRollResult(rollResult);
			displayResult(rollResult);
		}
	}

	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		currentPlayer = null;
		DicePair rollResult = rollDice(initialDelay, finalDelay, delayIncrement);
		getWinner();
		displayResult(rollResult);
	}

	private DicePair rollDice(int initialDelay, int finalDelay, int delayIncrement) {
		int dice1, dice2;		
		for(int i = initialDelay ; i <= finalDelay ; i+=delayIncrement) {
			dice1 = 1 + randomNumber.nextInt(GameEngine.NUM_FACES);
			dice2 = 1 + randomNumber.nextInt(GameEngine.NUM_FACES);
			DicePair dicePair = new DicePairImpl(dice1, dice2, GameEngine.NUM_FACES); 
			displayIntermediateResult(dicePair);
		}
		dice1 = 1 + randomNumber.nextInt(GameEngine.NUM_FACES);
		dice2 = 1 + randomNumber.nextInt(GameEngine.NUM_FACES);
		DicePair result = new DicePairImpl(dice1, dice2, GameEngine.NUM_FACES);
		setCurrentHighestTotal(getDiceTotal(result));
		return result;
	}

	private void displayIntermediateResult(DicePair dicePair) {
		Iterator<GameEngineCallback> iterator = gameEngineCallbacks.iterator();		
		if(currentPlayer != null) {
			while(iterator.hasNext()) {
				iterator.next().intermediateResult(currentPlayer, dicePair, this);
			}			
		} else {
			while(iterator.hasNext()) {
				iterator.next().intermediateHouseResult(dicePair, this);
			}
		}
	}

	private void displayResult(DicePair result) {
		Iterator<GameEngineCallback> iterator = gameEngineCallbacks.iterator();		
		if(currentPlayer != null) {
			while(iterator.hasNext()) {
				iterator.next().result(currentPlayer, result, this);
			}			
		} else {
			while(iterator.hasNext()) {
				iterator.next().houseResult(result, this);
			}
		}
	}

	private void getWinner() {
		//loop through the players and find the player(s) with the highest result(s)
		Iterator<Player> iterator = players.iterator();
		while(iterator.hasNext()) {
			Player player = iterator.next();
			if(player.getBet() != 0) {
				if(getDiceTotal(player.getRollResult()) < currentHighestTotal) {
					player.setPoints(player.getPoints() - player.getBet());
				} else if (getDiceTotal(player.getRollResult()) == currentHighestTotal && !draw) {
					player.setPoints(player.getPoints() + player.getBet());
				}
			}
		}
		currentHighestTotal = 0;
	}
	
	private int getDiceTotal(DicePair dicePair) {
		return dicePair.getDice1() + dicePair.getDice2();
	}

	private void setCurrentHighestTotal(int total) {
		if(total > currentHighestTotal) {
			currentHighestTotal = total;
			draw = false;
		} else if (total == currentHighestTotal) {
			draw = true;
		}
	}

	public void addPlayer(Player player) {
		if(!players.contains(player)) {
			System.out.println("Added " + player.getPlayerName());	
			players.add(player);
		}
	}

	public Player getPlayer(String id) {
		Iterator<Player> iterator = players.iterator();
		while(iterator.hasNext()) {
			Player player = iterator.next();
			if(player.getPlayerId().equals(id)) {
				return player;
			}
		}
		return null;
	}

	public boolean removePlayer(Player player) {
		if(players.contains(player)) {
			players.remove(player);
			return true;
		}		
		return false;
	}

	public Collection<Player> getAllPlayers() {
		return players;
	}

	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		if(!gameEngineCallbacks.contains(gameEngineCallback)) {
			gameEngineCallbacks.add(gameEngineCallback);
		}
	}

	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		if(gameEngineCallbacks.contains(gameEngineCallback)) {
			gameEngineCallbacks.remove(gameEngineCallback);
			return true;
		}
		return false;
	}
}
