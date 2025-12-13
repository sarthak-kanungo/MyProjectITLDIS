package com.i4o.dms.kubota.salesandpresales.branchtansfer.controller;


import com.i4o.dms.kubota.salesandpresales.branchtansfer.domain.BranchTransferIssue;
import com.i4o.dms.kubota.salesandpresales.branchtansfer.repository.BranchTransferIssueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.i4o.dms.kubota.configurations.Constants.*;

@RestController
@ResponseBody
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/branchTransferIssue")
public class BranchTransferIssueController {

    @Autowired
    private BranchTransferIssueRepo branchTransferIssueRepo;

    private Map<String, Object> map = new HashMap<>();


    @PostMapping
    public ResponseEntity<?> saveBranchTransferIssue(@RequestBody BranchTransferIssue branchTransferIssue) {
        map.clear();
        map.put(MESSAGE, "Branch Transfer Issue saved successfully");
        map.put(RESPONSE, branchTransferIssueRepo.save(branchTransferIssue));
        map.put(STATUS, SUCCESS);
        return ResponseEntity.ok(map);
    }
}
