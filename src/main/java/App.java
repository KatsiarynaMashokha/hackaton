import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

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
        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
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
            Team currentTeam = Team.findById(indexOfTeamToEdit);
            request.session().attribute("searchTeam", currentTeam); // new
            model.put("currentTeam", currentTeam);
            return new ModelAndView(model, "team-information.hbs");
        }, new HandlebarsTemplateEngine());

        // show a from to add a new team member
        get("/teams/:id/add_member", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int indexOfTeam = Integer.parseInt(request.params("id"));
            Team currentTeam = Team.findById(indexOfTeam);
            model.put("currentTeam", currentTeam);
            return new ModelAndView(model, "add-team-members.hbs");
        }, new HandlebarsTemplateEngine());

        // process a new form when adding a new team member
        post("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String memberName = request.queryParams("memberName");
            int memberAge = Integer.parseInt(request.queryParams("memberAge"));
            Member newMember = new Member(memberName, memberAge);
            int indexOfTeam = Integer.parseInt(request.params("id"));
            Team currentTeam = Team.findById(indexOfTeam);
            currentTeam.addMember(newMember);
            model.put("currentTeam", currentTeam);
            return new ModelAndView(model, "team-information.hbs");
        }, new HandlebarsTemplateEngine());

        // show a form to edit a team information
        get("/teams/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int indexOfTeam = Integer.parseInt(request.params("id"));
            Team currentTeam = Team.findById(indexOfTeam);
            model.put("currentTeam", currentTeam);
            return new ModelAndView(model, "edit-team-form.hbs");
        }, new HandlebarsTemplateEngine());

        // process a from to edit a team information
        post("/teams/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = request.queryParams("teamName");
            String newDescription = request.queryParams("teamDescription");
            int indexOfTeam = Integer.parseInt(request.params("id"));
            Team currentTeam = Team.findById(indexOfTeam);
            currentTeam.setTeamName(newName);
            currentTeam.setDescription(newDescription);
            model.put("currentTeam", currentTeam);
            response.redirect("/teams/" + indexOfTeam);
            return new ModelAndView(model, "team-information.hbs");
        }, new HandlebarsTemplateEngine());

        // delete a team member
        get("/teams/:id/members/:memberId/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int indexOfTeam = Integer.parseInt(request.params("id"));
            String idOfMemberToDelete = request.params("memberId");
            Team searchTeam = Team.findById(indexOfTeam);
            searchTeam.deleteMember(idOfMemberToDelete);
            model.put("currentTeam", searchTeam);
            response.redirect("/teams/" + indexOfTeam);
            return new ModelAndView(model, "team-information.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
