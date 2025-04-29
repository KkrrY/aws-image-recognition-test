package com.blueprint.image.recognition.web.input;

import com.blueprint.aws.s3.api.ImageAwsS3Api;
import com.blueprint.image.recognition.service.ImageRecognitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.http.paths.ApiRouteConstants.*;

@Controller
@RequestMapping(VIEW)
@RequiredArgsConstructor
public class ImageController {
    private final ImageRecognitionService imageRecognitionService;
    private final ImageAwsS3Api awsS3Api;

    @GetMapping(ImageRecognition.VIEW_IMAGES)
    public String showSearchPage(Model model) {
        List<String> allImages = awsS3Api.generatePresignedUrlsForAllFiles();

        model.addAttribute("searchTerm", "");
        model.addAttribute("results", List.of());
        model.addAttribute("allImages", allImages);

        return "search";
    }

    @PostMapping(ImageRecognition.VIEW_IMAGES_UPLOAD)
    public String uploadImage(@RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes) {
        if (file != null && !file.isEmpty()) {
            String key = file.getOriginalFilename();
            imageRecognitionService.saveImage(key, file);
            redirectAttributes.addFlashAttribute("message", "Upload successful!");
        }
        return "redirect:" + VIEW + ImageRecognition.VIEW_IMAGES;
    }
}
