package com.elecon.asset_mgt.visitor_mgt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class Dao {
  @Data
  public static class visitsDao {
    private Integer id;
    @NotNull
    private String visit_title;
    @NotNull
    private String visit_purpose;
//    @NotNull
    private Date visit_date_from;
    private Date visit_date_to;
    private Date visit_time_from;
    private Date visit_time_to;
    private String half;
//    private String wf_inst_id;
    private Integer visit_type;
    private Boolean is_multi_days;
    private Boolean is_multi_locations;
    private Integer requested_by;
    private Boolean is_completed;
    private Boolean is_in_progress;
    private Integer total_visitors;

  }
  @Data
  public static class VisitDetails {
    private Integer id;
    private Integer visit_id;
    private Integer visior_id;
    private Date in_time;
    private Date out_time;
    private Date visit_date;
    private Integer gate_user_id;
    private Integer gate_id;
  }

  @Data
  public static class visitLocations {
    private Integer id;
    private Integer visit_details_id;
    private Integer visit_id;
    private Integer location_id;
    private Integer location_gate_id;
    private Integer ref_person_id;
    private Date in_time;
    private Date out_time;
    private Integer gate_user_id;
  }

  @Data
  public static class Visitors{
    private Integer id;
    private String first_name;
    private String mid_name;
    private String last_name;
    private Integer mobile_no;
    private String email;
    private String pan_card;
    private String  addhar_Card;
    private String phoot;
  }

  @Data
  public static class visit_type {
    private Integer id;
    private String title;
    private Boolean is_approval;
  }

  @Data
  public static class locations{
    private Integer id;
    private String location_title;
    private String location_remarks;
  }

  @Data
  public static class loactionGates{
    private Integer id;
    private Integer location_id;
    private String gateName;
    private String gate_desc;
  }

}
