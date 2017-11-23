package constants;

import java.util.Locale;

import util.ResourceUtil;


public abstract class Constants {

	

	public static final String FILE_PERSISTENCE="FileDataStore";
	public static final String PERSISTENCE_CONTEXT_FILE="/conf/persistence.properties";
	public static final String PERSISTENCE_CONTEXT_NAME ="persitence_name";

	public static final String ENTER_USERNAME = "Enter USERNAME";
	public static final String ENTER_PASSWORD = "Enter PASSWORD";
	public static final String ENTER_FIRST_NAME ="Enter  First Name";
	public static final String ENTER_LAST_NAME = "Enter Last Name";
	public static final String WRONG_INFO_TRY_AGAIN = "Wrong Information! Try Again";
	public static final String LOGIN_INFO = "Press 1 for sign in or press 2 for sign up, you can go to previous menu or exit anytime by pressing 0!";
	public static final String START_GAME = "Press 1 for new game, 2 to load a game!";
	public static final String CHOOSE_CHARACTER = "Choose a character from the below list!";
	public static final String CHOOSE_MAP = "Choose a map from the below list!";

	public static final String ENCOUNTERED_ENEMY = "Encountered with \"{0}\", to win the fight answer the following question!";
	public static final String ANSWER_THE_QUESTION = "Answer the question by \"yes\" or \"no\" to continue or pass by typing \"pass\"";
	public static final String WIN_THE_FIGHT = "You win the fight, congratulations, your new experience point is {0}";
	public static final String LOST_THE_FIGHT = "You lost the fight, correct answer is \"{0}\"";
	public static final String NEW_EXPERIENCE = " your new experience {0}";
	public static final String MOVE_OPTIONS = "Please press  \n 1 + ENTER for  UP,  2 + ENTER for DOWN,\n 3 + ENTER for RIGHT,  4 + ENTER for LEFT \n 0 + ENTER for Exit!"; 
	public static final String OUT_OF_BORDER = "Out of borders!";
	public static final String ARE_YOU_SURE_EXIT = "Are you sure to exit? yes/no";
	public static final String SAVE_THE_GAME = "Save the game before exit? yes/no";
	public static final String YES = "yes";
	public static final String NO = "no";
	public static final String SAVED_SUCCESS = "Game saved successfully";
	public static final String WELCOME ="Welcome ";
	public static final String RESUME_GAME = "Resume to any of your previous game from below list!";
	public static final String GAME_MENU_OPTION = "Select Game to resume , To start a new game type new + Enter";
	public static final String EXPERIENCE_POINT = "Experience point = ";
	public static final String NO_SAVED_GAME = "You don't have any saved game!";
	public static final String GOODBYE = "Thanks for playing the game . Goodbye !:)";
	public static final String EXPLORING ="exploring ";
	public static final String USER_OPTION_NEW_GAME="new";
	public static final String SPACE=" ";
	public static final String GAME_PLAYERS_DATA = "game_records/game_players.data";
	public static final String GAME_SAVED_DATA = "game_records/saved_games.data";
	public static final String MAP_COMPLETE ="Congrats! you have Completed the map,To Exit Press 0 for New Game type new";
	public static final String GAME_CHARACTER_PROP_FILE = "/data/game_characters.properties";
	public static final String GAME_ENEMY_PROP_FILE = "/data/game_enemy_characters.properties";
	public static final String GAME_MAP_PROP_FILE = "/data/game_map.properties";
	public static final String GAME_QUESTION_PROP_FILE = "/data/game_questions.properties";

	public static final String GAME_CHARACTER_NAME_PATTERN = "CHARACTER_";
	public static final String GAME_MAP_PATTERN = "MAP_";
	public static final	String GAME_MAP_NAME="Name";
	public static final	String GAME_MAP_BORDERX="BorderX";
	public static final	String GAME_MAP_BORDERY="BorderY";
	public static final	String GAME_MAP_QUESTION_PATTERN="Question_";	
	public static final String WELCOME_MSG = "Welcome to ";
	public static final String LOGIN_DETAILS = "Press 1 for login or press 2 for sign-up";
	public static final String ARROW = " -> ";
	public static final String SPACE_FOR_MAP = "       ";
	public static final String YES_OR_NO="yes/no";
	public static final boolean isVisited = Boolean.TRUE;
	public static final String FILE_DATA_DELIMITER = ":";
	public static final String UNDERSCORE = "_";
	public static final String ENCODING = "UTF-8";
	public static final Locale LOCAL_EN = Locale.ENGLISH;
	public static final String PWD_PADDER = "0";
	public static final String DELIMITER_SPACE=" ";
	public static final String DELIMITER_QADELIMIT="#";
	public static final String STAR="*";	
	public static final String GAME_TITLE="GameTitle";
	public static final String GAME_TOPIC;
	
	static {
		GAME_TOPIC = ResourceUtil.getPropertyFromPropFile(GAME_MAP_PROP_FILE,Constants.GAME_TITLE);
	}
	public static final String EXIT = "0";
	public static final String USER_OPTION_LOGIN = "1";
	public static final String USER_OPTION_SIGNUP = "2";
	public static final String NO_MOVE = "0";
	public static final String DEFAULT_VAL = "-1";
	public static final String MOVE_RIGTH = "3";
	public static final String MOVE_LEFT = "4";
	public static final String MOVE_UP = "1";
	public static final String MOVE_DOWN = "2";
	public static final String ONE = "1";
	public static final String ZERO = "0";
	




}
