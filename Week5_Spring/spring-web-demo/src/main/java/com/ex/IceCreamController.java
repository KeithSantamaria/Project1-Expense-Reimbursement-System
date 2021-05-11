package com.ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("icecream")
public class IceCreamController {

    private IceCreamService service;

    @Autowired
    public IceCreamController(IceCreamService iceCreamService) {
        this.service = iceCreamService;
    }

    @GetMapping(produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getAllIcreamFlavors(@RequestParam(required = false)Double min, @RequestParam(required = false) Double max) {
        List<IceCream> iceCreams =  service.getAllIceCream();
        List<String> names = null;

        if(min != null && max != null) {
            names = iceCreams.stream()
                    .filter(i -> i.getPrice() >= min && i.getPrice() <= max)
                    .map(i -> i.getFlavorName())
                    .collect(Collectors.toList());
        } else {
            names = iceCreams.stream()
                    .map(IceCream::getFlavorName)
                    .collect(Collectors.toList());
        }

        return new ResponseEntity(names, HttpStatus.OK);
    }

    @GetMapping(value = "flavor", produces = MediaType.APPLICATION_JSON_VALUE) // /icecream/flavor?name=Shrek%20Surprise
    public IceCream getIceCreamFlavor(@RequestParam("name") String flavorName) {
        return service.getIceCreamFlavor(flavorName);
    }

    @GetMapping(path="flavor/{flavorName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public IceCream getIceCreamFlavorByName(@PathVariable String flavorName) {
        return  service.getIceCreamFlavor(flavorName);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addNewIceCream(@RequestBody IceCream icecream) throws URISyntaxException, UnsupportedEncodingException {
        service.saveIceCream(icecream);
        String newUrl = icecream.getFlavorName().replace(" ", "%20");
        return ResponseEntity.created(new URI("http://localhost:8080/api/icecream/flavor/" + newUrl)).build();
    }
}
