package dao.driver;

import java.util.*;

public class RecordDriver
{
    private TreeMap<String, Object> treeMap = new TreeMap<>();

    public void set(String key, Object value)
    {
        treeMap.put(key, value);
    }

    public Object get(String key)
    {
        return treeMap.get(key);
    }

    public void remove(String key)
    {
        treeMap.remove(key);
    }

    public Set<String> getFields()
    {
        return treeMap.keySet();
    }

    public Collection<Object> getValues()
    {
        return treeMap.values();
    }
}
