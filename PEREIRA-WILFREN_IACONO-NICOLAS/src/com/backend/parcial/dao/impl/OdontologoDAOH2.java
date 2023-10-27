package com.backend.parcial.dao.impl;

import com.backend.parcial.dao.H2Connection;
import com.backend.parcial.dao.IDao;
import com.backend.parcial.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDao<Odontologo> {
    private final Logger LOGGER = Logger.getLogger(OdontologoDAOH2.class);

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoRegistrado = null;
        String insert = "INSERT INTO ODONTOLOGOS (NUMERO_MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)";
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                odontologoRegistrado = new Odontologo(resultSet.getInt(1), odontologo.getNumeroMatricula(), odontologo.getNombre(), odontologo.getApellido());
            }

            connection.commit();
            LOGGER.info("Se registró el = " + odontologoRegistrado);


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Tuvimos un problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                LOGGER.error("No se pudo cerrar la conexión: " + exception.getMessage());
                exception.printStackTrace();
            }
        }
        return odontologoRegistrado;
    }

    @Override
    public List<Odontologo> listarTodos() {
        String select = "SELECT * FROM ODONTOLOGOS";
        Connection connection = null;
        List<Odontologo> odontologoList = new ArrayList<>();
        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                odontologoList.add(crearOdontologo(resultSet));
            }

            LOGGER.info("Lista de todos los odontólogos = " + odontologoList);

        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            exception.printStackTrace();
        } finally {
            try {
                connection.close();
                //LOGGER.info("Cerré la conexión correctamente");

            } catch (Exception exception) {
                LOGGER.error(exception.getMessage());
                exception.printStackTrace();
            }
        }

        return odontologoList;
    }

    private Odontologo crearOdontologo(ResultSet resultSet) throws SQLException {
        Odontologo odontologo;
        int id = resultSet.getInt(1);
        int numeroMatricula = resultSet.getInt(2);
        String nombre = resultSet.getString(3);
        String apellido = resultSet.getString(4);
        odontologo = new Odontologo(id, numeroMatricula, nombre, apellido);
        return odontologo;
    }


}
