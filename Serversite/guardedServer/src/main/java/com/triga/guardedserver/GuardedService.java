/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.triga.guardedserver;

import java.util.List;
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
    
    
    //add a AppUser to database.
    //URL: http://localhost:8080/guardedServer/services/guarded/add/appuser?firstName=Gard&lastName=Engen&password=123&phoneNumber=41761114&familyCode=skjer&guardian=true
    @GET
    @Path("add/appuser")
    public AppUser addAppUser(
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName,
            @QueryParam("password") String password,
            @QueryParam("phoneNumber") Integer phoneNumber,
            @QueryParam("familyCode") String familyCode,
            @QueryParam("guardian") Boolean guardian)
            
    {
    AppUser appUser = new AppUser(firstName,lastName,password,phoneNumber,familyCode,guardian);
       em.persist(appUser);
    return appUser;
    }
    
    //returns all AppUsers in database
    @GET
    @Path("getall/appusers")
    public List<AppUser> getAllAppUsers() {
        List<AppUser> list = em.createQuery("SELECT a from AppUser a").getResultList();
        return list;
    } 
            
          
}
