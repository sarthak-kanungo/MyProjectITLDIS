package com.i4o.dms.kubota.training.attendanceSheet.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.kubota.training.attendanceSheet.dto.TrainingAttendanceSheetDto;

@Transactional
public interface AttendenceService {
	
	void updateAttendence(TrainingAttendanceSheetDto tnAttendanceSheetUpdate);
	
	void nomineAttendanceSheetSave(TrainingAttendanceSheetDto tnAttendanceSheet , List<MultipartFile> tnAttendanceSheetDocs);

}
