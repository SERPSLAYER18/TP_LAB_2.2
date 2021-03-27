package Hibernate.DAO;


import Hibernate.DataSets.UserData;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UserData get(long id) throws HibernateException {
        return (UserData) session.get(UserData.class, id);
    }

    public long getUserId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UserData.class);
        return ((UserData) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
    }
    public ArrayList<UserData> getAllUsers()
    {
        Criteria criteria = session.createCriteria(UserData.class);
        return (ArrayList<UserData>)criteria.list();
    }

    public void deleteUSer(UserData data)
    {
        session.delete(data);
    }

    public ArrayList<UserData> get(Criterion restrictions)
    {
        Criteria criteria = session.createCriteria(UserData.class);
        return (ArrayList<UserData>)criteria.add(restrictions).list();
    }

    public long insertUser(UserData user) throws HibernateException {
        return (Long) session.save(user);
    }
}
