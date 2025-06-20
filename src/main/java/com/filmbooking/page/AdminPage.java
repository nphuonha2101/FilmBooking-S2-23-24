package com.filmbooking.page;

import com.filmbooking.utils.WebAppPathUtils;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author nphuo
 * @ide IntelliJ IDEA
 * @project_name FilmBooking-S2-23-24
 */
@NoArgsConstructor
public class AdminPage extends Page {

    public AdminPage(String pageTitle, String pageName, String layout) {
        super(pageTitle, layout);
        this.page = WebAppPathUtils.getAdminPagesPath(pageName + ".jsp");
    }

    public AdminPage(String pageTitle, String pageName, String layout, Map<String, Object> pageAttributes) {
        super(pageTitle, layout, pageAttributes);
        this.page = WebAppPathUtils.getAdminPagesPath(pageName + ".jsp");
    }

    @Override
    public void setPage(String page) {
        this.page = WebAppPathUtils.getAdminPagesPath(page + ".jsp");
    }
}
