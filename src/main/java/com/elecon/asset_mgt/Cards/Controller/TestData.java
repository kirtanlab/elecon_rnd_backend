package com.elecon.asset_mgt.Cards.Controller;

import java.util.Arrays;
import java.util.List;

public class TestData {
  public static List<CardData> getData() {
    return Arrays.asList(
      new CardData("Admin Assets Management", Arrays.asList(
        new Card("category/", "12", "Category Master" ),
        new Card("type/", "15", "Type Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" ),
        new Card("AssetRequest/getAll/", "5", "Requests Master" )

        // Add other cards
      )),
      new CardData("Admin Service Requisition", Arrays.asList(
        new Card("https://www.google.com", "12", "googles dksbfdlsbsibfbsfbsfsid asbdasbas absdasbjds"),
        new Card("https://www.google.com", "12", "googles")
        // Add other cards
      ))

      // Add other parent-card data
    );
  }
}
