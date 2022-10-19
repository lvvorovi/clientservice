package com.roadmap.clientservice.business.repository;

import com.roadmap.clientservice.business.repository.model.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientJpaRepository extends CrudRepository<ClientEntity, Long> {
}
