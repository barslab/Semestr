package services;

import dao.SubstitutesDaoImpl;
import factories.ConnectionFactory;
import models.Substitutes;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Sodium on 12.12.2016.
 */
public class SubstitutesServiceImpl implements SubstitutesService {
    private Connection connection;
    private SubstitutesDaoImpl substitutesDao=null;

    public SubstitutesServiceImpl() {
        this.connection = ConnectionFactory.getInstance().getConnection();
        this.substitutesDao = new SubstitutesDaoImpl(connection);
    }
    public List<Substitutes> find(int drug_id) {
        List<Substitutes> substitutes = substitutesDao.find(drug_id);
        return substitutes;
    }
}
