package org.closure.MMirror.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.closure.MMirror.models.MirrorConfig;
import org.closure.MMirror.models.MirrorDto;
import org.closure.MMirror.services.MirrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// import net.fortuna.ical4j.data.ParserException;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = {"api/v2/mirror"})
public class MirrorController {
    
    @Autowired
    private MirrorService mirrorService;

    @PostMapping(value="/add")
    public MirrorDto createMirror(@RequestBody MirrorDto mirrorDto) {
        return mirrorService.addMirror(mirrorDto);        
    }
    
    @PostMapping(value="/link")
    public ResponseEntity<Object> linkMirrorWithUser(@RequestBody MirrorDto mirrorDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body( mirrorService.linkUserWithMirror(mirrorDto.getUserId(), mirrorDto.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());

        }
    }

    @PostMapping(value="/upload-files")
    public ResponseEntity<List<Object>> uploadUserImages(@RequestParam("files") MultipartFile[] files, @RequestParam String mirrorId,@RequestParam String userId) {
        return mirrorService.multiUpload(files, mirrorId, userId);
        
    }

    @GetMapping(value="/assets/images/mirrors/{mirrorId}/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String mirrorId,@PathVariable String fileName) {
        return mirrorService.downloadFileFromLocal(mirrorId, fileName);
    }
    
    @GetMapping(value="/{mirrorId}/pics")
    public ResponseEntity<Map<String,Object>> getMirrorImages(@PathVariable String mirrorId) {
        return  ResponseEntity.ok(mirrorService.mirrorPics(mirrorId));
    }
    
    @GetMapping(value="/{mirrorId}/events")
    public ResponseEntity<List<Object>> getEvents(@PathVariable String mirrorId) {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(mirrorService.getEvents(mirrorId));
    }

    @GetMapping(value="/{mirrorId}/config/{style}")
    public MirrorConfig generateConfig(@PathVariable String mirrorId,@PathVariable(name = "style") int webcal) {
        return mirrorService.generateConfig(mirrorId,webcal);
    }

    @GetMapping(value="/{mirrorId}/ics")
    public ResponseEntity<Resource> geticsFile(@PathVariable String mirrorId) throws ParseException, IOException {
        try {
            return mirrorService.icsForMirror(mirrorId);
        } catch (Exception e) {
            return mirrorService.testIcsFile();
        }
    }
    @GetMapping(value="/ics/test")
    public ResponseEntity<Resource> getTestIcsFile() throws ParseException, IOException {
        return mirrorService.testIcsFile();
    }

    
     
    
}
