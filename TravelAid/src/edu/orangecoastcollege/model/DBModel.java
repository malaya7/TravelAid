package edu.orangecoastcollege.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * <code>DBModel</code> represents the database model for a table containing one
 * primary key and one or more fields. It provides mechanisms by which new
 * records can be created and existing ones can be updated or deleted.
 * <code>DBModel</code> also provides functionality to query the database table
 * for a single record, all records or the total count of records.
 * 
 * @author
 * @version
 */
public class DBModel {

	private String mDBName;
	private String mTableName;
	private String[] mFieldNames;
	private String[] mFieldTypes;


	/**
	 * Instantiates a new <code>DBModel</code> given the required parameters,
	 * such as the database name, table name, field names and field types.
	 * 
	 * @param dbName
	 *            The database name, e.g. cs272.db
	 * @param tableName
	 *            The table name, e.g. billionaire
	 * @param fieldNames
	 *            The field names, e.g. "id", "name", "age", "gender", "worth",
	 *            "citizenship", "sector", "political"
	 * @param fieldTypes
	 *            The field types, e.g. "INTEGER PRIMARY KEY", "TEXT",
	 *            "INTEGER", "TEXT", "REAL", "TEXT", "TEXT", "INTEGER"
	 * @throws SQLException
	 *             If the field names are not the same length as the field
	 *             types, or the names/types are empty, or there is an error
	 *             connecting to the database.
	 */
	public DBModel(String dbName, String tableName, String[] fieldNames, String[] fieldTypes) throws SQLException {
		super();
		mDBName = dbName;
		mTableName = tableName;
		mFieldNames = fieldNames;
		mFieldTypes = fieldTypes;
		if (mFieldNames == null || mFieldTypes == null || mFieldNames.length == 0
				|| mFieldNames.length != mFieldTypes.length)
			throw new SQLException("Database field names and types must exist and have the same number of elements.");

		createTable();
	}

	/**
	 * Creates the database table, only if it does not already exist.
	 * 
	 * @throws SQLException
	 *             If a database access error occurs, this method is called on a
	 *             closed Statement, or the given SQL statement produces
	 *             anything other than a single ResultSet object.
	 */
	private void createTable() throws SQLException {
		try (Connection connection = connectToDB();
				Statement stmt = connection.createStatement())
		{
		StringBuilder createSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
		createSQL.append(mTableName).append("(");
		for (int i = 0; i < mFieldNames.length; i++)
			createSQL.append(mFieldNames[i]).append(" ").append(mFieldTypes[i])
					.append((i < mFieldNames.length - 1) ? "," : ")");
		stmt.executeUpdate(createSQL.toString());
		}
	}

	/**
	 * Gets all records from the database.
	 * 
	 * @return A <code>ResultSet</code> containing all records from the database
	 *         table.
	 * @throws SQLException
	 *             If a database access error occurs, this method is called on a
	 *             closed Statement, or the given SQL statement produces
	 *             anything other than a single ResultSet object.
	 */
	public ArrayList<ArrayList<String>> getAllRecords() throws SQLException {
		try (Connection connection = connectToDB();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM " + mTableName);)
		{
			ArrayList<ArrayList<String>> resultsList = new ArrayList<>();
			while (rs.next())
			{
				ArrayList<String> values = new ArrayList<>(mFieldNames.length);
				for (String field : mFieldNames)
					values.add(rs.getString(field));
				resultsList.add(values);
			}
			return resultsList;
		}
	}

	/**
	 * Gets a single record from the database matching a specific primary key.
	 * 
	 * @param key
	 *            The primary key value for the record to return.
	 * @return A <code>ResultSet</code> containing a single record matching the
	 *         key.
	 * @throws SQLException
	 *             If a database access error occurs, this method is called on a
	 *             closed Statement, or the given SQL statement produces
	 *             anything other than a single ResultSet object.
	 */
	public ArrayList<ArrayList<String>> getRecord(String key) throws SQLException {
		try (Connection connection = connectToDB();
				Statement stmt = connection.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM " + mTableName + " WHERE " + mFieldNames[0] + " = " + key);)
			{		
				ResultSetMetaData rsData = rs.getMetaData();
				ArrayList<ArrayList<String>> resultsList = new ArrayList<>();
				
				int cols = rsData.getColumnCount();
				while (rs.next())
				{
					ArrayList<String> values = new ArrayList<>(cols);
					for (int i = 1; i <= cols; i++)
						values.add(rs.getString(i));
					resultsList.add(values);
				}
				return resultsList;
			}
	}

	/**
	 * Gets the count of all records in the database.
	 * 
	 * @return The count of all records in the database.
	 * @throws SQLException
	 *             If a database access error occurs, this method is called on a
	 *             closed Statement, or the given SQL statement produces
	 *             anything other than a single ResultSet object.
	 */
	public int getRecordCount() throws SQLException {
		
		return getAllRecords().size();
	}

	/**
	 * Creates (inserts) a new record into the database with the fields and
	 * values provided. Usage: Do not provide a primary key in the fields or
	 * values, as the database will assign one automatically.
	 * 
	 * @param fields
	 *            The field names, e.g. "name", "age", "gender", "worth",
	 *            "citizenship", "sector", "political"
	 * @param values
	 *            The values, e.g. "Mike Paul", "40", "male", "2.2", "United
	 *            States", "technology", "0"
	 * @return The newly generated primary key, or -1 if there was an error.
	 * @throws SQLException
	 *             If a database access error occurs, this method is called on a
	 *             closed Statement, or the given SQL statement produces
	 *             anything other than a single ResultSet object.
	 */
	public int createRecord(String[] fields, String[] values) throws SQLException {

		if (fields == null || values == null || fields.length == 0 || fields.length != values.length)
			return -1;
		try (Connection connection = connectToDB();
				Statement stmt = connection.createStatement())
		{
		StringBuilder insertSQL = new StringBuilder("INSERT INTO ");
		insertSQL.append(mTableName).append("(");
		for (int i = 0; i < fields.length; i++)
			insertSQL.append(fields[i]).append((i < fields.length - 1) ? "," : ") VALUES(");
		for (int i = 0; i < values.length; i++)
			insertSQL.append(convertToSQLText(fields[i], values[i])).append((i < values.length - 1) ? "," : ")");

		stmt.executeUpdate(insertSQL.toString());
		return stmt.getGeneratedKeys().getInt(1);
		}
	}

	/**
	 * Updates a single record from the database matching the given primary key
	 * value. Usage: Do not provide primary key in the fields or values, only
	 * provide it as the key parameter.
	 * 
	 * @param key
	 *            The primary key value to update.
	 * @param fields
	 *            The field names, e.g. "name", "age", "gender", "worth",
	 *            "citizenship", "sector", "political"
	 * @param values
	 *            The values, e.g. "Mike Paul", "40", "male", "2.2", "United
	 *            States", "technology", "0"
	 * @return True if the record was updated successfully, false if the fields
	 *         length does not match the values length (or if fields/values are
	 *         empty).
	 * @throws SQLException
	 */
	public boolean updateRecord(String key, String[] fields, String[] values) throws SQLException {
		if (fields == null || values == null || fields.length == 0 || fields.length != values.length)
			return false;
		try (Connection connection = connectToDB();
				Statement stmt = connection.createStatement())
		{
		StringBuilder updateSQL = new StringBuilder("UPDATE ");
		updateSQL.append(mTableName).append(" SET ");
		for (int i = 0; i < fields.length; i++)
			updateSQL.append(fields[i]).append("=").append(convertToSQLText(fields[i], values[i]))
					.append((i < fields.length - 1) ? ", " : " ");

		updateSQL.append("WHERE ").append(mFieldNames[0]).append("=").append(key);
		stmt.executeUpdate(updateSQL.toString());
		return true;
		}
	}

	/**
	 * Deletes all records from the database.
	 * 
	 * @throws SQLException
	 *             If a database access error occurs, this method is called on a
	 *             closed Statement, or the given SQL statement produces
	 *             anything other than a single ResultSet object.
	 */
	public void deleteAllRecords() throws SQLException {
		try (Connection connection = connectToDB();
				Statement stmt = connection.createStatement())
		{
		stmt.executeUpdate("DELETE FROM " + mTableName);
		}
	}

	/**
	 * Deletes a single record from the database matching the given primary key
	 * value.
	 * 
	 * @param key
	 *            The primary key value to delete.
	 * @throws SQLException
	 *             If a database access error occurs, this method is called on a
	 *             closed Statement, or the given SQL statement produces
	 *             anything other than a single ResultSet object.
	 */
	public void deleteRecord(String key) throws SQLException {
		try (Connection connection = connectToDB();
				Statement stmt = connection.createStatement())
		{
		stmt.executeUpdate("DELETE FROM " + mTableName + " WHERE " + mFieldNames[0] + " = " + key);
		}
	}

	/**
	 * Converts a field value into SQL text by surrounding value with single
	 * quotes (e.g. technology becomes 'technology') This only happens when the
	 * field provided has the SQL data type TEXT.
	 * 
	 * @param field
	 *            The field name (e.g. sector)
	 * @param value
	 *            The value (e.g. technology)
	 * @return The value surrounded with single quotes if it's field type is
	 *         TEXT, otherwise returns the original value.
	 */
	private String convertToSQLText(String field, String value) {
		// Lookup the field in field names, if found, check to see if the type
		// is TEXT (if so, append ' ' around value)
		for (int i = 0; i < mFieldNames.length; i++) {
			if (mFieldNames[i].equals(field)) {
				if (mFieldTypes[i].toUpperCase().startsWith("TEXT"))
					return "'" + value + "'";
				break;
			}
		}
		return value;
	}

	/**
	 * Establishes a connection to the database.
	 * 
	 * @return The connection to the database.
	 * @throws SQLException
	 *             If a database access error occurs, this method is called on a
	 *             closed Statement, or the given SQL statement produces
	 *             anything other than a single ResultSet object.
	 */
	private Connection connectToDB() throws SQLException {
		// Load SQLite database classes
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Establish a connection to the database and return that connection
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + mDBName);
		return connection;

	}

}

