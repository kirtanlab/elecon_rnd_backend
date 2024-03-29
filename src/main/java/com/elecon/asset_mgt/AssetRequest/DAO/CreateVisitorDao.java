package com.elecon.asset_mgt.AssetRequest.DAO;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Data
@Getter
@Setter
public class CreateVisitorDao {
  private String VisitorName;
  @Email
  private String EmailAddress;
  private Integer PhoneNumber;
  private String EntryGate;
  private String Appointment_half;
  private String isProfessional;
  private String purpose;
  private String Place;
  private String GuestCompany;
  private Date To_Date;
  private String Id_proof;
  private String VisitType;
}
