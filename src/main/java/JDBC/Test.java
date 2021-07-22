package JDBC;

import JavaBean.PsRole;
import JavaBean.PsRolePermission;
import JavaBean.PsSolverDetail;
import JavaBean.PsSystemConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test extends BaseDao {

    public static void main(String[] args) throws SQLException {
        PsRoleDao psRoleDao = new PsRoleDao();
//        System.out.println(psRoleDao.judgeRoleIdAndRoleName("ps_role01", "Test"));
//        psRoleDao.addRole("ps_role09", "Test", "admin", 1);

        PsSystemConfigDao psSystemConfigDao = new PsSystemConfigDao();
//        List<PsSystemConfig> psSystemConfigList = psSystemConfigDao.getAllSystemConfigByKind("1");
//        for (int i = 0; i < psSystemConfigList.size(); i ++) {
//            System.out.println(psSystemConfigList.get(i).getConfigCode() + "; " + psSystemConfigList.get(i).getConfigName());
//        }
//        System.out.println(psSystemConfigDao.judgeSystemConfigByConfigCodeAndConfigValueIdAndConfigValueName("RUNNING_STATUS", 10, "正常1"));
//        psSystemConfigDao.addSystemConfig("TEST01", "测试01", 1, "test01", 1);
//    System.out.println(psSystemConfigDao.getConfigNameByConfigCode("BUG_LEVEL"));
//    psSystemConfigDao.updateGetConfigCode("TEST01", "测试");
//    PsSystemConfig psSystemConfig = psSystemConfigDao.getConfig();
//    System.out.println("configCode = " + psSystemConfig.getConfigCode() + "; configName = " + psSystemConfig.getConfigName());
//    psSystemConfigDao.deleteConfigByConfigCodeAndConfigValueId("TEST02", 0);

//    System.out.println(psRoleDao.deleteRoleByRoleId("ps_role06"));
        PsFunctionDao psFunctionDao = new PsFunctionDao();
        PsRolePermissionDao psRolePermissionDao = new PsRolePermissionDao();
//        System.out.println(psFunctionDao.getCheckboxIdByFunctionCode("8"));
//        System.out.println(psRolePermissionDao.getFunctionCodeByRoleId("ps_role01"));
//        String str = "8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,";
//        String[] arrayCode = str.split(",");
//        for (int i = 0; i < arrayCode.length; i++) {
//            System.out.print(arrayCode[i]+ ",");
//        }
//        System.out.println(arrayCode.length);
//        System.out.println(psRolePermissionDao.updateFunctionCodeByRoleId("ps_role04", "20,21,24,25,"));

        PsSolverDetailDao psSolverDetailDao = new PsSolverDetailDao();
//        List<PsSolverDetail> psSolverDetailList = psSolverDetailDao.getSolverBySolverStatus("RW0245",1);
//
//        for (int i = 0; i < psSolverDetailList.size(); i++) {
//                System.out.println(psSolverDetailList.get(i).getSolverCode());
//        }

//        System.out.println(psSolverDetailDao.updateSolverStatusAndSolveTaskCodeAndSolverCode("XQ008", null, 0));

//        psSolverDetailDao.changeSolverStatusAndAssignments("XQ001,XQ002,", "RW0245", 1);

        String solverCodes = "RW001,1;RW002,2;RW003,0;";
        String[] solverCode = solverCodes.split(";");
        String[] taskCode = new String[solverCode.length];
        String[] defectType = new String[solverCode.length];
        for (int i = 0; i < solverCode.length; i++) {
            String[] temp = solverCode[i].split(",");
            taskCode[i] = temp[0];
            defectType[i] = temp[1];
        }
    }
}
