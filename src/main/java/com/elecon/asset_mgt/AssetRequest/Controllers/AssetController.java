package com.elecon.asset_mgt.AssetRequest.Controllers;

import com.elecon.asset_mgt.AssetRequest.DAO.CreateAssetRequestDao;
import com.elecon.asset_mgt.AssetRequest.DAO.CreateVisitorDao;
import com.elecon.asset_mgt.AssetRequest.Models.AssetRequestModel;
import com.elecon.asset_mgt.AssetRequest.Models.StatusModel;
import com.elecon.asset_mgt.AssetRequest.Service.AssetRequestService;
import com.elecon.asset_mgt.AssetRequest.Service.StatusService;
import com.elecon.asset_mgt.Classification.Models.ClassificationModel;
import com.elecon.asset_mgt.Exceptions.ForeignKeyViolationException;
import jakarta.persistence.PostUpdate;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/AssetRequest/")
public class AssetController {
  private static Map<String, Object> createColumn (String id, String label, String type) {
    Map<String, Object> column = new HashMap<>();
    column.put("id", id);
    column.put("label", label);
    column.put("type", type);
    column.put("show", true);
    return column;
  }
  private static Map<String, Object> createColumn(String id, String label, String type, boolean show) {
    Map<String, Object> column = new HashMap<>();
    column.put("id", id);
    column.put("label", label);
    column.put("type", type);
    column.put("show", show); // Add "show" attribute with default value
    return column;
  }

  private static Map<String, Object> createTab (String value, String label, String color) {
    Map<String, Object> tab = new HashMap<>();
    tab.put("id", value);
    tab.put("label", label);
    tab.put("type", color);
    return tab;
  }
  private static Map<String,Object> createModal (String label, String color, String api_endopoint){ //api_endopoint to get form to submit Modal
    Map<String,Object> modal_object = new HashMap<>();
    modal_object.put("label", label);
    modal_object.put("color", color);
    modal_object.put("api_endopoint", api_endopoint);
    return modal_object;
  }
  @Autowired
  private AssetRequestService assetreqservice;

  @Autowired
  private StatusService statusService;

  @PostMapping("createAssetRequest/")
  public ResponseEntity<Map<String, Object>> createAssetRequest(@RequestBody CreateAssetRequestDao assetreqdao , Principal principal) {
        System.out.println("in create");
        try {
            System.out.println("in create asset");
            List<String> actions = assetreqservice.save(assetreqdao,principal);
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("status", true);
            successResponse.put("message", "New asset request is created successfully!");
            successResponse.put("serviceResponse", actions);
            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
        } catch (Exception e) {
            return handleException(e);
        }
    }

  @PutMapping("/{requestId}/approve")
  public ResponseEntity<String> approveAssetRequest(@PathVariable Integer requestId) {
    // Call the service method to update the status
    boolean success = assetreqservice.approveAssetRequest(requestId,2); //2 as APPROVED ID

    if (success) {
      return ResponseEntity.ok("Asset request approved successfully.");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Failed to approve asset request.");
    }
  }

  @PutMapping("/{requestId}/pullback")
  public ResponseEntity<String> pullbackAssetRequest(@PathVariable Integer requestId) {
    // Call the service method to update the status
    boolean success = assetreqservice.approveAssetRequest(requestId,2); //2 as APPROVED ID

    if (success) {
      return ResponseEntity.ok("Asset request approved successfully.");
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Failed to approve asset request.");
    }
  }


//  @CrossOrigin(origins = "*", allowedHeaders = "*")
//  @GetMapping("getAllStatus/")
//  public ResponseEntity<Map<String,Object>> getAllStatus () {
//      try{
//        List<StatusModel> result = statusService.getAll();
//        List<Map<String, Object>> simplifiedData = new ArrayList<>();
//        for (StatusModel status : result) {
//          Map<String, Object> simplifiedRequest = new HashMap<>();
//          simplifiedRequest.put("status",status.getStatus());
//          simplifiedRequest.put("color",status.getColor().getColor_name());
//          simplifiedData.add(simplifiedRequest);
//        }
//
//
//        Map<String, Object> responseBody = new HashMap<>();
//        responseBody.put("status", true);
//        responseBody.put("data", simplifiedData);
//
//        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
//      } catch (Exception e) {
//        return handleException(e);
//      }
//  }
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("getAll/")
  public ResponseEntity<Map<String, Object>> getAllAssetsRequests() {
    try {
      List<AssetRequestModel> result = assetreqservice.getAll();
      List<Map<String, Object>> simplifiedData = new ArrayList<>();
      List<Map<String, Object>> simplifiedData_secondary = new ArrayList<>();
      Map<String, Object> simplifiedRequest_secondary_first = new HashMap<>();
      Map<String, Object> simplifiedRequest_secondary_second = new HashMap<>();
      simplifiedRequest_secondary_first.put("employee_name","kirtan");
      simplifiedRequest_secondary_first.put("visitor_phone_no","7984651212");
      simplifiedRequest_secondary_first.put("IN", createModal("IN", "secondary", "abc"));;
      simplifiedRequest_secondary_first.put("OUT", createModal("OUT", "success", "bcd"));

      simplifiedRequest_secondary_second.put("employee_name","kirtan2");
      simplifiedRequest_secondary_second.put("visitor_phone_no","9884651212");
      simplifiedRequest_secondary_second.put("IN", createModal("IN", "secondary", "abc"));;
      simplifiedRequest_secondary_second.put("OUT", createModal("OUT", "success", "bcd"));
      simplifiedData_secondary.add(simplifiedRequest_secondary_first);
      simplifiedData_secondary.add(simplifiedRequest_secondary_second);

      for (AssetRequestModel assetRequest : result) {
        Map<String, Object> simplifiedRequest = new HashMap<>();

        simplifiedRequest.put("id", assetRequest.getId());
//        simplifiedRequest.put("allocated_asset_id", assetRequest.getAllocated_asset_id());
        simplifiedRequest.put("required_by", assetRequest.getRequired_by());
        simplifiedRequest.put("updated_at", assetRequest.getUpdated_at());
//        simplifiedRequest.put("details", assetRequest.getDetails());
//        simplifiedRequest.put("reason", assetRequest.getReason());
        simplifiedRequest.put("status", assetRequest.getStatus().getStatus());
        simplifiedRequest.put("color", assetRequest.getStatus().getColor().getColor_name());

        // Add employee information directly to the parent map
        simplifiedRequest.put("employee_name", assetRequest.getEmployee().getEmployee_name());
        simplifiedRequest.put("employee_code", assetRequest.getEmployee().getEmployeeCode());
        simplifiedRequest.put("employee_phone_no", "9876541234");
        // Add reporting_to_id information directly to the parent map
        simplifiedRequest.put("reporting_to_name", assetRequest.getReporting_to_id().getEmployee_name());
        simplifiedRequest.put("reporting_to_code", assetRequest.getReporting_to_id().getEmployeeCode());

        simplifiedRequest.put("IN", createModal("IN", "secondary", "abc"));
        simplifiedRequest.put("OUT", createModal("OUT", "success", "bcd"));
        simplifiedRequest.put("Secondary_data", simplifiedData_secondary);
        simplifiedData.add(simplifiedRequest);
//        simplifiedRequest.put("Collapse", )
      }
      List <Map<String, Object>> secondary_columns = new ArrayList<Map<String, Object>>();
      List<Map<String, Object>> columns = new ArrayList<>();

      secondary_columns.add(createColumn("employee_name", "Employee Name", "string"));
      secondary_columns.add(createColumn("visitor_phone_no", "Phone Number", "string"));
      secondary_columns.add(createColumn("IN","IN","modal"));
      secondary_columns.add(createColumn("OUT","OUT","modal"));
      secondary_columns.add(createColumn("options", "Options", "options"));

      columns.add(createColumn("id", "ID", "string"));
      columns.add(createColumn("required_by", "Required By", "date"));
      columns.add(createColumn("updated_at", "Last Modified", "date"));
      columns.add(createColumn("employee_name", "Employee Name", "string"));
      columns.add(createColumn("employee_code", "Employee Code", "string"));
      columns.add(createColumn("reporting_to_name", "Reporting_to Name", "string"));
      columns.add(createColumn("reporting_to_code", "Reporting_to Code", "string"));
      columns.add(createColumn("employee_phone_no", "Phone Number", "string"));

//      columns.add(createColumn("status","status","tab",false));
      columns.add(createColumn("IN","IN","modal"));
      columns.add(createColumn("OUT","OUT","modal"));
      columns.add(createColumn("collapse","","collapse"));
      columns.add(createColumn("options", "Options", "options"));

      List<Integer> list= Arrays.asList(1, 2, 3,4);

      List<Map<String, Object>> tabs = statusService.getStatus_ColorByIds(list);

      Map<String, Object> tableInfo = new HashMap<>();
      tableInfo.put("tableTitle","Assets Requests Details");
      tableInfo.put("Breadcrumbs", "Asset Requests");
      tableInfo.put("tableSearchPlaceholder","Search id / employee name / reporting_to name / code");
      tableInfo.put("tableAddObject", "Add New Request");
      tableInfo.put("tableDensity", false); //dense true

      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("status", true);
      responseBody.put("data", simplifiedData);
      responseBody.put("tableLabels", columns);
      responseBody.put("tableInfo", tableInfo);
      responseBody.put("tableTabs", tabs);
      responseBody.put("SecondaryLabels", secondary_columns);
      return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    } catch (Exception e) {
      return handleException(e);
    }
  }

//  @CrossOrigin(origins = "*", allowedHeaders = "*")
//  @GetMapping
//  public ResponseEntity<Map<String,Object>> getAllRequestEditData (){
//      try{
//
//      }catch{
//
//    }
//  }
//    stage:
//            1) pullback, cancel -- comment
//            2) manager approve, reject -- comment
//            3) allocation approve, reject -- comment
//            4) handover -- comment

    //can be cancelled api for employee or manager whoever is requesting
    @DeleteMapping("{assetreqID}")
    public ResponseEntity<?> deleteAssetRequest(@PathVariable Integer assetreqID,Principal principal){
        System.out.println("in delete");
        try {
            assetreqservice.deletebyID(assetreqID,principal) ;
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("status", true);
            successResponse.put("message", "Delete asset request  successfully!");
            return ResponseEntity.status(HttpStatus.OK).body(successResponse);
        } catch (Exception e) {
             return handleException(e);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(@NotNull Exception e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", false);
        errorResponse.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
