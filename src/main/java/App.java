import java.util.List;
import java.util.Map;
import java.util.HashMap;

import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import models.Member;
import models.Team;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("public");
        String connectionString = "jdbc:h2:~/activeplanet.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        //instantiate Data Access Objects for teams and members
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);

        //show a home page with all teams
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> allTeams = teamDao.allTeams();
            model.put("teams", allTeams);
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
            teamDao.add(new Team(newTeamName, newTeamDescription));
            List<Team> allTeams = teamDao.allTeams();
            model.put("teams", allTeams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // see an individual team details page
        get("/teams/:id/members", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("id"));
            Team currentTeam = teamDao.findById(teamId);
            //request.session().attribute("searchTeam", currentTeam); // new
            model.put("currentTeam", currentTeam);
            model.put("currentMembers", memberDao.currentMembersByTeams(teamId));
            return new ModelAndView(model, "team-information.hbs");
        }, new HandlebarsTemplateEngine());

        // show a from to add a new team member
        get("/teams/:id/members/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("id"));
            Team currentTeam = teamDao.findById(teamId);
            model.put("currentTeam", currentTeam);
            return new ModelAndView(model, "add-team-members.hbs");
        }, new HandlebarsTemplateEngine());

        // process a new form when adding a new team member
        post("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String memberName = request.queryParams("memberName");
            int memberAge = Integer.parseInt(request.queryParams("memberAge"));
            int teamId = Integer.parseInt(request.params("id"));
            Member newMember = new Member(memberName, memberAge, teamId);
            model.put("currentMember", newMember);
            memberDao.add(new Member(memberName, memberAge, teamId));
            response.redirect("/teams/" + teamId + "/members");
            return null;
        });

        // show a form to edit a team information
        get("/teams/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("id"));
            Team currentTeam = teamDao.findById(teamId);
            model.put("currentTeam", currentTeam);
            return new ModelAndView(model, "edit-team-form.hbs");
        }, new HandlebarsTemplateEngine());

        // process a from to edit a team information
        post("/teams/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = request.queryParams("teamName");
            String newDescription = request.queryParams("teamDescription");
            int teamId = Integer.parseInt(request.params("id"));
            teamDao.update(teamId, newName, newDescription);
            response.redirect("/teams/" + teamId + "/members");
            return new ModelAndView(model, "team-information.hbs");
        }, new HandlebarsTemplateEngine());

        // delete a team member
        get("/teams/:id/members/:memberId/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("id"));
            int memberId = Integer.parseInt(request.params("memberId"));
            memberDao.deleteById(memberId);
            response.redirect("/teams/" + teamId + "/members");
            return new ModelAndView(model, "team-information.hbs");
        }, new HandlebarsTemplateEngine());

        // show a form to update current team member information
        get("/teams/:id/members/:memberId/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int memberId = Integer.parseInt(request.params("memberId"));
            Member currentMember = memberDao.findById(memberId);
            model.put("currentMember", currentMember);
            return new ModelAndView(model, "update-member.hbs");
            //memberDao.update(memberId, memberName, memberAge);
        }, new HandlebarsTemplateEngine());

        // process a form to update a team member information
        post("/teams/:id/members/:memberId/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int teamId = Integer.parseInt(request.params("id"));
            String memberName = request.queryParams("memberName");
            int memberAge = Integer.parseInt(request.queryParams("memberAge"));
            int memberId = Integer.parseInt(request.params("memberId"));
            memberDao.update(memberId, memberName, memberAge);
            response.redirect("/teams/" + teamId + "/members");
            return null;
        });

        // delete current team
        get("teams/:id/delete", (request, response) -> {
            int teamId = Integer.parseInt(request.params("id"));
            teamDao.deleteById(teamId);
            response.redirect("/");
            return null;
        });
    }
}
