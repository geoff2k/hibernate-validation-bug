package org.geofft;

import org.geofft.constraint.ValidCustomer;

import javax.validation.Payload;
import javax.validation.constraints.NotNull;

/**
 * Customer has a ValidCustomer constraint with no payload
 */
@ValidCustomer
public class Customer {

    public static class Warning implements Payload {}
    public static class Error implements Payload {}

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @NotNull(payload = Customer.Warning.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

