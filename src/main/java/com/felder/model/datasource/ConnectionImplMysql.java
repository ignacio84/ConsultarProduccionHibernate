package com.felder.model.datasource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionImplMysql implements IConnection {

    private static final String PATH_PROPIERTIES = "application.properties";

    //Objeto de conexión a base de datos tipo Connection
    private Connection connection = null;

    //Objeto de propiedades donde vamos a cargar las propiedades del archivo
    private final Properties propierties = new Properties();

    @Override
    public void loadDataSource() throws Exception {
        //Input Stream donde leemos el recurso donde está el archivo de propiedades
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PATH_PROPIERTIES);
        if (inputStream != null) {
            //Cargamos las propiedades que vienene del archivo
            this.propierties.load(inputStream);
            //Cerramos el recurso
            inputStream.close();
        } else {
            throw new Exception("No se encontro : application.properties,  no es posible conectar con la base de datos.");
        }
    }

    @Override
    public Boolean open() throws Exception {
        this.loadDataSource();
        //Abrimos conexión a base de datos
        this.connection = DriverManager.getConnection(
                this.propierties.getProperty("mysql.url"),
                this.propierties.getProperty("mysql.username"),
                this.propierties.getProperty("mysql.password")
        );
        return (this.connection != null);
    }

    @Override
    //return true:conexion cerrada, false:conexion abierta
    public Boolean close() throws Exception {
        if (!this.connection.isClosed()) {
            this.connection.close();
        }
        return this.connection.isClosed();
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }
}
