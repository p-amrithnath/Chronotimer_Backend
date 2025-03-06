package com.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TIMER")
public interface TimerClient {

	@DeleteMapping("/timesheets/all/{id}")
	public ResponseEntity<String> deleteAllByEmployeeId(@PathVariable Long id);

}