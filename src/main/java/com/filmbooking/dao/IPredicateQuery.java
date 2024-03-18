/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

package com.filmbooking.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;

/**
 * This interface is used to create a predicate for the query
 * <br>
 * For example, to create a predicate for a query to get all records with a specific name:
 * <br>
 * <hr>
 * <code>
 * createPredicate(CriteriaBuilder criteriaBuilder, Root root) {
 * <br>
 * return criteriaBuilder.equal(root.get("name"), "John");
 * <br>
 * }
 * </code>
 */
@FunctionalInterface
public interface IPredicateQuery {
    Predicate createPredicate(CriteriaBuilder criteriaBuilder, Root root);
}
