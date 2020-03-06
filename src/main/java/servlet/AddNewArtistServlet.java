package servlet;

import db.ArtistDao;
import model.Artist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addNewArtist")
public class AddNewArtistServlet extends HttpServlet {
    ArtistDao artistDao = new ArtistDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        long defaultId = 1;
        long defaultNumber = 1;
        Artist newArtist = new Artist(defaultId, name, defaultNumber);

        boolean successful = artistDao.addNewArtist(newArtist);
        if(successful) {
            resp.sendRedirect("/artistList");
        }
    }
}
