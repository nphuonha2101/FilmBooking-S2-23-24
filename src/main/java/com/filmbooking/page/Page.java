package com.filmbooking.page;

import com.filmbooking.utils.WebAppPathUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */

@Getter
@Setter
public abstract class Page {
    public static final String DEFAULT_LAYOUT = "master";

    protected String pageTitle;
    protected String page;
    protected String layout;
    protected Map<String, Object> pageAttributes;
    protected String customStyleSheet;

    public Page() {
        this.pageAttributes = new HashMap<>();
    }

    public Page(String pageTitle, String layout, Map<String, Object> pageAttributes) {
        this.pageTitle = pageTitle;
        this.layout = WebAppPathUtils.getLayoutPath(layout + ".jsp");
        this.pageAttributes = pageAttributes;
    }

    public Page(String pageTitle, String layout) {
        this(pageTitle, layout, new HashMap<>());
    }

    /**
     * Put an attribute to the page
     *
     * @param key   attribute key
     * @param value attribute value
     */
    public void putAttribute(String key, Object value) {
        this.pageAttributes.put(key, value);
    }

    /**
     * Set custom style sheet for the page
     *
     * @param customStyleSheet custom style sheet
     */

    public void setCustomStyleSheet(String customStyleSheet) {
        this.customStyleSheet = WebAppPathUtils.getStyleSheetsPath(customStyleSheet);
    }

    /**
     * Set page's attributes and render the page to the layout
     * @param req request to set attribute to the page
     * @param resp response to forward request
     */
    public void render(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("pageTitle", this.pageTitle);
        this.pageAttributes.forEach(req::setAttribute);

        this.renderViewToLayout(req, resp, this.page, this.layout);
    }

    /**
     * Put an error status code to the page
     * @param statusCodeErr error status code in {@link com.filmbooking.enumsAndConstants.enums.StatusCodeEnum}
     */
    public void putError(int statusCodeErr) {
        this.putAttribute("statusCodeErr", statusCodeErr);
    }

    /**
     * Put a success status code to the page
     * @param statusCodeSuccess success status code in {@link com.filmbooking.enumsAndConstants.enums.StatusCodeEnum}
     */
    public void putSuccess(int statusCodeSuccess) {
        this.putAttribute("statusCodeSuccess", statusCodeSuccess);
    }

    /**
     * Render a view to a layout by setting dynamicContents as a viewPath to the layoutPath
     *
     * @param request    used to set attribute and used to forward request
     * @param response   used to forward request
     * @param viewPath   a view path
     * @param layoutPath a layout path
     */
    private void renderViewToLayout(HttpServletRequest request, HttpServletResponse response, String viewPath, String layoutPath) {
        request.setAttribute("dynamicContents", viewPath);
        try {
            request.getRequestDispatcher(layoutPath).forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
