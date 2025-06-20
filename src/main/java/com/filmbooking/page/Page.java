package com.filmbooking.page;

import com.filmbooking.utils.WebAppPathUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    protected List<String> customStyleSheets;

    public Page() {
        this.pageAttributes = new HashMap<>();
    }

    public Page(String pageTitle, String layout, Map<String, Object> pageAttributes) {
        this.pageTitle = pageTitle;
        this.layout = WebAppPathUtils.getLayoutPath(layout + ".jsp");
        this.pageAttributes = pageAttributes;
        this.customStyleSheets = new ArrayList<>();
    }

    public Page(String pageTitle, String layout) {
        this(pageTitle, layout, new HashMap<>());
        this.customStyleSheets = new ArrayList<>();
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
     * @param customStyleSheets custom style sheets
     */

    public void setCustomStyleSheets(List<String> customStyleSheets) {
        for (String customStyleSheet : customStyleSheets) {
            this.customStyleSheets.add(WebAppPathUtils.getStyleSheetsPath(customStyleSheet));
        }

        putAttribute("customStyleSheets", this.customStyleSheets);
    }

    /**
     * Set page's attributes and render the page to the layout
     *
     * @param req  request to set attribute to the page
     * @param resp response to forward request
     *             return {@link RequestDispatcher} to handle other task
     */
    public RequestDispatcher render(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("pageTitle", this.pageTitle);
        this.pageAttributes.forEach(req::setAttribute);

        return this.renderViewToLayout(req, resp, this.page, this.layout);
    }

    /**
     * Put an error status code to the page
     *
     * @param statusCodeErr error status code in {@link com.filmbooking.enumsAndConstants.enums.StatusCodeEnum}
     */
    public void putError(int statusCodeErr) {
        this.putAttribute("statusCodeErr", statusCodeErr);
    }

    /**
     * Put a success status code to the page
     *
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
     * return {@link RequestDispatcher} to handle other task
     */
    private RequestDispatcher renderViewToLayout(HttpServletRequest request, HttpServletResponse response, String viewPath, String layoutPath) {
        request.setAttribute("dynamicContents", viewPath);
        try {
            RequestDispatcher requestDispatcher =
                    request.getRequestDispatcher(layoutPath);
            requestDispatcher.forward(request, response);

            return requestDispatcher;
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
