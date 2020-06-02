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

/**
 * Fetches stats from the servers and adds them to the DOM.
 */
function generateDiscussion() {
  	
    fetch('/data').then(response => response.json()).then((discussion) => {
        // discussion is an object, not a string, so we have to
        // reference its fields to create HTML content

        console.log(discussion);

        const discussionListElement = document.getElementById('discussion-container');
        discussionListElement.innerHTML = '';
    	discussionListElement.appendChild(
        	createListElement('Comment #0: ' + discussion.Comment0));
        discussionListElement.appendChild(
        	createListElement('Comment #1: ' + discussion.Comment1));
        discussionListElement.appendChild(
        	createListElement('Comment #2: ' + discussion.Comment2));
  	});
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}
