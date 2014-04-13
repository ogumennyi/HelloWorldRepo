package iShop.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errMsg;
	
	public CustomException(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public String getErrMsg() {
		return errMsg;
	}
	
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
