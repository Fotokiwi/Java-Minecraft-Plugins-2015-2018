package org.community.pointsOfInterest.Honorlist;

import java.util.UUID;

public class HonorlistEntry {
	private UUID id;
	private long timeStamp;
	
	public HonorlistEntry(UUID id, long timeStamp){
		this.setId(id);
		this.setTimeStamp(timeStamp);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
