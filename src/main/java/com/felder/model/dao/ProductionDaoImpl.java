package com.felder.model.dao;

import com.felder.model.datasource.ConnectionImplMysql;
import com.felder.model.datasource.IConnection;
import com.felder.model.pojo.Produccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductionDaoImpl implements IProductionDao {

    private IConnection connectionMysql;

    //Objeto que utilizaremos para lanzar un query a la base de datos
    private PreparedStatement preparedStatement = null;

    public ProductionDaoImpl() {
        this.connectionMysql = new ConnectionImplMysql();
    }

    @Override
    public List<Produccion> findProductionByDateAndKey(Timestamp from, Timestamp to, String key) throws Exception {
        List<Produccion> listProduccion = new ArrayList<>();
        if (this.connectionMysql.open()) {
            preparedStatement = this.connectionMysql.getConnection().prepareStatement("{call _findProductionByDateAndKey(?,?,?)}");
            preparedStatement.setTimestamp(1, from);//parametros del query
            preparedStatement.setTimestamp(2, to);//parametros del query
            preparedStatement.setString(3, key);//parametros del query
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Produccion produccion = new Produccion();
                produccion.setFecha(resultSet.getString("Fecha"));
                produccion.setHora(resultSet.getString("Hora"));
                produccion.setFolio(resultSet.getInt("Folio"));
                produccion.setCodigoProducto(resultSet.getString("Cod_Articulo"));
                produccion.setDescripcion(resultSet.getString("Descripcion"));
                produccion.setCarga(resultSet.getString("Num_Carga"));
                produccion.setTotalCajas(resultSet.getInt("Cajas"));
                produccion.setPeso(resultSet.getString("Peso"));
                produccion.setResponsable(resultSet.getString("Responsable"));
                listProduccion.add(produccion);
            }
            this.connectionMysql.close();
        }
        return listProduccion;
    }
}
