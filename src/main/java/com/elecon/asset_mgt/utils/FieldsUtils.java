package com.elecon.asset_mgt.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FieldsUtils {
  private Map<String, Object> generateTextField(String name, String label, boolean required, String defaultValue, boolean show,boolean disabled) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", name);
    field.put("label", label);
    field.put("required", required);
    field.put("type", "text");
    field.put("defaultValue", defaultValue);
    field.put("show", show);
    field.put("disabled",disabled);
    return field;
  }
  private Map<String, Object> generateEmailField(String name, String label, boolean required, String defaultValue,boolean show,boolean disabled) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", name);
    field.put("label", label);
    field.put("required", required);
    field.put("type", "email_id");
    field.put("defaultValue", defaultValue);
    field.put("show",show);
    field.put("disabled",disabled);
    return field;
  }
  private Map<String, Object> generateImageField(String name,boolean dependent,boolean show,boolean disabled){
    Map<String, Object> field = new HashMap<>();
    field.put("name", name);
    field.put("type", "image");
    field.put("show", show);
    field.put("disabled",disabled);
    return field;
  }
  private Map<String, Object> generateMobileNumber(String name, String label, boolean required, String defaultValue,boolean show,boolean disabled) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", name);
    field.put("label", label);
    field.put("required", required);
    field.put("type", "phone_no");
    field.put("defaultValue", defaultValue);
    field.put("show", show);
    field.put("disabled",disabled);
    return field;
  }
  private Map<String, Object> generateNumberField(String name, String label, boolean required, long defaultValue,boolean show,boolean disabled) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", name);
    field.put("label", label);
    field.put("required", required);
    field.put("type", "number");
    field.put("defaultValue", defaultValue);
    field.put("show", show);
    field.put("disabled",disabled);
    return field;
  }

  private Map<String, Object> generateAutocompleteField(String name, String label, boolean required, String defaultValue, String[] options,boolean show,boolean updateField,boolean disabled) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", name);
    field.put("label", label);
    field.put("required", required);
    field.put("type", "autocomplete");
    field.put("options", options);
    field.put("defaultValue", defaultValue);
    field.put("show", show);
    field.put("updateField",updateField);
    field.put("disabled",disabled);
    return field;
  }
  private Map<String, Object> generateDateField(String name, String label, boolean required, Date minDate, Date defaultValue, boolean show,boolean disabled) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", name);
    field.put("label", label);
    field.put("required", required);
    field.put("type", "Date");
    field.put("minDate", minDate);
    field.put("defaultValue",defaultValue);
    field.put("show", show);
    field.put("disabled",disabled);
    return field;
  }
  private static Map<String,Object> createModal (String label, String color, String api_endopoint,boolean show,boolean disabled){ //api_endopoint to get form to submit Modal
    Map<String,Object> modal_object = new HashMap<>();
    modal_object.put("label", label);
    modal_object.put("color", color);
    modal_object.put("api_endopoint", api_endopoint);
    modal_object.put("show", show);
    modal_object.put("disabled", disabled);

    return modal_object;
  }
  private static Map<String, Object> createColumn (String id, String label, String type) {
    Map<String, Object> column = new HashMap<>();
    column.put("id", id);
    column.put("label", label);
    column.put("type", type);
    return column;
  }
}
