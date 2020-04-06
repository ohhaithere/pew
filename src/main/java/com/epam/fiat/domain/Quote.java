package com.epam.fiat.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String isin;
  Double bid;
  Double ask;

}
