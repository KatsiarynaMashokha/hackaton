import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

import models.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("public");

        // show a form to add a new team
        get("/add_new_team", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        // homepage where all the teams are displayed
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // process add a new team form
        post("/add_new_team", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newTeamName = request.queryParams("teamName");
            String newTeamDescription = request.queryParams("teamDescription");
            int newTeamSize = Integer.parseInt(request.queryParams("numberOfMembers"));
            Team newTeam = new Team(newTeamName, newTeamDescription, newTeamSize);
            model.put("newTeam", newTeam);
            return new ModelAndView(model, "addTeamMembers.hbs");
        }, new HandlebarsTemplateEngine());




    }
}
