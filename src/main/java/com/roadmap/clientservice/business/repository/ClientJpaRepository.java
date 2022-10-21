package com.roadmap.clientservice.business.repository;

import com.roadmap.clientservice.business.repository.model.ClientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientJpaRepository extends CrudRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByPersonalNumber(String personalNumber);
    boolean existsByPersonalNumber(String personalNumber);

}
