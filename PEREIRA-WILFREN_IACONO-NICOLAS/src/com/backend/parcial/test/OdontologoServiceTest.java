package com.backend.parcial.test;

import com.backend.parcial.dao.impl.OdontologoDAOEnMemoria;
import com.backend.parcial.dao.impl.OdontologoDAOH2;
import com.backend.parcial.model.Odontologo;
import com.backend.parcial.service.OdontologoService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class OdontologoServiceTest {
    private OdontologoService odontologoService;

    @Test
    public void listarTodosLosOdontologosEnH2() {
        odontologoService = new OdontologoService(new OdontologoDAOH2());
        Odontologo odontologo = new Odontologo(852147, "Ricardo", "Arjona");
        Odontologo odontologo1 = new Odontologo(84526, "Daniel", "Pereira");

        odontologoService.registrarOdontologo(odontologo);
        odontologoService.registrarOdontologo(odontologo1);
        List<Odontologo> respuestaObtenida = odontologoService.listarOdontologos();

        assertFalse(respuestaObtenida.isEmpty());
    }


    @Test
    public void listarTodosLosOdontologosEnMemoria() {
        odontologoService = new OdontologoService(new OdontologoDAOEnMemoria());
        Odontologo odontologo = new Odontologo(852147, "Ricardo", "Arjona");
        Odontologo odontologo1 = new Odontologo(84526, "Daniel", "Pereira");

        odontologoService.registrarOdontologo(odontologo);
        odontologoService.registrarOdontologo(odontologo1);
        List<Odontologo> respuestaObtenida = odontologoService.listarOdontologos();
        assertFalse(respuestaObtenida.isEmpty());
    }



}