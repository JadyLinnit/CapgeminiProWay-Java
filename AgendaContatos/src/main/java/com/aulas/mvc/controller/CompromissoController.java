package com.aulas.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aulas.mvc.DTO.CompromissoDTO;
import com.aulas.mvc.dao.DaoCompromisso;
import com.aulas.mvc.dao.DaoContato;
import com.aulas.mvc.entidades.Compromisso;
import com.aulas.mvc.entidades.Contato;

@Controller
@RequestMapping("/")
public class CompromissoController {
    @GetMapping
    public String irIndice() {
    	return "index";
    }
    
    @GetMapping("/consultartodos")
    public String consultar(Model model) {
    	List<Compromisso> lista = new DaoCompromisso().getTodos();
    	model.addAttribute("lista", lista);
      	return "consultarcompromisso";
    }
    
    @PostMapping("/consultartodos")
    public String consultar(@RequestParam("criterio") String filtro, Model model) {
    	List<Compromisso> lista = new DaoCompromisso().getFilter(filtro);
    	model.addAttribute("lista", lista);
      	return "consultarcompromisso";
    }
    
    
    @GetMapping("/cadastrar")
    public String formCadastro(Model model) {
    	model.addAttribute("compromissoDTO", new CompromissoDTO());
    	
    	
    	List<Contato> listaContato = new DaoContato().getTodos();
    	model.addAttribute("contatos", listaContato);
    	
    	return "cadastrocompromisso";
    }
    
    @PostMapping("/cadastrar")
    public String cadastrar(@ModelAttribute CompromissoDTO compromissoDTO) {
        Contato ct = new DaoContato().getOne(compromissoDTO.getIdcontato());
    	
        Compromisso compromisso = new Compromisso();
    	compromisso.setLocal(compromissoDTO.getLocal());
      	compromisso.setData(compromissoDTO.getData());
      	
    	compromisso.setHora(compromissoDTO.getHora());
    	compromisso.setContato(ct);
    	
    	if(new DaoCompromisso().salvar(compromisso)) {
    		return "redirect:/consultartodos";
    	}
    	
    	
        return "cadastrocompromisso";
    }
    
}