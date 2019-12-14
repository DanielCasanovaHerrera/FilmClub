package org.iesalixar.daw2.rest;

import org.apache.log4j.Logger;
import org.iesalixar.daw2.dao.ProductDaoImpl;
import org.iesalixar.daw2.model.Product;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;

@Path("/services")

public class MethodsImpl {

	final static Logger logger = Logger.getLogger(MethodsImpl.class);

	@GET
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/getProducts")
	public Response getProducts() throws JSONException {

		JSONObject jsonObject = new JSONObject();
		Gson gson = new Gson();
		String products = "{";
		List<Product> productlist = ProductDaoImpl.getProducts(-1, true);

		for (int i = 0; i <= productlist.size() - 1; i++) {
			if (i == productlist.size() - 1) {
				products = products + " Product" + (i + 1) + ":[{ Nombre:" + productlist.get(i).getShortname() +", Descripcion Completa: " +productlist.get(i).getFulldescription()+", Compañia Creadora: "+productlist.get(i).getCompany()+", Precio: "+productlist.get(i).getReposition_value()+"€"+ "}]";
			}else {
				products = products + " Product" + (i + 1) + ":[{ Nombre:" + productlist.get(i).getShortname() +", Descripcion Completa: " +productlist.get(i).getFulldescription()+", Compañia Creadora: "+productlist.get(i).getCompany()+", Precio: "+productlist.get(i).getReposition_value()+"€"+ "}],";
			}

		}
		products = products + "}";
		jsonObject.put("Products ", products);

		logger.info("getProducts was executed in rest");

		return Response.status(200).entity(gson.toJson(jsonObject)).build();

	}
	
	@GET
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/getProduct/{id}")
	public Response getProduct(@PathParam("id") int id) throws JSONException {
		
		JSONObject jsonObject = new JSONObject();
		Gson gson = new Gson();
		String products = "{";
		Product product= ProductDaoImpl.getProductId(id);

		products = products + " Product" + ":[{ Nombre:" + product.getShortname() +", Descripcion Completa: " +product.getFulldescription()+", Compañia Creadora: "+product.getCompany()+", Precio: "+product.getReposition_value()+"€"+ "}]";
		
		products = products + "}";
		jsonObject.put("Product: ", products);

		logger.info("getProducts was executed in rest");
		return Response.status(200).entity(gson.toJson(jsonObject)).build();

	}

}
