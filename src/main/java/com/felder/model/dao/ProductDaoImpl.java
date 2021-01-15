package com.felder.model.dao;

import com.felder.model.datasource.ConnectionImplMysql;
import com.felder.model.datasource.IConnection;
import com.felder.model.pojo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDaoImpl implements IProductDao {

    private IConnection connectionMysql;

    //Objeto que utilizaremos para lanzar un query a la base de datos
    private PreparedStatement preparedStatement = null;

    public ProductDaoImpl() {
        this.connectionMysql = new ConnectionImplMysql();
    }

    @Override
    public List<Producto> getAll() throws Exception {
        List<Producto> listProducto = new ArrayList<>();
        if (this.connectionMysql.open()) {
            //Armamos el prepared statement con el query a realizar
            preparedStatement = this.connectionMysql.getConnection().prepareStatement("{call _getAllProduct()}");
            //Ejecutamos el query
            ResultSet resultSet = preparedStatement.executeQuery();
            //Obtenemos el resultado
            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setId(resultSet.getInt("cons"));
                producto.setClaveProducto(resultSet.getString("prod_codprod"));
                producto.setDescripcion(resultSet.getString("prod_descprod"));
                producto.setEstatus(resultSet.getString("prod_est"));
                producto.setTipoMovimiento(resultSet.getString("prod_tipomov"));
                producto.setFechaAlta(resultSet.getDate("prod_fecmov"));
                producto.setUsuarioIdMovimiento(resultSet.getInt("prod_idusrmov"));
                producto.setLimiteCajas(resultSet.getInt("prod_limitcajas"));
                producto.setTipoProducto(resultSet.getString("prod_tipo"));
                listProducto.add(producto);
            }
            this.connectionMysql.close();
        }
        return listProducto;
    }

}
