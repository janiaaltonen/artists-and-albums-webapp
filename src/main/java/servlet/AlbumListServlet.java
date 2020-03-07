package servlet;

import db.AlbumDao;
import model.Album;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long artistId = Long.parseLong(req.getParameter("artistId"));
        List<Album> albumList = albumDao.getAllAlbums(artistId);
        req.setAttribute("albumList", albumList);
        req.getRequestDispatcher("/WEB-INF/albums.jsp").forward(req, resp);
    }
}
