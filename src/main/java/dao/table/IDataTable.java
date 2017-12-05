package dao.table;

public interface IDataTable
{
    void add(Object...fieldNames);
    void del(Object...primaryKeys);
    void update(Object primaryKey, Object...newFieldNames);
    long query()
}
