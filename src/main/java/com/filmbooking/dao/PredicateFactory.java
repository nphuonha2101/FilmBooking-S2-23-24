package com.filmbooking.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

/**
 * <ul>
 * <li>This class is used to create a predicate for the query</li>
 * <li>This class is used to create a predicate from a map of conditions</li>
 * <li>The key of the map must be in the format: field_operator</li>
 * <li>For example: Entry(name_like, "Nha"): "name" is the object attribute, "like" is the operator
 * and "Nha" is value</li>
 * </ul>
 *
 * List of operators that can be used and their meanings:
 * <ul>
 *     <li>= : equal</li>
 *     <li><> : not equal</li>
 *     <li>< : less than</li>
 *     <li>>: greater than</li>
 *     <li><= : less than or equal</li>
 *     <li>>= : greater than or equal</li>
 *     <li>like : like (used for string type)</li>
 *     <li>in : in a list of value</li>
 *     <li>notin : not in a list of value</li>
 * </ul>
 */
public class PredicateFactory {
    private Map<String, Object> conditions;
    private CriteriaBuilder criteriaBuilder;
    private Root root;

    public PredicateFactory(Map<String, Object> conditions, CriteriaBuilder criteriaBuilder, Root root) {
        this.conditions = conditions;
        this.criteriaBuilder = criteriaBuilder;
        this.root = root;
    }

    /**
     * Create a predicate from a map of conditions
     * <br>
     * The key of the map must be in the format: field_operator
     * <br>
     * For example: name_like
     *
     * @return the predicate
     */
    public Predicate createPredicate() {
        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> entry : conditions.entrySet()) {
            Predicate predicate = createPredicate(entry.getKey(), entry.getValue());
            predicates.add(predicate);
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


    /**
     * Create a predicate from a key-value pair
     * <br>
     * The key must be in the format: field_operator
     * <br>
     * For example: name_like
     *
     * @param key   the key of the condition in the format: field_operator
     * @param value the value of the condition
     * @return the predicate
     */
    @SuppressWarnings("unchecked")
    private Predicate createPredicate(String key, Object value) {
        StringTokenizer tokenizer = new StringTokenizer(key, "_");
        String field = "";
        String operator = "";

        if (tokenizer.countTokens() == 2) {
            field = tokenizer.nextToken();
            operator = tokenizer.nextToken();
        } else {
            throw new RuntimeException("Invalid key format. Key must be in the format: field_operator");
        }

        Predicate predicate;
        switch (operator) {
            case "=":
                predicate = criteriaBuilder.equal(root.get(field), value);
                break;
            case "<>":
                predicate = criteriaBuilder.notEqual(root.get(field), value);
                break;
            case "<":
                predicate = criteriaBuilder.lessThan(root.get(field), (Comparable) value);
                break;
            case "le":
                predicate = criteriaBuilder.lessThanOrEqualTo(root.get(field), (Comparable) value);
                break;
            case "gt":
                predicate = criteriaBuilder.greaterThan(root.get(field), (Comparable) value);
                break;
            case "ge":
                predicate = criteriaBuilder.greaterThanOrEqualTo(root.get(field), (Comparable) value);
                break;
            case "like":
                predicate = criteriaBuilder.like(root.get(field), "%" + value + "%");
                break;
            case "in": {
                Expression expression = root.get(field);
                predicate = expression.in(value);
                break;
            }
            case "notin": {
                Expression expression = root.get(field);
                predicate = expression.in(value).not();
                break;
            }
            default:
                throw new RuntimeException("Invalid operator: " + operator);
        }
        return predicate;
    }
}
