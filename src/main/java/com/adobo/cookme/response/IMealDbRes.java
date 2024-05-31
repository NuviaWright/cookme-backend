package com.adobo.cookme.response;

import java.util.List;

public interface IMealDbRes<T> {
    List<T> getMeals();
    void setMeals(List<T> meals);
}
