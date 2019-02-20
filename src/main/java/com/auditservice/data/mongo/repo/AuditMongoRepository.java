package com.auditservice.data.mongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.auditservice.dto.AuditRecord;

@Repository
public interface AuditMongoRepository extends MongoRepository<AuditRecord, String> {
}
