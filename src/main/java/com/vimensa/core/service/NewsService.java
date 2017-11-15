package com.vimensa.core.service;

import com.vimensa.apis.responses.GetNewsResponse;

import java.sql.SQLException;

public interface NewsService {
    public GetNewsResponse getListNews(int start, int offset) throws SQLException;
}
