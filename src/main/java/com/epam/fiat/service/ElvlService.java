package com.epam.fiat.service;

import com.epam.fiat.domain.Elvl;
import com.epam.fiat.domain.Quote;
import java.util.List;

public interface ElvlService {

  Elvl updateElvl(Quote quote);
  Elvl getElvlByIsin(String isin);
  List<Elvl> getAllElvls();

}
