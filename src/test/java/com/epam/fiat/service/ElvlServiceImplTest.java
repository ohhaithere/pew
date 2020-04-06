package com.epam.fiat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.epam.fiat.domain.Elvl;
import com.epam.fiat.domain.Quote;
import com.epam.fiat.repository.ElvlRepository;
import com.epam.fiat.service.impl.ElvlServiceImpl;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ElvlServiceImplTest {

  @Mock
  private ElvlRepository elvlRepository;
  private Map<String, Elvl> elvlsMap;
  ElvlServiceImpl elvlService;

  @Before
  public void setUp() {
    elvlsMap = new ConcurrentHashMap<>();
    elvlService = new ElvlServiceImpl(elvlRepository, elvlsMap);
  }

  @Test
  public void testCalulateElvlIfNotExists() {
    Quote quote = Quote.builder().isin("123456789123")
        .bid(11.0)
        .ask(12.0)
        .build();
    Elvl elvl = elvlService.updateElvl(quote);

    assertThat(elvl.getValue()).isEqualTo(quote.getBid());
  }

  @Test
  public void testCalulateElvlIfExists() {
    Quote quote = Quote.builder().isin("123456789123")
        .bid(11.0)
        .ask(12.0)
        .build();

    elvlService.updateElvl(quote);
    quote.setBid(13.0);
    Elvl elvl = elvlService.updateElvl(quote);

    assertThat(elvl.getValue()).isEqualTo(quote.getBid());
  }

  @Test
  public void testCalulateElvlIfExistsAndBidLowerThanElvl() {
    Quote quote = Quote.builder().isin("123456789123")
        .bid(11.0)
        .ask(12.0)
        .build();

    elvlService.updateElvl(quote);
    quote.setBid(10.0);
    quote.setAsk(9.0);
    Elvl elvl = elvlService.updateElvl(quote);

    assertThat(elvl.getValue()).isEqualTo(quote.getAsk());
  }

}
