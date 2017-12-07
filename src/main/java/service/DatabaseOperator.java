package service;

import dao.driver.DatabaseDriver;
import dao.driver.RecordDriver;
import dao.driver.Log;

import java.util.List;

public class DatabaseOperator
{
    private Log serviceLog;
    private DatabaseDriver driver;
    public Company company;

    public DatabaseOperator(String serverPath, String serverPort, String databaseName, String userName, String passWord)
    {
        serviceLog = new Log();

        driver = new DatabaseDriver(serverPath, serverPort, databaseName, userName, passWord);
        company = new Company(driver);
    }

    public int connect()
    {
        return driver.connect();
    }

    public int close()
    {
        return driver.close();
    }

    public int execUpdate(String statement)
    {
        return driver.execUpdate(statement);
    }

    public int execQuery(String statement, List<RecordDriver> result)
    {
        return driver.execQuery(statement, result);
    }

}
