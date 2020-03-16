package servlet;

import com.google.gson.Gson;
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


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Album album = albumDao.removeAlbum(id);
        if(album != null) {
            String json = new Gson().toJson(album);
            resp.setContentType("application/json; charset=UTF-8");
            resp.getWriter().println(json);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().print("not found");
        }
    }
}
