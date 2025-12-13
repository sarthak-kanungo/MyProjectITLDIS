package com.i4o.dms.kubota.training.attendanceSheet.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.kubota.storage.StorageService;
import com.i4o.dms.kubota.training.attendanceSheet.dto.TrainingAttendanceSheetDoc;
import com.i4o.dms.kubota.training.attendanceSheet.dto.TrainingAttendanceSheetDto;
import com.i4o.dms.kubota.training.attendanceSheet.repo.TrainingAttendanceSheetDocRepo;
import com.i4o.dms.kubota.training.attendanceSheet.repo.TrainingAttendanceSheetRepo;

/**
 * @author suraj.gaur
 * @apiNote Create this service because transaction can't works in try-catch block that is why for  
 * working @Transactional correctly I have created service and then added all logics here.
 */
@Service
public class AttendenceServiceImpl implements AttendenceService {
	
//	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
    @Autowired
    private StorageService storageService;

	@Autowired
	private TrainingAttendanceSheetRepo tasRepo;
	
    @Autowired
    private TrainingAttendanceSheetDocRepo docRepo;
	
	@Transactional
	public void updateAttendence(TrainingAttendanceSheetDto tnAttendanceSheetUpdate) {
		
		//deletes the old attendance for that program no. and then save new attendance.
		tasRepo.deleteOldAttendance(tnAttendanceSheetUpdate.getProgramId());
		
		//Saving again the new updated attendance
		tnAttendanceSheetUpdate.getAttendanceDtl().forEach(attendancDtl -> {
			if(attendancDtl.getTrainingDate() != null && attendancDtl.getProgramNominationDtlId() != null) {
				tasRepo.save(attendancDtl);
			}
		});
	
		//updating index provided by HO.
		final float avgGrowthIndex = tnAttendanceSheetUpdate.getAvgGrowthIndex();			
		tnAttendanceSheetUpdate.getNominationEmpIndexs().forEach(nominationIndes -> {
			if(nominationIndes.getProgramNominationDtlId() != null) {
				tasRepo.updateIndexesValuesForEmployees(tnAttendanceSheetUpdate.getProgramId(), nominationIndes.getProgramNominationDtlId(), 
						nominationIndes.getPreTest(), nominationIndes.getPostTest(), 
						nominationIndes.getGrowthIndex(), avgGrowthIndex);
			}
		});
		
		//----------------------------------------
//		tnAttendanceSheetUpdate.getAttendanceDtl().forEach(attendancDtl -> {
//			if (attendancDtl.getTrainingDate() != null && id != attendancDtl.getProgramNominationDtlId()) {
//				List<NomineAttendanceSheet> tasUpdate = tasRepo
//						.findByProgramNominationDtlId(attendancDtl.getProgramNominationDtlId());
//				tasUpdate.forEach(up -> {
//					attendancDtl.setNomineeAttendanceDtlId(up.getNomineeAttendanceDtlId());
//					// System.out.println("vinayyy--"+up.getNomineeAttendanceDtlId());
//				});
//			}
//			id = attendancDtl.getProgramNominationDtlId();
//		});
//		tnAttendanceSheetUpdate.getAttendanceDtl().forEach(dtl -> {
//			if (dtl.getTrainingDate() != null) {
//				// System.out.println("vinayyy--"+dtl);
//				tasRepo.save(dtl);
//			}
//
//		});
		//----------------------------------------
		
		//updating the trainer1 and trainer2.
		if ((tnAttendanceSheetUpdate.getTrainer1() != null || tnAttendanceSheetUpdate.getTrainer2() != null ) && tnAttendanceSheetUpdate.getProgramId() != null) {
			tasRepo.updateTrainerInTRProgHrd(tnAttendanceSheetUpdate.getTrainer1(), tnAttendanceSheetUpdate.getTrainer2(), tnAttendanceSheetUpdate.getProgramId());
		}
	}

	@Override
	@Transactional
	public void nomineAttendanceSheetSave(TrainingAttendanceSheetDto tnAttendanceSheet,
			List<MultipartFile> tnAttendanceSheetDocs) 
	{		
		//Saving again the new updated attendance
		tnAttendanceSheet.getAttendanceDtl().forEach(attendancDtl ->{
			 tasRepo.save(attendancDtl);
		});
		
		// updating index provided by HO.
		final float avgGrowthIndex = tnAttendanceSheet.getAvgGrowthIndex();
		tnAttendanceSheet.getNominationEmpIndexs().forEach(nominationIndes -> {
			if (nominationIndes.getProgramNominationDtlId() != null) {
				tasRepo.updateIndexesValuesForEmployees(tnAttendanceSheet.getProgramId(),
						nominationIndes.getProgramNominationDtlId(), nominationIndes.getPreTest(),
						nominationIndes.getPostTest(), nominationIndes.getGrowthIndex(), avgGrowthIndex);
			}
		});
		
		//updating the trainer1 and trainer2.
		if ((tnAttendanceSheet.getTrainer1() !=null || tnAttendanceSheet.getTrainer2() !=null ) && tnAttendanceSheet.getProgramId() !=null) {
			tasRepo.updateTrainerInTRProgHrd(tnAttendanceSheet.getTrainer1(), tnAttendanceSheet.getTrainer2(), tnAttendanceSheet.getProgramId());
		}
		
		//Saving Image files and storing it to server.
		if (tnAttendanceSheetDocs.size() <= 5 && !tnAttendanceSheetDocs.isEmpty()) {
			tnAttendanceSheetDocs.forEach(m -> {
				TrainingAttendanceSheetDoc tasDoc = new TrainingAttendanceSheetDoc();
				String fileName = m.getOriginalFilename();
				String tasDocName = "TAS" + System.currentTimeMillis() + "_" + fileName;
				storageService.store(m, tasDocName);
				tasDoc.setProgramId(tnAttendanceSheet.getProgramId());
				tasDoc.setDocumentName(tasDocName);
				tasDoc.setDocumentPath(tasDocName);
				tasDoc.setCreatedDate(new Date());
				docRepo.save(tasDoc);
			});
		}
	}
}
