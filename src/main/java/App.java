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
            Team currentTeam = Team.findById(indexOfTeamToEdit);
            ArrayList<Member> currentTeamMembers = currentTeam.getAttendees();
            //request.session().attribute("teamToEdit", currentTeam);
            model.put("currentTeamMembers", currentTeamMembers);
            model.put("currentTeam", currentTeam);
            return new ModelAndView(model, "team-information.hbs");
        }, new HandlebarsTemplateEngine());

        // add a new team member
        get("/teams/:id/add_member", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
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
            //Team currentTeam = request.session().attribute("teamToEdit");
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

        // edit a team information
        post("/teams/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = request.queryParams("teamName");
            String newDescription = request.queryParams("teamDescription");
            int indexOfTeam = Integer.parseInt(request.params("id"));
            Team currentTeam = Team.findById(indexOfTeam);
            currentTeam.setTeamName(newName);
            currentTeam.setDescription(newDescription);
            model.put("currentTeam", currentTeam);
            return new ModelAndView(model, "team-information.hbs");
        }, new HandlebarsTemplateEngine());

        // delete a team member
        get("/members/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String idOfMemberToDelete = request.params("id");
            int indexOfTeam = Integer.parseInt(request.params("id"));
            Team searchTeam = Team.findById(indexOfTeam);
            ArrayList<Member> currentTeam = searchTeam.deleteMember(idOfMemberToDelete);
            model.put("currentTeam", currentTeam);
            return new ModelAndView(model, "team-information.hbs");

        }, new HandlebarsTemplateEngine());
    }
}
