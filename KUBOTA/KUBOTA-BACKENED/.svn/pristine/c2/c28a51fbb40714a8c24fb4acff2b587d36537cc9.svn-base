//package com.i4o.dms.kubota.service.activityproposal.service.serviceactivityproposalimpl;
//
//        import com.i4o.dms.kubota.service.activityproposal.dto.ServiceActivityCalculateActivityTypeDto;
//        import com.i4o.dms.kubota.service.activityproposal.repository.ServiceActivityProposalRepo;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.stereotype.Service;
//
//        import javax.transaction.Transactional;
//
//@Service
//public class ServiceActivityProposalImpl implements ServiceActivityProposalService
//{
//
//    @Autowired
//    private ServiceActivityProposalRepo serviceActivityProposalRepo;
//
//    @Transactional
//    @Override
//    public double calculationForActivityType(ServiceActivityCalculateActivityTypeDto serviceActivityCalculateActivityTypeDto)
//    {
//        Double maxBudget = serviceActivityProposalRepo.getMAxAllowedBudget(serviceActivityCalculateActivityTypeDto.getActivityType());
//        Double totalExpenditure = ((serviceActivityCalculateActivityTypeDto.getProposedBudget() * 50) / 100);
//        double result = 0;
//        if (serviceActivityCalculateActivityTypeDto.getActivityType().equals("Service Camp")) {
//            Integer totalMachineCost = (serviceActivityCalculateActivityTypeDto.getTargetedNumbers() * 500);
//            if (maxBudget <= totalExpenditure && maxBudget <= totalMachineCost) {
//                result = maxBudget;
//            } else if (totalExpenditure <= totalMachineCost && totalExpenditure <= maxBudget) {
//                result = totalExpenditure;
//            } else {
//                result = totalMachineCost;
//            }
//        }
//        else if (serviceActivityCalculateActivityTypeDto.getActivityType().equals("Campaign")){
//            if (maxBudget<totalExpenditure)result=maxBudget;
//            else result=totalExpenditure;
//        }
//        else if (serviceActivityCalculateActivityTypeDto.getActivityType().equals("5 in 1 Camp")) {
//            Integer totalMachineCost = (serviceActivityCalculateActivityTypeDto.getTargetedNumbers() * 500);
//            if (maxBudget <= totalExpenditure && maxBudget <= totalMachineCost) {
//                result = maxBudget + serviceActivityCalculateActivityTypeDto.getTotalSubActivity();
//            } else if (totalExpenditure <= totalMachineCost && totalExpenditure <= maxBudget) {
//                result = totalExpenditure + serviceActivityCalculateActivityTypeDto.getTotalSubActivity();
//            } else {
//                result = totalMachineCost + serviceActivityCalculateActivityTypeDto.getTotalSubActivity();
//            }
//        }
//        else if (serviceActivityCalculateActivityTypeDto.getActivityType().equals("USS")){
//            if (serviceActivityProposalRepo.getMAxAllowedBudgetByPerson(serviceActivityCalculateActivityTypeDto.getTargetedNumbers())!=null)
//                result=serviceActivityProposalRepo.getMAxAllowedBudgetByPerson(serviceActivityCalculateActivityTypeDto.getTargetedNumbers());
//        }
//
//        return result;
//    }
//
//
//}
