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
    @Autowired
    private JdbcTemplate jdbcTemplate;

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

        /*

        // query()
        out.print("sql? ");
        String sql = in.nextLine();
        out.print("***\n"+query(sql)+"****\n");

        // index()
        out.println(index());

        */

        findAll().forEach(out::println);
    }

    public String index() {
        String response = "";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from users_login");
        while (sqlRowSet.next()) {
            response += sqlRowSet.getString("user_name") + "\n";
        }
        return response;
    }

    public String query(String sql) {
        String response = "";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql);
        while (sqlRowSet.next()) {
            response += sqlRowSet.getString(1) + "\n";
        }
        return response;
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