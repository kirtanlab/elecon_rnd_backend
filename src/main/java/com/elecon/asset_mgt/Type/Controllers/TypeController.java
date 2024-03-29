package com.elecon.asset_mgt.Type.Controllers;

import com.elecon.asset_mgt.Classification.Services.ClassificationNotFoundException;
import com.elecon.asset_mgt.Exceptions.ForeignKeyViolationException;
import com.elecon.asset_mgt.Type.DAO.CreatetypeDao;
import com.elecon.asset_mgt.Type.Models.TypeModel;
import com.elecon.asset_mgt.Type.Services.TypeService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/type/")
public class TypeController {
    @Autowired
    private TypeService typeservice;
  private static Map<String, Object> createColumn (String id, String label, String type) {
    Map<String, Object> column = new HashMap<>();
    column.put("id", id);
    column.put("label", label);
    column.put("type", type);
    return column;
  }
    @PostMapping("/Createtype")
    public ResponseEntity<Map<String, Object>> createtype(@RequestBody CreatetypeDao typemodeldao) {
        try {
            System.out.println("typemodel "+typemodeldao);
            typeservice.save(typemodeldao);
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("status", true);
            successResponse.put("message", "New type is created successfully!");
            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
        } catch (ForeignKeyViolationException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", false);
            errorResponse.put("error", "selected attribute from dropdown is not valid!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            return handleException(e);
        }
    }

  @GetMapping("/")
  public ResponseEntity<Map<String, Object>> getType() {
    try {
      List<TypeModel> result = typeservice.getAll();
      List<Map<String, Object>> simplifiedData = new ArrayList<>();

      for (TypeModel type : result) {
        Map<String, Object> simplifiedType = new HashMap<>();

        // Destructure the TypeModel object
        String typeName = type.getType_name();
        String typeDesc = type.getType_desc();
        boolean status = type.isStatus();
        Integer id = type.getId();
        // Extract properties from associated objects
        String classificationName = type.getClass_id().getClass_name();
        String categoryName = type.getCategory_id().getCategory_name();

        // Add the extracted properties to the simplified map
        simplifiedType.put("typeName", typeName);
        simplifiedType.put("typeDesc", typeDesc);
        simplifiedType.put("status", status);
        simplifiedType.put("classificationName", classificationName);
        simplifiedType.put("categoryName", categoryName);
        simplifiedType.put("id", id);
        simplifiedType.put("image","https://i.ytimg.com/vi/hqYtDJunDzs/maxresdefault.jpg");
        simplifiedData.add(simplifiedType);
      }
      List<Map<String, Object>> columns = new ArrayList<>();

      columns.add(createColumn("id", "ID", "string"));
      columns.add(createColumn("image", "image", "image"));
      columns.add(createColumn("typeName", "Type Name", "string"));
      columns.add(createColumn("typeDesc", "Type Description", "string"));
      columns.add(createColumn("categoryName", "Category name", "string"));
      columns.add(createColumn("classificationName", "Category Description", "string"));
      columns.add(createColumn("status", "status", "status"));

      columns.add(createColumn("options", "Options", "options"));

      Map<String, Object> tableInfo = new HashMap<>();
      tableInfo.put("tableTitle","Assets Types Details");
      tableInfo.put("Breadcrumbs", "Asset Types");
      tableInfo.put("tableSearchPlaceholder","Search id / types / category name");
      tableInfo.put("tableAddObject", "Add New Type");
      tableInfo.put("tableDensity", true); //dense true


      Map<String, Object> responseBody = new HashMap<>();
      responseBody.put("status", true);
      responseBody.put("data", simplifiedData);
      responseBody.put("tableLabels", columns);
      responseBody.put("tableInfo", tableInfo);

      return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    } catch (Exception e) {
      return handleException(e);
    }
  }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTypeById(@PathVariable Integer id) {
        try {
            Optional<TypeModel> type = typeservice.findById(id);
            if (type.isPresent()) {
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("status", true);
                responseBody.put("data", type.get());
                return ResponseEntity.status(HttpStatus.OK).body(responseBody);
            } else {
                throw new ClassificationNotFoundException("type not found with ID: " + id);
            }
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletetypeById(@PathVariable Integer id) {
        try {
            typeservice.deleteById(id);
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("status", true);
            successResponse.put("message", "type with ID " + id + " deleted successfully!");
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
