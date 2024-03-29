package com.elecon.asset_mgt.AssetRequest.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "StatusModel")
public class StatusModel {
    @Id
    @GeneratedValue
    private Integer id;
    //#EDEFF1 : default
    //#D9F1E8 : primary
    //#ECDAFF: secondary
    //#38B8D8: info
    //#D9F6E5: success
    //#FEF1DC: warning
    //#FDE4DE: error
    @Column(name = "status", unique = true)
    private String status;
  @ManyToOne
  @JoinColumn(name = "color", referencedColumnName = "id")
  private ColorModel color;

}
