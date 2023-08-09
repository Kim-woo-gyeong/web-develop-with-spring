package org.example.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class RedirectView implements View {
    private final String viewName;
    private static final String DEFAULT_REDIRECT_PREIFX = "redirect:";
    public RedirectView(String viewName) {
        this.viewName = viewName;
    }
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        model.forEach(request::setAttribute);
        response.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREIFX.length()));
    }
}
