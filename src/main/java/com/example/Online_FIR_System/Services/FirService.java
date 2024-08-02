package com.example.Online_FIR_System.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online_FIR_System.Model.ComplaintStatus;
import com.example.Online_FIR_System.Model.FIR;
import com.example.Online_FIR_System.Repository.FirRepository;

@Service
public class FirService {
	
	@Autowired
	private FirRepository FirRepo;
	
	@Autowired
    private ComplaintStatusService complaintStatusService;
	
	public FIR SaveFir(FIR fir){
		return FirRepo.save(fir);
	}
	
	public List<FIR> getAllFIR(){
		return FirRepo.findAll();
	}
	
	public void acceptComplaint(Long complaintId) {
        FIR complaint = FirRepo.findById(complaintId).orElseThrow(() -> new IllegalArgumentException("Invalid complaint ID"));
        complaint.setStatus("Accepted");
        FirRepo.save(complaint);
    }

    public void rejectComplaint(Long complaintId) {
        FIR complaint = FirRepo.findById(complaintId).orElseThrow(() -> new IllegalArgumentException("Invalid complaint ID"));
        complaint.setStatus("Rejected");
        FirRepo.save(complaint);
    }

    public Optional<FIR> findById(Long id) {
        return FirRepo.findById(id);
    }
    public void deleteById(Long id) {
        FirRepo.deleteById(id); // Delete the complaint by ID
    }
    
    	public List<FIR> findByPoliceStation(String policeStation) {
        return FirRepo.findByPoliceStation(policeStation);
    }
    	
    	public void updateFirStatus(Long complaintId, String status) {
            Optional<ComplaintStatus> complaintStatus = Optional.ofNullable(complaintStatusService.findByComplaintId(complaintId));
            if (complaintStatus.isPresent()) {
                ComplaintStatus cs = complaintStatus.get();
                cs.setStatus(status);
                complaintStatusService.saveComplaintStatus(cs);
            }
        }

		public void arrestedCulprit(Long complaintId) {
			// TODO Auto-generated method stub
			FIR complaint = FirRepo.findById(complaintId).orElseThrow(() -> new IllegalArgumentException("Invalid complaint ID"));
	        complaint.setStatus("Culprit Arrested");
	        FirRepo.save(complaint);
			
		}
}
