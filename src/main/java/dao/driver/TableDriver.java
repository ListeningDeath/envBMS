package dao.driver;

import java.util.List;
import java.util.Set;

public class TableDriver
{
    private DatabaseDriver databaseDriver;
    public String name;
    public List<FieldDriver> fields;

    public TableDriver(DatabaseDriver databaseDriver, String tableName)
    {
        this.name = tableName;
        this.databaseDriver = databaseDriver;
    }

    public int add(List<RecordDriver> records)
    {
        String columnNames = "";
        StringBuilder recordContent = new StringBuilder();
        StringBuilder allRecordContents = new StringBuilder();
        if(records.size() == 0)
        {
            allRecordContents.append("DEFAULT");
        }
        else
        {
            Set<String> fields = records.get(0).getFields();
            columnNames = String.join(", ", fields);
            for(RecordDriver record : records)
            {
                if(recordContent.length() != 0)
                {
                    recordContent.append(", ");
                }
                for(String fieldName : fields)
                {
                    if(recordContent.length() != 0)
                    {
                        recordContent.append(", ");
                    }
                    String valueString = String.format("'%s'", record.get(fieldName).toString());
                    recordContent.append(valueString);
                }
                allRecordContents.append(String.format("(%s)", recordContent));
            }
        }
        String statement = String.format("INSERT INTO %s (%s) VALUES %s;", name, columnNames, allRecordContents);
        return databaseDriver.execUpdate(statement);
    }

    public int del()
    {
        return 0;
    }

    public int update()
    {
        return 0;
    }

    public int query()
    {
        return 0;
    }
}
