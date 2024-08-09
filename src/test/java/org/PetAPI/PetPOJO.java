package org.PetAPI;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PetPOJO {
    private int id;
    private category category;
    private String name;
    private List<String> photoUrls;
    private List<tags> tags;
    private String status;

    @Data
    @AllArgsConstructor
    @Builder
    public static class category{
        private int id;
        private String name;
    }
    @Data
    @AllArgsConstructor
    @Builder
    public static class tags{
        private int id;
        private String name;
    }
}
