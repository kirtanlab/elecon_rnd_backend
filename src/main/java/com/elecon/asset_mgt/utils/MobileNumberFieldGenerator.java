package com.elecon.asset_mgt.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class MobileNumberFieldGenerator {
  private String name;
  private String label;
  private boolean required;
  private String defaultValue;
  private boolean show;
  private boolean disabled;

  public MobileNumberFieldGenerator(String name, String label, boolean required, String defaultValue, boolean show) {
    this.name = name;
    this.label = label;
    this.required = required;
    this.defaultValue = defaultValue;
    this.show = show;
  }

  public MobileNumberFieldGenerator(String name, String label, boolean required, String defaultValue, boolean show,boolean disabled) {
    this.name = name;
    this.label = label;
    this.required = required;
    this.defaultValue = defaultValue;
    this.show = show;
    this.disabled = disabled;
  }

  public Map<String, Object> getField() {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "phone_no");
    field.put("defaultValue", this.defaultValue);
    field.put("show", this.show);
    return field;
  }
  public Map<String, Object> getField(String defaultValue) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "phone_no");
    field.put("defaultValue", defaultValue);
    field.put("show", this.show);
    return field;
  }
  public Map<String, Object> getField(String defaultValue,boolean show) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "phone_no");
    field.put("defaultValue", defaultValue);
    field.put("show", show);
    return field;
  }
  public Map<String, Object> getField(boolean show) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "phone_no");
    field.put("defaultValue", defaultValue);
    field.put("show", show);
    return field;
  }
  public Map<String, Object> getViewField(boolean show) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "phone_no");
    field.put("defaultValue", this.defaultValue);
    field.put("show", show);
    field.put("disabled",true);
    return field;
  }
  public Map<String, Object> getViewField() {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "phone_no");
    field.put("defaultValue", this.defaultValue);
    field.put("show", this.show);
    field.put("disabled",true);
    return field;
  }
  public Map<String, Object> getAddNewField(boolean show) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "phone_no");
    field.put("defaultValue", null);
    field.put("show", show);
    field.put("disabled", this.disabled);
    return field;
  }
  public Map<String, Object> getAddNewField(String defaultValue) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "phone_no");
    field.put("defaultValue", defaultValue);
    field.put("show", this.show);
    field.put("disabled", this.disabled);
    return field;
  }
  public Map<String, Object> getAddNewField(String defaultValue,boolean show) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "phone_no");
    field.put("defaultValue", defaultValue);
    field.put("show", show);
    field.put("disabled", this.disabled);
    return field;
  }
  public Map<String, Object> getAddNewField() {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("label", this.label);
    field.put("required", this.required);
    field.put("type", "phone_no");
    field.put("defaultValue", null);
    field.put("show", this.show);
    field.put("disabled", this.disabled);
    return field;
  }
}
