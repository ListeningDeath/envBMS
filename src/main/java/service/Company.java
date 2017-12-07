package service;

import dao.driver.DatabaseDriver;
import dao.driver.TableDriver;

public class Company extends TableDriver
{
    protected Company(DatabaseDriver databaseDriver)
    {
        super(databaseDriver, "company");
    }
}
