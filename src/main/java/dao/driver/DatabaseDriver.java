package dao.driver;

import java.sql.*;
import java.util.List;

public class DatabaseDriver
{
    private String driver;
    private String serverPath;
    private String serverPort;
    private String databaseName;
    private String userName;
    private String passWord;

    private Connection con = null;
    private PreparedStatement ps = null;

    private Log driverLog;

    public DatabaseDriver(String serverPath, String serverPort, String databaseName, String userName, String passWord)
    {
        driverLog = new Log();

        this.serverPath = serverPath;
        this.serverPort = serverPort;
        this.databaseName = databaseName;
        this.userName = userName;
        this.passWord = passWord;

        driverLog.record(String.format("Initialized with \"Path:%s, Port:%s, Database:%s, User:%s, Password:%s\".",
                serverPath, serverPort, databaseName, userName, passWord));
    }

    public int connect()
    {
        try
        {
            //加载驱动
            Class.forName("org.postgresql.Driver");
            //定义地址
            String url = String.format("jdbc:postgresql://%s:%s/%s",
                    serverPath, serverPort, databaseName);
            driverLog.record(String.format("Connecting to %s...", url));
            //建立连接
            con = DriverManager.getConnection(url, userName, passWord);
        }
        catch (SQLException | ClassNotFoundException e)
        {
            driverLog.record(e.toString());
            driverLog.record("Connection failed.");
            return -1;
        }
        driverLog.record("Connection succeeded.");
        return 0;
    }

    public int close()
    {
        try
        {
            con.close();
        }
        catch (Exception e)
        {
            driverLog.record(e.getStackTrace().toString());
            driverLog.record("Connection closed failed.");
            return -1;
        }
        driverLog.record("Connection is closed.");
        return 0;
    }

    public int execUpdate(String statement)
    {
        int returnValue;
        try
        {
            driverLog.record(String.format("Executing SQL statement: %s.", statement));
            ps = con.prepareStatement(statement);
            returnValue = ps.executeUpdate();
        }
        catch (SQLException e)
        {
            driverLog.record(e.getStackTrace().toString());
            driverLog.record("Execution failed.");
            return -1;
        }
        driverLog.record("Execution succeeded.");
        return returnValue;
    }

    public int execQuery(String statement, List<QueryItem> result)
    {
        int returnValue = 0;
        try
        {
            driverLog.record(String.format("Executing SQL statement: %s.", statement));
            ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while(rs.next())
            {
                QueryItem queryItem = new QueryItem();
                for(int i = 1; i <= columnCount; i++)
                {
                    queryItem.set(md.getColumnName(i), rs.getObject(i).toString());
                }
                result.add(queryItem);
            }
            rs.close();
        }
        catch (SQLException e)
        {
            driverLog.record(e.getStackTrace().toString());
            driverLog.record("Execution failed.");
            return -1;
        }
        driverLog.record("Execution succeeded.");
        return returnValue;
    }
}
