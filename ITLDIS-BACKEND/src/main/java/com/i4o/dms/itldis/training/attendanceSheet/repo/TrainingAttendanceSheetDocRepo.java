package com.i4o.dms.itldis.training.attendanceSheet.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i4o.dms.itldis.training.attendanceSheet.dto.TrainingAttendanceSheetDoc;

public interface TrainingAttendanceSheetDocRepo extends JpaRepository<TrainingAttendanceSheetDoc, Long> {

}
