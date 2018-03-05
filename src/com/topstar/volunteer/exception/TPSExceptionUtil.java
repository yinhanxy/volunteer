package com.topstar.volunteer.exception;

class TPSExceptionUtil {
    
    public static String buildMessage(String message, Throwable cause) {
        if (cause != null) {
            StringBuffer buf = new StringBuffer();
            if (message != null) {
                buf.append(message).append("; ");
            }
            buf.append("tps exception is ").append(cause);
            return buf.toString();
        }
        else {
            return message;
        }
    }
    
    public static String buildClientMessage(String message, Throwable cause) {
        if (cause != null) {
            StringBuffer buf = new StringBuffer();
            if (message != null) {
                buf.append(message).append("; ");
            }
            buf.append("tps client exception is ").append(cause);
            return buf.toString();
        }
        else {
            return message;
        }
    }
}
