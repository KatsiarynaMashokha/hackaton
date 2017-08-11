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

        //show a home page with all posts
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Team> teams = Team.getListOfTeams();
            model.put("teams", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        // show a form for adding a new team
        get("/add_new_team", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());



        // process a new form
        post("/add_new_team", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newTeamName = request.queryParams("teamName");
            String newTeamDescription = request.queryParams("teamDescription");
            new Team(newTeamName, newTeamDescription);
            ArrayList<Team> allTeams = Team.getListOfTeams();
            model.put("allTeams", allTeams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // see an individual team details page
        get("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int indexOfTeamToEdit = Integer.parseInt(request.params("id"));
            Team editTeam = Team.findById(indexOfTeamToEdit);
            model.put("teamToEdit", editTeam);
            return new ModelAndView(model, "team-information.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
