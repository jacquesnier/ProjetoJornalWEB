package br.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.model.Comentario;
import br.ufc.model.Noticia;
import br.ufc.repository.ComentarioRepository;

@Service
public class ComentarioService {
	
	@Autowired
	ComentarioRepository comentarioRepository;
	
	public void addOrUpdate(Comentario comentario) {
		comentarioRepository.save(comentario);
	}
	
	public List<Comentario> getComentarios(Noticia noticia){
		return comentarioRepository.findComentarioByNoticia(noticia);
	}
}
