package com.epam.fiat.repository;

import com.epam.fiat.domain.Elvl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElvlRepository extends JpaRepository<Elvl, Long> {

  Elvl findElvlByIsinEquals(String isin);

}
