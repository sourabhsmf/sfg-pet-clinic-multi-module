package guru.springframework.sfgpetclinic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.sfgpetclinic.model.Version;

@RestController
public class VersionController {
    
    @GetMapping({"/version.html", "/version"})
    public Version index(){
        
        String versionString = System.getenv("APP_VERSION");
        versionString = versionString == null ? "0.0.1" : versionString;  

        String releaseString = System.getenv("POD_RELEASE");
        releaseString = releaseString == null ? "blue" : releaseString;  
                
        return Version.builder().version(versionString).release(releaseString).build();
        
    }
}
