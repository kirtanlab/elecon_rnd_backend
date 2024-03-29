package com.elecon.asset_mgt.Cards.Controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public  class Card {
  private String api_endpoint;
  private String value;
  private String title;

  public Card(String api_endpoint, String value, String title){
    this.api_endpoint = api_endpoint;
    this.value = value;
    this.title = title;
  }
}
