package com.epam.fiat.service;


import com.epam.fiat.domain.Quote;
import java.util.List;

public interface QuoteService {

  Quote saveQuote(Quote quote);
  List<Quote> getAllQuotes();

}
