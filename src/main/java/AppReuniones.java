import com.linkedin.learning.dao.ReunionDao;

public class AppReuniones {
    public static void main(String[] args) {
        ReunionDao reunionDao = new ReunionDao();
        System.out.println("Reuniones Pedro: " + reunionDao.reuinonesParticipante("E002"));
    }
}
