package com.nopcommerce.data;

public class UserData {
	// Tạo ra các sub class để quản lý data test cho từng class
		//1- Chức năng có nhiều class test thì nhóm chức năng vào 1 class data (k có sub class)
		//2- Nhóm nguyên 1 module lớn vào 1 class data - Trong class này sẽ có các class con bên trong

		public class Register{
			public static final String FIRST_NAME ="Chiem";
			public static final String LAST_NAME ="Vu";
			public static final String EMAIL_ADDRESS ="chiemvt";
			public static final String PASSWORD ="123456";
		}
		public class Login{

		}
		public class Adrress{

		}
		public class MyAccount{
			public static final String ADDRESS ="";
			public static final String CITY ="";
			public static final String PHONE ="";
		}
}
