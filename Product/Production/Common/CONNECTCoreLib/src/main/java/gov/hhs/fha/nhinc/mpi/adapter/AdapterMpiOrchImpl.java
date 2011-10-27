/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *  
 * Copyright 2010(Year date of delivery) United States Government, as represented by the Secretary of Health and Human Services.  All rights reserved.
 *  
 */
package gov.hhs.fha.nhinc.mpi.adapter;

import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;
import gov.hhs.fha.nhinc.logger.ConnectLogFactory;
import gov.hhs.fha.nhinc.logger.TransactionType;
import gov.hhs.fha.nhinc.logger.defaulttransaction.DefaultTransactionLog;
import gov.hhs.fha.nhinc.mpi.adapter.component.proxy.AdapterComponentMpiProxy;
import gov.hhs.fha.nhinc.mpi.adapter.component.proxy.AdapterComponentMpiProxyObjectFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hl7.v3.PRPAIN201305UV02;
import org.hl7.v3.PRPAIN201306UV02;

/**
 * This is the business logic for the AdapterMpi.  This is a thin layer,
 * it simply takes the request and calls the AdapterComponentMpi.
 *
 * @author Les Westberg
 */
public class AdapterMpiOrchImpl
{
    private static Log log = LogFactory.getLog(AdapterMpiOrchImpl.class);
    private static DefaultTransactionLog transactionLog = (DefaultTransactionLog) ConnectLogFactory.getTransactionLog(TransactionType.PD_INTERNAL_TRANSACTION);

    /**
     * Send the patient query request to the actual MPI that is implemented
     *
     * @param findCandidatesRequest The request containing the query information.
     * @param assertion The assertion for this message.
     * @return The results of the query.
     */
    public PRPAIN201306UV02 query(PRPAIN201305UV02 findCandidatesRequest, AssertionType assertion)
    {
        log.debug("Entering AdapterMpiOrchImpl.query method...");
        transactionLog.begin();

        AdapterComponentMpiProxy oMpiProxy = null;
        AdapterComponentMpiProxyObjectFactory oFactory = new AdapterComponentMpiProxyObjectFactory();
        oMpiProxy = oFactory.getAdapterComponentMpiProxy();
        PRPAIN201306UV02 oResponse = oMpiProxy.findCandidates(findCandidatesRequest, assertion);
        transactionLog.end();
        return oResponse;
    }
}
