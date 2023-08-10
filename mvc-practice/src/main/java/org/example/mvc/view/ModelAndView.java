package org.example.mvc.view;

public class ModelAndView {
    private final String viewName;
    public ModelAndView(String viewName){

        this.viewName = viewName;
    }

    public String getViewName() {
        return this.viewName;
    }
}
