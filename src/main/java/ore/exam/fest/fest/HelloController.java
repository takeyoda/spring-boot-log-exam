package ore.exam.fest.fest;

import java.util.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@RestController
public class HelloController {
  private static final Logger log = LoggerFactory.getLogger(HelloController.class);

  public HelloController() {
    log.info("REDIS_HOST=" + System.getenv("REDIS_HOST"));
    log.info("REDIS_PORT=" + System.getenv("REDIS_PORT"));
  }

  @GetMapping(value="/hello")
  public Map hello(HttpSession session, HttpServletRequest request) {
    MDC.put("sid", session.getId());
    MDC.put("ua", request.getHeader("User-Agent"));
    MDC.put("ip", request.getRemoteAddr()); // TODO X-Forwarded-For
    try {
      log.info("[HOGE]access: " + new Date());
      try {
        throw new Exception("this is error log test");
      } catch (Exception e) {
        log.error("[HOGE]dame", e);
      }
      final Map res = new HashMap();
      res.put("aaa", 1);
      res.put("bbb", 2);
      res.put("sid", session.getId());
      return res;
    } finally {
      MDC.clear();
    }
  }

  @GetMapping(value="/login")
  public String login(HttpSession session) {
    session.setAttribute("hoge", 1);
    session.setAttribute("now", new Date());
    return "logout OK";
  }

  @GetMapping(value="/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "logout OK";
  }
}
