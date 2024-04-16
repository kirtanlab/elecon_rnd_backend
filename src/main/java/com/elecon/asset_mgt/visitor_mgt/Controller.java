package com.elecon.asset_mgt.visitor_mgt;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.elecon.asset_mgt.Category.Service.CategoryNotFoundException;
import com.elecon.asset_mgt.utils.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/visitor_mgt/")
public class Controller {
  private Map<String, Object> generateTextField(String name, String label, boolean required, String defaultValue,boolean show) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", name);
    field.put("label", label);
    field.put("required", required);
    field.put("type", "text");
    field.put("defaultValue", defaultValue);
    field.put("show", show);
    return field;
  }
  private Map<String, Object> generateEmailField(String name, String label, boolean required, String defaultValue,boolean show) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", name);
    field.put("label", label);
    field.put("required", required);
    field.put("type", "email_id");
    field.put("defaultValue", defaultValue);
    field.put("show",show);
    return field;
  }
  private Map<String, Object> generateImageField(String name,boolean dependent,boolean show){
    Map<String, Object> field = new HashMap<>();
    field.put("name", name);
    field.put("type", "image");
    field.put("show", show);
    return field;
  }
  private Map<String, Object> generateMobileNumber(String name, String label, boolean required, String defaultValue,boolean show) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", name);
    field.put("label", label);
    field.put("required", required);
    field.put("type", "phone_no");
    field.put("defaultValue", defaultValue);
    field.put("show", show);
    return field;
  }
  private Map<String, Object> generateNumberField(String name, String label, boolean required, long defaultValue,boolean show) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", name);
    field.put("label", label);
    field.put("required", required);
    field.put("type", "number");
    field.put("defaultValue", defaultValue);
    field.put("show", show);
    return field;
  }
  private Map<String, Object> generateAutocompleteField(String name, String label, boolean required, String defaultValue, String[] options,boolean show,boolean updateField) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", name);
    field.put("label", label);
    field.put("required", required);
    field.put("type", "autocomplete");
    field.put("options", options);
    field.put("defaultValue", defaultValue);
    field.put("show", show);
    field.put("updateField",updateField);
    return field;
  }
  private Map<String, Object> generateDateField(String name, String label, boolean required, Date minDate,Date defaultValue,boolean show) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", name);
    field.put("label", label);
    field.put("required", required);
    field.put("type", "Date");
    field.put("minDate", minDate);
    field.put("defaultValue",defaultValue);
    field.put("show", show);
    return field;
  }
  private final Dao dao = new Dao(); // Instantiate your DAO class
  private final Set<Integer> generatedIds = new HashSet<>(); // To store generated IDs and avoid duplicates


  @GetMapping("/Getfields")
  public ResponseEntity<Map<String, Object>> getFormFields() {
    try{
    List<Map<String, Object>> fields = new ArrayList<>();

    ImageFieldGenerator image = new ImageFieldGenerator("image", true);
    TextFieldGenerator name = new TextFieldGenerator("name", "Visitor Name", true, "Kirtan", false);
    AutoCompleteFieldGenerator visitType = new AutoCompleteFieldGenerator("visit_type", "Visit Type", true, "General", new String[]{"General", "Special BMC", "Special Foundry", "Special Floor"}, false, false);
    EmailFieldGenerator email = new EmailFieldGenerator("email", "Email Address", true, "kirtan@gmail.com", false);
    AutoCompleteFieldGenerator gender = new AutoCompleteFieldGenerator("Gender", "Gender", true, "Male", new String[]{"Male", "Female", "Others"}, true, true);
    TextFieldGenerator entryGate = new TextFieldGenerator("entry_gate", "Entry Gate", false, "Front", true);
    TextFieldGenerator designation = new TextFieldGenerator("designation", "Designation", false, "", true);
    TextFieldGenerator idProofNumber = new TextFieldGenerator("id_proof_number", "Id proof Number", true, "", true);
    TextFieldGenerator purpose = new TextFieldGenerator("purpose", "Purpose", false, "Visit to tepl", true);
    MobileNumberFieldGenerator phoneNo = new MobileNumberFieldGenerator("phone_no", "Phone Number", false, "7984651231", false);
    TextFieldGenerator place = new TextFieldGenerator("place", "Place", false, "Anand", false);
    TextFieldGenerator guestCompany = new TextFieldGenerator("guest_company", "Guest Company", false, "BMC", false);
    AutoCompleteFieldGenerator appointmentHalf = new AutoCompleteFieldGenerator("appointment_half", "Appointment half", false, "First Half", new String[]{"First Half", "Second Half"}, false, false);
    AutoCompleteFieldGenerator deptName = new AutoCompleteFieldGenerator("dept_name", "Department Name", false, "Mechanical Department", new String[]{"Mechanical Department", "Shop Department"}, true, false);
    AutoCompleteFieldGenerator visitFrequency = new AutoCompleteFieldGenerator("visit_frequency", "Visit Frequency", true, "Single", new String[]{"Single", "Multiple"}, true, false);
    NumberFieldGenerator visitorCount = new NumberFieldGenerator("visitor_count", "Total Visitors", false, 1, true);

    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DAY_OF_MONTH, 1);
    Date increasedDate = calendar.getTime();

    DateFieldGenerator toDate = new DateFieldGenerator("to_date", "To Date", true, new Date(), new Date(), false);
    DateFieldGenerator fromDate = new DateFieldGenerator("from_date", "Form Date", true, increasedDate, increasedDate, false);

    fields.add(image.getField());
    fields.add(name.getField());
    fields.add(visitType.getField());
    fields.add(email.getField());

    fields.add(gender.getField());

    fields.add(entryGate.getField());

    fields.add(designation.getField());

    fields.add(idProofNumber.getField());

    fields.add(purpose.getField());

    fields.add(phoneNo.getField());

    fields.add(place.getField());

    fields.add(guestCompany.getField());

    fields.add(appointmentHalf.getField());
    fields.add(deptName.getField());

    fields.add(visitFrequency.getField());
    fields.add(visitorCount.getField());


//    fields.add(generateImageField("image", true,false));
//    fields.add(generateTextField("name", "Visitor Name", true, "kirtan",false));
//    fields.add(generateAutocompleteField("visit_type", "Visit Type", true, "General",new String[]{"General", "Special BMC","Special Foundry","Special Floor"},false,false));
//    fields.add(generateEmailField("email", "Email Address", true, "kirtan@gmail.com",false));
//    fields.add(generateAutocompleteField("Gender", "Gender", true, "Male",new String[]{"Male", "Female", "Others"},true,true));
//    fields.add(generateTextField("entry_gate", "Entry Gate", false, "Front",true));
//    fields.add(generateTextField("designation", "Designation", false, "",true));
//    fields.add(generateTextField("id_proof_number", "Id proof Number", true, "",true));
//    fields.add(generateTextField("purpose", "Purpose", false, "Visit to tepl",true));
//    fields.add(generateMobileNumber("phone_no", "Phone Number", false,"7984651231",false));
//    fields.add(generateTextField("place", "Place", false, "Anand",false));
//    fields.add(generateTextField("guest_company", "Guest Company", false, "BMC",false));
//    fields.add(generateAutocompleteField("appointment_half", "Appointment half", false,  "First Half",new String[]{"First Half", "Second Half"},false,false));
//    fields.add(generateAutocompleteField("dept_name", "Department Name", false, "Mechanical Department",new String[]{"Mechanical Department", "Shop Department"},true,false));
//    fields.add(generateAutocompleteField("visit_frequency", "Visit Frequency", true, "Single",new String[]{"Single", "Multiple"},true,false));
//    fields.add(generateNumberField("visitor_count", "Total Visitors", false, 1,true));
//
//    Date date = new Date();
//    Calendar calendar = Calendar.getInstance();
//    calendar.setTime(date);
//    calendar.add(Calendar.DAY_OF_MONTH, 1);
//    Date increasedDate = calendar.getTime();
//    fields.add(generateDateField("to_date", "To Date", true, new Date() ,new Date(),false));
//
//    fields.add(generateDateField("from_date", "Form Date", true, increasedDate,increasedDate,false));
//


    ArrayList<String> watchFields = new ArrayList<>();
    watchFields.add("visit_frequency");
    watchFields.add("visit_type");
    watchFields.add("Gender");

    Map<String, Object> successResponse = new HashMap<>();
    successResponse.put("status", true);
    successResponse.put("fields", fields);
    successResponse.put("watchFields",watchFields);
    return ResponseEntity.ok(successResponse);
  } catch (Exception e) {
    return handleException(e);
  }
  }
  // edit (default values with options),
  // view(default values with disabled fields),
  // add new form(empty fields with options)

  @PostMapping("getFields/{FormType}")  //edit,view,AddNew
  public ResponseEntity<Map<String, Object>> getFields(@PathVariable String FormType) {
    ImageFieldGenerator image = new ImageFieldGenerator("image", true);
    TextFieldGenerator name = new TextFieldGenerator("name", "Visitor Name", true, "Kirtan", false);
    AutoCompleteFieldGenerator visitType = new AutoCompleteFieldGenerator("visit_type", "Visit Type", true, "General", new String[]{"General", "Special BMC", "Special Foundry", "Special Floor"}, false, false);
    EmailFieldGenerator email = new EmailFieldGenerator("email", "Email Address", true, "kirtan@gmail.com", true);
    AutoCompleteFieldGenerator gender = new AutoCompleteFieldGenerator("Gender", "Gender", true, "Male", new String[]{"Male", "Female", "Others"}, true, true);
    TextFieldGenerator entryGate = new TextFieldGenerator("entry_gate", "Entry Gate", false, "Front", true);
    TextFieldGenerator designation = new TextFieldGenerator("designation", "Designation", false, "", true);
    TextFieldGenerator idProofNumber = new TextFieldGenerator("id_proof_number", "Id proof Number", true, "", true);
    TextFieldGenerator purpose = new TextFieldGenerator("purpose", "Purpose", false, "Visit to tepl", true);
    MobileNumberFieldGenerator phoneNo = new MobileNumberFieldGenerator("phone_no", "Phone Number", false, "7984651231", false);
    TextFieldGenerator place = new TextFieldGenerator("place", "Place", false, "Anand", false);
    TextFieldGenerator guestCompany = new TextFieldGenerator("guest_company", "Guest Company", false, "BMC", false);
    AutoCompleteFieldGenerator appointmentHalf = new AutoCompleteFieldGenerator("appointment_half", "Appointment half", false, "First Half", new String[]{"First Half", "Second Half"}, false, false);
    AutoCompleteFieldGenerator deptName = new AutoCompleteFieldGenerator("dept_name", "Department Name", false, "Mechanical Department", new String[]{"Mechanical Department", "Shop Department"}, true, false);
    AutoCompleteFieldGenerator visitFrequency = new AutoCompleteFieldGenerator("visit_frequency", "Visit Frequency", true, "Single", new String[]{"Single", "Multiple"}, true, false);
    NumberFieldGenerator visitorCount = new NumberFieldGenerator("visitor_count", "Total Visitors", false, 1, true);

    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DAY_OF_MONTH, 1);
    Date increasedDate = calendar.getTime();

    DateFieldGenerator toDate = new DateFieldGenerator("to_date", "To Date", true, new Date(), new Date(), false);
    DateFieldGenerator fromDate = new DateFieldGenerator("from_date", "Form Date", true, increasedDate, increasedDate, false);


      switch (FormType) {
          case "edit" -> {
              List<Map<String, Object>> fields = new ArrayList<>();
              fields.add(image.getField());
              fields.add(name.getField());
              fields.add(visitType.getField());
              fields.add(email.getField());

              fields.add(gender.getField());

              fields.add(entryGate.getField());

              fields.add(designation.getField());

              fields.add(idProofNumber.getField());

              fields.add(purpose.getField());

              fields.add(phoneNo.getField());

              fields.add(place.getField());

              fields.add(guestCompany.getField());

              fields.add(appointmentHalf.getField());
              fields.add(deptName.getField());

              fields.add(visitFrequency.getField());
              fields.add(visitorCount.getField());
              Map<String, Object> successResponse = new HashMap<>();
              successResponse.put("status", true);
              successResponse.put("fields", fields);
              return ResponseEntity.ok(successResponse);
          }
          case "view" -> {
              List<Map<String, Object>> fields = new ArrayList<>();
              fields.add(image.getViewField(false));
              fields.add(name.getViewField());
              fields.add(visitType.getViewField());
              fields.add(email.getViewField());

              fields.add(gender.getViewField());

              fields.add(entryGate.getViewField());

              fields.add(designation.getViewField());

              fields.add(idProofNumber.getViewField());

              fields.add(purpose.getViewField());

              fields.add(phoneNo.getViewField());

              fields.add(place.getViewField());

              fields.add(guestCompany.getViewField());

              fields.add(appointmentHalf.getViewField());
              fields.add(deptName.getViewField());

              fields.add(visitFrequency.getViewField());
              fields.add(visitorCount.getViewField());
              fields.add(fromDate.getViewField());
              fields.add(toDate.getViewField());
              Map<String, Object> successResponse = new HashMap<>();
              successResponse.put("status", true);
              successResponse.put("fields", fields);
              return ResponseEntity.ok(successResponse);
          }
          case "AddNew" -> {
              List<Map<String, Object>> fields = new ArrayList<>();
              fields.add(image.getField(false));
              fields.add(name.getAddNewField());
              fields.add(visitType.getAddNewField());
              fields.add(email.getAddNewField());

              fields.add(gender.getAddNewField());

              fields.add(entryGate.getAddNewField());

              fields.add(designation.getAddNewField());

              fields.add(idProofNumber.getAddNewField());

              fields.add(purpose.getAddNewField());

              fields.add(phoneNo.getAddNewField());

              fields.add(place.getAddNewField());

              fields.add(guestCompany.getAddNewField());

              fields.add(appointmentHalf.getAddNewField());
              fields.add(deptName.getAddNewField());

              fields.add(visitFrequency.getAddNewField());
              fields.add(visitorCount.getAddNewField());
              fields.add(fromDate.getAddNewField());
              fields.add(toDate.getAddNewField());
              Map<String, Object> successResponse = new HashMap<>();
              successResponse.put("status", true);
              successResponse.put("fields", fields);
              return ResponseEntity.ok(successResponse);
          }
          default -> {
              Map<String, Object> successResponse = new HashMap<>();
              successResponse.put("status", false);
              successResponse.put("fields", null);
              successResponse.put("message", "please add Form Type");
              return ResponseEntity.ok(successResponse);
          }
      }
  }

  @PostMapping("/update_fields/{fieldName}")
  public ResponseEntity<Map<String, Object>> updateFields(@PathVariable String fieldName, @RequestBody Map<String,Map<String, String>> requestBody) {
    try{

      List<Map<String, Object>> fields = new ArrayList<>();

      fields.add(generateImageField("image", true,false));
      fields.add(generateTextField("name", "Visitor Name", true, requestBody.get("values").get("name"),true));
      fields.add(generateAutocompleteField("visit_type", "Visit Type", true, requestBody.get("values").get("visit_type"),new String[]{"General", "Special BMC","Special Foundry","Special Floor", "WOWW"},false,false));
      fields.add(generateEmailField("email", "Email Address", true, requestBody.get("values").get("email"),false));
      fields.add(generateAutocompleteField("Gender", "Gender", true, requestBody.get("values").get("Gender"),new String[]{"Male", "Female"},true,true));
      fields.add(generateTextField("entry_gate", "Entry Gate", false, requestBody.get("values").get("entry_gate"),true));
      fields.add(generateTextField("designation", "Designation", false, requestBody.get("values").get("designation"),true));
      fields.add(generateTextField("id_proof_number", "Id proof Number", true, requestBody.get("values").get("id_proof_number"),true));
      fields.add(generateTextField("purpose", "Purpose", false, requestBody.get("values").get("purpose"),true));
      fields.add(generateMobileNumber("phone_no", "Phone Number", false,requestBody.get("values").get("phone_no"),false));
      fields.add(generateTextField("place", "Place", false, requestBody.get("values").get("place"),false));
      fields.add(generateTextField("guest_company", "Guest Company", false, requestBody.get("values").get("guest_company"),false));
      fields.add(generateAutocompleteField("appointment_half", "Appointment half", false,  requestBody.get("values").get("appointment_half"),new String[]{"First Half", "Second Half"},false,false));
      fields.add(generateAutocompleteField("dept_name", "Department Name", false, requestBody.get("values").get("dept_name"),new String[]{"Mechanical Department", "Shop Department"},true,false));
      fields.add(generateAutocompleteField("visit_frequency", "Visit Frequency", true, requestBody.get("values").get("visit_frequency"),new String[]{"Single", "Multiple"},true,true));
      fields.add(generateNumberField("visitor_count", "Total Visitors", false, Integer.parseInt(requestBody.get("values").get("visitor_count")),false));

      Date date = new Date();
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(Calendar.DAY_OF_MONTH, 1);
      Date increasedDate = calendar.getTime();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

      Date toDate = dateFormat.parse(requestBody.get("values").get("to_date"));
      fields.add(generateDateField("to_date", "To Date", true, new Date(), toDate,true));

      Date fromDate = dateFormat.parse(requestBody.get("values").get("from_date"));
      fields.add(generateDateField("from_date", "Form Date", true, increasedDate,fromDate,true));
//      System.out.println("fieldName: "+ fieldName);
      System.out.println("13th field: "+ fields.get(13));
      System.out.println("requesteBody"+requestBody.get("fieldValue").get("newValue"));
      switch (fieldName){
        case "Gender":
          fields.get(4).put("defaultValue",requestBody.get("fieldValue").get("newValue"));
          if(requestBody.get("fieldValue").get("newValue").equals("Female")){
            System.out.println("13th field: "+ fields.get(13));
            fields.getFirst().put("show",true);
            fields.get(13).put("defaultValue", "IT Department");
            fields.get(13).put("options",new String[]{"IT Department", "CRM Department"});
            fields.get(14).put("show",false);
            fields.get(15).put("show",true);
            fields.get(15).put("defaultValue",4);
          }
          if(requestBody.get("fieldValue").get("newValue").equals("Male")){
            fields.getFirst().put("show",false);
            fields.get(13).put("defaultValue", "Mechanical Department");
            fields.get(13).put("options",new String[]{"Mechanical Department", "Shop Department"});
          }
          System.out.println("GetFirst: "+ fields.getFirst());
          break;
        case "visit_frequency":
          fields.get(4).put("defaultValue",requestBody.get("fieldValue").get("newValue"));
          if(requestBody.get("fieldValue").get("newValue").equals("Multiple")){
            System.out.println("13th field: "+ fields.get(13));
            fields.getLast().put("show",true);
          }
          if(requestBody.get("fieldValue").get("newValue").equals("Single")){
            fields.getLast().put("show",false);
          }
          System.out.println("GetFirst: "+ fields.getFirst());
          break;
      }
      System.out.println("13th field: "+ fields.get(13));

      ArrayList<String> watchFields = new ArrayList<>();
      watchFields.add("visit_frequency");
      watchFields.add("visit_type");
      watchFields.add("Gender");
      Map<String, Object> successResponse = new HashMap<>();
      successResponse.put("status", true);
      successResponse.put("fields", fields);
      successResponse.put("watchFields",watchFields);
      return ResponseEntity.ok(successResponse);
    }
    catch (Exception e) {
      return handleException(e);
    }

  }
  @GetMapping("/visits")
  public ResponseEntity<Map<String, Object>> getVisits() {
    try {
      Dao.visitsDao dummyVisit = createRandomVisits();
      // Ensure the ID is unique
      while (generatedIds.contains(dummyVisit.getId())) {
        dummyVisit.setId(dummyVisit.getId() + 1);
      }
      generatedIds.add(dummyVisit.getId());

      VisitsResponse resp1 = new VisitsResponse("IN",123,"adasd","Multiple",new Date(),new Date(),new Date(),"kirtan","https://img.freepik.com/free-photo/painting-mountain-lake-with-mountain-background_188544-9126.jpg");
      VisitsResponse resp2 = new VisitsResponse(null,123,"adasd","Multiple",new Date(),null,new Date(),"kirtan","https://img.freepik.com/free-photo/painting-mountain-lake-with-mountain-background_188544-9126.jpg");
      VisitsResponse resp3 = new VisitsResponse("IN",123,"adasd","Multiple",null,null,new Date(),"kirtan","https://img.freepik.com/free-photo/painting-mountain-lake-with-mountain-background_188544-9126.jpg");
//      VisitsResponse resp2 = new VisitsResponse(123,"adasd","Multiple",new Date("2024-03-11T09:47:05"),new Date(),new Date(),"kirtan","https://img.freepik.com/free-photo/painting-mountain-lake-with-mountain-background_188544-9126.jpg");

      List<VisitsResponse> visitsList = new ArrayList<>();
      visitsList.add(resp1);
      visitsList.add(resp2);
      visitsList.add(resp3);

      Map<String, Object> successResponse = new HashMap<>();
      successResponse.put("status", true);
      successResponse.put("data", visitsList);

      return ResponseEntity.ok(successResponse);
    } catch (Exception e) {
      return handleException(e);
    }
  }
  @PostMapping("/createVisits")
  public ResponseEntity<Map<String, Object>> createVisits(@Valid @RequestBody Object requestBody) {
    System.out.println("Request body: " + requestBody);
    Map<String, Object> successResponse = new HashMap<>();
      successResponse.put("status", true);
      successResponse.put("message", "Visit created successfully!");
      return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);

  }

  @GetMapping("/visitDetails")
  public ResponseEntity<Map<String, Object>> getVisitDetails() {
    try {
      Dao.VisitDetails dummyVisitDetails = createRandomVisitDetails();
      while (generatedIds.contains(dummyVisitDetails.getId())) {
        dummyVisitDetails.setId(dummyVisitDetails.getId() + 1);
      }
      generatedIds.add(dummyVisitDetails.getId());
      Map<String, Object> successResponse = new HashMap<>();
      successResponse.put("status", true);
      successResponse.put("data", dummyVisitDetails);
      return ResponseEntity.ok(successResponse);
    } catch (Exception e) {
      return handleException(e);
    }
  }

  @GetMapping("/visitLocations")
  public ResponseEntity<Map<String, Object>> getVisitLocations() {
    try {
      Dao.visitLocations dummyVisitLocations = createRandomVisitLocations();
      // Ensure the ID is unique
      while (generatedIds.contains(dummyVisitLocations.getId())) {
        dummyVisitLocations.setId(dummyVisitLocations.getId() + 1);
      }
      generatedIds.add(dummyVisitLocations.getId());

      Map<String, Object> successResponse = new HashMap<>();
      successResponse.put("status", true);
      successResponse.put("data", dummyVisitLocations);

      return ResponseEntity.ok(successResponse);
    } catch (Exception e) {
      return handleException(e);
    }
  }

  @GetMapping("/visitors")
  public ResponseEntity<Map<String, Object>> getVisitors() {
    try {
      Dao.Visitors dummyVisitor = createRandomVisitor();
      // Ensure the ID is unique
      while (generatedIds.contains(dummyVisitor.getId())) {
        dummyVisitor.setId(dummyVisitor.getId() + 1);
      }
      generatedIds.add(dummyVisitor.getId());

      Map<String, Object> successResponse = new HashMap<>();
      successResponse.put("status", true);
      successResponse.put("data", dummyVisitor);

      return ResponseEntity.ok(successResponse);
    } catch (Exception e) {
      return handleException(e);
    }
  }

  @GetMapping("/visitType")
  public ResponseEntity<Map<String, Object>> getVisitType() {
    try {
      Dao.visit_type dummyVisitType = createRandomVisitType();
      // Ensure the ID is unique
      while (generatedIds.contains(dummyVisitType.getId())) {
        dummyVisitType.setId(dummyVisitType.getId() + 1);
      }
      generatedIds.add(dummyVisitType.getId());

      Map<String, Object> successResponse = new HashMap<>();
      successResponse.put("status", true);
      successResponse.put("data", dummyVisitType);

      return ResponseEntity.ok(successResponse);
    } catch (Exception e) {
      return handleException(e);
    }
  }

  @GetMapping("/locations")
  public ResponseEntity<Map<String, Object>> getLocations() {
    try {
      Dao.locations dummyLocation = createRandomLocation();
      // Ensure the ID is unique
      while (generatedIds.contains(dummyLocation.getId())) {
        dummyLocation.setId(dummyLocation.getId() + 1);
      }
      generatedIds.add(dummyLocation.getId());

      Map<String, Object> successResponse = new HashMap<>();
      successResponse.put("status", true);
      successResponse.put("data", dummyLocation);

      return ResponseEntity.ok(successResponse);
    } catch (Exception e) {
      return handleException(e);
    }
  }

  @GetMapping("/locationGates")
  public ResponseEntity<Map<String, Object>> getLocationGates() {
    try {
      Dao.loactionGates dummyLocationGate = createRandomLocationGate();
      // Ensure the ID is unique
      while (generatedIds.contains(dummyLocationGate.getId())) {
        dummyLocationGate.setId(dummyLocationGate.getId() + 1);
      }
      generatedIds.add(dummyLocationGate.getId());

      Map<String, Object> successResponse = new HashMap<>();
      successResponse.put("status", true);
      successResponse.put("data", dummyLocationGate);

      return ResponseEntity.ok(successResponse);
    } catch (Exception e) {
      return handleException(e);
    }
  }

  // Helper methods to create random dummy objects
  private Dao.visitsDao createRandomVisits() {
    String VisitTitle = UUID.randomUUID().toString().substring(0, 8);
    String VisitPurpose = UUID.randomUUID().toString().substring(0, 8);
    Date visit_date_from  = new Date(System.currentTimeMillis() + (new Random().nextInt(30) * 24L * 3600 * 1000));
    Dao.visitsDao visit = new Dao.visitsDao();
    visit.setId(new Random().nextInt(1000) + 1);
    visit.setVisit_title("Visit_" + UUID.randomUUID().toString().substring(0, 8));
    visit.setVisit_purpose("Purpose_" + UUID.randomUUID().toString().substring(0, 8));
    visit.setVisit_date_from(new Date(System.currentTimeMillis() + (new Random().nextInt(30) * 24L * 3600 * 1000)));
    visit.setVisit_date_to(new Date(System.currentTimeMillis() + (new Random().nextInt(30) * 24L * 3600 * 1000)));
    visit.setVisit_time_from(new Date(System.currentTimeMillis() + (new Random().nextInt(24) * 3600 * 1000)));
    visit.setVisit_time_to(new Date(System.currentTimeMillis() + (new Random().nextInt(24) * 3600 * 1000)));
    visit.setHalf(UUID.randomUUID().toString().substring(0, 4));
    visit.setVisit_type(new Random().nextInt(5) + 1);
    visit.setIs_multi_days(new Random().nextBoolean());
    visit.setIs_multi_locations(new Random().nextBoolean());
    visit.setRequested_by(new Random().nextInt(100) + 1);
    visit.setIs_completed(new Random().nextBoolean());
    visit.setIs_in_progress(new Random().nextBoolean());
    visit.setTotal_visitors(new Random().nextInt(20) + 1);

    return visit;
  }

  private Dao.visit_type createRandomVisitType() {
    Dao.visit_type visitType = new Dao.visit_type();
    visitType.setId(new Random().nextInt(1000) + 1);
    visitType.setTitle("VisitType_" + UUID.randomUUID().toString().substring(0, 8));
    visitType.setIs_approval(new Random().nextBoolean());

    return visitType;
  }

  private Dao.locations createRandomLocation() {
    Dao.locations location = new Dao.locations();
    location.setId(new Random().nextInt(1000) + 1);
    location.setLocation_title("Location_" + UUID.randomUUID().toString().substring(0, 8));
    location.setLocation_remarks("Remarks_" + UUID.randomUUID().toString().substring(0, 8));

    return location;
  }

  private Dao.VisitDetails createRandomVisitDetails() {
    Dao.VisitDetails visitDetails = new Dao.VisitDetails();
    visitDetails.setId(new Random().nextInt(1000) + 1);
    visitDetails.setVisit_id(new Random().nextInt(1000) + 1);
    visitDetails.setVisior_id(new Random().nextInt(1000) + 1);
    visitDetails.setIn_time(new Date(System.currentTimeMillis() + (new Random().nextInt(24) * 3600 * 1000)));
    visitDetails.setOut_time(new Date(System.currentTimeMillis() + (new Random().nextInt(24) * 3600 * 1000)));
    visitDetails.setVisit_date(new Date(System.currentTimeMillis() + (new Random().nextInt(30) * 24L * 3600 * 1000)));
    visitDetails.setGate_user_id(new Random().nextInt(100) + 1);
    visitDetails.setGate_id(new Random().nextInt(100) + 1);

    return visitDetails;
  }

  private Dao.visitLocations createRandomVisitLocations() {
    Dao.visitLocations visitLocations = new Dao.visitLocations();
    visitLocations.setId(new Random().nextInt(1000) + 1);
    visitLocations.setVisit_details_id(new Random().nextInt(1000) + 1);
    visitLocations.setVisit_id(new Random().nextInt(1000) + 1);
    visitLocations.setLocation_id(new Random().nextInt(100) + 1);
    visitLocations.setLocation_gate_id(new Random().nextInt(100) + 1);
    visitLocations.setRef_person_id(new Random().nextInt(1000) + 1);
    visitLocations.setIn_time(new Date(System.currentTimeMillis() + (new Random().nextInt(24) * 3600 * 1000)));
    visitLocations.setOut_time(new Date(System.currentTimeMillis() + (new Random().nextInt(24) * 3600 * 1000)));
    visitLocations.setGate_user_id(new Random().nextInt(100) + 1);

    return visitLocations;
  }

  private Dao.loactionGates createRandomLocationGate() {
    Dao.loactionGates locationGate = new Dao.loactionGates();
    locationGate.setId(new Random().nextInt(1000) + 1);
    locationGate.setLocation_id(new Random().nextInt(100) + 1);
    locationGate.setGateName("Gate_" + UUID.randomUUID().toString().substring(0, 8));
    locationGate.setGate_desc("Description_" + UUID.randomUUID().toString().substring(0, 8));

    return locationGate;
  }

  private Dao.Visitors createRandomVisitor() {
    Dao.Visitors visitor = new Dao.Visitors();
    visitor.setId(new Random().nextInt(1000) + 1);
    visitor.setFirst_name("First_" + UUID.randomUUID().toString().substring(0, 8));
    visitor.setMid_name("Mid_" + UUID.randomUUID().toString().substring(0, 8));
    visitor.setLast_name("Last_" + UUID.randomUUID().toString().substring(0, 8));
    visitor.setMobile_no(new Random().nextInt(1000000000) + 100000000); // Random 10-digit mobile number
    visitor.setEmail(visitor.getFirst_name().toLowerCase() + "." + visitor.getLast_name().toLowerCase() + "@example.com");
    visitor.setPan_card("PAN" + UUID.randomUUID().toString().substring(0, 8));
    visitor.setAddhar_Card("AADHAR" + UUID.randomUUID().toString().substring(0, 8));
    visitor.setPhoot("photo_url"); // Replace with the actual photo URL

    return visitor;
  }
  @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception e) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("status", false);
    errorResponse.put("error", e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
      Map<String,String> resp = new HashMap<>();
      ex.getBindingResult().getAllErrors().forEach((error) -> {
        String fieldName = ((FieldError) error).getField();
        String message = error.getDefaultMessage();
        resp.put(fieldName,message);
      });
        return new ResponseEntity<Map<String,String>>(resp, HttpStatus.BAD_REQUEST);
    }


  @ExceptionHandler(CategoryNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleCategoryNotFoundException(CategoryNotFoundException e) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("status", false);
    errorResponse.put("error", e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }
}
