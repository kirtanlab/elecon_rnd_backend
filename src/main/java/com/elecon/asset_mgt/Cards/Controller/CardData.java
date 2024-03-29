package com.elecon.asset_mgt.Cards.Controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class CardData {

  private String parent;
  private List<Card> cards;
  public CardData (String parent, List<Card> cards) {
    this.parent = parent;
    this.cards = cards;
  }
}

