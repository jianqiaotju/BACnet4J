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
package com.serotonin.bacnet4j.service.confirmed;

import com.serotonin.bacnet4j.LocalDevice;
import com.serotonin.bacnet4j.Network;
import com.serotonin.bacnet4j.exception.BACnetException;
import com.serotonin.bacnet4j.exception.NotImplementedException;
import com.serotonin.bacnet4j.service.acknowledgement.AcknowledgementService;
import com.serotonin.bacnet4j.type.constructed.Address;
import com.serotonin.bacnet4j.type.primitive.OctetString;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;
import com.serotonin.util.queue.ByteQueue;

public class VtDataRequest extends ConfirmedRequestService {
    private static final long serialVersionUID = 5285787585416136977L;

    public static final byte TYPE_ID = 23;

    private final UnsignedInteger vtSessionIdentifier;
    private final OctetString vtNewData;
    private final UnsignedInteger vtDataFlag;

    public VtDataRequest(UnsignedInteger vtSessionIdentifier, OctetString vtNewData, UnsignedInteger vtDataFlag) {
        this.vtSessionIdentifier = vtSessionIdentifier;
        this.vtNewData = vtNewData;
        this.vtDataFlag = vtDataFlag;
    }

    @Override
    public byte getChoiceId() {
        return TYPE_ID;
    }

    @Override
    public void write(ByteQueue queue) {
        write(queue, vtSessionIdentifier);
        write(queue, vtNewData);
        write(queue, vtDataFlag);
    }

    VtDataRequest(ByteQueue queue) throws BACnetException {
        vtSessionIdentifier = read(queue, UnsignedInteger.class);
        vtNewData = read(queue, OctetString.class);
        vtDataFlag = read(queue, UnsignedInteger.class);
    }

    @Override
    public AcknowledgementService handle(LocalDevice localDevice, Address from, Network network) throws BACnetException {
        throw new NotImplementedException();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((vtDataFlag == null) ? 0 : vtDataFlag.hashCode());
        result = PRIME * result + ((vtNewData == null) ? 0 : vtNewData.hashCode());
        result = PRIME * result + ((vtSessionIdentifier == null) ? 0 : vtSessionIdentifier.hashCode());
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
        final VtDataRequest other = (VtDataRequest) obj;
        if (vtDataFlag == null) {
            if (other.vtDataFlag != null)
                return false;
        }
        else if (!vtDataFlag.equals(other.vtDataFlag))
            return false;
        if (vtNewData == null) {
            if (other.vtNewData != null)
                return false;
        }
        else if (!vtNewData.equals(other.vtNewData))
            return false;
        if (vtSessionIdentifier == null) {
            if (other.vtSessionIdentifier != null)
                return false;
        }
        else if (!vtSessionIdentifier.equals(other.vtSessionIdentifier))
            return false;
        return true;
    }
}
