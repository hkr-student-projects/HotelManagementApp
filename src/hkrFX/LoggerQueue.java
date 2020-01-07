package hkrFX;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;

public class LoggerQueue {

    public static LoggerQueue Resource = new LoggerQueue();
    private static Object lock = new Object();
    private ArrayDeque<LogEntry> logEntryQueue = new ArrayDeque<>();

    public void enqueue(LogEntry le)
    {
        synchronized (lock)
        {
            logEntryQueue.addLast(le);
        }
    }

    public synchronized void processLog(){

        while (true){
            Boolean flag = false;

            if(logEntryQueue.size() <= 0)
                break;
            if(logEntryQueue.size() > 1)
                flag = true;

            process(logEntryQueue.pollFirst());

            if(flag && logEntryQueue.size() <= 0)
                break;
        }
    }

    private void process(LogEntry entry){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("App.log", true))) {

            writer.write("["+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(new Date(System.currentTimeMillis())) +"]" +
                    " [" + entry.logType.toString() + "] " + entry.message + "");
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
