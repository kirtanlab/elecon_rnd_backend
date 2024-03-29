package com.elecon.asset_mgt.Cards.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cards")

public class CardsModel {
  @Id
  @GeneratedValue
  private Integer id;
  private String name;
}
