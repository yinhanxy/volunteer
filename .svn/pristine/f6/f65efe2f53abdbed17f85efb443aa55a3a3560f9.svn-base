package com.topstar.volunteer.exception;

/**  
 * 文件名称：TPSException.java</p> 
 * 类说明：自定义异常类
 */

public class TPSException extends Exception {
    

    private static final long serialVersionUID = -6654841948080460521L;
    

    public TPSException(String message) {
        super(message);
    }
    
    public TPSException(Throwable cause){
        super(cause);
    }

    public TPSException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public String getMessage(){
        return super.getMessage();
    }

    public String getLocalizedMessage() {
        return TPSExceptionUtil.buildMessage(super.getMessage(), getCause());
    }


    public Throwable getRootCause() {
        Throwable rootCause = null;
        Throwable cause = getCause();
        while (cause != null && cause != rootCause) {
            rootCause = cause;
            cause = cause.getCause();
        }
        return rootCause;
    }

    public Throwable getMostSpecificCause() {
        Throwable rootCause = getRootCause();
        return (rootCause != null ? rootCause : this);
    }

    @SuppressWarnings("rawtypes")
    public boolean contains(Class exType) {
        if (exType == null) {
            return false;
        }
        if (exType.isInstance(this)) {
            return true;
        }
        Throwable cause = getCause();
        if (cause == this) {
            return false;
        }
        if (cause instanceof TPSException) {
            return ((TPSException) cause).contains(exType);
        }
        else {
            while (cause != null) {
                if (exType.isInstance(cause)) {
                    return true;
                }
                if (cause.getCause() == cause) {
                    break;
                }
                cause = cause.getCause();
            }
            return false;
        }
    }
}
