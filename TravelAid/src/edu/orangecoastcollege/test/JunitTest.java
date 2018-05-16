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
import  edu.orangecoastcollege.model.*;

public class JunitTest {
	private static final String DB_NAME = "USADairyTest.db";
	private static final String DAIRY_TABLE_NAME = "dairy";
	private static final String[] DAIRY_TABLE_FIELD_NAME = {"_id", "type", "description", "unit", "price", "country_code" };
	private static final String[] DAIRY_TABLE_FIELD_TYPE =  {"INTEGER PRIMARY KEY", "TEXT", "TEXT", "TEXT", "REAL","INTEGER"};
	
	private static final String USA_DAIRY_FILE_DATA_FILE = "Dairy USA.csv";
	private static DBModel mDB;
	private static Controller controler; 
	String[] fields;
	String[] values;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mDB = new DBModel(DB_NAME, DAIRY_TABLE_NAME, DAIRY_TABLE_FIELD_NAME, DAIRY_TABLE_FIELD_TYPE);
		controler = Controller.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {
		fields = new String[] { "type", "description", "unit", "price", "country_code" };
		values = new String[] {"cheese","cheddar","lb","25","1" };
		mDB.deleteAllRecords();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateRecord() {
	try {
		int key = mDB.createRecord(fields, values);
			assertEquals("testing that the key is 1", 1, key);
	} catch (SQLException e) {
		fail("should have not failed");
		
	}

	
	}
	@Test
public void testInitializeDairy()
{
	controler = Controller.getInstance();
	
	
	for(Grocery f: controler.getAllGroceries())
	{	System.out.println(f.getDescription());
		
	}
	try {
		ArrayList<ArrayList<String>> f = mDB.getAllRecords();
		
		for(ArrayList<String> p: f)
		{
			for(String g: p)
			{
				System.out.println(g);
			}
		}
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
	
}
