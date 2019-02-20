package com.auditservice.audit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.auditservice.data.mongo.repo.AuditMongoRepository;
import com.auditservice.dto.AuditRecord;
import com.fasterxml.jackson.databind.ObjectMapper;



//@WebMvcTest
/*@RunWith(SpringRunner.class)
@SpringBootTest
public class AuditApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	AuditRecord auditRecord;

	@MockBean
	private AuditMongoRepository auditMongoRepository;

	@MockBean
	private AuditRecordsServiceImpl auditRecordsServiceImpl;


	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);

	}*/



/*@RunWith(SpringRunner.class)
@SpringBootTest*/

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AuditApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;

	/*@Autowired
	private AuditRecord auditRecord;*/

	@MockBean
	private AuditMongoRepository auditMongoRepository;
	
	 @Autowired
	 private ObjectMapper objectMapper;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/*	  
	  @Autowired
	  private AuditRecord auditRecord;

	  @MockBean
	  AuditMongoRepository auditMongoRepository;


		@Test
		public void testFindTheGreatestFromAllData() {

			auditRecord.setId("5c61c7b0c9aaae25f8f807da");
			auditRecord.setUserId	("skp");
			auditRecord.setApiPath("/audits");
			auditRecord.setApiMethod("GET");
			auditRecord.setApiResponseCode(200);
			when(auditMongoRepository.save(auditRecord)).thenReturn(auditRecord);
			assertEquals(auditRecord, auditMongoRepository.findAll());
		}*/

	//@Test
	public void contextLoads() throws Exception {

		AuditRecord auditRecord  = new AuditRecord();
		auditRecord.setUserId("skp");
		auditRecord.setApiPath("/audits1");
		auditRecord.setApiMethod("POST");
		auditRecord.setApiResponseCode(200);
		//auditRecord.setApiCallTime("2019-02-11T00:00:00.000Z");

		Mockito.when(auditMongoRepository.save(auditRecord)).thenReturn(auditRecord);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/audits1");
		//.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("result is " + result.getResponse().getContentAsString());

		MockHttpServletResponse output = result.getResponse();
		System.out.println("output is: "+output);

		String output1 = result.getResponse().getContentAsString();

		/*System.out.println("Output1 is " + output1);
		System.out.println("-------------------------------");
		//System.out.println("output is"+output.getContentAsString());
		System.out.println("output is"+output);*/
		assertEquals(auditRecord, output);
	}

	//@Test
	public void contextLoads1() throws Exception {

		AuditRecord auditRecord  = new AuditRecord();
		auditRecord.setUserId("skp");
		auditRecord.setApiPath("/audits");
		auditRecord.setApiMethod("GET");
		auditRecord.setApiResponseCode(200);
		//auditRecord.setApiCallTime("2019-02-11T00:00:00.000Z");


		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/audits1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		int status = result.getResponse().getStatus();
		System.out.println("result is " + status);
		assertEquals(201, status);

		MockHttpServletResponse output = result.getResponse();
		System.out.println("output is: "+output);

		String output1 = result.getResponse().getContentAsString();

		/*System.out.println("Output1 is " + output1);
		System.out.println("-------------------------------");
		//System.out.println("output is"+output.getContentAsString());
		System.out.println("output is"+output);*/
		assertEquals(auditRecord, output);
	}

	//@Test
	public void getProductsList() throws Exception {
		//String uri = "http://localhost:8080/audits11";
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

	@Test
	public void createProduct() throws Exception {
		String uri = "/audits1";
		AuditRecord auditRecord  = new AuditRecord();
		auditRecord.setUserId("skp");
		auditRecord.setApiPath("/audits1");
		auditRecord.setApiMethod("POST");
		auditRecord.setApiResponseCode(200);
		//String inputJson = super.mapToJson(auditRecord);
		/*MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
				//.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("dummyStr")).andReturn();*/
		
		
		/*MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/audits1")
         .contentType(MediaType.APPLICATION_JSON)
         .content(objectMapper.writeValueAsString(auditRecord)));*/
		
		String json = objectMapper.writeValueAsString(auditRecord);
		MvcResult mvcResult = mockMvc.perform(post("/audits1")
	       .contentType(MediaType.APPLICATION_JSON)
	       .content(json)
	       .accept(MediaType.APPLICATION_JSON)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		/*String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Product is created successfully");*/
	}

}

