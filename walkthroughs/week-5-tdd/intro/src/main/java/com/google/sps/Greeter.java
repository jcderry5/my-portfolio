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

package com.google.sps;

/**
 * Utility class for creating greeting messages.
 */
public class Greeter {
    final int asciiForUppercaseA = 65;
    final int asciiForUppercaseZ = 90;
    final int asciiForLowercaseA = 97;
    final int asciiForLowercaseZ = 122;
  /**
   * Returns a greeting for the given name.
   */
  public String greet(String name) {
    return "Hello " + trimName(name);
  }

  public String trimName(String name){
      String result = "";
      for(int index = 0; index < name.length(); index++){
          char currentChar = name.charAt(index);
          // Check if the currentChar is a letter based on ascii value
          if((currentChar >= asciiForUppercaseA && currentChar <= asciiForUppercaseZ) 
            || (currentChar >= asciiForLowercaseA && currentChar <= asciiForLowercaseZ)){
              result += currentChar;
          }
      }
      return result;
  }
}