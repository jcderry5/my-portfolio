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

import com.google.sps.data.PortfolioComments;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/** Servlet that both sends userComments to the client and receives + stores inputed User Comments*/
@WebServlet("/data")
public final class DataServlet extends HttpServlet {
  private ArrayList<String> commentsRecord = new ArrayList<>();
  private String json = "";
  private String userCommentId = "user-comment";
  private String discussionPageLink = "/discussion.html";

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Create commentsRecord object in json form
    json = new Gson().toJson(commentsRecord);
    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  /* Receive Any New Inputed Comments from User */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get Input from the Form
    String userInput = request.getParameter(userCommentId);
    // Add Input to the Master list of User Comments
    commentsRecord.addComment(userInput);
    //Redirect back to HTML page
    response.sendRedirect(discussionPageLink);
  }
}
