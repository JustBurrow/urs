/**
 *
 */
package kr.lul.urs.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * URS 애플리케이션 기동 코드.
 *
 * @author Just Burrow just.burrow@lul.kr
 */
@SpringBootApplication
public class Runner {
  /**
   * @param args
   */
  public static void main(String[] args) {
    @SuppressWarnings("unused")
    ApplicationContext context = SpringApplication.run(Runner.class, args);
  }
}
