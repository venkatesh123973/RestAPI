package org.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@AllArgsConstructor
@Builder
public class JIRALombokPOJO {
  private fields Fields;
    @Data
    @AllArgsConstructor
    @Builder
    public static class fields{
      private project project;
      private String summary;
      private issuetype issuetype;
    }
    @Data
    @AllArgsConstructor
    @Builder
    public static class project{
        private String id;
    }
    @Data
    @AllArgsConstructor
    @Builder
    public static class issuetype{
        private String id;
    }

}
