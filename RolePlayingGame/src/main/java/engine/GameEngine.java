package engine;

import static util.PrintUtil.print;
import static util.PrintUtil.printLn;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import constants.Constants;
import exception.GameExceptionType;
import exception.RolePlayGameException;
import helper.GenericHelper;
import model.Player;
import service.GamePlayerService;
import service.GameService;
import service.IGamePlayerService;
import staticdata.GameStaticData;

/**
 * This class is the starting point of the Role Playing Game.This Game is console based game .
 * Game instructions are as following :
 * Step-1 :Authentication   : Login / SignUp - Player needs to login or if he is a new user he needs to sign up .
 * 
 * Step-2 :Select a Game    : 1) Existing User -If you are returning user you are shown previously saved game . 
 *                             You can select to resume or start a new Game.
 *                            2) IF you are new user then a new Game will be started for you.
 *                            
 * Step-3 :Choose Character,Map : Game shows you a list of character which you can select and a map which you wanna explore
 * Step-4 : Explore the map  : Once a Map is selected , Map is drawn as 2-D matrix and character name is shown in the
 *                            first cell on top left corner .You can move your player as per the instruction shown 
 *                            on the screen.
 *                            
 * Step-5 :Fight With Enemy : If there is an enemy present at any cell, you have to fight with enemy .
 *                            To defeat an enemy you need answer a question . Correct answer will be rewarded as 
 *                            experience points.
 *                            Once you defeat the enemy in a cell , you can visit that cell again freely .
 * Step-6 :Save/Quit Game   : You can quit the game any time by pressing 0 , you will be asked to save the game .
 * 							  You can resume the game once you login back .                                                         
 * 
 * @author ashutosh
 *
 */
public class GameEngine {

	
	public static final Logger logger = LoggerFactory.getLogger(GameEngine.class);
	private GameEngine()
	{

	}

	public static void main(String[] args) throws RolePlayGameException {


		Scanner scanner = new Scanner(System.in);
		Player player = null;
		String userOption="";
		print(Constants.WELCOME_MSG);
		printLn(Constants.GAME_TOPIC);


		do{
			try{
				printLn(Constants.LOGIN_DETAILS);
				IGamePlayerService playerService = GamePlayerService.getInstance();
				userOption = scanner.next();
				if(userOption.equals(Constants.USER_OPTION_LOGIN))
				{
					player = playerService.signIn(scanner);
				}else if(userOption.equals(Constants.USER_OPTION_SIGNUP))
				{
					player = playerService.registerPlyer(scanner);
				}
			}catch(RolePlayGameException exception)
			{
				if(!GenericHelper.needDifferentInputs(exception))
				{
					throw exception; 
				}
			}
		}while(player==null && !userOption.equals(Constants.EXIT));
		try{
			if(player!=null)
			{
				GameStaticData.currentPlayer=player;
				GameService.getInstance().startGame(scanner);
			}
		}catch(Exception exception)
		{
			logger.error(exception.getMessage(),exception);
			printLn(GameExceptionType.ERROR.getMsg());
		}finally
		{
			scanner.close();
		}





	}

}