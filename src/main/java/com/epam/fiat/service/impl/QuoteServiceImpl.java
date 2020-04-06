package com.epam.fiat.service.impl;

import com.epam.fiat.domain.Quote;
import com.epam.fiat.repository.QuoteRepository;
import com.epam.fiat.service.ElvlService;
import com.epam.fiat.service.QuoteService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

  private final ElvlService elvlService;
  private final QuoteRepository quoteRepository;

  @Override
  public Quote saveQuote(Quote quote) {
    Quote savedQuote = quoteRepository.save(quote);
    elvlService.updateElvl(quote);
    return savedQuote;
  }

  @Override
  public List<Quote> getAllQuotes() {
    return quoteRepository.findAll();
  }
}
