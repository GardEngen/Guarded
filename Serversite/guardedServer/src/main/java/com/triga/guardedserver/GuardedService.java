/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.triga.guardedserver;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Gard
 * TODO VMDeploy https://autodeploy.uials.no/
 */
@Path("guarded")
@Produces("application/json")
@Stateless
public class GuardedService {
    @PersistenceContext
    EntityManager em;
    
    
    
    @GET
    @Path("add/appuser")
    public AppUser addAppUser(
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName,
            @QueryParam("password") String password,
            @QueryParam("phonenumber") Integer phonenumber,
            @QueryParam("familyCode") String familyCode,
            @QueryParam("guardian") Boolean guardian)
    {
    AppUser appUser = new AppUser(firstName,lastName,password,phonenumber,familyCode,guardian);
       em.persist(appUser);
    return appUser;
    }
}
