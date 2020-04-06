package com.epam.fiat.resource;

import com.epam.fiat.domain.Quote;
import com.epam.fiat.service.QuoteService;
import com.epam.fiat.validator.RequestValidator;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quote/")
@RequiredArgsConstructor
public class QuoteController {

  private final QuoteService quoteService;
  private final RequestValidator requestValidator;

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.addValidators(requestValidator);
  }

  @PostMapping
  public Quote saveQuote(@RequestBody @Valid Quote quotes) {
    quoteService.saveQuote(quotes);
    return quotes;
  }

  @GetMapping
  public List<Quote> getAllQuotes() {
    return quoteService.getAllQuotes();
  }

}
