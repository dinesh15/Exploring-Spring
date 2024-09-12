package com.dinesh.rest.webservies.person;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionConrtollerPerson {

    @GetMapping("/v1/person")
    public PersonV1 URIVersionContollerPersonV1() {
        return new PersonV1("Dinesh Raj Pampati");
    }

    @GetMapping("/v2/person")
    public PersonV2 URIVersionContollerPersonV2() {
        return new PersonV2(new Name("Pampati","Dinesh Raj"));
    }

    @GetMapping(value = "/person",params = "version=1")
    public PersonV1 RequestParameterVersionContollerPersonV1() {
        return new PersonV1("Dinesh Raj Pampati");
    }

    @GetMapping(value ="/person",params = "version=2")
    public PersonV2 RequestParameterVersionContollerPersonV2() {
        return new PersonV2(new Name("Pampati","Dinesh Raj"));
    }

    @GetMapping(value = "/person",headers = "api-version=1")
    public PersonV1 HeaderParameterVersionContollerPersonV1() {
        return new PersonV1("Dinesh Raj Pampati");
    }

    @GetMapping(value ="/person",headers = "api-version=2")
    public PersonV2 HeaderParameterVersionContollerPersonV2() {
        return new PersonV2(new Name("Pampati","Dinesh Raj"));
    }

    @GetMapping(value = "/person",produces = "application/api-version1+json")
    public PersonV1 AcceptHeaderParameterVersionContollerPersonV1() {
        return new PersonV1("Dinesh Raj Pampati");
    }

    @GetMapping(value ="/person",produces = "application/api-version2+json")
    public PersonV2 AcceptHeaderParameterVersionContollerPersonV2() {
        return new PersonV2(new Name("Pampati","Dinesh Raj"));
    }


}
