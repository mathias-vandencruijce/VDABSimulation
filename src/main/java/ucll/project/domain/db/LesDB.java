package ucll.project.domain.db;

import ucll.project.domain.model.Les;

import java.util.List;

public interface LesDB {
    void addLes(Les les);
    Les getLes(int id);
    List<Les> getAllLessen();
    List<Integer> getAllIds();
    void addPersonToLes(String userid, int id);
    List<Les> getAllLessenFromLector(String userid);
    void changePlaats(String plaats, int id);
    void changeJoinableState(Boolean bool, String id);
    List<Les> getAllActiveLessenFromStudent(String userid);
}
