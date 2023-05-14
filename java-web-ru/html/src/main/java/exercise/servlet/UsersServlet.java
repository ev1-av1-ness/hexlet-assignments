package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.User;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List<User> getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        String usersString = Files.readString(Path.of("src/main/resources/users.json"));
        return new ObjectMapper().readValue(usersString, new TypeReference<>(){});
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<User> users = getUsers();
        StringBuilder usersTable = new StringBuilder();
        usersTable.append("""
                <!DOCTYPE html>
                                <html lang="ru">
                                    <head>
                                        <meta charset="UTF-8">
                                        <title>Example application | Users</title>
                                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
                                        rel="stylesheet"
                                        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
                                        crossorigin="anonymous">
                                    </head>
                                    <body>
                                    <table>
                                      <thead>
                                        <tr>
                                          <th>id</th>
                                          <th>fullName</th>
                                        </tr>
                                      </thead>
                                      <tbody>
                """
        );

        for (User user : users) {
            usersTable.append("<tr>")
                    .append("<td>").append(user.getId()).append("</td>")
                    .append("<td>").append("<a href=\"/users/")
                    .append(user.getId()).append("\">")
                    .append(user.getFirstName())
                    .append(" ")
                    .append(user.getLastName())
                    .append("</a>")
                    .append("</td>")
                    .append("</tr>");
        }

        usersTable.append("""
                                    </tbody>
                                 </table>
                                 </body>
                </html>
                """
        );
        response.getWriter().println(usersTable);
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        User user = getUsers().stream()
                .filter(u -> u.getId().equals(id))
                .findAny()
                .orElse(null);
        if (user == null) {
            response.sendError(404);
        } else {
            StringBuilder usersTable = new StringBuilder();
            usersTable.append("""
                    <!DOCTYPE html>
                                    <html lang="ru">
                                        <head>
                                            <meta charset="UTF-8">
                                            <title>Example application | Users</title>
                                            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
                                            rel="stylesheet"
                                            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
                                            crossorigin="anonymous">
                                        </head>
                                        <body>
                                        <table>
                                          <thead>
                                            <tr>
                                              <th>id</th>
                                              <th>firstName</th>
                                              <th>lastName</th>
                                              <th>email</th>
                                            </tr>
                                          </thead>
                                          <tbody>
                    """
            )
                    .append("<tr>" + "<td>")
                    .append(user.getId())
                    .append("</td>")
                    .append("<td>")
                    .append(user.getFirstName())
                    .append("</td>")
                    .append("<td>")
                    .append(user.getLastName())
                    .append("</td>")
                    .append("<td>")
                    .append(user.getEmail())
                    .append("</td>")
                    .append("</tr>");
            response.getWriter().println(usersTable);
        }
        // END
    }
}
