package JavaBean;

import java.util.logging.Logger;

/**
 *  ��Ŀ��־��
 *  ������� �����־����
 */
public class ProjectLogger {
    private String className = "";
    private String message = "";

    public ProjectLogger(){}
    public ProjectLogger(String className,String message) {
        this.className = className;
        this.message = message;
    }

    public void getLoggerMessage(){
          Logger logger = Logger.getLogger(className);
          logger.info(message);
    }

    public void getLoggerMessage(String className,String message){
        Logger logger = Logger.getLogger(className);
        logger.info(message);
    }
}
