package com.hakimen.utils;

import com.hakimen.persistance.JPAInstance;
import net.sf.jasperreports.engine.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ReportingUtils {
    public static JasperPrint getReport(String location, Map<String, Object> params) throws JRException {
        JPAInstance.INSTANCE.getTransaction().begin();
        Connection connection = JPAInstance.INSTANCE.getManager().unwrap(Connection.class);

        JasperReport report = JasperCompileManager.compileReport(ReportingUtils.class.getClassLoader().getResource(location).getFile());
        JasperPrint print = JasperFillManager.fillReport(report, params, connection);
        JPAInstance.INSTANCE.getTransaction().commit();

        return print;
    }
}
