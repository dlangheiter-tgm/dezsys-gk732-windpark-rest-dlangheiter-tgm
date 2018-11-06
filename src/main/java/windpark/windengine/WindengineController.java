package windpark.windengine;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import windpark.model.WindengineData;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class WindengineController {
    private List<String> tokens;

    WindengineController() {
        tokens = new ArrayList<>();
    }

    @Autowired
    private WindengineService service;

    @RequestMapping("/windengine")
    public ResponseEntity getWindengine(@CookieValue("auth") String token) {
        String ret = "This is the windengine application! (DEZSYS_GK72_WINDPARK) <br/><br/>\n" +
                "<a href='/jquery/index.html'>Link to windengine/001/jquery/index.html</a><br/>\n" +
                "<a href='/windengine/001/data/xml'>Link to windengine/001/data/xml</a><br/>\n" +
                "<a href='/windengine/001/data/json'>Link to windengine/001/data/json</a><br/>\n" +
                "<a href='/windengine/001/transfer'>Link to windengine/001/transfer</a><br/>";

        return checkToken(token, ret);
    }

    @RequestMapping("/login")
    public String login(
            HttpServletResponse response,
            @RequestParam(value = "user") String user,
            @RequestParam(value = "password") String password) {

        if (!user.equals(password)) {
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                return "Not authorized";
            }
        }

        String uuid = UUID.randomUUID().toString();
        tokens.add(uuid);
        response.addCookie(new Cookie("auth", uuid));
        try {
            response.sendRedirect("/windengine");
        } catch (IOException e) {
            return "Not authorized";
        }

        return "Redirecting to <a href='/windengine'>Windengine</a>";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse response, @CookieValue("auth") String token) {
        tokens.remove(token);

        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            return "Redirecting to <a href='/'>Home</a>";
        }
        return "";
    }

    private ResponseEntity checkToken(String token, Object object) {
        if(tokens.contains(token)) {
            return ResponseEntity.ok(object);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @RequestMapping(value = "/windengine/{windengineID}/data/xml", produces = "application/xml")
    public ResponseEntity windengineDataXml(@PathVariable String windengineID, @CookieValue("auth") String token) {
        return checkToken(token, service.getWindengineData(windengineID));
    }

    @RequestMapping(value = "/windengine/{windengineID}/data/json", produces = "application/json")
    public ResponseEntity windengineDataJson(@PathVariable String windengineID, @CookieValue("auth") String token) {
        return checkToken(token, service.getWindengineData(windengineID));
    }

    @RequestMapping("/windengine/{windengineID}/transfer")
    public ResponseEntity windengineTransfer(@PathVariable String windengineID, @CookieValue("auth") String token) {
        return checkToken(token, service.getGreetings("Windengine.Transfer!"));
    }

}