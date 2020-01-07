package hkrDB;

import java.lang.reflect.Method;

public class QueryThread extends Thread {

    private Method method;
    private Object[] args;

    public QueryThread(String name, Method method, Object... args){
        super(name);
        this.method = method;
        this.args = args;
    }

    public void run(){
        QueryQueue.Resource.enqueue(method, args);
        QueryQueue.Resource.processLog();
    }
}
