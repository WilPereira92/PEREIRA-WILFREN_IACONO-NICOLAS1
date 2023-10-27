package com.backend.parcial.service;

import com.backend.parcial.dao.IDao;
import com.backend.parcial.model.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> dao;

    public OdontologoService(IDao<Odontologo> dao) {
        this.dao = dao;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return dao.registrar(odontologo);
    }

    public List<Odontologo> listarOdontologos() {
        return dao.listarTodos();
    }

}
