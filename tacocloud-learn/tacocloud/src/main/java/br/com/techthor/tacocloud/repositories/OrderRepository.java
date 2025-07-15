package br.com.techthor.tacocloud.repositories;

import br.com.techthor.tacocloud.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {}

