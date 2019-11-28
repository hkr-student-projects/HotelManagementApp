package hkrFX;

import static java.lang.System.out;

enum ELogType{

    Error("\u001B[31m"),
    Warning("\u001B[33m"),
    Log("\u001B[0m"),
    Exception("\u001B[31m");
    //more

    private String code;

    ELogType(String code){
        this.code = code;
    }
    public String getCode(){ return code;}
}

class LogEntry{
    String message;
    ELogType logType;

    public LogEntry(String message, ELogType logType){
        this.message = message;
        this.logType = logType;
    }
}

 public class Logger {


    public static void Log(String message){
        writeConsole(message, ELogType.Log);
    }

    public static void LogWarning(String message){
        writeConsole(message, ELogType.Warning);
    }

    public static void LogError(String message){
        writeConsole(message, ELogType.Error);
    }

    public static void LogException(String message){
        writeConsole(message, ELogType.Exception);
    }

    private static void writeConsole(String message, ELogType logType){

        switch (logType){
            case Log:
                out.println(logType.Log.getCode() + message + "\u001B[0m");
                new LogThread("logThread", new LogEntry(message, logType)).start();
                break;
            case Error:
                out.println(logType.Error.getCode() + message + "\u001B[0m");
                new LogThread("logErrorThread", new LogEntry(message, logType)).start();
                break;
            case Warning:
                out.println(logType.Warning.getCode() + message + "\u001B[0m");
                new LogThread("logWarningThread", new LogEntry(message, logType)).start();
                break;
            case Exception:
                out.println(logType.Exception.getCode() + message + "\u001B[0m");
                new LogThread("logExceptionThread", new LogEntry(message, logType)).start();
                break;
            default:
                out.println("\u001B[0m" + message + "\u001B[0m");
                break;
        }
    }
}
