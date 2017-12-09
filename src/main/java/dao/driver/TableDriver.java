package dao.driver;

import java.util.List;
import java.util.Set;
import java.util.UUID;

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

    private void buildRecordValues(RecordDriver record, StringBuilder recordContent, Set<String> fields)
    {
        for(String fieldName : fields)
        {
            if(recordContent.length() != 0)
            {
                recordContent.append(", ");
            }
            String valueString = String.format("'%s'", record.get(fieldName).toString());
            recordContent.append(valueString);
        }
    }

    private void buildColumnValues(RecordDriver columns, StringBuilder content)
    {
        for(String setField : columns.getFields())
        {
            if(content.length() != 0)
            {
                content.append(", ");
            }
            String setValue = columns.get(setField).toString();
            String setContent = String.format("%s='%s'", setField, setValue);
            content.append(setContent);
        }
    }

    public int insert(String...recordValues)
    {
        String recordContent = String.format("'%s'", String.join("', '", recordValues));
        String statement = String.format("INSERT INTO %s VALUES (%s);", name, recordContent);
        return databaseDriver.execUpdate(statement);
    }

    public int insert(RecordDriver record)
    {
        String columnNames = "";
        StringBuilder recordContent = new StringBuilder();
        Set<String> fields = record.getFields();
        columnNames = String.join(", ", fields);
        buildRecordValues(record, recordContent, fields);
        String statement = String.format("INSERT INTO %s (%s) VALUES %s;", name, columnNames, recordContent);
        return databaseDriver.execUpdate(statement);
    }

    public int insert(List<RecordDriver> records)
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
                buildRecordValues(record, recordContent, fields);
                allRecordContents.append(String.format("(%s)", recordContent));
            }
        }
        String statement = String.format("INSERT INTO %s (%s) VALUES %s;", name, columnNames, allRecordContents);
        return databaseDriver.execUpdate(statement);
    }

    public int delete(UUID primaryKeyValue)
    {
        String statement = String.format("DELETE FROM %s WHERE id='%s'", name, primaryKeyValue);
        return databaseDriver.execUpdate(statement);
    }

    public int delete(String condition)
    {
        String statement = String.format("DELETE FROM %s WHERE %s", name, condition);
        return databaseDriver.execUpdate(statement);
    }

    public int delete(String fieldName, Object value)
    {
        String statement = String.format("DELETE FROM %s WHERE %s='%s'", name, fieldName, value);
        return databaseDriver.execUpdate(statement);
    }

    public int update(RecordDriver setColumns, String condition)
    {
        StringBuilder setContents = new StringBuilder();
        buildColumnValues(setColumns, setContents);
        String statement = String.format("UPDATE %s SET %s WHERE %s", name, setContents, condition);
        return databaseDriver.execUpdate(statement);
    }

    public int update(RecordDriver setColumns, RecordDriver conditionColumns)
    {
        StringBuilder setContents = new StringBuilder();
        StringBuilder conditionContents = new StringBuilder();
        buildColumnValues(setColumns, setContents);
        buildColumnValues(conditionColumns, conditionContents);
        String statement = String.format("UPDATE %s SET %s WHERE %s", name, setContents, conditionContents);
        return databaseDriver.execUpdate(statement);
    }

    public int select(List<String> fields, String condition, List<RecordDriver> result)
    {
        String fieldContent;
        if(fields.size() == 0)
        {
            fieldContent = "*";
        }
        else
        {
            fieldContent = String.join(",", fields);
        }
        String statement = String.format("SELECT %s FROM %s WHERE %s", fieldContent, name, condition);
        return databaseDriver.execQuery(statement, result);
    }
}
