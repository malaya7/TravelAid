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
	private static final String DB_NAME = "USADairyTest.db";
	private static final String DAIRY_TABLE_NAME = "dairy";
	private static final String[] DAIRY_TABLE_FIELD_NAME = { "_id", "type", "description", "unit", "price",
			"country_code" };
	private static final String[] DAIRY_TABLE_FIELD_TYPE = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "REAL",
			"INTEGER" };

	private static final String USA_DAIRY_FILE_DATA_FILE = "Dairy USA.csv";
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
		fields = new String[] { "type", "description", "unit", "price", "country_code" };
		values = new String[] { "cheese", "cheddar", "lb", "25", "1" };
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testControllerGetInstance() {
		controler = Controller.getInstance();

		System.out.println(controler.getAllGroceries().size());
		System.out.println(controler.getAllTransportation().size());
		/*for (Grocery f : controler.getAllGroceries()) 
			System.out.println(f.toString());*/

		

	}

}
