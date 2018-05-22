package view;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.ConsoleHandler;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * 
 * Skeleton example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see model.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
	private Logger logger = Logger.getLogger("assignment1");
	private Handler consoleHandler = new ConsoleHandler();
  private GameEngineCallbackGUI GUI;

	public GameEngineCallbackImpl(GameEngineCallbackGUI GUI)
	{
    this.GUI = GUI;
		consoleHandler.setLevel(Level.FINE);
		logger.addHandler(consoleHandler);
		logger.setLevel(Level.FINE);
		logger.setUseParentHandlers(false);
	}

	@Override
	public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine)
	{ 
		logger.log(Level.FINE, String.format("%s: ROLLING %s", player.getPlayerName(), dicePair.toString()));
    String dice1 = String.valueOf(dicePair.getDice1());
    String dice2 = String.valueOf(dicePair.getDice2());
    String total = String.valueOf(dicePair.getDice1() + dicePair.getDice2());
    GUI.getRollResultPanel().updateIntermediateResult(dice1, dice2, total);
    try {
     Thread.sleep(1000);
    } catch(Exception e) {
      e.printStackTrace();  
    }
	}

	@Override
	public void result(Player player, DicePair result, GameEngine gameEngine)
	{
		logger.log(Level.INFO, String.format("%s: *RESULT* %s\n", player.getPlayerName(), result.toString()));
    String dice1 = String.valueOf(result.getDice1());
    String dice2 = String.valueOf(result.getDice2());
    String total = String.valueOf(result.getDice1() + result.getDice2());
    GUI.getRollResultPanel().updateFinalResult(dice1, dice2, total);
    try {
     Thread.sleep(1000);
    } catch(Exception e) {
      e.printStackTrace();  
    }

  }
	
	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine){
		logger.log(Level.FINE, String.format("House: ROLLING %s", dicePair.toString()));
	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine){
		logger.log(Level.INFO, String.format("House: *RESULT* %s\n", result.toString()));
		//log the final results for each player		
		Collection<Player> players = gameEngine.getAllPlayers();
		Iterator<Player> iterator = players.iterator();
		while(iterator.hasNext()) {
			logger.log(Level.INFO, String.format("%s", iterator.next().toString()));
		}
		System.out.println();
		
	}
}
