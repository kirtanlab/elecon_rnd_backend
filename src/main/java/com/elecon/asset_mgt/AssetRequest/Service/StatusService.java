package com.elecon.asset_mgt.AssetRequest.Service;


import com.elecon.asset_mgt.AssetRequest.Models.StatusModel;
import com.elecon.asset_mgt.AssetRequest.Repository.StatusModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatusService {

  private static Map<String, Object> createTab (String value, String label, String color) {
    Map<String, Object> column = new HashMap<>();
    column.put("value", value);
    column.put("label", label);
    column.put("color", color);
    return column;
  }
  @Autowired
  private StatusModelRepository statusrepo;
  public List<StatusModel> getAll() {return statusrepo.findAll();};
  public List<Map<String, Object>> getStatus_ColorByIds(List<Integer> ids) {
    List<StatusModel> obj =  statusrepo.findAllById(ids);
    List<Map<String, Object>> tabs = new ArrayList<>();
    for (StatusModel tab : obj){
      Map<String, Object> simplifiedRequest = new HashMap<>();
      simplifiedRequest.put("value",tab.getStatus());
      simplifiedRequest.put("label", tab.getStatus().toLowerCase());
      simplifiedRequest.put("color",tab.getColor().getColor_name().toLowerCase());
      tabs.add(simplifiedRequest);
    }
    return tabs;
  }
}
