package com.coursemanagement.src.controllers.responsebuilders;

import jakarta.ws.rs.core.Response;

public class ResponseOK {

    public static Response buildResponse(Response.Status status, Object obj) {
        return Response.status(status).entity(obj).build();
    }

}
