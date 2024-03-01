package alde.framework;

public class ALDEException extends Exception {
    public ALDEException(String message) {
        super(message);
    }

    public ALDEException(String message, Throwable cause) {
        super(message, cause);
    }

    public ALDEException(Throwable cause) {
        super(cause);
    }

    protected ALDEException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
