package controller;

public class InvalidFileException extends Exception {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	InvalidFileException(String s) {
		super(s);
	}
}