/*           Tips para fechas
1- Utilizar el tipo de dato LocalDate.
2- Para obtener la fecha de hoy se utiliza el método LocalDate.now()
3- Para calcular períodos entre dos fechas se utiliza la clase Period y el método between. Por ejemplo:
Period.between (fecha_hoy, otra_fecha)
4- Si utilizamos el método getYears en el objeto Period que tengamos, obtendremos la cantidad de años
de diferencia.*/


package com.example.odontologo.controller;

import com.example.odontologo.Paciente;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


@RestController
public class OdontologiaController {

    @GetMapping("/paciente/traer")
    public List<Paciente> traerPacientes(){

        //Simulamos lista lógica de pacientes como si fuera una BD
        List<Paciente> listaPacientes = new ArrayList<Paciente>();

        listaPacientes.add(new Paciente(1L,42579253,"Nicolas",
                "Pozzer", LocalDate.of(2000,5,4)));
        listaPacientes.add(new Paciente(2L, 13558965, "Sara",
                "Barrios",LocalDate.of(1960,1,20)));
        listaPacientes.add(new Paciente(3L, 49663221, "Benjamín",
                "Goichuk", LocalDate.of(2008,11,25)));
        listaPacientes.add(new Paciente(4L, 50111889, "Gladys",
                "Andreiszyn", LocalDate.of(2015,9,27)));
        listaPacientes.add(new Paciente(5L, 12000320, "Eduardo",
                "Malim", LocalDate.of(1958,5,13)));

        //Retornamos la lista entera, no hace falta recorrer con un forEach
        return listaPacientes;
    }


    //AHORA CON LA FUNCION PARA QUE ENCUENTRE LOS MENORES DE EDAD
    @GetMapping("/paciente/traer/menores")
    @ResponseBody
    public List<Paciente> traerMenores() {
        List<Paciente> listaPacientes = new ArrayList<Paciente>();
        listaPacientes.add(new Paciente(1L, 52333654, "Yasmín", "Romero", LocalDate.of(2016,8,18)));
        listaPacientes.add(new Paciente(2L, 13558965, "Sara", "Barrios",LocalDate.of(1960,1,20)));
        listaPacientes.add(new Paciente(3L, 49663221, "Benjamín", "Goichuk", LocalDate.of(2008,11,25)));
        listaPacientes.add(new Paciente(4L, 50111889, "Gladys", "Andreiszyn", LocalDate.of(2015,9,27)));
        listaPacientes.add(new Paciente(5L, 12000320, "Eduardo", "Malim", LocalDate.of(1958,5,13)));

        //creo lista para guardar a los menores
        List<Paciente> listaMenores = new ArrayList<Paciente>();

        for (Paciente pac : listaPacientes) {

            //fecha de hoy
            LocalDate hoy = LocalDate.now();
            //calculo período entre fecha de nacimiento del paciente y fecha de hoy
            Period cant_anios = Period.between(pac.getFecha_nac(), hoy);

            //si es menor de 18 lo agrego a lista menores
            if (cant_anios.getYears()<18) {
                System.out.println("cant anios " + cant_anios.getYears());
                listaMenores.add(pac);
            }

        }
        //luego muestro la lista de menores.
        return listaMenores;
    }






    }
