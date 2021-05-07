package com.trellisconnect.missingFieldIdentification;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MissingFieldIdentificationApplicationTests {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private MockMvc mockMvc;
 
	@Test
	public void testInvaildPolicyHolderAddress() throws Exception {
		String requestJson = "{\n" + 
				"    \"policyId\": \"9afe73e5-77c9-4677-ba5f-1b9e807396a0\",\n" + 
				"    \"issuer\": \"nationwide\",\n" + 
				"    \"issueDate\": \"2021-01-21\",\n" + 
				"    \"renewalDate\": \"2021-07-22\",\n" + 
				"    \"policyTermMonths\": 6,\n" + 
				"    \"premiumCents\": 49234,\n" + 
				"    \"policyHolder\": {\n" + 
				"      \"name\": {\n" + 
				"        \"firstName\": \"Caroline\",\n" + 
				"        \"middleName\": \"\",\n" + 
				"        \"lastName\": \"White\"\n" + 
				"      }, \n" + 
				"      \"email\": \"Caroline.White897@me.com\"\n" + 
				"    },\n" + 
				"    \"operators\": [\n" + 
				"      {\n" + 
				"        \"isPrimary\": true,\n" + 
				"        \"name\": {\n" + 
				"          \"firstName\": \"Caroline\",\n" + 
				"          \"middleName\": \"\",\n" + 
				"          \"lastName\": \"White\"\n" + 
				"        },\n" + 
				"        \"birthdayRange\": {\n" + 
				"          \"start\": \"1988-05-22\",\n" + 
				"          \"end\": \"1989-05-21\"\n" + 
				"        },\n" + 
				"        \"gender\": \"female\",\n" + 
				"        \"driversLicenseState\": \"IA\",\n" + 
				"        \"driversLicenseStatus\": \"ValidUSLicense\",\n" + 
				"        \"driversLicenseNumber\": \"67712211\",\n" + 
				"        \"relationship\": \"Named Insured\"\n" + 
				"      }\n" + 
				"    ]\n" + 
				"  }";
		this.mockMvc.perform(put("/api/findMissingFields/colonel").contentType(APPLICATION_JSON_UTF8)
		        .content(requestJson)).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("{\"policyHolder\":{\"address\":{\"number\":null,\"street\":null,\"city\":null,\"state\":null,\"zip\":null}}}")));
	}
	
	
	@Test
	public void testMissedPolicyHolderName() throws Exception {
		String requestJson = "{\n" + 
				"    \"policyId\": \"9afe73e5-77c9-4677-ba5f-1b9e807396a0\",\n" + 
				"    \"issuer\": \"nationwide\",\n" + 
				"    \"issueDate\": \"2021-01-21\",\n" + 
				"    \"renewalDate\": \"2021-07-22\",\n" + 
				"    \"policyTermMonths\": 6,\n" + 
				"    \"premiumCents\": 49234,\n" + 
				"    \"policyHolder\": {\n" + 
				"      \"address\": {\n" + 
				"        \"number\": \"292\",\n" + 
				"        \"street\": \"Hillside Way Apt 398\",\n" + 
				"        \"suffix\": \"S\",\n" + 
				"        \"city\": \"Pierce\",\n" + 
				"        \"state\": \"IA\",\n" + 
				"        \"zip\": \"30328\"\n" + 
				"      },\n" + 
				"      \"email\": \"Caroline.White897@me.com\"\n" + 
				"    },\n" + 
				"    \"operators\": [\n" + 
				"      {\n" + 
				"        \"isPrimary\": true,\n" + 
				"        \"name\": {\n" + 
				"          \"firstName\": \"Caroline\",\n" + 
				"          \"middleName\": \"\",\n" + 
				"          \"lastName\": \"White\"\n" + 
				"        },\n" + 
				"        \"birthdayRange\": {\n" + 
				"          \"start\": \"1988-05-22\",\n" + 
				"          \"end\": \"1989-05-21\"\n" + 
				"        },\n" + 
				"        \"gender\": \"female\",\n" + 
				"        \"driversLicenseState\": \"IA\",\n" + 
				"        \"driversLicenseStatus\": \"ValidUSLicense\",\n" + 
				"        \"driversLicenseNumber\": \"11212211633\",\n" + 
				"        \"relationship\": \"Named Insured\"\n" + 
				"      }\n" + 
				"    ]\n" + 
				"  }";
		this.mockMvc.perform(put("/api/findMissingFields/colonel").contentType(APPLICATION_JSON_UTF8)
		        .content(requestJson)).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("{\"policyHolder\":{\"name\":{\"firstName\":null,\"lastName\":null}}}")));
	}
	
	@Test
	public void testMissedPolicyHolderEmail() throws Exception {
		String requestJson = "{\n" + 
				"    \"policyId\": \"9afe73e5-77c9-4677-ba5f-1b9e807396a0\",\n" + 
				"    \"issuer\": \"nationwide\",\n" + 
				"    \"issueDate\": \"2021-01-21\",\n" + 
				"    \"renewalDate\": \"2021-07-22\",\n" + 
				"    \"policyTermMonths\": 6,\n" + 
				"    \"premiumCents\": 49234,\n" + 
				"    \"policyHolder\": {\n" + 
				"      \"name\": {\n" + 
				"        \"firstName\": \"Caroline\",\n" + 
				"        \"middleName\": \"\",\n" + 
				"        \"lastName\": \"White\"\n" + 
				"      },\n" + 
				"      \"address\": {\n" + 
				"        \"number\": \"292\",\n" + 
				"        \"street\": \"Hillside Way Apt 398\",\n" + 
				"        \"suffix\": \"S\",\n" + 
				"        \"city\": \"Pierce\",\n" + 
				"        \"state\": \"IA\",\n" + 
				"        \"zip\": \"30328\"\n" + 
				"      }\n" + 
				"    },\n" + 
				"    \"operators\": [\n" + 
				"      {\n" + 
				"        \"isPrimary\": true,\n" + 
				"        \"name\": {\n" + 
				"          \"firstName\": \"Caroline\",\n" + 
				"          \"middleName\": \"\",\n" + 
				"          \"lastName\": \"White\"\n" + 
				"        },\n" + 
				"        \"birthdayRange\": {\n" + 
				"          \"start\": \"1988-05-22\",\n" + 
				"          \"end\": \"1989-05-21\"\n" + 
				"        },\n" + 
				"        \"gender\": \"female\",\n" + 
				"        \"driversLicenseState\": \"IA\",\n" + 
				"        \"driversLicenseStatus\": \"ValidUSLicense\",\n" + 
				"        \"driversLicenseNumber\": \"11212211633\",\n" + 
				"        \"relationship\": \"Named Insured\"\n" + 
				"      }\n" + 
				"    ]\n" + 
				"  }";
		this.mockMvc.perform(put("/api/findMissingFields/colonel").contentType(APPLICATION_JSON_UTF8)
		        .content(requestJson)).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("{\"policyHolder\":{\"email\":null}}")));
	}
	
	@Test
	public void testInvaildPolicyHolderDriverLicense() throws Exception {
		String requestJson = "{\n" + 
				"    \"policyId\": \"9afe73e5-77c9-4677-ba5f-1b9e807396a0\",\n" + 
				"    \"issuer\": \"nationwide\",\n" + 
				"    \"issueDate\": \"2021-01-21\",\n" + 
				"    \"renewalDate\": \"2021-07-22\",\n" + 
				"    \"policyTermMonths\": 6,\n" + 
				"    \"premiumCents\": 49234,\n" + 
				"    \"policyHolder\": {\n" + 
				"      \"name\": {\n" + 
				"        \"firstName\": \"Caroline\",\n" + 
				"        \"middleName\": \"\",\n" + 
				"        \"lastName\": \"White\"\n" + 
				"      },\n" + 
				"      \"address\": {\n" + 
				"        \"number\": \"292\",\n" + 
				"        \"street\": \"Hillside Way Apt 398\",\n" + 
				"        \"suffix\": \"S\",\n" + 
				"        \"city\": \"Pierce\",\n" + 
				"        \"state\": \"IA\",\n" + 
				"        \"zip\": \"30328\"\n" + 
				"      },\n" + 
				"      \"email\": \"Caroline.White897@me.com\"\n" + 
				"    },\n" + 
				"    \"operators\": [\n" + 
				"      {\n" + 
				"        \"isPrimary\": true,\n" + 
				"        \"name\": {\n" + 
				"          \"firstName\": \"Caroline\",\n" + 
				"          \"middleName\": \"\",\n" + 
				"          \"lastName\": \"White\"\n" + 
				"        },\n" + 
				"        \"birthdayRange\": {\n" + 
				"          \"start\": \"1988-05-22\",\n" + 
				"          \"end\": \"1989-05-21\"\n" + 
				"        },\n" + 
				"        \"gender\": \"female\",\n" + 
				"        \"driversLicenseState\": \"IA\",\n" + 
				"        \"driversLicenseStatus\": \"ValidUSLicense\",\n" + 
				"        \"driversLicenseNumber\": \"XXXXX1633\",\n" + 
				"        \"relationship\": \"Named Insured\"\n" + 
				"      }\n" + 
				"    ]\n" + 
				"  }";
		this.mockMvc.perform(put("/api/findMissingFields/colonel").contentType(APPLICATION_JSON_UTF8)
		        .content(requestJson)).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("{\"operators\":[{\"isPrimary\":true,\"driversLicenseNumber\":\"XXXXX1633\"}]}")));
	}
	
	@Test
	public void testMissedOperatorBirtyDayRange() throws Exception {
		String requestJson = "{\n" + 
				"    \"policyId\": \"9afe73e5-77c9-4677-ba5f-1b9e807396a0\",\n" + 
				"    \"issuer\": \"nationwide\",\n" + 
				"    \"issueDate\": \"2021-01-21\",\n" + 
				"    \"renewalDate\": \"2021-07-22\",\n" + 
				"    \"policyTermMonths\": 6,\n" + 
				"    \"premiumCents\": 49234,\n" + 
				"    \"policyHolder\": {\n" + 
				"      \"name\": {\n" + 
				"        \"firstName\": \"Caroline\",\n" + 
				"        \"middleName\": \"\",\n" + 
				"        \"lastName\": \"White\"\n" + 
				"      },\n" + 
				"      \"address\": {\n" + 
				"        \"number\": \"292\",\n" + 
				"        \"street\": \"Hillside Way Apt 398\",\n" + 
				"        \"suffix\": \"S\",\n" + 
				"        \"city\": \"Pierce\",\n" + 
				"        \"state\": \"IA\",\n" + 
				"        \"zip\": \"30328\"\n" + 
				"      },\n" + 
				"      \"email\": \"Caroline.White897@me.com\"\n" + 
				"    },\n" + 
				"    \"operators\": [\n" + 
				"      {\n" + 
				"        \"isPrimary\": true,\n" + 
				"        \"name\": {\n" + 
				"          \"firstName\": \"Caroline\",\n" + 
				"          \"middleName\": \"\",\n" + 
				"          \"lastName\": \"White\"\n" + 
				"        },\n" + 
				"        \"gender\": \"female\",\n" + 
				"        \"driversLicenseState\": \"IA\",\n" + 
				"        \"driversLicenseStatus\": \"ValidUSLicense\",\n" + 
				"        \"driversLicenseNumber\": \"11212211633\",\n" + 
				"        \"relationship\": \"Named Insured\"\n" + 
				"      }\n" + 
				"    ]\n" + 
				"  }";
		this.mockMvc.perform(put("/api/findMissingFields/colonel").contentType(APPLICATION_JSON_UTF8)
		        .content(requestJson)).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("{\"operators\":[{\"birthdayRange\":{\"start\":null,\"end\":null}}]}")));
	}
	
	
	@Test
	public void testMissedOperatorGender() throws Exception {
		String requestJson = "{\n" + 
				"    \"policyId\": \"9afe73e5-77c9-4677-ba5f-1b9e807396a0\",\n" + 
				"    \"issuer\": \"nationwide\",\n" + 
				"    \"issueDate\": \"2021-01-21\",\n" + 
				"    \"renewalDate\": \"2021-07-22\",\n" + 
				"    \"policyTermMonths\": 6,\n" + 
				"    \"premiumCents\": 49234,\n" + 
				"    \"policyHolder\": {\n" + 
				"      \"name\": {\n" + 
				"        \"firstName\": \"Caroline\",\n" + 
				"        \"middleName\": \"\",\n" + 
				"        \"lastName\": \"White\"\n" + 
				"      },\n" + 
				"      \"address\": {\n" + 
				"        \"number\": \"292\",\n" + 
				"        \"street\": \"Hillside Way Apt 398\",\n" + 
				"        \"suffix\": \"S\",\n" + 
				"        \"city\": \"Pierce\",\n" + 
				"        \"state\": \"IA\",\n" + 
				"        \"zip\": \"30328\"\n" + 
				"      },\n" + 
				"      \"email\": \"Caroline.White897@me.com\"\n" + 
				"    },\n" + 
				"    \"operators\": [\n" + 
				"      {\n" + 
				"        \"isPrimary\": true,\n" + 
				"        \"name\": {\n" + 
				"          \"firstName\": \"Caroline\",\n" + 
				"          \"middleName\": \"\",\n" + 
				"          \"lastName\": \"White\"\n" + 
				"        },\n" + 
				"        \"birthdayRange\": {\n" + 
				"          \"start\": \"1988-05-22\",\n" + 
				"          \"end\": \"1989-05-21\"\n" + 
				"        }, \n" + 
				"        \"driversLicenseState\": \"IA\",\n" + 
				"        \"driversLicenseStatus\": \"ValidUSLicense\",\n" + 
				"        \"driversLicenseNumber\": \"11212211633\",\n" + 
				"        \"relationship\": \"Named Insured\"\n" + 
				"      }\n" + 
				"    ]\n" + 
				"  }";
		this.mockMvc.perform(put("/api/findMissingFields/colonel").contentType(APPLICATION_JSON_UTF8)
		        .content(requestJson)).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("{\"operators\":[{\"gender\":null}]}")));
	}
	

}
