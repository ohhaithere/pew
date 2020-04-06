package com.epam.fiat.validator;

import com.epam.fiat.domain.Quote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class RequestValidator implements Validator {

  private final static String TOO_SHORT_ISIN_MESSAGE = "isin меньше 12 символов";
  private final static String BID_BIGGER_THAN_ASK = "Знаение bid больше чем ask";


  @Override
  public boolean supports(Class<?> aClass) {
    return Quote.class.isAssignableFrom(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    Quote quote = (Quote) o;
    if(quote.getIsin().length() != 12) {
      errors.rejectValue("isin", "400", new String[]{"isin"},TOO_SHORT_ISIN_MESSAGE);
    }
    if(quote.getBid() > quote.getAsk()) {
      errors.rejectValue("bid", "400", new String[]{"bid"},BID_BIGGER_THAN_ASK);
    }
  }
}
