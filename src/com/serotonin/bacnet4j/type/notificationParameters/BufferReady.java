/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * Copyright (C) 2006-2009 Serotonin Software Technologies Inc. http://serotoninsoftware.com
 * @author Matthew Lohbihler
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 */
package com.serotonin.bacnet4j.type.notificationParameters;

import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.type.constructed.DeviceObjectPropertyReference;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.util.queue.ByteQueue;

public class BufferReady extends NotificationParameters {
    private static final long serialVersionUID = 1210370718867995350L;

    public static final byte TYPE_ID = 10;

    private final DeviceObjectPropertyReference bufferProperty;
    private final UnsignedInteger previousNotification;
    private final UnsignedInteger currentNotification;

    public BufferReady(DeviceObjectPropertyReference bufferProperty, UnsignedInteger previousNotification,
            UnsignedInteger currentNotification) {
        this.bufferProperty = bufferProperty;
        this.previousNotification = previousNotification;
        this.currentNotification = currentNotification;
    }

    @Override
    protected void writeImpl(ByteQueue queue) {
        write(queue, bufferProperty, 0);
        write(queue, previousNotification, 1);
        write(queue, currentNotification, 2);
    }

    public BufferReady(ByteQueue queue) throws BACnetException {
        bufferProperty = read(queue, DeviceObjectPropertyReference.class, 0);
        previousNotification = read(queue, UnsignedInteger.class, 1);
        currentNotification = read(queue, UnsignedInteger.class, 2);
    }

    @Override
    protected int getTypeId() {
        return TYPE_ID;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((bufferProperty == null) ? 0 : bufferProperty.hashCode());
        result = PRIME * result + ((currentNotification == null) ? 0 : currentNotification.hashCode());
        result = PRIME * result + ((previousNotification == null) ? 0 : previousNotification.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final BufferReady other = (BufferReady) obj;
        if (bufferProperty == null) {
            if (other.bufferProperty != null)
                return false;
        }
        else if (!bufferProperty.equals(other.bufferProperty))
            return false;
        if (currentNotification == null) {
            if (other.currentNotification != null)
                return false;
        }
        else if (!currentNotification.equals(other.currentNotification))
            return false;
        if (previousNotification == null) {
            if (other.previousNotification != null)
                return false;
        }
        else if (!previousNotification.equals(other.previousNotification))
            return false;
        return true;
    }
}
