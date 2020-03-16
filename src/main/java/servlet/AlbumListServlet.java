package servlet;

import db.AlbumDao;
import db.ArtistDao;
import model.Album;
import model.Artist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/albums")
public class AlbumListServlet extends HttpServlet {
    AlbumDao albumDao = new AlbumDao();
    ArtistDao artistDao = new ArtistDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long artistId = Long.parseLong(req.getParameter("artistId"));
        List<Album> albumList = albumDao.getAllAlbums(artistId);
        Artist artist = artistDao.getArtist(artistId);
        req.setAttribute("albumList", albumList);
        req.setAttribute("artist", artist);
        req.getRequestDispatcher("/WEB-INF/albums.jsp").forward(req, resp);
    }
}
