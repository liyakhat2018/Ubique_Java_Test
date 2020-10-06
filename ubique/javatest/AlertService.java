package com.ubique.javatest;

import java.util.Date;
import java.util.UUID;

class AlertService {

	private final MapAlertDAO storage = new MapAlertDAO();

	public AlertService(AlertDAO alertDAO) {
		super();

	}

	public UUID raiseAlert() {
		return this.storage.addAlert(new Date());
	}

	public Date getAlertTime(UUID id) {
		return this.storage.getAlert(id);
	}
}

//•	The raiseAlert and getAlertTime methods should use the object passed through the constructor.