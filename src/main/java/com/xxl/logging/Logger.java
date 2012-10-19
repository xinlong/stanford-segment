package com.xxl.logging;

public interface Logger {

    public void trace(String message);

    public void trace(String format, Object... args);

    public void trace(String message, Throwable t);

    public void debug(String message);

    public void debug(String format, Object... args);

    public void debug(String message, Throwable t);

    public void info(String message);

    public void info(String format, Object... args);

    public void info(String message, Throwable t);

    public void warn(String message);

    public void warn(String format, Object... args);

    public void warn(String message, Throwable t);

    public void error(String message);

    public void error(String format, Object ... args);

    public void error(String message, Throwable t);

}
