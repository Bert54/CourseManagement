package com.coursemanagement.src.controllers.responsebuilders;

import jakarta.ws.rs.core.Response;

public class ResponseOK {

    public static Response buildResponse(Response.Status status, Object obj, ResponseOKOptional... responseOKOptionals) {
        Response.ResponseBuilder responseBuilder = Response.status(status).entity(obj);
        for (ResponseOKOptional responseOKOptional : responseOKOptionals) {
            responseBuilder = responseOKOptional.applyOption(responseBuilder);
        }

        return responseBuilder.build();
    }

}
