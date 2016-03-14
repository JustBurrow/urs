package kr.lul.urs.spring.jpa.listener;

public class IllegalJpaSupportConfigurationException extends RuntimeException {
  private static final long serialVersionUID = -1643100055513842459L;

  public IllegalJpaSupportConfigurationException() {
    super();
  }

  public IllegalJpaSupportConfigurationException(String message) {
    super(message);
  }

  public IllegalJpaSupportConfigurationException(Throwable cause) {
    super(cause);
  }
}
