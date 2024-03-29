package com.elecon.asset_mgt.visitor_mgt;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
public class VisitsResponse {
  private Integer id;
  private String VisitTitle;
  private String VisitType = "Normal";

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
  private Date in_time;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
  private Date out_time;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
  private Date visit_date;

  private String requested_by;
  private String photo;

  private String tab; //can be ADDED/IN/OUT

  private String approve; //can be approved, rejected

  public VisitsResponse(String tab,Integer id, String VisitTitle, String VisitType, Date in_time, Date out_time, Date visit_date, String requested_by, String photo) {
    this.id = id;
    this.VisitTitle = VisitTitle;
    this.VisitType = VisitType;
    this.in_time = in_time;
    this.out_time = out_time;
    this.visit_date = visit_date;
    this.requested_by = requested_by;
    this.photo = photo;
    this.tab = tab;

    // Add 5 hours to the current date
    if(in_time != null){
      this.in_time = addHoursToDate(new Date(), 5);
    }
    if(out_time != null){
      // Set an older date (e.g., 2 days ago)
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.DAY_OF_MONTH, -2);
      this.out_time = calendar.getTime();
    }
  }

  private Date addHoursToDate(Date date, int hours) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.HOUR_OF_DAY, hours);
    return calendar.getTime();
  }
}
