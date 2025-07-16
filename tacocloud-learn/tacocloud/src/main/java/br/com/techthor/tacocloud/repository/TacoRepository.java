package br.com.techthor.tacocloud.repository;

import br.com.techthor.tacocloud.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
