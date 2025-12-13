package com.i4o.dms.kubota.masters.products.controller;

import com.i4o.dms.kubota.masters.products.dto.ItemSearchDto;
import com.i4o.dms.kubota.masters.products.repository.MachineMasterRepository;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping("api/master/product")
public class ItemMasterController {
    @Autowired
    private MachineMasterRepository machineMasterRepository;
    @Autowired
    private UserAuthentication userAuthentication;
    
    @GetMapping("/machineMaster/autoCompleteItems")
    public ResponseEntity<?> getAutoCompleteItemNo(@RequestParam String itemNo,
                                                   @RequestParam String productGroup,
                                                   @RequestParam String functionality,
                                                   @RequestParam(required = false) String machineType) {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String,Object>> item = machineMasterRepository.getAutoCompleteItemNo(itemNo,productGroup,functionality, machineType);
        apiResponse.setMessage("get item no");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(item);
        return ResponseEntity.ok(apiResponse);
    }

    //To be changed in sales module
    @GetMapping("/getItemNo")
    public ResponseEntity<?> getItemNo(String itemNo) {
        ApiResponse apiResponse = new ApiResponse();
        List<Object> item = machineMasterRepository.getItemNo(itemNo);
        apiResponse.setMessage("get item no");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(item);
        return ResponseEntity.ok(apiResponse);
    }

    //Autocomplete item no. while create
    @GetMapping("/getMachineItemNo")
    public ResponseEntity<?> getMachineItemNo(@RequestParam String itemNo) {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String,Object>> item = machineMasterRepository.getMachineItemNo(itemNo);
        apiResponse.setMessage("get item no");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(item);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/getByItemNo")
    public ResponseEntity<?> getByItemNo(String itemNo, @RequestParam(required=false) String requestFrom, @RequestParam(required=false) String depot) {
        ApiResponse apiResponse = new ApiResponse();
        System.out.println(itemNo+":"+ requestFrom+":"+ depot+":"+ userAuthentication.getBranchId());
        Map<String, Object> item = machineMasterRepository.getByItemNo(itemNo, requestFrom, depot, userAuthentication.getBranchId());
        apiResponse.setMessage("get item details");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(item);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getExchangeBrand")
    public ResponseEntity<?> getExchangeBrand() {
        ApiResponse apiResponse = new ApiResponse();
        List exchangeBrand = machineMasterRepository.getExchangeBrand();
        apiResponse.setMessage("get exchange brand");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(exchangeBrand);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getModel")
    public ResponseEntity<?> getModel(String model) {
        ApiResponse apiResponse = new ApiResponse();
        List model1 = machineMasterRepository.getModel(model);
        apiResponse.setMessage("get model");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(model1);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getSubModel")
    public ResponseEntity<?> getSubModel(String model) {
        ApiResponse apiResponse = new ApiResponse();
        List subModel = machineMasterRepository.getSubModel(model);
        apiResponse.setMessage("get sub model");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(subModel);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getVariant")
    public ResponseEntity<?> getVariant(@RequestParam String model,@RequestParam String subModel) {
        ApiResponse apiResponse = new ApiResponse();
        List submodel = machineMasterRepository.getVariant(model,subModel);
        apiResponse.setMessage("get variant");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(submodel);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getAllModel")
    public ResponseEntity<?> getAllModel() {
        ApiResponse apiResponse = new ApiResponse();
        List model = machineMasterRepository.getAllModel();
        apiResponse.setMessage("get all model");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(model);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getAllSubModel")
    public ResponseEntity<?> getAllSubModel() {
        ApiResponse apiResponse = new ApiResponse();
        List subModel = machineMasterRepository.getAllSubModel();
        apiResponse.setMessage("get all sub model");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(subModel);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getAllVariant")
    public ResponseEntity<?> getAllVariant() {
        ApiResponse apiResponse = new ApiResponse();
        List variant = machineMasterRepository.getAllVariant();
        apiResponse.setMessage("get all variant");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(variant);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProduct() {
        ApiResponse apiResponse = new ApiResponse();
        List product = machineMasterRepository.getAllProduct();
        apiResponse.setMessage("get all product");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(product);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getProductByGroup")
    public ResponseEntity<?> getProductByGroup(@RequestParam("prodGroup")String prodGroup) {
        ApiResponse apiResponse = new ApiResponse();
        List product = machineMasterRepository.getProductByGroup(prodGroup);
        apiResponse.setMessage("get product by group");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(product);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getProduct")
    public ResponseEntity<?> getProduct() {
        ApiResponse apiResponse = new ApiResponse();
        List product = machineMasterRepository.getProduct();
        apiResponse.setMessage("get all product");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(product);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getAllSeries")
    public ResponseEntity<?> getAllSeries() {
        ApiResponse apiResponse = new ApiResponse();
        List series = machineMasterRepository.getAllSeries();
        apiResponse.setMessage("get all product");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(series);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getVariantByModel")
    public ResponseEntity<?> getVariantByModel(@RequestParam String model) {
        ApiResponse apiResponse = new ApiResponse();
        List variant = machineMasterRepository.getVariantByModel(model);
        apiResponse.setMessage("get variant based on model");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(variant);
        return ResponseEntity.ok(apiResponse);

    }


    @GetMapping("/modelDropdown")
    public ResponseEntity<?> modelDropdown(@RequestParam(required = false) String series)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> model = machineMasterRepository.getModelBySeriesId(series);
        apiResponse.setMessage("Data get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(model);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/subModelDropdown")
    public ResponseEntity<?> sunModelDropdown(@RequestParam(required = false) String model)
    {
        ApiResponse apiResponse= new ApiResponse();
        List<Map<String,Object>> subModel = machineMasterRepository.getSubModelByModelId(model);
        apiResponse.setMessage("Data Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(subModel);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/variantDropdown")
    public ResponseEntity<?> variantDropdown(@RequestParam(required = false) String subModel)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> variant = machineMasterRepository.getVariantBySubModel(subModel);
        apiResponse.setMessage("Data Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(variant);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("seriesDropdown")
    public ResponseEntity<?> seriesDropdown(@RequestParam(required = false) String product ) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get enquiry list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineMasterRepository.getSeriesByProductId(product));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("getSeriesAndProductByModel")
    public ResponseEntity<?> getSeriesAndProduct(@RequestParam(required = false) String model ) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get  series and product by model");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineMasterRepository.getSeriesAndProduct(model));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("getItemByModelSubModelVariant")
    public ResponseEntity<?> getItemByModelSubModelVariant(@RequestParam(required = false) String model,String subModel,String variant) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get item number by model submodel variant");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineMasterRepository.getItemByModelSubModelVariant(model,subModel,variant));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getSeriesByProduct")
    public ResponseEntity<?> getSeriesByProduct(@RequestParam(required = true) String product) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get series by model");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineMasterRepository.getSeriesByProduct(product));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getModelBySeries")
    public ResponseEntity<?> getModelBySeries(@RequestParam(required = true) String series) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get series by model");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineMasterRepository.getModelBySeries(series));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("getItemNumberModelProductSeries")
    public ResponseEntity<?> getItemNumberModelProductSeries(@RequestParam(required = true) String search) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get item number model,product series");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineMasterRepository.getItemNumberModelProductSeries(search));

        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping("/searchItem")
    public ResponseEntity<?> searchItem(@RequestBody ItemSearchDto itemSearchDto){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get items");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineMasterRepository.searchItem(itemSearchDto.getItemNo(),
                itemSearchDto.getModel(),itemSearchDto.getProduct(),
                itemSearchDto.getSeries(),itemSearchDto.getSubModel(),itemSearchDto.getVariant(),itemSearchDto.getPage(),itemSearchDto.getSize()));
        apiResponse.setCount(machineMasterRepository.searchItemCount(itemSearchDto.getItemNo(),
                itemSearchDto.getModel(),itemSearchDto.getProduct(),
                itemSearchDto.getSeries(),itemSearchDto.getSubModel(),itemSearchDto.getVariant(),itemSearchDto.getPage(),itemSearchDto.getSize()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/itemNoAuto")
    public ResponseEntity<?>itemNooAuto(@RequestParam("itemNo") String itemNo)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> itemNumber=machineMasterRepository.findByItemNoContaining(itemNo);
        apiResponse.setMessage("Item No get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(itemNumber);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getVariantBySubModel")
    public ResponseEntity<?>getVariantBySubModel(@RequestParam("subModel") String subModel)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> variant=machineMasterRepository.getVariantBySubModels(subModel);
        apiResponse.setMessage("get variant by sub model");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(variant);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getSeries")
    public ResponseEntity<?>getSeries()
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> series=machineMasterRepository.getSeries();
        apiResponse.setMessage("Series get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(series);
        return ResponseEntity.ok(apiResponse);
    }
}
