package net.problem.appforkrit.domain.repositories;

import net.problem.appforkrit.domain.entities.NalogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NalogRepository extends CrudRepository<NalogEntity, Long> {

}
