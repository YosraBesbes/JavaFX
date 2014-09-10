package ph.txtdis.exception;

public class InvalidException extends RuntimeException {
    
    private static final long serialVersionUID = -2704062424620965401L;

	public InvalidException(String message) {
		super(message);
	}
}
