package com.coursemanagement.src.controllers.responsebuilders;

import jakarta.ws.rs.core.Response;

public interface ResponseOKOptional {
    Response.ResponseBuilder applyOption(Response.ResponseBuilder responseBuilder);

}
