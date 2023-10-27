package com.backend.parcial.test;

import com.backend.parcial.dao.impl.OdontologoDAOEnMemoria;
import com.backend.parcial.dao.impl.OdontologoDAOH2;
import com.backend.parcial.model.Odontologo;
import com.backend.parcial.service.OdontologoService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OdontologoServiceTest {
    private OdontologoService odontologoService;
    @Test
    public void listarTodosLosOdontologosEnH2() {
        odontologoService = new OdontologoService(new OdontologoDAOH2());

        List<Odontologo> respuestaObtenida = odontologoService.listarOdontologos();

        assertFalse(respuestaObtenida.isEmpty());
    }


    @Test
    public void listarTodosLosOdontologosEnMemoria() {
        odontologoService = new OdontologoService(new OdontologoDAOEnMemoria());

        List<Odontologo> respuestaObtenida = odontologoService.listarOdontologos();
        assertTrue(respuestaObtenida.isEmpty());
    }



}