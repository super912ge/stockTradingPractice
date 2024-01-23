package org.acme.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.data.Account;
import org.acme.model.OrderRequest;
import org.acme.model.OrderOut;

@Path("account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AccountResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Account getAccount();

    @POST
    @Path("create")
    void createAccount();

    @POST
    @Path("order")
    OrderOut createOrder(OrderRequest order);
}
