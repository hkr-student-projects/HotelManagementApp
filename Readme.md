# Hotel Management App

A project is a prototype of a hotel management application that stores data on local database and uses GUI to interact with a user.

## User Interface
![alt text](https://github.com/Group4-ProjectCourse/HotelManagementApp/blob/master/img/logcus.png = 150x115)
![alt text](https://github.com/Group4-ProjectCourse/HotelManagementApp/blob/master/img/emplog.png = 150x115)
![alt text](https://github.com/Group4-ProjectCourse/HotelManagementApp/blob/master/img/cusper.png = 150x115)
![alt text](https://github.com/Group4-ProjectCourse/HotelManagementApp/blob/master/img/empper.png = 150x115)
![alt text](https://github.com/Group4-ProjectCourse/HotelManagementApp/blob/master/img/cus2per.png = 150x115)
![alt text](https://github.com/Group4-ProjectCourse/HotelManagementApp/blob/master/img/emp2per.png = 150x115)
![alt text](https://github.com/Group4-ProjectCourse/HotelManagementApp/blob/master/img/cus3per.png = 150x115)
![alt text](https://github.com/Group4-ProjectCourse/HotelManagementApp/blob/master/img/emp3per.png = 150x115)
To crate the app's GUI, JavaFX libraries were used. The major parts of the project are classes containing definition for different stages.

Additionally, some classes were used in both: Customer and Employee panels, but with different field values e.g: background color field and permissions. 

Employee panel is a polymorphic customer, who can become any of existing customers and make bookings for that customer.
#
#### List of entities in hkrFX package

  - [x] Addbooking
  - [x] BookingsInfo
  - [x] Config
  - [x] CreateCustomer
  - [x] FxmlConverter
  - [x] HomeStage
  - [x] Injection
  - [x] Localization
  - [x] Logger 
    * ELogType
    * LogEntry
  - [x] LoggerQueue
  - [x] LogTread
  - [x] LoginCus
  - [x] LoginEmp
  - [x] MainFX
    * IDeserializable
    * ISerializable
  - [x] PersonalArea
  - [x] PopUP
  - [x] Translator
  
  
## Database activity
We decided to store data on local database, there is a file called __config.json__ that stores database connection credentials to communiate with database server.

#### List of entities in hkrDB package

  - [x] DatabaseManager
  - [ ] QueryQueue
  - [ ] QueryThread

Default .json file looks like: 
```json
[
  {
    "DatabaseUsername":"root",
    "DatabasePort":3306,
    "DatabasePassword":"P@sw0rd314",
    "DatabaseName":"hotel",
    "lang":"en",
    "DatabaseAddress":"jdbc:mysql://localhost/hotel?&allowMultiQueries=true&serverTimezone=Europe/Stockholm&useSSL=false"
  }
]
```

For example the method used for selecting available rooms during some timespan looks like:
```java
public ObservableList<String> getAvailableRooms(LocalDate movein, LocalDate moveout){
        ArrayList<String> arooms = new ArrayList<>();
        try (Connection conn = createConnection()){

            PreparedStatement pst = conn.prepareStatement("SELECT `Room_number` As Room " +
                    "FROM hotel.BookedRoom, hotel.Booking " +
                    "WHERE hotel.BookedRoom.Booking_reference = hotel.Booking.reference AND `Room_number` NOT IN " +
                    "(SELECT `Room_number` " +
                    "FROM hotel.BookedRoom, hotel.Booking " +
                    "WHERE hotel.BookedRoom.Booking_reference = hotel.Booking.reference AND " +
                    "(" +
                    "(('"+ movein +"' >= `movein` AND '"+ movein +"' < `moveout`) OR ('"+ moveout +"' >= `movein` AND '"+ moveout +"' <= `moveout`)) " +
                    "    OR " +
                    "(`movein` >= '"+ movein +"' AND `moveout` <= '"+ moveout +"') " +
                    "))" +
                    "UNION SELECT `number`" +
                    "FROM hotel.Room " +
                    "WHERE `number` " +
                    "NOT IN (SELECT `Room_number` " +
                    "FROM hotel.BookedRoom) " +
                    "ORDER BY Room;");

            boolean isResult = pst.execute();
            do {
                try (ResultSet rs = pst.getResultSet()) {

                    while (rs.next()) {

                        arooms.add(rs.getString(1));
                    }

                    isResult = pst.getMoreResults();
                }

            } while (isResult);
        }
        catch(Exception ex){
            Logger.logException(ex);
        }
        return FXCollections.observableArrayList(arooms);
    }
```

## Implemented interesting features

#### Logger 
This tool is used to log different types of instances *(simple message, exception, error or warning)* and store them in __App.log__ file. 

For example I decided to log a __simple message__, 
the following method will be used to log a message:
```java
public static void log(String message){
        writeConsole(message, ELogType.Log);
    }
```

That will call for __writeConsole(String, ElogType)__:
```java
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
                out.print(logType.Exception.getCode() + message + "\u001B[0m");
                new LogThread("logExceptionThread", new LogEntry(message, logType)).start();
                break;
            default:
                out.println("\u001B[0m" + message + "\u001B[0m");
                break;
        }
    }
```
This block of code actually could be improved so we don't even need a switch pattern here.

One version is by using ternary expression: logType

    logType == ELogType.Log ? "logThread" : logType == ELogType.Error ? "logErrorThread" : logType == ELogType.Exception ? "logExceptionThread" : "logWarningThread"
But still, this looks very inefficient. So, the other version will be having some local variables that stores: *color*, *ELogType*, *message* 
to make things "look simplier" because we would not need a creation of 4  instances of __LogThread__.

The idea behind this is still the defining the type of ELogType to pick appropriate color, so for me the idea of storing data as local variable looks fine at the moment.

#
#### QueryQueue
This is unimplemented cool feature that would store queries in a list of waiting processes to be performed
one by one. The feature was implemented in __LoggerQueue__ class that performs logs one-by-one by picking each from the queue of logs.

*(Logging is performed asynchronously unlike query execution in Database part which is a shame for such a project, in future this will be finished wisely and fixed.)*

```java
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
```

- "Resource" - is a one for all instances fields for accessing the LoggerQueue.
- "lock" - actually could be any kind of object just to serve like a cork in a bottle that restricts access to the resource (*App.log file*) until the current log is finished to be written in.
- "logEntryQueue" - a queue of logs to be processed.

#
Method to actually process the log.
```java
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
```

A keyword __synchronized__ means that this method will be locked until the operation inside is finished 
(*in our case a write-in-file (App.log)*).
#
The method that is called by synchronized method:
```java
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
```
*to be continued..*

