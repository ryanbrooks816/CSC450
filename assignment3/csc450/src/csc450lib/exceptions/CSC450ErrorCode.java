package csc450lib.exceptions;

/**
 * Enumerated type to store error codes and companion messages.
 *
 */
public enum CSC450ErrorCode {
	// 0-99 error codes for generic errors
	NO_ERROR(0, "No error"),
	//
	UNKNOWN_ERROR(1, "Unknown CSC450Lib error"),

	// 100-199 error codes for calculus-related exceptions
	FUNCTION_NOT_DEFINED_AT_EVALUATION_POINT(100, "Function is not defined at evaluation point"),
	ARRAY_LENGTH_DOES_NOT_EQUAL_FUNCTION_DIMENSION(110, "Array size does not equal function dimension"),
	STARTING_POINT_BELOW_GROUND(111, "The starting point's value is below the ground elevation");

	// 200-299 error codes for linear algebra-related exceptions

	/**
	 * The error code
	 */
	private final int code_;

	/**
	 * The error message for each error code
	 */
	private final String message_;

	CSC450ErrorCode(int code, String message) {
		code_ = code;
		message_ = message;
	}

	public int getCode() {
		return code_;
	}

	public String getMessage() {
		return message_;
	}
}
