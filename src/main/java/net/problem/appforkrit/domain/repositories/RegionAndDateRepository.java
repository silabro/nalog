package net.problem.appforkrit.domain.repositories;

import net.problem.appforkrit.domain.entities.NalogEntity;
import net.problem.appforkrit.domain.entities.RegionAndDateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionAndDateRepository extends CrudRepository<RegionAndDateEntity, Long> {

}
