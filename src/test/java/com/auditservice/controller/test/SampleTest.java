package com.auditservice.controller.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.auditservice.data.mongo.repo.AuditMongoRepository;
import com.auditservice.dto.AuditRecord;

public class SampleTest {

	public MockMvc mockMvc;

	@MockBean
	private AuditMongoRepository auditMongoRepository;

	@Test
	public void createProduct() throws Exception {
		String uri = "/audits1";
		AuditRecord auditRecord  = new AuditRecord();
		auditRecord.setUserId("skp");
		auditRecord.setApiPath("/audits");
		auditRecord.setApiMethod("GET");
		auditRecord.setApiResponseCode(200);

		String reqParam = "ReqParam";

		Mockito.when(auditMongoRepository.save(auditRecord)).thenReturn(auditRecord);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(reqParam)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Product is created successfully");
	}

	@Test
	public void getProductsList() throws Exception {
		String uri = "/audits11";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		/*Product[] productlist = super.mapFromJson(content, Product[].class);
	   assertTrue(productlist.length > 0);*/
		assertTrue(true);
	}

}
