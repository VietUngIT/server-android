package com.vimensa.apis.handlers;

import com.vimensa.apis.responses._BaseResponse;
import com.vimensa.core.service.NewsService;
import com.vimensa.core.serviceimp.NewsServiceImp;
import io.vertx.core.http.HttpServerRequest;

public class GetNewsHandler extends _BaseApiHandler {
    private NewsService newsService = new NewsServiceImp();
    @Override
    public _BaseResponse handle(HttpServerRequest request) throws Exception {
        int start = Integer.parseInt(request.getParam("start"));
        int offset = Integer.parseInt(request.getParam("offset"));
        _BaseResponse response = newsService.getListNews(start,offset);
        return response;
    }
}
