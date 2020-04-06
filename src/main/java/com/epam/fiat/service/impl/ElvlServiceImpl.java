package com.epam.fiat.service.impl;

import com.epam.fiat.domain.Elvl;
import com.epam.fiat.domain.Quote;
import com.epam.fiat.repository.ElvlRepository;
import com.epam.fiat.service.ElvlService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ElvlServiceImpl implements ElvlService {

  private final ElvlRepository elvlRepository;
  @Qualifier("elvlsMap")
  private final Map<String, Elvl> elvlsMap;

  @Override
  public Elvl updateElvl(Quote quote) {
    Elvl elvl = elvlsMap.compute(quote.getIsin(), (k, v) -> calculateElvl(quote, elvlsMap.get(quote.getIsin())));
    return elvl;
  }

  @Override
  public Elvl getElvlByIsin(String isin) {
    return elvlsMap.get(isin);
  }

  @Override
  public List<Elvl> getAllElvls() {
    return elvlRepository.findAll();
  }

  private Elvl createNewElvl(Quote quote) {
    Elvl elvl = Elvl.builder().isin(quote.getIsin()).build();
    if(quote.getBid() != null) {
      elvl.setValue(quote.getBid());
    } else {
      elvl.setValue(quote.getAsk());
    }
    return elvl;
  }

  private Elvl calculateElvl(Quote quote, Elvl elvl) {
    if(elvl == null) {
      elvl = createNewElvl(quote);
      elvlRepository.save(elvl);
      return elvl;
    }
    if(quote.getBid() > elvl.getValue()) {
      elvl.setValue(quote.getBid());
    } else if (quote.getAsk() < elvl.getValue()) {
      elvl.setValue(quote.getAsk());
    }
    elvlRepository.save(elvl);
    return elvl;
  }

}
