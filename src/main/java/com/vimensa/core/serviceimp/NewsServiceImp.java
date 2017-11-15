package com.vimensa.core.serviceimp;

import com.vimensa.apis.responses.GetNewsResponse;
import com.vimensa.config.ErrorCode;
import com.vimensa.core.dao.InfoNews;
import com.vimensa.core.pools.HikariPool;
import com.vimensa.core.service.NewsService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewsServiceImp implements NewsService {
    @Override
    public GetNewsResponse getListNews(int start, int offset) throws SQLException {
        Connection connection = HikariPool.getConnection();
        GetNewsResponse response = new GetNewsResponse();

        ArrayList<InfoNews> array=null;
        try {
            String query = "SELECT * from tb_news WHERE id_news>? LIMIT ?";
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, start);
            st.setInt(2, offset);
            ResultSet rs = st.executeQuery();
            array = new ArrayList();
            while (rs.next()) {
                InfoNews infoNews = new InfoNews();
                infoNews.setId(rs.getInt("id_news"));
                infoNews.setTitle(rs.getString("title"));
                infoNews.setContent(rs.getString("content"));
                infoNews.setImage(rs.getString("image"));
                infoNews.setAuthor(rs.getString("author"));
                infoNews.setCreateTime(rs.getString("create_time"));
                array.add(infoNews);
            }
            response.setArray(array);
            st.cancel();
            st.close();
        } catch (Exception e) {
            response.setError(ErrorCode.SYSTEM_ERROR);
        } finally {
            connection.close();
        }
        return response;
    }
}
