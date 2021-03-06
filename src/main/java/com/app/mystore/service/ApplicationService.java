package com.app.mystore.service;

import com.app.mystore.dao.ApplicationDao;
import com.app.mystore.dao.NotificationsDao;
import com.app.mystore.dao.UserDao;
import com.app.mystore.dto.Application;
import com.app.mystore.dto.Notification;
import com.app.mystore.enums.NotificationTypeEnum;
import com.app.mystore.utils.MystoreHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Author: Mitchell Moore
 * B00647455
 * ApplicationService connects the controller to the doa class. It also creates notifications and
 * generates and sends emails to users.
 */
@Service("ApplicationService")
public class ApplicationService {

    @Autowired
    public ApplicationDao dao;

    @Autowired
    private NotificationsDao notificationDao;

    @Autowired
    public UserDao userDao;

    @Autowired
    public MystoreHelper helper;

    @Autowired
    public LoginControllerService loginControllerService;

    public List<Application> fetchAll() {
        List<Application> applications = dao.fetchAll();
        return applications;
    }

    public Application fetchByApplicationID(int applicationID) {
        Application application = dao.getByApplicationID(applicationID);
        return application;
    }

    /**
     * updateApplication connects controller updateApplication request to dao. Converts the number of rows
     * changed to a boolean.
     * @param updateApplication
     * @return Boolean true => update success, false => update failed
     */
    public Boolean updateApplication(Application updateApplication) {
        int result = dao.updateApplication(updateApplication);
        if(result > 0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * addApplication converts the number of rows changed to a boolean. It also creates a new notification
     * to be sent to the manager that a new application has been created for one of their job posts.
     * @param newApplication
     * @return Boolean true => create Application success, false => create Application failed
     */
    public Boolean addApplication(Application newApplication) {
        int result = dao.insertApplication(newApplication);
        if(result > 0){
            int managerUserID = 0;
            try {
                managerUserID = userDao.getManagerId();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            String applicationNotification = "New application from " + newApplication.getFirstName();
            Notification notification = new Notification();
            NotificationTypeEnum notificationTypeEnum = NotificationTypeEnum.JOB_NOTIFICATION;
            notification.setUserId(managerUserID);
            notification.setNotification(applicationNotification);
            notification.setNotificationType(notificationTypeEnum.getType());
            try{
                notificationDao.createNotification(notification);
            }
            catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * deleteApplication converts the number of rows changed to a boolean. It also creates a new notification
     * to be sent to the applicant to tell them their application has been denied.
     * @param applicationID
     * @return
     */
    public Boolean deleteApplication(int applicationID) {
        Application application = dao.getByApplicationID(applicationID);
        if (application != null) {
            String applicationNotification = "Thank you for your application. Unfortunately you were not selected but please apply again.";
            Notification notification = new Notification();
            NotificationTypeEnum notificationTypeEnum = NotificationTypeEnum.JOB_NOTIFICATION;
            notification.setUserId(application.getUserID());
            notification.setNotification(applicationNotification);
            notification.setNotificationType(notificationTypeEnum.getType());
            try{
                notificationDao.createNotification(notification);
            }
            catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            int result = dao.deleteApplication(applicationID);
            if(result > 0){
                System.out.println("here");
                return true;
            }
        }

        return false;
    }

    /**
     * acceptApplication sends an email to the applicant with a job offer. It also connects with the updateRole
     * in the loginControllerService to upgrade the user from guest to employee. It then deletes the application
     * from the database. It also sends a notification to the applicant welcoming them to the company.
     * @param applicationID
     * @return Boolean true => operations successful, false => operations failed.
     */
    public Boolean acceptApplication(int applicationID) {
        Application application = dao.getByApplicationID(applicationID);
        int row =0;
        System.out.println(application.getUserID());

        if(application != null){
            String body = "Hello, We would like to inform you we have accepted your application! Please sign into myStore to view the employee page.";
            helper.sendEmail(application.getEmail(), body, "Approved application");
            try {
                row = loginControllerService.updateRole(application.getUserID());
            } catch (Exception e) {
                e.printStackTrace();
            }
            dao.deleteApplication(application.getApplicationID());
            String applicationNotification = "Congrats on your new job at myStore!";
            Notification notification = new Notification();
            NotificationTypeEnum notificationTypeEnum = NotificationTypeEnum.JOB_NOTIFICATION;
            notification.setUserId(application.getUserID());
            notification.setNotification(applicationNotification);
            notification.setNotificationType(notificationTypeEnum.getType());
            try{
                notificationDao.createNotification(notification);
            }
            catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        System.out.println("row  result: " + row);
        if(row > 0){
            return true;
        }
        else{
            return false;
        }
    }
}
