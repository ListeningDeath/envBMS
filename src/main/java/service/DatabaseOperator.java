package service;

import dao.driver.DatabaseDriver;
import dao.driver.QueryItem;

import java.util.List;

public class DatabaseOperator
{
    private DatabaseDriver driver = null;

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

    public int select(String[] fields, String[] tables, String condition, List<QueryItem> result)
    {
        String fieldPart;
        String tablePart;
        String conditionPart;
        String statement;
        if (fields.length == 0)
        {
            fieldPart = "select *";
        }
        else
        {
            fieldPart = "select " + String.join(",", fields);
        }
        if (tables.length == 0)
        {
            tablePart = "from pg_tables";
        }
        else
        {
            tablePart = "from " + String.join(",", tables);
        }
        if (condition == "")
        {
            conditionPart = "";
        }
        else
        {
            conditionPart = "where" + condition;
        }
        statement = String.join(" ", new String[]{fieldPart, tablePart, conditionPart});
        return execQuery(statement, result);
    }
}
