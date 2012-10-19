package com.xxl.logging;

import java.io.Serializable;

public class Slf4jLogger implements Logger,Serializable {

    /**
	 *
	 */
	private static final long serialVersionUID = 4524002710913214310L;
	org.slf4j.Logger logger;

    public Slf4jLogger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    @Override
    public void trace(String message) {
        if(logger.isTraceEnabled())
            logger.trace(message);
    }

    @Override
    public void trace(String format, Object... args) {
        logger.trace(String.format(format, args));
    }

    @Override
    public void trace(String message, Throwable t) {
        logger.trace(message, t);
    }

    @Override
    public void debug(String message) {
        if(logger.isDebugEnabled())
            logger.debug(message);
    }

    @Override
    public void debug(String format, Object... args) {
        logger.debug(String.format(format, args));

    }

    @Override
    public void debug(String message, Throwable t) {
        logger.debug(message, t);
    }

    @Override
    public void info(String message) {
        if(logger.isInfoEnabled())
            logger.info(message);
    }

    @Override
    public void info(String format, Object... args) {
        logger.info(String.format(format, args));
    }

    @Override
    public void info(String message, Throwable t) {
        logger.info(message, t);
    }

    @Override
    public void warn(String message) {
        if(logger.isWarnEnabled())
            logger.warn(message);
    }

    @Override
    public void warn(String format, Object... args) {
        logger.warn(String.format(format, args));
    }

    @Override
    public void warn(String message, Throwable t) {
        logger.warn(message, t);
    }

    @Override
    public void error(String message) {
        if(logger.isErrorEnabled())
            logger.error(message);
    }

    @Override
    public void error(String format, Object... args) {
        logger.error(String.format(format, args));
    }

    @Override
    public void error(String message, Throwable t) {
        logger.error(message, t);
    }

}
