package dao.driver;

import java.text.DateFormat;
import java.util.Date;

public class Log
{
    public void record(String logStr)
    {
        String dateTime = DateFormat.getDateTimeInstance().format(new Date());
        StringBuilder logLine = new StringBuilder(dateTime);
        logLine.append("\t");
        logLine.append(logStr);
        System.out.println(logLine);
    }
}
