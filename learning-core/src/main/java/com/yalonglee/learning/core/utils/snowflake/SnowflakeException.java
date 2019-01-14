package com.yalonglee.learning.core.utils.snowflake;

public class SnowflakeException extends RuntimeException {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -27048199131316992L;

    /**
     * Default constructor
     */
    public SnowflakeException() {
        super();
    }

    /**
     * Constructor with message & cause
     *
     * @param message
     * @param cause
     */
    public SnowflakeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with message
     *
     * @param message
     */
    public SnowflakeException(String message) {
        super(message);
    }

    /**
     * Constructor with message format
     *
     * @param msgFormat
     * @param args
     */
    public SnowflakeException(String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
    }

    /**
     * Constructor with cause
     *
     * @param cause
     */
    public SnowflakeException(Throwable cause) {
        super(cause);
    }

}
