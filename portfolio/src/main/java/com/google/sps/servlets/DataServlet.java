// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.sps.data.PortfolioComments;
import com.google.gson.Gson;
import com.google.sps.data.Task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public final class DataServlet extends HttpServlet {

	private PortfolioComments commentsRecord = new PortfolioComments();

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Query query = new Query("Comments").addSort("timestamp", SortDirection.DESCENDING);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        PreparedQuery results = datastore.prepare(query);

        List<Task> commentsRecord = new ArrayList<>();
        for (Entity entity : results.asIterable()) {
            long id = entity.getKey().getId();
            String userInput = (String) entity.getProperty("userInput");
            long timestamp = (long) entity.getProperty("timestamp");

            Task task = new Task(id, userInput, timestamp);
            commentsRecord.add(task);
    	}

	    // Create commentsRecord object in json form
        String json = new Gson().toJson(commentsRecord);
        
        // Send the JSON as the response
        response.setContentType("application/json;");
        response.getWriter().println(json);

    }

	/* Receive Any New Inputed Comments from User */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get Input from the Form
    	String userInput = request.getParameter("user-comment");
        long timestamp = System.currentTimeMillis();

        // Add Input to the Master list of User Comments
        //commentsRecord.addComment(userInput);
        Entity commentsEntity = new Entity("Comments");
        commentsEntity.setProperty("userInput", userInput);
        commentsEntity.setProperty("timestamp", timestamp);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(commentsEntity);


        //Redirect back to HTML page
        response.sendRedirect("/discussion.html");
    }
}