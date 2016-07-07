/**
 *
 */
package kr.lul.urs.util.slf4j;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 로거 선택, 로그 레벨의 활성화 여부 확인을 대신 처리한다.
 * 로거 선택은 로그를 남기려고 한 클래스의 이름을 사용한다.
 *
 * @since 2016. 7. 6.
 * @author Just Burrow just.burrow@lul.kr
 */
public abstract class Logging {
  private static final Map<String, Logger> loggerCache = new HashMap<>();

  /**
   * @return
   * @since 2016. 7. 6.
   */
  private static Logger getLogger() {
    String loggerName = Thread.currentThread().getStackTrace()[3].getClassName();

    Logger logger;
    synchronized (loggerCache) {
      logger = loggerCache.get(loggerName);
      if (null == logger) {
        logger = LoggerFactory.getLogger(loggerName);
        loggerCache.put(loggerName, logger);
      }
    }

    return logger;
  }

  /**
   * @param message
   * @since 2016. 7. 7.
   */
  public static void trace(String message) {
    Logger log = getLogger();
    if (log.isTraceEnabled()) {
      log.trace(message);
    }
  }

  /**
   * @param message
   * @param e
   * @since 2016. 7. 7.
   */
  public static void trace(String message, Throwable e) {
    Logger log = getLogger();
    if (log.isTraceEnabled()) {
      log.trace(message, e);
    }
  }

  /**
   * @param message
   * @since 2016. 7. 6.
   */
  public static void debug(String message) {
    Logger log = getLogger();
    if (log.isDebugEnabled()) {
      log.debug(message);
    }
  }

  /**
   * @param message
   * @param e
   * @since 2016. 7. 6.
   */
  public static void debug(String message, Throwable e) {
    Logger log = getLogger();
    if (log.isDebugEnabled()) {
      log.debug(message, e);
    }
  }

  /**
   * @param message
   * @since 2016. 7. 7.
   */
  public static void info(String message) {
    Logger log = getLogger();
    if (log.isInfoEnabled()) {
      log.info(message);
    }
  }

  /**
   * @param message
   * @param e
   * @since 2016. 7. 7.
   */
  public static void info(String message, Throwable e) {
    Logger log = getLogger();
    if (log.isInfoEnabled()) {
      log.info(message, e);
    }
  }

  /**
   * @param message
   * @since 2016. 7. 7.
   */
  public static void warn(String message) {
    Logger logger = getLogger();
    if (logger.isWarnEnabled()) {
      logger.warn(message);
    }
  }

  /**
   * @param message
   * @param e
   * @since 2016. 7. 7.
   */
  public static void warn(String message, Throwable e) {
    Logger logger = getLogger();
    if (logger.isWarnEnabled()) {
      logger.warn(message, e);
    }
  }

  /**
   * @param message
   * @since 2016. 7. 7.
   */
  public static void err(String message) {
    Logger logger = getLogger();
    if (logger.isErrorEnabled()) {
      logger.error(message);
    }
  }

  /**
   * @param message
   * @param e
   * @since 2016. 7. 7.
   */
  public static void err(String message, Throwable e) {
    Logger logger = getLogger();
    if (logger.isErrorEnabled()) {
      logger.error(message, e);
    }
  }

  protected Logging() {
    throw new UnsupportedOperationException();
  }
}
