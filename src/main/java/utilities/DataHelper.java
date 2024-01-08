package utilities;

import java.util.Locale;

import com.github.javafaker.Faker;

public class DataHelper {
	private Locale local = new Locale("en");
	private Faker fake = new Faker(local);

	public static DataHelper getDataHelper() {
		return new DataHelper();
	}

	// dữ liệu hay sử dụng: Firstname/ Lastname/ Email/ city/ Phone/ Address/ State/ Pin/ Zip Code/ CountryYOKANOJO
	public String getFirstName() {
		return fake.address().firstName();
	}

	public String getLastName() {
		return fake.address().lastName();
	}

	public String getEmailAddress() {
		return fake.internet().emailAddress();
	}

	public String getCityName() {
		return fake.address().city();
	}

	public String getPhoneNumber() {
		return fake.phoneNumber().phoneNumber();
	}

	public String getAddress() {
		return fake.address().streetAddress();
	}

	public String getPassword() {
		return fake.internet().password(6, 8, true, true);
	}
}
