package com.auditservice.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auditservice.dto.AuditRecord;
import com.auditservice.service.impl.AuditRecordsServiceImpl;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuditServiceController {

	private static Logger logger = LoggerFactory.getLogger(AuditServiceController.class);

	@Autowired
	AuditRecordsServiceImpl auditRecordsServiceImpl;

	@RequestMapping(method=RequestMethod.GET, value="/audits" )
	public ResponseEntity<?> getAllAuditRecords(){
		/*public ResponseEntity<?> getAllAuditRecords(@RequestHeader(value="jwt") String jwt, @RequestParam("userId") String userId, @RequestParam("apiPath") String apiPath,
			@RequestParam("apiMethod") String apiMethod, @RequestParam("apiCallTimeFrom") String apiCallTimeFrom, @RequestParam("apiCallTimeTo") String apiCallTimeTo,
			@RequestParam("apiResponseCode") String apiResponseCode) {*/
		List<AuditRecord> auditRecords = new ArrayList<>();
		HttpHeaders headers = new HttpHeaders();
		try {
			auditRecords = auditRecordsServiceImpl.getAllAuditRecordsFromDB();
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), headers, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(auditRecords, headers, HttpStatus.OK);
	}


	@RequestMapping(method=RequestMethod.GET, value="/audits/{userId}" )
	//public ResponseEntity<String> empiSearchByLocalId(@RequestParam("localId") String localId, @RequestParam("systemCode") String systemCode) {
	public ResponseEntity<?> getAuditRecords(@PathVariable("userId") String userId){
		JSONObject jsonObj = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		try {
			//Record record = auditRecordsServiceImpl.getAuditRecordsFromDB(userId);
		} catch(Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(jsonObj.toString(), headers, HttpStatus.OK);
	}


	@RequestMapping(method=RequestMethod.POST, value="/audits")
	public ResponseEntity<?> postAuditRecords(@RequestBody AuditRecord record, @RequestHeader("Authorization") String jwtToken){
		JSONObject json = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		
		

		//String jwtToken = Jwts.builder().setSubject("sunil").claim("roles", "user").setIssuedAt(new Date())
		//				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

		String userName = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(jwtToken).getBody().get("sub", String.class);
		//JwtParser parsedJwt = Jwts.parser().setSigningKey("secretkey");
		System.out.println("jwt body after parsing"+Jwts.parser().setSigningKey("secretkey").parseClaimsJws(jwtToken).getBody());
		System.out.println(userName);
		//Jwt finalToken = parsedJwt.parse(jwt);
		//String  tokenBody = finalToken.getBody().toString();
		//tokenBody.get("name", String.class);
		if(userName.equals(record.getUserId())) {
			try {
				record = auditRecordsServiceImpl.postAuditRecordsToDB(record);
				if (record.getUserId() != null) {
					json.put("success", "Record has been Created for User " + record.getUserId());
					return new ResponseEntity<>(json.toString(), headers, HttpStatus.CREATED);
				} else {
					json.put("Not Successful ", "Issue creating Record for User " + record.getUserId());
					return new ResponseEntity<>(json.toString(),headers,  HttpStatus.CONFLICT);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try{
				json.put("Unauthorized User ", "Record not inserted to DB ");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>(json.toString(), headers,  HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(json.toString(), headers,  HttpStatus.CREATED);
	}

	//@RequestMapping(method=RequestMethod.POST, value="/audits1", consumes= {"application/json", "application/xml"}, produces="application/json")
	@RequestMapping(method=RequestMethod.POST, value="/audits1")
	public ResponseEntity<?> postAuditRecords1(@RequestBody AuditRecord record){
		HttpHeaders headers = new HttpHeaders();
		//record = auditRecordsServiceImpl.postAuditRecordsToDB(record);
		return new ResponseEntity<>(record ,headers,  HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/audits11" )
	public ResponseEntity<?> getAllAuditRecords1(){
		/*public ResponseEntity<?> getAllAuditRecords(@RequestHeader(value="jwt") String jwt, @RequestParam("userId") String userId, @RequestParam("apiPath") String apiPath,
			@RequestParam("apiMethod") String apiMethod, @RequestParam("apiCallTimeFrom") String apiCallTimeFrom, @RequestParam("apiCallTimeTo") String apiCallTimeTo,
			@RequestParam("apiResponseCode") String apiResponseCode) {*/
		List<AuditRecord> auditRecords = new ArrayList<>();
		HttpHeaders headers = new HttpHeaders();
		try {
			//auditRecords = auditRecordsServiceImpl.getAllAuditRecordsFromDB();
			System.out.println("Came to getAllAuditRecords1");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(auditRecords, headers, HttpStatus.OK);
	}

}
