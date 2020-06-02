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

import com.google.sps.data.ServerStats;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public final class DataServlet extends HttpServlet {

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            
        // Generate the comments for the discussion board in array list

        ArrayList<String> sampleComments = new ArrayList<>();
        sampleComments.add("You rock! Keep on coding!");
        sampleComments.add("This is momma! You got this girl :-)");
        sampleComments.add("This is dad! Go break some eggs, babygirl.");

        // Create serverData object in json form
        ServerStats serverData = new ServerStats(sampleComments);
        String json = convertToJson(serverData);

        // Send the JSON as the response
        response.setContentType("application/json;");
        response.getWriter().println(json);

    }

    /**
    * Converts a serverData instance into a JSON string using manual String concatentation.
    */
    private String convertToJson(ServerStats serverData) {
        String json = "{";
        json += "\"Comment" + 0 + "\": ";
    	json += "\"" + serverData.getSampleComment(0) + "\"";

        // loop through all comments in serverData array and concatenate it to json
        for(int index = 1; index < serverData.getSampleCommentsSize(); index++){
            json += ", ";
            json += "\"Comment" + index + "\": ";
            json += "\"" + serverData.getSampleComment(index) + "\"";
        }
        json += "}";
        return json;
    }
}