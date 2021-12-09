package com.Covid.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.Covid.model.Covid;

public class RestClient {
	private static final String GET_ALL_REPORT = "http://localhost:8080/api/v1/report/";
	private static final String GET_REPORT_BY_ID = "http://localhost:8080/api/v1/report/{id}";
	private static final String UPDATE_STATE = "http://localhost:8080/api/v1/report/{id}";
	private static final String DELETE_STATE = "http://localhost:8080/api/v1/report/{id}";
	//private static final String 
	static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		//CallgetReportbyID();
		//CallDeleteStateByID();
		CallGetAllReport();
		
		//CallCreate();
	}

	public static void CallGetAllReport() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<String> result = restTemplate.exchange(GET_ALL_REPORT, HttpMethod.GET, entity, String.class);
		System.out.println(result);

	}

	private static void CallgetReportbyID() {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", 4);
		Covid getrep = restTemplate.getForObject(GET_REPORT_BY_ID, Covid.class, param);
		System.out.println(getrep.getId());
		System.out.println(getrep.getState());
		System.out.println(getrep.getCases());

	}

	private static void CallDeleteStateByID() {
		Map<String, Integer> param = new HashMap<>();
		Scanner sc = new Scanner(System.in);
		System.out.println("enter id to remove");
		int myid = sc.nextInt();

		param.put("id", myid);
		restTemplate.delete(DELETE_STATE, param);

		System.out.println("id is removed");
		CallGetAllReport();
		sc.close();

	}

	private static void CallUpdate() {
//upadte state name or cases 
		Map<String, Integer> param = new HashMap<>();
		param.put("id", 9);
		Covid update = new Covid(9, "Jharkhand", 99);
		restTemplate.put(UPDATE_STATE, update, param);
		CallGetAllReport();


	}
	private static void CallCreate() {
		
		Covid update = new Covid(15, "Pune", 6023);
		//restTemplate.postForEntity(null, update, null)
		restTemplate.postForEntity(GET_ALL_REPORT, update, Covid.class);
		//restTemplate.put(UPDATE_STATE, update, param);
		CallGetAllReport();
	}

}
