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
public class ClientPage extends Page {
    public ClientPage(String pageTitle, String pageName, String layout, Map<String, Object> pageAttributes) {
        super(pageTitle, layout, pageAttributes);
        this.page = WebAppPathUtils.getClientPagesPath(pageName + ".jsp");
    }

    public ClientPage(String pageTitle, String pageName, String layout) {
        super(pageTitle, layout);
        this.page = WebAppPathUtils.getClientPagesPath(pageName + ".jsp");
    }
}
