package com.springboot.h2.commons;

public enum ResponseCode {

	
		ER201("ER201", "Error  while processing request"), 
		ER202("ER202", "Remmittance Internal Error"),
		ER204("ER204", "Error while saving data"), 
		ER205("ER205", "Error while updating data"),
		VALIDATION_ERROR("ER206", "Validation Error"), 
		ER207("ER207", "System Error"),
		ER208("ER208", "Error while getting records from database"), 
		ER209("ER209", "Error while converting response"),
		ER211("ER211", "Duplicate beyonic Transaction Id"),
		ER215("ER215", "Null response"),
		ER216("ER216", "Request cannot be null"),
		ER217("ER217","Error while converting request"),
		ER203("ER203","Error while connecting partner"),
		ER210("ER210","Error while getting config data from database"),
		ER213("ER213", "No record found against transactionID"),
		ER214("ER214", "No record found against account_number"),
		ER411("ER411","You are not autherized to access this service"),
		LOG_SUCCESS("Log Success","Log Success"),
		LOG_FAIL("Log Fail" , "Log Fail"),
		COMMIT_SUCCESS("Commit Success", "Commit Success"),
		COMMIT_PENDING("Commit Pending" , "Commit Pending"),
		COMMIT_FAIL("Commit Fail","Commit Fail"),
		INVALID_REFERENCE("INVALID_REFERENCE", "Invalid Reference");
		
		private String code;
		private String message;

		private ResponseCode(String code, String message) {
			this.code = code;
			this.message = message;
		}

		public String getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}
	
}
