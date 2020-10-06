/**
 * 
 */
package com.ubique.javatest;

import java.util.Date;
import java.util.UUID;

/**
 * @author Liyakath Ali
 *
 */
interface AlertDAO {
	
	public UUID addAlert(Date time);
		

	public Date getAlert(UUID id);

}
