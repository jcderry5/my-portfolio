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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    //throw new UnsupportedOperationException("TODO: Implement this method.");
    
    List<TimeRange> availableTimes = new ArrayList<>();
    // Edge Case:
    // If the event lasts longer than a day, there is no available Time
    if(!eventDurationLessThanDay(request)){
      return availableTimes;
    }
    List<TimeRange> busyTimeRange = extractTimeRange(events);
    sortTimeRange(busyTimeRange);
    System.out.println("Busy time range: " + busyTimeRange);
    availableTimes = findAvailableTime(busyTimeRange);
    System.out.println(availableTimes);
    
    return availableTimes;
  }

  private List<TimeRange> extractTimeRange(Collection<Event> events){
    List<TimeRange> busyTimes = new ArrayList<>();
    Iterator<Event> iterateThroughEvents = events.iterator();
    while(iterateThroughEvents.hasNext()){
      Event currEvent = iterateThroughEvents.next();
      busyTimes.add(currEvent.getWhen());
    }
    return busyTimes;
  }

  private void sortTimeRange(List<TimeRange> events){
    Collections.sort(events, TimeRange.ORDER_BY_START);
  }

  private List<TimeRange> findAvailableTime(List<TimeRange> sortedBusyTimes){
    List<TimeRange> availableTimes = new ArrayList<>();
    int currTimeMarker = TimeRange.START_OF_DAY;
    int indexOfBusyTimes = 0;
    // handle edge case of no busy time slots
    if(sortedBusyTimes.size() == 0){
        availableTimes.add(TimeRange.WHOLE_DAY);
        return availableTimes;
    }
    System.out.println("Size of sortedBusyTimes: " + sortedBusyTimes.size());
    while(currTimeMarker != TimeRange.END_OF_DAY && indexOfBusyTimes <= sortedBusyTimes.size()){
      TimeRange currBusyTime = sortedBusyTimes.get(indexOfBusyTimes);
      int currDurationOfAvailableTime = currBusyTime.start() - currTimeMarker;
      TimeRange currTR = TimeRange.fromStartDuration(currTimeMarker, currDurationOfAvailableTime);
      availableTimes.add(currTR);
      currTimeMarker = currBusyTime.end();
      indexOfBusyTimes++; // This is where i can work with overlaps
    }
    // In order to get the final time range to the end of the day
    if(indexOfBusyTimes == sortedBusyTimes.size() && currTimeMarker != TimeRange.END_OF_DAY){
      int durationFromTimeMarkerToEndOfDay = TimeRange.END_OF_DAY - currTimeMarker;
      availableTimes.add(TimeRange.fromStartDuration(currTimeMarker, durationFromTimeMarkerToEndOfDay));
    }
    return availableTimes;
  }

  private boolean eventDurationLessThanDay(MeetingRequest request){
    return request.getDuration() < TimeRange.WHOLE_DAY.duration();
  }
}

