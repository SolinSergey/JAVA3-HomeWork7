import java.lang.reflect.Method;

public class PrioritizedMethod implements Comparable <PrioritizedMethod> {
    private final Method method;
    private final byte priority;

    public PrioritizedMethod (Method method,byte priority){
        this.method=method;
        this.priority=priority;
    }
    public Method getMethod(){
        return method;
    }

    public byte getPriority(){
        return priority;
    }

    @Override
    public int compareTo(PrioritizedMethod o){
        return Byte.compare(o.getPriority(),this.getPriority());
    }
}
