package com.modernapp.services.dto;

import java.util.List;

public class VehicleInfoInitDataDTO {
    private List<DropdownOptionDTO> jobTypes;
    private List<DropdownOptionDTO> locations;
    private List<DropdownOptionDTO> serviceTypes;
    private List<DropdownOptionDTO> productCategories;

    public List<DropdownOptionDTO> getJobTypes() { return jobTypes; }
    public void setJobTypes(List<DropdownOptionDTO> jobTypes) { this.jobTypes = jobTypes; }

    public List<DropdownOptionDTO> getLocations() { return locations; }
    public void setLocations(List<DropdownOptionDTO> locations) { this.locations = locations; }

    public List<DropdownOptionDTO> getServiceTypes() { return serviceTypes; }
    public void setServiceTypes(List<DropdownOptionDTO> serviceTypes) { this.serviceTypes = serviceTypes; }

    public List<DropdownOptionDTO> getProductCategories() { return productCategories; }
    public void setProductCategories(List<DropdownOptionDTO> productCategories) { this.productCategories = productCategories; }
}
