package br.fiap.checkpoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fiap.checkpoint.model.Viagem;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {

	List<Viagem> findById(Integer idViagem);

}
