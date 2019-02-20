package com.auditservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.auditservice.data.mongo.repo.AuditMongoRepository;
import com.auditservice.dto.AuditRecord;
import com.auditservice.exceptions.AuditServiceException;
import com.auditservice.service.AuditRecordsService;

@Component
@Service
public class AuditRecordsServiceImpl implements AuditRecordsService {

	private static Logger logger = LoggerFactory.getLogger(AuditRecordsServiceImpl.class);

	@Autowired
	AuditMongoRepository auditMongoRepository;
	

	@Override
	public List<AuditRecord> getAllAuditRecordsFromDB() {
		List<AuditRecord> auditRecords = new ArrayList<>();
		try {
			 auditRecords =  auditMongoRepository.findAll();
		} catch(Exception e) {
			e.printStackTrace();
			throw new AuditServiceException("Error occured in getAllAuditRecordsFromDB: " + e);
		}
		return auditRecords;
	}

	@Override
	public AuditRecord getAuditRecordsFromDB(String userId) {
//		JSONObject jsonObj = new JSONObject();
//		try {
//			return auditMongoRepository.findById(userId);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		return null;
	}

	@Override
	public AuditRecord postAuditRecordsToDB(AuditRecord auditRecord) {
			return auditMongoRepository.save(auditRecord);
	}

}
