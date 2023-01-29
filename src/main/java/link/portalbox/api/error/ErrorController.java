package link.portalbox.api.error;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://127.0.0.1:5173", "https://portalbox.link"}, maxAge = 3600)
@RestController
@RequestMapping(value = "/error", produces = {MediaType.APPLICATION_JSON_VALUE})
public final class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping
    public String handleError() {
        return "{\"status\": 400, \"message\": \"Unknown request mapping provided.\"}";
    }
}