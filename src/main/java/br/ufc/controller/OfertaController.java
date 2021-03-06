package br.ufc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufc.model.Classificado;
import br.ufc.model.Oferta;
import br.ufc.model.Pessoa;
import br.ufc.service.ClassificadoService;
import br.ufc.service.OfertaService;
import br.ufc.service.PessoaService;

@Controller
@RequestMapping(value="/oferta")
public class OfertaController {
	
	@Autowired
	ClassificadoService classificadoService;
	
	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	OfertaService ofertaService;
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addOferta(HttpSession session, Oferta oferta, @RequestParam Long id_classificado){
		Classificado classificado = classificadoService.get(id_classificado);
		Pessoa pessoa = (Pessoa) session.getAttribute("PESSOA_LOGADA");
		
		if(classificado!=null && pessoa!=null && pessoa.isLeitor() && !pessoa.isJornalista()){
			if(classificado.getMelhorOferta() != null){
				if(oferta.getValor() > classificado.getMelhorOferta().getValor()){
					Oferta ofertaAux = classificado.getMelhorOferta();
					ofertaAux.setAutor(pessoa);
					ofertaAux.setClassificado(classificado);
					ofertaAux.setValor(oferta.getValor());
					ofertaService.addOrUpdate(ofertaAux);		
					return "redirect:/classificado/";
				}
			}else if(oferta.getValor() > classificado.getPreco()){
				oferta.setAutor(pessoa);
				oferta.setClassificado(classificado);
				ofertaService.addOrUpdate(oferta);		
				return "redirect:/classificado/";
			}else{
				return "redirect:/classificado/";
			}
			
			return "redirect:/classificado/";
		}
		return "redirect:/classificado/";
	}

}
