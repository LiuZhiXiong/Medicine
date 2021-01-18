package com.itcmdas.test;

import com.itcmdas.dao.MedicineDao;
import com.itcmdas.vo.Medicine;

import java.sql.SQLException;

/**
 * @Classname DaoTest
 * @Description TODO
 * @Author liubo
 * @Date 2020/10/8 16:25
 * @Version 1.0
 */
public class DaoTest {
    public static void main(String[] args) throws SQLException {
        MedicineDao medicineDao=new MedicineDao();

        Medicine medicine=new Medicine();
        medicine=medicineDao.getMedicineByMedicineName("甘草");

        System.out.println(medicine);
    }
}
