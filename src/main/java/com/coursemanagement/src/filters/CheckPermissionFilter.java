package com.coursemanagement.src.filters;

import com.coursemanagement.src.bindings.CheckPermission;
import com.coursemanagement.src.controllers.responsebuilders.ResponseError;
import com.coursemanagement.src.entities.people.Person;
import com.coursemanagement.src.managers.PeopleManager.PeopleManager;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.List;

@Provider
@CheckPermission
@Priority(Priorities.AUTHENTICATION)
public class CheckPermissionFilter implements ContainerRequestFilter {

    @Inject
    private PeopleManager peopleManager;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String userIDStr = requestContext.getHeaderString("X-UserID");
        if (userIDStr == null || userIDStr.isEmpty()) {
            requestContext.abortWith(
                    ResponseError.buildResponse(Response.Status.UNAUTHORIZED,
                            new Exception("Missing 'X-UserID' header"))
            );

            return;
        }


        int userID;

        try {
            userID = Integer.parseInt(userIDStr);
        } catch (NumberFormatException e) {
            requestContext.abortWith(
                    ResponseError.buildResponse(Response.Status.BAD_REQUEST,
                            new IllegalArgumentException(
                                    String.format("'%s' is not a valid id", userIDStr))
                    )
            );
            return;
        }

        Person person;
        try {
            person = this.peopleManager.getPersonById(userID);
        } catch (Exception e) {
            requestContext.abortWith(
                    ResponseError.buildResponse(Response.Status.NOT_FOUND, e)
            );

            return;
        }

        Method method = this.resourceInfo.getResourceMethod();
        if (method == null) {
            requestContext.abortWith(
                    ResponseError.buildResponse(Response.Status.INTERNAL_SERVER_ERROR,
                            new Exception("'resourceInfo' context missing"))
            );

            return;
        }

        CheckPermission permissions = method.getAnnotation(CheckPermission.class);
        String permissionToCheck = permissions.permission();
        if (permissionToCheck.isEmpty()) {
            requestContext.abortWith(
                    ResponseError.buildResponse(Response.Status.INTERNAL_SERVER_ERROR,
                            new Exception("Permission to check is missing"))
            );

            return;
        }

        if (!person.getPermissions().contains(permissionToCheck)) {
            requestContext.abortWith(
                    ResponseError.buildResponse(Response.Status.FORBIDDEN,
                            new Exception("Missing permission to perform this operation"))
            );

            return;
        }

        SecurityContext originalContext = requestContext.getSecurityContext();
        Authorizer authorizer = new Authorizer(
                person.getPermissions(),
                person.getName(),
                userID,
                originalContext.isSecure());
        requestContext.setSecurityContext(authorizer);
    }

    private static class Authorizer implements SecurityContext {

        protected List<String> roles;

        protected int id;
        protected String username;
        protected boolean isSecure;

        public Authorizer(List<String> roles, final String username, int id, boolean isSecure) {
            this.roles = roles;
            this.username = username;
            this.isSecure = isSecure;
            this.id = id;
        }

        @Override
        public Principal getUserPrincipal() {
            return new UserInfo(this.id, this.username);
        }

        @Override
        public boolean isUserInRole(String role) {
            return roles.contains(role);
        }

        @Override
        public boolean isSecure() {
            return isSecure;
        }

        @Override
        public String getAuthenticationScheme() {
            return "Your Scheme";
        }
    }

}
