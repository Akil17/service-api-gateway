package com.akil.services.apigateway.youtube_api;

import com.akil.services.apigateway.commons.models.ApiResponse;
import com.akil.services.apigateway.commons.models.ApiStatus;
import com.assignment.services.youtube_api.*;
import com.akil.services.apigateway.core.ResourceManager;
import com.akil.services.apigateway.youtube_api.models.SearchResponsePayload;
import com.akil.services.apigateway.youtube_api.models.VideoSearchResponsePayload;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * author:  akil vohra
 * created: 19.06.2021
 */

@Slf4j
@Path("/youtube-api")
@Produces(MediaType.APPLICATION_JSON)
public class YoutubeApi {

    @GET
    @Path("/search")
    public ApiResponse<SearchResponsePayload> search(@QueryParam("type") String type,
                                                     @QueryParam("q") String query,
                                                     @QueryParam("page") int page,
                                                     @QueryParam("limit") int limit) {
        log.info("[REQ] Search API");
        ApiResponse<SearchResponsePayload> result = new ApiResponse<>();
        try {
            SearchResultType searchResultType;
            switch (type) {
                case "video":
                    searchResultType = SearchResultType.video;
                    break;
                case "video_tags":
                    searchResultType = SearchResultType.video_tags;
                    break;
                default:
                    result.setStatus(ApiStatus.UNPROCESSABLE_ENTITY);
                    return result;
            }
            SearchFilter.Builder searchFilterBuilder = SearchFilter.newBuilder();
            searchFilterBuilder = searchFilterBuilder.setType(searchResultType);
            switch (searchResultType) {
                case video:
                    VideoFilter videoFilter = VideoFilter.newBuilder()
                            .build();
                    searchFilterBuilder.setVideoFilter(videoFilter);
                    break;
                case video_tags:
                    break;
                default:
                    result.setStatus(ApiStatus.UNPROCESSABLE_ENTITY);
                    return result;
            }
            PaginationInfo paginationInfo = PaginationInfo.newBuilder()
                    .setPageNo(page)
                    .setLimit(limit)
                    .build();
            SearchRequest request = SearchRequest.newBuilder()
                    .setFilter(searchFilterBuilder.build())
                    .setQuery(query)
                    .setPaginationInfo(paginationInfo)
                    .build();
            SearchResponse response = ResourceManager.getInstance().getYoutubeApiServiceBlocking().search(request);
            if (response.hasError()) {
                return new ApiResponse(ApiStatus.INTERNAL_ERROR);
            }
            SearchResponsePayload payload;
            SearchResult searchResult = response.getResult();
            switch (searchResult.getType()) {
                case video:
                    payload = new VideoSearchResponsePayload(searchResult.getVideoSearchResult());
                break;
                default:
                    result.setStatus(ApiStatus.UNPROCESSABLE_ENTITY);
                    return result;
            }
            payload.setType(searchResult.getType().name());
            result.setStatus(ApiStatus.OK);
            result.setPayload(payload);
            log.info("[RES] SUCCESS Search API");
        } catch (Exception e) {
            log.error("[RES] ERROR Search API", e);
            result = new ApiResponse(ApiStatus.INTERNAL_ERROR);
        }
        return result;
    }
}
