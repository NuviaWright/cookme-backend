package com.adobo.cookme.response;

import java.util.List;

public interface PaginatedResponse {
    int getSize();
    List<?> getPaginatedList(int start, int end);
}
