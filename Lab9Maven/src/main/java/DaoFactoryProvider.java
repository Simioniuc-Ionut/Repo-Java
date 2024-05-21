import singleton.dao.AbstractDaoFactory;
import singleton.dao.JdbcDaoFactory;
import singleton.dao.JpaDaoFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class DaoFactoryProvider {
    private static AbstractDaoFactory daoFactory;

    public static AbstractDaoFactory getDaoFactory() {
        if (daoFactory == null) {
            Properties properties = new Properties();
            try {
                properties.load(Files.newInputStream(Paths.get("C:\\Users\\Asus\\Documents\\Facultate\\Anul2\\Sem2\\Java\\Repo-Java_vechi\\Lab9Maven\\src\\main\\resources\\Config.properties")));
                String daoType = properties.getProperty("daoType");
                if ("jdbc".equals(daoType)) {
                    daoFactory = new JdbcDaoFactory();
                } else if ("jpa".equals(daoType)) {
                    daoFactory = new JpaDaoFactory();
                } else {
                    throw new IllegalArgumentException("Invalid daoType: " + daoType);
                }
            } catch (IOException e) {
                System.out.println("Error daoFactory");
                e.printStackTrace();
            }
        }
        return daoFactory;
    }
}
