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
  	console.log('Fetching discussion.');

    // fetch() function returns a promise
    const discussionLogPromise = fetch('/data');

	// When the request is complete, pass the response into parseJSON()
    discussionLogPromise.then(parseJSON);
}


// This function will handle the response and turn it to json
function parseJSON(response){
    console.log('Parse the response into JSON');

    const discussionLog = response.json();

    discussionLog.then(addToContainer);
}

// This function will add each comment to the discussion container
function addToContainer(discussion_log){

	console.log('Adding comments to the discussion-container');
    // Build the list of previous comments
    const discussionListElement = document.getElementById('discussion-container');
    console.log('Outside the forEach loop.');
    console.log(discussion_log);
    discussion_log.forEach((userInput) => {
        console.log('Inside the forEach loop.');
        discussionListElement.appendChild(createElement(userInput));
    })
    
}

/** Creates an <li> element containing text. */
function createElement(userInput) {
	
    //Printing userInput for testing purposes
    console.log('Inside the createElement function');
    console.log(userInput);

    // Create Body Element that userName and userComment will be in
    const commentBody = document.createElement('div');
    commentBody.className = 'comment-body';

	// Create Title Element that contains the userName
	const titleElement = document.createElement('h2');
    titleElement.className = 'user-title';
    titleElement.innerText = userInput.userName;

	// Create Comment Element that Comment will go in
    const commentElement = document.createElement('p');
    commentElement.className = 'comment-box';
    commentElement.innerText = userInput.userComment;

	// Create a button element that allows the user to delete comment
    // TODO: Make functioning
    const deleteButtonElement = document.createElement('button');
  	deleteButtonElement.innerText = 'Delete';
  	deleteButtonElement.addEventListener('click', () => {
    	deleteComment(comment);

    	// Remove the comment from the DOM.
    	commentBody.remove();
  	});
	
    // Add a horizontal line break between comments
    const horizLineElement = document.createElement('hr');

    //Append all children of the comment body to comment body
    commentBody.appendChild(titleElement);
    commentBody.appendChild(commentElement);
    commentBody.appendChild(deleteButtonElement);
    commentBody.appendChild(horizLineElement);
    return commentBody;
}

/** Tells the server to delete the comment. */
function deleteComment(comment) {
  const params = new URLSearchParams();
  params.append('id', comment.id);
  fetch('/delete-comment', {method: 'POST', body: params});
}