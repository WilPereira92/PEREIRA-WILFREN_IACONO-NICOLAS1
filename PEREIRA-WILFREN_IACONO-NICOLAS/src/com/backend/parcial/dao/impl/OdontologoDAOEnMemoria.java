package com.backend.parcial.dao.impl;

import com.backend.parcial.dao.IDao;
import com.backend.parcial.model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOEnMemoria implements IDao<Odontologo> {
    private static int contador = 1;
    private final Logger LOGGER = Logger.getLogger(OdontologoDAOEnMemoria.class);
    private List<Odontologo> odontologoList = new ArrayList<>();

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Odontologo odontologoRegistrado = new Odontologo(contador, odontologo.getNumeroMatricula(), odontologo.getNombre(), odontologo.getApellido());
        odontologoList.add(odontologoRegistrado);
        LOGGER.info("Se registró el odontólogo = " + odontologoRegistrado);
        return odontologoRegistrado;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Listado de todos los odontólogos " + odontologoList);
        return odontologoList;
    }
}
