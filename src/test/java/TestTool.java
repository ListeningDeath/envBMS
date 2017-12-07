import dao.driver.RecordDriver;
import service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestTool
{
    public static void main(String args[])
    {
        DatabaseOperator dbOperator = new DatabaseOperator("192.168.237.134", "5432",
                "env_db", "postgres", "postgres");
        dbOperator.connect();
        Company company = dbOperator.company;
        List<RecordDriver> list = new ArrayList<>();
        RecordDriver record = new RecordDriver();
        record.set("id", UUID.randomUUID());
        record.set("name", "test_name");
        record.set("reg_address", "test_reg_address");
        record.set("address", "test_address");
        record.set("credit_code", "012345678901234567");
        record.set("rep_person", "test_rep_person");
        list.add(record);
        company.add(list);
        dbOperator.close();
    }
}
