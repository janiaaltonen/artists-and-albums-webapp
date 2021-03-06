package servlet;

import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;

import db.ArtistDao;
import model.Artist;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/artists")
public class ArtistListServlet extends HttpServlet {
    ArtistDao artistDao = new ArtistDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Artist> artistList = artistDao.getAllArtistsWithAlbums();

        // passing the artist list as an attribute
        // forward the request to the artists.jsp page
        req.setAttribute("artistList", artistList);
        req.getRequestDispatcher("/WEB-INF/artists.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        // mysql db table has auto-increment for ArtistId, so it doesn't matter what ArtistId we use
        long defaultId = 1;
        // number is not added to db, so we use default value 1
        long defaultNumber = 1;
        int defaultAlbums = 0;
        Artist newArtist = new Artist(defaultId, name, defaultNumber, defaultAlbums);

        boolean successful = artistDao.addNewArtist(newArtist);
        if (successful) {
            resp.sendRedirect("/artists");
        } // else part need to show something like couldn't add artist cause already in list
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Artist artist = artistDao.removeArtist(id);
        if(artist != null) {
            String json = new Gson().toJson(artist);
            resp.setContentType("application/json; charset=UTF-8");
            resp.getWriter().println(json);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().print("not found");
        }
    }
}
