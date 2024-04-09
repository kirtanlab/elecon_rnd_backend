package com.elecon.asset_mgt.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ImageFieldGenerator {
  private String name;
  private boolean show;
  private boolean disabled;

  public ImageFieldGenerator(String name, boolean show, boolean disabled) {
    this.name = name;
    this.show = show;
    this.disabled = disabled;
  }
  public ImageFieldGenerator(String name, boolean show) {
    this.name = name;
    this.show = show;
  }

  public Map<String, Object> getField() { //for edit form
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("type", "image");
    field.put("show", this.show);
    return field;
  }
  public Map<String, Object> getViewField(boolean show) { //for edit form
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("type", "image");
    field.put("show", show);
    field.put("disabled", disabled);
    return field;
  }
  public Map<String, Object> getViewField() { //for edit form
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("type", "image");
    field.put("show", this.show);
    field.put("disabled", disabled);
    return field;
  }
  public Map<String, Object> getField(boolean show) {
    Map<String, Object> field = new HashMap<>();
    field.put("name", this.name);
    field.put("type", "image");
    field.put("show", show);
    return field;
  }
//  public Map<String, Object> getField(String defaultValue) {
//    Map<String, Object> field = new HashMap<>();
//    field.put("name", this.name);
//    field.put("type", "image");
//    field.put("show", this.show);
//    return field;
//  }
//  public Map<String, Object> getField(String defaultValue,boolean show) {
//    Map<String, Object> field = new HashMap<>();
//    field.put("name", this.name);
//    field.put("type", "image");
//    field.put("show", this.show);
//    return field;
//  }


}
