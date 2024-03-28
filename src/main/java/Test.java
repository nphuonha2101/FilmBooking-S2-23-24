/*
 *  @created 13/01/2024 - 8:06 PM
 *  @project FilmBooking-WebProgramming
 *  @author nphuonha
 */

import com.filmbooking.dao.DataAccessObjects;
import com.filmbooking.dao.IDAO;
import com.filmbooking.dao.daoDecorators.OffsetDAODecorator;
import com.filmbooking.dao.daoDecorators.PredicatesDAODecorator;
import com.filmbooking.email.AbstractSendEmail;
import com.filmbooking.email.SendResetPasswordEmail;
import com.filmbooking.enumsAndConstants.enums.LanguageEnum;
import com.filmbooking.hibernate.HibernateSessionProvider;
import com.filmbooking.model.Film;
import com.filmbooking.model.User;
import com.filmbooking.utils.GSONUtils;
import jakarta.persistence.criteria.Predicate;

public class Test {
    public static void main(String[] args) {
        AbstractSendEmail resetEmail = new SendResetPasswordEmail();
        resetEmail
                .loadHTMLEmail(LanguageEnum.ENGLISH)
                .putEmailInfo("userFullName", "Nguyen Phuong Nha")
                .loadEmailContent();


//        System.out.println(htmls);
        HibernateSessionProvider hibernateSessionProvider = new HibernateSessionProvider();

        IDAO<Film> filmDAO = new DataAccessObjects<>(Film.class);
        filmDAO.setSessionProvider(hibernateSessionProvider);
        IDAO<Film> filmDAOPredicate = new PredicatesDAODecorator<>(new OffsetDAODecorator<>(filmDAO, 0, 5), (criteriaBuilder, rootEntry) -> {
            Predicate predicate = criteriaBuilder.greaterThan(rootEntry.get("filmPrice"), 100000);
            return predicate;
        });

        IDAO< User> userDAO = new DataAccessObjects<>(User.class);
        userDAO.setSessionProvider(hibernateSessionProvider);

        System.out.println(filmDAOPredicate
                .getAll()
                .getMultipleResults().size());

        User user = userDAO.getByID("nphuonha", false);
        System.out.println(GSONUtils.getGson().toJson(user));

        hibernateSessionProvider.closeSession();


    }
}
