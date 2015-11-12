import com.epam.brest.course2015.project.core.Malfunction;
import com.epam.brest.course2015.project.dao.MalfunctionDao;

import java.util.List;

/**
 * Created by blondeks on 11/12/15.
 */
public class MalfunctionServiceImpl implements MalfunctionService {


    private MalfunctionDao malfunctionDao;

    public void setMalfunctionDao(MalfunctionDao malfunctionDao) {
        this.malfunctionDao = malfunctionDao;
    }
    @Override
    public Integer addMalfunction(Malfunction malfunction) {
        return null;
    }

    @Override
    public void deleteMalfunction(Integer malfunctionId) {

    }

    @Override
    public void updateMalfunction(Malfunction malfunction) {

    }

    @Override
    public Malfunction getMalfunctionById(Integer malfunctionId) {
        return null;
    }

    @Override
    public List<Malfunction> getAllMalfunctionsByIdApplication(Integer applicationId) {
        return null;
    }
}
