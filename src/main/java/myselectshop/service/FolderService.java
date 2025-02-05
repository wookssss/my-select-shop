package myselectshop.service;

import lombok.RequiredArgsConstructor;
import myselectshop.dto.FolderResponseDto;
import myselectshop.entity.Folder;
import myselectshop.entity.User;
import myselectshop.repository.FolderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {
    private final FolderRepository folderRepository;

    public void addFolders(List<String> folderNames, User user) {
        List<Folder> existFolderList = folderRepository.findAllByUserAndNameIn(user, folderNames);

        List<Folder> folderList = new ArrayList<>();

        for(String folderName : folderNames){
            if(!isExistFolderName(folderName, existFolderList)){
                Folder folder = new Folder(folderName,user);
                folderList.add(folder);
            } else {
                throw new IllegalArgumentException("이미 생성된 폴더입니다.");
            }
        }

        folderRepository.saveAll(folderList);
    }

    public List<FolderResponseDto> getFolders(User user) {
        List<Folder> folderList = folderRepository.findAllByUser(user);
        List<FolderResponseDto> responseDtoList = new ArrayList<>();

        for(Folder folder : folderList){
            responseDtoList.add(new FolderResponseDto(folder));
        }

        return responseDtoList;
    }

    private boolean isExistFolderName(String folderName, List<Folder> existFolderList) {
        for(Folder existFolder : existFolderList){
            if(folderName.equals(existFolder.getName())){
                return true;
            }
        }
        return false;
    }

}
