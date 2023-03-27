package com.clients.restapi.models.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class ClientFilter {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    public static Specification<Client> getSpecification(String firstName, String lastName, String email, Long regionId, Date createAt){
		return ( (root,query,cb) -> {
			
			List<Predicate> predicates = new ArrayList<>();
	        if (firstName!=null)
	        {
	            predicates.add(cb.equal(root.get("firstName"), firstName));
	        }
	        if (lastName!=null)
	        {
	            predicates.add(cb.equal(root.get("lastName"), lastName));
	        }
	        if (email!=null)
	        {
	            predicates.add(cb.equal(root.get("email"), email));
	        }
	        if (regionId!=null)
	        {
	            predicates.add(cb.equal(root.join("region").get("id"), regionId));
	        }
	        if (createAt!=null)
	        {
	            predicates.add(cb.equal(root.get("createAt"), createAt));
	        }
	        return  cb.and(predicates.toArray(new Predicate[0]));
		});
		
		
    	
    }


}
