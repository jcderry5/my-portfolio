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
    
    //SortedSet<TimeRange> mandatoryEvents = sortBusyTimes(events, request);
    List<TimeRange> busyTimeRange = extractTimeRange(events);
    secondSortAlgo(busyTimeRange);
    System.out.println(busyTimeRange);
    
  }

    /**
  private SortedSet<TimeRange> sortBusyTimes(Collection<Event> events, MeetingRequest request){
    SortedSet<TimeRange> mandatoryEvents = new TreeSet<>(TimeRange.ORDER_BY_START);

    for(Event currEvent: events){
        //check to see if any of the attendees are in currEvent
        for(String currAttendee : currEvent.getAttendee()){
            if(request.getAttendees().contains(currAttendee)){
                mandatoryEvents.add(currEvent.getWhen());
                // if one mandatory attendee is going to a prev. scheduled event, that 
                // prev. scheduled event's time slot is considered non-available time slot
                continue currEvent; 
            }
        }
    }
    return mandatoryEvents;
  }
    */

  private List<TimeRange> extractTimeRange(Collection<Event> events){
    List<TimeRange> busyTimes = new ArrayList<>();
    Iterator<Event> iterateThroughEvents = events.iterator();
    while(iterateThroughEvents.hasNext()){
      Event currEvent = iterateThroughEvents.next();
      busyTimes.add(currEvent.getWhen());
    }
    return busyTimes;
  }

  private void secondSortAlgo(List<TimeRange> events){
   Collections.sort(events, TimeRange.ORDER_BY_START);
  }
}

