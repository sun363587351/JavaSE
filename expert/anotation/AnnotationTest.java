package expert.anotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with ExpertTest.
 * User: IFT8
 * Date: 2014/10/15 16:59
 */

//元注解
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTest {
    String contentDef() default "content";
    String content();
    int[] arrayInt();
}
