import dao.driver.RecordDriver;
import service.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TestTool
{
    public static void main(String args[])
    {
        DatabaseOperator dbOperator = new DatabaseOperator("192.168.237.134", "5432",
                "env_db", "postgres", "postgres");
        dbOperator.connect();
        Company company = dbOperator.table.company;
//        List<RecordDriver> list = new ArrayList<>();
//        RecordDriver record = new RecordDriver();
//        record.set("id", UUID.randomUUID());
//        record.set("name", "test_name");
//        record.set("reg_address", "test_reg_address");
//        record.set("address", "test_address");
//        record.set("credit_code", "012345678901234567");
//        record.set("rep_person", "test_rep_person");
//        list.add(record);
        company.insert(UUID.randomUUID().toString(), "test_name", "test_reg_address", "test_address",
                new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "012345678901234567", "test_rep_person", "test_tech_person", "test_tech_person_contact");
        dbOperator.close();
    }
}
