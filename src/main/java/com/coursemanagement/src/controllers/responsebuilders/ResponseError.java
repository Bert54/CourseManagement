package com.coursemanagement.src.controllers.responsebuilders;

import jakarta.ws.rs.core.Response;

public class ResponseError {

    public record ErrorObj(int status, String message) {}
    public static Response buildResponse(Response.Status status, Exception e) {

        return Response.status(status).entity(
                new ErrorObj(
                        status.getStatusCode(), e.getMessage()
                )
        ).build();

    }

}
