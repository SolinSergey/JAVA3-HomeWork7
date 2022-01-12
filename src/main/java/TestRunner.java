import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.PriorityQueue;

public class TestRunner {
    public static void main(String[] args) {
        start(ToTest.class, new ToTest());
    }

    public static void start (Class<?> testClass, Object object){
        runOnce(testClass,object,BeforeSuite.class);
        startTests(testClass,object);
        runOnce(testClass,object,AfterSuite.class);
    }

    private static void startTests(Class<?> testClass, Object object){
        Method[] methods = testClass.getDeclaredMethods();
        PriorityQueue<PrioritizedMethod> prioritizedMethods = new PriorityQueue<PrioritizedMethod>();
        for (Method method:methods){
            if (method.getAnnotation(Test.class) != null){
                Test annotation = method.getAnnotation(Test.class);
                prioritizedMethods.add(new PrioritizedMethod(method, annotation.priority()));

            }
        }
        while (!prioritizedMethods.isEmpty()){
            try{
                prioritizedMethods.remove().getMethod().invoke(object);
            }
            catch (IllegalAccessException | InvocationTargetException e){
                e.printStackTrace();
            }
        }
    }

    private static <T extends Annotation> void runOnce (Class<?> testClass, Object testObject, Class<T> annotationClass){
        Method[] methods = testClass.getDeclaredMethods();
        boolean isRun = false;
        for (Method method:methods){
            if (method.getAnnotation(annotationClass)!=null){
                try{
                    if (!isRun){
                        method.invoke(testObject);
                        isRun=true;
                    }
                    else{
                        throw new RuntimeException("More than one method annotated" + annotationClass.getSimpleName() + "found");
                    }
                }
                catch(IllegalAccessException | InvocationTargetException e){
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
