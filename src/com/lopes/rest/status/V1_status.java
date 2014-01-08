package com.lopes.rest.status;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.lopes.dao.DBConnection;

@Path("/v1/status")
public class V1_status {

	private static final String api_version = "00.01.00";

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p>";
	}

	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version: </p>" + api_version;
	}

	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		String returnString = "Olá";
		try {
			
			Statement stmt = DBConnection.getDBConnection();
			ResultSet rs = stmt.executeQuery("Select * from products");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}
			rs.close();
			DBConnection.closeConnection(stmt);
		} catch (Exception e) {
			System.out.println("***** ERROR ***** ->" + e);

		}

		return returnString;
	}
}
