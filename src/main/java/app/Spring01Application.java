package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;


/*
@SpringBootApplication
public class app.Spring01Application {
    public static void main(String[] args) {
        SpringApplication.run(app.Spring01Application.class, args);
    }
}
*/
@SpringBootApplication
public class Spring01Application
        implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory
            .getLogger(Spring01Application.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Spring01Application(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(Spring01Application.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");

        // mvn package && java -jar target/*.jar arg1 arg2
        for (int i = 0; i < args.length; ++i) {
            LOG.info("args[{}]: {}", i, args[i]);
        }

        PrintStream out = System.out;
        Scanner in = new Scanner(System.in);

        // findAll()
        findAll().forEach(out::println);

        // index()
        out.println(index());

        // query()
        out.print("sql? ");
        String sql = in.nextLine();
        out.print("***\n" + query(sql) + "****\n");
    }

    public String index() {
        StringBuilder response = new StringBuilder();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from users_login");
        while (sqlRowSet.next()) {
            response.append(sqlRowSet.getString("user_name")).append("\n");
        }
        return response.toString();
    }

    public String query(String sql) {
        StringBuilder response = new StringBuilder();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql);
        while (sqlRowSet.next()) {
            response.append(sqlRowSet.getString(1)).append("\n");
        }
        return response.toString();
    }

    // https://mkyong.com/spring/spring-jdbctemplate-querying-examples/
    public List<Customer> findAll() {

        String sql = "select * from \"Customer\" ";

        return jdbcTemplate.query( // or queryForObject()
                sql,
                Customer.getRowMapper()
        );
    }
}