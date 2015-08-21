package expert.anotation;

/**
 * Created with ExpertTest.
 * User: IFT8
 * Date: 2014/10/13 21:52
 */
@AnnotationTest(contentDef = "def",content = "cont",arrayInt = {1,2,3})
public class JavaDocTest {
   //去过时提示
   @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        DeprecatedClass m1 = new DeprecatedClass();

        //过时方法
        m1.sayHello();

       AnnotationTest annotationTest=JavaDocTest.class.getAnnotation(AnnotationTest.class);
       int[] arryInt=annotationTest.arrayInt();
       for (int i : arryInt) {
           System.out.println(i);
       }
    }
}

class DeprecatedClass {

    @Deprecated
    public void sayHello() {
        System.out.println("hello");
    }

}