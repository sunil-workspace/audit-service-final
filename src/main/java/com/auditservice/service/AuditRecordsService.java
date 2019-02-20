package com.auditservice.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.auditservice.dto.AuditRecord;

@Service
public interface AuditRecordsService {

	public List<AuditRecord> getAllAuditRecordsFromDB();
	public AuditRecord getAuditRecordsFromDB(String userId);
	public AuditRecord postAuditRecordsToDB(AuditRecord auditRecord);
}
