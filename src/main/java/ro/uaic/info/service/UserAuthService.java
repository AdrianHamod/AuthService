package ro.uaic.info.service;

import jakarta.annotation.Resource;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

@Path("auth")
public class UserAuthService {

    @GET
    public String hello() throws NamingException, SQLException {
        InitialContext ctx = new InitialContext();
        DataSource dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/postgresqldb");
        return dataSource.getConnection().toString();
    }
}
