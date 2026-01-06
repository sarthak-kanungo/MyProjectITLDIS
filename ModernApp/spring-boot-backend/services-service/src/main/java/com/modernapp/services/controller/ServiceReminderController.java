package com.modernapp.services.controller;

import com.modernapp.services.dto.ServiceReminderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/services/reminders")
@CrossOrigin(origins = "http://localhost:4200")
public class ServiceReminderController {

    @GetMapping
    public ResponseEntity<List<ServiceReminderDTO>> getServiceReminders(
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) String jobType,
            @RequestParam(required = false) String dealerCode) {

        List<ServiceReminderDTO> list = new ArrayList<>();
        
        // Mock Data Generation
        Calendar cal = Calendar.getInstance();
        
        // Item 1: Upcoming
        cal.add(Calendar.DAY_OF_YEAR, 5);
        list.add(new ServiceReminderDTO("SCH-1001", "VIN1234567890", "M5000", "Free Service - 1", "John Doe", "9876543210", cal.getTime(), "PENDING", null, "DLR001", "Agri Motors"));

        // Item 2: Overdue
        cal.add(Calendar.DAY_OF_YEAR, -10);
        list.add(new ServiceReminderDTO("SCH-1002", "VIN0987654321", "M5500", "Paid Service / Repair", "Jane Smith", "9123456789", cal.getTime(), "PROMISED", cal.getTime(), "DLR001", "Agri Motors"));

        // Item 3: Far Future
        cal.add(Calendar.DAY_OF_YEAR, 20);
        list.add(new ServiceReminderDTO("SCH-1003", "VIN1122334455", "M6000", "Free Service - 2", "Bob Farmer", "9988776655", cal.getTime(), "DECLINED", cal.getTime(), "DLR002", "Tractor Hub"));

        return ResponseEntity.ok(list);
    }
}
