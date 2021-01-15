package com.felder.model.datasource;

import java.sql.Connection;

public interface IConnection {

    public void loadDataSource() throws Exception;

    public Boolean open () throws Exception;

    public Connection getConnection();

    public Boolean close() throws Exception;
}
