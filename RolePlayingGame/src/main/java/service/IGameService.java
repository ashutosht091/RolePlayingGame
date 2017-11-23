package service;

import java.util.Scanner;

import exception.RolePlayGameException;
import model.Game;

/**
 * This is interface for GameService class 
 * @author ashutosh
 *
 */
public interface IGameService {
	
	/**
	 * This method is used to load the game
	 * @param sn
	 * @throws RolePlayGameException
	 */
	public Game startGame(Scanner scanner) throws RolePlayGameException ;

}
