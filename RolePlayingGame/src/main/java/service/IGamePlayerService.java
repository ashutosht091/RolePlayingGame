package service;

import java.util.Scanner;

import exception.RolePlayGameException;
import model.Player;
/**
 * This is an interface to GamePlayerService . It declares player specific activites 
 * as singIn and registration
 * @author ashutosh
 *
 */
public interface IGamePlayerService {
	/**
	 * Used to signIn a player
	 * @param scanner
	 * @return logged In player 
	 * @throws RolePlayGameException
	 */
	public Player signIn(Scanner scanner) throws RolePlayGameException;
	/**
	 * User for new player registration
	 * @param scanner
	 * @return registered player 
	 * @throws RolePlayGameException
	 */
	public Player registerPlyer(Scanner scanner) throws RolePlayGameException;
	
	

}
