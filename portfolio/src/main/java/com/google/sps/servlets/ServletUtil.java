package com.google.sps.servlets;
import com.google.gson.Gson;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class ServletUtil {
    public static DatastoreService DATASTORE = DatastoreServiceFactory.getDatastoreService();
}