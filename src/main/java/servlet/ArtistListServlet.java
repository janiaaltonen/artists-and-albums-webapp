package servlet;

import java.io.IOException;
import java.util.List;

import db.ArtistDao;
import model.Artist;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/artistList")
public class ArtistListServlet extends HttpServlet {
    ArtistDao artistDao = new ArtistDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Artist> artistList = artistDao.getAllArtists();

        // passing the artist list as an attribute
        // forward the request to the artists.jsp page
        req.setAttribute("artistList", artistList);
        req.getRequestDispatcher("/WEB-INF/artists.jsp").forward(req, resp);
    }
}
