package org.closure.MMirror.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.closure.MMirror.entities.Event;
import org.closure.MMirror.entities.Mirror;
import org.closure.MMirror.entities.Pics;
import org.closure.MMirror.entities.User;
import org.closure.MMirror.models.CalenderConfigModel;
import org.closure.MMirror.models.CalenderConfigObjectModel;
import org.closure.MMirror.models.EventDto;
import org.closure.MMirror.models.MMMFacialModel;
import org.closure.MMirror.models.MMMFacialModelConfig;
import org.closure.MMirror.models.MirrorConfig;
import org.closure.MMirror.models.MirrorDto;
import org.closure.MMirror.models.PicsDto;
import org.closure.MMirror.repositories.MirrorRepo;
import org.closure.MMirror.repositories.PicsRepo;
import org.closure.MMirror.repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// import net.fortuna.ical4j.data.CalendarBuilder;
// import net.fortuna.ical4j.data.CalendarOutputter;
// import net.fortuna.ical4j.data.ParserException;
// import net.fortuna.ical4j.model.Calendar;
// import net.fortuna.ical4j.model.component.VEvent;
// import net.fortuna.ical4j.model.property.CalScale;
// import net.fortuna.ical4j.model.property.ProdId;
// import net.fortuna.ical4j.model.property.Uid;
// import net.fortuna.ical4j.model.property.Version;
// import net.fortuna.ical4j.util.RandomUidGenerator;
// import net.fortuna.ical4j.util.UidGenerator;

@Service
public class MirrorService {

    @Autowired
    private MirrorRepo mirrorRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PicsRepo picsRepo;

    public MirrorDto addMirror(MirrorDto mirrorDto) {
        Mirror mirror = mirrorRepo
                .save(new Mirror().id(IdGeneration.getNextRandomString()).createdAt(mirrorDto.getCreatedAt())
                        .deviceid(mirrorDto.getDeviceid()).productionDate(mirrorDto.getProductionDate())
                        .productionLocation(mirrorDto.getProductionLocation()).soc(mirrorDto.getSoc()));
        return mirrorDto.id(mirror.getId());
    }

    public MirrorDto linkUserWithMirror(String userId, String mirrorId) throws Exception {
        Optional<Mirror> mirrorRes = mirrorRepo.findById(mirrorId);
        Mirror mirror = mirrorRes.get();
        if (mirror.getUser() != null)
            throw new Exception("mirror already linked with a user");
        mirror.setUser(userRepo.findById(userId).get());
        mirror = mirrorRepo.save(mirror);
        User user = userRepo.save(userRepo.findById(userId).get().mirror(mirror));
        userRepo.save(user);
        return new MirrorDto().createdAt(mirror.getCreatedAt()).deviceid(mirror.getDeviceid())
                .productionDate(mirror.getProductionDate()).productionLocation(mirror.getProductionLocation())
                .soc(mirror.getSoc()).userId(mirror.getUser().getId()).id(mirror.getId());
    }

    public static <T> Consumer<T> withCounter(BiConsumer<Integer, T> consumer) {
        AtomicInteger counter = new AtomicInteger(0);
        return item -> consumer.accept(counter.getAndIncrement(), item);
    }

    public ResponseEntity<List<Object>> multiUpload(MultipartFile[] files, String mirrorId, String userId) {
        List<Object> fileDownloadUrls = new ArrayList<>();
        Arrays.asList(files).stream().forEach(withCounter(
                (i, file) -> fileDownloadUrls.add(uploadToLocalFileSystem(file, mirrorId, userId, i).getBody())));
        return ResponseEntity.ok(fileDownloadUrls);
    }

    private ResponseEntity<Object> uploadToLocalFileSystem(MultipartFile file, String mirrorId, String userId,
            int counter) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(String.format("assets/images/mirrors/%s/%s_%d%s", mirrorId, userId, counter, fileName));
        path.toFile().getParentFile().mkdir();
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v2/mirror/assets/images/mirrors/")
                .path(String.format("%s/%s_%d%s", mirrorId, userId, counter, fileName)).toUriString();
        picsRepo.save(new Pics().id(IdGeneration.getNextRandomString()).url(fileDownloadUri)
                .user(userRepo.findById(userId).get()));
        return ResponseEntity.ok(fileDownloadUri);
    }

    public ResponseEntity<Resource> downloadFileFromLocal(String mirrorId, String fileName) {
        Path path = Paths.get(String.format("assets/images/mirrors/%s/%s", mirrorId, fileName));
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("multipart/form-data"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // TODO fix function to run with production jar file
    public Map<String,Object> mirrorPics(String mirrorId) {
        // Path path = Paths.get(String.format("assets/images/mirrors/%s", mirrorId));

        // List<Object> imgs = new ArrayList<Object>();
        // path.forEach((item)-> imgs.add(item.getFileName().toString()));
        // path.forEach((e)->imgs.add(e.getFileName()));
        // List<Object> pics = imgs.stream()
        // .map((item) -> (Object) ServletUriComponentsBuilder.fromCurrentContextPath()
        // .path("/api/v2/mirror/assets/images/mirrors/")
        // .path(String.format("%s/%s", mirrorId, item)).toUriString())
        // .toList();
        // return pics;
        List<PicsDto> pics =  mirrorRepo.findById(mirrorId).get().getUser().getPics().stream()
                .map((item) -> new PicsDto().id(item.getId()).url(item.getUrl())).toList();
        HashMap<String,Object> result = new HashMap<>();
        result.put("username", mirrorRepo.findById(mirrorId).get().getUser().getName());
        result.put("pics", pics);
        return result;
    }

    public List<Object> getEvents(String mirrorId) {
        return mirrorRepo.findById(mirrorId).get().getUser().getEvents().stream()
                .map((item) -> (Object) new EventDto().id(item.getId()).end(item.getEnd()).start(item.getStart())
                        .summery(item.getSummery()).title(item.getTitle()).user_name(item.getUser().getName())
                        .user_id(item.getUser().getId()))
                .toList();
    }

    public MirrorConfig generateConfig(String mirrorId, int webcal) {
        MirrorConfig mirrorConfig = new MirrorConfig();
        mirrorConfig.setAddress("localhost");
        mirrorConfig.setPort(8080);
        mirrorConfig.setBasePath("/");
        mirrorConfig.setHttpsCertificate("");
        mirrorConfig.setHttpsPrivateKey("");
        mirrorConfig.setUseHttps(false);
        mirrorConfig.setLocale("en-US");
        mirrorConfig.setLanguage("en");
        ArrayList<String> logLevel = new ArrayList<String>();
        logLevel.add("INFO");
        logLevel.add("LOG");
        logLevel.add("WARN");
        logLevel.add("ERROR");
        mirrorConfig.setLogLevel(logLevel);
        ArrayList<String> ipWhitelist = new ArrayList<String>();
        ipWhitelist.add("127.0.0.1");
        ipWhitelist.add("::ffff:127.0.0.1");
        ipWhitelist.add("::1");
        mirrorConfig.setIpWhitelist(ipWhitelist);
        mirrorConfig.setTimeFormat(24);
        mirrorConfig.setUnits("metrics");
        HashMap<String, String> alert = new HashMap<String, String>();
        alert.put("module", "alert");

        HashMap<String, String> updatenotification = new HashMap<String, String>();
        updatenotification.put("module", "updatenotification");
        updatenotification.put("position", "top_bar");

        HashMap<String, String> clock = new HashMap<String, String>();
        clock.put("module", "clock");
        clock.put("position", "top_left");

        CalenderConfigModel calenderConfigModel = new CalenderConfigModel();
        calenderConfigModel.setModule("calender");
        calenderConfigModel.setHeader("primary events");
        calenderConfigModel.setPosition("top_left");
        calenderConfigModel.setClasses(mirrorRepo.findById(mirrorId).get().getUser().getName());

        {
            CalenderConfigObjectModel calenderConfigObjectModel = new CalenderConfigObjectModel();
            ArrayList<HashMap<String, String>> calenders = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> calenderItem = new HashMap<String, String>();
            calenderItem.put("symbol", "calendar-check");
            if (webcal == 1)
                calenderItem.put("url",
                        String.format("webcal:http://magicm.hi-do.eu:8080/api/v2/mirror/%s/ics", mirrorId));
            else if (webcal == 2)
                calenderItem.put("url", String.format("http://magicm.hi-do.eu:8080/api/v2/mirror/%s/ics", mirrorId));
            else if (webcal == 3)
                calenderItem.put("url",
                        String.format("webcal:www.magicm.hi-do.eu:8080/api/v2/mirror/%s/ics", mirrorId));
            calenders.add(calenderItem);
            calenderConfigObjectModel.setCalendars(calenders);
            calenderConfigModel.setConfig(calenderConfigObjectModel);
        }

        MMMFacialModel mmmFacialModel = new MMMFacialModel();
        mmmFacialModel.setModule("MMM-Facial-Recognition-OCV3");
        {
            MMMFacialModelConfig mmmFacialModelConfig = new MMMFacialModelConfig();
            mmmFacialModelConfig.setThreshold(80);
            mmmFacialModelConfig.setUseUSBCam(false);
            mmmFacialModelConfig
                    .setTrainingFile("/home/pi/MagicMirror/modules/MMM-Facial-Recognition-OCV3/training.xml");
            mmmFacialModelConfig.setInterval(2);
            mmmFacialModelConfig.setLogoutDelay(15);
            ArrayList<String> users = new ArrayList<String>();
            users.add(mirrorRepo.findById(mirrorId).get().getUser().getName());
            mmmFacialModelConfig.setUsers(users);
            mmmFacialModelConfig.setDefaultClass("default");
            mmmFacialModelConfig.setEveryoneClass("everyone");
            mmmFacialModelConfig.setWelcomeMessage(true);
            mmmFacialModel.setConfig(mmmFacialModelConfig);
        }

        ArrayList<Object> modules = new ArrayList<Object>();
        modules.add(mmmFacialModel);
        modules.add(calenderConfigModel);
        modules.add(clock);
        modules.add(updatenotification);
        modules.add(alert);

        mirrorConfig.setModules(modules);

        return mirrorConfig;
    }

    public ResponseEntity<Resource> generateIcs(String mirrorId) throws ParseException, IOException {
        java.time.ZoneId zoneId = java.time.ZoneId.systemDefault();
        File filePath = new File("./assets/ics/" + mirrorId + ".ics");
        if (filePath.exists())
            filePath.delete();
        filePath.createNewFile();
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(String.format("BEGIN:VCALENDAR\nPRODID:%s\nVERSION:2.0\nCALSCALE:GREGORIAN\nEND:VCALENDAR",
                "-//ABC Corporation//NONSGML My Product//EN").getBytes());
        fos.close();
        FileInputStream fis = new FileInputStream(filePath);

        // CalendarBuilder calendarBuilder = new CalendarBuilder();

        // List<Event> events =
        // mirrorRepo.findById(mirrorId).get().getUser().getEvents();
        // Calendar calendar = calendarBuilder.build(fis);
        // // calendar.getProperties().add(new ProdId("-//ABC Corporation//NONSGML My
        // Product//EN"));
        // // calendar.getProperties().add(Version.VERSION_2_0);
        // // calendar.getProperties().add(CalScale.GREGORIAN);
        // for (Event event : events) {
        // Long startDateTimeInMillis = new
        // SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ssXXX").parse(event.getStart()).getTime();
        // Long endDateTimeInMillis = new
        // SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ssXXX").parse(event.getEnd()).getTime();
        // Date start = new
        // SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ssXXX").parse(event.getStart());
        // Date end = new
        // SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ssXXX").parse(event.getEnd());

        // java.util.Calendar calendarStartTime = new java.util.GregorianCalendar();
        // calendarStartTime.setTimeInMillis(startDateTimeInMillis);

        // // Time zone info
        // // TimeZone tz = calendarStartTime.getTimeZone();
        // // ZoneId zid = ZoneId.systemDefault();

        // /* Generate unique identifier */
        // UidGenerator ug = new RandomUidGenerator();
        // Uid uid = ug.generateUid();

        // /* Create the event */
        // String eventSummary = event.getSummery();

        // // VEvent iscEvent = new VEvent(LocalDateTime.now(), LocalDateTime.now(),
        // eventSummary);
        // // iscEvent.getProperties().add(uid);
        // // calendar.getComponents().add(iscEvent);
        // }
        // FileOutputStream fout = null;
        // try {

        // fout = new FileOutputStream(filePath);

        // CalendarOutputter outputter = new CalendarOutputter();
        // outputter.output(calendar, fout);

        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // } catch (IOException e) {
        // e.printStackTrace();
        // } finally {
        // if (fout != null) {
        // try {
        // fout.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        // }
        Path path = Paths.get(String.format("assets/ics/%s.ics", mirrorId));
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("multipart/form-data"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    public ResponseEntity<Resource> icsForMirror(String mirrorId) throws IOException {
        String header = "BEGIN:VCALENDAR\nVERSION:2.0\nPRODID:-//Calendar Labs//Calendar 1.0//EN\nCALSCALE:GREGORIAN\nMETHOD:PUBLISH\nX-WR-CALNAME:US Holidays\nX-WR-TIMEZONE:Etc/GMT\n";
        List<Event> events = mirrorRepo.findById(mirrorId).get().getUser().getEvents();
        for (Event event : events) {
            String startEvent = event.getStart();
            startEvent = startEvent.substring(0, startEvent.indexOf("T")).replace("-", "");
            String endEvent = event.getEnd();
            endEvent = endEvent.substring(0, endEvent.indexOf("T")).replace("-", "");
            String eventString = String.format(
                    "BEGIN:VEVENT\nSUMMARY:%s\nDTSTART:%s\nDTEND:%s\nLOCATION:United States\nDESCRIPTION:%s \nUID:%s@magicm.hi-do.eu\nDTSTAMP:20201201T233413Z\nSTATUS:CONFIRMED\nTRANSP:TRANSPARENT\nSEQUENCE:0\nEND:VEVENT\n",
                    event.getSummery(), startEvent, endEvent, event.getTitle(), mirrorId);
            header = header + eventString;
        }
        header = header + "END:VCALENDAR";
        System.out.println(header);
        File filePath = new File("./assets/ics/" + mirrorId + ".ics");
        if (filePath.exists())
            filePath.delete();
        filePath.createNewFile();
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(header.getBytes());
        fos.close();
        Path path = Paths.get(String.format("assets/ics/%s.ics", mirrorId));
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("multipart/form-data"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // TODO : to test function
    public ResponseEntity<Resource> testIcsFile() {
        Path path = Paths.get("assets/ics/test.ics");
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("multipart/form-data"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
