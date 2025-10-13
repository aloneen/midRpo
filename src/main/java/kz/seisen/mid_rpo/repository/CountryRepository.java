package kz.seisen.mid_rpo.repository;

import kz.seisen.mid_rpo.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
}
