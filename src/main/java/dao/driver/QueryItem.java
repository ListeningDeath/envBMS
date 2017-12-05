package dao.driver;

import java.util.HashMap;

public class QueryItem
{
    private HashMap<String, String> hashMap = new HashMap<>();

    void set(String key, String value)
    {
        hashMap.put(key, value);
    }

    public String get(String key)
    {
        return hashMap.get(key);
    }

    void remove(String key)
    {
        hashMap.remove(key);
    }
}
