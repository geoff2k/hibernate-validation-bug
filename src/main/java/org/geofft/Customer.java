package org.geofft;

import org.geofft.constraint.ValidCustomer;

import javax.validation.Payload;
import javax.validation.constraints.NotNull;

@ValidCustomer
public class Customer {

    public static class Warning implements Payload {}
    public static class Error implements Payload {}

    private String name;
    private String address;

    @NotNull(payload = Customer.Error.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

