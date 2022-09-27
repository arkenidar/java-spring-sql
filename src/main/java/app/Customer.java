package app;

import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;

public class Customer {
    private final long id;
    private final String name;
    private final int age;
    private final LocalDateTime created_date;

    public Customer(long id, String name, int age, LocalDateTime created_date) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.created_date = created_date;
    }

    public static RowMapper<Customer> getRowMapper() {
        return (rs, rowNum) ->
                new Customer(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getTimestamp("created_date").toLocalDateTime()
                );
    }

    @Override
    public String toString() {
        return "app.Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", created_date=" + created_date +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public long getId() {
        return id;
    }
}
