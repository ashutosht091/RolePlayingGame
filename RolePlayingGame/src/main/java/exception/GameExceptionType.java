package exception;
/**
 * This class holds information about all error codes and their description
 * @author ashutosh
 *
 */
public enum GameExceptionType {

	USER_ALREADY_EXIST(100,"User already Exist"),
	USERNAME_ERROR(101,"No Such User registered . Please sign-up"),
	PASSWORD_INCORRECT(102,"Password is incorrect"),
	FILE_WRITE_ERROR(200,"There is an error while writing data to file"),
	FILE_READ_ERROR(201,"There is an error while reading data from file "),
	GAME_SAVE_ERROR(300,"Problem while saving game"),
	ERROR(001,"Internal Error! Please Try Again");
	private final int errorCode;
	private final String msg;

	GameExceptionType(int errorCode, String msg) {
		this.errorCode = errorCode;
		this.msg = msg;
	}

	public int getErrorCode() {
		return this.errorCode;
	}

	public String getMsg() {
		return this.msg;
	}

}
