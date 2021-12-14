package com.Covid;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Covid.model.Covid;
import com.Covid.repository.CovidRepository;
import java.util.List;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CovidReportApplicationTests {

@Autowired

CovidRepository covidRepo;
@Test
@Order(1)
	public void testAdd()
	{
		Covid covid =new Covid();
		covid.setId(21);
		covid.setCases(000);
		covid.setState("Test");
		covidRepo.save(covid);
		assertNotNull(covidRepo.findById(21L).get());
		
	}
@Test
@Order(4)
public void testGetAllReport()
{
	List<Covid> covidList =covidRepo.findAll();
	assertThat(covidList).size().isGreaterThan(1);
}
@Test
@Order(2)
public void testGetbyID()
{
	Covid covid =covidRepo.findById(1L).get();
	assertEquals(5, covid.getCases());
}
@Test
@Order(3)
public void testDelete()
{
covidRepo.deleteById(20L);
assertThat(covidRepo.existsById(20L)).isFalse();
}
}
