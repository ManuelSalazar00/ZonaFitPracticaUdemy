package zona_fit.domain;

import java.util.Objects;

public class Customer {
    private long id;
    private String name;
    private String lastName;
    private int membership;

    public Customer() {
    }

    public Customer(long id) {
        this.id = id;
    }

    public Customer(String name, String lastName, int membership) {
        this.name = name;
        this.lastName = lastName;
        this.membership = membership;
    }

    public Customer(int id, String name, String lastName, int membership) {
        this(name, lastName, membership);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMembership() {
        return membership;
    }

    public void setMembership(int membership) {
        this.membership = membership;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", membership='" + membership + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer client = (Customer) o;
        return id == client.id
                && Objects.equals(name, client.name)
                && Objects.equals(lastName, client.lastName)
                && Objects.equals(membership, client.membership);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, membership);
    }
}
