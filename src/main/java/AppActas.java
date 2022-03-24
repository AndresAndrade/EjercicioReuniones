import com.linkedin.learning.dao.ActaDao;
import com.linkedin.learning.dominio.Acta;

import java.util.List;

public class AppActas {
    public static void main(String[] args) {
        ActaDao actaDao = new ActaDao();
        List<Acta> actasAntiguas = actaDao.findActasreunionesAntiguasQuery();
        System.out.println("Actas antiguas: " + actasAntiguas);

        List<Acta> actasAntiguas2 = actaDao.findActasreunionesAntiguasQuery();
        System.out.println("Actas antiguas: " + actasAntiguas2);
    }
}
