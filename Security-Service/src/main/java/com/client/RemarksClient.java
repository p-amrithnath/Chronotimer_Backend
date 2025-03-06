package com.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "REMARKS")
public interface RemarksClient {


	@DeleteMapping("/remarks/all/{id}")
	 public ResponseEntity<String> deleteAllByEmployeeId(@PathVariable Long id);

}
