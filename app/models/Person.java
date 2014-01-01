package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Entity
public class Person extends Model
{
    private static Finder<Long, Person> FIND = new Finder<>(Long.class,
                                                            Person.class);

    @Id
    public Long id;

    public String name;

    public static List<Person> getAll()
    {
        return FIND.all();
    }

    public static Person getById(Long id)
    {
        return FIND.byId(id);
    }
}
