package myselectshop.controller;

import lombok.RequiredArgsConstructor;
import myselectshop.dto.FolderRequestDto;
import myselectshop.dto.FolderResponseDto;
import myselectshop.exception.RestApiException;
import myselectshop.security.UserDetailsImpl;
import myselectshop.service.FolderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    @PostMapping("/folders")
    public void addFolder(@RequestBody FolderRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        List<String> folderNames = requestDto.getFolderNames();
        folderService.addFolders(folderNames, userDetails.getUser());
    }

    @GetMapping("/folders")
    public List<FolderResponseDto> getFolders(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return folderService.getFolders(userDetails.getUser());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<RestApiException> handleException(IllegalArgumentException ex) {
        RestApiException restApiException = new RestApiException(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
            // HTTP body
            restApiException,
            // HTTP status code
            HttpStatus.BAD_REQUEST
        );
    }
}
