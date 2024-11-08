package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;


@RestController
@RequestMapping(value = "/time")
public class TimeController {


    @Autowired
    private TimeRepository timeRepository;

    @PostMapping
    public void adicionar(){
        Time time = new Time();
        time.setData(LocalDate.now());
        timeRepository.save(time);
    }
}