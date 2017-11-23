package helper;

import java.util.List;
import java.util.Scanner;

import exception.RolePlayGameException;
import model.Game;

/**
 *  IGameEnginHelper is the interface of helper class of the GameService
 * @author ashutosh
 *
 */
public interface IGameServiceHelper {
	
	
	/**
	 * This Method starts a new Game , It ask for User inputs as character , Map 
	 * and builds a Game with user input data
	 * @param scanner
	 * @return
	 * @throws RolePlayGameException 
	 */
	Game newGame(Scanner scanner) throws RolePlayGameException;

	/**
	 * This function will start a game, its input are the previously saved games of a user . User can choose to run
	 * a new game as well.
	 * @param games
	 * @param scanner
	 * @return Game which is currenlty being run 
	 * @throws RolePlayGameException
	 */
	
	Game startGame(List<Game> games,Scanner scanner)throws RolePlayGameException;
	
	
	/**
	 * This function draws Map and show instruction to explore the map. Puts questions present in the map
	 * to conquer the enemy .When  a user quit it asks if he wants to save the game as well.
	 * @param game
	 * @param scanner
	 * @throws RolePlayGameException
	 */
	
	Game exploreGameAndConquer(Game game,Scanner scanner) throws RolePlayGameException;
	
	
	
	
	/**
	 * This function is used to save the game .
	 * @param game
	 * @param scanner
	 * @return
	 * @throws RolePlayGameException
	 */
	Game saveTheGame(Game game, Scanner scanner) throws RolePlayGameException  ;
	
	
}
