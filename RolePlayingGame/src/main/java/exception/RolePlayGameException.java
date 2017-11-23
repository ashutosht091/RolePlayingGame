package exception;
/**
 * This is a custom Exception class used to 
 * @author ashutosh
 *
 */
public class RolePlayGameException extends Exception {

	 /**
	 * 
	 */
	private static final long serialVersionUID = -9088320746660251727L;
	private  final  int errorCode;
	  private  final String errorMsg;

	  public RolePlayGameException(GameExceptionType code) {
	    this.errorMsg = code.getMsg();
	    this.errorCode = code.getErrorCode();
	  }

	  public int getErrorCode() {
	    return errorCode;
	  }

	  public String getErrorMsg() {
	    return errorMsg;
	  }

	@Override
	public String toString() {
		return "RolePlayGameException [errorCode=" + errorCode + ", errorMsg=" + errorMsg + "]";
	}
	
}
