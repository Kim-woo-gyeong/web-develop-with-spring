package org.example.mvc.view;

public class JspViewResolver implements ViewResolver{
    private static final String DEFAUT_PREFIX_REDIRECT = "redirect:/";

    @Override
    public View resolverView(String viewName) {
        if(viewName.startsWith(DEFAUT_PREFIX_REDIRECT)){
            return new RedirectView(viewName);
        }
        return new JspView(viewName + ".jsp");
    }
}
