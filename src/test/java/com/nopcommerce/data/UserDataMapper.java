package com.nopcommerce.data;

import commons.GlobalConstants;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDataMapper {
	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("emailAddress")
	private String emailAddress;

	@JsonProperty("password")
	private String password;

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public String getPassword() {
		return password;
	}
	
		public static UserDataMapper getUserData() {
			try {
				ObjectMapper mapper = new ObjectMapper();
				// Check nếu thuộc tính ko lấy thì báo lỗi, đủ rồi thì ko báo lỗi
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				return mapper.readValue(new File(GlobalConstants.RELATIVE_PROJECT_PATH + "/src/test/resources/UserData.json"), UserDataMapper.class);
				// Đường dẫn / có thể chạy được trên cả Mac và window, ko cần xử lý thêm
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}

}