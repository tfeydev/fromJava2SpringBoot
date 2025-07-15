package br.com.techthor.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import br.com.techthor.tacocloud.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
