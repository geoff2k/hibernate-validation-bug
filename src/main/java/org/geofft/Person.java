package org.geofft;

import org.geofft.constraint.ValidCustomer;

/**
 * Person has a Warning payload for the composed "ValidCustomer" constraint
 */
@ValidCustomer(payload = Customer.Warning.class)
public class Person extends Customer {

    public Person(String name) {
        super(name);
    }

}
