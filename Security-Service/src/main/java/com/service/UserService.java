package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.client.RemarksClient;
import com.client.TimerClient;
import com.entity.UserInfo;
import com.exception.ResourceNotFoundException;
import com.repository.UserInfoRepository;

@Service
public class UserService {
	@Autowired
	private UserInfoRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RemarksClient remarksClient;
	
	@Autowired
	private TimerClient timerClient;
	
	private static final String TEAM_NOT_FOUND = "team not found with id:";

	public String addUser(UserInfo userInfo) {
		String name = userInfo.getName();
		UserInfo obj1 = repository.findByName(name).orElse(null);
		System.out.println(obj1);
		if (obj1 == null) {
			userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
			repository.save(userInfo);
			return "Registration Successfully ";
		} else {
			return "Already Existing User is updated.";
		}
	}

	public String getRoles(String username) {
		UserInfo obj2 = repository.findByName(username).orElse(null);
		if (obj2 != null) {
			return obj2.getRoles();
		}
		return "Not Found";
	}
	
	public List<UserInfo> getAllTeams() {
		return repository.findAll();
	}
	
	public Optional<UserInfo> getTeamById(int id) {
		Optional<UserInfo> optional = repository.findById(id);
		if (optional.isPresent())
			return Optional.ofNullable(optional.get());
		else
			throw new ResourceNotFoundException(TEAM_NOT_FOUND + id);
	}
	
	public UserInfo updateTeam(int id, UserInfo teamDetails) {
		UserInfo team = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TEAM_NOT_FOUND + id));
		team.setName(teamDetails.getName());
		team.setEmail(teamDetails.getEmail());
		team.setEmpDesg(teamDetails.getEmpDesg());
		team.setRoles(teamDetails.getRoles());
		team.setSalary(teamDetails.getSalary());
		team.setDoj(teamDetails.getDoj());
		team.setSkillset(teamDetails.getSkillset());
		team.setEmpType(teamDetails.getEmpType());
		team.setDepartmentName(teamDetails.getDepartmentName());
		team.setYearsOfExperience(teamDetails.getYearsOfExperience());
		return repository.save(team);
	}
	
	public void deleteTeam(int id) {
		UserInfo team = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TEAM_NOT_FOUND + id));
		long longid = (long) id;
		remarksClient.deleteAllByEmployeeId(longid);
		timerClient.deleteAllByEmployeeId(longid);
		repository.delete(team);
	}
}
