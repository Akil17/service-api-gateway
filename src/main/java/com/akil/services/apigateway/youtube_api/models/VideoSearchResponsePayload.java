package com.akil.services.apigateway.youtube_api.models;

import com.assignment.services.youtube_api.VideoSearchResult;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * author:  akil vohra
 * created: 19.06.2021
 */

@JsonAutoDetect(
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE,
        fieldVisibility = JsonAutoDetect.Visibility.ANY
)
@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class VideoSearchResponsePayload extends SearchResponsePayload {

    private static final Function<com.assignment.services.youtube_api.Thumbnail, Thumbnail> thumbnailProtoToThumbnailPayload
            = thumbnail -> new Thumbnail(thumbnail.getUrl(), thumbnail.getHeight(), thumbnail.getWidth());

    private static final Function<com.assignment.services.youtube_api.Snippet, Snippet> snippetProtoToSnippetPayload = snippet -> {
        final Map<String, com.assignment.services.youtube_api.Thumbnail> thumbnailMap = snippet.getThumbnailsMap();
        final Map<String, Thumbnail> protoThumbnailMap = new HashMap<>();
        for(Map.Entry<String, com.assignment.services.youtube_api.Thumbnail> entry : thumbnailMap.entrySet()) {
            protoThumbnailMap.put(entry.getKey(), thumbnailProtoToThumbnailPayload.apply(entry.getValue()));
        }
        return new Snippet(
                snippet.getPublishedAt(),
                snippet.getChannelId(),
                snippet.getTitle(),
                snippet.getDescription(),
                protoThumbnailMap,
                snippet.getChannelTitle()
        );
    };

    private List<Snippet> videos;

    public VideoSearchResponsePayload(VideoSearchResult videoSearchResult) {
        this.videos = videoSearchResult.getVideosList().stream().map(snippetProtoToSnippetPayload).collect(Collectors.toList());
    }
}
