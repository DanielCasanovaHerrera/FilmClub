package org.iesalixar.daw2.rest;

import org.apache.log4j.Logger;
import org.iesalixar.daw2.dao.ProductDaoImpl;
import org.iesalixar.daw2.model.Product;
import javax.ws.rs.Path;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;

@Path("/")

public class MethodsImpl {
	
	final static Logger logger = Logger.getLogger(MethodsImpl.class);
	
	@GET
	@Path("/getProducts")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getProducts() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		Gson gson = new Gson();
		String products = "";
		List<Product> productlist = ProductDaoImpl.getProducts(-1, true);

		for (int i = 0; i <= productlist.size() - 1; i++) {

			products = " Product: " + productlist.get(i).getShortname() + "; "+ products;

		}
		jsonObject.put("The Products: ", products);

		logger.info("getProducts was executed in rest");

		return Response.status(200).entity(gson.toJson(jsonObject)).build();
		
	}

}
