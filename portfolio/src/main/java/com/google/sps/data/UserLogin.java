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

package com.google.sps.data;

/** A user's entry in the discussion log. */
public final class UserLogin {

  private final long id;
  private final String userEmail;
  private final long timestamp;
  private final boolean loggedIn;

  public UserLogin(long id, String userEmail, long timestamp, boolean loggedIn) {
    this.id = id;
    this.userEmail = userEmail;
    this.timestamp = timestamp;
    this.loggedIn = loggedIn;
  }
}
