package br.fiap.checkpoint.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import br.fiap.checkpoint.model.Viagem;
import br.fiap.checkpoint.repository.ViagemRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/viagem")
public class ViagemController {

	@Autowired
	private ViagemRepository viagemRepository;
	
	@GetMapping("")
	public ModelAndView get() {
		ModelAndView model = new ModelAndView("viagem/index");
		
		List<Viagem> listaViagem = viagemRepository.findAll();
		model.addObject("viagens", listaViagem);
		
		return model;
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long idViagem) {
	    Optional<Viagem> selecionado = viagemRepository.findById(idViagem);
	    if(selecionado.isPresent()) {
	    	Viagem viagem = selecionado.get();
	    	model.addAttribute("viagem", viagem);
	    	return "viagem/edit";
	    } else {
	    	return "redirect:/viagem";
	    }
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Viagem> edit(@Valid @RequestBody Viagem objViagem) {
		objViagem.setDateDecolagem(objViagem.getDateDecolagem());
		objViagem.setAssento(objViagem.getAssento());
		objViagem.setPrimeiroComandante(objViagem.getPrimeiroComandante());
		objViagem.setSegundoComandante(objViagem.getSegundoComandante());
		
		viagemRepository.save(objViagem);
		
		return new ResponseEntity<Viagem>(objViagem, HttpStatus.OK);
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView model = new ModelAndView("viagem/create");
		return model;
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute("viagem") Viagem objViagem) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataDecolagem = LocalDate.parse(objViagem.getDateDecolagem(), fmt);
		LocalDate dataRetorno = dataDecolagem.plusDays(520 + Integer.parseInt(objViagem.getEstadia()));
		objViagem.setDateDecolagem(dataDecolagem.format(fmt));
		objViagem.setRetorno(dataRetorno.format(fmt));
		viagemRepository.save(objViagem);
		
		return "redirect:/viagem";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		viagemRepository.deleteById(id);
		return "redirect:/viagem";
	}
	
}
