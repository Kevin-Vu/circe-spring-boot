package com.okayo.facture.configuration;

import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.List;

public class OkayoContentNegotiationStrategy implements ContentNegotiationStrategy {

    /**
     * Custom media type for the response
     * @param nativeWebRequest : nativeWebRequest
     * @return : list of media type
     */
    @Override
    public List<MediaType> resolveMediaTypes(NativeWebRequest nativeWebRequest) {
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.valueOf("text/csv; charset=utf-8"));
        return mediaTypes;
    }

}
