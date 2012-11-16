package com.aimprosoft.common.exception;

/**
 * ApplicationException is the superclass of those
 * exceptions that can be thrown within this project
 *
 */
public abstract class ApplicationException extends Exception{

    /**
     * Constructs an VocabularyException with no
     * detail message.
     */
    protected ApplicationException() {
        super();
    }

    /**
     * Constructs an ApplicationException with the
     * specified detail message.
     *
     * @param message - the detail message.
     */
    protected ApplicationException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link Throwable#getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link Throwable#getCause()} method).  (A null value
     *         is permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
    protected ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     *
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link Throwable#getCause()} method).  (A null value
     *         is permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
    protected ApplicationException(Throwable cause) {
        super(cause);
    }
}
