
package AOP;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

// Point1 : @Aspect
//um Protokollnachrichten auszugeben
@EnableAspectJAutoProxy
@ComponentScan 
@Aspect
@Component
public class LogAspect {
// Point 2: AOP implementation

@Before("execution(* com.example.demo2.login.LoginController.getLogin(..))")
public void startLog(JoinPoint jp) {
    System.err.println("Method start: " + jp.getSignature());
}

@After("execution(* com.example.demo2.login.LoginController.getLogin(..))")
public void endLog(JoinPoint jp) {
    System.err.println("Method end: " + jp.getSignature());
}

}
