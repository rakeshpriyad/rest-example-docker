package raj.aayush.example.service.exception;

public class BookAlreadyExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookAlreadyExistsException(){
		super();
	}
	
	public BookAlreadyExistsException(String msg){
		super(msg);
	}
}
