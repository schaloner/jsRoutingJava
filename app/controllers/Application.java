package controllers;

import models.Person;
import org.codehaus.jackson.JsonNode;
import play.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;
import views.html.index;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class Application extends Controller
{
    public static Result index()
    {
        return ok(index.render("Your new application is ready."));
    }

    public static Result getAll()
    {
        return ok(Json.toJson(Person.getAll()));
    }

    public static Result delete(Long id)
    {
        Result result;
        Person person = Person.getById(id);
        if (person != null)
        {
            person.delete();
            result = ok(person.name + " deleted");
        } else
        {
            result = notFound(String.format("Person with ID [%d] not found", id));
        }
        return result;
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result create()
    {
        JsonNode json = request().body()
                                 .asJson();
        Person person = Json.fromJson(json, Person.class);
        person.save();
        return ok(Json.toJson(person));
    }

    public static Result jsRoutes()
    {
        response().setContentType("text/javascript");
        return ok(Routes.javascriptRouter("appRoutes", //appRoutes will be the JS object available in our view
                                          routes.javascript.Application.getAll(),
                                          routes.javascript.Application.delete(),
                                          routes.javascript.Application.create()));
    }
}
