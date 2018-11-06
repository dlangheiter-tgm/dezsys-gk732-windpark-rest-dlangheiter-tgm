package windpark.windengine;

import org.springframework.web.bind.annotation.RestController;

import windpark.model.WindengineData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class WindengineController {

    @Autowired
    private WindengineService service;

    // Not needed anymore because of public folder
    /*@RequestMapping("/")
    public String windengineMain() {
        String mainPage = "This is the windengine application! (DEZSYS_GK72_WINDPARK) <br/><br/>" +
                "<a href='http://localhost:8080/windengine/001/data/xml'>Link to windengine/001/data/xml</a><br/>" +
                "<a href='http://localhost:8080/windengine/001/data/json'>Link to windengine/001/data/json</a><br/>" +
                "<a href='http://localhost:8080/windengine/001/transfer'>Link to windengine/001/transfer</a><br/>";
        return mainPage;
    }*/

    @RequestMapping(value = "/windengine/{windengineID}/data/xml", produces = "application/xml")
    public WindengineData windengineDataXml(@PathVariable String windengineID) {
        return service.getWindengineData(windengineID);
    }

    @RequestMapping(value = "/windengine/{windengineID}/data/json", produces = "application/json")
    public WindengineData windengineDataJson(@PathVariable String windengineID) {
        return service.getWindengineData(windengineID);
    }

    @RequestMapping("/windengine/{windengineID}/transfer")
    public String windengineTransfer(@PathVariable String windengineID) {
        return service.getGreetings("Windengine.Transfer!");
    }


}