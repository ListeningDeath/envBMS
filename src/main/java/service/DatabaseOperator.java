package service;

import dao.driver.DatabaseDriver;
import dao.driver.QueryItem;

import java.util.List;

public class DatabaseOperator
{
    private DatabaseDriver driver;

    public DatabaseOperator(String serverPath, String serverPort, String databaseName, String userName, String passWord)
    {
        driver = new DatabaseDriver(serverPath, serverPort, databaseName, userName, passWord);
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

    public int execQuery(String statement, List<QueryItem> result)
    {

        return driver.execQuery(statement, result);
    }
}
