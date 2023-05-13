package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        List<String> companies;
        String parameter = request.getParameter("search");

        PrintWriter writer = response.getWriter();

        if (parameter == null) companies = getCompanies();
        else companies = getCompanies().stream()
                .filter(company -> company.contains(parameter))
                .toList();
        if (companies.isEmpty()) writer.println("Companies not found");
        companies.forEach(writer::println);
        // END
    }
}
