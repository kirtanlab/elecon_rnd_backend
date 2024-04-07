package com.elecon.asset_mgt.AssetRequest.Service;

import com.elecon.asset_mgt.AssetRequest.DAO.CreateAssetRequestDao;
import com.elecon.asset_mgt.AssetRequest.Models.AssetRequestModel;
import com.elecon.asset_mgt.AssetRequest.Models.StatusModel;
import com.elecon.asset_mgt.AssetRequest.Repository.AssetRequestRepository;
import com.elecon.asset_mgt.AssetRequest.Repository.StatusModelRepository;
import com.elecon.asset_mgt.Category.Models.CategoryModel;
import com.elecon.asset_mgt.Category.Repository.CategoryRepo;
import com.elecon.asset_mgt.Classification.Models.ClassificationModel;
import com.elecon.asset_mgt.Classification.Repository.ClassificationRepo;
import com.elecon.asset_mgt.Employee.Models.EmployeeModel;
import com.elecon.asset_mgt.Employee.Repository.EmployeeRepo;
import com.elecon.asset_mgt.Type.Models.TypeModel;
import com.elecon.asset_mgt.Type.Repository.TypeRepo;
import com.elecon.asset_mgt.location.Models.LocationModel;
import com.elecon.asset_mgt.location.Repository.LocationRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
public class AssetRequestService {

    @Autowired private AssetRequestRepository repo;
    @Autowired private StatusModelRepository statusrepo;
    @Autowired private ClassificationRepo classrepo;
    @Autowired private LocationRepo locationrepo;
    @Autowired private CategoryRepo catrepo;
    @Autowired private TypeRepo typerepo;
    @Autowired private EmployeeRepo employeerepo;

    public List<AssetRequestModel> getAll() {return repo.findAll();};

  public boolean approveAssetRequest(Integer requestId, Integer statusId) {
    Optional<AssetRequestModel> optionalAssetRequest = repo.findById(requestId);

    if (optionalAssetRequest.isPresent()) {
      AssetRequestModel assetRequest = optionalAssetRequest.get();

      // Update the status to 'APPROVED' (assuming 'StatusModel' has an ID for 'APPROVED')
      assetRequest.getStatus().setId(statusId);

      // Optionally, update 'updated_at' field to reflect the latest update time
      assetRequest.setUpdated_at(new Date());

      // Save the updated AssetRequestModel
      repo.save(assetRequest);

      return true;
    }

    return false;
  }

    public List<String> save(CreateAssetRequestDao assetreqdao,Principal principal){
        AssetRequestModel assetreqmodel = new AssetRequestModel();
        List<String> actionresponse = new ArrayList<>();
        Date currentDate = new Date(System.currentTimeMillis());

        String employeeCodejwt = principal.getName();
        System.out.println(employeeCodejwt);
        EmployeeModel employee = employeerepo.findByEmployeeCode(employeeCodejwt);

        //check if employee then pending else if manager than approved
        if(Objects.equals(employee.getRole(), "employee"))
        {
            StatusModel status = statusrepo.findByStatus("pending");
            System.out.println(status);
            assetreqmodel.setStatus(status);
            System.out.println("he is employee");
            actionresponse.add("RollBack");
        }
        if(Objects.equals(employee.getRole(), "manager"))
        {
            StatusModel status = statusrepo.findByStatus("approve");
            System.out.println(status);
            assetreqmodel.setStatus(status);
            System.out.println("he is manager");
            actionresponse.add("no action to be performed");
        }

        ClassificationModel classification = classrepo.findById(assetreqdao.getClassification_id()).orElseThrow(() -> new IllegalArgumentException("ID classification doesn't exist"));
        LocationModel location =  locationrepo.findById(assetreqdao.getLocation_id()).orElseThrow(() -> new IllegalArgumentException("Invalid location ID"));
        CategoryModel category = catrepo.findById(assetreqdao.getCategory_id()).orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        TypeModel type = typerepo.findById(assetreqdao.getAsset_type_id()).orElseThrow(() -> new IllegalArgumentException("Invalid type ID"));
        EmployeeModel reporting_to_id = employeerepo.findById(assetreqdao.getReporting_to_id()).orElseThrow(() -> new IllegalArgumentException("Invalid reporting_to ID"));

        assetreqmodel.setClassification_id(classification);
        assetreqmodel.setLocation_id(location);
        assetreqmodel.setCategory_id(category);
        assetreqmodel.setAsset_type_id(type);
        assetreqmodel.setEmployee(employee);
        assetreqmodel.setReporting_to_id(reporting_to_id);

        //set date from server side
        assetreqmodel.setUpdated_at(currentDate);
        BeanUtils.copyProperties(assetreqdao, assetreqmodel);

        repo.save(assetreqmodel);
        return actionresponse;
    }
  public Optional<AssetRequestModel> findById(Integer id) {
    return repo.findById(id);
  }

    public void deletebyID(Integer assetreqID,Principal principal){
        // check employeeId in database with employeeId in jwt
        repo.deleteById(assetreqID);
    }
  public void deleteSelected(List<Integer> ids) {
    List<AssetRequestModel> assetrequestsToDelete = repo.findAllById(ids);
    for (AssetRequestModel assetrequests : assetrequestsToDelete) {
      if (assetrequests != null) {
        repo.delete(assetrequests);
      }
    }

  } public void updateAssetRequests(AssetRequestModel updatedAssetRequest) {
    Integer assetRequID = updatedAssetRequest.getId();
    AssetRequestModel exisitingAssetRequest = repo.findById(assetRequID)
      .orElseThrow(() -> new AssetRequestNotFoundException("Asset Request not found with Id: " + assetRequID));
    System.out.println("updateAssetRequests Status ID: "+updatedAssetRequest.getClassification_id());

    exisitingAssetRequest.setAsset_type_id(updatedAssetRequest.getAsset_type_id());
    exisitingAssetRequest.setRequired_by(updatedAssetRequest.getRequired_by());
    exisitingAssetRequest.setDetails(updatedAssetRequest.getDetails());
    exisitingAssetRequest.setReason(updatedAssetRequest.getReason());
    exisitingAssetRequest.setStatus(updatedAssetRequest.getStatus());
    exisitingAssetRequest.setClassification_id(updatedAssetRequest.getClassification_id());
    exisitingAssetRequest.setCategory_id(updatedAssetRequest.getCategory_id());
    exisitingAssetRequest.setLocation_id(updatedAssetRequest.getLocation_id());
    exisitingAssetRequest.setEmployee(updatedAssetRequest.getEmployee());
    exisitingAssetRequest.setReporting_to_id(updatedAssetRequest.getReporting_to_id());
    exisitingAssetRequest.setAllocated_asset_id(updatedAssetRequest.getAllocated_asset_id());
    exisitingAssetRequest.setUpdated_at(new Date());
    repo.save(exisitingAssetRequest);
  }
  public Optional<AssetRequestModel> updateAssetRequestToPullBack(Integer assetRequestsID, Principal principal) {
    String employeeCodejwt = principal.getName();
    EmployeeModel employee = employeerepo.findByEmployeeCode(employeeCodejwt);
    System.out.println("employee" + employee);
    Optional<AssetRequestModel> assetRequestOptional = findById(assetRequestsID);
    System.out.println("asset request" + assetRequestOptional);
    assetRequestOptional.ifPresent(assetRequest -> {
      // Retrieve the status with status 'PullBack'
      StatusModel pullBackStatus = statusrepo.findByStatus("PullBack");
      System.out.println("pullBackStatus" + pullBackStatus);
      if (pullBackStatus != null) {
        // Set the 'StatusModel' associated with 'AssetRequestModel'
        assetRequest.setStatus(pullBackStatus);

        // Set updated_at field to current timestamp
        assetRequest.setUpdated_at(new Date());

        // Save the updated 'AssetRequestModel' back to the database
        repo.save(assetRequest);
      } else {
        System.out.println("PullBack status not found");
        // Handle the case when 'PullBack' status is not found
      }
    });
    return assetRequestOptional;
  }
  public  Optional<AssetRequestModel> updateAssetRequestToApproved(Integer assetRequestsID, Principal principal) {
    String employeeCodejwt = principal.getName();
    EmployeeModel employee = employeerepo.findByEmployeeCode(employeeCodejwt);
    Optional<AssetRequestModel> assestrequest = findById(assetRequestsID);

    assestrequest.ifPresent(assetRequest -> {
      StatusModel approvedStatus = statusrepo.findByStatus("Approved");
      if (approvedStatus != null) {
        assetRequest.setStatus(approvedStatus);
        assetRequest.setUpdated_at(new Date());
        repo.save(assetRequest);
      } else {
        return;
      }
    });
    return  assestrequest;
  }
}
