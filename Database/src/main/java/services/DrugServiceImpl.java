package services;

import dao.DrugDaoImpl;
import dao.SubstitutesDaoImpl;
import factories.ConnectionFactory;
import models.Drug;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Sodium on 12.12.2016.
 */
public class DrugServiceImpl implements DrugService {
    private Connection connection;
    private DrugDaoImpl drugDao=null;

    public DrugServiceImpl() {
        this.connection = ConnectionFactory.getInstance().getConnection();
        this.drugDao= new DrugDaoImpl(connection);
    }
    public List<Drug> findAll() {
        return drugDao.findAll();
    }

    public List<Drug> findDrugs(int desiase_id) {
        return drugDao.findDrugs(desiase_id);
    }

    public Drug findDrug(int drug_id) {
        return drugDao.findDrug(drug_id);
    }
}
