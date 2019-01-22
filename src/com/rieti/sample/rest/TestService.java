package com.rieti.sample.rest;

import java.util.Date;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.rieti.sample.listener.SampleTable;

@Path("/")
public class TestService
{

	@GET
	@Produces("application/json")
	@Path("/{key}")
	public Response get(@PathParam("key") Integer key)
	{
		String result = SampleTable.select(key);
		if (result == null)
			return Response.status(Status.NOT_FOUND).build();
		
		
		TestServiceDTO dto = new TestServiceDTO();
		dto.setAction("GET");
		dto.setKey(key);
		dto.setValue(result);
		dto.setTime(new Date().toString());
		return Response.ok(dto).build();
	}
	
	@DELETE
	@Produces("application/json")
	@Path("/{key}")
	public Response delete(@PathParam("key") Integer key)
	{
		String result = SampleTable.delete(key);
		if (result == null)
			return Response.status(Status.NOT_FOUND).build();
		
		
		TestServiceDTO dto = new TestServiceDTO();
		dto.setAction("DELETE");
		dto.setKey(key);
		dto.setValue(result);
		dto.setTime(new Date().toString());
		///MediaType.APPLICATION_JSON
		return Response.ok(dto).build();
	}
	
	public static class TestServiceDTO
	{
		private String action;
		private Integer key;
		private String value;
		private String time;

		public Integer getKey() {
			return key;
		}

		public void setKey(Integer key) {
			this.key = key;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}
		
		
	}
}
