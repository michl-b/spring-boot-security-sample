package de.michlb.sample.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by michael on 04.06.17.
 */
@RestController
@RequestMapping("/api/test/")
public class TestApiController implements TestApiInterface {

    @Override
    @RequestMapping("wohoo")
    public String wohoo() {
        return "This was a test!";
    }
}
