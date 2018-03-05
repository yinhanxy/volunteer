package com.topstar.volunteer.exception;

/**
 * 客户端可见异常封装<br/>
 * 可在页面直接显示此异常的message
 * @author Liqy
 *
 */
public class TPSClientException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5066216276531836589L;

	public TPSClientException(String message) {
        super(message);
    }
    
    public TPSClientException(Throwable cause){
        super(cause);
    }

    public TPSClientException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public String getMessage(){
        return super.getMessage();
    }

    public String getLocalizedMessage() {
        return TPSExceptionUtil.buildClientMessage(super.getMessage(), getCause());
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
            return ((TPSClientException) cause).contains(exType);
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
