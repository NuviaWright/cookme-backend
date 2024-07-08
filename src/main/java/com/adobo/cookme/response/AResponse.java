package com.adobo.cookme.response;

import java.util.List;

public abstract class AResponse<T> {
    protected List<T> meals;

    public abstract List<T> getMeals();
    public abstract void setMeals(List<T> meals);

    public abstract void setSize(int size);
}
