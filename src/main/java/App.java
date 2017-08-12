import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

import models.Member;
import models.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("public");

        //show a home page with all teams
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
        post("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newTeamName = request.queryParams("teamName");
            String newTeamDescription = request.queryParams("teamDescription");
            new Team(newTeamName, newTeamDescription);
            ArrayList<Team> allTeams = Team.getListOfTeams();
            model.put("teams", allTeams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // see an individual team details page
        get("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int indexOfTeamToEdit = Integer.parseInt(request.params("id"));
            Team editTeam = Team.findById(indexOfTeamToEdit);
            request.session().attribute("teamToEdit", editTeam);
            model.put("currentTeam", editTeam);
            return new ModelAndView(model, "team-information.hbs");
        }, new HandlebarsTemplateEngine());

        // add a new team member
        get("/teams/:id/add_member", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int indexOfTeam = Integer.parseInt(request.params("id"));
            Team currentTeam = Team.findById(indexOfTeam);
            model.put("currentTeam", currentTeam);
            return new ModelAndView(model, "addTeamMembers.hbs");
        }, new HandlebarsTemplateEngine());

        // process a new form when adding a new team member
        post("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String memberName = request.queryParams("memberName");
            int memberAge = Integer.parseInt(request.queryParams("memberAge"));
            Member newMember = new Member(memberName, memberAge);
            Team currentTeam = request.session().attribute("teamToEdit");
            currentTeam.addMember(newMember);
            model.put("currentTeam", currentTeam);
            return new ModelAndView(model, "team-information.hbs");
        }, new HandlebarsTemplateEngine());

        // show a form to edit a team information
        get("/teams/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Team currentTeam = request.session().attribute("teamToEdit");
            model.put("teamToEdit", currentTeam);
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
