import service.*;

public class TestTool
{
    public static void main(String args[])
    {
        DatabaseOperator dbOperator = new DatabaseOperator("192.168.237.134", "5432",
                "env_db", "postgres", "postgres");
        dbOperator.connect();
        dbOperator.close();
    }
}
