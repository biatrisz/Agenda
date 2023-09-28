package com.Agenda.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Agenda.entitie.Agenda;
import com.Agenda.repository.AgendaRepository;

@Service
public class AgendaService {
    private final AgendaRepository agendaRepository;
    
    @Autowired
    public AgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public List<Agenda> getAllAgenda() {
        return agendaRepository.findAll();
    }

    public Agenda getAgendaById(Long id) {
        Optional<Agenda> produto = agendaRepository.findById(id);
        return produto.orElse(null);
    }

    public Agenda salvarAgenda(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public Agenda updateAgenda(Long id, Agenda updatedProduto) {
        Optional<Agenda> existingAgenda = agendaRepository.findById(id);
        if (existingAgenda.isPresent()) {
            updatedProduto.setId(id);
            return agendaRepository.save(updatedProduto);
        }
        return null;
    }

    public boolean deleteAgenda(Long id) {
        Optional<Agenda> existingAgenda = agendaRepository.findById(id);
        if (existingAgenda.isPresent()) {
        	agendaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}