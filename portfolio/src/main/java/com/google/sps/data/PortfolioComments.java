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

package com.google.sps.data;

import java.util.ArrayList;


/** Class containing list of Comments for Jalyn's Portfolio */
public final class PortfolioComments {

  private final ArrayList<String> masterCommentList = new ArrayList<>();

  // Creates an instance of PortfolioComments with a hard-coded comment
  // as the first comment in the list
  public PortfolioComments() {
    addComment("This is the first comment in masterCommentList");
  }
  
  // Adds provided comment to the masterCommentList data structure
  public void addComment(String comment){
    masterCommentList.add(comment);
  }

  // Calling this method will give back the comment at that provided index
  // OR an IndexOutOfBoundsException if < 0 || > masterCommentList.size()
  public String getComment(int index){
    if(index >= masterCommentList.size() || index < 0){
      throws new IndexOutOfBoundsException("There is not a comment at the index");
    }
    return masterCommentList.get(index);
  }

  // Returns masterCommentList's size
  public int getMasterCommentListSize(){
    return masterCommentList.size();
    }
}
