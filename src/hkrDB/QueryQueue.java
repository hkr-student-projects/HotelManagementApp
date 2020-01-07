package hkrDB;

import com.mysql.cj.log.Log;
import hkrFX.Logger;
import hkrFX.MainFX;

import java.lang.reflect.Method;
import java.util.ArrayDeque;

public class QueryQueue {

    public static QueryQueue Resource = new QueryQueue();
    private static Object lock = new Object();
    private ArrayDeque<Method> methodQueue = new ArrayDeque<>();
    private ArrayDeque<Object[]> methodArgsQueue = new ArrayDeque<>();

    public void enqueue(Method m, Object[] args)
    {
        synchronized (lock)
        {
            methodQueue.addLast(m);
            methodArgsQueue.addLast(args);
        }
    }

    public synchronized void processLog(){

        while (true){
            Boolean flag = false;

            if(methodQueue.size() <= 0)
                break;
            if(methodQueue.size() > 1)
                flag = true;

            executeMethod(methodQueue.pollFirst(), methodArgsQueue.pollFirst());

            if(flag && methodQueue.size() <= 0)
                break;
        }
    }

    private void executeMethod(Method method, Object[] args){
        try {
            method.invoke(MainFX.databaseManager, args);
        }
        catch (Exception e){
            Logger.logException(e);
        }

    }
}
