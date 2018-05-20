package edu.orangecoastcollege.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.orangecoastcollege.controller.Controller;
import edu.orangecoastcollege.model.*;

public class JunitTest {

	
	private static DBModel mDB;
	private static Controller controler;
	String[] fields;
	String[] values;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		controler = Controller.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {

		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testControllerGetInstance() {
		controler = Controller.getInstance();

		System.out.println(controler.getAllGroceries().size());
		System.out.println(controler.getAllTransportation().size());
		
		for (Grocery f : controler.getAllGroceries()) 
			{if(Integer.valueOf(Controller.SPAIN_COUNTRY_CODE).equals(f.getCountry_code())){
				System.out.println(f);
			}
			}

		

	}

}
