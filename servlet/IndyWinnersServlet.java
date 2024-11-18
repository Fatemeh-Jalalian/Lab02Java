package servlet;

import dao.IndyWinnerDAO;
import model.IndyWinner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/IndyWinners")
public class IndyWinnersServlet extends HttpServlet {
    private final IndyWinnerDAO indyWinnerDAO = new IndyWinnerDAO();
    private static final int WINNERS_PER_PAGE = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        int offset = (page - 1) * WINNERS_PER_PAGE;

        List<IndyWinner> winners = indyWinnerDAO.getWinners(offset, WINNERS_PER_PAGE);

        out.println("<html>");
        out.println("<head><title>Indy Winners</title></head>");
        out.println("<body>");
        out.println("<h1>Indy Winners</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>Year</th><th>Driver</th><th>Average Speed</th><th>Country</th></tr>");

        for (IndyWinner winner : winners) {
            out.println("<tr>");
            out.println("<td>" + winner.getYear() + "</td>");
            out.println("<td>" + winner.getDriver() + "</td>");
            out.println("<td>" + winner.getAverageSpeed() + "</td>");
            out.println("<td>" + winner.getCountry() + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");

        if (page > 1) {
            out.println("<a href='IndyWinners?page=" + (page - 1) + "'>Previous</a>");
        }
        if (winners.size() == WINNERS_PER_PAGE) {
            out.println("<a href='IndyWinners?page=" + (page + 1) + "'>Next</a>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
