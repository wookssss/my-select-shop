package myselectshop.repository;

import myselectshop.entity.Folder;
import myselectshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    // select * from folder where user_id = ? and name in (?, ?, ?);
    List<Folder> findAllByUser(User user);

    List<Folder> findAllByUserAndNameIn(User user, List<String> folderNames);
}
