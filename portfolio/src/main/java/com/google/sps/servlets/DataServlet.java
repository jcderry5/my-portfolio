// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//   https://www.apache.org/licenses/LICENSE-2.0
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
import com.google.gson.Gson;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.sps.data.UserEntry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
* Servlet that handles user's comment data 
*/
@WebServlet("/data")
public final class DataServlet extends HttpServlet {
  // Set default to 5
  int maxCommentsPosted = 5;
  private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Gather all the user comments and sort them by timestamp
    FetchOptions options = FetchOptions.Builder.withLimit(limit);
    Query query = new Query("Comments").addSort("timestamp", SortDirection.DESCENDING);
    List<Entity> commentsResults = ServletUtil.DATASTORE.prepare(query).asList(options);
    
    //PreparedQuery results = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(maxCommentsPosted));
    // Create a List of User Entries with only the number of comments that the user requested
    List<UserEntry> commentsRecord = collectEntriesToPost(commentResults);
	// Create commentsRecord object in json form
    String json = new Gson().toJson(commentsRecord);
    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  /* 
  * Create the list of comments to be posted on website
  */
  private List collectEntriesToPost(List<Entity> commentsResults){
    // Create list of entities that contain UserEntry objects
    List<UserEntry> commentsRecord = new ArrayList<>();

    /**
    * For loop through the number of stored results that the user requests,
    * collect info, and create a UserEntry from it
    */
    for (Entity entity : commentsResults.asIterable()) {
      long id = entity.getKey().getId();
      String username = (String) entity.getProperty("userName");
      String comment = (String) entity.getProperty("userComment");
      long timestamp = (long) entity.getProperty("timestamp");
      // Only add to the list if you have yet to reach the number of comments requested 
      UserEntry userEntry = new UserEntry(id, username, comment, timestamp);
      commentsRecord.add(userEntry);
  	}
    return commentsRecord;
  }

  /*
  * Receive Any New Inputed Comments from User
  */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get Input from the Form
    String userName = request.getParameter("user-name");
  	String userComment = request.getParameter("user-comment");
    long timestamp = System.currentTimeMillis();

    // Get max number of comments to show 
    maxCommentsPosted = getMaxComments(request);

    // Add Input to the Master list of User Comments
    Entity inputEntity = new Entity("Comments");
    inputEntity.setProperty("userName", userName);
    inputEntity.setProperty("userComment", userComment);
    inputEntity.setProperty("timestamp", timestamp);

    // if statement to ensure empty usernames nor usercomments are put into database
	if(inputEntity.getProperty("userName")!= null || inputEntity.getProperty("userComment")!= null){
      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      datastore.put(inputEntity);
    }

    //Redirect back to HTML page
    response.sendRedirect("/discussion.html");
  }

  private int getMaxComments(HttpServletRequest request) {
    final int DEFAULT_MAX = 3;
    String maxNumberString =  request.getParameter("quantity");

    //Convert String to int
    int maxNumberInt;
    try {
      maxNumberInt = Integer.parseInt(maxNumberString);
    } catch (NumberFormatException e) {
      System.err.println("Could not convert to int: " + maxNumberString);
      return DEFAULT_MAX;
    }
    // Check that the input is greater than 0
  	if (maxNumberInt < 1) {
      System.err.println("Player choice is out of range: " + maxNumberString);
      return DEFAULT_MAX;
    }
  	return maxNumberInt;
  }
}
