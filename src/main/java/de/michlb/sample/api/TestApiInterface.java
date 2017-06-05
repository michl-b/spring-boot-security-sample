package de.michlb.sample.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by michael on 04.06.17.
 */
@Api(value = "TestApi")
public interface TestApiInterface {

    @ApiOperation(value = "wohoo", httpMethod = "GET", notes = "liefert einen Test String")
    String wohoo();
}
