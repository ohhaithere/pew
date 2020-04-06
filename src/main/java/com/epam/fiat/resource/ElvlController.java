package com.epam.fiat.resource;

import com.epam.fiat.domain.Elvl;
import com.epam.fiat.service.ElvlService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elvl/")
@RequiredArgsConstructor
public class ElvlController {

  private final ElvlService elvlService;

  @GetMapping
  public List<Elvl> getAllElvls() {
    return elvlService.getAllElvls();
  }

  @GetMapping("/{isin}")
  public Elvl getElvlByIsin(@PathVariable String isin) {
    return elvlService.getElvlByIsin(isin);
  }

}
