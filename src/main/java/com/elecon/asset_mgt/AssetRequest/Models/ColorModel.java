package com.elecon.asset_mgt.AssetRequest.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ColorModel")
public class ColorModel {
  @Id
  @GeneratedValue
  private Integer id;
  private String  color_name;
  private String color_code;

}
