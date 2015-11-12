import com.epam.brest.course2015.project.core.Malfunction;

import java.util.List;

/**
 * Created by blondeks on 11/12/15.
 */
public interface MalfunctionService {
    Integer addMalfunction(Malfunction malfunction);
    void deleteMalfunction(Integer malfunctionId);
    void updateMalfunction(Malfunction malfunction);
    Malfunction getMalfunctionById(Integer malfunctionId);
    List<Malfunction> getAllMalfunctionsByIdApplication(Integer applicationId);
}
