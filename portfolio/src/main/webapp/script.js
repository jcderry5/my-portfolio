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

/**
 * Fetches stats from the servers and adds them to the DOM.
 */
function generateDiscussion() {
  // fetch() function returns a promise
  const discussionLogPromise = fetch('/data');

  // When the request is complete, pass the response into convertToJson()
  discussionLogPromise.then(convertToJson);
}

/**
* This function will convert the response to JSON and pass json
* to the addToContainer function
*/
function convertToJson(response){
  const discussionLog = response.json();
  discussionLog.then(addToContainer);
}

/*
* This function will add each comment to the discussion container
*/
function addToContainer(discussion_log){
  // Build the list of previous comments
  const discussionListElement = document.getElementById('discussion-container');
  discussion_log.masterCommentList.forEach((line) =>
    discussionListElement.appendChild(createListElement(line))
  );
}

/** 
* Creates an <li> element containing text. 
*/
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}
