package com.elecon.asset_mgt.Cards.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/Cards/")
public class CardsController {
  @GetMapping("getCards")
  public List<CardData> getCards() {
    // Assuming CardData is a class representing the structure of each card
    return TestData.getData();
  }
}
