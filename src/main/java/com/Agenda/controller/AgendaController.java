package com.Agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Agenda.entitie.Agenda;
import com.Agenda.services.AgendaService;



@RestController
@RequestMapping("/produtos")
public class AgendaController {
    
    private final AgendaService agendaService;
    
    @Autowired
    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> getProductById(@PathVariable Long id) {
    	Agenda agenda = agendaService.getAgendaById(id);
        if (agenda != null) {
            return ResponseEntity.ok(agenda);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Agenda>> getAllProdutos() {
        List<Agenda> agendas = agendaService.getAllAgenda();
        return ResponseEntity.ok(agendas);
    }

    @PostMapping("/")
    public ResponseEntity<Agenda> criarProduto(@RequestBody Agenda agenda) {
    	Agenda criarProduto = agendaService.salvarAgenda(agenda);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarProduto);
    }
   

    @PutMapping("/{id}")
    public ResponseEntity<Agenda> updateProduto(@PathVariable Long id, @RequestBody Agenda agenda) {
    	Agenda updatedProduto = agendaService.updateAgenda(id, agenda);
        if (updatedProduto != null) {
            return ResponseEntity.ok(updatedProduto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduto(@PathVariable Long id) {
        boolean deleted = agendaService.deleteAgenda(id);
        if (deleted) {
        	 return ResponseEntity.ok().body("O produto foi excluído com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
       
    
}