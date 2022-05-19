

package club.tabstudio.gridmanagementsystem.controller;

import club.tabstudio.gridmanagementsystem.model.EventImage;
import club.tabstudio.gridmanagementsystem.model.Events;
import club.tabstudio.gridmanagementsystem.model.Response;
import club.tabstudio.gridmanagementsystem.service.IEvnetImageService;
import club.tabstudio.gridmanagementsystem.validation.groups.CreateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author wangyihan
 */
@RestController
public class EventImgController {

    @Autowired
    private IEvnetImageService iEvnetImageService;

    @PostMapping("uploadImg")
    @PreAuthorize("hasAuthority('event:create')")
    public Response uploadImg(@Validated({CreateGroup.class})List<MultipartFile> imageList){
        List<String> uuidList = new ArrayList<>();
        if (imageList!=null) {
            for (MultipartFile image : imageList) {
                String originalFileName = image.getOriginalFilename();
                String randUuid = UUID.randomUUID().toString();
                uuidList.add(randUuid);
                try {
                    File directory = new File("");
                    String courseFile = directory.getCanonicalPath();
                    String filePath = "eventImg/"+randUuid + "." + originalFileName;
                    EventImage eventImage = new EventImage(randUuid,null,filePath);
                    image.transferTo(new File(courseFile+"/src/main/resources/"+filePath));
                    // 
                    iEvnetImageService.insertSelective(eventImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Response.success(uuidList);
    }
}
