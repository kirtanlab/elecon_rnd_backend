package com.elecon.asset_mgt.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class AutoCompleteFieldGenerator {
  private String name;
  private String label;
  private boolean required;
  private String defaultValue;
  private String[] options;
  private boolean show;
  private boolean updateField;
  private boolean disabled;

  public AutoCompleteFieldGenerator(String name, String label, boolean required, String defaultValue, String[] options, boolean show, boolean updateField,boolean disabled) {
    this.name = name;
    this.label = label;
    this.required = required;
    this.defaultValue = defaultValue;
    this.options = options;
    this.show = show;
    this.updateField = updateField;
    this.disabled = disabled;
  }
  public AutoCompleteFieldGenerator(String name, String label, boolean required, String defaultValue, String[] options, boolean show, boolean updateField) {
    this.name = name;
    this.label = label;
    this.required = required;
    this.defaultValue = defaultValue;
    this.options = options;
    this.show = show;
    this.updateField = updateField;
  }

  public Map<String, Object> getField() {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "autocomplete");
    field.put("options", this.options);
    field.put("defaultValue", this.defaultValue);
    field.put("show", this.show);
    field.put("updateField", this.updateField);
    return field;
  }
  public Map<String, Object> getField(String defaultValue) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "autocomplete");
    field.put("options", this.options);
    field.put("defaultValue", defaultValue);
    field.put("show", this.show);
    field.put("updateField", this.updateField);
    return field;
  }
  public Map<String, Object> getField(String defaultValue,boolean show) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "autocomplete");
    field.put("options", this.options);
    field.put("defaultValue", defaultValue);
    field.put("show", show);
    field.put("updateField", this.updateField);
    return field;
  }
  public Map<String, Object> getField(boolean show) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "autocomplete");
    field.put("options", this.options);
    field.put("defaultValue", this.defaultValue);
    field.put("show", show);
    field.put("updateField", this.updateField);
    return field;
  }
  public Map<String, Object> getViewField(boolean show) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "autocomplete");
    field.put("options", this.options);
    field.put("defaultValue", this.defaultValue);
    field.put("show", show);
    field.put("updateField", this.updateField);
    field.put("disabled", true);
    return field;
  }
  public Map<String, Object> getViewField() {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "autocomplete");
    field.put("options", this.options);
    field.put("defaultValue", this.defaultValue);
    field.put("show", this.show);
    field.put("updateField", this.updateField);
    field.put("disabled", true);
    return field;
  }
  public Map<String, Object> getAddNewField(String defaultValue,boolean show) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "autocomplete");
    field.put("options", this.options);
    field.put("defaultValue", defaultValue);
    field.put("show", show);
    field.put("updateField", this.updateField);
    field.put("disabled", this.disabled);
    return field;
  }
  public Map<String, Object> getAddNewField(boolean show) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "autocomplete");
    field.put("options", this.options);
    field.put("defaultValue", this.defaultValue);
    field.put("show", show);
    field.put("updateField", this.updateField);
    field.put("disabled", this.disabled);
    return field;
  }
  public Map<String, Object> getAddNewField(String defaultValue) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "autocomplete");
    field.put("options", this.options);
    field.put("defaultValue", defaultValue);
    field.put("show", this.show);
    field.put("updateField", this.updateField);
    field.put("disabled", this.disabled);
    return field;
  }

  public Map<String, Object> getAddNewField() {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "autocomplete");
    field.put("options", this.options);
    field.put("defaultValue", this.defaultValue);
    field.put("show", this.show);
    field.put("updateField", this.updateField);
    field.put("disabled", true);
    return field;
  }
}
